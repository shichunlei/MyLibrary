package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.LogUtils;
import chingtech.library.utils.StatusBarHelper;
import com.bumptech.glide.Glide;
import com.chingtech.sample.R;
import com.chingtech.sample.bean.JiSuBaseBean;
import com.chingtech.sample.bean.JieQiBean;
import com.chingtech.sample.http.JiSuHttpManager;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.chingtech.sample.http.ApiUtils.JISU_KEY;

/**
 * <p>
 * *    ***********    ***********    **
 * *    ***********    ***********    **
 * *    **             **             **
 * *    **             **             **
 * *    **             **             **
 * *    ***********    **             **
 * *    ***********    **             **
 * *             **    **             **
 * *             **    **             **
 * *             **    **             **
 * *    ***********    ***********    ***********
 * *    ***********    ***********    ***********
 * </p>
 * MyLibrary
 * Package com.chingtech.sample.view
 * Description:
 * Created by 师春雷
 * Created at 17/9/17 下午8:32
 */
public class JieQiInfoActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_name)
    TextView  tvName;
    @BindView(R.id.tv_time)
    TextView  tvTime;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_jianjie)
    TextView  tvJianjie;
    @BindView(R.id.tv_yangsheng)
    TextView  tvYangsheng;
    @BindView(R.id.tv_xisu)
    TextView  tvXisu;
    @BindView(R.id.tv_qiyuan)
    TextView  tvYoulai;

    @BindView(R.id.layout)
    NestedScrollView layout;

    private String jieqiid;

    @Override
    protected void init() {
        jieqiid = getStringExtra("id");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jieqi_info;
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
        return layout;
    }

    @Override
    protected void loadData() {
        mStateView.showLoading();
        JiSuHttpManager.getInstance()
                       .getApiService()
                       .getJieQiInfo(JISU_KEY, "2017", jieqiid)
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(new Observer<JiSuBaseBean<JieQiBean>>() {
                           @Override
                           public void onSubscribe(Disposable d) {
                           }

                           @Override
                           public void onNext(JiSuBaseBean<JieQiBean> value) {
                               if (value.getStatus().equals("0")) {
                                   mStateView.showContent();
                                   tvName.setText(value.getResult().getName());
                                   tvTitle.setText(value.getResult().getName());

                                   tvTime.setText(value.getResult().getDate());
                                   tvYangsheng.setText(value.getResult().getYangsheng());
                                   tvXisu.setText(value.getResult().getXisu());
                                   tvJianjie.setText(value.getResult().getJianjie());
                                   tvYoulai.setText(value.getResult().getYoulai());

                                   Glide.with(context)
                                        .load(value.getResult().getPic())
                                        .centerCrop()
                                        .into(img);
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
