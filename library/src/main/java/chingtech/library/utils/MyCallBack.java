package chingtech.library.utils;

import android.util.Log;

import org.xutils.common.Callback;

public class MyCallBack<ResultType> implements Callback.CommonCallback<ResultType>{

    @Override
    public void onSuccess(ResultType result) {
        Log.i("MyCallBack", "onSuccess:" + result.toString());
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        Log.i("MyCallBack", "onError:" + ex.getMessage());
    }

    @Override
    public void onCancelled(CancelledException cex) {
        Log.i("MyCallBack", "onCancelled:" + cex.getMessage());
    }

    @Override
    public void onFinished() {
        Log.i("MyCallBack", "onFinished");
    }
}
