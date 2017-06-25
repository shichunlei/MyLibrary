package com.chingtech.library.view;

import static chingtech.library.utils.TimeUtils.DATE_SHAORT_FORMAT;
import static chingtech.library.utils.TimeUtils.DATE_TIME_FORMAT;
import static chingtech.library.utils.TimeUtils.DATE_TIME_HOUR_MIN_FORMAT;
import static chingtech.library.utils.TimeUtils.D_FORMAT;
import static chingtech.library.utils.TimeUtils.H_12_FORMAT;
import static chingtech.library.utils.TimeUtils.H_24_FORMAT;
import static chingtech.library.utils.TimeUtils.MIN_FORMAT;
import static chingtech.library.utils.TimeUtils.M_FORMAT;
import static chingtech.library.utils.TimeUtils.S_FORMAT;
import static chingtech.library.utils.TimeUtils.TIME_FORMAT;
import static chingtech.library.utils.TimeUtils.TIME_MS_FORMAT;
import static chingtech.library.utils.TimeUtils.TIME_SHAORT_FORMAT;
import static chingtech.library.utils.TimeUtils.TZ_FORMAT;
import static chingtech.library.utils.TimeUtils.YY_FORMAT;
import static chingtech.library.utils.TimeUtils.Y_FORMAT;
import static chingtech.library.utils.TimeUtils.Y_M_FORMAT;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import chingtech.library.utils.ConversionUtils;
import chingtech.library.widget.SearchView;
import com.bumptech.glide.Glide;
import com.chingtech.library.R;

import java.util.ArrayList;
import java.util.List;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

import chingtech.library.interfaces.OnSheetItemClickListener;
import chingtech.library.utils.AppUitls;
import chingtech.library.utils.FileUtils;
import chingtech.library.utils.StringUtils;
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

    @ViewInject(R.id.main_search_view_rsv)
    private SearchView searchView;
    @ViewInject(R.id.main_toolbar_tb)
    private Toolbar    toolbar;

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
    private FrameLayout layout;

    @ViewInject(R.id.tv_1)
    private TextView tv1;

    @ViewInject(R.id.tv_2)
    private TextView tv2;

    @ViewInject(R.id.tv_3)
    private TextView tv3;

    @ViewInject(R.id.tv_4)
    private TextView tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);

        setSupportActionBar(toolbar);

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

        iiiiiii();

        ViewUtils.setViewWidth(this, tv2, 1 / 7f);
        ViewUtils.setViewHeight(this, layout, 1 / 2f);
        ViewUtils.setViewWidth(this, tv1, 1 / 2f);

        ViewUtils.setViewSize(this, tv3, 100, 100);

        ViewUtils.setViewSize(this, tv4, 1 / 3f);

        progress = new ProgressDialog(this);
        progress.setTitleText("正在登录");

        number.startNumber();

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Log.w("Tag", "=====onCreate: " + i);

            int k = 0;

            if (i < 3) {
                for (int j = 0; j < 3; j++) {
                    if (j == 2) {
                        k = j;
                    }
                }

                Log.w("Tag", i + "=====onCreate: " + k);
            } else {
                for (int j = 0; j < 3; j++) {
                    if (j == 3) {
                        k = j;
                    }
                }

                Log.w("Tag", i + "-----onCreate: " + k);
            }

            list.add(k);
        }

        Log.i("Tag", "list: " + list.toString());

        Log.i("tag", "----" + TimeUtils.getNowDateTime(TZ_FORMAT));
        Log.i("tag", "----" + TimeUtils.getNowDateTime(Y_FORMAT));
        Log.i("tag", "----" + TimeUtils.getNowDateTime(YY_FORMAT));
        Log.i("tag", "----" + TimeUtils.getNowDateTime(M_FORMAT));
        Log.i("tag", "----" + TimeUtils.getNowDateTime(D_FORMAT));
        Log.i("tag", "----" + TimeUtils.getNowDateTime(H_12_FORMAT));
        Log.i("tag", "----" + TimeUtils.getNowDateTime(H_24_FORMAT));
        Log.i("tag", "----" + TimeUtils.getNowDateTime(MIN_FORMAT));
        Log.i("tag", "----" + TimeUtils.getNowDateTime(S_FORMAT));

        Log.i("tag", "=---------------" + TimeUtils.intervalDays("2016-12-31 12:22:22",
                                                                 "2017-01-01 01:01:01"));

        Log.i("tag", "=======" + StringUtils.hangeToBig(3453450.23));

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

        Glide.with(this)
             .load("http://pic.58pic.com/58pic/11/75/23/17n58PIC9um.jpg")
             .centerCrop()
             .crossFade()
             .error(R.drawable.wall04)
             .placeholder(R.drawable.wall04)
             .into(image1);
        Glide.with(this)
             .load("http://file.neihan8.com/mm/2016-03-08/ffbcf468338ae8e5ebe94d93ad378fa9.jpg")
             .centerCrop()
             .crossFade()
             .error(R.drawable.wall04)
             .placeholder(R.drawable.wall04)
             .into(image2);
        Glide.with(this)
             .load("http://image.tianjimedia.com/uploadImages/2015/199/50/52VV98K5ENH3.jpg")
             .centerCrop()
             .crossFade()
             .error(R.drawable.wall04)
             .placeholder(R.drawable.wall04)
             .into(image3);

        findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AlertDialog(MainActivity.this).builder();
                dialog.setTitle("这里是Title", Gravity.CENTER)
                      .setCancel("", null)
                      .addSheetItem("item0", new OnSheetItemClickListener() {
                          @Override
                          public void onClick(int which) {
                              showToast("item0");
                          }
                      })
                      .addSheetItem("item1", new OnSheetItemClickListener() {
                          @Override
                          public void onClick(int which) {
                              showToast("item1");
                          }
                      })
                      .addSheetItem("item2", new OnSheetItemClickListener() {
                          @Override
                          public void onClick(int which) {
                              showToast("item2");
                          }
                      })
                      .addSheetItem("item3", new OnSheetItemClickListener() {
                          @Override
                          public void onClick(int which) {
                              showToast("item3");
                          }
                      })
                      .show();
            }
        });

        findViewById(R.id.btn_dialog2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AlertDialog(MainActivity.this).builder();
                dialog.setTitle("Title")
                      .setMsg("Message")
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
                dialog.setTitle("Title")
                      .setSingleChoiceItems(items)
                      .setNegativeButton("", null)
                      .setPositiveButton("", new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              Toast.makeText(MainActivity.this,
                                             items[dialog.getSingleChoiceItems()],
                                             Toast.LENGTH_SHORT).show();
                          }
                      })
                      .show();
            }
        });

        findViewById(R.id.btn_dialog4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout, null);
                final EditText username = (EditText) v.findViewById(R.id.edittxt_username);
                final EditText phone    = (EditText) v.findViewById(R.id.edittxt_phone);
                final EditText password = (EditText) v.findViewById(R.id.edittxt_password);
                dialog = new AlertDialog(MainActivity.this).builder();
                dialog.setTitle("Title")
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
                              Toast.makeText(MainActivity.this,
                                             items[dialog.getSingleChoiceItems()],
                                             Toast.LENGTH_SHORT).show();
                          }
                      })
                      .show();
            }
        });

        findViewById(R.id.bottom_alertdialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout, null);
                dialog = new AlertDialog(MainActivity.this).builder();
                dialog.setTitle("这里是Title", Gravity.CENTER, R.color.white)
                      .setBackgroundColor(R.color.google_orange)
                      .setColseImage(R.mipmap.ic_launcher)
                      .setView(v)
                      .setColse(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              showToast("关闭");
                          }
                      })
                      .show();
            }
        });

        findViewById(R.id.bottom_alertdialog2).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                dialog = new AlertDialog(MainActivity.this).builder();
                dialog.setTitle("这里是Title", Gravity.CENTER)
                      .setBackgroundDrawable(R.mipmap.ic_launcher)
                      .setMsg("234444444444444444")
                      .setPositiveButton("", new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {

                          }
                      })
                      .show();
            }
        });

        findViewById(R.id.bottom_alertdialog3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AlertDialog(MainActivity.this).builder();
                dialog.setTitle("这里是Title", Gravity.CENTER)
                      .setBackgroundResource(R.mipmap.ic_launcher)
                      .setMsg("234444444444444444")
                      .setCancel("", new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              showToast("取消");
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
                             .addSheetItem("item0", R.color.google_red,
                                           new OnSheetItemClickListener() {

                                               @Override
                                               public void onClick(int which) {
                                                   Log.i("TAG", "item0");
                                               }
                                           })
                             .addSheetItem("item1", R.color.google_blue,
                                           new OnSheetItemClickListener() {

                                               @Override
                                               public void onClick(int which) {
                                                   Log.i("TAG", "item1");
                                               }
                                           })
                             .addSheetItem("item2", R.color.google_green,
                                           new OnSheetItemClickListener() {

                                               @Override
                                               public void onClick(int which) {
                                                   goNextClass(WaveLoadView.class);
                                               }
                                           })
                             .addSheetItem("item3", R.color.google_pink,
                                           new OnSheetItemClickListener() {

                                               @Override
                                               public void onClick(int which) {
                                                   goNextClass(LikeBangActivity.class);
                                               }
                                           })
                             .addSheetItem("item4", R.color.google_purple,
                                           new OnSheetItemClickListener() {

                                               @Override
                                               public void onClick(int which) {
                                                   goNextClass(ExpandTextViewActivity.class);
                                               }
                                           })
                             .addSheetItem("item5", R.color.google_orange,
                                           new OnSheetItemClickListener() {

                                               @Override
                                               public void onClick(int which) {
                                                   goNextClass(RecyclerViewActivity.class);
                                               }
                                           })
                             .addSheetItem("item6", R.color.google_yellow,
                                           new OnSheetItemClickListener() {

                                               @Override
                                               public void onClick(int which) {
                                                   goNextClass(SampleActivity.class);
                                               }
                                           })
                             .addSheetItem("item7", R.color.google_cyan,
                                           new OnSheetItemClickListener() {

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
                View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout, null);
                final EditText username = (EditText) v.findViewById(R.id.edittxt_username);
                final EditText phone    = (EditText) v.findViewById(R.id.edittxt_phone);
                final EditText password = (EditText) v.findViewById(R.id.edittxt_password);
                mBottomDialog = new BottomDialog(MainActivity.this).builder();
                mBottomDialog.setView(v).setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("tag", username.getText().toString().trim());
                        Log.i("tag", phone.getText().toString().trim());
                        Log.i("tag", password.getText().toString().trim());
                    }
                }).setNegativeButton("取消", null).show();
            }
        });

        findViewById(R.id.bottom_dialog3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomDialog = new BottomDialog(MainActivity.this).builder();
                mBottomDialog.setTitle(
                        "这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题，这里是标题",
                        Gravity.LEFT, R.color.gray_6).setCancel("取消", null).show();
            }
        });

        easyFlipView.setFlipDuration(1000);

        findViewById(R.id.imgFrontCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Front Card");
                easyFlipView.flipTheView();
            }
        });

        findViewById(R.id.imgBackCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Back Card");
                easyFlipView.flipTheView();
            }
        });

        findViewById(R.id.cardview_front).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Front Card");
                easyFlipView2.flipTheView();
            }
        });

        findViewById(R.id.cardview_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Back Card");
                easyFlipView2.flipTheView();
            }
        });
    }

    private void iiiiiii() {
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

        hpd = new HorizontalProgressDialog(this);
        hpd.builder();
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
                hpd.setMessage("应用更新");
                hpd.show();
                hpd.setMax((int) total);
                hpd.setProgress((int) current);
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
    }

    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    private void goNextClass(Class<?> claxx) {
        Intent intent = new Intent();
        intent.setClass(this, claxx);
        startActivity(intent);
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

        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                searchView.showSearch();
                return false;
            }
        });

        MenuItem item1 = menu.findItem(R.id.action_search1);

        item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                showToast("=====");
                return false;
            }
        });

        MenuItem item2 = menu.findItem(R.id.action_search2);
        item2.setVisible(false);

        item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                showToast("=====");
                return false;
            }
        });
        return true;
    }
}
