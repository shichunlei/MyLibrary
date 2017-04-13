package chingtech.library.adapter.base.abslistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import chingtech.library.adapter.base.helper.ViewHelper;
import chingtech.library.utils.ENUM;
import chingtech.library.utils.ViewUtils;
import chingtech.library.widget.SmoothCheckBox;

public class BaseAbsListHolder implements ViewHelper.AbsListView<BaseAbsListHolder> {

    /**
     * findViewById后保存View集合
     */
    private SparseArray<View> mViews = new SparseArray<>();
    private SparseArray<View> mConvertViews = new SparseArray<>();

    private View mConvertView;
    protected int mPosition;
    protected int mLayoutId;
    protected Context mContext;

    protected BaseAbsListHolder(Context context, int position, ViewGroup parent, int layoutId) {
        this.mConvertView = mConvertViews.get(layoutId);
        this.mPosition = position;
        this.mContext = context;
        this.mLayoutId = layoutId;
        if (mConvertView == null) {
            mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            mConvertViews.put(layoutId, mConvertView);
            mConvertView.setTag(this);
        }
    }

    protected BaseAbsListHolder() {
    }

    public <BVH extends BaseAbsListHolder> BVH get(Context context, int position, View convertView, ViewGroup parent, int layoutId) {
        if (convertView == null) {
            return (BVH) new BaseAbsListHolder(context, position, parent, layoutId);
        } else {
            BaseAbsListHolder holder = (BaseAbsListHolder) convertView.getTag();
            if (holder.mLayoutId != layoutId) {
                return (BVH) new BaseAbsListHolder(context, position, parent, layoutId);
            }
            holder.setPosition(position);
            return (BVH) holder;
        }
    }

    /**
     * 获取item布局
     * @return
     */
    public View getConvertView() {
        return mConvertViews.valueAt(0);
    }

    public View getConvertView(int layoutId) {
        return mConvertViews.get(layoutId);
    }

    public <V extends View> V getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (V) view;
    }

    public void setPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    public int getLayoutId() {
        return mLayoutId;
    }

    @Override
    public BaseAbsListHolder setText(int viewId, String value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    @Override
    public BaseAbsListHolder setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setTextColor(color);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public BaseAbsListHolder setTextColorRes(int viewId, int colorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(colorRes, null));
        return this;
    }

    @Override
    public BaseAbsListHolder setImageResource(int viewId, int imgResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imgResId);
        return this;
    }

    @Override
    public BaseAbsListHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    @Override
    public BaseAbsListHolder setBackgroundColorRes(int viewId, int colorRes) {
        View view = getView(viewId);
        view.setBackgroundResource(colorRes);
        return this;
    }

    @Override
    public BaseAbsListHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public BaseAbsListHolder setImageDrawableRes(int viewId, int drawableRes) {
        Drawable drawable = mContext.getResources().getDrawable(drawableRes, null);
        return setImageDrawable(viewId, drawable);
    }

    @Override
    public BaseAbsListHolder setImageUrl(int viewId, String imgUrl) {
        ImageView imageview = getView(viewId);
        Glide.with(mContext).load(imgUrl).centerCrop().crossFade().into(imageview);
        return this;
    }

    @Override
    public BaseAbsListHolder setImageUrl(int viewId, String imageUrl, int defaultDrawable) {
        ImageView view = getView(viewId);
        Glide.with(mContext).load(imageUrl).centerCrop().crossFade().error(defaultDrawable).placeholder(defaultDrawable).into(view);
        return this;
    }

    @Override
    public BaseAbsListHolder setImageBitmap(int viewId, Bitmap imgBitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(imgBitmap);
        return this;
    }

    @Override
    public BaseAbsListHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    @Override
    public BaseAbsListHolder setVisible(int viewId, int visible) {
        View view = getView(viewId);
        view.setVisibility(visible);
        return this;
    }

    @Override
    public BaseAbsListHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    @Override
    public BaseAbsListHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    @Override
    public BaseAbsListHolder setChecked(int viewId, boolean checked) {
        Checkable view = getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 设置SmoothCheckBox控件的选择情况
     *
     * @param viewId  viewId
     * @param checked 选择
     * @param animate 动画
     * @return viewHolder
     */
    @Override
    public BaseAbsListHolder setChecked(int viewId, boolean checked, boolean animate) {
        SmoothCheckBox view = getView(viewId);
        view.setChecked(checked, animate);
        return this;
    }

    @Override
    public BaseAbsListHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    @Override
    public BaseAbsListHolder setTypeface(int viewId, Typeface typeface) {
        TextView view = getView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    @Override
    public BaseAbsListHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    /**
     * Sets the adapter of a adapter view.
     */
    @Override
    public BaseAbsListHolder setAdapter(@IdRes int viewId, @NonNull Adapter adapter) {
        AdapterView view = getView(viewId);
        view.setAdapter(adapter);
        return this;
    }

    /**
     * Sets the progress and max of a ProgressBar.
     *
     * @param viewId   The view id.
     * @param drawable The drawable.
     */
    @Override
    public BaseAbsListHolder setProgressDrawable(@IdRes int viewId, Drawable drawable) {
        ProgressBar view = getView(viewId);
        view.setProgressDrawable(drawable);
        return this;
    }

    /**
     * Sets the progress and max of a ProgressBar.
     *
     * @param viewId   The view id.
     * @param progress The progress.
     * @param max      The max value of a ProgressBar.
     */
    @Override
    public BaseAbsListHolder setProgress(@IdRes int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the progress of a ProgressBar.
     *
     * @param viewId   The view id.
     * @param progress The progress.
     */
    @Override
    public BaseAbsListHolder setProgress(@IdRes int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the enable of a view.
     *
     * @param viewId  The view id.
     * @param enabled The progress.
     */
    @Override
    public BaseAbsListHolder setEnabled(@IdRes int viewId, boolean enabled) {
        View view = getView(viewId);
        view.setEnabled(enabled);
        return this;
    }

    /**
     * 设置控件的高度
     *
     * @param viewId The view id.
     */
    @Override
    public BaseAbsListHolder setViewHeight(int viewId, ENUM.Proportion type) {
        View view = getView(viewId);
        ViewUtils.setViewHeight(mContext, view, type);
        return this;
    }

    /**
     * 设置控件的高度
     *
     * @param viewId The view id.
     */
    @Override
    public BaseAbsListHolder setViewHeight(int viewId, ENUM.Proportion type, float screen) {
        View view = getView(viewId);
        ViewUtils.setViewHeight(mContext, view, type, screen);
        return this;
    }

    @Override
    public BaseAbsListHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}
