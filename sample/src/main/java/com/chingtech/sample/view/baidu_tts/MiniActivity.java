package com.chingtech.sample.view.baidu_tts;

import android.Manifest;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.LogUtils;
import chingtech.library.utils.StatusBarHelper;
import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.chingtech.sample.R;
import com.chingtech.sample.view.baidu_tts.listener.UiMessageListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import java.io.File;

import static com.chingtech.sample.Constant.*;

/**
 * 除了SDK，该类没有任何依赖，可以直接复制进你的项目
 *
 * TEMP_DIR = "/sdcard/baiduTTS"; //重要！请手动将assets目录下的3个dat 文件复制到该目录
 * Created by fujiayi on 2017/9/14.
 */
public class MiniActivity extends BaseActivity {

    private static final String TEXT = "欢迎使用百度语音合成，请在代码中修改合成文本";

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar  toolbar;

    @BindView(R.id.layout)
    LinearLayout layout;

    @BindView(R.id.showText)
    TextView mShowText;

    // ================== 初始化参数设置开始 ==========================

    // TtsMode.MIX; 离在线融合，在线优先； TtsMode.ONLINE 纯在线； 没有纯离线
    private TtsMode ttsMode = TtsMode.MIX;

    // ================选择TtsMode.ONLINE  不需要设置以下参数; 选择TtsMode.MIX 需要设置下面2个离线资源文件的路径
    private static String TEMP_DIR = "/sdcard/baiduTTS"; //重要！请手动将assets目录下的3个dat 文件复制到该目录

    private static String TEXT_FILENAME = TEMP_DIR + "/" + "bd_etts_text.dat"; // 请确保该PATH下有这个文件

    private static String MODEL_FILENAME = TEMP_DIR + "/" + "bd_etts_speech_male.dat";
    // 请确保该PATH下有这个文件 male是男声 female女声

    // ===============初始化参数设置完毕，更多合成参数请至getParams()方法中设置 =================

    protected SpeechSynthesizer mSpeechSynthesizer;

    protected Handler mainHandler;

    private static final String DESC = "精简版合成，仅给出示例集成合成的调用过程。可以测试离线合成功能，首次使用请联网。\n"
            + "其中initTTS方法需要在新线程调用，否则引起UI阻塞。\n"
            + "纯在线请修改代码里ttsMode为TtsMode.ONLINE， 没有纯离线。\n"
            + "离线功能需要手动将assets目录下的资源文件复制到TEMP_DIR =/sdcard/baiduTTS \n"
            + "完整的SDK调用方式可以参见MainActivity\n\n";

    private static final String TAG = "MiniActivity";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mini;
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
        mShowText.setText(DESC);

        mainHandler = new Handler() {
            /*
             * @param msg
             */
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.obj != null) {
                    print(msg.obj.toString());
                }
            }
        };

        initPermission();
        initTTs();
    }

    @Override
    protected View injectTarget() {
        return layout;
    }

    @Override
    protected void loadData() {
    }

    /**
     * 注意此处为了说明流程，故意在UI线程中调用。
     * 实际集成中，该方法一定在新线程中调用，并且该线程不能结束。具体可以参考NonBlockSyntherizer的写法
     */
    private void initTTs() {
        boolean isMix = ttsMode.equals(TtsMode.MIX);
        boolean isSuccess;
        if (isMix) {
            // 检查2个离线资源是否可读
            isSuccess = checkOfflineResources();
            if (!isSuccess) {
                return;
            } else {
                print("离线资源存在并且可读, 目录：" + TEMP_DIR);
            }
        }
        SpeechSynthesizerListener listener = new UiMessageListener(
                mainHandler); // 日志更新在UI中，可以换成MessageListener，在logcat中查看日志
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        mSpeechSynthesizer.setContext(this);
        mSpeechSynthesizer.setSpeechSynthesizerListener(listener);

        int result = mSpeechSynthesizer.setAppId(appId);
        checkResult(result, "setAppId");
        result = mSpeechSynthesizer.setApiKey(appKey, secretKey);
        checkResult(result, "setApiKey");
        if (isMix) {
            // 检查离线授权文件是否下载成功，离线授权文件联网时SDK自动下载管理，有效期3年，3年后的最后一个月自动更新。
            isSuccess = checkAuth();
            if (!isSuccess) {
                return;
            }
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE,
                                        TEXT_FILENAME); // 文本模型文件路径 (离线引擎使用)
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE,
                                        MODEL_FILENAME); // 声学模型文件路径 (离线引擎使用)
        }

        // 以下setParam 参数选填。不填写则默认值生效
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER,
                                    "0"); // 设置在线发声音人： 0 普通女声（默认） 1 普通男声 2 特别男声 3 情感男声<度逍遥> 4 情感儿童声<度丫丫>
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "9"); // 设置合成的音量，0-9 ，默认 5
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED, "5");// 设置合成的语速，0-9 ，默认 5
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_PITCH, "5");// 设置合成的语调，0-9 ，默认 5

        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE,
                                    SpeechSynthesizer.MIX_MODE_DEFAULT);
        // 该参数设置为TtsMode.MIX生效。即纯在线模式不生效。
        // MIX_MODE_DEFAULT 默认 ，wifi状态下使用在线，非wifi离线。在线状态下，请求超时6s自动转离线
        // MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI wifi状态下使用在线，非wifi离线。在线状态下， 请求超时1.2s自动转离线
        // MIX_MODE_HIGH_SPEED_NETWORK ， 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
        // MIX_MODE_HIGH_SPEED_SYNTHESIZE, 2G 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线

        mSpeechSynthesizer.setAudioStreamType(AudioManager.MODE_IN_CALL);
        result = mSpeechSynthesizer.initTts(ttsMode);
        checkResult(result, "initTts");

    }

    /**
     * 检查appId ak sk 是否填写正确，另外检查官网应用内设置的包名是否与运行时的包名一致。本demo的包名定义在build.gradle文件中
     *
     * @return
     */
    private boolean checkAuth() {
        AuthInfo authInfo = mSpeechSynthesizer.auth(ttsMode);
        if (!authInfo.isSuccess()) {
            // 离线授权需要网站上的应用填写包名。本demo的包名是com.baidu.tts.sample，定义在build.gradle中
            String errorMsg = authInfo.getTtsError().getDetailMessage();
            print("【error】鉴权失败 errorMsg=" + errorMsg);
            return false;
        } else {
            print("验证通过，离线正式授权文件存在。");
            return true;
        }
    }

    /**
     * 检查 TEXT_FILENAME, MODEL_FILENAME 这2个文件是否存在，不存在请自行从assets目录里手动复制
     *
     * @return
     */
    private boolean checkOfflineResources() {
        String[] filenames = {TEXT_FILENAME, MODEL_FILENAME};
        for (String path : filenames) {
            File f = new File(path);
            if (!f.canRead()) {
                print("[ERROR] 文件不存在或者不可读取，请从assets目录复制改文件到：" + path);
                return false;
            }
        }
        return true;
    }

    private void speak() {
        /* 以下参数每次合成时都可以修改
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0"); // 设置在线发声音人： 0 普通女声（默认） 1 普通男声 2 特别男声 3 情感男声<度逍遥> 4 情感儿童声<度丫丫>
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "5"); // 设置合成的音量，0-9 ，默认 5
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED, "5");// 设置合成的语速，0-9 ，默认 5
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_PITCH, "5");// 设置合成的语调，0-9 ，默认 5
         *
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
         *  MIX_MODE_DEFAULT 默认 ，wifi状态下使用在线，非wifi离线。在线状态下，请求超时6s自动转离线
         *  MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI wifi状态下使用在线，非wifi离线。在线状态下， 请求超时1.2s自动转离线
         *  MIX_MODE_HIGH_SPEED_NETWORK ， 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
         *  MIX_MODE_HIGH_SPEED_SYNTHESIZE, 2G 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
         */

        mShowText.setText("");
        print("合成并播放 按钮已经点击");
        int result = mSpeechSynthesizer.speak(TEXT);
        checkResult(result, "speak");
    }

    private void stop() {
        print("停止合成引擎 按钮已经点击");
        int result = mSpeechSynthesizer.stop();
        checkResult(result, "stop");
    }

    private void resume() {
        print("继续播放 按钮已经点击");
        int result = mSpeechSynthesizer.resume();
        checkResult(result, "resume");
    }

    private void pause() {
        print("暂停播放 按钮已经点击");
        int result = mSpeechSynthesizer.pause();
        checkResult(result, "pause");
    }

    private void print(String message) {
        Log.i(TAG, message);
        mShowText.append(message + "\n");
    }

    @Override
    protected void onDestroy() {
        if (mSpeechSynthesizer != null) {
            mSpeechSynthesizer.stop();
            mSpeechSynthesizer.release();
            mSpeechSynthesizer = null;
            print("释放资源成功");
        }
        super.onDestroy();
    }

    private void checkResult(int result, String method) {
        if (result != 0) {
            print("error code :"
                          + result
                          + " method:"
                          + method
                          + ", 错误码文档:http://yuyin.baidu.com/docs/tts/122 ");
        }
    }

    @OnClick({R.id.speak, R.id.stop, R.id.pause, R.id.resume})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.speak:
                speak();
                break;
            case R.id.stop:
                stop();
                break;
            case R.id.pause:
                pause();
                break;
            case R.id.resume:
                resume();
                break;
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
