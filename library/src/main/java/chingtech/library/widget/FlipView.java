package chingtech.library.widget;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import chingtech.library.R;

/**
 * 可翻转的View，仿支付宝-蚂蚁宝卡里流量兑换的翻转View
 */
public class FlipView extends FrameLayout {

    public static final int DEFAULT_FLIP_DURATION = 500;

    private int animLeftFlipOutId = R.animator.left_flip_out;
    private int animLeftFlipInId  = R.animator.left_flip_in;

    private int animRightFlipOutId = R.animator.right_flip_out;
    private int animRightFlipInId  = R.animator.right_flip_in;

    /** 面朝上状态 */
    private int mFlipState;
    /** 正面 */
    private static final int FRONT_SIDE = 0;
    /** 背面 */
    private static final int BACK_SIDE  = 1;

    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;

    private AnimatorSet mSetLeftOut;
    private AnimatorSet mSetRightIn;

    private View mCardFrontLayout;
    private View mCardBackLayout;

    private boolean flipOnTouch;
    private int     flipDuration;
    private boolean flipEnabled;

    private Context context;
    private float   x1;
    private float   y1;

    public FlipView(Context context) {
        super(context);
        this.context = context;
        init(context, null);
    }

    public FlipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // Check for the attributes
        if (attrs != null) {
            // Attribute initialization
            TypedArray attrArray = context.obtainStyledAttributes(attrs, R.styleable.FlipView, 0,
                                                                  0);
            try {
                flipOnTouch = attrArray.getBoolean(R.styleable.FlipView_flip_touch, true);
                flipDuration = attrArray.getInt(R.styleable.FlipView_flip_duration,
                                                DEFAULT_FLIP_DURATION);
                flipEnabled = attrArray.getBoolean(R.styleable.FlipView_flip_enabled, true);
                mFlipState = attrArray.getInt(R.styleable.FlipView_flip_state, FRONT_SIDE);
            } finally {
                attrArray.recycle();
            }
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (getChildCount() > 2) {
            throw new IllegalStateException("EasyFlipView can host only two direct children!");
        }

        findViews();

        loadAnimationsFrontToBack();
        loadAnimationsBackToFront();

        setFlipDuration(getFlipDuration());

        changeCameraDistance();
    }

    /**
     * 初始化正反面
     */
    private void findViews() {
        mCardFrontLayout = getChildAt(1);
        mCardBackLayout = getChildAt(0);

        if (!isFlipOnTouch()) {
            if (isFrontSide()) {
                mCardFrontLayout.setVisibility(VISIBLE);
                mCardBackLayout.setVisibility(GONE);
            } else if (isBackSide()) {
                mCardFrontLayout.setVisibility(GONE);
                mCardBackLayout.setVisibility(VISIBLE);
            }
        }
    }

    /**
     * 正面转向背面
     */
    private void loadAnimationsFrontToBack() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(context, animRightFlipOutId);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(context, animRightFlipInId);

        mSetRightOut.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                if (isFrontSide()) {
                    mCardBackLayout.setVisibility(GONE);
                    mCardFrontLayout.setVisibility(VISIBLE);
                } else if (isBackSide()) {
                    mCardBackLayout.setVisibility(VISIBLE);
                    mCardFrontLayout.setVisibility(GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    /**
     * 背面转向正面
     */
    private void loadAnimationsBackToFront() {
        mSetLeftOut = (AnimatorSet) AnimatorInflater.loadAnimator(context, animLeftFlipOutId);
        mSetRightIn = (AnimatorSet) AnimatorInflater.loadAnimator(context, animLeftFlipInId);

        mSetLeftOut.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                if (isFrontSide()) {
                    mCardBackLayout.setVisibility(GONE);
                    mCardFrontLayout.setVisibility(VISIBLE);
                } else if (isBackSide()) {
                    mCardBackLayout.setVisibility(VISIBLE);
                    mCardFrontLayout.setVisibility(GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private void changeCameraDistance() {
        int   distance = 8000;
        float scale    = getResources().getDisplayMetrics().density * distance;
        mCardFrontLayout.setCameraDistance(scale);
        mCardBackLayout.setCameraDistance(scale);
    }

    /**
     * Play the animation of flipping and flip the view for one side!
     */
    public void flipTheView() {

        if (!flipEnabled) {
            return;
        }

        if (mSetRightOut.isRunning() || mSetLeftIn.isRunning()) {
            return;
        }
        if (mSetLeftOut.isRunning() || mSetRightIn.isRunning()) {
            return;
        }

        mCardBackLayout.setVisibility(VISIBLE);
        mCardFrontLayout.setVisibility(VISIBLE);

        if (isFrontSide()) {
            // From front to back
            mSetRightOut.setTarget(mCardFrontLayout);
            mSetLeftIn.setTarget(mCardBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mFlipState = BACK_SIDE;
        } else if (isBackSide()) {
            // from back to front
            mSetLeftOut.setTarget(mCardBackLayout);
            mSetRightIn.setTarget(mCardFrontLayout);
            mSetLeftOut.start();
            mSetRightIn.start();
            mFlipState = FRONT_SIDE;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (isEnabled() && flipOnTouch) {
            this.getParent().requestDisallowInterceptTouchEvent(true);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x1 = event.getX();
                    y1 = event.getY();
                    return true;
                case MotionEvent.ACTION_UP:
                    float x2 = event.getX();
                    float y2 = event.getY();
                    float dx = x2 - x1;
                    float dy = y2 - y1;
                    float MAX_CLICK_DISTANCE = 0.5f;
                    if ((dx >= 0 && dx < MAX_CLICK_DISTANCE) && (dy >= 0
                            && dy < MAX_CLICK_DISTANCE)) {
                        flipTheView();
                    }
                    return true;
            }
        } else {
            return super.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    /**
     * Whether view is set to flip on touch or not.
     *
     * @return true or false
     */
    public boolean isFlipOnTouch() {
        return flipOnTouch;
    }

    /**
     * Set whether view should be flipped on touch or not!
     *
     * @param flipOnTouch value (true or false)
     */
    public void setFlipOnTouch(boolean flipOnTouch) {
        this.flipOnTouch = flipOnTouch;
    }

    /**
     * Returns duration of flip in milliseconds!
     *
     * @return duration in milliseconds
     */
    public int getFlipDuration() {
        return flipDuration;
    }

    /**
     * Sets the flip duration (in milliseconds)
     *
     * @param flipDuration duration in milliseconds
     */
    public void setFlipDuration(int flipDuration) {
        this.flipDuration = flipDuration;

        mSetRightOut.getChildAnimations().get(0).setDuration(flipDuration);
        mSetRightOut.getChildAnimations().get(1).setStartDelay(flipDuration / 2);

        mSetLeftIn.getChildAnimations().get(1).setDuration(flipDuration);
        mSetLeftIn.getChildAnimations().get(2).setStartDelay(flipDuration / 2);

        mSetLeftOut.getChildAnimations().get(0).setDuration(flipDuration);
        mSetLeftOut.getChildAnimations().get(1).setStartDelay(flipDuration / 2);

        mSetRightIn.getChildAnimations().get(1).setDuration(flipDuration);
        mSetRightIn.getChildAnimations().get(2).setStartDelay(flipDuration / 2);
    }

    /**
     * Returns whether flip is enabled or not!
     *
     * @return true or false
     */
    public boolean isFlipEnabled() {
        return flipEnabled;
    }

    /**
     * Enable / Disable flip view.
     *
     * @param flipEnabled true or false
     */
    public void setFlipEnabled(boolean flipEnabled) {
        this.flipEnabled = flipEnabled;
    }

    /**
     * Returns which flip state is currently on of the flip view.
     *
     * @return current state of flip view
     */
    public int getFlipState() {
        return this.mFlipState;
    }

    /**
     * Set The FlipState
     *
     * @param state
     */
    public void setFlipState(int state) {
        this.mFlipState = state;
    }

    /**
     * Returns true if the front side of flip view is visible.
     *
     * @return true if the front side of flip view is visible.
     */
    public boolean isFrontSide() {
        return (getFlipState() == FRONT_SIDE);
    }

    /**
     * Returns true if the back side of flip view is visible.
     *
     * @return true if the back side of flip view is visible.
     */
    public boolean isBackSide() {
        return (getFlipState() == BACK_SIDE);
    }
}
