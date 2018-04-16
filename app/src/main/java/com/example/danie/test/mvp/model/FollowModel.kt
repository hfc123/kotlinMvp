package com.example.danie.test.mvp.model

import com.example.danie.test.mvp.model.bean.HomeBean
import com.example.danie.test.net.RetrofitManager
import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

class FollowModel {
    fun  requestFistFollowData():Observable<HomeBean.Issue>{
            return RetrofitManager.service.getFollowFistData().compose(SchedulerUtils.ioToMain())
        }
    fun  requestMoreFollowData(url :String):Observable<HomeBean.Issue>{
        return RetrofitManager.service.getMoreFollowData(url).compose(SchedulerUtils.ioToMain())
    }
}