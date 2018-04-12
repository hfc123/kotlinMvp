package com.example.danie.test.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.classic.common.MultipleStatusView
import com.example.danie.test.utils.TUtil
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<T:BasePresenter>: Fragment() {
  var mPresenter: T? = null
  /*
         *多种状态view的切换
         * */
  var mLayoutStatusView: MultipleStatusView?=null;
  /**
   * 视图是否加载完毕
   */
  private var isViewPrepare = false
  /**
   * 数据是否加载过了
   */
  private var hasLoadData = false
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initPresenter()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(layoutid(),null)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    isViewPrepare = true
    initviews()
    lazyLoadDataIfPrepared()
    //多种状态切换的view 重试点击事件
    mLayoutStatusView?.setOnClickListener(mRetryClickListener)
  }

  open val mRetryClickListener:View.OnClickListener= View.OnClickListener {   lazyLoad() }
  //懒加载
  abstract fun lazyLoad()

   fun lazyLoadDataIfPrepared(){
    if (userVisibleHint && isViewPrepare && !hasLoadData) {
      lazyLoad()
      hasLoadData = true
    }
  }
  override fun setUserVisibleHint(isVisibleToUser: Boolean) {
    super.setUserVisibleHint(isVisibleToUser)
    if (isVisibleToUser) {
      lazyLoadDataIfPrepared()
    }
  }
  abstract fun initviews()

  abstract fun layoutid(): Int

  override fun onDestroy() {
    super.onDestroy()
    //presenter解绑定
    mPresenter?.detachView()
  }
  //presenter 初始化
  private fun initPresenter() {
    if (this is IBaseView &&
            this.javaClass.genericSuperclass is ParameterizedType &&
            (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.size > 0) {

      mPresenter = TUtil.getT(this, 0)
      mPresenter!!.attachView(this)
      Log.e("初始化", this.javaClass.simpleName + " presenter 初始化成功...")
    }
  }
}
