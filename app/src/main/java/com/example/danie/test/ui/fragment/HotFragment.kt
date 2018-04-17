package com.example.danie.test.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.danie.test.R
import com.example.danie.test.base.BaseFragment
import com.example.danie.test.base.BasePresenter
import com.example.danie.test.mvp.contract.HotContract
import com.example.danie.test.mvp.model.bean.RankBean
import com.example.danie.test.mvp.presenter.HotPresenter
import com.example.danie.test.ui.adapter.DiscoveryAdapter

import kotlinx.android.synthetic.main.fragment_hot.*


/**
 * Created by Administrator on 2018/4/10 0010.
 */
class HotFragment : BaseFragment<HotPresenter>() ,HotContract.View{
  override fun showLoading() {
    //mLayoutStatusView?.showLoading()
  }

  override fun dismissLoading() {
  }
  val fragments :ArrayList<Fragment>  by lazy {
    ArrayList<Fragment>()
  }
  val titles :ArrayList<String> by lazy {
    ArrayList<String>()
  }
  override fun setHotData(hotBean: RankBean) {
    //mLayoutStatusView?.showContent()
    hotBean.getTabInfo()?.tabList?.forEach {
      var fragment=RankFragment()
      var bundle =Bundle()
      bundle.putString("url",it.apiUrl!!)
      fragment.setArguments(bundle)
      titles.add(it.name!!)
      fragments.add(fragment)
    }

    mViewPager.adapter= DiscoveryAdapter(fragmentManager,fragments,titles)
    mTabLayout.setupWithViewPager(mViewPager)

  }

  override fun lazyLoad() {
    mPresenter?.getHotBean()
  }

  override fun initviews() {


  }

  override fun layoutid(): Int {
      return R.layout.fragment_hot
  }
}
