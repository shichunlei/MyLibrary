package com.chingtech.sample.view;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.AnimatorUtils;
import chingtech.library.utils.StatusBarHelper;
import chingtech.library.widget.MarqueeView;
import com.chingtech.sample.R;
import java.util.ArrayList;
import java.util.List;

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
 * Created at 17/11/22 上午10:43
 */
public class MarqueeViewActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.marqueeView1)
    MarqueeView marqueeView1;
    @BindView(R.id.marqueeView2)
    MarqueeView marqueeView2;
    @BindView(R.id.marqueeView3)
    MarqueeView marqueeView3;
    @BindView(R.id.marqueeView4)
    MarqueeView marqueeView4;

    @BindView(R.id.image)
    ImageView image;

    @Override
    protected void init() {
        List<CharSequence> list = new ArrayList<>();
        SpannableString    ss1  = new SpannableString("1、MarqueeView开源项目");
        ss1.setSpan(new ForegroundColorSpan(Color.RED), 2, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss1);
        SpannableString ss2 = new SpannableString("2、GitHub：sfsheng0322");
        ss2.setSpan(new ForegroundColorSpan(Color.GREEN), 9, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss2);
        SpannableString ss3 = new SpannableString("3、个人博客：sunfusheng.com");
        ss3.setSpan(new URLSpan("http://sunfusheng.com/"), 7, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss3);
        list.add("4、新浪微博：@孙福生微博");

        marqueeView.startWithList(list);
        marqueeView.setOnItemClickListener(
                (position, textView) -> showToast(textView.getText().toString().trim()));

        marqueeView1.startWithText(getString(R.string.marquee_texts), R.anim.anim_top_in,
                                   R.anim.anim_bottom_out);
        marqueeView1.setOnItemClickListener((position, textView) -> showToast(
                String.valueOf(marqueeView1.getPosition()) + ". " + textView.getText()));

        marqueeView2.startWithText(getString(R.string.marquee_text));

        marqueeView3.startWithText(getString(R.string.marquee_texts));

        marqueeView4.startWithText(getString(R.string.marquee_texts));

        AnimatorUtils.alpha(image, 0, 1, 1000);

        AnimatorUtils.breath(image);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_marquee_view;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("跑马灯效果");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        marqueeView.startFlipping();
        marqueeView1.startFlipping();
        marqueeView2.startFlipping();
        marqueeView3.startFlipping();
        marqueeView4.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeView.stopFlipping();
        marqueeView1.stopFlipping();
        marqueeView2.stopFlipping();
        marqueeView3.stopFlipping();
        marqueeView4.stopFlipping();
    }
}
