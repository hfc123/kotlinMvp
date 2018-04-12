package com.example.danie.test.base

interface IBasePresenter<T> {
    fun attachView(mRootView: T)

    fun detachView()
}