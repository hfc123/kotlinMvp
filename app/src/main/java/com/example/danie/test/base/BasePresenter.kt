package com.example.danie.test.base

import com.example.danie.test.mvp.model.HomeModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter :IBasePresenter<IBaseView> {


    var mRootView:IBaseView?=null
                private set

    private var compositeDisposable = CompositeDisposable()
    override fun attachView(mRootView: IBaseView) {
        this.mRootView = mRootView
    }

    override fun detachView() {
        mRootView = null

        //保证activity结束时取消所有正在执行的订阅
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }    }
    private val isViewAttached: Boolean
        get() = mRootView != null

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

}

class MvpViewNotAttachedException internal constructor(): RuntimeException("Please call IPresenter.attachView(IBaseView) before\" + \" requesting data to the IPresenter")
