package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.LogUtils;
import chingtech.library.utils.StatusBarHelper;
import com.chingtech.sample.http.AvatarHttpManager;
import com.chingtech.sample.R;
import com.chingtech.sample.adapter.ViewPagerAdapter;
import com.chingtech.sample.bean.AFanDaBaseBean;
import com.chingtech.sample.bean.JztkBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

import static com.chingtech.sample.http.ApiUtils.JZTK_KEY;

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
 * Created at 17/8/27 下午5:02
 */
public class QuestionActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private List<JztkBean> jztk_list = new ArrayList<>();

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_question;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("驾考题库");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return viewPager;
    }

    @Override
    protected void loadData() {
        mStateView.showLoading();
        AvatarHttpManager.getInstance()
                         .getApiService()
                         .getJztk(JZTK_KEY, "1", "c1", "rand")
                         .subscribeOn(Schedulers.io())
                         .observeOn(AndroidSchedulers.mainThread())
                         .subscribe(new Observer<AFanDaBaseBean<List<JztkBean>>>() {
                       @Override
                       public void onSubscribe(Disposable d) {
                       }

                       @Override
                       public void onNext(AFanDaBaseBean<List<JztkBean>> value) {
                           if (value.getError_code() == 0) {
                               if (jztk_list.size() > 0) {
                                   jztk_list.clear();
                               }
                               jztk_list.addAll(value.getResult());
                               LogUtils.i("TAG", jztk_list.toString());

                               ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(
                                       getSupportFragmentManager(), jztk_list);
                               viewPager.setAdapter(viewPagerAdapter);
                               viewPager.setCurrentItem(0);
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
                           mStateView.showContent();
                       }
                   });
    }
}
