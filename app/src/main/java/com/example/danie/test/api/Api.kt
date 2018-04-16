package com.example.danie.test.api

import com.example.danie.test.mvp.model.bean.FenLeiBean
import com.example.danie.test.mvp.model.bean.HomeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {
    //获取首页数据
    @GET("v2/feed?")
    fun gethomedata(@Query("num")num:Int):Observable<HomeBean>
    /**
     * 根据 nextPageUrl 请求数据下一页数据
     */
    @GET
    fun getMoreHomeData(@Url url: String): Observable<HomeBean>
    /*
    * 获取关注
    * */
    @GET("v4/tabs/follow")
    fun getFollowFistData():Observable<HomeBean.Issue>
    @GET
    fun getMoreFollowData (@Url url: String):Observable<HomeBean.Issue>
   /*
    *获取分类
    */
    @GET("v4/categories")
    fun getFenLeiData():Observable<ArrayList<FenLeiBean>>
}
