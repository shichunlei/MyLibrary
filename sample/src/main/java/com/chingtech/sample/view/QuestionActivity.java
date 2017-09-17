package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.FileUtils;
import chingtech.library.utils.JsonUtils;
import chingtech.library.utils.StatusBarHelper;
import com.chingtech.sample.R;
import com.chingtech.sample.adapter.ViewPagerAdapter;
import com.chingtech.sample.bean.BaseBean;
import com.chingtech.sample.bean.QuestionBean;
import java.util.ArrayList;
import java.util.List;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

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
@ContentView(R.layout.activity_question)
public class QuestionActivity extends BaseActivity {

    @ViewInject(R.id.toolbar)
    protected Toolbar  toolbar;
    @ViewInject(R.id.tv_title)
    private   TextView tvTitle;

    @ViewInject(R.id.viewpager)
    private ViewPager viewPager;

    private ViewPagerAdapter pagerAdapter;

    private BaseBean bean = new BaseBean();

    public static List<QuestionBean> questionsList = new ArrayList<>();

    @Override
    protected void init() {
        String json = FileUtils.readFromAssets(this, "data.json");
        bean = (BaseBean) JsonUtils.fromJson(json, BaseBean.class);

        questionsList.addAll(bean.getData());

        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), questionsList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);

        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageSelected(int arg0) {
            tvTitle.setText("(" + (arg0 + 1) + "/" + questionsList.size() + ")");
        }
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("(1/" + questionsList.size() + ")");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return viewPager;
    }
}
