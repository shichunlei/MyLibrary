package com.chingtech.sample.view;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.decoration.NormalDecoration;
import chingtech.library.utils.FileUtils;
import chingtech.library.utils.JsonUtils;
import chingtech.library.utils.StatusBarHelper;
import chingtech.library.widget.IndexLayout;
import com.chingtech.sample.R;
import com.chingtech.sample.adapter.RecCarAdapter;
import com.chingtech.sample.bean.BaseBean;
import com.chingtech.sample.bean.CarBean;
import java.util.ArrayList;
import java.util.List;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 在XML中指定Header和Footer
 */
@ContentView(R.layout.activity_using_assign_xml)
public class AssignXmlUsingActivity extends BaseActivity {

    @ViewInject(R.id.toolbar)
    protected Toolbar  toolbar;
    @ViewInject(R.id.tv_title)
    private   TextView tvTitle;

    @ViewInject(R.id.recView)
    private RecyclerView mRecyclerView;

    //侧边导航栏
    @ViewInject(R.id.index_layout)
    private IndexLayout indexLayout;

    private BaseBean bean = new BaseBean();

    private List<CarBean> carList = new ArrayList<>();

    private RecCarAdapter adapter;

    private LinearLayoutManager manager;

    private NormalDecoration decoration;

    @Override
    protected void init() {
        mStateView.showEmpty();
        mStateView.showLoading();
        new Handler().postDelayed(() -> {
            mStateView.showContent();
        }, 3000);

        String json = FileUtils.readFromAssets(this, "cars.json");
        bean = (BaseBean) JsonUtils.fromJson(json, BaseBean.class);

        if (bean.getStatus() == 1) {
            carList.addAll(bean.getCars());

            adapter = new RecCarAdapter(this, carList, R.layout.item_car);
        }

        decoration = new NormalDecoration() {
            @Override
            public String getHeaderName(int pos) {
                return carList.get(pos).getInitial();
            }
        };

        decoration.setHeaderHeight(80);
        decoration.setOnHeaderClickListener(
                pos -> showToast("点击到头部" + carList.get(pos).getInitial()));

        manager = new LinearLayoutManager(this);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);

        List<String> heads = new ArrayList<>();
        for (int i = 0; i < carList.size(); i++) {
            if (!heads.contains(carList.get(i).getInitial())) {
                heads.add(carList.get(i).getInitial());
            }
        }

        indexLayout.setIndexBarHeightRatio(0.9f);
        indexLayout.getIndexBar().setIndexsList(heads);
        indexLayout.getIndexBar().setIndexChangeListener(indexName -> {
            for (int i = 0; i < carList.size(); i++) {
                if (indexName.equals(carList.get(i).getInitial())) {
                    manager.scrollToPositionWithOffset(i, 0);
                    return;
                }
            }
        });
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("在XML中指定Header和Footer");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }
}
