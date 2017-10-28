package chingtech.library.utils;

import android.util.Log;

public class LogUtils {

    private static final boolean LOG_DEBUG = true;

    public static void logMethod(Object msg) {
        try {
            if (LOG_DEBUG) {
                StackTraceElement stackTrace = new Throwable().getStackTrace()[1];
                d("method",
                  stackTrace.getFileName() + ":" + stackTrace.getMethodName() + ":" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void v(String tag, String msg) {
        try {
            if (LOG_DEBUG) {
                Log.v(tag, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void v(Object tag, String msg) {
        try {
            if (LOG_DEBUG) {
                Log.v(tag.getClass().getSimpleName(), msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void i(String tag, String msg) {
        try {
            if (LOG_DEBUG) {
                Log.i(tag, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void i(Object tag, String msg) {
        try {
            if (LOG_DEBUG) {
                Log.i(tag.getClass().getSimpleName(), msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void d(String tag, String msg) {
        try {
            if (LOG_DEBUG) {
                Log.d(tag, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void d(Object tag, String msg) {
        try {
            if (LOG_DEBUG) {
                Log.d(tag.getClass().getSimpleName(), msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void w(String tag, String msg) {
        if (LOG_DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void w(Object tag, String msg) {
        try {
            if (LOG_DEBUG) {
                Log.w(tag.getClass().getSimpleName(), msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void e(String tag, Object obj) {
        try {
            if (LOG_DEBUG) {
                Log.e(tag, obj + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void e(Object tag, Object obj) {
        try {
            if (LOG_DEBUG) {
                Log.e(tag.getClass().getSimpleName(), obj + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
