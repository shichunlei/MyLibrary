package com.chingtech.library.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;

import com.chingtech.library.R;
import com.chingtech.library.bean.Child;
import com.chingtech.library.bean.Parent;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import chingtech.library.adapter.base.recyclerview.BaseRecyclerHolder;
import chingtech.library.adapter.base.recyclerview.CommonRecyclerAdapter;

/**
 * MyLibrary
 * Package com.chingtech.library.view
 * Description: RecyclerView嵌套
 * Created by 师春雷
 * Created at 2017/4/28 16:56
 */
@ContentView(R.layout.activity_recycler)
public class RecyclerViewActivity extends AppCompatActivity {

    @ViewInject(R.id.recyclerview)
    private RecyclerView mRecyclerView;

    private ParentAdapter adapter;
    private ChildAdapter c_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);

        setData();
    }

    private void setData() {
        List<Child> children = new ArrayList<>();
        children.add(new Child("名字1", "http://imgtu.5011.net/uploads/content/20170406/4698681491456593.jpg"));
        children.add(new Child("名字2", "http://www.qqjia.com/z/01/tu3957_7.jpg"));
        children.add(new Child("名字3", "http://tx.haiqq.com/uploads/allimg/150325/12225632C-7.jpg"));

        Log.i("tag", children.toString());

        List<Parent> parent = new ArrayList<>();
        parent.add(new Parent("Title1", children));
        parent.add(new Parent("Title2", children));
        parent.add(new Parent("Title3", children));
        parent.add(new Parent("Title4", children));
        parent.add(new Parent("Title5", children));

        Log.i("tag", parent.toString());

        adapter = new ParentAdapter(this, parent, R.layout.item_parent);
        mRecyclerView.setAdapter(adapter);
    }

    class ParentAdapter extends CommonRecyclerAdapter<Parent> {

        public ParentAdapter(Context context, List<Parent> list, int... layoutIds) {
            super(context, list, layoutIds);
        }

        @Override
        protected void onBindData(BaseRecyclerHolder holder, Parent item, int position) {
            holder.setText(R.id.tv_name, item.getName());
            c_adapter = new ChildAdapter(RecyclerViewActivity.this, item.getChildren(), R.layout.item_child);
            holder.setLayoutManager(R.id.recyclerview_child, new LinearLayoutManager(RecyclerViewActivity.this));
            holder.setAdapter(R.id.recyclerview_child, c_adapter);
        }
    }

    class ChildAdapter extends CommonRecyclerAdapter<Child> {

        public ChildAdapter(Context context, List<Child> list, int... layoutIds) {
            super(context, list, layoutIds);
        }

        @Override
        protected void onBindData(BaseRecyclerHolder holder, Child item, int position) {
            holder.setLabelText(R.id.labelview, "A");
            holder.setGravity(R.id.labelview, Gravity.TOP | Gravity.LEFT);
            holder.setLabelBgColor(R.id.labelview, R.color.google_orange);
            holder.setText(R.id.tv_name, item.getName());
            holder.setImageUrl(R.id.image, item.getUrl(), R.mipmap.ic_launcher);
        }
    }
}
