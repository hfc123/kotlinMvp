package com.example.danie.test.mvp.contract

import com.example.danie.test.base.IBaseView
import com.example.danie.test.mvp.model.bean.HomeBean

/**
 * Created by Administrator on 2018/4/16 0016.
 */
interface RankContract {

  interface View:IBaseView{
    fun setrankdata(issue:HomeBean.Issue)
    fun setmoreData(issue:HomeBean.Issue)
  }
  interface Prensenter<View>{
    fun getRankData(url:String)
    fun  getMoreRankData(url: String)
  }
}
