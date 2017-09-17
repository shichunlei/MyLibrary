package com.chingtech.sample.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.LinearLayout;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.SPUtils;
import com.chingtech.sample.R;
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
 * Created at 17/8/27 下午1:32
 */
@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends BaseActivity {

    @ViewInject(R.id.layout)
    LinearLayout welcome;

    private boolean isPatternLock;
    private boolean isPinLock;

    @Override
    protected void init() {
        isPatternLock = (Boolean) SPUtils.get(this, "PatternLock", false, "DEMO");
        isPinLock = (Boolean) SPUtils.get(this, "PinLock", false, "DEMO");

        // 透明度动画
        ObjectAnimator animator = ObjectAnimator.ofFloat(welcome, "alpha", 0.0f, 1.0f);
        animator.setDuration(3000);//动画时间3秒
        animator.start();

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (isPatternLock) {
                    openActivity(PatternLockActivity.class, "flag", "welcome", true);
                } else if (isPinLock) {
                    openActivity(PinLockActivity.class, "flag", "welcome", true);
                } else {
                    openActivity(MainActivity.class, true);
                }
            }
        });
    }

    @Override
    protected void initToolBar() {
    }

    @Override
    protected View injectTarget() {
        return welcome;
    }
}
