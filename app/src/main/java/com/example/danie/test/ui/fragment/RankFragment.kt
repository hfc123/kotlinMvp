package com.example.danie.test.ui.fragment

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.example.danie.test.R
import com.example.danie.test.base.BaseFragment
import com.example.danie.test.mvp.contract.RankContract
import com.example.danie.test.mvp.model.bean.HomeBean
import com.example.danie.test.mvp.presenter.RankPresenter
import com.example.danie.test.ui.adapter.RankAdapter
import kotlinx.android.synthetic.main.fragment_rank.*
import kotlinx.android.synthetic.main.homelayout.*

/**
 * Created by Administrator on 2018/4/16 0016.
 */
class RankFragment : BaseFragment<RankPresenter>(), RankContract.View {

  override fun showLoading() {
   // mLayoutStatusView?.showLoading()
  }


  override fun dismissLoading() {
  }
  var url :String?=""
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    url=arguments?.getString("url")
    Log.e("error",url)

  }
  override fun setrankdata(issue: HomeBean.Issue) {
    issue.itemList.forEach {


      itemList.add(it)

    }
    //需要调用这个代码声明loadmore已经完成可以继续请求更多数据
    //  adapter.loadMoreComplete()
    adapter.notifyDataSetChanged()
  }
  val itemList by lazy {
    ArrayList<HomeBean.Issue.Item>()
  }
  val adapter by lazy {
    RankAdapter(R.layout.item_category_detail,itemList)
  }
  override fun setmoreData(issue: HomeBean.Issue) {

  }

  override fun lazyLoad() {
    mPresenter?.getRankData(url!!)
  }

  override fun initviews() {

    mRecyclerView.layoutManager= LinearLayoutManager(activity,LinearLayout.VERTICAL,false)
    mRecyclerView.adapter=adapter

    adapter.setOnItemClickListener { adapter, view, position ->
    }
  }

  override fun layoutid(): Int {
    return R.layout.fragment_rank
  }
}
