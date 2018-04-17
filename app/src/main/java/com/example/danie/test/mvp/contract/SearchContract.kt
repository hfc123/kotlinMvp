package com.example.danie.test.mvp.contract

import com.example.danie.test.base.IBaseView
import com.example.danie.test.mvp.model.bean.HomeBean

interface SearchContract {

    interface View:IBaseView{
        fun setHotWords(hotWords:ArrayList<String>)
        fun setHotSearchWord(issue: HomeBean.Issue)
    }
    interface Presenter<View>{
        fun getHotWords()
        fun getHotSerchWord(hotWord :String)
        fun loadmore()
    }
}