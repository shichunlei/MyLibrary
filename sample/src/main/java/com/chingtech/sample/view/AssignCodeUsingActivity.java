package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.StatusBarHelper;
import com.chingtech.sample.R;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;

/**
 * 在Java代码中指定Header和Footer
 */
public class AssignCodeUsingActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected void init() {
        //设置 Header 为 Material风格
        refreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        //设置 Footer 为 球脉冲
        refreshLayout.setRefreshFooter(
                new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));

        //设置主题颜色
        refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);

        recyclerview.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_using_assign_code;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("在Java代码中指定Header和Footer");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.refreshLayout);
    }

    @Override
    protected void loadData() {

    }
}
