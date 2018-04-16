package com.example.danie.test.mvp.presenter

import android.util.Log
import com.example.danie.test.base.BasePresenter
import com.example.danie.test.mvp.contract.FenLeiContract
import com.example.danie.test.mvp.model.FenLeiModel

class FenLeiPresenter : BasePresenter(),FenLeiContract.Presenter<FenLeiContract.View> {

    val  fenLeiModel by lazy {
        FenLeiModel()
    }
    override fun getFirstdata() {
        checkViewAttached()
        val  compose= fenLeiModel.getFenLeiData().subscribe({
            ( mRootView as FenLeiContract.View).setFirstData(it)
        },{
            Log.e("error","error",it)
        })
        addSubscription(compose)
    }
}