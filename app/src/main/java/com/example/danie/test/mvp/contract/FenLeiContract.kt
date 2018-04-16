package com.example.danie.test.mvp.contract

import com.example.danie.test.base.IBaseView
import com.example.danie.test.mvp.model.bean.FenLeiBean
import com.example.danie.test.mvp.model.bean.HomeBean

interface FenLeiContract {
    interface View: IBaseView {
        /**
         * 设置第一次请求的数据
         */
        fun setFirstData(list:ArrayList<FenLeiBean>)


        /**
         * 显示错误信息
         */
        fun showError(msg: String,errorCode:Int)
    }

    interface  Presenter<View>{
        fun getFirstdata()
    }
}