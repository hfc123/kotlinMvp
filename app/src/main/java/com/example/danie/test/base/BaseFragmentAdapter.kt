package com.example.danie.test.base

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

 class BaseFragmentAdapter: FragmentPagerAdapter {
     override fun getItem(position: Int): Fragment {
         return fragmentList?.get(position)!!
     }

     override fun getCount(): Int {
         return mTitles?.size!!
     }

     private var fragmentList: List<Fragment>? = ArrayList()
     private var mTitles: List<String>? = null

     constructor(fm: FragmentManager, fragmentList: List<Fragment>) : super(fm) {
         this.fragmentList = fragmentList
     }

     constructor(fm: FragmentManager, fragmentList: List<Fragment>, mTitles: List<String>) : super(fm) {
         this.mTitles = mTitles
         setFragments(fm, fragmentList, mTitles)
     }

     //刷新fragment
     @SuppressLint("CommitTransaction")
     private fun setFragments(fm: FragmentManager, fragments: List<Fragment>, mTitles: List<String>) {
         this.mTitles = mTitles
         if (this.fragmentList != null) {
             val ft = fm.beginTransaction()
             for (f in this.fragmentList!!) {
                 ft!!.remove(f)
             }
             ft!!.commitAllowingStateLoss()
             fm.executePendingTransactions()
         }
         this.fragmentList = fragments
         notifyDataSetChanged()
     }

 }