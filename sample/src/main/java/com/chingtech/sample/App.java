package com.chingtech.sample;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;
import chingtech.library.base.application.BaseApplication;
import com.chingtech.greendao.gen.DaoMaster;
import com.chingtech.greendao.gen.DaoSession;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;

/**
 * <p>
 * *    ***********    ***********    **
 * *    ***********    ***********    **
 * *    **             **             **
 * *    **             **             **
 * *    **             **             **
 * *    ***********    **             **
 * *    ***********    **             **
 * *             **    **             **
 * *             **    **             **
 * *             **    **             **
 * *    ***********    ***********    ***********
 * *    ***********    ***********    ***********
 * </p>
 * MyLibrary
 * Package com.chingtech.library
 * Description:
 * Created by 师春雷
 * Created at 2017/4/17 17:26
 */
public class App extends BaseApplication {

    private       DaoMaster.DevOpenHelper mHelper;
    private       SQLiteDatabase          db;
    private       DaoMaster               mDaoMaster;
    private       DaoSession              mDaoSession;
    public static App                     instances;

    public static App getInstances() {
        return instances;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instances = this;
        setDatabase();

        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
            MaterialHeader header = new MaterialHeader(context);
            header.setShowBezierWave(true);
            header.setPrimaryColors(ContextCompat.getColor(context, R.color.colorPrimary),
                                    ContextCompat.getColor(context, android.R.color.white));
            return header;
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> {
            BallPulseFooter footer = new BallPulseFooter(context);
            footer.setPrimaryColors(ContextCompat.getColor(context, R.color.colorPrimary),
                                    ContextCompat.getColor(context, android.R.color.white));
            footer.setSpinnerStyle(SpinnerStyle.Scale);//设置为拉伸模式
            return footer;
        });
    }

    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
