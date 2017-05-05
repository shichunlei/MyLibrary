package chingtech.library.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
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
     * dp转px
     *
     * @param ctx
     * @param dpValue
     * @return
     */
    public static int dip2px(Context ctx, float dpValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param ctx
     * @param pxValue
     * @return
     */
    public static int px2dip(Context ctx, float pxValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转px
     *
     * @param ctx
     * @param spValue
     * @return
     */
    public static int sp2px(Context ctx, float spValue) {
        final float scale = ctx.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * Gets the width of the display, in pixels.
     *
     * @param context
     * @return
     */
    public static int screenWidthPixel(Context context) {
        int sScreenWidthPixels;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Point outPoint = new Point();
            display.getRealSize(outPoint);
            sScreenWidthPixels = outPoint.x;
        }  else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point outPoint = new Point();
            display.getSize(outPoint);
            sScreenWidthPixels = outPoint.x;
        } else {
            sScreenWidthPixels = display.getWidth();
        }
        return sScreenWidthPixels;
    }
}
