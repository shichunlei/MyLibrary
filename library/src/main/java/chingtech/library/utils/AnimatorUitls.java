package chingtech.library.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.TranslateAnimation;
import chingtech.library.bean.RadarData;
import chingtech.library.widget.RadarView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

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
     * 从控件所在位置移动到底部隐藏
     *
     * @param view View
     */
    public static void moveToBottomHiden(View view) {
        TranslateAnimation mHiddenAction = new TranslateAnimation(RELATIVE_TO_SELF, 0.0f,
                                                                  RELATIVE_TO_SELF, 0.0f,
                                                                  RELATIVE_TO_SELF, 0.0f,
                                                                  RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(500);
        view.setAnimation(mHiddenAction);
        view.setVisibility(View.GONE);
    }

    /**
     * 从底部移动到控件所在位置显示
     *
     * @param view View
     */
    public static void fromBottomToLocationShow(View view) {
        TranslateAnimation mShowAction = new TranslateAnimation(RELATIVE_TO_SELF, 0.0f,
                                                                RELATIVE_TO_SELF, 0.0f,
                                                                RELATIVE_TO_SELF, 1.0f,
                                                                RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);
        view.setAnimation(mShowAction);
        view.setVisibility(View.VISIBLE);
    }

    /***
     * 从控件所在位置移动到控件顶部隐藏
     *
     * @param view View
     */
    public void moveToTopHiden(View view) {
        TranslateAnimation mHiddenAction = new TranslateAnimation(RELATIVE_TO_SELF, 0.0f,
                                                                  RELATIVE_TO_SELF, 0.0f,
                                                                  RELATIVE_TO_SELF, 0.0f,
                                                                  RELATIVE_TO_SELF, -1.0f);
        mHiddenAction.setDuration(500);
        view.setAnimation(mHiddenAction);
        view.setVisibility(View.GONE);
    }

    /**
     * 从控件顶部位置移动到控件所在位置显示
     *
     * @param view View
     */
    public void fromTopToLocationShow(View view) {
        TranslateAnimation mShowAction = new TranslateAnimation(RELATIVE_TO_SELF, 0.0f,
                                                                RELATIVE_TO_SELF, 0.0f,
                                                                RELATIVE_TO_SELF, -1.0f,
                                                                RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);
        view.setAnimation(mShowAction);
        view.setVisibility(View.VISIBLE);
    }

    public void fromLeftIn(View view) {
        TranslateAnimation mInAction = new TranslateAnimation(RELATIVE_TO_SELF, -1.0f,
                                                              RELATIVE_TO_SELF, 0.0f,
                                                              RELATIVE_TO_SELF, 0.0f,
                                                              RELATIVE_TO_SELF, 0.0f);
        mInAction.setDuration(500);
        view.setAnimation(mInAction);
        view.setVisibility(View.VISIBLE);
    }

    public void fromRightOut(View view) {
        TranslateAnimation mOutAction = new TranslateAnimation(RELATIVE_TO_SELF, 0.0f,
                                                               RELATIVE_TO_SELF, 1.0f,
                                                               RELATIVE_TO_SELF, 0.0f,
                                                               RELATIVE_TO_SELF, 0.0f);
        mOutAction.setDuration(500);
        view.setAnimation(mOutAction);
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

    private WeakReference<RadarView>          mWeakRadarView;
    private HashMap<RadarData, ValueAnimator> mAnimes;

    public AnimatorUitls(RadarView view) {
        mWeakRadarView = new WeakReference<>(view);
        mAnimes = new HashMap<>();
    }

    public void animeValue(AnimeType type, int duration, RadarData data) {
        switch (type) {
            case ZOOM:
                startZoomAnime(duration, data);
                break;
        }
    }

    public boolean isPlaying() {
        boolean isPlaying = false;
        for (ValueAnimator anime : mAnimes.values()) {
            isPlaying = anime.isStarted();
            if (isPlaying) {
                break;
            }
        }
        return isPlaying;
    }

    public boolean isPlaying(RadarData data) {
        ValueAnimator anime = mAnimes.get(data);
        return anime != null && anime.isStarted();
    }

    private void startZoomAnime(final int duration, final RadarData data) {
        final ValueAnimator anime   = ValueAnimator.ofFloat(0, 1f);
        final List<Float>   values  = data.getValue();
        final List<Float>   values2 = new ArrayList<>(values);
        anime.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                RadarView view = mWeakRadarView.get();
                if (view == null) {
                    anime.end();
                } else {
                    float percent = Float.parseFloat(animation.getAnimatedValue().toString());
                    for (int i = 0; i < values.size(); i++) {
                        values.set(i, values2.get(i) * percent);
                    }
                    view.invalidate();
                }
            }
        });
        anime.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mAnimes.remove(data);
            }
        });
        anime.setDuration(duration).start();
        mAnimes.put(data, anime);
    }

    public enum AnimeType {
        ZOOM, ROTATE
    }

    /** //////////////////////////////////////////////////////////////////////////// */
}
