package chingtech.library.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import chingtech.library.bean.RadarData;
import chingtech.library.widget.RadarView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.view.animation.Animation.RELATIVE_TO_PARENT;
import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * MyLibrary
 * Package chingtech.library.utils
 * Description: 动画工具类
 * Created by 师春雷
 * Created at 2017/6/7 15:35
 */
public class AnimatorUtils {

    // 动画持续时间
    public static final int ANIMATION_DURATION = 300;

    private AnimatorUtils() {
        /** cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 淡入
     *
     * @param view View
     */
    public static void showAlphaView(View view) {
        AlphaAnimation alphaAnim = new AlphaAnimation(0, 1);
        alphaAnim.setFillAfter(true);
        alphaAnim.setDuration(ANIMATION_DURATION);
        view.setAnimation(alphaAnim);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 淡出
     *
     * @param view View
     */
    public static void hidenAlphaView(View view) {
        AlphaAnimation alphaAnim = new AlphaAnimation(1, 0);
        alphaAnim.setFillAfter(true);
        alphaAnim.setDuration(ANIMATION_DURATION);
        view.setAnimation(alphaAnim);
        view.setVisibility(View.GONE);
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
        mHiddenAction.setDuration(ANIMATION_DURATION);
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
        mShowAction.setDuration(ANIMATION_DURATION);
        view.setAnimation(mShowAction);
        view.setVisibility(View.VISIBLE);
    }

    /***
     * 从控件所在位置移动到控件顶部隐藏
     *
     * @param view View
     */
    public static void moveToTopHiden(View view) {
        TranslateAnimation mHiddenAction = new TranslateAnimation(RELATIVE_TO_SELF, 0.0f,
                                                                  RELATIVE_TO_SELF, 0.0f,
                                                                  RELATIVE_TO_SELF, 0.0f,
                                                                  RELATIVE_TO_SELF, -1.0f);
        mHiddenAction.setDuration(ANIMATION_DURATION);
        view.setAnimation(mHiddenAction);
        view.setVisibility(View.GONE);
    }

    /**
     * 从控件顶部位置移动到控件所在位置显示
     *
     * @param view View
     */
    public static void fromTopToLocationShow(View view) {
        TranslateAnimation mShowAction = new TranslateAnimation(RELATIVE_TO_SELF, 0.0f,
                                                                RELATIVE_TO_SELF, 0.0f,
                                                                RELATIVE_TO_SELF, -1.0f,
                                                                RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(ANIMATION_DURATION);
        view.setAnimation(mShowAction);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 从左侧进入
     *
     * @param view
     */
    public static void fromLeftIn(View view) {
        TranslateAnimation mInAction = new TranslateAnimation(RELATIVE_TO_SELF, -1.0f,
                                                              RELATIVE_TO_SELF, 0.0f,
                                                              RELATIVE_TO_SELF, 0.0f,
                                                              RELATIVE_TO_SELF, 0.0f);
        mInAction.setDuration(ANIMATION_DURATION);
        view.setAnimation(mInAction);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 从左侧滑出
     *
     * @param view
     */
    public static void fromLeftOut(View view) {
        TranslateAnimation mOutAction = new TranslateAnimation(RELATIVE_TO_SELF, 0.0f,
                                                               RELATIVE_TO_SELF, -1.0f,
                                                               RELATIVE_TO_SELF, 0.0f,
                                                               RELATIVE_TO_SELF, 0.0f);
        mOutAction.setDuration(ANIMATION_DURATION);
        view.setAnimation(mOutAction);
        view.setVisibility(View.GONE);
    }

    /**
     * 从右侧进入
     *
     * @param view
     */
    public static void fromRightIn(View view) {
        TranslateAnimation mInAction = new TranslateAnimation(RELATIVE_TO_SELF, 1.0f,
                                                              RELATIVE_TO_SELF, 0.0f,
                                                              RELATIVE_TO_SELF, 0.0f,
                                                              RELATIVE_TO_SELF, 0.0f);
        mInAction.setDuration(ANIMATION_DURATION);
        view.setAnimation(mInAction);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 从右侧滑出
     *
     * @param view
     */
    public static void fromRightOut(View view) {
        TranslateAnimation mOutAction = new TranslateAnimation(RELATIVE_TO_SELF, 0.0f,
                                                               RELATIVE_TO_SELF, 1.0f,
                                                               RELATIVE_TO_SELF, 0.0f,
                                                               RELATIVE_TO_SELF, 0.0f);
        mOutAction.setDuration(ANIMATION_DURATION);
        view.setAnimation(mOutAction);
        view.setVisibility(View.GONE);
    }

    /**
     * 左侧中心旋转进入
     *
     * @param view
     */
    public static void RotaLeftCenterIn(View view) {
        RotateAnimation rotateAnim = new RotateAnimation(-90, 0, RELATIVE_TO_PARENT, 0f,
                                                         RELATIVE_TO_PARENT, 0.5f);
        rotateAnim.setFillAfter(true);
        rotateAnim.setDuration(ANIMATION_DURATION);
        view.setAnimation(rotateAnim);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 左侧中心旋转出
     *
     * @param view
     */
    public static void RotaLeftCenterOut(View view) {
        RotateAnimation rotateAnim = new RotateAnimation(0, 180, RELATIVE_TO_PARENT, 0f,
                                                         RELATIVE_TO_PARENT, 0.5f);
        rotateAnim.setFillAfter(true);
        rotateAnim.setDuration(ANIMATION_DURATION);
        view.setAnimation(rotateAnim);
        view.setVisibility(View.GONE);
    }

    /**
     * 左上角旋转
     *
     * @param view
     */
    public static void RotaLeftTopIn(View view) {
        RotateAnimation rotateAnim = new RotateAnimation(-90, 0, RELATIVE_TO_PARENT, 0f,
                                                         RELATIVE_TO_PARENT, 0f);
        rotateAnim.setFillAfter(true);
        rotateAnim.setDuration(ANIMATION_DURATION);
        view.setAnimation(rotateAnim);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 左上角旋转
     *
     * @param view
     */
    public static void RotaLeftTopOut(View view) {
        RotateAnimation rotateAnim = new RotateAnimation(0, -90, RELATIVE_TO_PARENT, 0f,
                                                         RELATIVE_TO_PARENT, 0f);
        rotateAnim.setFillAfter(true);
        rotateAnim.setDuration(ANIMATION_DURATION);
        view.setAnimation(rotateAnim);
        view.setVisibility(View.GONE);
    }

    /**
     * 中心旋转
     *
     * @param view
     */
    public static void RotaCenterIn(View view) {
        RotateAnimation rotateAnim = new RotateAnimation(0, 360, RELATIVE_TO_PARENT, 0.5f,
                                                         RELATIVE_TO_PARENT, 0.5f);
        rotateAnim.setFillAfter(true);
        rotateAnim.setDuration(ANIMATION_DURATION);
        view.setAnimation(rotateAnim);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 中心旋转
     *
     * @param view
     */
    public static void RotaCenterOut(View view) {
        RotateAnimation rotateAnim = new RotateAnimation(0, -360, RELATIVE_TO_PARENT, 0.5f,
                                                         RELATIVE_TO_PARENT, 0.5f);
        rotateAnim.setFillAfter(true);
        rotateAnim.setDuration(ANIMATION_DURATION);
        view.setAnimation(rotateAnim);
        view.setVisibility(View.GONE);
    }

    /**
     * 中心放大
     *
     * @param view
     */
    public static void ScaleBig(View view) {
        ScaleAnimation scaleAnim = new ScaleAnimation(0, 1.0f, 0, 1.0f, RELATIVE_TO_PARENT, 0.5f,
                                                      RELATIVE_TO_PARENT, 0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(ANIMATION_DURATION);
        view.setAnimation(scaleAnim);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 中心缩小
     *
     * @param view
     */
    public static void ScaleSmall(View view) {
        ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0, 1.0f, 0, RELATIVE_TO_PARENT, 0.5f,
                                                      RELATIVE_TO_PARENT, 0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(ANIMATION_DURATION);
        view.setAnimation(scaleAnim);
        view.setVisibility(View.GONE);
    }

    /**
     * 左上角放大
     *
     * @param view
     */
    public static void ScaleBigLeftTop(View view) {
        ScaleAnimation scaleAnim = new ScaleAnimation(0, 1.0f, 0, 1.0f, RELATIVE_TO_PARENT, 0,
                                                      RELATIVE_TO_PARENT, 0);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(ANIMATION_DURATION);
        view.setAnimation(scaleAnim);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 左上角缩小
     *
     * @param view
     */
    public static void ScaleSmallLeftTop(View view) {
        ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0, 1.0f, 0, RELATIVE_TO_PARENT, 0,
                                                      RELATIVE_TO_PARENT, 0);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(ANIMATION_DURATION);
        view.setAnimation(scaleAnim);
        view.setVisibility(View.GONE);
    }

    /**
     * 横向展开
     *
     * @param view
     */
    public static void ScaleToBigHorizontalIn(View view) {
        ScaleAnimation scaleAnim = new ScaleAnimation(0f, 1.0f, 1.0f, 1.0f, RELATIVE_TO_PARENT,
                                                      0.5f, RELATIVE_TO_PARENT, 0);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(ANIMATION_DURATION);
        view.setAnimation(scaleAnim);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 横向收缩
     *
     * @param view
     */
    public static void ScaleToBigHorizontalOut(View view) {
        ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0f, 1.0f, 1.0f, RELATIVE_TO_PARENT,
                                                      0.5f, RELATIVE_TO_PARENT, 0);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(ANIMATION_DURATION);
        view.setAnimation(scaleAnim);
        view.setVisibility(View.GONE);
    }

    /**
     * 纵向展开
     *
     * @param view
     */
    public static void ScaleToBigVerticalIn(View view) {
        ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 1.0f, 0f, 1.0f, RELATIVE_TO_PARENT, 0f,
                                                      RELATIVE_TO_PARENT, 0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(ANIMATION_DURATION);
        view.setAnimation(scaleAnim);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 纵向收缩
     *
     * @param view
     */
    public static void ScaleToBigVerticalOut(View view) {
        ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0f, RELATIVE_TO_PARENT, 0f,
                                                      RELATIVE_TO_PARENT, 0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(ANIMATION_DURATION);
        view.setAnimation(scaleAnim);
        view.setVisibility(View.GONE);
    }

    /**
     * 震动
     *
     * @param shakeCount
     * @param view
     */
    public static void ShakeMode(Integer shakeCount, View view) {
        TranslateAnimation transAnim = new TranslateAnimation(RELATIVE_TO_PARENT, -0.01f,
                                                              RELATIVE_TO_PARENT, 0.01f,
                                                              RELATIVE_TO_PARENT, 0,
                                                              RELATIVE_TO_PARENT, 0);
        if (shakeCount == null) {
            transAnim.setRepeatCount(1);
        } else {
            transAnim.setRepeatCount(shakeCount);
        }
        transAnim.setDuration(100);
        view.setAnimation(transAnim);
    }

    /**
     * 上下翻转
     *
     * @param view
     */
    public static void FlipUpDown(final View view, final boolean isShow) {
        ObjectAnimator objAnim = ObjectAnimator.ofFloat(view, "rotationX", 0, 360);
        objAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        objAnim.setDuration(ANIMATION_DURATION);
        objAnim.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {
            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                if (isShow) {
                    if (view.getVisibility() != View.VISIBLE) {
                        view.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (view.getVisibility() != View.GONE) {
                        view.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator arg0) {
            }
        });
        AnimatorSet as = new AnimatorSet();
        as.play(objAnim);
        as.start();
    }

    /**
     * 左右翻转
     *
     * @param view
     */
    public static void FlipLeftRight(final View view, final boolean isShow) {
        ObjectAnimator objAnim = ObjectAnimator.ofFloat(view, "rotationY", 0, 360);
        objAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        objAnim.setDuration(ANIMATION_DURATION);
        objAnim.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {
            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                if (isShow) {
                    if (view.getVisibility() != View.VISIBLE) {
                        view.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (view.getVisibility() != View.GONE) {
                        view.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator arg0) {
            }
        });
        AnimatorSet as = new AnimatorSet();
        as.play(objAnim);
        as.start();
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

    public AnimatorUtils(RadarView view) {
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

    /**
     * alpha
     *
     * @param v
     * @param fromAlpha
     * @param toAlpha
     * @param duration
     */
    public static void alpha(View v, float fromAlpha, float toAlpha, int duration) {
        alpha(v, fromAlpha, toAlpha, duration, null);
    }

    /**
     * alpha
     *
     * @param v
     * @param fromAlpha
     * @param toAlpha
     * @param duration
     * @param animatorListener
     */
    public static void alpha(View v, float fromAlpha, float toAlpha, int duration,
            Animator.AnimatorListener animatorListener) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, View.ALPHA, fromAlpha, toAlpha);
        animator.setDuration(duration);
        if (animatorListener != null) {
            animator.addListener(animatorListener);
        }
        animator.start();
    }

    /**
     * translation x
     *
     * @param v
     * @param fromX
     * @param toX
     * @param duration
     */
    public static void translationX(View v, float fromX, float toX, int duration) {
        translationX(v, fromX, toX, duration, null);
    }

    /**
     * translation x
     *
     * @param v
     * @param fromX
     * @param toX
     * @param duration
     * @param animatorListener
     */
    public static void translationX(View v, float fromX, float toX, int duration,
            Animator.AnimatorListener animatorListener) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, fromX, toX);
        animator.setDuration(duration);
        if (animatorListener != null) {
            animator.addListener(animatorListener);
        }
        animator.start();
    }

    /**
     * translation y
     *
     * @param v
     * @param fromY
     * @param toY
     * @param duration
     */
    public static void translationY(View v, float fromY, float toY, int duration) {
        translationY(v, fromY, toY, duration, null);
    }

    /**
     * translation y
     *
     * @param v
     * @param fromY
     * @param toY
     * @param duration
     * @param animatorListener
     */
    public static void translationY(View v, float fromY, float toY, int duration,
            Animator.AnimatorListener animatorListener) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, View.TRANSLATION_Y, fromY, toY);
        animator.setDuration(duration);
        if (animatorListener != null) {
            animator.addListener(animatorListener);
        }
        animator.start();
    }

    /**
     * rotation x
     *
     * @param v
     * @param fromX
     * @param toX
     * @param duration
     */
    public static void rotationX(View v, float fromX, float toX, int duration) {
        rotationX(v, fromX, toX, duration, null);
    }

    /**
     * rotation x
     *
     * @param v
     * @param fromX
     * @param toX
     * @param duration
     * @param animatorListener
     */
    public static void rotationX(View v, float fromX, float toX, int duration,
            Animator.AnimatorListener animatorListener) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, View.ROTATION_X, fromX, toX);
        animator.setDuration(duration);
        if (animatorListener != null) {
            animator.addListener(animatorListener);
        }
        animator.start();
    }

    /**
     * rotation y
     *
     * @param v
     * @param fromY
     * @param toY
     * @param duration
     */
    public static void rotationY(View v, float fromY, float toY, int duration) {
        rotationY(v, fromY, toY, duration, null);
    }

    /**
     * rotation y
     *
     * @param v
     * @param fromY
     * @param toY
     * @param duration
     * @param animatorListener
     */
    public static void rotationY(View v, float fromY, float toY, int duration,
            Animator.AnimatorListener animatorListener) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, View.ROTATION_Y, fromY, toY);
        animator.setDuration(duration);
        if (animatorListener != null) {
            animator.addListener(animatorListener);
        }
        animator.start();
    }

    /**
     * scale x
     *
     * @param v
     * @param fromX
     * @param toX
     * @param duration
     */
    public static void scaleX(View v, float fromX, float toX, int duration) {
        scaleX(v, fromX, toX, duration, null);
    }

    /**
     * scale x
     *
     * @param v
     * @param fromX
     * @param toX
     * @param duration
     * @param animatorListener
     */
    public static void scaleX(View v, float fromX, float toX, int duration,
            Animator.AnimatorListener animatorListener) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, View.SCALE_X, fromX, toX);
        animator.setDuration(duration);
        if (animatorListener != null) {
            animator.addListener(animatorListener);
        }
        animator.start();
    }

    /**
     * scale y
     *
     * @param v
     * @param fromY
     * @param toY
     * @param duration
     */
    public static void scaleY(View v, float fromY, float toY, int duration) {
        scaleY(v, fromY, toY, duration, null);
    }

    /**
     * scale y
     *
     * @param v
     * @param fromY
     * @param toY
     * @param duration
     * @param animatorListener
     */
    public static void scaleY(View v, float fromY, float toY, int duration,
            Animator.AnimatorListener animatorListener) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, View.SCALE_Y, fromY, toY);
        animator.setDuration(duration);
        if (animatorListener != null) {
            animator.addListener(animatorListener);
        }
        animator.start();
    }

    /**
     * shake x
     *
     * @param v
     */
    public static void shakeX(View v) {
        shakeX(v, 10, 1000, 5.0f);
    }

    /**
     * shake x
     *
     * @param v
     * @param offset
     * @param duration
     * @param times
     */
    public static void shakeX(View v, float offset, long duration, float times) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, 0, offset);
        animator.setDuration(duration);
        animator.setInterpolator(new CycleInterpolator(times));
        animator.start();
    }

    /**
     * shake y
     *
     * @param v
     */
    public static void shakeY(View v) {
        shakeY(v, 10, 1000, 5.0f);
    }

    /**
     * shake y
     *
     * @param v
     * @param offset
     * @param duration
     * @param times
     */
    public static void shakeY(View v, float offset, long duration, float times) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, View.TRANSLATION_Y, 0, offset);
        animator.setDuration(duration);
        animator.setInterpolator(new CycleInterpolator(times));
        animator.start();
    }

    /**
     * 呼吸灯效果
     *
     * @param v
     */
    public static void breath(View v) {
        breath(v, 0.0f, 1.0f, 1000);
    }

    /**
     * 呼吸灯效果
     *
     * @param v
     * @param fromRange
     * @param toRange
     * @param duration
     */
    public static void breath(View v, float fromRange, float toRange, long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, View.ALPHA, fromRange, toRange);
        animator.setDuration(duration);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}
