package chingtech.library.base.adapter.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import chingtech.library.base.adapter.ablistview.BaseAbsListHolder;
import com.bumptech.glide.Glide;

import chingtech.library.base.adapter.helper.ViewHelper;
import chingtech.library.utils.ViewUtils;
import chingtech.library.widget.LabelView;
import chingtech.library.widget.SmoothCheckBox;
import com.bumptech.glide.load.Transformation;

public class BaseRecyclerHolder extends RecyclerView.ViewHolder
        implements ViewHelper.RecyclerView<BaseRecyclerHolder> {

    private SparseArray<View> mViews = new SparseArray<>();

    private   View    mConvertView;
    private   int     mLayoutId;
    protected Context mContext;

    public BaseRecyclerHolder(Context context, int layoutId, View itemView) {
        super(itemView);
        this.mContext = context;
        this.mLayoutId = layoutId;
        mConvertView = itemView;
        mConvertView.setTag(this);
    }

    public <V extends View> V getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (V) view;
    }

    public int getLayoutId() {
        return mLayoutId;
    }

    /**
     * 获取item布局
     *
     * @return
     */
    public View getItemView() {
        return mConvertView;
    }

    public BaseRecyclerHolder setOnItemViewClickListener(View.OnClickListener listener) {
        mConvertView.setOnClickListener(listener);
        return this;
    }

    public BaseRecyclerHolder setOnItemViewLongClickListener(View.OnLongClickListener listener) {
        mConvertView.setOnLongClickListener(listener);
        return this;
    }

    @Override
    public BaseRecyclerHolder setText(int viewId, String value) {
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
    public BaseRecyclerHolder setText(int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    @Override
    public BaseRecyclerHolder setTextDeleteLine(int viewId) {
        TextView view = getView(viewId);
        view.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        return this;
    }

    @Override
    public BaseRecyclerHolder setTextUnderLine(int viewId) {
        TextView view = getView(viewId);
        view.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        return this;
    }

    @Override
    public BaseRecyclerHolder setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setTextColor(color);
        return this;
    }

    @Override
    public BaseRecyclerHolder setTextColorRes(int viewId, int colorRes) {
        TextView view = getView(viewId);
        view.setTextColor(ContextCompat.getColor(mContext, colorRes));
        return this;
    }

    @Override
    public BaseRecyclerHolder setImageResource(int viewId, int imgResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imgResId);
        return this;
    }

    @Override
    public BaseRecyclerHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @param viewId viewId
     * @return viewHolder
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public BaseRecyclerHolder setBackground(int viewId, Drawable background) {
        View view = getView(viewId);
        view.setBackground(background);
        return this;
    }

    @Override
    public BaseRecyclerHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    @Override
    public BaseRecyclerHolder setCardBackgroundColor(int viewId, int color) {
        CardView view = getView(viewId);
        view.setCardBackgroundColor(ContextCompat.getColor(mContext, color));
        return this;
    }

    @Override
    public BaseRecyclerHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    @Override
    public BaseRecyclerHolder setImageDrawableRes(int viewId, int drawableRes) {
        Drawable drawable = ContextCompat.getDrawable(mContext, drawableRes);
        return setImageDrawable(viewId, drawable);
    }

    @Override
    public BaseRecyclerHolder setImageUrl(int viewId, String imgUrl) {
        ImageView imageview = getView(viewId);
        Glide.with(mContext).load(imgUrl).centerCrop().crossFade().into(imageview);
        return this;
    }

    @Override
    public BaseRecyclerHolder setImageUrl(int viewId, String imageUrl, int defaultDrawable) {
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
    public BaseRecyclerHolder setImageUrl(int viewId, String imgUrl, int defaultDrawable,
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
    public BaseRecyclerHolder setImageBitmap(int viewId, Bitmap imgBitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(imgBitmap);
        return this;
    }

    /**
     * 设置img图片Icon
     *
     * @param viewId viewId
     * @param icon   icon
     * @return viewHolder
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public BaseRecyclerHolder setImageIcon(@IdRes int viewId, @NonNull Icon icon) {
        ImageView view = getView(viewId);
        view.setImageIcon(icon);
        return this;
    }

    @Override
    public BaseRecyclerHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    @Override
    public BaseRecyclerHolder setVisible(int viewId, int visible) {
        View view = getView(viewId);
        view.setVisibility(visible);
        return this;
    }

    @Override
    public BaseRecyclerHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    @Override
    public BaseRecyclerHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) of a RatingBar.
     *
     * @param viewId The view id.
     * @param rating The rating.
     * @return The BaseRecyclerHolder for chaining.
     */
    @Override
    public BaseRecyclerHolder setRating(int viewId, float rating) {
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
     * @return The BaseRecyclerHolder for chaining.
     */
    @Override
    public BaseRecyclerHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    @Override
    public BaseRecyclerHolder setChecked(int viewId, boolean checked) {
        View view = getView(viewId);
        // View unable cast to Checkable
        if (view instanceof CompoundButton) {
            ((CompoundButton) view).setChecked(checked);
        } else if (view instanceof CheckedTextView) {
            ((CheckedTextView) view).setChecked(checked);
        }
        return this;
    }

    @Override
    public BaseRecyclerHolder setChecked(int viewId, boolean checked, boolean animate) {
        SmoothCheckBox view = getView(viewId);
        view.setChecked(checked, animate);
        return this;
    }

    @Override
    public BaseRecyclerHolder setAlpha(int viewId, float value) {
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
    public BaseRecyclerHolder setTypeface(int viewId, Typeface typeface) {
        TextView view = getView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    @Override
    public BaseRecyclerHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    /**
     * Sets the adapter of a RecyclerView.
     */
    @Override
    public BaseRecyclerHolder setAdapter(@IdRes int viewId, @NonNull RecyclerView.Adapter adapter) {
        RecyclerView view = getView(viewId);
        view.setAdapter(adapter);
        return this;
    }

    /**
     * Sets the LayoutManager of a RecyclerView.
     */
    @Override
    public BaseRecyclerHolder setLayoutManager(@IdRes int viewId,
            @NonNull RecyclerView.LayoutManager manager) {
        RecyclerView view = getView(viewId);
        view.setLayoutManager(manager);
        return this;
    }

    /**
     * Sets the progress and max of a ProgressBar.
     *
     * @param viewId   The view id.
     * @param drawable The drawable.
     */
    @Override
    public BaseRecyclerHolder setProgressDrawable(@IdRes int viewId, Drawable drawable) {
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
    public BaseRecyclerHolder setProgress(@IdRes int viewId, int progress, int max) {
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
    public BaseRecyclerHolder setProgress(@IdRes int viewId, int progress) {
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
    public BaseRecyclerHolder setMax(@IdRes int viewId, int max) {
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
    public BaseRecyclerHolder setEnabled(@IdRes int viewId, boolean enabled) {
        View view = getView(viewId);
        view.setEnabled(enabled);
        return this;
    }

    /**
     * Sets the clickable of a view.
     *
     * @param viewId The view id.
     */
    @Override
    public BaseRecyclerHolder setClickable(@IdRes int viewId, boolean clickable) {
        View view = getView(viewId);
        view.setClickable(clickable);
        return this;
    }

    /**
     * 设置控件的高度
     *
     * @param viewId The view id.
     */
    @Override
    public BaseRecyclerHolder setViewHeight(int viewId, float type) {
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
    public BaseRecyclerHolder setViewHeight(int viewId, float type, float screen) {
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
    public BaseRecyclerHolder setViewWidth(int viewId, float type) {
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
    public BaseRecyclerHolder setViewWidth(int viewId, float type, float screen) {
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
    public BaseRecyclerHolder setViewSize(int viewId, float type) {
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
    public BaseRecyclerHolder setViewSize(int viewId, float type, float screen) {
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
    public BaseRecyclerHolder setViewSize(int viewId, int width, int height) {
        View view = getView(viewId);
        ViewUtils.setViewSize(mContext, view, width, height);
        return this;
    }

    /**
     * Will set the text of a LabelView.
     *
     * @param viewId The view id.
     * @param value  The text to put in the text view.
     * @return The BaseAdapterHelper for chaining.
     */
    @Override
    public BaseRecyclerHolder setLabelText(@IdRes int viewId, String value) {
        LabelView view = getView(viewId);
        view.setText(value);
        return this;
    }

    /**
     * Will set the gravity of a View.
     *
     * @param viewId  The view id.
     * @param gravity The text gravity.
     *
     *                Gravity.TOP | Gravity.LEFT
     *                Gravity.TOP | Gravity.RIGHT
     *                Gravity.BOTTOM | Gravity.LEFT
     */
    @Override
    public BaseRecyclerHolder setGravity(@IdRes int viewId, int gravity) {
        LabelView view = getView(viewId);
        view.setGravity(gravity);
        return this;
    }

    /**
     * Will set the background color of a LabelView.
     *
     * @param viewId  The view id.
     * @param bgColor The text background color.
     * @return The BaseAdapterHelper for chaining.
     */
    @Override
    public BaseRecyclerHolder setLabelBgColor(@IdRes int viewId, int bgColor) {
        LabelView view = getView(viewId);
        view.setBgColor(ContextCompat.getColor(mContext, bgColor));
        return this;
    }

    @Override
    public BaseRecyclerHolder addView(@IdRes int viewId, View subview) {
        ViewGroup layout = getView(viewId);
        layout.addView(subview);
        return this;
    }

    @Override
    public BaseRecyclerHolder addView(@IdRes int viewId, View subview,
            ViewGroup.LayoutParams params) {
        ViewGroup layout = getView(viewId);
        layout.addView(subview, params);
        return this;
    }

    @Override
    public BaseRecyclerHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * Sets item selected click listener of the view
     *
     * @param viewId   The view id.
     * @param listener The item selected click listener;
     * @return The BaseRecyclerHolder for chaining.
     */
    @Override
    public BaseRecyclerHolder setOnItemSelectedClickListener(int viewId,
            AdapterView.OnItemSelectedListener listener) {
        AdapterView view = getView(viewId);
        view.setOnItemSelectedListener(listener);
        return this;
    }

    @Override
    public BaseRecyclerHolder setOnCheckedChangeListener(int viewId,
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
     * @return The BaseRecyclerHolder for chaining.
     */
    @Override
    public BaseRecyclerHolder setOnCheckedChangeListener(int viewId,
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
    public BaseRecyclerHolder setOnLongClickListener(int viewId,
            View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    /**
     * Sets the on touch listener of the view.
     *
     * @param viewId   The view id.
     * @param listener The on touch listener;
     * @return The BaseRecyclerHolder for chaining.
     */
    @Override
    public BaseRecyclerHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }


    /**
     * Sets the listview or gridview's item click listener of the view
     *
     * @param viewId   The view id.
     * @param listener The item on click listener;
     * @return The BaseRecyclerHolder for chaining.
     */
    @Override
    public BaseRecyclerHolder setOnItemClickListener(int viewId,
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
     * @return The BaseRecyclerHolder for chaining.
     */
    @Override
    public BaseRecyclerHolder setOnItemLongClickListener(int viewId,
            AdapterView.OnItemLongClickListener listener) {
        AdapterView view = getView(viewId);
        view.setOnItemLongClickListener(listener);
        return this;
    }
}
