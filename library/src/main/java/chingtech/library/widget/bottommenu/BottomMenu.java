package chingtech.library.widget.bottommenu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import chingtech.library.R;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static chingtech.library.utils.ScreenUtils.dp2px;

/**
 * 弹出菜单
 * Created by Joe on 16/12/10
 */
public class BottomMenu {

    /**
     * 默认的列数为4个
     */
    private static final int DEFAULT_COLUMN_COUNT = 3;

    /**
     * 动画时间
     */
    private static final int DEFAULT_DURATION = 300;

    /**
     * 拉力系数
     */
    private static final int DEFAULT_TENSION  = 10;
    /**
     * 摩擦力系数
     */
    private static final int DEFAULT_FRICTION = 5;

    /**
     * item水平之间的间距
     */
    private static final int DEFAULT_HORIZONTAL_PADDING = 40;
    /**
     * item竖直之间的间距
     */
    private static final int DEFAULT_VERTICAL_PADDING   = 15;

    /**
     * 文字大小
     */
    private int textsize = -1;

    /**
     * 文字颜色 资源R.Color.颜色值
     */
    private int textcolor = -1;

    public static int getDefaultColumnCount() {
        return DEFAULT_COLUMN_COUNT;
    }

    public int getTextsize() {
        return textsize;
    }

    public void setTextsize(int textsize) {
        this.textsize = textsize;
    }

    public int getTextcolor() {
        return textcolor;
    }

    public void setTextcolor(int textcolor) {
        this.textcolor = textcolor;
    }

    private Activity mActivity;
    private int      mColumnCount;
    private List<BottomMenuItem> mBottomMenuItems = new ArrayList<>();
    private RelativeLayout         mAnimateLayout;
    private GridLayout             mGridLayout;
    private ImageView              mCloseIv;
    private int                    mDuration;
    private double                 mTension;
    private double                 mFriction;
    private int                    mHorizontalPadding;
    private int                    mVerticalPadding;
    private BottomMenuItemListener mBottomMenuItemListener;
    private boolean isclosevisible = true;

    public boolean isclosevisible() {
        return isclosevisible;
    }

    public void setIsclosevisible(boolean isclosevisible) {
        this.isclosevisible = isclosevisible;
    }

    private int mScreenWidth;
    private int mScreenHeight;

    /**
     * 返回相应的menuitem
     *
     * @param i
     * @return
     */
    public MenuSubView getMenuItem(int i) {
        MenuSubView subView = null;
        try {
            subView = (MenuSubView) mGridLayout.getChildAt(i);
        } catch (Throwable e) {
        }

        if (subView != null) {
            return subView;
        } else {
            return null;
        }
    }

    public int getmBackGroundColor() {
        return mBackGroundColor;
    }

    public void setmBackGroundColor(int mBackGroundColor) {
        this.mBackGroundColor = mBackGroundColor;
    }

    /**
     * 透明背景
     */
    public void setmBackGroundTrasnparent() {
        this.mBackGroundColor = Color.parseColor("#00ffffff");
    }


    public int getmCloseButtomResourceid() {
        return mCloseButtomResourceid;
    }

    public void setmCloseButtomResourceid(int mCloseButtomResourceid) {
        this.mCloseButtomResourceid = mCloseButtomResourceid;
    }

    public int getCloseMenuMarginbottom() {
        return mCloseMenuMarginbottom;
    }

    public void setCloseMenuMarginbottom(int mCloseMenuMarginbottom) {
        this.mCloseMenuMarginbottom = mCloseMenuMarginbottom;
    }

    /**
     * 关闭按钮距离屏幕底部位置单位dp
     */
    private int mCloseMenuMarginbottom = 15;

    /**
     * 背景颜色
     */
    private int mBackGroundColor = Color.parseColor("#f0f3f3f3");

    /**
     * 关闭按钮的图片
     */
    private int mCloseButtomResourceid = R.drawable.ic_action_navigation_close;

    /**
     * Menu相对于屏幕顶部的距离（去掉菜单本身高度剩下部分除以这个倍数因子）
     */

    private float mMarginTopRemainSpace = 1.5f;

    /**
     * 是否错位弹出菜单
     */
    private boolean mIsmalpositionAnimatOut = true;

    /**
     * 错位动画时间（毫秒）默认50
     */
    private int malposition = 50;

    private boolean isShowing = false;

    public float getmMarginTopRemainSpace() {
        return mMarginTopRemainSpace;
    }

    public void setmMarginTopRemainSpace(float mMarginTopRemainSpace) {
        this.mMarginTopRemainSpace = mMarginTopRemainSpace;
    }

    public boolean ismIsmalpositionAnimatOut() {
        return mIsmalpositionAnimatOut;
    }

    public void setmIsmalpositionAnimatOut(boolean mIsmalpositionAnimatOut) {
        this.mIsmalpositionAnimatOut = mIsmalpositionAnimatOut;
    }

    public int getMalposition() {
        return malposition;
    }

    public void setMalposition(int malposition) {
        this.malposition = malposition;
    }

    private SpringSystem mSpringSystem;

    {
        mSpringSystem = SpringSystem.create();
    }

    private BottomMenu(Builder builder) {
        this.mActivity = builder.activity;
        this.mBottomMenuItems.clear();
        this.mBottomMenuItems.addAll(builder.itemList);

        this.mColumnCount = builder.columnCount;
        this.mDuration = builder.duration;
        this.mTension = builder.tension;
        this.mFriction = builder.friction;
        this.mHorizontalPadding = builder.horizontalPadding;
        this.mVerticalPadding = builder.verticalPadding;
        this.mBottomMenuItemListener = builder.mBottomMenuItemListener;

        mScreenWidth = mActivity.getResources().getDisplayMetrics().widthPixels;
        mScreenHeight = mActivity.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 显示菜单
     */
    public void show() {
        buildAnimateGridLayout();

        if (mAnimateLayout.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) mAnimateLayout.getParent();
            viewGroup.removeView(mAnimateLayout);
        }

        ViewGroup decorView = (ViewGroup) mActivity.getWindow().getDecorView();
        decorView.addView(mAnimateLayout);

        // decorView.setPadding(0,0,0,getNavigationBarHeight(mActivity));
        ViewGroup.MarginLayoutParams lp
                = (ViewGroup.MarginLayoutParams) mAnimateLayout.getLayoutParams();
        lp.setMargins(0, 0, 0, getNavigationBarHeight(mActivity));
        mAnimateLayout.setLayoutParams(lp);

        //执行显示动画
        showSubMenus(mGridLayout);

        isShowing = true;
    }

    /**
     * 隐藏菜单
     */
    public void hide() {
        //先执行消失的动画
        if (isShowing && mGridLayout != null) {
            hideSubMenus(mGridLayout, new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    ViewGroup decorView = (ViewGroup) mActivity.getWindow().getDecorView();
                    decorView.removeView(mAnimateLayout);
                }
            });
            isShowing = false;
        }
    }

    public boolean isShowing() {
        return isShowing;
    }

    /**
     * 构建动画布局
     */
    private void buildAnimateGridLayout() {
        mAnimateLayout = new RelativeLayout(mActivity);

        mAnimateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });

        mGridLayout = new GridLayout(mActivity);
        mGridLayout.setColumnCount(mColumnCount);
        mGridLayout.setBackgroundColor(mBackGroundColor);
        int hPadding  = (int) dp2px(mHorizontalPadding);
        int vPadding  = (int) dp2px(mVerticalPadding);
        int itemWidth = (mScreenWidth - (mColumnCount + 1) * hPadding) / mColumnCount;

        int rowCount = mBottomMenuItems.size() % mColumnCount == 0 ? mBottomMenuItems.size() / mColumnCount :
                mBottomMenuItems.size() / mColumnCount + 1;

        int topMargin = (int) ((mScreenHeight - (itemWidth + vPadding) * rowCount + vPadding)
                / mMarginTopRemainSpace);

        for (int i = 0; i < mBottomMenuItems.size(); i++) {
            final int   position = i;
            MenuSubView subView  = new MenuSubView(mActivity);
            if (textcolor != -1) {
                subView.getTextView().setTextColor(mActivity.getResources().getColor(textcolor));

            }
            if (textsize != -1) {
                subView.getTextView().setTextSize(textsize);

            }
            BottomMenuItem bottomMenuItem = mBottomMenuItems.get(i);
            subView.setPopMenuItem(bottomMenuItem);
            subView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mBottomMenuItemListener != null) {
                        mBottomMenuItemListener.onItemClick(BottomMenu.this, position);
                    }
                    hide();
                }
            });

            GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
            lp.width = itemWidth;
            lp.leftMargin = hPadding;

            if (i / mColumnCount == 0) {
                lp.topMargin = topMargin;
            } else {
                lp.topMargin = vPadding;
            }
            mGridLayout.addView(subView, lp);
        }

        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams2.addRule(RelativeLayout.CENTER_HORIZONTAL);

        mAnimateLayout.addView(mGridLayout, layoutParams2);

        mCloseIv = new ImageView(mActivity);
        mCloseIv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mCloseIv.setImageResource(mCloseButtomResourceid);
        mCloseIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });
        if (isclosevisible) {
            mCloseIv.setVisibility(View.VISIBLE);
        } else {
            mCloseIv.setVisibility(View.GONE);
        }

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.bottomMargin = (int) dp2px(mCloseMenuMarginbottom);
        mAnimateLayout.addView(mCloseIv, layoutParams);
    }

    /**
     * show sub menus with animates
     *
     * @param viewGroup
     */
    private void showSubMenus(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return;
        }
        int childCount = viewGroup.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View view = viewGroup.getChildAt(i);
            view.setVisibility(View.INVISIBLE);

            animationAction(i, view);
        }
    }

    /**
     * 动画动作
     *
     * @param i
     * @param view
     */
    private void animationAction(int i, final View view) {

        if (mIsmalpositionAnimatOut) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    view.setVisibility(View.VISIBLE);
                    animateViewDirection(view, mScreenHeight, 0, mTension, mFriction);


                }
            }, i * malposition);
        } else {

            view.setVisibility(View.VISIBLE);
            animateViewDirection(view, mScreenHeight, 0, mTension, mFriction);

        }
    }

    /**
     * hide sub menus with animates
     *
     * @param viewGroup
     * @param listener
     */
    private void hideSubMenus(ViewGroup viewGroup, final AnimatorListenerAdapter listener) {
        if (viewGroup == null) {
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = viewGroup.getChildAt(i);
            view.animate()
                .translationY(mScreenHeight)
                .setDuration(mDuration)
                .setListener(listener)
                .start();
        }
    }

    /**
     * 弹簧动画
     *
     * @param v        动画View
     * @param from
     * @param to
     * @param tension  拉力系数
     * @param friction 摩擦力系数
     */
    private void animateViewDirection(final View v, float from, float to, double tension,
            double friction) {
        Spring spring = mSpringSystem.createSpring();
        spring.setCurrentValue(from);
        spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(tension, friction));
        spring.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                v.setTranslationY((float) spring.getCurrentValue());
            }
        });
        spring.setEndValue(to);
    }

    public static class Builder {

        private Activity activity;
        private int                  columnCount       = DEFAULT_COLUMN_COUNT;
        private List<BottomMenuItem> itemList          = new ArrayList<>();
        private int                  duration          = DEFAULT_DURATION;
        private double               tension           = DEFAULT_TENSION;
        private double               friction          = DEFAULT_FRICTION;
        private int                  horizontalPadding = DEFAULT_HORIZONTAL_PADDING;
        private int                  verticalPadding   = DEFAULT_VERTICAL_PADDING;
        private BottomMenuItemListener mBottomMenuItemListener;

        public Builder attachToActivity(Activity activity) {
            this.activity = activity;
            return this;
        }

        public Builder columnCount(int count) {
            this.columnCount = count;
            return this;
        }

        public Builder addMenuItem(BottomMenuItem bottomMenuItem) {
            this.itemList.add(bottomMenuItem);
            return this;
        }

        public Builder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder tension(double tension) {
            this.tension = tension;
            return this;
        }

        public Builder friction(double friction) {
            this.friction = friction;
            return this;
        }

        public Builder horizontalPadding(int padding) {
            this.horizontalPadding = padding;
            return this;
        }

        public Builder verticalPadding(int padding) {
            this.verticalPadding = padding;
            return this;
        }

        public Builder setOnItemClickListener(BottomMenuItemListener listener) {
            this.mBottomMenuItemListener = listener;
            return this;
        }

        public BottomMenu build() {
            final BottomMenu bottomMenu = new BottomMenu(this);
            return bottomMenu;
        }
    }

    public boolean checkDeviceHasNavigationBar(Context context) {
        WindowManager windowManager = ((Activity) context).getWindowManager();

        DisplayMetrics dm      = new DisplayMetrics();
        Display        display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);

        int            screenHeight       = dm.heightPixels;
        DisplayMetrics realDisplayMetrics = new DisplayMetrics();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            display.getRealMetrics(realDisplayMetrics);
        } else {
            Class c;
            try {
                c = Class.forName("android.view.Display");
                Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
                method.invoke(display, realDisplayMetrics);
            } catch (Exception e) {
                realDisplayMetrics.setToDefaults();
                e.printStackTrace();
            }
        }

        int screenRealHeight = realDisplayMetrics.heightPixels;

        return (screenRealHeight - screenHeight) > 0;//screenRealHeight上面方法中有计算
    }

    private int getNavigationBarHeight(Context context) {
        int       navigationBarHeight = 0;
        Resources rs                  = context.getResources();
        int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
        if (id > 0 && checkDeviceHasNavigationBar(context)) {
            navigationBarHeight = rs.getDimensionPixelSize(id);
        }
        return navigationBarHeight;
    }

    public interface BottomMenuItemListener {
        /**
         * Item点击事件
         *
         * @param bottomMenu
         * @param position
         */
        void onItemClick(BottomMenu bottomMenu, int position);
    }
}
