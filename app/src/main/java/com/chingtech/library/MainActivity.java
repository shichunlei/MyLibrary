package com.chingtech.library;

import static chingtech.library.utils.TimeUtils.DATE_SHAORT_FORMAT;
import static chingtech.library.utils.TimeUtils.DATE_TIME_FORMAT;
import static chingtech.library.utils.TimeUtils.DATE_TIME_HOUR_MIN_FORMAT;
import static chingtech.library.utils.TimeUtils.D_FORMAT;
import static chingtech.library.utils.TimeUtils.H_12_FORMAT;
import static chingtech.library.utils.TimeUtils.H_24_FORMAT;
import static chingtech.library.utils.TimeUtils.MIN_FORMAT;
import static chingtech.library.utils.TimeUtils.M_FORMAT;
import static chingtech.library.utils.TimeUtils.TIME_FORMAT;
import static chingtech.library.utils.TimeUtils.TIME_MS_FORMAT;
import static chingtech.library.utils.TimeUtils.TIME_SHAORT_FORMAT;
import static chingtech.library.utils.TimeUtils.Y_FORMAT;
import static chingtech.library.utils.TimeUtils.Y_M_FORMAT;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

import chingtech.library.interfaces.OnSheetItemClickListener;
import chingtech.library.utils.AppUitls;
import chingtech.library.utils.FileUtils;
import chingtech.library.utils.TimeUtils;
import chingtech.library.utils.ViewUtils;
import chingtech.library.widget.AlertDialog;
import chingtech.library.widget.BottomDialog;
import chingtech.library.widget.FlipView;
import chingtech.library.widget.HorizontalProgressDialog;
import chingtech.library.widget.NumberAnimTextView;
import chingtech.library.widget.ProgressDialog;
import chingtech.library.widget.RoundImageView;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

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

    private HorizontalProgressDialog hpd;

    @ViewInject(R.id.layout1)
    private LinearLayout layout;

    @ViewInject(R.id.tv_1)
    private TextView tv1;

    @ViewInject(R.id.tv_2)
    private TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);

        ViewUtils.setViewWidth(this, tv2, 1 / 7f);
        ViewUtils.setViewHeight(this, layout, 1 / 2f);
        ViewUtils.setViewWidth(this, tv1, 1 / 2f);

        progress = new ProgressDialog(this);
        progress.setTitle("正在登录");

        number.startNumber();

        String appname = AppUitls.getAppName(MainActivity.this);
        Log.i("tag", "appname:" + appname);
        String pkname = AppUitls.getPkName(MainActivity.this);
        Log.i("tag", "pkname:" + pkname);
        String versionname = AppUitls.getVersionName(MainActivity.this);
        Log.i("tag", "versionname:" + versionname);
        int versioncode = AppUitls.getVersionCode(MainActivity.this);
        Log.i("tag", "versioncode:" + versioncode);

        String s = TimeUtils.formatDate(date, DATE_SHAORT_FORMAT);
        Log.i("tag", s);

        String s0 = TimeUtils.formatDate(date, Y_FORMAT);
        Log.i("tag", s0);

        String s01 = TimeUtils.formatDate(date, Y_M_FORMAT);
        Log.i("tag", s01);
        String s02 = TimeUtils.formatDate(date, D_FORMAT);
        Log.i("tag", s02);
        String s03 = TimeUtils.formatDate(date, M_FORMAT);
        Log.i("tag", s03);

        String s2 = TimeUtils.formatDateTime(datetime, TIME_FORMAT);
        Log.i("tag", s2);

        String s3 = TimeUtils.formatDateTime(datetime, TIME_SHAORT_FORMAT);
        Log.i("tag", s3);

        String s4 = TimeUtils.formatDateTime(datetime, DATE_TIME_HOUR_MIN_FORMAT);
        Log.i("tag", s4);

        String s5 = TimeUtils.formatDateTime(datetime, TIME_SHAORT_FORMAT);
        Log.i("tag", s5);

        String s6 = TimeUtils.formatDateTime(datetime, DATE_TIME_FORMAT);
        Log.i("tag", s6);

        String s7 = TimeUtils.formatDateTime(datetime, TIME_MS_FORMAT);
        Log.i("tag", s7);

        String s8 = TimeUtils.formatDateTime(datetime, H_24_FORMAT);
        Log.i("tag", s8);

        String s9 = TimeUtils.formatDateTime(datetime, H_12_FORMAT);
        Log.i("tag", s9);

        String s09 = TimeUtils.formatDateTime(datetime, MIN_FORMAT);
        Log.i("tag", s09);

        String s090 = TimeUtils.formatDateTime(datetime, "HH:00");
        Log.i("tag", s090);

        image1 = (RoundImageView)findViewById(R.id.image1);
        image2 = (RoundImageView)findViewById(R.id.image2);
        image3 = (RoundImageView)findViewById(R.id.image3);

        Glide.with(this).load("http://www.5857.com/uploadfile/2015/0321/20150321103512823.jpg").centerCrop().crossFade().error(R.drawable.wall04).placeholder(R.drawable.wall04).into(image1);
        Glide.with(this).load("http://file.neihan8.com/mm/2016-03-08/ffbcf468338ae8e5ebe94d93ad378fa9.jpg").centerCrop().crossFade().error(R.drawable.wall04).placeholder(R.drawable.wall04).into(image2);
        Glide.with(this).load("http://image.tianjimedia.com/uploadImages/2015/199/50/52VV98K5ENH3.jpg").centerCrop().crossFade().error(R.drawable.wall04).placeholder(R.drawable.wall04).into(image3);

        findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AlertDialog(MainActivity.this).builder();
                dialog
                        .setTitle("这里是Title", Gravity.CENTER)
                        .addSheetItem("item0", new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Toast.makeText(MainActivity.this, "item0", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addSheetItem("item1", new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Toast.makeText(MainActivity.this, "item1", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addSheetItem("item2", new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Toast.makeText(MainActivity.this, "item2", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addSheetItem("item3", new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Toast.makeText(MainActivity.this, "item3", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.btn_dialog2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AlertDialog(MainActivity.this).builder();
                dialog
                        .setTitle("Title")
                        .setMsg("Message")
                        .setEditText("用户名")
                        .setPassword("密码")
                        .setNegativeButton("", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setPositiveButton("", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.btn_dialog3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AlertDialog(MainActivity.this).builder();
                dialog
                        .setTitle("Title")
                        .setSingleChoiceItems(items)
                        .setNegativeButton("", null)
                        .setPositiveButton("", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, items[dialog.getSingleChoiceItems()], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.btn_dialog4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(MainActivity.this).inflate(
                        R.layout.layout, null);
                final EditText username = (EditText) v.findViewById(R.id.edittxt_username);
                final EditText phone = (EditText) v.findViewById(R.id.edittxt_phone);
                final EditText password = (EditText) v.findViewById(R.id.edittxt_password);
                dialog = new AlertDialog(MainActivity.this).builder();
                dialog
                        .setTitle("Title")
                        .setView(v)
                        .setSingleChoiceItems(items)
                        .setNegativeButton("", null)
                        .setPositiveButton("", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                progress.show();
                                Log.i("tag", username.getText().toString().trim());
                                Log.i("tag", phone.getText().toString().trim());
                                Log.i("tag", password.getText().toString().trim());
                                Toast.makeText(MainActivity.this, items[dialog.getSingleChoiceItems()], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.bottom_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomDialog = new BottomDialog(MainActivity.this).builder();
                mBottomDialog.setTitle("这里是Title", 0, R.color.black)
                        .setCancel("取消", null)
                        .addSheetItem("item0", R.color.google_red, new OnSheetItemClickListener(){

                            @Override
                            public void onClick(int which) {
                                Log.i("TAG", "item0");
                            }
                        })
                        .addSheetItem("item1", R.color.google_blue, new OnSheetItemClickListener(){

                            @Override
                            public void onClick(int which) {
                                Log.i("TAG", "item1");
                            }
                        })
                        .addSheetItem("item2", R.color.google_green, new OnSheetItemClickListener(){

                            @Override
                            public void onClick(int which) {
                                Log.i("TAG", "item2");
                            }
                        })
                        .addSheetItem("item3", R.color.google_pink, new OnSheetItemClickListener(){

                            @Override
                            public void onClick(int which) {
                                Log.i("TAG", "item3");
                            }
                        })
                        .addSheetItem("item4", R.color.google_purple, new OnSheetItemClickListener(){

                            @Override
                            public void onClick(int which) {
                                Log.i("TAG", "item4");
                            }
                        })
                        .addSheetItem("item5", R.color.google_orange, new OnSheetItemClickListener(){

                            @Override
                            public void onClick(int which) {
                                Log.i("TAG", "item5");
                            }
                        })
                        .addSheetItem("item6", R.color.google_yellow, new OnSheetItemClickListener(){

                            @Override
                            public void onClick(int which) {
                                Log.i("TAG", "item6");
                            }
                        })
                        .addSheetItem("item7", R.color.google_cyan, new OnSheetItemClickListener(){

                            @Override
                            public void onClick(int which) {
                                download();
                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.bottom_dialog2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(MainActivity.this).inflate(
                        R.layout.layout, null);
                final EditText username = (EditText) v.findViewById(R.id.edittxt_username);
                final EditText phone = (EditText) v.findViewById(R.id.edittxt_phone);
                final EditText password = (EditText) v.findViewById(R.id.edittxt_password);
                mBottomDialog = new BottomDialog(MainActivity.this).builder();
                mBottomDialog
                        .setView(v)
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.i("tag", username.getText().toString().trim());
                                Log.i("tag", phone.getText().toString().trim());
                                Log.i("tag", password.getText().toString().trim());
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });

        findViewById(R.id.bottom_dialog3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomDialog = new BottomDialog(MainActivity.this).builder();
                mBottomDialog
                        .setTitle("这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题", Gravity.LEFT, R.color.gray_6)
                        .setCancel("取消", null)
                        .show();
            }
        });

        easyFlipView.setFlipDuration(1000);

        findViewById(R.id.imgFrontCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Front Card", Toast.LENGTH_SHORT).show();
                easyFlipView.flipTheView();
            }
        });

        findViewById(R.id.imgBackCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Back Card", Toast.LENGTH_SHORT).show();
                easyFlipView.flipTheView();
            }
        });

        findViewById(R.id.cardview_front).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Front Card", Toast.LENGTH_SHORT).show();
                easyFlipView2.flipTheView();
            }
        });

        findViewById(R.id.cardview_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Back Card", Toast.LENGTH_SHORT).show();
                easyFlipView2.flipTheView();
            }
        });
    }

    private void download() {

        hpd = new HorizontalProgressDialog(this);
        hpd.builder();
        RequestParams requestParams = new RequestParams("http://101.200.174.126:9898/system/app_versions/appfiles/000/000/001/original/app-release.apk?1490756569");
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
                hpd.setMessage("应用更新");
                hpd.show();
                hpd.setMax((int) total);
                hpd.setProgress((int) current);
            }

            @Override
            public void onSuccess(File result) {
                Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                hpd.dismiss();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                Toast.makeText(MainActivity.this, "下载失败，请检查网络和SD卡", Toast.LENGTH_SHORT).show();
                hpd.dismiss();
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }
}
