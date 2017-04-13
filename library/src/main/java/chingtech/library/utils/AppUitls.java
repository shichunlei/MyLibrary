package chingtech.library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.inputmethod.InputMethodManager;

import java.io.File;

public class AppUitls {

    private AppUitls() {
		/* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static PackageManager manager;

    /***
     * 得到程序名称
     *
     * @param context
     * @return
     */
    public static String getAppName(Context context) {
        try {
            manager = context.getPackageManager();
            return context.getApplicationInfo().loadLabel(manager).toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取包名
     *
     * @param context
     * @return
     */
    public static String getPkName(Context context) {
        try {
            return context.getPackageName();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取版本名
     *
     * @param context
     * @return 当前应用的版本名
     */
    public static String getVersionName(Context context) {
        try {
            manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(getPkName(context), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return 当前应用的版本号
     */
    public static int getVersionCode(Context context) {
        try {
            manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(getPkName(context), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 隐藏虚拟键盘
     *
     * @param context
     */
    public static void hideKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        // 判断隐藏软键盘是否弹出
        if (imm.isActive()) {
            try {
                imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 安装应用
     *
     * @param context
     * @param fileName
     */
    public static void installApp(Context context, String fileName) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
