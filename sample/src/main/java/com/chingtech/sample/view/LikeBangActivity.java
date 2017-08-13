package com.chingtech.sample.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chingtech.sample.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import chingtech.library.interfaces.SmallBangListener;
import chingtech.library.widget.RecyclerViewBanner;
import chingtech.library.widget.SmallBang;

/**
 * MyLibrary
 * Package com.chingtech.library.view
 * Description:
 * Created by 师春雷
 * Created at 2017/5/27 10:13
 */
@ContentView(R.layout.activity_like_bang)
public class LikeBangActivity extends AppCompatActivity {

    private SmallBang mSmallBang;
    @ViewInject(R.id.image)
    private ImageView mImage;
    @ViewInject(R.id.text)
    private TextView mText;

    @ViewInject(R.id.rv_banner_1)
    private RecyclerViewBanner recyclerViewBanner1;
    @ViewInject(R.id.rv_banner_2)
    private RecyclerViewBanner recyclerViewBanner2;
    @ViewInject(R.id.rv_banner_3)
    private RecyclerViewBanner recyclerViewBanner3;
    @ViewInject(R.id.rv_banner_4)
    private RecyclerViewBanner recyclerViewBanner4;
    @ViewInject(R.id.rv_banner_5)
    private RecyclerViewBanner recyclerViewBanner5;

    private boolean like = false;

    private List<String> banners = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);

        banners.add(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487221110004&di"
                        + "=d6043e4b0c90ddf3ea5096c3d8eb8f58&imgtype=0&src=http%3A%2F%2Fimage"
                        + ".tianjimedia.com%2FuploadImages%2F2014%2F067%2F5116EPAUD762_1000x500.jpg");
        banners.add(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487221129421&di=c085432cf7c15836f8a6479138740f39&imgtype=0&src=http%3A%2F%2Fimage85.360doc.com%2FDownloadImg%2F2015%2F05%2F0517%2F53199602_2.jpg");
        banners.add(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490438881557&di"
                        + "=e61065ccc8d7b44591e1c4ba8df672ee&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu"
                        + ".com%2Fzhidao%2Fpic%2Fitem%2F18d8bc3eb13533fa00428309a0d3fd1f41345b24.jpg");
        banners.add(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491018668060&di"
                        + "=b96bf4ac065ab547eddc5af24dfbedd5&imgtype=0&src=http%3A%2F%2Fi2"
                        + ".sanwen8.cn%2Fdoc%2F1610%2F704-161024211H3.jpg");
        banners.add(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490440556037&di"
                        + "=ade75ba29126922124b063a2a57873f7&imgtype=0&src=http%3A%2F%2Fi2.download.fd.pchome"
                        + ".net%2Ft_960x600%2Fg1%2FM00%2F0E%2F05%2FooYBAFTbGOmIDPSLAAXPs6l7AQMAACSVgDyBqkABc_L421.jpg");

        mSmallBang = SmallBang.attach2Window(this);

        if (like) {
            mImage.setImageResource(R.drawable.heart_red);
        } else {
            mImage.setImageResource(R.drawable.heart);
        }

        recyclerViewBanner1.setRvBannerData(banners);
        recyclerViewBanner1.setOnSwitchRvBannerListener(
                new RecyclerViewBanner.OnSwitchRvBannerListener() {
                    @Override
                    public void switchBanner(int position, AppCompatImageView bannerView) {
                        Glide.with(bannerView.getContext())
                                .load(banners.get(position))
                                .placeholder(R.drawable.wall04)
                                .into(bannerView);
                    }
                });
        recyclerViewBanner1.setOnRvBannerClickListener(
                new RecyclerViewBanner.OnRvBannerClickListener() {
                    @Override
                    public void onClick(int position) {
                        Toast.makeText(LikeBangActivity.this, "position: " + position,
                                Toast.LENGTH_SHORT).show();
                    }
                });

        recyclerViewBanner2.setRvBannerData(banners);
        recyclerViewBanner2.setOnSwitchRvBannerListener(
                new RecyclerViewBanner.OnSwitchRvBannerListener() {
                    @Override
                    public void switchBanner(int position, AppCompatImageView bannerView) {
                        Glide.with(bannerView.getContext())
                                .load(banners.get(position))
                                .placeholder(R.drawable.wall04)
                                .into(bannerView);
                    }
                });

        recyclerViewBanner3.setRvBannerData(banners);
        recyclerViewBanner3.setOnSwitchRvBannerListener(
                new RecyclerViewBanner.OnSwitchRvBannerListener() {
                    @Override
                    public void switchBanner(int position, AppCompatImageView bannerView) {
                        Glide.with(bannerView.getContext())
                                .load(banners.get(position))
                                .placeholder(R.drawable.wall04)
                                .into(bannerView);
                    }
                });

        recyclerViewBanner4.setIndicatorInterval(2000);
        recyclerViewBanner4.setRvBannerData(banners);
        recyclerViewBanner4.setOnSwitchRvBannerListener(
                new RecyclerViewBanner.OnSwitchRvBannerListener() {
                    @Override
                    public void switchBanner(int position, AppCompatImageView bannerView) {
                        Glide.with(bannerView.getContext())
                                .load(banners.get(position))
                                .placeholder(R.drawable.wall04)
                                .into(bannerView);
                    }
                });

        recyclerViewBanner5.setRvBannerData(banners);
        recyclerViewBanner5.setOnSwitchRvBannerListener(
                new RecyclerViewBanner.OnSwitchRvBannerListener() {
                    @Override
                    public void switchBanner(int position, AppCompatImageView bannerView) {
                        Glide.with(bannerView.getContext())
                                .load(banners.get(position))
                                .placeholder(R.drawable.wall04)
                                .into(bannerView);
                    }
                });
    }

    @Event({R.id.image, R.id.button, R.id.text})
    private void onEvent(View view) {
        switch (view.getId()) {
            case R.id.image:
                like(view);
                break;
            case R.id.button:
                addNumber(view);
                break;
            case R.id.text:
                redText(view);
                break;
        }
    }

    public void addNumber(View view) {
        mSmallBang.bang(view, new SmallBangListener() {
            @Override
            public void onAnimationStart() {
            }

            @Override
            public void onAnimationEnd() {
                toast("button +1");
            }
        });
    }

    public void redText(View view) {
        mText.setTextColor(0xFFCD8BF8);
        mSmallBang.bang(view, 50, new SmallBangListener() {
            @Override
            public void onAnimationStart() {
            }

            @Override
            public void onAnimationEnd() {
                toast("text+1");
            }
        });
    }

    public void like(View view) {
        if (like) {
            like = false;
            mImage.setImageResource(R.drawable.heart_red);
        } else {
            like = true;
            mImage.setImageResource(R.drawable.heart);
        }
        mSmallBang.bang(view);
        mSmallBang.setmListener(new SmallBangListener() {
            @Override
            public void onAnimationStart() {
            }

            @Override
            public void onAnimationEnd() {
                toast("heart+1");
            }
        });
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
