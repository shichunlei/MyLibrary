package com.chingtech.library;

import android.app.Application;

import org.xutils.x;

/**
 * MyLibrary
 * Package com.chingtech.library
 * Description:
 * Created by 师春雷
 * Created at 2017/4/17 17:26
 */
public class App extends Application{
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        x.Ext.init(instance);
    }
}
