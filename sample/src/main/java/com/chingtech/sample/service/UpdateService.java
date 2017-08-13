package com.chingtech.sample.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

/**
 * MyLibrary
 * Package com.chingtech.library.service
 * Description:
 * Created by 师春雷
 * Created at 17/7/13 下午5:56
 */
public class UpdateService extends Service {

    private int     data;
    private Handler handler;
    private boolean isStart;
    private boolean startUpdate;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Tag", "onCreate: =====");
    }

    @Override
    public IBinder onBind(Intent intent) {
        isStart = true;
        new Thread(new MyThread()).start();
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public void setDate(final TextView tv, final UpdateData updata) {
            startUpdate = true;
            handler = new Handler() {
                public void handleMessage(Message msg) {
                    updata.update(tv, data);
                }
            };
        }
    }

    public class MyThread implements Runnable {

        @Override
        public void run() {
            while (isStart) {
                if (startUpdate) {
                    data++;
                    Message msg = handler.obtainMessage();
                    msg.arg1 = data;
                    handler.sendMessage(msg);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public interface UpdateData {
        void update(TextView tv, int data);
    }
}
