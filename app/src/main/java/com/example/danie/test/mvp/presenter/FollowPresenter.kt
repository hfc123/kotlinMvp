package com.example.danie.test.mvp.presenter

import android.util.Log
import com.example.danie.test.base.BasePresenter
import com.example.danie.test.base.IBasePresenter
import com.example.danie.test.mvp.contract.FollowContract
import com.example.danie.test.mvp.model.FollowModel
import org.jetbrains.annotations.Contract

class FollowPresenter :FollowContract.FollowPresenter<FollowContract.View>,BasePresenter(){
    private val model:FollowModel by lazy {
        FollowModel()
    }
    var nextPageUrl:String=""
    override fun getFirstdata() {
        checkViewAttached()
        val  mView=mRootView as FollowContract.View
        val disposable=model.requestFistFollowData().subscribe({
            nextPageUrl= it.nextPageUrl
            mView?.setFirstData(it)
        },{
          //  mView.showError(it.message)
            Log.e("error", "error",it)
        })
        addSubscription(disposable)
    }

    override fun getmoredata() {
        checkViewAttached()
        val  mView=mRootView as FollowContract.View
        val disposable=model.requestMoreFollowData(nextPageUrl).subscribe({
            nextPageUrl= it.nextPageUrl
            mView?.setMoreData(it)
        },{
            //  mView.showError(it.message)
            Log.e("error", "error",it)
        })
        addSubscription(disposable)
    }
}