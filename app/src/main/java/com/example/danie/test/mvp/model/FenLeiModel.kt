package com.example.danie.test.mvp.model

import com.example.danie.test.mvp.model.bean.FenLeiBean
import com.example.danie.test.net.RetrofitManager
import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

class FenLeiModel {

    fun getFenLeiData():Observable<ArrayList<FenLeiBean>>{

        return RetrofitManager.service.getFenLeiData().compose(SchedulerUtils.ioToMain())
    }
}