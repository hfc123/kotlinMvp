package com.example.danie.test.base

interface IBasePresenter<in V :IBaseView> {
    fun attachView(mRootView: V)

    fun detachView()
}