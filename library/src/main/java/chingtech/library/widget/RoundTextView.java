package chingtech.library.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import chingtech.library.R;

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
 * <p>
 * 1. RoundTextView 让你从此不再编写和管理大量 selector 文件（这个太可恨了）
 * 2. RoundTextView 改造了 drawableLeft/drawableXXX 图片的大小，从此你不在需要使用 LinearLayout + ImageView + TextView
 * 就能够直接实现文字带图片的功能，关键还能设置icon大小
 * 3. RoundTextView 能够直接设置各种圆角效果： 四周圆角，某一方向圆角，甚至椭圆，圆形都简单实现。 边框效果，虚线边框都是一个属性搞定
 * 4. RoundTextView 不仅能够定义默认状态的背景，边框，连按下/点击状态通通一起搞定
 * 5. RoundTextView 按下变色支持：背景色，边框，文字，drawableLeft/xxx （这个赞啊）
 * <p>
 * MyLibrary
 * Package chingtech.library.widget
 * Description:
 * Created by 师春雷
 * Created at 17/9/5 下午1:30
 */
public class RoundTextView extends AppCompatTextView {

    //default value
    public static final int ICON_DIR_LEFT   = 1;
    public static final int ICON_DIR_TOP    = 2;
    public static final int ICON_DIR_RIGHT  = 3;
    public static final int ICON_DIR_BOTTOM = 4;

    //icon
    private int mIconHeight;
    private int mIconWidth;
    private int mIconDirection;

    //corner
    private float mCornerRadius;
    private float mCornerRadiusTopLeft;
    private float mCornerRadiusTopRight;
    private float mCornerRadiusBottomLeft;
    private float mCornerRadiusBottomRight;

    //BorderWidth
    private float mBorderDashWidth = 0;
    private float mBorderDashGap   = 0;
    private int mBorderWidthNormal;
    private int mBorderWidthPressed;
    private int mBorderWidthUnable;

    //BorderColor
    private int mBorderColorNormal;
    private int mBorderColorPressed;
    private int mBorderColorUnable;

    //Background
    private int mBackgroundColorNormal;
    private int mBackgroundColorPressed;
    private int mBackgroundColorUnable;

    private GradientDrawable mBackgroundNormal;
    private GradientDrawable mBackgroundPressed;
    private GradientDrawable mBackgroundUnable;

    // Text
    private int mTextColorNormal;
    private int mTextColorPressed;
    private int mTextColorUnable;

    private ColorStateList mTextColorStateList;

    //Icon
    private Drawable mIcon = null;
    private Drawable mIconNormal;
    private Drawable mIconPressed;
    private Drawable mIconUnable;

    private int[][] states = new int[4][];
    private StateListDrawable mStateBackground;
    private float mBorderRadii[] = new float[8];

    /**
     * Cache the touch slop from the context that created the view.
     */
    private int mTouchSlop;

    public RoundTextView(Context context) {
        this(context, null);
    }

    public RoundTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        initAttributeSet(context, attrs);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            if (mIconNormal != null) {
                mIcon = mIconNormal;
                setIcon();
            }
        } else {
            if (mIconUnable != null) {
                mIcon = mIconUnable;
                setIcon();
            }
        }
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN://按下
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mIconPressed != null) {
                            mIcon = mIconPressed;
                            setIcon();
                        }
                    }
                }, ViewConfiguration.getTapTimeout());
                break;
            case MotionEvent.ACTION_UP://抬起
                if (mIconNormal != null) {
                    mIcon = mIconNormal;
                    setIcon();
                }
                break;
            case MotionEvent.ACTION_MOVE://移动
                int x = (int) event.getX();
                int y = (int) event.getY();
                if (isOutsideView(x, y)) {
                    if (mIconNormal != null) {
                        mIcon = mIconNormal;
                        setIcon();
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL://父级控件获取控制权
                if (mIconNormal != null) {
                    mIcon = mIconNormal;
                    setIcon();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 初始化控件属性
     *
     * @param context
     * @param attrs
     */
    private void initAttributeSet(Context context, AttributeSet attrs) {
        if (context == null || attrs == null) {
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundTextView);
        //corner
        mCornerRadius = a.getDimensionPixelSize(R.styleable.RoundTextView_corner_radius_all, -1);
        mCornerRadiusTopLeft = a.getDimensionPixelSize(
                R.styleable.RoundTextView_corner_radius_top_left, 0);
        mCornerRadiusTopRight = a.getDimensionPixelSize(
                R.styleable.RoundTextView_corner_radius_top_right, 0);
        mCornerRadiusBottomLeft = a.getDimensionPixelSize(
                R.styleable.RoundTextView_corner_radius_bottom_left, 0);
        mCornerRadiusBottomRight = a.getDimensionPixelSize(
                R.styleable.RoundTextView_corner_radius_bottom_right, 0);
        //border
        mBorderDashWidth = a.getDimensionPixelSize(R.styleable.RoundTextView_border_dash_width, 0);
        mBorderDashGap = a.getDimensionPixelSize(R.styleable.RoundTextView_border_dash_gap, 0);
        mBorderWidthNormal = a.getDimensionPixelSize(R.styleable.RoundTextView_border_width_normal,
                                                     0);
        mBorderWidthPressed = a.getDimensionPixelSize(
                R.styleable.RoundTextView_border_width_pressed, 0);
        mBorderWidthUnable = a.getDimensionPixelSize(R.styleable.RoundTextView_border_width_unable,
                                                     0);
        mBorderColorNormal = a.getColor(R.styleable.RoundTextView_border_color_normal,
                                        Color.TRANSPARENT);
        mBorderColorPressed = a.getColor(R.styleable.RoundTextView_border_color_pressed,
                                         Color.TRANSPARENT);
        mBorderColorUnable = a.getColor(R.styleable.RoundTextView_border_color_unable,
                                        Color.TRANSPARENT);
        //icon
        mIconNormal = a.getDrawable(R.styleable.RoundTextView_icon_src_normal);
        mIconPressed = a.getDrawable(R.styleable.RoundTextView_icon_src_pressed);
        mIconUnable = a.getDrawable(R.styleable.RoundTextView_icon_src_unable);
        mIconWidth = a.getDimensionPixelSize(R.styleable.RoundTextView_icon_width, 0);
        mIconHeight = a.getDimensionPixelSize(R.styleable.RoundTextView_icon_height, 0);
        mIconDirection = a.getInt(R.styleable.RoundTextView_icon_direction, ICON_DIR_LEFT);
        //text
        mTextColorNormal = a.getColor(R.styleable.RoundTextView_text_color_normal,
                                      getCurrentTextColor());
        mTextColorPressed = a.getColor(R.styleable.RoundTextView_text_color_pressed,
                                       getCurrentTextColor());
        mTextColorUnable = a.getColor(R.styleable.RoundTextView_text_color_unable,
                                      getCurrentTextColor());

        //background
        mBackgroundColorNormal = a.getColor(R.styleable.RoundTextView_background_normal, 0);
        mBackgroundColorPressed = a.getColor(R.styleable.RoundTextView_background_pressed, 0);
        mBackgroundColorUnable = a.getColor(R.styleable.RoundTextView_background_unable, 0);

        mBackgroundNormal = new GradientDrawable();
        mBackgroundPressed = new GradientDrawable();
        mBackgroundUnable = new GradientDrawable();

        mBackgroundNormal.setColor(mBackgroundColorNormal);
        mBackgroundPressed.setColor(mBackgroundColorPressed);
        mBackgroundUnable.setColor(mBackgroundColorUnable);

        a.recycle();

        setup();
    }

    /**
     * 设置
     */
    private void setup() {

        Drawable drawable = getBackground();
        if (drawable != null && drawable instanceof StateListDrawable) {
            mStateBackground = (StateListDrawable) drawable;
        } else {
            mStateBackground = new StateListDrawable();
        }

        //pressed, focused, normal, unable
        states[0] = new int[] {android.R.attr.state_enabled, android.R.attr.state_pressed};
        states[1] = new int[] {android.R.attr.state_enabled, android.R.attr.state_focused};
        states[3] = new int[] {-android.R.attr.state_enabled};
        states[2] = new int[] {android.R.attr.state_enabled};
        mStateBackground.addState(states[0], mBackgroundPressed);
        mStateBackground.addState(states[1], mBackgroundPressed);
        mStateBackground.addState(states[3], mBackgroundUnable);
        mStateBackground.addState(states[2], mBackgroundNormal);

        if (isEnabled() == false) {
            mIcon = mIconUnable;
        } else {
            mIcon = mIconNormal;
        }

        /**
         * 赋值为默认值
         */
        if (mBorderWidthPressed == 0) {
            mBorderWidthPressed = mBorderWidthNormal;
        }
        if (mBorderWidthUnable == 0) {
            mBorderWidthUnable = mBorderWidthNormal;
        }
        if (mBorderColorPressed == 0) {
            mBorderColorPressed = mBorderColorNormal;
        }
        if (mBorderColorUnable == 0) {
            mBorderColorUnable = mBorderColorNormal;
        }

        if (mBackgroundColorNormal == 0
                && mBackgroundColorUnable == 0
                && mBackgroundColorPressed == 0) {
            if (mBorderColorPressed == 0 && mBorderColorUnable == 0 && mBorderColorNormal == 0) {
                setBackground(getBackground());
            } else {
                setBackground(mStateBackground);
            }
        } else {
            //设置背景资源
            setBackground(mStateBackground);
        }

        //设置文本颜色
        setTextColor();

        //设置边框
        setBorder();

        //设置ICON
        setIcon();

        //设置圆角
        setRadius();
    }

    /**
     * 是否移出view
     *
     * @param x
     * @param y
     * @return
     */
    private boolean isOutsideView(int x, int y) {
        boolean flag = false;
        // Be lenient about moving outside of buttons
        if ((x < 0 - mTouchSlop) || (x >= getWidth() + mTouchSlop) || (y < 0 - mTouchSlop) || (y
                >= getHeight() + mTouchSlop)) {
            // Outside button
            flag = true;
        }
        return flag;
    }

    /************************
     * Icon
     ************************/

    public RoundTextView setIconNormal(Drawable icon) {
        this.mIconNormal = icon;
        this.mIcon = icon;
        setIcon();
        return this;
    }

    public Drawable getIconNormal() {
        return mIconNormal;
    }

    public RoundTextView setIconPressed(Drawable icon) {
        this.mIconPressed = icon;
        this.mIcon = icon;
        setIcon();
        return this;
    }

    public Drawable getIconPressed() {
        return mIconPressed;
    }

    public RoundTextView setIconUnable(Drawable icon) {
        this.mIconUnable = icon;
        this.mIcon = icon;
        setIcon();
        return this;
    }

    public Drawable getIconUnable() {
        return mIconUnable;
    }

    public RoundTextView setIconSize(int iconWidth, int iconHeight) {
        this.mIconWidth = iconWidth;
        this.mIconHeight = iconHeight;
        setIcon();
        return this;
    }

    public RoundTextView setIconWidth(int iconWidth) {
        this.mIconWidth = iconWidth;
        setIcon();
        return this;
    }

    public int getIconWidth() {
        return mIconWidth;
    }

    public RoundTextView setIconHeight(int iconHeight) {
        this.mIconHeight = iconHeight;
        setIcon();
        return this;
    }

    public int getIconHeight() {
        return mIconHeight;
    }

    public RoundTextView setIconDirection(int iconDirection) {
        this.mIconDirection = iconDirection;
        setIcon();
        return this;
    }

    public int getIconDirection() {
        return mIconDirection;
    }

    private void setIcon() {
        setIcon(mIcon, mIconWidth, mIconHeight, mIconDirection);
    }

    private void setIcon(Drawable drawable, int width, int height, int direction) {
        if (drawable != null) {
            if (width != 0 && height != 0) {
                drawable.setBounds(0, 0, width, height);
            }
            switch (direction) {
                case ICON_DIR_LEFT:
                    setCompoundDrawables(drawable, null, null, null);
                    break;
                case ICON_DIR_TOP:
                    setCompoundDrawables(null, drawable, null, null);
                    break;
                case ICON_DIR_RIGHT:
                    setCompoundDrawables(null, null, drawable, null);
                    break;
                case ICON_DIR_BOTTOM:
                    setCompoundDrawables(null, null, null, drawable);
                    break;
            }
        }
    }

    /************************
     * text color
     ************************/

    public RoundTextView setTextColorNormal(int textColor) {
        this.mTextColorNormal = textColor;
        setTextColor();
        return this;
    }

    public int getTextColorNormal() {
        return mTextColorNormal;
    }

    public RoundTextView setPressedTextColor(int textColor) {
        this.mTextColorPressed = textColor;
        setTextColor();
        return this;
    }

    public int getPressedTextColor() {
        return mTextColorPressed;
    }

    public RoundTextView setTextColorUnable(int textColor) {
        this.mTextColorUnable = textColor;
        setTextColor();
        return this;
    }

    public int getTextColorUnable() {
        return mTextColorUnable;
    }

    public void setTextColor(int normal, int pressed, int unable) {
        this.mTextColorNormal = normal;
        this.mTextColorPressed = pressed;
        this.mTextColorUnable = unable;
        setTextColor();
    }

    private void setTextColor() {
        int[] colors = new int[] {mTextColorPressed, mTextColorPressed, mTextColorNormal,
                mTextColorUnable};
        mTextColorStateList = new ColorStateList(states, colors);
        setTextColor(mTextColorStateList);
    }

    /*********************
     * border
     *********************/

    public RoundTextView setBorderWidthNormal(int width) {
        this.mBorderWidthNormal = width;
        setBorder(mBackgroundNormal, mBorderColorNormal, mBorderWidthNormal);
        return this;
    }

    public int getBorderWidthNormal() {
        return mBorderWidthNormal;
    }

    public RoundTextView setBorderColorNormal(int color) {
        this.mBorderColorNormal = color;
        setBorder(mBackgroundNormal, mBorderColorNormal, mBorderWidthNormal);
        return this;
    }

    public int getBorderColorNormal() {
        return mBorderColorNormal;
    }

    public RoundTextView setBorderWidthPressed(int width) {
        this.mBorderWidthPressed = width;
        setBorder(mBackgroundPressed, mBorderColorPressed, mBorderWidthPressed);
        return this;
    }

    public int getBorderWidthPressed() {
        return mBorderWidthPressed;
    }

    public RoundTextView setBorderColorPressed(int color) {
        this.mBorderColorPressed = color;
        setBorder(mBackgroundPressed, mBorderColorPressed, mBorderWidthPressed);
        return this;
    }

    public int getBorderColorPressed() {
        return mBorderColorPressed;
    }

    public RoundTextView setBorderWidthUnable(int width) {
        this.mBorderWidthUnable = width;
        setBorder(mBackgroundUnable, mBorderColorUnable, mBorderWidthUnable);
        return this;
    }

    public int getBorderWidthUnable() {
        return mBorderWidthUnable;
    }

    public RoundTextView setBorderColorUnable(int color) {
        this.mBorderColorUnable = color;
        setBorder(mBackgroundUnable, mBorderColorUnable, mBorderWidthUnable);
        return this;
    }

    public int getBorderColorUnable() {
        return mBorderColorUnable;
    }

    public void setBorderWidth(int normal, int pressed, int unable) {
        this.mBorderWidthNormal = normal;
        this.mBorderWidthPressed = pressed;
        this.mBorderWidthUnable = unable;
        setBorder();
    }

    public void setBorderColor(int normal, int pressed, int unable) {
        this.mBorderColorNormal = normal;
        this.mBorderColorPressed = pressed;
        this.mBorderColorUnable = unable;
        setBorder();
    }

    public void setBorderDashWidth(float dashWidth) {
        this.mBorderDashWidth = dashWidth;
        setBorder();
    }

    public float getBorderDashWidth() {
        return mBorderDashWidth;
    }

    public void setBorderDashGap(float dashGap) {
        this.mBorderDashGap = dashGap;
        setBorder();
    }

    public float getBorderDashGap() {
        return mBorderDashGap;
    }

    public void setBorderDash(float dashWidth, float dashGap) {
        this.mBorderDashWidth = dashWidth;
        this.mBorderDashGap = dashGap;
        setBorder();
    }

    private void setBorder() {
        setBorder(mBackgroundNormal, mBorderColorNormal, mBorderWidthNormal);
        setBorder(mBackgroundPressed, mBorderColorPressed, mBorderWidthPressed);
        setBorder(mBackgroundUnable, mBorderColorUnable, mBorderWidthUnable);
    }

    private void setBorder(GradientDrawable background, int borderColor, int borderWidth) {
        background.setStroke(borderWidth, borderColor, mBorderDashWidth, mBorderDashGap);
    }

    /*********************
     * radius
     ********************/

    public void setCornerRadius(float radius) {
        this.mCornerRadius = radius;
        setRadius();
    }

    public float getCornerRadius() {
        return mCornerRadius;
    }

    public RoundTextView setCornerRadiusTopLeft(float topLeft) {
        this.mCornerRadius = -1;
        this.mCornerRadiusTopLeft = topLeft;
        return this;
    }

    public float getCornerRadiusTopLeft() {
        return mCornerRadiusTopLeft;
    }

    public RoundTextView setCornerRadiusTopRight(float topRight) {
        this.mCornerRadius = -1;
        this.mCornerRadiusTopRight = topRight;
        return this;
    }

    public float getCornerRadiusTopRight() {
        return mCornerRadiusTopRight;
    }

    public RoundTextView setCornerRadiusBottomRight(float bottomRight) {
        this.mCornerRadius = -1;
        this.mCornerRadiusBottomRight = bottomRight;
        return this;
    }

    public float getCornerRadiusBottomRight() {
        return mCornerRadiusBottomRight;
    }

    public RoundTextView setCornerRadiusBottomLeft(float bottomLeft) {
        this.mCornerRadius = -1;
        this.mCornerRadiusBottomLeft = bottomLeft;
        return this;
    }

    public float setCornerRadiusBottomLeft() {
        return mCornerRadiusBottomLeft;
    }

    public void setCornerRadius(float topLeft, float topRight, float bottomRight,
            float bottomLeft) {
        this.mCornerRadius = -1;
        this.mCornerRadiusTopLeft = topLeft;
        this.mCornerRadiusTopRight = topRight;
        this.mCornerRadiusBottomRight = bottomRight;
        this.mCornerRadiusBottomLeft = bottomLeft;
        setRadius();
    }

    private void setRadiusRadii() {
        mBackgroundNormal.setCornerRadii(mBorderRadii);
        mBackgroundPressed.setCornerRadii(mBorderRadii);
        mBackgroundUnable.setCornerRadii(mBorderRadii);
    }

    private void setRadius() {
        if (mCornerRadius >= 0) {
            mBorderRadii[0] = mCornerRadius;
            mBorderRadii[1] = mCornerRadius;
            mBorderRadii[2] = mCornerRadius;
            mBorderRadii[3] = mCornerRadius;
            mBorderRadii[4] = mCornerRadius;
            mBorderRadii[5] = mCornerRadius;
            mBorderRadii[6] = mCornerRadius;
            mBorderRadii[7] = mCornerRadius;
            setRadiusRadii();
            return;
        }

        if (mCornerRadius < 0) {
            mBorderRadii[0] = mCornerRadiusTopLeft;
            mBorderRadii[1] = mCornerRadiusTopLeft;
            mBorderRadii[2] = mCornerRadiusTopRight;
            mBorderRadii[3] = mCornerRadiusTopRight;
            mBorderRadii[4] = mCornerRadiusBottomRight;
            mBorderRadii[5] = mCornerRadiusBottomRight;
            mBorderRadii[6] = mCornerRadiusBottomLeft;
            mBorderRadii[7] = mCornerRadiusBottomLeft;
            setRadiusRadii();
            return;
        }
    }
}
