package com.example.danie.test.mvp.model

import com.example.danie.test.mvp.model.bean.HomeBean
import com.example.danie.test.mvp.model.bean.RankBean
import com.example.danie.test.net.RetrofitManager
import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by Administrator on 2018/4/16 0016.
 */
class RankModel {

      fun geturldata(): Observable<RankBean>{
        return  RetrofitManager.service.getRankList().compose(SchedulerUtils.ioToMain())
      }
      fun getRankdata(url:String):Observable<HomeBean.Issue>{
        return RetrofitManager.service.getRankdata(url).compose(SchedulerUtils.ioToMain())
      }
}
