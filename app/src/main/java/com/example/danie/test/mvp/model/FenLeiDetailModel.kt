package com.example.danie.test.mvp.model

import com.example.danie.test.mvp.model.bean.HomeBean
import com.example.danie.test.net.RetrofitManager
import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

class FenLeiDetailModel  {

    fun getFenLeiDetailData(id : Long): Observable<HomeBean.Issue>{

        return RetrofitManager.service.getCategoryDetailList(id).compose(SchedulerUtils.ioToMain())
    }
    fun getMore(url:String):Observable<HomeBean.Issue>{

        return RetrofitManager.service.getMoreFollowData(url).compose(SchedulerUtils.ioToMain())
    }
}