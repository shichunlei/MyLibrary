package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.SPUtils;
import chingtech.library.utils.StatusBarHelper;
import chingtech.library.widget.SwitchButton;
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
 * Created at 17/8/27 下午1:17
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.switch_pattern_lock)
    SwitchButton switchPatternLock;
    @BindView(R.id.tv_pattern_lock)
    TextView     tvPatternLock;

    @BindView(R.id.switch_pin_lock)
    SwitchButton switchPinLock;
    @BindView(R.id.tv_pin_lock)
    TextView     tvPinLock;

    private boolean isPatternLock;
    private boolean isPinLock;

    @Override
    protected void init() {
        isPatternLock = (Boolean) SPUtils.get(this, "PatternLock", false, "DEMO");
        isPinLock = (Boolean) SPUtils.get(this, "PinLock", false, "DEMO");

        switchPinLock.setChecked(isPinLock);
        switchPatternLock.setChecked(isPatternLock);

        if (isPatternLock) {
            tvPatternLock.setVisibility(View.VISIBLE);
        }

        if (isPinLock) {
            tvPinLock.setVisibility(View.VISIBLE);
        }

        switchPatternLock.setOnCheckedChangeListener((view, isChecked) -> {
            if (isChecked) {
                switchPinLock.setChecked(false);
                tvPatternLock.setVisibility(View.VISIBLE);
                tvPinLock.setVisibility(View.GONE);
            } else {
                SPUtils.put(context, "PatternLock", false, "DEMO");
                tvPatternLock.setVisibility(View.GONE);
            }
        });

        switchPinLock.setOnCheckedChangeListener((view, isChecked) -> {
            if (isChecked) {
                switchPatternLock.setChecked(false);
                tvPinLock.setVisibility(View.VISIBLE);
                tvPatternLock.setVisibility(View.GONE);
            } else {
                SPUtils.put(context, "PinLock", false, "DEMO");
                tvPinLock.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("设置");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.tv_pattern_lock, R.id.tv_pin_lock, R.id.btn_switch})
    public void onEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_pattern_lock:
                openActivity(PatternLockActivity.class, "flag", "setting", false);
                break;
            case R.id.tv_pin_lock:
                openActivity(PinLockActivity.class, "flag", "setting", false);
                break;
            case R.id.btn_switch:
                openActivity(SwitchSampleActivity.class, false);
                break;
        }

    }
}
