package com.chingtech.sample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class DBManager {

    private static String  tag     = DBManager.class.getSimpleName(); // for LogCat
    private static boolean isDebug = true;

    @SuppressLint("SdCardPath")
    private static String databasepath = "/data/data/%s/database";// %s is packageName

    // A mapping from assets database file to SQLiteDatabase object
    private Map<String, SQLiteDatabase> databases = new HashMap<>();

    // Context of application
    private        Context   context   = null;
    // Singleton Pattern
    private static DBManager mInstance = null;

    /**
     * Initialize AssetsDatabaseManager
     *
     * @param context, context of application
     */
    public static void initManager(Context context) {
        if (mInstance == null) {
            mInstance = new DBManager(context);
        }
    }

    /**
     * Get a AssetsDatabaseManager object
     *
     * @return, if success return a AssetsDatabaseManager object, else return null
     */
    public static DBManager getManager() {
        return mInstance;
    }

    private DBManager(Context context) {
        this.context = context;
    }

    /**
     * Get a assets database, if this database is opened this method is only return a copy of the
     * opened database
     *
     * @param dbfile, the assets file which will be opened for a database
     * @return, if success it return a SQLiteDatabase object else return null
     */
    @SuppressLint("ApplySharedPref")
    public SQLiteDatabase getDatabase(String dbfile) {
        if (databases.get(dbfile) != null) {
            Log(String.format("Return a database copy of %s", dbfile));
            return databases.get(dbfile);
        }
        if (context == null) {
            return null;
        }
        Log(String.format("Create database %s", dbfile));

        String spath = getDatabaseFilepath();
        String sfile = getDatabaseFile(dbfile);
        File   file  = new File(sfile);

        SharedPreferences dbs = context.getSharedPreferences(DBManager.class.toString(), 0);
        boolean flag = dbs.getBoolean(dbfile,
                                      false); // Get Database file flag, if true means this database file was copied and valid
        if (!flag || !file.exists()) {
            file = new File(spath);
            if (!file.exists() && !file.mkdirs()) {
                Log("Create \"" + spath + "\" fail!");
                return null;
            }
            if (!copyAssetsToFilesystem(dbfile, sfile)) {
                Log(String.format("Copy %s to %s fail!", dbfile, sfile));
                return null;
            }
            dbs.edit().putBoolean(dbfile, true).commit();
        }
        SQLiteDatabase db = SQLiteDatabase.openDatabase(sfile, null,
                                                        SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        if (db != null) {
            databases.put(dbfile, db);
        }
        return db;
    }

    private String getDatabaseFilepath() {
        return String.format(databasepath, context.getApplicationInfo().packageName);
    }

    private String getDatabaseFile(String dbfile) {
        Log("数据库保存路径：-----------------" + getDatabaseFilepath());
        return getDatabaseFilepath() + "/" + dbfile;
    }

    private boolean copyAssetsToFilesystem(String assetsSrc, String des) {
        Log("Copy " + assetsSrc + " to " + des);

        InputStream  istream = null;
        OutputStream ostream = null;
        try {
            AssetManager am = context.getAssets();
            istream = am.open(assetsSrc);
            ostream = new FileOutputStream(des);
            byte[] buffer = new byte[1024];
            int    length;
            while ((length = istream.read(buffer)) > 0) {
                ostream.write(buffer, 0, length);
            }
            istream.close();
            ostream.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (istream != null) {
                    istream.close();
                }
                if (ostream != null) {
                    ostream.close();
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            return false;
        }
        return true;
    }

    /**
     * Close assets database
     *
     * @param dbfile, the assets file which will be closed soon
     * @return, the status of this operating
     */
    public boolean closeDatabase(String dbfile) {
        if (databases.get(dbfile) != null) {
            SQLiteDatabase db = databases.get(dbfile);
            db.close();
            databases.remove(dbfile);
            return true;
        }
        return false;
    }

    /**
     * Close all assets database
     */
    static public void closeAllDatabase() {
        Log("closeAllDatabase");
        if (mInstance != null) {
            for (int i = 0; i < mInstance.databases.size(); ++i) {
                if (mInstance.databases.get(i) != null) {
                    mInstance.databases.get(i).close();
                }
            }
            mInstance.databases.clear();
        }
    }

    public static void setDebug(boolean debug) {
        isDebug = debug;
    }

    private static void Log(String logcat) {
        if (isDebug) {
            Log.i(tag, logcat);
        }
    }
}
