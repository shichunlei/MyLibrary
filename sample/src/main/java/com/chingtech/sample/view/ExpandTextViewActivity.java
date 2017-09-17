package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.StatusBarHelper;
import com.chingtech.sample.R;

import com.chingtech.sample.adapter.RecyclerAdapter;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * MyLibrary
 * Package com.chingtech.library.view
 * Description:
 * Created by 师春雷
 * Created at 2017/5/26 18:01
 */
@ContentView(R.layout.activity_recycler)
public class ExpandTextViewActivity extends BaseActivity {

    @ViewInject(R.id.toolbar)
    protected Toolbar  toolbar;
    @ViewInject(R.id.tv_title)
    private   TextView tvTitle;

    @ViewInject(R.id.recyclerview)
    private RecyclerView mRecyclerView;

    private List<String> data = new ArrayList<>();

    @Override
    protected void init() {
        mRecyclerView.setAdapter(new RecyclerAdapter(this, setData(), R.layout.item_extext));
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("ExpandTextView");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    private List<String> setData() {
        String test = getString(R.string.test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);

        return data;
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }
}
