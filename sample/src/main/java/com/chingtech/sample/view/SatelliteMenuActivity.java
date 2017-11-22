package com.chingtech.sample.view;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.LogUtils;
import chingtech.library.utils.StatusBarHelper;
import chingtech.library.widget.circlemenu.CircleMenuView;
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
 * Created at 17/10/28 上午10:50
 */
public class SatelliteMenuActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.layout)
    LinearLayout layout;

    @BindView(R.id.circle_menu)
    CircleMenuView menu;

    @Override
    protected void init() {
        menu.setEventListener(new CircleMenuView.EventListener() {
            @Override
            public void onMenuOpenAnimationStart(@NonNull CircleMenuView view) {
                LogUtils.d("D", "onMenuOpenAnimationStart");
            }

            @Override
            public void onMenuOpenAnimationEnd(@NonNull CircleMenuView view) {
                LogUtils.d("D", "onMenuOpenAnimationEnd");
            }

            @Override
            public void onMenuCloseAnimationStart(@NonNull CircleMenuView view) {
                LogUtils.d("D", "onMenuCloseAnimationStart");
            }

            @Override
            public void onMenuCloseAnimationEnd(@NonNull CircleMenuView view) {
                LogUtils.d("D", "onMenuCloseAnimationEnd");
            }

            @Override
            public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int index) {
                LogUtils.d("D", "onButtonClickAnimationStart| index: " + index);
            }

            @Override
            public void onButtonClickAnimationEnd(@NonNull CircleMenuView view, int index) {
                LogUtils.d("D", "onButtonClickAnimationEnd| index: " + index);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_satellite_menu;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("SatelliteMenu");

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
