package com.example.danie.test.mvp.presenter

import android.util.Log
import com.example.danie.test.base.BasePresenter
import com.example.danie.test.mvp.contract.SearchContract
import com.example.danie.test.mvp.model.SearchModel

class SearchPresenter :BasePresenter(),SearchContract.Presenter<SearchContract.View> {
    var url :String =""
    override fun loadmore() {
        val mView=mRootView as SearchContract.View
        var compose=model.getMoreData(url).subscribe({
            url=it.nextPageUrl
            mView.setHotSearchWord(it)
        },{
            Log.e("error","error",it)
        })
        addSubscription(compose)
    }

    val model by lazy {
        SearchModel()
    }

    override fun getHotWords() {
        val mView=mRootView as SearchContract.View
        var compose= model.getHotWord().subscribe({
               mView.setHotWords(it)
           },{
               Log.e("error","error",it)
           })
        addSubscription(compose)
    }

    override fun getHotSerchWord(hotWord: String) {
        val mView=mRootView as SearchContract.View
        var compose=model.getSearchData(hotWord).subscribe({
            url=it.nextPageUrl
            mView.setHotSearchWord(it)
        },{
            Log.e("error","error",it)
        })
        addSubscription(compose)
    }
}