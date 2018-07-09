package com.weiche.module_common.http;

import com.weiche.module_common.utils.Utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ${chewei} on 2018/7/9.
 * params:2018/7/9
 * Retrofit管理类
 */

public class RetrofitHelper {

    private static OkHttpClient okHttpClient = null;

    private static Retrofit getRetrofit(String url){
        initOkHttp();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    private static void initOkHttp() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
//            File cacheFile = new File(Constants.PATH_CACHE);
//            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
            Interceptor cacheInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request.Builder builder;
                    if (!Utils.isNetworkConnected()) {
                        builder = request.newBuilder()
                                .cacheControl(CacheControl.FORCE_NETWORK);
                    } else {
                        builder = request.newBuilder()
                                .addHeader("Accept-Language", "zh,zh-cn");
                        builder.addHeader("Cache", "no-cache");
                        //动态设置请求头
//                        String sessionId = App.getSessionID();
//                        String jtUserID = App.getUserID();
//                        if (!StringUtils.isEmpty(sessionId)) {
//                            builder.addHeader("sessionID", sessionId);
//                        }
//                        if (!TextUtils.isEmpty(jtUserID)) {
//                            builder.addHeader("jtUserID", jtUserID);
//                        }
//                        if (!TextUtils.isEmpty(EUtil.getVersionName(App.getApplicationConxt()))) {
//                            String version = "android_" + EUtil.getVersionName(App.getApplicationConxt());
//                            builder.addHeader("gtversion", version);
//                        }
                    }
                    request = builder.build();
                    Response response = chain.proceed(request);

                    if (Utils.isNetworkConnected()) {
//                        int maxAge = 0;
                        // 有网络时, 不缓存, 最大保存时长为0
                        response.newBuilder()
                                .header("Cache-Control", "no-cache")
                                .removeHeader("Pragma")
                                .build();
                    } else {
                        // 无网络时，设置超时为4周
//                        int maxStale = 60 * 60 * 24 * 28;
//                        response.newBuilder()
//                                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                                .removeHeader("Pragma")
//                                .build();


                        response.newBuilder()
                                .header("Cache-Control", "no-cache")
                                .removeHeader("Pragma")
                                .build();
                    }
                    return response;
                }
            };
            //设置缓存
            builder.addNetworkInterceptor(cacheInterceptor);
            builder.addInterceptor(cacheInterceptor);
            //builder.cache(cache);
            //设置超时
            builder.connectTimeout(20, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            //错误重连
            builder.retryOnConnectionFailure(true);
            okHttpClient = builder.build();
        }
    }

}
