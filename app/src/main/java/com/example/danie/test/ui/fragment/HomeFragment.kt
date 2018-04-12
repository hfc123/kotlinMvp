package com.example.danie.test.ui.fragment
import com.example.danie.test.R
import com.example.danie.test.base.BaseFragment
import com.example.danie.test.mvp.contract.HomeContract
import com.example.danie.test.mvp.model.bean.HomeBean
import kotlinx.android.synthetic.main.homelayout.*

/**
 * Created by Administrator on 2018/4/10 0010.
 */
class HomeFragment : BaseFragment(),HomeContract.View {

  override fun setHomeData(homeBean: HomeBean) {


  }

  override fun setMoreData(itemList:  ArrayList<HomeBean.Issue.Item>) {


  }

  override fun showError(msg: String, errorCode: Int) {
  }

  override fun showLoading() {
    mLayoutStatusView?.showLoading()
  }

  override fun dismissLoading() {

  }

  override fun lazyLoad() {

  }

  override fun initviews() {
    mLayoutStatusView=multipleStatusView

  }

  override fun layoutid(): Int {
    return R.layout.homelayout
  }


}
