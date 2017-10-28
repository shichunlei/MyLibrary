package chingtech.library.base.application;

import android.app.Application;

/**
 * MyLibrary
 * Package chingtech.library.base.application
 * Description:
 * Created by 师春雷
 * Created at 17/6/27 上午9:27
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }
}
