package com.example.danie.test.mvp.contract

import com.example.danie.test.base.IBaseView
import com.example.danie.test.mvp.model.bean.RankBean

/**
 * Created by Administrator on 2018/4/16 0016.
 */
interface HotContract {
  interface  View:IBaseView{

    fun setHotData(hotBean:RankBean)
  }
  interface Prensenter<View>{
    fun getHotBean()
  }
}
