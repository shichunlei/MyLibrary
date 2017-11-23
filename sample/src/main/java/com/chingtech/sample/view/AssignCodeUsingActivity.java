package com.chingtech.sample.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.base.adapter.recyclerview.BaseRecyclerHolder;
import chingtech.library.base.adapter.recyclerview.CommonRecyclerAdapter;
import chingtech.library.utils.LogUtils;
import chingtech.library.utils.StatusBarHelper;
import com.chingtech.sample.http.JiSuHttpManager;
import com.chingtech.sample.R;
import com.chingtech.sample.bean.JiSuBaseBean;
import com.chingtech.sample.bean.JieQiListBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

import static com.chingtech.sample.http.ApiUtils.JISU_KEY;

/**
 * 在Java代码中指定Header和Footer
 */
public class AssignCodeUsingActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.recView)
    RecyclerView recyclerview;

    private JieQiListBean jieQiListBean;

    JieqiAdapter adapter;

    List<JieQiListBean.ListBean> listBean = new ArrayList<>();

    @Override
    protected void init() {
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new GridLayoutManager(this, 3));

        adapter = new JieqiAdapter(this, listBean);
        recyclerview.setAdapter(adapter);
        recyclerview.setHasFixedSize(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_using_assign_xml;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("二十四节气");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }

    @Override
    protected void loadData() {
        mStateView.showLoading();
        JiSuHttpManager.getInstance()
                       .getApiService()
                       .getJieQiList(JISU_KEY, "2017")
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(new Observer<JiSuBaseBean<JieQiListBean>>() {
                           @Override
                           public void onSubscribe(Disposable d) {
                           }

                           @Override
                           public void onNext(JiSuBaseBean<JieQiListBean> value) {
                               if (value.getStatus().equals("0")) {
                                   mStateView.showContent();
                                   jieQiListBean = value.getResult();
                                   setAdapter(jieQiListBean);
                               } else {
                                   mStateView.showRetry();
                               }
                           }

                           @Override
                           public void onError(Throwable e) {
                               LogUtils.d("TAG", e.getMessage().toString());
                               mStateView.showRetry();
                           }

                           @Override
                           public void onComplete() {
                           }
                       });
    }

    private void setAdapter(JieQiListBean jieQiListBean) {
        listBean.clear();
        listBean.addAll(jieQiListBean.getList());
        LogUtils.i("TAG", "setAdapter: " + listBean.toString());
        adapter.notifyDataSetChanged();
    }

    class JieqiAdapter extends CommonRecyclerAdapter<JieQiListBean.ListBean> {

        Context context;

        public JieqiAdapter(Context context, List<JieQiListBean.ListBean> list, int... layoutIds) {
            super(context, list, R.layout.item_jieqi);
            this.context = context;
        }

        @Override
        protected void onBindData(BaseRecyclerHolder holder, JieQiListBean.ListBean item,
                int position) {
            LogUtils.i("TAG", "onBindData: " + item.toString());
            holder.setViewHeight(R.id.iv_logo, 1 / 3f);
            holder.setText(R.id.tv_name, item.getName());
            holder.setText(R.id.tv_date, item.getTime());
            holder.setImageUrl(R.id.iv_logo, item.getPic());
            holder.setOnClickListener(R.id.layout, view -> {
                openActivity(JieQiInfoActivity.class, "id", item.getJieqiid(), false);
            });
        }
    }
}
