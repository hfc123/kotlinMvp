package com.example.danie.test.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.danie.test.R
import com.example.danie.test.base.BaseFragment
import com.example.danie.test.base.BasePresenter

/**
 * Created by Administrator on 2018/4/10 0010.
 */
class MineFragment :BaseFragment<BasePresenter>() {
  override fun lazyLoad() {

  }

  override fun initviews() {
  }

  override fun layoutid(): Int {
    return R.layout.minelayout
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    val textview: TextView = TextView(activity)
    textview.setText("4")

    return textview
  }
}
