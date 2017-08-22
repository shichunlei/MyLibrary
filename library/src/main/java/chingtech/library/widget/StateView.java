package chingtech.library.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ScrollingView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
 * MyLibrary
 * Package chingtech.library.widget
 * Description:
 * Created by 师春雷
 * Created at 17/8/14 上午11:35
 */
public class StateView extends View {

    private int mEmptyResource;
    private int mRetryResource;

    private View mEmptyView;
    private View mRetryView;

    private LayoutInflater       mInflater;
    private OnRetryClickListener mRetryClickListener;

    private RelativeLayout.LayoutParams mLayoutParams;

    private ProgressDialog progress = null;

    private Context context;

    /**
     * 注入到activity中
     *
     * @param activity Activity
     * @return StateView
     */
    public static StateView inject(@NonNull Activity activity) {
        return inject(activity, false);
    }

    /**
     * 注入到activity中
     *
     * @param activity     Activity
     * @param hasActionBar 是否有actionbar/toolbar
     * @return StateView
     */
    public static StateView inject(@NonNull Activity activity, boolean hasActionBar) {
        ViewGroup rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        return inject(rootView, hasActionBar);
    }

    /**
     * 注入到ViewGroup中
     *
     * @param parent extends ViewGroup
     * @return StateView
     */
    public static StateView inject(@NonNull ViewGroup parent) {
        return inject(parent, false);
    }

    /**
     * 注入到ViewGroup中
     *
     * @param parent       extends ViewGroup
     * @param hasActionBar 是否有actionbar/toolbar,
     *                     true: 会setMargin top, margin大小是actionbarSize
     *                     false: not set
     * @return StateView
     */
    public static StateView inject(@NonNull ViewGroup parent, boolean hasActionBar) {
        // 因为 LinearLayout/ScrollView/AdapterView 的特性
        // 为了 StateView 能正常显示，自动再套一层（开发的时候就不用额外的工作量了）
        // SwipeRefreshLayout/NestedScrollView
        int screenHeight = 0;
        if (parent instanceof LinearLayout
                || parent instanceof ScrollView
                || parent instanceof AdapterView
                || (parent instanceof NestedScrollingParent
                && parent instanceof NestedScrollingChild)) {
            ViewParent viewParent = parent.getParent();
            if (viewParent == null) {
                // create a new FrameLayout to wrap StateView and parent's childView
                FrameLayout wrapper = new FrameLayout(parent.getContext());
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                wrapper.setLayoutParams(layoutParams);

                if (parent instanceof LinearLayout) {
                    // create a new LinearLayout to wrap parent's childView
                    LinearLayout wrapLayout = new LinearLayout(parent.getContext());
                    wrapLayout.setLayoutParams(parent.getLayoutParams());
                    wrapLayout.setOrientation(((LinearLayout) parent).getOrientation());

                    for (int i = 0, childCount = parent.getChildCount(); i < childCount; i++) {
                        View childView = parent.getChildAt(0);
                        parent.removeView(childView);
                        wrapLayout.addView(childView);
                    }
                    wrapper.addView(wrapLayout);
                } else if (parent instanceof ScrollView || parent instanceof ScrollingView) {
                    // not recommended to inject Scrollview/NestedScrollView
                    if (parent.getChildCount() != 1) {
                        throw new IllegalStateException(
                                "the ScrollView does not have one direct child");
                    }
                    View directView = parent.getChildAt(0);
                    parent.removeView(directView);
                    wrapper.addView(directView);

                    WindowManager wm = (WindowManager) parent.getContext()
                                                             .getSystemService(
                                                                     Context.WINDOW_SERVICE);
                    DisplayMetrics metrics = new DisplayMetrics();
                    wm.getDefaultDisplay().getMetrics(metrics);
                    screenHeight = metrics.heightPixels;
                } else if (parent instanceof NestedScrollingParent
                        && parent instanceof NestedScrollingChild) {
                    if (parent.getChildCount() == 2) {
                        View targetView = parent.getChildAt(1);
                        parent.removeView(targetView);
                        wrapper.addView(targetView);
                    } else if (parent.getChildCount() > 2) {
                        throw new IllegalStateException(
                                "the view is not refresh layout? view = " + parent.toString());
                    }
                } else {
                    throw new IllegalStateException(
                            "the view does not have parent, view = " + parent.toString());
                }
                // parent add wrapper
                parent.addView(wrapper);
                // StateView will be added to wrapper
                parent = wrapper;
            } else {
                FrameLayout root = new FrameLayout(parent.getContext());
                root.setLayoutParams(parent.getLayoutParams());
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                parent.setLayoutParams(layoutParams);

                if (viewParent instanceof ViewGroup) {
                    ViewGroup rootGroup = (ViewGroup) viewParent;
                    // 把 parent 从它自己的父容器中移除
                    rootGroup.removeView(parent);
                    // 然后替换成新的
                    rootGroup.addView(root);
                }
                root.addView(parent);
                parent = root;
            }
        }
        StateView stateView = new StateView(parent.getContext());
        if (screenHeight > 0) {
            // let StateView be shown in the center
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    hasActionBar ? screenHeight - stateView.getActionBarHeight() : screenHeight);
            parent.addView(stateView, params);
        } else {
            parent.addView(stateView);
        }
        if (hasActionBar) {
            stateView.setTopMargin();
        }
        return stateView;
    }

    /**
     * 注入到View中
     *
     * @param view instanceof ViewGroup
     * @return StateView
     */
    public static StateView inject(@NonNull View view) {
        return inject(view, false);
    }

    /**
     * 注入到View中
     *
     * @param view         instanceof ViewGroup
     * @param hasActionBar 是否有actionbar/toolbar
     * @return StateView
     */
    public static StateView inject(@NonNull View view, boolean hasActionBar) {
        if (view instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) view;
            return inject(parent, hasActionBar);
        } else {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                return inject((ViewGroup) parent, hasActionBar);
            } else {
                throw new ClassCastException("view or view.getParent() must be ViewGroup");
            }
        }
    }

    public StateView(Context context) {
        this(context, null);
        this.context = context;
    }

    public StateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    public StateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StateView);
        mEmptyResource = a.getResourceId(R.styleable.StateView_emptyResource, 0);
        mRetryResource = a.getResourceId(R.styleable.StateView_retryResource, 0);
        a.recycle();

        if (mEmptyResource == 0) {
            mEmptyResource = R.layout.base_empty;
        }
        if (mRetryResource == 0) {
            mRetryResource = R.layout.base_retry;
        }

        if (attrs == null) {
            mLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                            ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            mLayoutParams = new RelativeLayout.LayoutParams(context, attrs);
        }

        setVisibility(GONE);
        setWillNotDraw(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(0, 0);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void draw(Canvas canvas) {
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
    }

    @Override
    public void setVisibility(int visibility) {
        setVisibility(mEmptyView, visibility);
        setVisibility(mRetryView, visibility);
    }

    private void setVisibility(View view, int visibility) {
        if (view != null) {
            view.setVisibility(visibility);
        }
    }

    public void showContent() {
        hidenLoading();
        setVisibility(GONE);
    }

    public View showEmpty() {
        if (mEmptyView == null) {
            mEmptyView = inflate(mEmptyResource);
        }

        showView(mEmptyView);
        return mEmptyView;
    }

    public View showRetry() {
        if (mRetryView == null) {
            mRetryView = inflate(mRetryResource);
            mRetryView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mRetryClickListener != null) {
                        showLoading();
                        mRetryView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mRetryClickListener.onRetryClick();
                            }
                        }, 200);
                    }
                }
            });
        }

        showView(mRetryView);
        return mRetryView;
    }

    public void showLoading() {
        showLoading("加载中...");
    }

    public void showLoading(String titleTxt) {
        progress = new ProgressDialog(this.context);
        progress.setTitleText(titleTxt);
        progress.show();
    }

    public void hidenLoading() {
        if (null != progress) {
            progress.dismiss();
        }
    }

    private void showView(View view) {
        hidenLoading();
        setVisibility(view, VISIBLE);
        if (mEmptyView == view) {
            setVisibility(mRetryView, GONE);
        } else if (mRetryView == view) {
            setVisibility(mEmptyView, GONE);
        }
    }

    public View inflate(@LayoutRes int layoutResource) {
        final ViewParent viewParent = getParent();

        if (viewParent != null && viewParent instanceof ViewGroup) {
            if (layoutResource != 0) {
                final ViewGroup      parent = (ViewGroup) viewParent;
                final LayoutInflater factory;
                if (mInflater != null) {
                    factory = mInflater;
                } else {
                    factory = LayoutInflater.from(getContext());
                }
                final View view = factory.inflate(layoutResource, parent, false);

                final int index = parent.indexOfChild(this);
                // 防止还能触摸底下的 View
                view.setClickable(true);

                final ViewGroup.LayoutParams layoutParams = getLayoutParams();
                if (layoutParams != null) {
                    if (parent instanceof RelativeLayout) {
                        ViewGroup.MarginLayoutParams lp
                                = (ViewGroup.MarginLayoutParams) layoutParams;
                        mLayoutParams.setMargins(lp.leftMargin, lp.topMargin, lp.rightMargin,
                                                 lp.bottomMargin);

                        parent.addView(view, index, mLayoutParams);
                    } else {
                        parent.addView(view, index, layoutParams);
                    }
                } else {
                    parent.addView(view, index);
                }

                return view;
            } else {
                throw new IllegalArgumentException("StateView must have a valid layoutResource");
            }
        } else {
            throw new IllegalStateException("StateView must have a non-null ViewGroup viewParent");
        }
    }

    /**
     * 设置topMargin, 当有actionbar/toolbar的时候
     */
    public void setTopMargin() {
        ViewGroup.MarginLayoutParams layoutParams
                = (ViewGroup.MarginLayoutParams) getLayoutParams();
        layoutParams.topMargin = getActionBarHeight();
    }

    /**
     * @return actionBarSize
     */
    public int getActionBarHeight() {
        int        height = 0;
        TypedValue tv     = new TypedValue();
        if (getContext().getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)) {
            height = TypedValue.complexToDimensionPixelSize(tv.data,
                                                            getResources().getDisplayMetrics());
        }
        return height;
    }

    /**
     * 设置emptyView的自定义Layout
     *
     * @param emptyResource emptyView的layoutResource
     */
    public void setEmptyResource(@LayoutRes int emptyResource) {
        this.mEmptyResource = emptyResource;
    }

    /**
     * 设置retryView的自定义Layout
     *
     * @param retryResource retryView的layoutResource
     */
    public void setRetryResource(@LayoutRes int retryResource) {
        this.mRetryResource = retryResource;
    }

    public LayoutInflater getInflater() {
        return mInflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.mInflater = inflater;
    }

    /**
     * 监听重试
     *
     * @param listener {@link OnRetryClickListener}
     */
    public void setOnRetryClickListener(OnRetryClickListener listener) {
        this.mRetryClickListener = listener;
    }

    public interface OnRetryClickListener {
        void onRetryClick();
    }
}
