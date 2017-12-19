/**
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.chingtech.sample.view.baidu_tts;

import android.Manifest;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.LogUtils;
import chingtech.library.utils.StatusBarHelper;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.chingtech.sample.OfflineResource;
import com.chingtech.sample.R;
import com.chingtech.sample.view.baidu_tts.control.InitConfig;
import com.chingtech.sample.view.baidu_tts.control.MySyntherizer;
import com.chingtech.sample.view.baidu_tts.control.NonBlockSyntherizer;
import com.chingtech.sample.view.baidu_tts.listener.UiMessageListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.chingtech.sample.Constant.*;

/**
 * 合成demo。含在线和离线，没有纯离线功能。
 * 根据网络状况优先走在线，在线时访问服务器失败后转为离线。
 */
public class SynthActivity extends BaseActivity implements MainHandlerConstant {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar  toolbar;

    @BindView(R.id.speak)
    Button mSpeak;
    @BindView(R.id.pause)
    Button mPause;
    @BindView(R.id.resume)
    Button mResume;
    @BindView(R.id.stop)
    Button mStop;
    @BindView(R.id.synthesize)
    Button mSynthesize;
    @BindView(R.id.batchSpeak)
    Button mBatchSpeak;
    @BindView(R.id.loadModel)
    Button mLoadModel;

    protected Button[] buttons;
    @BindView(R.id.help)
    Button   mHelp;
    @BindView(R.id.input)
    EditText mInput;
    @BindView(R.id.showText)
    TextView mShowText;
    protected Handler mainHandler;


    // ================== 初始化参数设置开始 ==========================

    // TtsMode.MIX; 离在线融合，在线优先； TtsMode.ONLINE 纯在线； 没有纯离线
    protected TtsMode ttsMode = TtsMode.MIX;

    // 离线发音选择，VOICE_FEMALE即为离线女声发音。
    // assets目录下bd_etts_speech_female.data为离线男声模型；bd_etts_speech_female.data为离线女声模型
    protected String offlineVoice = OfflineResource.VOICE_MALE;

    // ===============初始化参数设置完毕，更多合成参数请至getParams()方法中设置 =================

    // 主控制类，所有合成控制方法从这个类开始
    protected MySyntherizer synthesizer;

    protected String DESC = "请先看完说明。之后点击“合成并播放”按钮即可正常测试。\n"
            + "测试离线合成功能需要首次联网。\n"
            + "纯在线请修改代码里ttsMode为TtsMode.ONLINE， 没有纯离线。\n"
            + "本Demo的默认参数设置为wifi情况下在线合成, 其它网络（包括4G）使用离线合成。 在线普通女声发音，离线男声发音.\n"
            + "合成可以多次调用，SDK内部有缓存队列，会依次完成。\n\n";

    private final String TAG = "SynthActivity";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_synth;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("百度语音合成");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected void init() {
        mainHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handle(msg);
            }

        };
        buttons = new Button[] {mSpeak, mPause, mResume, mStop, mSynthesize, mBatchSpeak,
                mLoadModel};
        mShowText.setMovementMethod(new ScrollingMovementMethod());

        initPermission(); // android 6.0以上动态权限申请

        mShowText.setText(DESC);

        initialButtons(); // 配置onclick
        initialTts(); // 初始化TTS引擎
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }

    @Override
    protected void loadData() {
    }

    /**
     * 界面上的按钮对应方法
     */
    @OnClick({R.id.speak, R.id.stop, R.id.pause, R.id.resume, R.id.help, R.id.synthesize,
            R.id.batchSpeak, R.id.loadModel})
    public void onViewClicked(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.speak:
                speak(); // 合成并播放
                break;
            case R.id.synthesize:
                synthesize(); // 只合成不播放
                break;
            case R.id.batchSpeak:
                batchSpeak(); //  批量合成并播放
                break;
            case R.id.loadModel:
                loadModel(); // 停止合成引擎
                break;
            case R.id.pause:
                pause(); // 播放暂停
                break;
            case R.id.resume:
                resume(); // 播放恢复
                break;
            case R.id.stop:
                stop(); // 停止合成引擎
                break;
            case R.id.help: // 启动另一个activity
                mShowText.setText(DESC);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化引擎，需要的参数均在InitConfig类里
     * <p>
     * DEMO中提供了3个SpeechSynthesizerListener的实现
     * MessageListener 仅仅用log.i记录日志，在logcat中可以看见
     * UiMessageListener 在MessageListener的基础上，对handler发送消息，实现UI的文字更新
     * FileSaveListener 在UiMessageListener的基础上，使用 onSynthesizeDataArrived回调，获取音频流
     */
    protected void initialTts() {
        // 设置初始化参数
        SpeechSynthesizerListener listener = new UiMessageListener(
                mainHandler); // 此处可以改为 含有您业务逻辑的SpeechSynthesizerListener的实现类

        Map<String, String> params = getParams();

        // appId appKey secretKey 网站上您申请的应用获取。注意使用离线合成功能的话，需要应用中填写您app的包名。包名在build.gradle中获取。
        InitConfig initConfig = new InitConfig(appId, appKey, secretKey, ttsMode, offlineVoice,
                                               params, listener);

        synthesizer = new NonBlockSyntherizer(this, initConfig,
                                              mainHandler); // 此处可以改为MySyntherizer 了解调用过程
    }

    /**
     * 合成的参数，可以初始化时填写，也可以在合成前设置。
     *
     * @return
     */
    protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        // 以下参数均为选填
        params.put(SpeechSynthesizer.PARAM_SPEAKER,
                   "4"); // 设置在线发声音人： 0 普通女声（默认） 1 普通男声 2 特别男声 3 情感男声<度逍遥> 4 情感儿童声<度丫丫>
        params.put(SpeechSynthesizer.PARAM_VOLUME, "5"); // 设置合成的音量，0-9 ，默认 5
        params.put(SpeechSynthesizer.PARAM_SPEED, "5");// 设置合成的语速，0-9 ，默认 5
        params.put(SpeechSynthesizer.PARAM_PITCH, "5");// 设置合成的语调，0-9 ，默认 5
        params.put(SpeechSynthesizer.PARAM_MIX_MODE,
                   SpeechSynthesizer.MIX_MODE_DEFAULT);         // 该参数设置为TtsMode.MIX生效。即纯在线模式不生效。
        // MIX_MODE_DEFAULT 默认 ，wifi状态下使用在线，非wifi离线。在线状态下，请求超时6s自动转离线
        // MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI wifi状态下使用在线，非wifi离线。在线状态下， 请求超时1.2s自动转离线
        // MIX_MODE_HIGH_SPEED_NETWORK ， 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
        // MIX_MODE_HIGH_SPEED_SYNTHESIZE, 2G 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
        return params;
    }

    /**
     * speak 实际上是调用 synthesize后，获取音频流，然后播放。
     * 获取音频流的方式见SaveFileActivity及FileSaveListener
     * 需要合成的文本text的长度不能超过1024个GBK字节。
     */
    private void speak() {
        mShowText.setText("");
        String text = mInput.getText().toString();
        //需要合成的文本text的长度不能超过1024个GBK字节。
        if (TextUtils.isEmpty(mInput.getText())) {
            text = "欢迎使用百度语音合成SDK,百度语音为你提供支持。";
            mInput.setText(text);
        }
        // 合成前可以修改参数：
        // Map<String, String> params = getParams();
        // synthesizer.setParams(params);
        int result = synthesizer.speak(text);
        checkResult(result, "speak");
    }


    /**
     * 合成但是不播放，
     * 音频流保存为文件的方法可以参见SaveFileActivity及FileSaveListener
     */
    private void synthesize() {
        mShowText.setText("");
        String text = this.mInput.getText().toString();
        if (TextUtils.isEmpty(mInput.getText())) {
            text = "欢迎使用百度语音合成SDK,百度语音为你提供支持。";
            mInput.setText(text);
        }
        int result = synthesizer.synthesize(text);
        checkResult(result, "synthesize");
    }

    /**
     * 批量播放
     */
    private void batchSpeak() {
        mShowText.setText("");
        List<Pair<String, String>> texts = new ArrayList<>();
        texts.add(new Pair<>("开始批量播放，", "a0"));
        texts.add(new Pair<>("123456，", "a1"));
        texts.add(new Pair<>("欢迎使用百度语音，，，", "a2"));
        texts.add(new Pair<>("重(zhong4)量这个是多音字示例", "a3"));
        int result = synthesizer.batchSpeak(texts);
        checkResult(result, "batchSpeak");
    }


    /**
     * 切换离线发音。注意需要添加额外的判断：引擎在合成时该方法不能调用
     */
    private void loadModel() {
        if (offlineVoice.equals(OfflineResource.VOICE_FEMALE)) {
            offlineVoice = OfflineResource.VOICE_MALE;
        } else {
            offlineVoice = OfflineResource.VOICE_FEMALE;
        }
        int result = synthesizer.loadModel(offlineVoice);
        checkResult(result, "loadModel");
    }

    private void checkResult(int result, String method) {
        if (result != 0) {
            toPrint("error code :"
                            + result
                            + " method:"
                            + method
                            + ", 错误码文档:http://yuyin.baidu.com/docs/tts/122 ");
        }
    }


    /**
     * 暂停播放。仅调用speak后生效
     */
    private void pause() {
        int result = synthesizer.pause();
        checkResult(result, "pause");
    }

    /**
     * 继续播放。仅调用speak后生效，调用pause生效
     */
    private void resume() {
        int result = synthesizer.resume();
        checkResult(result, "resume");
    }

    /*
     * 停止合成引擎。即停止播放，合成，清空内部合成队列。
     */
    private void stop() {
        int result = synthesizer.stop();
        checkResult(result, "stop");
    }

    @Override
    protected void onDestroy() {
        synthesizer.release();
        Log.i(TAG, "释放资源成功");
        super.onDestroy();
    }

    protected void handle(Message msg) {
        switch (msg.what) {
            case INIT_SUCCESS:
                for (Button b : buttons) {
                    b.setEnabled(true);
                }
                msg.what = PRINT;
                break;
        }
        handle2(msg);
    }

    private void initialButtons() {
        for (Button b : buttons) {
            b.setEnabled(false); // 先禁用按钮，等待引擎初始化后打开。
        }
    }

    protected void handle2(Message msg) {
        int what = msg.what;
        switch (what) {
            case PRINT:
                print(msg);
                break;
            case UI_CHANGE_INPUT_TEXT_SELECTION:
                if (msg.arg1 <= mInput.getText().length()) {
                    mInput.setSelection(0, msg.arg1);
                }
                break;
            case UI_CHANGE_SYNTHES_TEXT_SELECTION:
                SpannableString colorfulText = new SpannableString(mInput.getText().toString());
                if (msg.arg1 <= colorfulText.toString().length()) {
                    colorfulText.setSpan(new ForegroundColorSpan(Color.GRAY), 0, msg.arg1,
                                         Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mInput.setText(colorfulText);
                }
                break;
            default:
                break;
        }
    }

    protected void toPrint(String str) {
        Message msg = Message.obtain();
        msg.obj = str;
        mainHandler.sendMessage(msg);
    }

    private void print(Message msg) {
        String message = (String) msg.obj;
        if (message != null) {
            scrollLog(message);
        }
    }

    private void scrollLog(String message) {
        Spannable colorMessage = new SpannableString(message + "\n");
        colorMessage.setSpan(new ForegroundColorSpan(0xff0000ff), 0, message.length(),
                             Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mShowText.append(colorMessage);
        Layout layout = mShowText.getLayout();
        if (layout != null) {
            int scrollAmount = layout.getLineTop(mShowText.getLineCount()) - mShowText.getHeight();
            if (scrollAmount > 0) {
                mShowText.scrollTo(0, scrollAmount + mShowText.getCompoundPaddingBottom());
            } else {
                mShowText.scrollTo(0, 0);
            }
        }
    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        // 6.0权限请求
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.INTERNET,
                                  Manifest.permission.ACCESS_NETWORK_STATE,
                                  Manifest.permission.MODIFY_AUDIO_SETTINGS,
                                  Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                  Manifest.permission.WRITE_SETTINGS,
                                  Manifest.permission.READ_PHONE_STATE,
                                  Manifest.permission.ACCESS_WIFI_STATE,
                                  Manifest.permission.CHANGE_WIFI_STATE).subscribe(permission -> {
            if (permission.granted) {
                LogUtils.d("permission", permission.name + "开启");
            } else if (permission.shouldShowRequestPermissionRationale) {
                LogUtils.d("permission", permission.name + "再次询问");
            } else {
                LogUtils.d("permission", permission.name + "被拒绝");
            }
        });
    }
}
