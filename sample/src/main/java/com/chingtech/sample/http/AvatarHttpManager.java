package com.chingtech.sample.http;

import chingtech.library.utils.LogUtils;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.chingtech.sample.http.ApiUtils.BASE_URL;

public class AvatarHttpManager {

    private static AvatarHttpManager mHttpClient;

    private final OkHttpClient.Builder mBuilder;
    private final Retrofit             mRetrofit;
    private final ApiService           mApiService;

    public static AvatarHttpManager getInstance() {
        if (mHttpClient == null) {
            synchronized (AvatarHttpManager.class) {
                if (mHttpClient == null) {
                    mHttpClient = new AvatarHttpManager();
                }
            }
        }
        return mHttpClient;
    }

    public AvatarHttpManager() {
        mBuilder = new OkHttpClient.Builder()
                // 超时设置
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS);

        // 添加各种插入器
        addInterceptor(mBuilder);

        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                                          .addConverterFactory(GsonConverterFactory.create())
                                          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                          .client(mBuilder.build())
                                          .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    private void addInterceptor(OkHttpClient.Builder builder) {
        // 添加http log
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor(
                message -> LogUtils.i("HttpManager", message));
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logger);

    }

    public ApiService getApiService() {
        return mApiService;
    }
}
