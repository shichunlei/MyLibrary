package chingtech.library.base.adapter.helper;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import chingtech.library.base.adapter.ablistview.BaseAbsListHolder;
import chingtech.library.base.adapter.recyclerview.BaseRecyclerHolder;
import chingtech.library.widget.SmoothCheckBox;
import com.bumptech.glide.load.Transformation;

public interface ViewHelper {

    interface AbsListView<VH extends BaseAbsListHolder> {
        /**
         * 设置textView文本内容
         *
         * @param viewId viewId
         * @param value  文本内容
         * @return viewHolder
         */
        VH setText(int viewId, String value);

        /**
         * 设置textView文本内容
         *
         * @param viewId viewId
         * @param value  文本内容
         * @return viewHolder
         */
        VH setText(int viewId, CharSequence value);

        /**
         * 设置文本删除线
         *
         * @param viewId viewId
         * @return
         */
        VH setTextDeleteLine(int viewId);

        /**
         * 设置文本下划线
         *
         * @param viewId viewId
         * @return
         */
        VH setTextUnderLine(int viewId);

        /**
         * 设置textView文本颜色
         *
         * @param viewId viewId
         * @param color  颜色数值
         * @return viewHolder
         */
        VH setTextColor(int viewId, int color);

        /**
         * 设置textView文本颜色
         *
         * @param viewId   viewId
         * @param colorRes 颜色Id
         * @return viewHolder
         */
        VH setTextColorRes(int viewId, int colorRes);

        /**
         * 设置imgView的图片,通过Id设置
         *
         * @param viewId   viewId
         * @param imgResId 图片Id
         * @return viewHolder viewHolder
         */
        VH setImageResource(int viewId, int imgResId);

        /**
         * 设置背景颜色
         *
         * @param viewId viewId
         * @param color  颜色数值
         * @return viewHolder viewHolder
         */
        VH setBackgroundColor(int viewId, int color);

        /**
         * 设置背景颜色
         *
         * @param viewId   viewId
         * @param colorRes 颜色Id
         * @return viewHolder
         */
        VH setBackgroundColorRes(int viewId, int colorRes);

        /**
         * 设置img的Drawable
         *
         * @param viewId   viewId
         * @param drawable drawable
         * @return viewHolder
         */
        VH setImageDrawable(int viewId, Drawable drawable);

        /**
         * 设置img的Drawable
         *
         * @param viewId      viewId
         * @param drawableRes drawableId
         * @return viewHolder
         */
        VH setImageDrawableRes(int viewId, int drawableRes);

        /**
         * 设置img图片路径
         *
         * @param viewId viewId
         * @param imgUrl 图片路径
         * @return viewHolder
         */
        VH setImageUrl(int viewId, String imgUrl);

        /**
         * 设置img图片路径
         *
         * @param viewId viewId
         * @param imgUrl 图片路径
         * @return viewHolder
         */
        VH setImageUrl(int viewId, String imgUrl, int defaultDrawable);

        /**
         * 设置img图片路径
         *
         * @param viewId          viewId
         * @param imgUrl          图片路径
         * @param defaultDrawable 默认图片
         * @param transformation  变换形式
         * @return viewHolder
         */
        VH setImageUrl(int viewId, String imgUrl, int defaultDrawable,
                Transformation transformation);

        /**
         * 设置img图片Bitmap
         *
         * @param viewId    viewId
         * @param imgBitmap imgBitmap
         * @return viewHolder
         */
        VH setImageBitmap(int viewId, Bitmap imgBitmap);

        /**
         * 设置控件是否显示
         *
         * @param viewId  viewId
         * @param visible true(visible)/false(gone)
         * @return viewHolder
         */
        VH setVisible(int viewId, boolean visible);

        /**
         * 设置控件是否显示
         *
         * @param viewId  viewId
         * @param visible visible,invisible,gone
         * @return viewHolder
         */
        VH setVisible(int viewId, int visible);

        /**
         * 设置控件的tag
         *
         * @param viewId viewId
         * @param tag    tag
         * @return viewHolder
         */
        VH setTag(int viewId, Object tag);

        /**
         * 设置控件tag
         *
         * @param viewId viewId
         * @param key    tag的key
         * @param tag    tag
         * @return viewHolder
         */
        VH setTag(int viewId, int key, Object tag);

        /**
         * Sets the rating (the number of stars filled) of a RatingBar.
         *
         * @param viewId The view id.
         * @param rating The rating.
         * @return
         */
        VH setRating(int viewId, float rating);

        /**
         * Sets the rating (the number of stars filled) and max of a RatingBar.
         *
         * @param viewId The view id.
         * @param rating The rating.
         * @param max    The range of the RatingBar to 0...max.
         * @return
         */
        VH setRating(int viewId, float rating, int max);

        /**
         * 设置Checkable控件的选择情况
         *
         * @param viewId  viewId
         * @param checked 选择
         * @return viewHolder
         */
        VH setChecked(int viewId, boolean checked);

        /**
         * 设置SmoothCheckBox控件的选择情况
         *
         * @param viewId  viewId
         * @param checked 选择
         * @param animate 动画
         * @return viewHolder
         */
        VH setChecked(int viewId, boolean checked, boolean animate);

        /**
         * 设置控件透明效果
         *
         * @param viewId viewId
         * @param value  透明值
         * @return viewHolder
         */
        VH setAlpha(int viewId, float value);

        /**
         * 设置TextView字体
         *
         * @param viewId   viewId
         * @param typeface typeface
         * @return viewHolder
         */
        VH setTypeface(int viewId, Typeface typeface);

        /**
         * 设置多个TextView字体
         *
         * @param typeface typeface
         * @param viewIds  viewId组合
         * @return viewHolder
         */
        VH setTypeface(Typeface typeface, int... viewIds);

        /**
         * Sets the adapter of a adapter view.
         *
         * @param viewId
         * @param adapter
         * @return
         */
        VH setAdapter(@IdRes int viewId, @NonNull Adapter adapter);

        /**
         * Sets the progress and max of a ProgressBar.
         *
         * @param viewId   The view id.
         * @param drawable The drawable.
         * @return
         */
        VH setProgressDrawable(@IdRes int viewId, Drawable drawable);

        /**
         * Sets the progress and max of a ProgressBar.
         *
         * @param viewId   The view id.
         * @param progress The progress.
         * @param max      The max value of a ProgressBar.
         * @return
         */
        VH setProgress(@IdRes int viewId, int progress, int max);

        /**
         * Sets the progress of a ProgressBar.
         *
         * @param viewId   The view id.
         * @param progress The progress.
         * @return
         */
        VH setProgress(@IdRes int viewId, int progress);

        /**
         * Sets the progress and max of a ProgressBar.
         *
         * @param viewId The view id.
         * @param max    The max value of a ProgressBar.
         * @return
         */
        VH setMax(@IdRes int viewId, int max);

        /**
         * Sets the enable of a view.
         *
         * @param viewId  The view id.
         * @param enabled The progress.
         * @return
         */
        VH setEnabled(@IdRes int viewId, boolean enabled);

        /**
         * 设置控件的高度
         *
         * @param viewId The view id.
         * @param type
         * @return
         */
        VH setViewHeight(int viewId, float type);

        /**
         * 设置控件的高度
         *
         * @param viewId The view id.
         * @param type
         * @param screen
         * @return
         */
        VH setViewHeight(int viewId, float type, float screen);

        /**
         * 设置控件的宽度
         *
         * @param viewId The view id.
         * @param type
         * @return
         */
        VH setViewWidth(int viewId, float type);

        /**
         * 设置控件的宽度
         *
         * @param viewId The view id.
         * @param type
         * @param screen
         * @return
         */
        VH setViewWidth(int viewId, float type, float screen);

        /**
         * 设置控件的大小
         *
         * @param viewId The view id.
         * @param type
         * @return
         */
        VH setViewSize(int viewId, float type);

        /**
         * 设置控件的大小
         *
         * @param viewId The view id.
         * @param type
         * @param screen
         * @return
         */
        VH setViewSize(int viewId, float type, float screen);

        /**
         * 设置控件的尺寸
         *
         * @param viewId The view id.
         * @param width  The view width.
         * @param height The view height.
         * @return
         */
        VH setViewSize(int viewId, int width, int height);

        /**
         * 设置监听
         *
         * @param viewId
         * @param listener
         * @return
         */
        VH setOnClickListener(int viewId, View.OnClickListener listener);

        /**
         * Sets the listview or gridview's item selected click listener of the view
         *
         * @param viewId   The view id.
         * @param listener The item selected click listener;
         * @return
         */
        VH setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener);

        /**
         * 设置监听 SmoothCheckBox.OnCheckedChangeListener
         *
         * @param viewId
         * @param listener
         * @return
         */
        VH setOnCheckedChangeListener(int viewId, SmoothCheckBox.OnCheckedChangeListener listener);

        /**
         * 设置监听 CompoundButton.OnCheckedChangeListener
         *
         * @param viewId
         * @param listener
         * @return
         */
        VH setOnCheckedChangeListener(int viewId, CompoundButton.OnCheckedChangeListener listener);

        /**
         * Sets the on longClick listener of the view.
         *
         * @param viewId
         * @param listener
         * @return
         */
        VH setOnLongClickListener(int viewId, View.OnLongClickListener listener);

        /**
         * Sets the on touch listener of the view.
         *
         * @param viewId   The view id.
         * @param listener The on touch listener;
         * @return
         */
        VH setOnTouchListener(int viewId, View.OnTouchListener listener);

        /**
         * Sets item click listener of the view
         *
         * @param viewId   The view id.
         * @param listener The item on click listener;
         * @return
         */
        VH setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener);

        /**
         * Sets item long click listener of the view
         *
         * @param viewId   The view id.
         * @param listener The item long click listener;
         * @return
         */
        VH setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener);

    }

    interface RecyclerView<VH extends BaseRecyclerHolder> {
        /**
         * 设置textView文本内容
         *
         * @param viewId viewId
         * @param value  文本内容
         * @return viewHolder
         */
        VH setText(int viewId, String value);

        /**
         * 设置textView文本内容
         *
         * @param viewId viewId
         * @param value  文本内容
         * @return viewHolder
         */
        VH setText(int viewId, CharSequence value);

        /**
         * 设置文本删除线
         *
         * @param viewId viewId
         * @return viewHolder
         */
        VH setTextDeleteLine(int viewId);

        /**
         * 设置文本下划线
         *
         * @param viewId viewId
         * @return viewHolder
         */
        VH setTextUnderLine(int viewId);

        /**
         * 设置textView文本颜色
         *
         * @param viewId viewId
         * @param color  颜色数值
         * @return viewHolder
         */
        VH setTextColor(int viewId, int color);

        /**
         * 设置textView文本颜色
         *
         * @param viewId   viewId
         * @param colorRes 颜色Id
         * @return viewHolder
         */
        VH setTextColorRes(int viewId, int colorRes);

        /**
         * 设置imgView的图片,通过Id设置
         *
         * @param viewId   viewId
         * @param imgResId 图片Id
         * @return viewHolder viewHolder
         */
        VH setImageResource(int viewId, int imgResId);

        /**
         * 设置背景颜色
         *
         * @param viewId viewId
         * @param color  颜色数值
         * @return viewHolder viewHolder
         */
        VH setBackgroundColor(int viewId, int color);

        /**
         * 设置背景颜色
         *
         * @param viewId     viewId
         * @param background
         * @return viewHolder viewHolder
         */
        VH setBackground(int viewId, Drawable background);

        /**
         * 设置背景颜色
         *
         * @param viewId   viewId
         * @param backgroundRes
         * @return viewHolder
         */
        VH setBackgroundRes(int viewId, int backgroundRes);

        /**
         * 设置CardView背景颜色
         *
         * @param viewId
         * @param colorRes
         * @return
         */
        VH setCardBackgroundColor(int viewId, int colorRes);

        /**
         * 设置img的Drawable
         *
         * @param viewId   viewId
         * @param drawable drawable
         * @return viewHolder
         */
        VH setImageDrawable(int viewId, Drawable drawable);

        /**
         * 设置img的Drawable
         *
         * @param viewId      viewId
         * @param drawableRes drawableId
         * @return viewHolder
         */
        VH setImageDrawableRes(int viewId, int drawableRes);

        /**
         * 设置img图片路径
         *
         * @param viewId viewId
         * @param imgUrl 图片路径
         * @return viewHolder
         */
        VH setImageUrl(int viewId, String imgUrl);

        /**
         * 设置img图片路径
         *
         * @param viewId          viewId
         * @param imgUrl          图片路径
         * @param defaultDrawable 默认图片
         * @return viewHolder
         */
        VH setImageUrl(int viewId, String imgUrl, int defaultDrawable);

        /**
         * 设置img图片路径
         *
         * @param viewId          viewId
         * @param imgUrl          图片路径
         * @param defaultDrawable 默认图片
         * @param transformation  变换形式
         * @return viewHolder
         */
        VH setImageUrl(int viewId, String imgUrl, int defaultDrawable,
                Transformation transformation);

        /**
         * 设置img图片Bitmap
         *
         * @param viewId    viewId
         * @param imgBitmap imgBitmap
         * @return viewHolder
         */
        VH setImageBitmap(int viewId, Bitmap imgBitmap);

        /**
         * 设置img图片Bitmap
         *
         * @param viewId viewId
         * @param icon   icon
         * @return viewHolder
         */
        VH setImageIcon(@IdRes int viewId, @NonNull Icon icon);

        /**
         * 设置控件是否显示
         *
         * @param viewId  viewId
         * @param visible true(visible)/false(gone)
         * @return viewHolder
         */
        VH setVisible(int viewId, boolean visible);

        /**
         * 设置控件是否显示
         *
         * @param viewId  viewId
         * @param visible visible,invisible,gone
         * @return viewHolder
         */
        VH setVisible(int viewId, int visible);

        /**
         * 设置控件的tag
         *
         * @param viewId viewId
         * @param tag    tag
         * @return viewHolder
         */
        VH setTag(int viewId, Object tag);

        /**
         * 设置控件tag
         *
         * @param viewId viewId
         * @param key    tag的key
         * @param tag    tag
         * @return viewHolder
         */
        VH setTag(int viewId, int key, Object tag);

        /**
         * Sets the rating (the number of stars filled) of a RatingBar.
         *
         * @param viewId The view id.
         * @param rating The rating.
         * @return
         */
        VH setRating(int viewId, float rating);

        /**
         * Sets the rating (the number of stars filled) and max of a RatingBar.
         *
         * @param viewId The view id.
         * @param rating The rating.
         * @param max    The range of the RatingBar to 0...max.
         * @return
         */
        VH setRating(int viewId, float rating, int max);

        /**
         * 设置Checkable控件的选择情况
         *
         * @param viewId  viewId
         * @param checked 选择
         * @return viewHolder
         */
        VH setChecked(int viewId, boolean checked);

        /**
         * 设置SmoothCheckBox控件的选择情况
         *
         * @param viewId  viewId
         * @param checked 选择
         * @param animate 动画
         * @return viewHolder
         */
        VH setChecked(int viewId, boolean checked, boolean animate);

        /**
         * 设置控件透明效果
         *
         * @param viewId viewId
         * @param value  透明值
         * @return viewHolder
         */
        VH setAlpha(@IdRes int viewId, @FloatRange(from = 0.0, to = 1.0) float value);

        /**
         * 设置TextView字体
         *
         * @param viewId   viewId
         * @param typeface typeface
         * @return viewHolder
         */
        VH setTypeface(int viewId, Typeface typeface);

        /**
         * 设置多个TextView字体
         *
         * @param typeface typeface
         * @param viewIds  viewId组合
         * @return viewHolder
         */
        VH setTypeface(Typeface typeface, int... viewIds);

        /**
         * Sets the adapter of a RecyclerView.
         *
         * @param viewId
         * @param adapter
         * @return
         */
        VH setAdapter(@IdRes int viewId,
                @NonNull android.support.v7.widget.RecyclerView.Adapter adapter);

        /**
         * Sets the LayoutManager of a RecyclerView.
         *
         * @param viewId
         * @param manager
         * @return
         */
        VH setLayoutManager(@IdRes int viewId, @NonNull LayoutManager manager);

        /**
         * Sets the progress and max of a ProgressBar.
         *
         * @param viewId   The view id.
         * @param drawable The drawable.
         * @return
         */
        VH setProgressDrawable(@IdRes int viewId, Drawable drawable);

        /**
         * Sets the progress and max of a ProgressBar.
         *
         * @param viewId   The view id.
         * @param progress The progress.
         * @param max      The max value of a ProgressBar.
         * @return
         */
        VH setProgress(@IdRes int viewId, int progress, int max);

        /**
         * Sets the progress of a ProgressBar.
         *
         * @param viewId   The view id.
         * @param progress The progress.
         * @return
         */
        VH setProgress(@IdRes int viewId, int progress);

        /**
         * Sets the progress and max of a ProgressBar.
         *
         * @param viewId The view id.
         * @param max    The max value of a ProgressBar.
         * @return
         */
        VH setMax(@IdRes int viewId, int max);

        /**
         * Sets the enable of a view.
         *
         * @param viewId  The view id.
         * @param enabled
         * @return
         */
        VH setEnabled(@IdRes int viewId, boolean enabled);

        /**
         * Sets the clickable of a view.
         *
         * @param viewId    The view id.
         * @param clickable
         * @return
         */
        VH setClickable(@IdRes int viewId, boolean clickable);

        /**
         * 设置控件的高度
         *
         * @param viewId The view id.
         * @param type
         * @return
         */
        VH setViewHeight(@IdRes int viewId, float type);

        /**
         * 设置控件的高度
         *
         * @param viewId The view id.
         * @param type
         * @param screen
         * @return
         */
        VH setViewHeight(@IdRes int viewId, float type, float screen);

        /**
         * 设置控件的宽度
         *
         * @param viewId The view id.
         * @param type
         * @return
         */
        VH setViewWidth(@IdRes int viewId, float type);

        /**
         * 设置控件的宽度
         *
         * @param viewId The view id.
         * @param type
         * @param screen
         * @return
         */
        VH setViewWidth(@IdRes int viewId, float type, float screen);

        /**
         * 设置控件的大小
         *
         * @param viewId The view id.
         * @param type
         * @return
         */
        VH setViewSize(int viewId, float type);

        /**
         * 设置控件的大小
         *
         * @param viewId The view id.
         * @param type
         * @param screen
         * @return
         */
        VH setViewSize(@IdRes int viewId, float type, float screen);

        /**
         * 设置控件的尺寸
         *
         * @param viewId The view id.
         * @param width  The view width.
         * @param height The view height.
         * @return
         */
        VH setViewSize(@IdRes int viewId, int width, int height);

        /**
         * Will set the text of a LabelView.
         *
         * @param viewId The view id.
         * @param value  The text to put in the text view.
         * @return The BaseAdapterHelper for chaining.
         */
        VH setLabelText(@IdRes int viewId, String value);

        /**
         * Will set the gravity of a View.
         *
         * @param viewId  The view id.
         * @param gravity The text gravity.
         *
         *                Gravity.TOP | Gravity.LEFT
         *                Gravity.TOP | Gravity.RIGHT
         *                Gravity.BOTTOM | Gravity.LEFT
         *                Gravity.BOTTOM | Gravity.RIGHT
         */
        VH setGravity(@IdRes int viewId, int gravity);

        /**
         * Will set the background color of a LabelView.
         *
         * @param viewId  The view id.
         * @param bgColor The text background color.
         * @return The BaseAdapterHelper for chaining.
         */
        VH setLabelBgColor(@IdRes int viewId, int bgColor);

        /**
         * 动态加载布局
         *
         * @param viewId
         * @param view
         * @return
         */
        VH addView(@IdRes int viewId, View view);

        /**
         * 动态加载布局
         *
         * @param viewId
         * @param view
         * @param params
         * @return
         */
        VH addView(@IdRes int viewId, View view, ViewGroup.LayoutParams params);

        /**
         * 设置监听 View.OnClickListener
         *
         * @param viewId
         * @param listener
         * @return
         */
        VH setOnClickListener(int viewId, View.OnClickListener listener);

        /**
         * Sets the listview or gridview's item selected click listener of the view
         *
         * @param viewId   The view id.
         * @param listener The item selected click listener;
         * @return
         */
        VH setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener);

        /**
         * 设置监听 SmoothCheckBox.OnCheckedChangeListener
         *
         * @param viewId
         * @param listener
         * @return
         */
        VH setOnCheckedChangeListener(int viewId, SmoothCheckBox.OnCheckedChangeListener listener);

        /**
         * 设置监听 CompoundButton.OnCheckedChangeListener
         *
         * @param viewId
         * @param listener
         * @return
         */
        VH setOnCheckedChangeListener(int viewId, CompoundButton.OnCheckedChangeListener listener);

        /**
         * Sets the on longClick listener of the view.
         *
         * @param viewId
         * @param listener
         * @return
         */
        VH setOnLongClickListener(int viewId, View.OnLongClickListener listener);

        /**
         * Sets the on touch listener of the view.
         *
         * @param viewId   The view id.
         * @param listener The on touch listener;
         * @return
         */
        VH setOnTouchListener(int viewId, View.OnTouchListener listener);

        /**
         * Sets item click listener of the view
         *
         * @param viewId   The view id.
         * @param listener The item on click listener;
         * @return
         */
        VH setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener);

        /**
         * Sets item long click listener of the view
         *
         * @param viewId   The view id.
         * @param listener The item long click listener;
         * @return
         */
        VH setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener);
    }
}
