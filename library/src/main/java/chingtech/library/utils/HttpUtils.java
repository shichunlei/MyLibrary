package chingtech.library.utils;

import android.util.Log;

import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

public class HttpUtils {

    /**
     * 发送get请求
     * @param <T>
     */
    public static <T> Cancelable Get(String url, Map<String, String> map, CommonCallback<T> callback){
        RequestParams params = new RequestParams(url);
        if(null != map){
            for(Map.Entry<String, String> entry : map.entrySet()){
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        Log.d("HttpUtils", "Get: " + params.toString());
        return x.http().get(params, callback);
    }

    /**
     * 发送post请求
     * @param <T>
     */
    public static <T> Cancelable Post(String url, Map<String, String> map, CommonCallback<T> callback){
        RequestParams params = new RequestParams(url);
        if(null != map){
            for(Map.Entry<String, String> entry : map.entrySet()){
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        Log.i("HttpUtils", "Post: " + params.toString());
        return x.http().post(params, callback);
    }

    /**
     * 上传文件
     * @param <T>
     */
    public static <T> Cancelable UpLoadFile(String url, Map<String, Object> map, CommonCallback<T> callback){
        RequestParams params = new RequestParams(url);
        if(null != map){
            for(Map.Entry<String, Object> entry : map.entrySet()){
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        Log.i("HttpUtils", "UpLoadFile: " + params.toString());
        params.setMultipart(true);
        return x.http().post(params, callback);
    }
}
