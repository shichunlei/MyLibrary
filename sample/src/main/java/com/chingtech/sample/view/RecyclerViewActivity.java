package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.FileUtils;
import chingtech.library.utils.JsonUtils;
import chingtech.library.utils.StatusBarHelper;
import com.chingtech.sample.R;
import com.chingtech.sample.adapter.HeaderAdapter;

import com.chingtech.sample.bean.GirlsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * MyLibrary
 * Package com.chingtech.library.view
 * Description: RecyclerView嵌套
 * Created by 师春雷
 * Created at 2017/4/28 16:56
 */
public class RecyclerViewActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private HeaderAdapter adapter;

    private List<GirlsBean> girlsList = new ArrayList<>();

    @Override
    protected void init() {
        String json = FileUtils.readFromAssets(this, "girls.json");
        girlsList = (List<GirlsBean>) JsonUtils.fromJson(json, GirlsBean.class);

        adapter = new HeaderAdapter(this, girlsList, R.layout.item_girl_header);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("RecyclerView嵌套");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }

    @Override
    protected void loadData() {

    }
}
