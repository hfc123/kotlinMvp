package com.example.danie.test.mvp.contract

import com.example.danie.test.base.IBaseView
import com.example.danie.test.mvp.model.bean.HomeBean

interface FenLeiDetailContract {
    interface View :IBaseView{
        fun setFenLeiDetai(issue: HomeBean.Issue)
        fun setMore(issue: HomeBean.Issue?)
    }
    interface Presenter<View>{
        fun getFenLeiDetai(id:Long)
        fun getMore()
    }
}