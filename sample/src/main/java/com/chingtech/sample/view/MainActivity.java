package com.chingtech.sample.view;

import static chingtech.library.utils.AddSpaceTextWatcher.SpaceType.*;
import static chingtech.library.utils.DESHelper.decrypt;
import static chingtech.library.utils.DESHelper.encrypt;
import static chingtech.library.utils.IdcardUtil.*;
import static chingtech.library.utils.StringUtils.*;
import static chingtech.library.utils.TimeUtils.*;

import android.content.*;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.*;

import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.ConversionUtils;
import chingtech.library.widget.*;
import chingtech.library.widget.SearchView;
import com.bumptech.glide.Glide;
import com.chingtech.sample.R;

import com.chingtech.sample.service.UpdateService;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.*;
import org.xutils.x;

import java.io.File;

import chingtech.library.utils.*;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.main_search_view_rsv)
    private SearchView searchView;
    @ViewInject(R.id.toolbar)
    private Toolbar    toolbar;
    @ViewInject(R.id.tv_title)
    private TextView   tvTitle;

    private AlertDialog dialog;

    private String[] items = {"item0", "item1", "item2", "item3"};

    @ViewInject(R.id.image1)
    private RoundImageView image1;
    @ViewInject(R.id.image2)
    private RoundImageView image2;
    @ViewInject(R.id.image3)
    private RoundImageView image3;

    private String date = "2017-04-13";

    private String datetime = "2017-04-13 17:32:21";

    @ViewInject(R.id.flipView)
    private FlipView easyFlipView;
    @ViewInject(R.id.flipView2)
    private FlipView easyFlipView2;

    @ViewInject(R.id.tv_number)
    private NumberAnimTextView number;

    private ProgressDialog progress;

    private BottomDialog mBottomDialog;

    private UpdateAppDialog hpd;

    @ViewInject(R.id.layout1)
    private FrameLayout layout;

    @ViewInject(R.id.tv_1)
    private TextView tv1;

    @ViewInject(R.id.tv_2)
    private TextView tv2;

    @ViewInject(R.id.tv_3)
    private TextView tv3;

    @ViewInject(R.id.tv_4)
    private TextView tv4;

    @ViewInject(R.id.tv_update_info)
    private TextView updateText;

    @ViewInject(R.id.pone_number)
    private EditText etPhone;
    @ViewInject(R.id.id_number)
    private EditText etIdCard;
    @ViewInject(R.id.idcard_number)
    private EditText etIdCardNo;
    @ViewInject(R.id.money)
    private EditText etMoney;

    @ViewInject(R.id.seekbar1)
    private RangeSeekBar seekbar1;
    @ViewInject(R.id.seekbar2)
    private RangeSeekBar seekbar2;
    @ViewInject(R.id.progress2_tv)
    private TextView     tv;

    private DecimalFormat df = new DecimalFormat("0.00");

    @ViewInject(R.id.switch_button)
    SwitchButton switchButton;

    @ViewInject(R.id.timer)
    private TextView timer;

    private MyCountDownTimer mc;

    private UpdateService.MyBinder binder;

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (UpdateService.MyBinder) service;
            updateText.setText("已连接");
        }
    };

    @Override
    protected void init() {
        initSeekBar();

        Intent intent = new Intent(this, UpdateService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);

        mc = new MyCountDownTimer(30000, 1000);
        mc.start();

        easyFlipView.setFlipDuration(1000);

        Log.i("Tag", "23132422.63232保留2位小数:" + doubleToString(23132422.63232));
        Log.i("Tag", "23132422.23282保留3位小数:" + round(23132422.23282, 3));
        Log.i("Tag", "23132422.93232转整数:" + round(23132422.93232, 0));

        Log.i("Tag", "金额格式化: " + roundMoney(new BigDecimal(23132422.23832)));
        Log.i("Tag", "金额格式化: " + roundMoney(new BigDecimal(-23132422.23832)));

        Log.i("Tag", "金额格式化: " + formatMoney(new BigDecimal(23132422.23832)));

        String idcard15 = "131128900923065";
        String idcard18 = "131128199009230658";
        Log.i("Tag", "131128199009230658身份证号码校验：" + isIdcard(idcard18));
        Log.i("Tag", "131128900923065身份证号码校验：" + isIdcard(idcard15));

        String str = "320125193206214815";
        try {
            System.out.println(encrypt(str, "@njytyl&"));
            System.out.println(
                    decrypt("68d151bd4ed0662abe2aaddd5d961ceb8e317efb78b9d420", "@njytyl&"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        conversion();

        ViewUtils.setViewWidth(this, tv2, 1 / 7f);
        ViewUtils.setViewHeight(this, layout, 1 / 2f);
        ViewUtils.setViewWidth(this, tv1, 1 / 2f);

        ViewUtils.setViewSize(this, tv3, 100, 100);

        ViewUtils.setViewSize(this, tv4, 1 / 3f);

        ViewUtils.setPricePoint(etMoney);

        new AddSpaceTextWatcher(etIdCard, 23).setSpaceType(bankCardNumberType);
        new AddSpaceTextWatcher(etPhone, 13).setSpaceType(mobilePhoneNumberType);
        new AddSpaceTextWatcher(etIdCardNo, 21).setSpaceType(IDCardNumberType);

        progress = new ProgressDialog(this);
        progress.setTitleText("正在登录");

        number.startNumber();
        ViewUtils.setUnderLine(number);

        Log.i("Tag", "----" + TimeUtils.getNowDateTime(TZ_FORMAT));
        Log.i("Tag", "----" + TimeUtils.getNowDateTime(Y_FORMAT));
        Log.i("Tag", "----" + TimeUtils.getNowDateTime(YY_FORMAT));
        Log.i("Tag", "----" + TimeUtils.getNowDateTime(M_FORMAT));
        Log.i("Tag", "----" + TimeUtils.getNowDateTime(D_FORMAT));
        Log.i("Tag", "----" + TimeUtils.getNowDateTime(H_12_FORMAT));
        Log.i("Tag", "----" + TimeUtils.getNowDateTime(H_24_FORMAT));
        Log.i("Tag", "----" + TimeUtils.getNowDateTime(MIN_FORMAT));
        Log.i("Tag", "----" + TimeUtils.getNowDateTime(S_FORMAT));

        Log.i("Tag", "=---------------" + TimeUtils.intervalDays("2016-12-31 12:22:22",
                                                                 "2017-01-01 01:01:01"));

        Log.i("Tag", "=======" + StringUtils.hangeToBig(3453450.23));

        String appname = AppUitls.getAppName(MainActivity.this);
        Log.i("tag", "appname:" + appname);
        String pkname = AppUitls.getPkName(MainActivity.this);
        Log.i("tag", "pkname:" + pkname);
        String versionname = AppUitls.getVersionName(MainActivity.this);
        Log.i("tag", "versionname:" + versionname);
        int versioncode = AppUitls.getVersionCode(MainActivity.this);
        Log.i("tag", "versioncode:" + versioncode);

        CharacterParser c = new CharacterParser();
        Log.i("Tag", "我们拼音: " + c.getSelling("我们"));

        String s00 = TimeUtils.formatDate(date, DATE_SHAORT_FORMAT);
        Log.i("Tag", s00);

        String s0 = TimeUtils.formatDate(date, Y_FORMAT);
        Log.i("Tag", s0);

        String s01 = TimeUtils.formatDate(date, Y_M_FORMAT);
        Log.i("Tag", s01);
        String s02 = TimeUtils.formatDate(date, D_FORMAT);
        Log.i("Tag", s02);
        String s03 = TimeUtils.formatDate(date, M_FORMAT);
        Log.i("Tag", s03);

        String s2 = TimeUtils.formatDateTime(datetime, TIME_FORMAT);
        Log.i("Tag", s2);

        String s3 = TimeUtils.formatDateTime(datetime, TIME_SHAORT_FORMAT);
        Log.i("Tag----------", s3);

        String s4 = TimeUtils.formatDateTime(datetime, DATE_TIME_HOUR_MIN_FORMAT);
        Log.i("Tag", s4);

        String s5 = TimeUtils.formatDateTime(datetime, TIME_SHAORT_FORMAT);
        Log.i("Tag=========", s5);

        String s6 = TimeUtils.formatDateTime(datetime, DATE_TIME_FORMAT);
        Log.i("Tag", s6);

        String s7 = TimeUtils.formatDateTime(datetime, TIME_MS_FORMAT);
        Log.i("Tag", s7);

        String s8 = TimeUtils.formatDateTime(datetime, H_24_FORMAT);
        Log.i("Tag", s8);

        String s9 = TimeUtils.formatDateTime(datetime, H_12_FORMAT);
        Log.i("Tag", s9);

        String s09 = TimeUtils.formatDateTime(datetime, MIN_FORMAT);
        Log.i("Tag", s09);

        String s090 = TimeUtils.formatDateTime(datetime, "HH:00");
        Log.i("Tag", s090);

        setGlide("http://pic.58pic.com/58pic/11/75/23/17n58PIC9um.jpg", image1);
        setGlide("http://file.neihan8.com/mm/2016-03-08/ffbcf468338ae8e5ebe94d93ad378fa9.jpg",
                 image2);
        setGlide("http://image.tianjimedia.com/uploadImages/2015/199/50/52VV98K5ENH3.jpg", image3);

        findViewById(R.id.btn_dialog).setOnClickListener(view -> {
            dialog = new AlertDialog(MainActivity.this).builder();
            dialog.setTitle("这里是Title", Gravity.CENTER)
                  .setCancel("", null)
                  .addSheetItem("item0", which -> showToast("item0"))
                  .addSheetItem("item1", which -> showToast("item1"))
                  .addSheetItem("item2", which -> showToast("item2"))
                  .addSheetItem("item3", which -> showToast("item3"))
                  .show();
        });

        findViewById(R.id.btn_dialog2).setOnClickListener(view -> {
            dialog = new AlertDialog(MainActivity.this).builder();
            dialog.setTitle("Title").setMsg("Message").setNegativeButton("", view14 -> {
            }).setPositiveButton("", null).show();
        });

        findViewById(R.id.btn_dialog3).setOnClickListener(view -> {
            dialog = new AlertDialog(MainActivity.this).builder();
            dialog.setTitle("Title")
                  .setSingleChoiceItems(items)
                  .setNegativeButton("", null)
                  .setPositiveButton("", v -> {
                      showToast(items[dialog.getSingleChoiceItems()]);
                  })
                  .show();
        });

        findViewById(R.id.btn_dialog4).setOnClickListener(view -> {
            View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout, null);
            final EditText username = v.findViewById(R.id.edittxt_username);
            final EditText phone    = v.findViewById(R.id.edittxt_phone);
            final EditText password = v.findViewById(R.id.edittxt_password);
            dialog = new AlertDialog(MainActivity.this).builder();
            dialog.setTitle("Title")
                  .setView(v)
                  .setSingleChoiceItems(items)
                  .setNegativeButton("", null)
                  .setPositiveButton("", view12 -> {
                      progress.show();
                      Log.i("tag", username.getText().toString().trim());
                      Log.i("tag", phone.getText().toString().trim());
                      Log.i("tag", password.getText().toString().trim());
                      showToast(items[dialog.getSingleChoiceItems()]);
                  })
                  .show();
        });

        findViewById(R.id.bottom_alertdialog).setOnClickListener(view -> {
            View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout, null);
            dialog = new AlertDialog(MainActivity.this).builder();
            dialog.setTitle("这里是Title", Gravity.CENTER, R.color.white)
                  .setBackgroundColor(R.color.google_orange)
                  .setColseImage(R.mipmap.ic_launcher)
                  .setView(v)
                  .setColse(view1 -> showToast("关闭"))
                  .show();
        });

        findViewById(R.id.bottom_alertdialog2).setOnClickListener(view -> {
            dialog = new AlertDialog(MainActivity.this).builder();
            dialog.setTitle("这里是Title", Gravity.CENTER)
                  .setBackgroundDrawable(R.mipmap.ic_launcher)
                  .setMsg("234444444444444444")
                  .setPositiveButton("", v -> openActivity(CompactActivity.class, false))
                  .show();
        });

        findViewById(R.id.bottom_alertdialog3).setOnClickListener(view -> {
            dialog = new AlertDialog(MainActivity.this).builder();
            dialog.setTitle("这里是Title", Gravity.CENTER)
                  .setBackgroundResource(R.mipmap.ic_launcher)
                  .setMsg("234444444444444444")
                  .setCancel("", v -> showToast("取消"))
                  .show();
        });

        findViewById(R.id.bottom_dialog).setOnClickListener(view -> {
            mBottomDialog = new BottomDialog(MainActivity.this).builder();
            mBottomDialog.setTitle("这里是Title", 0, R.color.black)
                         .setCancel("取消", null)
                         .addSheetItem("RadarView", R.color.google_red,
                                       which -> openActivity(RadarViewActivity.class, false))
                         .addSheetItem("开启服务", R.color.google_blue, which -> {
                             if (binder != null) {
                                 binder.setDate(updateText, (tv5, data) -> tv5.setText(data + ""));
                             } else {
                                 showToast("连接失败");
                             }
                         })
                         .addSheetItem("水波纹", R.color.google_green,
                                       which -> openActivity(WaveLoadView.class, false))
                         .addSheetItem("LikeBang", R.color.google_pink,
                                       which -> openActivity(LikeBangActivity.class, false))
                         .addSheetItem("ExpandTextView", R.color.google_purple,
                                       which -> openActivity(ExpandTextViewActivity.class, false))
                         .addSheetItem("嵌套RecyclerView", R.color.google_orange,
                                       which -> openActivity(RecyclerViewActivity.class, false))
                         .addSheetItem("刷新", R.color.google_yellow,
                                       which -> openActivity(RefreshActivity.class, false))
                         .addSheetItem("下载", R.color.google_cyan, which -> download())
                         .show();
        });

        findViewById(R.id.bottom_dialog2).setOnClickListener(view -> {
            View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout, null);
            final EditText username = v.findViewById(R.id.edittxt_username);
            final EditText phone    = v.findViewById(R.id.edittxt_phone);
            final EditText password = v.findViewById(R.id.edittxt_password);
            mBottomDialog = new BottomDialog(MainActivity.this).builder();
            mBottomDialog.setView(v).setPositiveButton("确定", view1 -> {
                Log.i("tag", username.getText().toString().trim());
                Log.i("tag", phone.getText().toString().trim());
                Log.i("tag", password.getText().toString().trim());
            }).setNegativeButton("取消", null).show();
        });

        findViewById(R.id.bottom_dialog3).setOnClickListener(view -> {
            mBottomDialog = new BottomDialog(MainActivity.this).builder();
            mBottomDialog.setTitle(
                    "这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题",
                    Gravity.LEFT, R.color.gray_6).setCancel("取消", null).show();
        });

        switchButton.setChecked(true);
        switchButton.isChecked();
        switchButton.toggle();     //switch state
        switchButton.toggle(false);//switch without animation
        switchButton.setShadowEffect(true);//disable shadow effect
        switchButton.setEnabled(false);//disable button
        switchButton.setEnableEffect(false);//disable the switch animation
        switchButton.setOnCheckedChangeListener((view, isChecked) -> {
            //TODO do your job
        });
    }

    private void setGlide(String url, ImageView view) {
        Glide.with(this)
             .load(url)
             .centerCrop()
             .crossFade()
             .error(R.drawable.wall04)
             .placeholder(R.drawable.wall04)
             .into(view);
    }

    private void initSeekBar() {
        seekbar1.setValue(10);
        seekbar2.setValue(-0.5f, 0.8f);

        seekbar1.setOnRangeChangedListener(
                (view, min, max, isFromUser) -> seekbar1.setProgressDescription((int) min + "%"));

        seekbar2.setOnRangeChangedListener((view, min, max, isFromUser) -> {
            if (isFromUser) {
                tv.setText(min + "-" + max);
                seekbar2.setLeftProgressDescription(df.format(min));
                seekbar2.setRightProgressDescription(df.format(max));
            }
        });
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        tvTitle.setText("首页");
        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String queryText) {
                Toast.makeText(MainActivity.this, "你搜索了" + queryText, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void conversion() {
        System.out.println("1的十六进制结果是：" + ConversionUtils.intToHex(1));

        System.out.println("125的十六进制结果是：" + ConversionUtils.intToHex(125));
        System.out.println("125的二进制结果是：" + ConversionUtils.intToBinary(125));
        System.out.println("125的八进制结果是：" + ConversionUtils.intToOctal(125));

        System.out.println("十进制字符串125转16进制为" + ConversionUtils.intStrToHex("125"));
        System.out.println("十进制字符串125转二进制为" + ConversionUtils.intStrToBinary("125"));
        System.out.println("十进制字符串125转八进制为" + ConversionUtils.intStrToOctal("125"));

        System.out.println("十六进制字符串01 87转为10进制后为:" + ConversionUtils.hexToIntStr("01 87"));

        System.out.println("二进制字符串0111101 1111101转为10进制后为:" + ConversionUtils.binaryToIntStr(
                "0111101 1111101"));

        System.out.println("八进制字符串175 245转为10进制后为:" + ConversionUtils.octalToIntStr("175 245"));

        System.out.println(
                "字符串175的bytes数组转为16进制后为:\n" + ConversionUtils.bytesToHex("175".getBytes()));

        System.out.println(
                "字符串175的bytes数组转为2进制后为:\n" + ConversionUtils.bytesToBinary("175".getBytes()));

        System.out.println(
                "字符串31 37 35的16进制转为bytes数组后为:\n" + ConversionUtils.hexToBytes("31 37 35"));
        System.out.println("字符串31 37 35的16进制转为2进制后为:\n" + ConversionUtils.bytesToBinary(
                ConversionUtils.hexToBytes("31 37 35")));
    }

    private void download() {
        hpd = new UpdateAppDialog(this);
        hpd.builder();
        hpd.setMsg("这里是更新的内容");
        hpd.setTitle("应用更新");
        hpd.setDownLoad(v -> {
            RequestParams requestParams = new RequestParams(
                    "http://101.200.174.126:9898/system/app_versions/appfiles/000/000/001/original"
                            + "/app-release.apk?1490756569");
            requestParams.setSaveFilePath(FileUtils.getSDPath() + "/a.apk");
            x.http().get(requestParams, new Callback.ProgressCallback<File>() {
                @Override
                public void onWaiting() {
                }

                @Override
                public void onStarted() {
                }

                @Override
                public void onLoading(long total, long current, boolean isDownloading) {
                    hpd.setUnreachedBarColor(Color.YELLOW);
                    hpd.setProgressTextColor(Color.BLUE);
                    hpd.setReachedBarColor(Color.RED);
                    hpd.setmMax((int) total / 1024);
                    hpd.setmProgress((int) current / 1024);
                }

                @Override
                public void onSuccess(File result) {
                    showToast("下载成功");
                    hpd.dismiss();
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    ex.printStackTrace();
                    showToast("下载失败，请检查网络和SD卡");
                    hpd.dismiss();
                }

                @Override
                public void onCancelled(CancelledException cex) {
                }

                @Override
                public void onFinished() {
                }
            });
        });
        hpd.setColse(null);
        hpd.show();
    }

    @Override
    public void onBackPressed() {
        // 这里控制如果SearchView打开了，按返回键先关掉SearchView
        if (searchView.isSearchOpen()) {
            searchView.hideSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);

        item.setOnMenuItemClickListener(item12 -> {
            showToast("搜索");
            searchView.showSearch();
            return false;
        });

        MenuItem item1 = menu.findItem(R.id.action_search1);

        item1.setOnMenuItemClickListener(item22 -> {
            showToast("=====");
            return false;
        });

        MenuItem item2 = menu.findItem(R.id.action_search2);
        item2.setVisible(false);

        item2.setOnMenuItemClickListener(item3 -> {
            showToast("=====");
            return false;
        });
        return true;
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }

    public void oncancel(View view) {
        Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_LONG).show();// toast有显示时间延迟
        mc.cancel();
    }

    public void restart(View view) {
        Toast.makeText(MainActivity.this, "重新开始", Toast.LENGTH_LONG).show();// toast有显示时间延迟
        mc.start();
    }

    /**
     * 继承 CountDownTimer 防范
     *
     * 重写 父类的方法 onTick() 、 onFinish()
     */
    class MyCountDownTimer extends CountDownTimer {
        /**
         * @param millisInFuture    表示以毫秒为单位 倒计时的总数
         *
         *                          例如 millisInFuture=1000 表示1秒
         * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick 方法
         *
         *                          例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            timer.setText("done");
            openActivity(GreenDaoActivity.class, false);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.i("MainActivity", showTimeCount(millisUntilFinished));
            timer.setText("倒计时(" + millisUntilFinished / 1000 + ")");
        }
    }

    @Event({R.id.fab, R.id.imgFrontCard, R.id.imgBackCard, R.id.cardview_front, R.id.cardview_back,
            R.id.tv_number})
    private void onEvent(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", view -> {
                            showToast("Action");
                        })
                        .show();
                break;

            case R.id.imgFrontCard:
                showToast("Back Card");
                easyFlipView.flipTheView();
                break;

            case R.id.imgBackCard:
                showToast("Back Card");
                easyFlipView.flipTheView();
                break;

            case R.id.cardview_front:
                showToast("Front Card");
                easyFlipView2.flipTheView();
                break;

            case R.id.cardview_back:
                showToast("Back Card");
                easyFlipView2.flipTheView();
                break;

            case R.id.tv_number:
                number.startNumber();
                break;
        }
    }
}
