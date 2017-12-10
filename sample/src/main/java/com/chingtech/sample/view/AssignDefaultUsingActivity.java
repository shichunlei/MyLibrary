package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.StatusBarHelper;
import chingtech.library.widget.SwitchIconView;
import com.chingtech.sample.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

/**
 * 全局指定默认的Header和Footer
 */
public class AssignDefaultUsingActivity extends BaseActivity {

    private static boolean isFirstEnter = true;

    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

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

    /**
     * 关键代码，需要在布局生成之前设置，建议代码放在 Application.onCreate 中
     */
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
            ClassicsHeader header = new ClassicsHeader(context).setSpinnerStyle(
                    SpinnerStyle.Scale);
            header.setPrimaryColors(ContextCompat.getColor(context, R.color.colorPrimary),
                                    ContextCompat.getColor(context, android.R.color.white));
            return header;//指定为经典Header，默认是 贝塞尔雷达Header
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> {
            ClassicsFooter footer = new ClassicsFooter(context);
            footer.setBackgroundResource(android.R.color.white);
            footer.setSpinnerStyle(SpinnerStyle.Scale);//设置为拉伸模式
            return footer;//指定为经典Footer，默认是 BallPulseFooter
        });
    }

    @Override
    protected void init() {
        /**
         * 以下代码仅仅为了演示效果而已，不是必须的
         * 关键代码在构造函数中
         */
        if (isFirstEnter) {
            isFirstEnter = false;
            //触发上啦加载
            refreshLayout.autoLoadmore();
            //通过多功能监听接口实现 在第一次加载完成之后 自动刷新
            refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
                @Override
                public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState,
                        RefreshState newState) {
                    if (oldState == RefreshState.LoadFinish && newState == RefreshState.None) {
                        refreshLayout.autoRefresh();
                        refreshLayout.setOnMultiPurposeListener(null);
                    }
                }
            });
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_using_assign_default;
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
        return (View) refreshLayout;
    }

    @Override
    protected void loadData() {
        button1.setOnClickListener(v -> switchIcon1.switchState());
        button2.setOnClickListener(v -> switchIcon2.switchState());
        button3.setOnClickListener(v -> switchIcon3.switchState());
    }
}
