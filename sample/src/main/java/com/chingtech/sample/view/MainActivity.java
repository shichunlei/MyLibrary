package com.chingtech.sample.view;

import static chingtech.library.utils.DESHelper.*;
import static chingtech.library.utils.IdcardUtil.*;
import static chingtech.library.utils.StringUtils.*;
import static chingtech.library.utils.TimeUtils.*;

import android.content.*;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.*;

import butterknife.BindView;
import butterknife.OnClick;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.ConversionUtils;
import chingtech.library.widget.*;
import chingtech.library.widget.SearchView;
import cn.bingoogolapple.bgabanner.BGABanner;
import com.bumptech.glide.Glide;
import com.chingtech.sample.R;

import com.chingtech.sample.service.UpdateService;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import chingtech.library.utils.*;

public class MainActivity extends BaseActivity {

    private final static String TAG = "TAG";

    protected ActionBarDrawerToggle mDrawerToggle;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.main_search_view_rsv)
    SearchView searchView;
    @BindView(R.id.toolbar)
    Toolbar    toolbar;
    @BindView(R.id.tv_title)
    TextView   tvTitle;

    @BindView(R.id.bgabanner)
    BGABanner bgaBanner;

    private List<String> images = new ArrayList<>();

    @BindView(R.id.img_header)
    RoundImageView header;

    private AlertDialog dialog;

    private String[] items = {"图片选择Activity", "图片选择Fragment", "圆形菜单", "日历大全"};

    @BindView(R.id.image1)
    RoundImageView image1;
    @BindView(R.id.image2)
    RoundImageView image2;
    @BindView(R.id.image3)
    RoundImageView image3;

    @BindView(R.id.flipView)
    FlipView easyFlipView;
    @BindView(R.id.flipView2)
    FlipView easyFlipView2;

    @BindView(R.id.tv_number)
    NumberAnimTextView number;

    private BottomDialog mBottomDialog;

    @BindView(R.id.layout1)
    FrameLayout layout;

    @BindView(R.id.tv_1)
    TextView tv1;

    @BindView(R.id.tv_2)
    TextView tv2;

    @BindView(R.id.tv_3)
    TextView tv3;

    @BindView(R.id.tv_4)
    TextView tv4;

    @BindView(R.id.tv_update_info)
    TextView updateText;

    @BindView(R.id.money)
    EditText etMoney;

    @BindView(R.id.seekbar1)
    RangeSeekBar seekbar1;
    @BindView(R.id.seekbar2)
    RangeSeekBar seekbar2;
    @BindView(R.id.progress2_tv)
    TextView     tv;

    private DecimalFormat df = new DecimalFormat("0.00");

    @BindView(R.id.timer)
    TextView timer;

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

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        if (null != bgaBanner) {
            bgaBanner.startAutoPlay();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        if (null != bgaBanner) {
            bgaBanner.stopAutoPlay();
        }
    }

    @Override
    protected void init() {
        //设置抽屉DrawerLayout
        setupActionBarDrawerToogle();

        mc = new MyCountDownTimer(30000, 1000);
        mc.start();

        initSeekBar();

        Intent intent = new Intent(this, UpdateService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);

        initBanner();

        easyFlipView.setFlipDuration(1000);

        LogUtils.i(TAG, "23132422.63232保留2位小数:" + doubleToString(23132422.63232));
        LogUtils.i(TAG, "23132422.23282保留3位小数:" + round(23132422.23282, 3));
        LogUtils.i(TAG, "23132422.93232转整数:" + round(23132422.93232, 0));

        LogUtils.i(TAG, "金额格式化: " + roundMoney(new BigDecimal(23132422.23832)));
        LogUtils.i(TAG, "金额格式化: " + roundMoney(new BigDecimal(-23132422.23832)));

        LogUtils.i(TAG, "金额格式化: " + formatMoney(new BigDecimal(23132422.23832)));

        String idcard15 = "131128900923065";
        String idcard18 = "131128199009230658";
        LogUtils.i(TAG, "131128199009230658身份证号码校验：" + isIdcard(idcard18));
        LogUtils.i(TAG, "131128900923065身份证号码校验：" + isIdcard(idcard15));

        String str = "320125193206214815";
        try {
            System.out.println(encrypt(str, "@njytyl&"));
            System.out.println(
                    decrypt("68d151bd4ed0662abe2aaddd5d961ceb8e317efb78b9d420", "@njytyl&"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        conversion();

        setViewSize();

        ViewUtils.setPricePoint(etMoney);

        progress.setTitleText("正在登录");

        number.startNumber();
        ViewUtils.setUnderLine(number);

        LogUtils.i(TAG, "=======" + StringUtils.hangeToBig(3453450.23));

        String appname = AppUtils.getAppName(MainActivity.this);
        LogUtils.i(TAG, "appname:" + appname);
        String pkname = AppUtils.getPkName(MainActivity.this);
        LogUtils.i(TAG, "pkname:" + pkname);
        String versionname = AppUtils.getVersionName(MainActivity.this);
        LogUtils.i(TAG, "versionname:" + versionname);
        int versioncode = AppUtils.getVersionCode(MainActivity.this);
        LogUtils.i(TAG, "versioncode:" + versioncode);

        CharacterParser c = new CharacterParser();
        LogUtils.i(TAG, "我们拼音: " + c.getSelling("我们"));

        testTimeUtils();

        setGlide("http://img5.imgtn.bdimg.com/it/u=439122560,4153639957&fm=26&gp=0.jpg", header);

        setGlide("http://pic.58pic.com/58pic/11/75/23/17n58PIC9um.jpg", image1);
        setGlide("http://file.neihan8.com/mm/2016-03-08/ffbcf468338ae8e5ebe94d93ad378fa9.jpg",
                 image2);
        setGlide("http://image.tianjimedia.com/uploadImages/2015/199/50/52VV98K5ENH3.jpg", image3);

        findViewById(R.id.btn_dialog).setOnClickListener(view -> {
            dialog = new AlertDialog(MainActivity.this).builder();
            dialog.setTitle("这里是Title", Gravity.CENTER)
                  .setCancel("", null)
                  .addSheetItem("滚动显示TextView的数字",
                                which -> openActivity(AnimNumberTextViewActivity.class, false))
                  .addSheetItem("Ring", which -> openActivity(RingActivity.class, false))
                  .addSheetItem("DashBoard", which -> openActivity(DashBoardActivity.class, false))
                  .addSheetItem("跑马灯", which -> openActivity(MarqueeViewActivity.class, false))
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
                      if (dialog.getSingleChoiceItems() == 0) {
                          openActivity(PhotoActivity.class, false);
                      } else if (dialog.getSingleChoiceItems() == 1) {
                          openActivity(PhotoFragmentActivity.class, false);
                      } else if (dialog.getSingleChoiceItems() == 2) {
                          openActivity(SatelliteMenuActivity.class, false);
                      } else if (dialog.getSingleChoiceItems() == 3) {
                          openActivity(CalendarActivity.class, false);
                      }

                      showToast(items[dialog.getSingleChoiceItems()]);
                  })
                  .show();
        });

        findViewById(R.id.bottom_alertdialog).setOnClickListener(view -> {
            View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout, null);
            dialog = new AlertDialog(MainActivity.this).builder();
            dialog.setTitle("这里是Title", Gravity.CENTER, R.color.white)
                  .setBackgroundColor(R.color.google_orange)
                  .setCloseImage(R.drawable.icon_close)
                  .setView(v)
                  .setClose(view1 -> showToast("关闭"))
                  .show();
        });

        findViewById(R.id.bottom_alertdialog2).setOnClickListener(view -> {
            dialog = new AlertDialog(MainActivity.this).builder();
            dialog.setTitle("这里是Title", Gravity.CENTER)
                  .setBackgroundDrawable(R.drawable.wall04)
                  .setMsg("日历")
                  .setPositiveButton("", v -> openActivity(CompactActivity.class, false))
                  .show();
        });

        findViewById(R.id.bottom_alertdialog3).setOnClickListener(view -> {
            dialog = new AlertDialog(MainActivity.this).builder();
            dialog.setTitle("这里是Title", Gravity.CENTER)
                  .setBackgroundResource(R.drawable.recycler_bg)
                  .setMsg(R.string.test)
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
                         .addSheetItem("刻度尺", R.color.google_cyan, which -> {
                             openActivity(RulerActivity.class, false);
                         })
                         .show();
        });

        findViewById(R.id.bottom_dialog2).setOnClickListener(view -> {
            View           v        = LayoutInflater.from(MainActivity.this)
                                                    .inflate(R.layout.layout, null);
            final EditText username = v.findViewById(R.id.edittxt_username);
            final EditText phone    = v.findViewById(R.id.edittxt_phone);
            final EditText password = v.findViewById(R.id.edittxt_password);
            mBottomDialog = new BottomDialog(MainActivity.this).builder();
            mBottomDialog.setView(v).setPositiveButton("确定", view1 -> {
                LogUtils.i(TAG, username.getText().toString().trim());
                LogUtils.i(TAG, phone.getText().toString().trim());
                LogUtils.i(TAG, password.getText().toString().trim());
            }).setNegativeButton("取消", null).show();
        });

        findViewById(R.id.bottom_dialog3).setOnClickListener(view -> {
            mBottomDialog = new BottomDialog(MainActivity.this).builder();
            mBottomDialog.setTitle(getString(R.string.test), Gravity.LEFT, R.color.gray_6)
                         .setCancel("取消", null)
                         .show();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void testTimeUtils() {
        String today = TimeUtils.getNowDateTime(DATE_TIME_FORMAT);

        LogUtils.i(TAG, "此刻：" + TimeUtils.getNowDateTime(TZ_FORMAT));
        LogUtils.i(TAG, "today:" + today);
        LogUtils.i(TAG, "今年：" + TimeUtils.getNowDateTime(Y_FORMAT));
        LogUtils.i(TAG, "今年：" + TimeUtils.getNowDateTime(YY_FORMAT));
        LogUtils.i(TAG, "本月：" + TimeUtils.getNowDateTime(M_FORMAT));
        LogUtils.i(TAG, "今天日期：" + TimeUtils.getNowDateTime(D_FORMAT));
        LogUtils.i(TAG, "小时（12）：" + TimeUtils.getNowDateTime(H_12_FORMAT));
        LogUtils.i(TAG, "小时（24）:" + TimeUtils.getNowDateTime(H_24_FORMAT));
        LogUtils.i(TAG, "分钟:" + TimeUtils.getNowDateTime(MIN_FORMAT));
        LogUtils.i(TAG, "秒:" + TimeUtils.getNowDateTime(S_FORMAT));

        String date     = "2017-04-13";
        String datetime = "2017-04-13 17:32:21";

        LogUtils.i(TAG, "两天相差天数：" + TimeUtils.intervalDays(date, today));

        String s00 = TimeUtils.formatDate(date, DATE_SHAORT_FORMAT);
        LogUtils.i(TAG, s00);

        String s0 = TimeUtils.formatDate(date, Y_FORMAT);
        LogUtils.i(TAG, s0);

        String s01 = TimeUtils.formatDate(date, Y_M_FORMAT);
        LogUtils.i(TAG, s01);
        String s02 = TimeUtils.formatDate(date, D_FORMAT);
        LogUtils.i(TAG, s02);
        String s03 = TimeUtils.formatDate(date, M_FORMAT);
        LogUtils.i(TAG, s03);

        String s2 = TimeUtils.formatDateTime(datetime, TIME_FORMAT);
        LogUtils.i(TAG, s2);

        String s3 = TimeUtils.formatDateTime(datetime, TIME_SHAORT_FORMAT);
        LogUtils.i(TAG, s3);

        String s4 = TimeUtils.formatDateTime(datetime, DATE_TIME_HOUR_MIN_FORMAT);
        LogUtils.i(TAG, s4);

        String s5 = TimeUtils.formatDateTime(datetime, TIME_SHAORT_FORMAT);
        LogUtils.i(TAG, s5);

        String s6 = TimeUtils.formatDateTime(datetime, DATE_TIME_FORMAT);
        LogUtils.i(TAG, s6);

        String s7 = TimeUtils.formatDateTime(datetime, TIME_MS_FORMAT);
        LogUtils.i(TAG, s7);

        String s8 = TimeUtils.formatDateTime(datetime, H_24_FORMAT);
        LogUtils.i(TAG, s8);

        String s9 = TimeUtils.formatDateTime(datetime, H_12_FORMAT);
        LogUtils.i(TAG, s9);

        String s09 = TimeUtils.formatDateTime(datetime, MIN_FORMAT);
        LogUtils.i(TAG, s09);

        String s090 = TimeUtils.formatDateTime(datetime, "HH:00");
        LogUtils.i(TAG, s090);
    }

    /**
     * 动态设置控件的大小（长、宽）
     */
    private void setViewSize() {
        ViewUtils.setViewWidth(this, tv2, 1 / 7f);
        ViewUtils.setViewHeight(this, layout, 1 / 2f);
        ViewUtils.setViewWidth(this, tv1, 1 / 2f);

        ViewUtils.setViewSize(this, tv3, 100, 100);

        ViewUtils.setViewSize(this, tv4, 1 / 3f);
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

    /**
     * 初始化seekBar
     */
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
        toolbar.setNavigationIcon(R.mipmap.ic_menu);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        tvTitle.setText("首页");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> mDrawerLayout.openDrawer(GravityCompat.START));

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

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout_main);
    }

    @Override
    protected void loadData() {
    }

    /**
     * 进制转换
     */
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item0 = menu.findItem(R.id.action_search);

        item0.setOnMenuItemClickListener(item -> {
            showToast("搜索");
            searchView.showSearch();
            return false;
        });

        MenuItem item1 = menu.findItem(R.id.action_search1);

        item1.setOnMenuItemClickListener(item -> {

            return false;
        });

        MenuItem item2 = menu.findItem(R.id.action_search2);
        item2.setVisible(false);

        item2.setOnMenuItemClickListener(item -> {
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
            LogUtils.i("TAG", showTimeCount(millisUntilFinished));
            timer.setText("倒计时(" + millisUntilFinished / 1000 + ")");
        }
    }

    @OnClick({R.id.fab, R.id.imgFrontCard, R.id.imgBackCard, R.id.cardview_front,
            R.id.cardview_back, R.id.tv_number, R.id.btnCheckUpdate, R.id.btnLoadUpdateInfo,
            R.id.tv_setting, R.id.tv_info, R.id.tv_about, R.id.start, R.id.cancel})
    public void onEvent(View v) {
        switch (v.getId()) {
            case R.id.start:
                showToast("重新开始");
                mc.start();
                break;
            case R.id.cancel:
                showToast("取消");
                mc.cancel();
                break;
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

            case R.id.btnCheckUpdate:
                Beta.checkUpgrade();
                break;

            case R.id.btnLoadUpdateInfo:
                loadUpgradeInfo();
                break;
            case R.id.tv_setting:
                closeNavDrawer();
                openActivity(SettingActivity.class, false);
                break;
            case R.id.tv_info:
                closeNavDrawer();
                openActivity(QuestionActivity.class, false);
                break;
            case R.id.tv_about:
                closeNavDrawer();
                break;
        }
    }

    private void loadUpgradeInfo() {
        /***** 获取升级信息 *****/
        UpgradeInfo upgradeInfo = Beta.getUpgradeInfo();

        if (upgradeInfo == null) {
            showToast("无升级信息");
            return;
        }

        StringBuilder info = new StringBuilder();
        info.append("id: ").append(upgradeInfo.id).append("\n");
        info.append("标题: ").append(upgradeInfo.title).append("\n");
        info.append("升级说明: ").append(upgradeInfo.newFeature).append("\n");
        info.append("versionCode: ").append(upgradeInfo.versionCode).append("\n");
        info.append("versionName: ").append(upgradeInfo.versionName).append("\n");
        info.append("发布时间: ").append(upgradeInfo.publishTime).append("\n");
        info.append("安装包Md5: ").append(upgradeInfo.apkMd5).append("\n");
        info.append("安装包下载地址: ").append(upgradeInfo.apkUrl).append("\n");
        info.append("安装包大小: ").append(upgradeInfo.fileSize).append("\n");
        info.append("弹窗间隔（ms）: ").append(upgradeInfo.popInterval).append("\n");
        info.append("弹窗次数: ").append(upgradeInfo.popTimes).append("\n");
        info.append("发布类型（0:测试 1:正式）: ").append(upgradeInfo.publishType).append("\n");
        info.append("弹窗类型（1:建议 2:强制 3:手工）: ").append(upgradeInfo.upgradeType);

        LogUtils.i(TAG, "loadUpgradeInfo: " + info);

        showToast(info);
    }

    /**
     * 初始化banner
     */
    private void initBanner() {
        images.add(
                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2839432607,3967940098&fm=26&gp=0.jpg");
        images.add(
                "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=820805568,3482726218&fm=26&gp=0.jpg");
        images.add(
                "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1495649415,1273524504&fm=26&gp=0.jpg");
        images.add(
                "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=321741969,1210935552&fm=26&gp=0.jpg");
        images.add(
                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1971779904,950854969&fm=26&gp=0.jpg");

        List<String> titles = new ArrayList<>();
        titles.add("美女1");
        titles.add("美女2");
        titles.add("美女3");
        titles.add("美女4");
        titles.add("美女5");

        bgaBanner.setAdapter((banner, itemView, model, position) -> {
            Glide.with(MainActivity.this)
                 .load(model)
                 .placeholder(R.drawable.wall04)
                 .error(R.drawable.wall04)
                 .centerCrop()
                 .dontAnimate()
                 .into((ImageView) itemView);
        });

        bgaBanner.setData(images, titles);

        bgaBanner.setDelegate((banner, itemView, model, position) -> {
            // TODO
            showToast(titles.get(position));
        });
    }

    /**
     * In case if you require to handle drawer open and close states
     */
    @SuppressWarnings("deprecation")
    private void setupActionBarDrawerToogle() {
        mDrawerToggle = new ActionBarDrawerToggle(this,                  /* host Activity */
                                                  mDrawerLayout,         /* DrawerLayout object */
                                                  R.string.drawer_open,  /* "open drawer" description */
                                                  R.string.drawer_close  /* "close drawer" description */);

        mDrawerToggle.syncState();//初始化状态
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        StatusBarHelper.tintStatusBarForDrawer(this, mDrawerLayout,
                                               getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void onBackPressed() {
        // 这里控制如果SearchView打开了，按返回键先关掉SearchView
        if (searchView.isSearchOpen()) {
            searchView.hideSearch();
        } else if (isNavDrawerOpen()) {
            closeNavDrawer();
        } else {
            doExitApp();
        }
    }

    protected boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    protected void closeNavDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}
