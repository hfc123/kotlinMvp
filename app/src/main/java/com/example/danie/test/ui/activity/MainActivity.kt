package com.example.danie.test.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import com.example.danie.test.R
import com.example.danie.test.base.BaseActivity
import com.example.danie.test.base.BasePresenter
import com.example.danie.test.base.IBaseView
import com.example.danie.test.mvp.model.bean.CostomBean
import com.example.danie.test.ui.fragment.DiscoverFragment
import com.example.danie.test.ui.fragment.HomeFragment
import com.example.danie.test.ui.fragment.HotFragment
import com.example.danie.test.ui.fragment.MineFragment
import com.flyco.tablayout.listener.CustomTabEntity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<BasePresenter<IBaseView>>() {
    private val mTitles = arrayOf("每日精选", "发现", "热门", "我的")
    private  val unSeclectMaps= intArrayOf(R.mipmap.ic_home_normal,R.mipmap.ic_discovery_normal,R.mipmap.ic_hot_normal,R.mipmap.ic_mine_normal)
    private  val SeclectMaps= intArrayOf(R.mipmap.ic_home_selected,R.mipmap.ic_discovery_selected,R.mipmap.ic_hot_selected,R.mipmap.ic_mine_selected)
    private var mHomeFragment: HomeFragment? = null
    private var mDiscoveryFragment: DiscoverFragment? = null
    private var mHotFragment: HotFragment? = null
    private var mMineFragment: MineFragment? = null
    private val fragments=ArrayList<Fragment>()
    private  val customlist=ArrayList<CustomTabEntity>()

   override fun startnet() {
      
    }

    override fun initview() {
        mColorId=R.color.color_translucent
      mHomeFragment= HomeFragment()
      mDiscoveryFragment= DiscoverFragment()
      mHotFragment= HotFragment()
      mMineFragment= MineFragment()
      fragments.add(mHomeFragment!!)
      fragments.add(mDiscoveryFragment!!)
      fragments.add(mHotFragment!!)
      fragments.add(mMineFragment!!)
      (0 until mTitles.size).mapTo(customlist){
        CostomBean(mTitles[it],unSeclectMaps[it],SeclectMaps[it])
      }
      tab_layout.setTabData(customlist,this,R.id.fragmentlayout,fragments)
    }

    override fun initdata() {
    }

    override fun layoutid(): Int {
        return R.layout.activity_main
    }

    override fun isNeedLoadStatusBar(): Boolean {
      return true
    }


}
