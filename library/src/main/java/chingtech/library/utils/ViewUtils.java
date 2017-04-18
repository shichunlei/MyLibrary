package chingtech.library.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * MyLibrary
 * Package chingtech.library.utils
 * Description:
 * Created by 师春雷
 * Created at 2017/4/11 11:32
 */
public class ViewUtils {

    private ViewUtils() {
		/* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 设置EditText输入金钱时小数点动态监测
     *
     * @param editText
     */
    public static void setPricePoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                    int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
    }

    /**
     * 设置View高度
     *
     * @param context
     * @param view
     * @param type
     * @param screen
     */
    public static void setViewHeight(Context context, View view, float type, float screen) {
        int width = ScreenUtils.screenWidthPixel(context);

        int height = (int) ((width * 1.0f - ScreenUtils.dip2px(context, screen)) * type);

        /** 取控件View当前的布局参数 */
        ViewGroup.LayoutParams params = view.getLayoutParams();
        /** 控件的高强制设成屏幕宽度 */
        params.height = height;
        /** 控件的高强制设成屏幕宽度 */
//		params.width = width;
        /** 使设置好的布局参数应用到控件 */
        view.setLayoutParams(params);
    }

    /**
     * 设置View高度
     *
     * @param context
     * @param view
     * @param type
     */
    public static void setViewHeight(Context context, View view, float type) {
        setViewHeight(context, view, type, 0f);
    }

    /**
     * 设置View宽度
     *
     * @param context
     * @param view
     * @param type
     * @param screen
     */
    public static void setViewWidth(Context context, View view, float type, float screen) {
        int width = ScreenUtils.screenWidthPixel(context);

        int height = (int) ((width * 1.0f - ScreenUtils.dip2px(context, screen)) * type);

        /** 取控件View当前的布局参数 */
        ViewGroup.LayoutParams params = view.getLayoutParams();
        /** 控件的高强制设成屏幕宽度 */
//		params.height = height;
        /** 控件的高强制设成屏幕宽度 */
        params.width = height;
        /** 使设置好的布局参数应用到控件 */
        view.setLayoutParams(params);
    }

    /**
     * 设置View宽度
     *
     * @param context
     * @param view
     * @param type
     */
    public static void setViewWidth(Context context, View view, float type) {
        setViewWidth(context, view, type, 0f);
    }
}
