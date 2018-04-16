package com.example.danie.test.mvp.contract

import com.example.danie.test.base.IBaseView
import com.example.danie.test.mvp.model.bean.HomeBean

interface FollowContract {
    interface View:IBaseView{
        /**
         * 设置第一次请求的数据
         */
        fun setFirstData(issue: HomeBean.Issue)

        /**
         * 设置加载更多的数据
         */
        fun setMoreData(itemList: HomeBean.Issue)

        /**
         * 显示错误信息
         */
        fun showError(msg: String,errorCode:Int)
    }

    interface  FollowPresenter<View>{
        fun getFirstdata()
        fun getmoredata()
    }

}