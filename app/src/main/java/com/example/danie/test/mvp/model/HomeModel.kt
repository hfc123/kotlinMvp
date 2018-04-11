package com.example.danie.test.mvp.model

import com.example.danie.test.mvp.model.bean.HomeBean
import com.example.danie.test.net.RetrofitManager
import io.reactivex.Observable

class HomeModel {
    //获取首页数据
    fun requestHomeData(num:Int):Observable<HomeBean>{

        return RetrofitManager.service.gethomedata(num)
    }
    //获取更多数据
    fun requestMoreData(url:String):Observable<HomeBean>{
        return RetrofitManager.service.getMoreHomeData(url)
    }
}