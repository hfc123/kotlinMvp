package com.example.danie.test.mvp.presenter

import android.util.Log
import com.example.danie.test.base.BasePresenter

import com.example.danie.test.mvp.contract.FenLeiDetailContract
import com.example.danie.test.mvp.model.FenLeiDetailModel

class FenLeiDetaiPresenter:BasePresenter(),FenLeiDetailContract.Presenter<FenLeiDetailContract.View>{
    var url:String=""
    override fun getMore() {
        if (url==""){
            ( mRootView as FenLeiDetailContract.View ).setMore(null)
            return
        }
        val compose=model.getMore(url).subscribe({
            url=it.nextPageUrl
            ( mRootView as FenLeiDetailContract.View ).setMore(it)
        },{ Log.e("error","error",it)})
        addSubscription(compose)
    }

    override fun getFenLeiDetai(id: Long) {
       val compose= model.getFenLeiDetailData(id).subscribe({
           url=it.nextPageUrl
           ( mRootView as FenLeiDetailContract.View ).setFenLeiDetai(it)
       },{
           Log.e("error","error",it)
       })
        addSubscription(compose)
    }

    val  model by lazy {
        FenLeiDetailModel()
    }

}