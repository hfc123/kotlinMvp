package com.example.danie.test.api

import com.example.danie.test.mvp.model.bean.FenLeiBean
import com.example.danie.test.mvp.model.bean.HomeBean
import com.example.danie.test.mvp.model.bean.RankBean
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
    /**
     * 热门搜索词
     */
    @GET("v3/queries/hot")
    fun getHotWord():Observable<ArrayList<String>>

  /**
   * 获取全部排行榜的Info（包括，title 和 Url）
   */
     @GET("v4/rankList")
      fun getRankList():Observable<RankBean>
  /*
  * 根据url获取bean类
  * */
      @GET
      fun  getRankdata(@Url url: String):Observable<HomeBean.Issue>/*
  * 根据url获取bean类
  * */
      @GET("v3/queries/hot")
      fun  getHotWords():Observable<ArrayList<String>>
    /**
     * 获取搜索信息
     */
    @GET("v1/search?&num=10&start=10")
    fun getSearchData(@Query("query") query :String) : Observable<HomeBean.Issue>
}
