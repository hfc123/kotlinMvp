package com.example.danie.test.mvp.presenter

import android.util.Log
import com.example.danie.test.base.BasePresenter
import com.example.danie.test.mvp.contract.HotContract
import com.example.danie.test.mvp.contract.RankContract
import com.example.danie.test.mvp.model.RankModel

/**
 * Created by Administrator on 2018/4/16 0016.
 */
class RankPresenter :BasePresenter(),RankContract.Prensenter<RankContract.View> {
  val model by lazy{
    RankModel()
  }
  override fun getRankData(url: String) {
val compose=model.getRankdata(url) .subscribe(
  {
    (mRootView as RankContract.View).setrankdata(it)
  }
  ,{
  Log.e("error","error",it)
}
)
  addSubscription(compose)
  }

  override fun getMoreRankData(url: String) {
   // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }


}
