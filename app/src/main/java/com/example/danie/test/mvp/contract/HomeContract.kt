package com.example.danie.test.mvp.contract

import com.example.danie.test.base.IBaseView
import com.example.danie.test.mvp.model.bean.HomeBean

interface HomeContract  {

    interface View:IBaseView{
        /**
         * 设置第一次请求的数据
         */
        fun setHomeData(homeBean: HomeBean)

        /**
         * 设置加载更多的数据
         */
        fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>)

        /**
         * 显示错误信息
         */
        fun showError(msg: String,errorCode:Int)

    }
    interface Presenter<View>{
        /**
         * 获取首页精选数据
         */
        fun requestHomeData(num: Int)
        //fun initdata()
        /**
         * 加载更多数据
         */
        fun loadMoreData(url: String)
    }
}
