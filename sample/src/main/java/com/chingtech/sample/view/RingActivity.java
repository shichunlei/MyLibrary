package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.StatusBarHelper;
import chingtech.library.widget.RingView;
import com.chingtech.sample.R;

/**
 * Created by David on 2017/4/13.
 */

public class RingActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar  toolbar;

    @BindView(R.id.layout)
    LinearLayout layout;

    @BindView(R.id.humi)
    RingView tempDashView;
    @BindView(R.id.humi2)
    RingView tempDashView2;

    @Override
    protected void init() {
        String[] str    = {"差", "中", "好"};
        int[]    colors = {R.color.arc1, R.color.arc2, R.color.arc3};
        tempDashView.setTotalSection(3);
        tempDashView.setSelectPosition(1);
        tempDashView.initDash(str, colors);
        tempDashView.startAnim(800);
        tempDashView2.setTotalSection(5);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ring;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("Ring");

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
