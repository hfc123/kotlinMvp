package com.example.danie.test.net

import com.example.danie.test.App
import com.example.danie.test.api.Api
import com.example.danie.test.api.UriConstant
import com.example.danie.test.utils.NetworkUtil
import com.example.danie.test.utils.Preference
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object  RetrofitManager {

    private var okHttpClient:OkHttpClient?=null
    private var retrofit:Retrofit?=null

    val service:Api by lazy {
        getretrofit()!!.create(Api::class.java)
    }
    private var token:String by Preference("token","")

    private fun getretrofit(): Retrofit? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        if (retrofit==null){
           synchronized(Retrofit::class.java){
               //设置拦截器
               val httpLoggingInterceptor=HttpLoggingInterceptor()
               httpLoggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
               //设置缓存
               val cacheFile=File(App.context.cacheDir,"cache")
               val cache=Cache(cacheFile,1024*1024*50)

               okHttpClient=OkHttpClient.Builder()
                       .addInterceptor(httpLoggingInterceptor)//log日志
                       .addInterceptor(addheadinterceptor())
                       .addInterceptor(addQueryParameterInterceptor())
                       .cache(cache)//缓存
                       .readTimeout(60L,TimeUnit.SECONDS)//
                       .writeTimeout(60L,TimeUnit.SECONDS)
                       .connectTimeout(60L,TimeUnit.SECONDS)
                       .build()
               retrofit=Retrofit.Builder()
                       .baseUrl(UriConstant.BASE_URL)
                       .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//rx
                       .addConverterFactory(GsonConverterFactory.create())
                       .build()
           }

        }
        return retrofit;

    }
    //设置头
    private fun addheadinterceptor():Interceptor{
        return Interceptor {
            chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                    // Provide your custom header here
                    .header("token", token)
                    .method(originalRequest.method(), originalRequest.body())
            val request = requestBuilder.build()
              chain.proceed(request)
        }
    }
    /**
     * 设置公共参数
     */
    private fun addQueryParameterInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val request: Request
            val modifiedUrl = originalRequest.url().newBuilder()
                    // Provide your custom parameter here
                    .addQueryParameter("phoneSystem", "")
                    .addQueryParameter("phoneModel", "")
                    .build()
            request = originalRequest.newBuilder().url(modifiedUrl).build()
            chain.proceed(request)
        }
    }
    //设置缓存
    private fun addCacheInterceptor():Interceptor{
        return Interceptor {
            chain ->
            var request=chain.request()
            if (!NetworkUtil.isNetworkAvailable(App.context)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
            }
            val response = chain.proceed(request)
            if (!NetworkUtil.isNetworkAvailable(App.context)){
                val maxAge = 0
                // 有网络时 设置缓存超时时间0个小时 ,意思就是不读取缓存数据,只对get有用,post没有缓冲
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Retrofit")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build()
            }else{
                // 无网络时，设置超时为4周  只对get有用,post没有缓冲
                val maxStale = 60 * 60 * 24 * 28
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("nyn")
                        .build()
            }
            response
        }
    }


}

