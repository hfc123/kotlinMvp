package com.example.danie.test.mvp.contract

import com.example.danie.test.base.IBaseView
import com.example.danie.test.mvp.model.bean.HomeBean

/**
 * Created by Administrator on 2018/4/17 0017.
 */
interface VideoDetailContract {

  interface View:IBaseView{
    fun setXiangGuan(issue: HomeBean.Issue)
  }
  interface Presenter<View>{
    fun getXiangGuan(id:Long)
  }
}
