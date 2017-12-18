package com.chingtech.sample.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.StatusBarHelper;
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
 * Created at 17/12/16 下午3:01
 */
public class AnimtionSampleActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar  toolbar;

    @BindView(R.id.progress_horizontal)
    ProgressBar progressBar;

    @BindView(R.id.view)
    View view;

    @BindView(R.id.scrollView)
    NestedScrollView scrollView;

    ValueAnimator valueAnimator;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_animtion;
    }

    @Override
    protected void init() {
        valueAnimator = ValueAnimator.ofInt(0, 100, 50, 100);
        valueAnimator.addUpdateListener(
                animation -> progressBar.setProgress((Integer) animation.getAnimatedValue()));
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Toast.makeText(context, "动画开始", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(context, "动画结束", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Toast.makeText(context, "动画取消", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Toast.makeText(context, "动画重复", Toast.LENGTH_SHORT).show();
            }
        });
        valueAnimator.setDuration(5000);
        valueAnimator.setRepeatCount(1);
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("基础动画示例");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return scrollView;
    }

    @Override
    protected void loadData() {
    }

    @SuppressLint({"NewApi", "WrongConstant"})
    @OnClick({R.id.btn_start, R.id.btn_resume, R.id.btn_ofARGB, R.id.btn_pause, R.id.btn_cancel,
            R.id.btn_stop, R.id.btn_alpha, R.id.btn_ScaleX, R.id.btn_ScaleY, R.id.btn_TranslationX,
            R.id.btn_TranslationY, R.id.btn_TranslationZ, R.id.btn_rotation, R.id.btn_rotationX,
            R.id.btn_rotationY, R.id.btn_set})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                valueAnimator.start();
                break;
            case R.id.btn_resume:
                valueAnimator.resume();
                break;
            case R.id.btn_pause:
                valueAnimator.pause();
                break;
            case R.id.btn_cancel:
                valueAnimator.cancel();
                break;
            case R.id.btn_stop:
                valueAnimator.end();
                break;
            case R.id.btn_ofARGB:// 点击按钮，让背景颜色由白变红再变蓝
                ObjectAnimator animator = ObjectAnimator.ofArgb(view, "BackgroundColor", 0xffff00ff,
                                                                0xffffff00, 0xffff00ff);
                /**
                 * ArgbEvaluator：这种评估者可以用来执行类型之间的插值整数值代表ARGB颜色。
                 * FloatEvaluator：这种评估者可以用来执行浮点值之间的插值。
                 * IntEvaluator：这种评估者可以用来执行类型int值之间的插值。
                 * RectEvaluator：这种评估者可以用来执行类型之间的插值矩形值。
                 *
                 * 由于本例是改变View的backgroundColor属性的背景颜色所以此处使用ArgbEvaluator
                 */
                animator.setEvaluator(new ArgbEvaluator());
                animator.setDuration(4000);
                animator.start();
                break;
            case R.id.btn_alpha:
                ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
                alpha.setDuration(5000);
                /**
                 * Interpolator对象	                    功能作用
                 * AccelerateDecelerateInterpolator	    先加速再减速
                 * AccelerateInterpolator	            加速
                 * AnticipateInterpolator	            先回退一小步然后加速前进
                 * AnticipateOvershootInterpolator	    在上一个基础上超出终点一小步再回到终点
                 * BounceInterpolator	                最后阶段弹球效果
                 * CycleInterpolator	                周期运动
                 * DecelerateInterpolator	            减速
                 * LinearInterpolator	                匀速
                 * OvershootInterpolator	            快速到达终点并超出一小步最后回到终点
                 */
                alpha.setInterpolator(new DecelerateInterpolator());//设置动画插入器，减速
                alpha.setRepeatCount(-1);//设置动画重复次数，这里-1代表无限
                alpha.setRepeatMode(Animation.REVERSE);//设置动画循环模式。 RESTART从头开始 或者 REVERSE 从末尾开始
                alpha.start();
                break;

            case R.id.btn_ScaleX:
                ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1.5f, 2f,
                                                                       1.5f, 0f, 0.5f, 0.2f, 1f);
                animatorScaleX.setDuration(5000);
                animatorScaleX.start();
                break;
            case R.id.btn_ScaleY:
                ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f, 1f);
                animatorScaleY.setDuration(5000);
                animatorScaleY.start();
                break;

            case R.id.btn_TranslationX:
                ObjectAnimator animatorTranslationX = ObjectAnimator.ofFloat(view, "translationX",
                                                                             0, 100, -100, 0);
                animatorTranslationX.setDuration(5000);
                animatorTranslationX.start();
                break;
            case R.id.btn_TranslationY:
                ObjectAnimator animatorTranslationY = ObjectAnimator.ofFloat(view, "translationY",
                                                                             0, 100, -100, 0);
                animatorTranslationY.setDuration(5000);
                animatorTranslationY.start();
                break;
            case R.id.btn_TranslationZ:
                ObjectAnimator animatorTranslationZ = ObjectAnimator.ofFloat(view, "translation",
                                                                             10);
                animatorTranslationZ.setDuration(5000);
                animatorTranslationZ.start();
                break;
            case R.id.btn_rotation:
                ObjectAnimator animatorZ = ObjectAnimator.ofFloat(view, "rotation", 0, 180, 0, -180,
                                                                  0);
                animatorZ.setDuration(3000);
                animatorZ.start();
                break;
            case R.id.btn_rotationX:
                ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "rotationX", 0, 180, 0,
                                                                  -180, 0);
                animatorX.setDuration(3000);
                animatorX.start();
                break;
            case R.id.btn_rotationY:
                ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "rotationY", 0, 180, 0,
                                                                  -180, 0);
                animatorY.setDuration(3000);
                animatorY.start();
                break;

            case R.id.btn_set:
                AnimatorSet set = new AnimatorSet();
                ObjectAnimator anim0 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
                anim0.setDuration(2000);
                ObjectAnimator anim = ObjectAnimator.ofFloat(view, "rotationX", 0f, 180f);
                anim.setDuration(2000);
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "rotationX", 180f, 0f);
                anim2.setDuration(2000);
                ObjectAnimator anim3 = ObjectAnimator.ofFloat(view, "rotationY", 0f, 180f);
                anim3.setDuration(2000);
                ObjectAnimator anim4 = ObjectAnimator.ofFloat(view, "rotationY", 180f, 0f);
                anim4.setDuration(2000);

                set.play(anim).with(anim0).before(anim2); //先执行anim动画之后在执行anim2
                set.play(anim3).before(anim4);
                set.start();
                break;
        }
    }
}
