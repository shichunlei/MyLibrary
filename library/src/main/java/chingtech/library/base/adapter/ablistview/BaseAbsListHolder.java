package chingtech.library.base.adapter.ablistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import chingtech.library.base.adapter.recyclerview.BaseRecyclerHolder;
import com.bumptech.glide.Glide;

import chingtech.library.base.adapter.helper.ViewHelper;
import chingtech.library.utils.ViewUtils;
import chingtech.library.widget.SmoothCheckBox;
import com.bumptech.glide.load.Transformation;

public class BaseAbsListHolder implements ViewHelper.AbsListView<BaseAbsListHolder> {

    /**
     * findViewById后保存View集合
     */
    private SparseArray<View> mViews        = new SparseArray<>();
    private SparseArray<View> mConvertViews = new SparseArray<>();

    private   View    mConvertView;
    protected int     mPosition;
    protected int     mLayoutId;
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

    public <BVH extends BaseAbsListHolder> BVH get(Context context, int position, View convertView,
            ViewGroup parent, int layoutId) {
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
     *
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

    /**
     * 设置textView文本内容
     *
     * @param viewId viewId
     * @param value  文本内容
     * @return viewHolder
     */
    @Override
    public BaseAbsListHolder setText(int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    @Override
    public BaseAbsListHolder setTextDeleteLine(int viewId) {
        TextView view = getView(viewId);
        view.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        return this;
    }

    @Override
    public BaseAbsListHolder setTextUnderLine(int viewId) {
        TextView view = getView(viewId);
        view.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
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
        view.setTextColor(ContextCompat.getColor(mContext, colorRes));
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
        Drawable drawable = ContextCompat.getDrawable(mContext, drawableRes);
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
        Glide.with(mContext)
             .load(imageUrl)
             .centerCrop()
             .crossFade()
             .error(defaultDrawable)
             .placeholder(defaultDrawable)
             .into(view);
        return this;
    }

    @Override
    public BaseAbsListHolder setImageUrl(int viewId, String imgUrl, int defaultDrawable,
            Transformation transformation) {
        ImageView view = getView(viewId);
        Glide.with(mContext)
             .load(imgUrl)
             .centerCrop()
             .crossFade()
             .bitmapTransform(transformation)
             .error(defaultDrawable)
             .placeholder(defaultDrawable)
             .into(view);
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

    /**
     * Sets the rating (the number of stars filled) of a RatingBar.
     *
     * @param viewId The view id.
     * @param rating The rating.
     * @return The BaseAbsListHolder for chaining.
     */
    @Override
    public BaseAbsListHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) and max of a RatingBar.
     *
     * @param viewId The view id.
     * @param rating The rating.
     * @param max    The range of the RatingBar to 0...max.
     * @return The BaseAbsListHolder for chaining.
     */
    @Override
    public BaseAbsListHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    @Override
    public BaseAbsListHolder setChecked(int viewId, boolean checked) {
        View view = getView(viewId);
        // View unable cast to Checkable
        if (view instanceof CompoundButton) {
            ((CompoundButton) view).setChecked(checked);
        } else if (view instanceof CheckedTextView) {
            ((CheckedTextView) view).setChecked(checked);
        }
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
     * Sets the progress and max of a ProgressBar.
     *
     * @param viewId The view id.
     * @param max    The max value of a ProgressBar.
     */
    @Override
    public BaseAbsListHolder setMax(@IdRes int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
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
    public BaseAbsListHolder setViewHeight(int viewId, float type) {
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
    public BaseAbsListHolder setViewHeight(int viewId, float type, float screen) {
        View view = getView(viewId);
        ViewUtils.setViewHeight(mContext, view, type, screen);
        return this;
    }

    /**
     * 设置控件的宽度
     *
     * @param viewId The view id.
     */
    @Override
    public BaseAbsListHolder setViewWidth(int viewId, float type) {
        View view = getView(viewId);
        ViewUtils.setViewWidth(mContext, view, type);
        return this;
    }

    /**
     * 设置控件的宽度
     *
     * @param viewId The view id.
     */
    @Override
    public BaseAbsListHolder setViewWidth(int viewId, float type, float screen) {
        View view = getView(viewId);
        ViewUtils.setViewWidth(mContext, view, type, screen);
        return this;
    }

    /**
     * 设置控件的大小
     *
     * @param viewId The view id.
     */
    @Override
    public BaseAbsListHolder setViewSize(int viewId, float type) {
        View view = getView(viewId);
        ViewUtils.setViewSize(mContext, view, type);
        return this;
    }

    /**
     * 设置控件的大小
     *
     * @param viewId The view id.
     */
    @Override
    public BaseAbsListHolder setViewSize(int viewId, float type, float screen) {
        View view = getView(viewId);
        ViewUtils.setViewSize(mContext, view, type, screen);
        return this;
    }

    /**
     * 设置控件的尺寸
     *
     * @param viewId The view id.
     * @param width  The view width.
     * @param height The view height.
     */
    @Override
    public BaseAbsListHolder setViewSize(int viewId, int width, int height) {
        View view = getView(viewId);
        ViewUtils.setViewSize(mContext, view, width, height);
        return this;
    }

    @Override
    public BaseAbsListHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * Sets the listview or gridview's item selected click listener of the view
     *
     * @param viewId   The view id.
     * @param listener The item selected click listener;
     * @return The BaseAbsListHolder for chaining.
     */
    @Override
    public BaseAbsListHolder setOnItemSelectedClickListener(int viewId,
            AdapterView.OnItemSelectedListener listener) {
        AdapterView view = getView(viewId);
        view.setOnItemSelectedListener(listener);
        return this;
    }

    @Override
    public BaseAbsListHolder setOnCheckedChangeListener(int viewId,
            SmoothCheckBox.OnCheckedChangeListener listener) {
        SmoothCheckBox view = getView(viewId);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

    /**
     * Sets the on checked change listener of the view.
     *
     * @param viewId   The view id.
     * @param listener The checked change listener of compound button.
     * @return The BaseAbsListHolder for chaining.
     */
    @Override
    public BaseAbsListHolder setOnCheckedChangeListener(int viewId,
            CompoundButton.OnCheckedChangeListener listener) {
        CompoundButton view = getView(viewId);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

    /**
     * Sets the on longClick listener of the view.
     *
     * @param viewId
     * @param listener
     * @return
     */
    @Override
    public BaseAbsListHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    /**
     * Sets the on touch listener of the view.
     *
     * @param viewId   The view id.
     * @param listener The on touch listener;
     * @return The BaseAbsListHolder for chaining.
     */
    @Override
    public BaseAbsListHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }


    /**
     * Sets the listview or gridview's item click listener of the view
     *
     * @param viewId   The view id.
     * @param listener The item on click listener;
     * @return The BaseAbsListHolder for chaining.
     */
    @Override
    public BaseAbsListHolder setOnItemClickListener(int viewId,
            AdapterView.OnItemClickListener listener) {
        AdapterView view = getView(viewId);
        view.setOnItemClickListener(listener);
        return this;
    }

    /**
     * Sets the listview or gridview's item long click listener of the view
     *
     * @param viewId   The view id.
     * @param listener The item long click listener;
     * @return The BaseAbsListHolder for chaining.
     */
    @Override
    public BaseAbsListHolder setOnItemLongClickListener(int viewId,
            AdapterView.OnItemLongClickListener listener) {
        AdapterView view = getView(viewId);
        view.setOnItemLongClickListener(listener);
        return this;
    }
}
