package com.example.danie.test.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.danie.test.R
import com.example.danie.test.base.BaseFragment
import com.example.danie.test.base.BasePresenter
import com.example.danie.test.setIndicator
import com.example.danie.test.ui.adapter.DiscoveryAdapter
import kotlinx.android.synthetic.main.discoverylayout2.*
import kotlin.reflect.KProperty

/**
 * Created by Administrator on 2018/4/10 0010.
 */
class DiscoverFragment :BaseFragment<BasePresenter>() {

  override fun lazyLoad() {

  }
  val frags:ArrayList<Fragment> by lazy {
    ArrayList<Fragment>()
  }
  val titles:ArrayList<String> by lazy {
    ArrayList<String>().apply {
      this.add("关注")
      this.add("分类")
    }
  }
  override fun initviews() {
    var followFragment :FollowFragment=FollowFragment()
    var classIficationFragment:ClassIfication= ClassIfication()
    frags.add(followFragment)
    frags.add(classIficationFragment)
    discoverypager.adapter=DiscoveryAdapter(fragmentManager,frags,titles)
      discoverytabLayout.setupWithViewPager(discoverypager)
    setIndicator(discoverytabLayout,60,60)
  }

  override fun layoutid(): Int {
      return R.layout.discoverylayout2
  }


}


