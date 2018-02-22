package com.wangshifei.douyin;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 墨羽 on 2018/2/22.
 */

public class RetrofitHelper {

    private static volatile Retrofit retrofit;

    public RetrofitHelper() {

    }

    public static  Retrofit getRetrofit(String baseurl){
        if(retrofit == null){
            synchronized (RetrofitHelper.class){
                if(retrofit == null){
                    OkHttpClient client = new OkHttpClient.Builder()
                            .build();
                    retrofit = new Retrofit.Builder()
                            .baseUrl(baseurl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(client)
                            .build();
                }
            }
        }
        return retrofit;
    }
}
