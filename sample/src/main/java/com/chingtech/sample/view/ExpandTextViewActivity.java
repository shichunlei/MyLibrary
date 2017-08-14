package com.chingtech.sample.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import chingtech.library.base.activity.BaseActivity;
import com.chingtech.sample.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import chingtech.library.base.adapter.recyclerview.BaseRecyclerHolder;
import chingtech.library.base.adapter.recyclerview.CommonRecyclerAdapter;

/**
 * MyLibrary
 * Package com.chingtech.library.view
 * Description:
 * Created by 师春雷
 * Created at 2017/5/26 18:01
 */
@ContentView(R.layout.activity_recycler)
public class ExpandTextViewActivity extends BaseActivity {

    @ViewInject(R.id.recyclerview)
    private RecyclerView mRecyclerView;

    private List<String> data = new ArrayList<>();

    @Override
    protected void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(new RecyclerAdapter(this, setData(), R.layout.item_extext));
    }

    @Override
    protected void initToolBar() {

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

    class RecyclerAdapter extends CommonRecyclerAdapter<String> {

        public RecyclerAdapter(Context context, List<String> list, int... layoutIds) {
            super(context, list, layoutIds);
        }

        @Override
        protected void onBindData(BaseRecyclerHolder holder, String item, int position) {
            holder.setText(R.id.tv_expand, item);
        }
    }
}