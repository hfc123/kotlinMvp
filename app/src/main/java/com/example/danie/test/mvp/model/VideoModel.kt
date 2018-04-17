package com.example.danie.test.mvp.model

import com.example.danie.test.mvp.model.bean.HomeBean
import com.example.danie.test.net.RetrofitManager
import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Administrator on 2018/4/17 0017.
 */
class VideoModel {

  fun getRelatedData(id:Long): Observable<HomeBean.Issue>{
    return  RetrofitManager.service.getRelatedData(id).compose(SchedulerUtils.ioToMain())
  }
}
