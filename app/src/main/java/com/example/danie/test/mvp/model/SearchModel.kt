package com.example.danie.test.mvp.model

import com.example.danie.test.mvp.model.bean.HomeBean
import com.example.danie.test.net.RetrofitManager
import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

class SearchModel {

    fun getHotWord(): Observable<ArrayList<String>> {
        return RetrofitManager.service.getHotWords().compose(SchedulerUtils.ioToMain())
    }
    fun getSearchData(url:String): Observable<HomeBean.Issue>{
        return RetrofitManager.service.getSearchData(url).compose(SchedulerUtils.ioToMain())
    }
    fun getMoreData (url: String): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getRankdata(url).compose(SchedulerUtils.ioToMain())
    }
}