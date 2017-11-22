package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.StatusBarHelper;
import chingtech.library.widget.AnimNumberTextView;
import com.chingtech.sample.R;

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
 * Created at 17/11/22 下午12:16
 */
public class AnimNumberTextViewActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar  toolbar;

    @BindView(R.id.rtv)
    AnimNumberTextView rtv;

    @BindView(R.id.layout)
    LinearLayout layout;

    private int[] pianyiliang = new int[6];

    @Override
    protected void init() {
        pianyiliang[0] = 10;
        pianyiliang[1] = 9;
        pianyiliang[2] = 8;
        pianyiliang[3] = 7;
        pianyiliang[4] = 6;
        pianyiliang[5] = 5;
        rtv.setPianyilian(pianyiliang);
        rtv.start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_number_text;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("跳动的数字");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return layout;
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.button2, R.id.button3, R.id.button4, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button2:
                rtv.setText("912111");
                pianyiliang[0] = 7;
                pianyiliang[1] = 6;
                pianyiliang[2] = 12;
                pianyiliang[3] = 8;
                pianyiliang[4] = 18;
                pianyiliang[5] = 10;
                rtv.setMaxLine(20);
                rtv.setPianyilian(pianyiliang);
                rtv.start();
                break;
            case R.id.button3:
                rtv.setText("9078111123");
                rtv.setPianyilian(AnimNumberTextView.FIRSTF_LAST);
                rtv.start();
                break;
            case R.id.button4:
                rtv.setText("12313288");
                rtv.setPianyilian(AnimNumberTextView.FIRSTF_FIRST);
                rtv.start();
                break;
            case R.id.button:
                rtv.setText("876543");
                rtv.setPianyilian(AnimNumberTextView.ALL);
                rtv.start();
                break;
        }
    }
}
