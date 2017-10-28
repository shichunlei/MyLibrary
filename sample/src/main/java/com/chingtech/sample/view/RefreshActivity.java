package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.StatusBarHelper;
import com.chingtech.sample.R;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * MyLibrary
 * Package com.chingtech.library
 * Description:
 * Created by 师春雷
 * Created at 2017/4/28 11:43
 */
public class RefreshActivity extends BaseActivity implements OnLoadmoreListener, OnRefreshListener {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void init() {
        refreshLayout.setEnableAutoLoadmore(true);//开启自动加载功能（非必须）
        refreshLayout.setOnLoadmoreListener(this);
        refreshLayout.setOnRefreshListener(this);

        //触发自动刷新
        refreshLayout.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_refresh;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("刷新");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.refreshLayout);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onLoadmore(final RefreshLayout refreshlayout) {
        ((View) refreshlayout).postDelayed(() -> {
            refreshlayout.finishLoadmore();
            Toast.makeText(getApplication(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
            refreshlayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
        }, 2000);
    }

    @Override
    public void onRefresh(final RefreshLayout refreshlayout) {
        ((View) refreshlayout).postDelayed(() -> refreshlayout.finishRefresh(), 2000);
    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3})
    public void onEvent(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                openActivity(AssignDefaultUsingActivity.class, false);
                break;
            case R.id.btn_2:
                openActivity(AssignXmlUsingActivity.class, false);
                break;
            case R.id.btn_3:
                openActivity(AssignCodeUsingActivity.class, false);
                break;
        }
    }
}
