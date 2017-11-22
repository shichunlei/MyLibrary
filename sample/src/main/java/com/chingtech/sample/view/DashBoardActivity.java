package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindArray;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.StatusBarHelper;
import chingtech.library.widget.DashboardView;
import com.chingtech.sample.R;

public class DashBoardActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView     tvTitle;
    @BindView(R.id.toolbar)
    Toolbar      toolbar;
    @BindView(R.id.layout)
    LinearLayout layout;

    @BindView(R.id.temp)
    DashboardView tempDashView;
    @BindView(R.id.humi)
    DashboardView humDashView;

    @BindArray(R.array.mult_temp_dash)
    String[] str;
    @BindArray(R.array.mult_huim_dash)
    String[] str2;

    private final static int   invs[]    = {35, 10, 35};
    private final static int[] colorRes  = {R.color.arc1, R.color.arc2, R.color.arc3};
    private final static int   invs1[]   = {25, 50, 25};
    private final static int[] colorRes1 = {R.color.arc21, R.color.arc22, R.color.arc23};

    @Override
    protected void init() {
        tempDashView.initDash(-20, invs, str, "℃", colorRes);
        humDashView.initDash(0, invs1, str2, "%", colorRes1);
        tempDashView.setAngleWithAnim(30);
        humDashView.setAngleWithAnim(70);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dash_broad;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("DashBoard");

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
