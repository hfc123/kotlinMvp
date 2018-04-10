package com.example.danie.test.mvp.model.bean

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * 配套底部导航栏使用的bean类
 * Created by Administrator on 2018/4/10 0010.
 */
class CostomBean (var title:String,var unselectid:Int,var selectid:Int):CustomTabEntity {
  override fun getTabUnselectedIcon(): Int {
    return unselectid
  }

  override fun getTabSelectedIcon(): Int {
    return selectid
  }

  override fun getTabTitle(): String {
    return title
  }
}
