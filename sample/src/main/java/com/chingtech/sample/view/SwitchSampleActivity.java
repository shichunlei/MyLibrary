package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.FontHelper;
import chingtech.library.utils.StatusBarHelper;
import chingtech.library.widget.SwitchIconView;
import chingtech.library.widget.SwitchView;
import chingtech.library.widget.ThreeStateSwitch;
import com.chingtech.sample.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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
 * Created at 18/2/6 上午11:13
 */
public class SwitchSampleActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar  toolbar;

    @BindView(R.id.smartrefresh)
    SmartRefreshLayout layout;

    @BindView(R.id.xswitch)
    SwitchView switchView;

    @BindView(R.id.threeState)
    ThreeStateSwitch threeState;
    @BindView(R.id.threeState1)
    ThreeStateSwitch threeState1;

    @BindView(R.id.switchIconView1)
    SwitchIconView switchIcon1;
    @BindView(R.id.switchIconView2)
    SwitchIconView switchIcon2;
    @BindView(R.id.switchIconView3)
    SwitchIconView switchIcon3;

    @BindView(R.id.button1)
    View button1;
    @BindView(R.id.button2)
    View button2;
    @BindView(R.id.button3)
    View button3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_switch;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("SWITCHVIEW");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected void init() {
        switchView.setOnSwitchViewChangeListener(isRight -> showToast(isRight ? "男" : "女"));

        ///////////////////////////////////////////
        threeState.setNormalTextTypeface(FontHelper.get(this, "vazir.ttf"));
        threeState.setSelectedTextTypeface(FontHelper.get(this, "vazir_b.ttf"));

        threeState1.setNormalTextTypeface(FontHelper.get(this, "vazir.ttf"));
        threeState1.setSelectedTextTypeface(FontHelper.get(this, "vazir_b.ttf"));

        threeState1.setOnChangeListener(currentState -> {
            showToast("状态：" + currentState);
        });

        button1.setOnClickListener(v -> switchIcon1.switchState());
        button2.setOnClickListener(v -> switchIcon2.switchState());
        button3.setOnClickListener(v -> switchIcon3.switchState());
    }

    @Override
    protected View injectTarget() {
        return layout;
    }

    @Override
    protected void loadData() {

    }
}
