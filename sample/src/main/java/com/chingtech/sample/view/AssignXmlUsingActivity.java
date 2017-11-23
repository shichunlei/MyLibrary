package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.LogUtils;
import chingtech.library.utils.StatusBarHelper;
import com.chingtech.sample.http.JiSuHttpManager;
import com.chingtech.greendao.gen.CarBeanDao;
import com.chingtech.sample.App;
import com.chingtech.sample.R;
import com.chingtech.sample.adapter.RecCarAdapter;
import com.chingtech.sample.bean.CarBean;
import com.chingtech.sample.bean.JiSuBaseBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

import static com.chingtech.sample.http.ApiUtils.JISU_KEY;

/**
 * 在XML中指定Header和Footer
 */
public class AssignXmlUsingActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.recView)
    RecyclerView mRecyclerView;

    private List<CarBean> carList = new ArrayList<>();

    private RecCarAdapter adapter;

    private CarBeanDao dao;

    @Override
    protected void init() {
        dao = App.getInstances().getDaoSession().getCarBeanDao();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mStateView.setOnRetryClickListener(() -> {
            loadData();
        });
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
        tvTitle.setText("车型大全");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }

    @Override
    protected void loadData() {
        mStateView.showLoading();
        carList.addAll(dao.loadAll());
        if (carList.size() > 0) {
            setAdapter(carList);
            mStateView.hidenLoading();
        } else {
            JiSuHttpManager.getInstance()
                           .getApiService()
                           .getCarList(JISU_KEY)
                           .subscribeOn(Schedulers.io())
                           .observeOn(AndroidSchedulers.mainThread())
                           .subscribe(new Observer<JiSuBaseBean<List<CarBean>>>() {
                               @Override
                               public void onSubscribe(Disposable d) {
                               }

                               @Override
                               public void onNext(JiSuBaseBean<List<CarBean>> value) {
                                   if (value.getStatus().equals("0")) {
                                       mStateView.showContent();
                                       carList.addAll(value.getResult());
                                       LogUtils.i("TAG", carList.toString());
                                       // 保存到本地数据库中
                                       dao.insertInTx(carList);
                                       setAdapter(carList);
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
    }

    public void setAdapter(List<CarBean> carList) {
        adapter = new RecCarAdapter(context, carList, R.layout.item_car);
        mRecyclerView.setAdapter(adapter);

        List<String> heads = new ArrayList<>();
        for (int i = 0; i < carList.size(); i++) {
            if (!heads.contains(carList.get(i).getInitial())) {
                heads.add(carList.get(i).getInitial());
            }
        }

        LogUtils.i("TAG", heads.toString());
    }
}
