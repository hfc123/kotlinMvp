package com.example.danie.test.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.lang.StringBuilder

class DiscoveryAdapter: FragmentPagerAdapter {
   var  fms:ArrayList<Fragment> = ArrayList<Fragment>()
   var  titlelist:ArrayList<String> = ArrayList<String>()
    constructor(fm: FragmentManager?,fragments:ArrayList<Fragment>, titles:ArrayList<String>) : super(fm){
        fms=fragments
        titlelist=titles
    }

    override fun getItem(position: Int): Fragment {

        return fms.get(position)
    }

    override fun getCount(): Int {
        return fms.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titlelist.get(position)
    }
}