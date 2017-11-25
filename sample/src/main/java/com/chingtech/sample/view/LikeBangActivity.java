package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.FontHelper;
import chingtech.library.utils.StatusBarHelper;
import chingtech.library.widget.ThreeStateSwitch;
import com.chingtech.sample.R;

import java.util.ArrayList;
import java.util.List;

import chingtech.library.interfaces.SmallBangListener;
import chingtech.library.widget.SmallBang;

/**
 * MyLibrary
 * Package com.chingtech.library.view
 * Description:
 * Created by 师春雷
 * Created at 2017/5/27 10:13
 */
public class LikeBangActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private SmallBang mSmallBang;
    @BindView(R.id.image)
    ImageView mImage;
    @BindView(R.id.text)
    TextView  mText;

    @BindView(R.id.threeState)
    ThreeStateSwitch threeState;
    @BindView(R.id.threeState1)
    ThreeStateSwitch threeState1;

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

        mText.setTypeface(FontHelper.get(this, "vazir.ttf"));

        threeState.setNormalTextTypeface(FontHelper.get(this, "vazir.ttf"));
        threeState.setSelectedTextTypeface(FontHelper.get(this, "vazir_b.ttf"));

        threeState1.setNormalTextTypeface(FontHelper.get(this, "vazir.ttf"));
        threeState1.setSelectedTextTypeface(FontHelper.get(this, "vazir_b.ttf"));

        threeState1.setOnChangeListener(currentState -> {
            showToast("状态：" + currentState);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_like_bang;
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

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.image, R.id.button, R.id.text})
    public void onEvent(View view) {
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
