package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.StatusBarHelper;
import chingtech.library.widget.ruler.DecimalRulerView;
import chingtech.library.widget.ruler.IntegerRulerView;
import com.chingtech.sample.R;

/**
 * <p>
 * *    ***********    ***********    **
 * *    ***********    ***********    **
 * *    **             **             **
 * *    **             **             **
 * *    **             **             **
 * *    ***********    **             **
 * *    ***********    **             **
 * *             **    **             **
 * *             **    **             **
 * *             **    **             **
 * *    ***********    ***********    ***********
 * *    ***********    ***********    ***********
 * </p>
 * MyLibrary
 * Package com.chingtech.sample.view
 * Description:
 * Created by 师春雷
 * Created at 17/10/28 上午8:29
 */
public class RulerActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_height)
    TextView tvHeight;

    @BindView(R.id.ruler_weight)
    DecimalRulerView mWeightRulerView;

    @BindView(R.id.ruler_height)
    IntegerRulerView mHeightRulerView;

    @BindView(R.id.layout)
    LinearLayout layout;

    private float mWeight = 20.0f;

    private float mHeight    = 170;
    private float mMaxHeight = 220;
    private float mMinHeight = 100;

    @Override
    protected void init() {
        mWeightRulerView.initViewParam(20, 0, 100f, 1f);
        mWeightRulerView.setChooseValueChangeListener(value -> {
            mWeight = value;
        });

        mHeightRulerView.initViewParam(mHeight, mMaxHeight, mMinHeight);
        mHeightRulerView.setValueChangeListener(value -> {
            tvHeight.setText((int) value + "cm");
            mHeight = value;
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ruler;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("刻度尺");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return layout;
    }

    @Override
    protected void loadData() {
    }
}
