package com.example.danie.test.mvp.presenter

import com.example.danie.test.base.BasePresenter
import com.example.danie.test.mvp.contract.HomeContract

class HomePresenter :BasePresenter<HomeContract.View>(),HomeContract.Presenter {

    var homeview: HomeContract.View? =null
    override fun requestHomeData(num: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadMoreData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}