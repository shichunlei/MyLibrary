package com.chingtech.sample;

import chingtech.library.utils.LogUtils;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.chingtech.sample.Constant.JISU_API_URL;

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
 * Package com.chingtech.sample.view
 * Description:
 * Created by 师春雷
 * Created at 17/9/17 下午5:20
 */
public class JiSuHttpManager {

    private static JiSuHttpManager mHttpClient;

    private final OkHttpClient.Builder mBuilder;
    private final Retrofit             mRetrofit;
    private final ApiService           mApiService;

    public static JiSuHttpManager getInstance() {
        if (mHttpClient == null) {
            synchronized (JiSuHttpManager.class) {
                if (mHttpClient == null) {
                    mHttpClient = new JiSuHttpManager();
                }
            }
        }
        return mHttpClient;
    }

    public JiSuHttpManager() {
        mBuilder = new OkHttpClient.Builder()
                // 超时设置
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS);

        // 添加各种插入器
        addInterceptor(mBuilder);

        mRetrofit = new Retrofit.Builder().baseUrl(JISU_API_URL)
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
