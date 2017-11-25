package chingtech.library.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

/**
 * MyLibrary
 * Package chingtech.library.utils
 * Description:
 * Created by 师春雷
 * Created at 2017/4/11 11:24
 */
public class ScreenUtils {

    private ScreenUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转为px
     *
     * @param dpVal
     * @return
     */
    public static float dp2px(float dpVal) {
        Resources r = Resources.getSystem();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, r.getDisplayMetrics());
    }

    /**
     * sp转为px
     *
     * @param spVal
     * @return
     */
    public static float sp2px(float spVal) {
        Resources r = Resources.getSystem();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, r.getDisplayMetrics());
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param pxValue
     */
    public static float px2dp(float pxValue) {
        Resources   r     = Resources.getSystem();
        final float scale = r.getDisplayMetrics().density;
        return (pxValue / scale + 0.5f);
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    public static float px2sp(float pxVal) {
        Resources r = Resources.getSystem();
        return pxVal / r.getDisplayMetrics().scaledDensity + 0.5f;
    }

    /**
     * Gets the width of the display, in pixels.
     *
     * @param context
     * @return
     */
    public static int screenWidthPixel(Context context) {
        int sScreenWidthPixels;
        WindowManager windowManager = (WindowManager) context.getSystemService(
                Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Point outPoint = new Point();
            display.getRealSize(outPoint);
            sScreenWidthPixels = outPoint.x;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point outPoint = new Point();
            display.getSize(outPoint);
            sScreenWidthPixels = outPoint.x;
        } else {
            sScreenWidthPixels = display.getWidth();
        }
        return sScreenWidthPixels;
    }
}
