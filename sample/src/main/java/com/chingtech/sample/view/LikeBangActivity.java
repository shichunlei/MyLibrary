package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.StatusBarHelper;
import com.bumptech.glide.Glide;
import com.chingtech.sample.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

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
public class LikeBangActivity extends BaseActivity {

    @ViewInject(R.id.toolbar)
    protected Toolbar  toolbar;
    @ViewInject(R.id.tv_title)
    private   TextView tvTitle;

    private SmallBang mSmallBang;
    @ViewInject(R.id.image)
    private ImageView mImage;
    @ViewInject(R.id.text)
    private TextView  mText;

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
    protected void init() {
        banners.add(
                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2839432607,3967940098&fm=26&gp=0.jpg");
        banners.add(
                "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=820805568,3482726218&fm=26&gp=0.jpg");
        banners.add(
                "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1495649415,1273524504&fm=26&gp=0.jpg");
        banners.add(
                "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=321741969,1210935552&fm=26&gp=0.jpg");
        banners.add(
                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1971779904,950854969&fm=26&gp=0.jpg");

        mSmallBang = SmallBang.attach2Window(this);

        if (like) {
            mImage.setImageResource(R.drawable.heart_red);
        } else {
            mImage.setImageResource(R.drawable.heart);
        }

        recyclerViewBanner1.setRvBannerData(banners);
        recyclerViewBanner1.setOnSwitchRvBannerListener(
                (position, bannerView) -> Glide.with(bannerView.getContext())
                                               .load(banners.get(position))
                                               .placeholder(R.drawable.wall04)
                                               .into(bannerView));
        recyclerViewBanner1.setOnRvBannerClickListener(
                position -> Toast.makeText(LikeBangActivity.this, "position: " + position,
                                           Toast.LENGTH_SHORT).show());

        recyclerViewBanner2.setRvBannerData(banners);
        recyclerViewBanner2.setOnSwitchRvBannerListener(
                (position, bannerView) -> Glide.with(bannerView.getContext())
                                               .load(banners.get(position))
                                               .placeholder(R.drawable.wall04)
                                               .into(bannerView));

        recyclerViewBanner3.setRvBannerData(banners);
        recyclerViewBanner3.setOnSwitchRvBannerListener(
                (position, bannerView) -> Glide.with(bannerView.getContext())
                                               .load(banners.get(position))
                                               .placeholder(R.drawable.wall04)
                                               .into(bannerView));

        recyclerViewBanner4.setIndicatorInterval(2000);
        recyclerViewBanner4.setRvBannerData(banners);
        recyclerViewBanner4.setOnSwitchRvBannerListener(
                (position, bannerView) -> Glide.with(bannerView.getContext())
                                               .load(banners.get(position))
                                               .placeholder(R.drawable.wall04)
                                               .into(bannerView));

        recyclerViewBanner5.setRvBannerData(banners);
        recyclerViewBanner5.setOnSwitchRvBannerListener(
                (position, bannerView) -> Glide.with(bannerView.getContext())
                                               .load(banners.get(position))
                                               .placeholder(R.drawable.wall04)
                                               .into(bannerView));
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("全局指定默认的Header和Footer");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.scrollView);
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
                showToast("button +1");
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
                showToast("text+1");
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
                showToast("heart+1");
            }
        });
    }
}
