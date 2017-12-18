package com.chingtech.sample.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.SPUtils;
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
 * Created at 17/8/27 下午1:32
 */
public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.layout)
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
                    openActivity(LoginActivity.class, true);
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initToolBar() {
    }

    @Override
    protected View injectTarget() {
        return welcome;
    }

    @Override
    protected void loadData() {

    }
}
