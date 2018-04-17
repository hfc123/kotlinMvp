package com.example.danie.test.mvp.presenter

import android.util.Log
import com.example.danie.test.base.BasePresenter
import com.example.danie.test.mvp.contract.HotContract
import com.example.danie.test.mvp.model.RankModel
import com.example.danie.test.mvp.model.bean.RankBean

/**
 * Created by Administrator on 2018/4/16 0016.
 */
class HotPresenter : BasePresenter(),HotContract.Prensenter<HotContract.View> {
  override fun getHotBean() {

    val mView= mRootView as HotContract.View
    mView.showLoading()
    val compose=hotModel.geturldata().subscribe({
      mView.setHotData(it)
      mView.dismissLoading()
    },{
      mView.dismissLoading()
      Log.e("error","error",it)
    })
    addSubscription(compose)
  }

  val  hotModel:RankModel by lazy {
    RankModel()
  }

}
