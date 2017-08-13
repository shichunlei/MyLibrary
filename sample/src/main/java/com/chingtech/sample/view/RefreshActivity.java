package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.StatusBarHelper;
import com.chingtech.sample.R;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * MyLibrary
 * Package com.chingtech.library
 * Description:
 * Created by 师春雷
 * Created at 2017/4/28 11:43
 */
@ContentView(R.layout.activity_refresh)
public class RefreshActivity extends BaseActivity implements OnLoadmoreListener, OnRefreshListener {

    @ViewInject(R.id.refreshLayout)
    private RefreshLayout refreshLayout;

    @ViewInject(R.id.toolbar)
    protected Toolbar  toolbar;
    @ViewInject(R.id.tv_title)
    private   TextView tvTitle;

    @Override
    protected void init() {

        refreshLayout.setEnableAutoLoadmore(true);//开启自动加载功能（非必须）
        refreshLayout.setOnLoadmoreListener(this);
        refreshLayout.setOnRefreshListener(this);

        //触发自动刷新
        refreshLayout.autoRefresh();
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tvTitle.setText("刷新");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    public void onLoadmore(final RefreshLayout refreshlayout) {
        ((View) refreshlayout).postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshlayout.finishLoadmore();
                Toast.makeText(getApplication(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                refreshlayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
            }
        }, 2000);
    }

    @Override
    public void onRefresh(final RefreshLayout refreshlayout) {
        ((View) refreshlayout).postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshlayout.finishRefresh();
            }
        }, 2000);
    }

    @Event({R.id.btn_1, R.id.btn_2, R.id.btn_3})
    private void onEvent(View view) {
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
