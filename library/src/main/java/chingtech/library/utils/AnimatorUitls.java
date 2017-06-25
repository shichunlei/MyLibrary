package chingtech.library.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * MyLibrary
 * Package chingtech.library.utils
 * Description:
 * Created by 师春雷
 * Created at 2017/6/7 15:35
 */
public class AnimatorUitls {

    private AnimatorUitls() {
        /** cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 淡入
     *
     * @param view View
     */
    public static void showAlphaView(final View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        animator.setDuration(500);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * 淡出
     *
     * @param view View
     */
    public static void hidenAlphaView(final View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        animator.setDuration(500);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
    }

    /***
     * 从控件所在位置移动到控件的底部
     *
     * @param view View
     */
    public static void moveToViewBottom(View view) {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                                                                  Animation.RELATIVE_TO_SELF, 0.0f,
                                                                  Animation.RELATIVE_TO_SELF, 0.0f,
                                                                  Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(500);
        view.setAnimation(mHiddenAction);
        view.setVisibility(View.GONE);
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @param view View
     */
    public static void moveToViewLocation(View view) {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                                                                  Animation.RELATIVE_TO_SELF, 0.0f,
                                                                  Animation.RELATIVE_TO_SELF, 1.0f,
                                                                  Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(500);
        view.setAnimation(mHiddenAction);
        view.setVisibility(View.GONE);
    }

    /** //////////////////////////////////////////////////////////////////////////// */

    // 动画持续时间
    public static final int ANIMATION_DURATION = 300;

    public static void fadeInView(View targetView) {
        fadeInView(targetView, ANIMATION_DURATION);
    }

    /**
     * 渐隐式显示控件
     *
     * @param targetView 目标控件
     * @param duration   动画持续时间
     */
    public static void fadeInView(final View targetView, int duration) {
        ViewCompat.animate(targetView)
                  .alpha(1f)
                  .setDuration(duration)
                  .setListener(new ViewPropertyAnimatorListener() {
                      @Override
                      public void onAnimationStart(View view) {
                          if (targetView.getVisibility() != View.VISIBLE) {
                              targetView.setVisibility(View.VISIBLE);
                          }
                          targetView.setAlpha(0f);
                      }

                      @Override
                      public void onAnimationEnd(View view) {

                      }

                      @Override
                      public void onAnimationCancel(View view) {

                      }
                  });
    }

    public static void fadeOutView(View targetView) {
        fadeOutView(targetView, ANIMATION_DURATION);
    }

    /**
     * 渐隐式隐藏控件
     *
     * @param targetView 目标控件
     * @param duration   动画持续时间
     */
    public static void fadeOutView(final View targetView, int duration) {
        ViewCompat.animate(targetView)
                  .alpha(0f)
                  .setDuration(duration)
                  .setListener(new ViewPropertyAnimatorListener() {
                      @Override
                      public void onAnimationStart(View view) {

                      }

                      @Override
                      public void onAnimationEnd(View view) {
                          if (targetView.getVisibility() != View.GONE) {
                              targetView.setVisibility(View.GONE);
                          }
                      }

                      @Override
                      public void onAnimationCancel(View view) {

                      }
                  });
    }

    /**
     * 揭露式显示/隐藏控件
     *
     * @param targetView 目标控件
     * @param isShow     true 为显示View false为隐藏View
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void revealViewToggle(final View targetView, final boolean isShow) {
        // 圆点坐标
        int cx = targetView.getWidth();
        int cy = targetView.getHeight() / 2;
        // 半径
        int      endRadius = Math.max(targetView.getWidth(), targetView.getHeight());
        Animator circularReveal;
        if (isShow) {
            circularReveal = ViewAnimationUtils.createCircularReveal(targetView, cx, cy, 0,
                                                                     endRadius);
        } else {
            circularReveal = ViewAnimationUtils.createCircularReveal(targetView, cx, cy, endRadius,
                                                                     0);
        }
        circularReveal.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (isShow) {
                    targetView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!isShow) {
                    targetView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        circularReveal.start();
    }

    /** //////////////////////////////////////////////////////////////////////////// */
}
