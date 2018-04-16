package com.example.danie.test.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.danie.test.R
import com.example.danie.test.base.BaseFragment
import com.example.danie.test.mvp.contract.FollowContract
import com.example.danie.test.mvp.model.bean.HomeBean
import com.example.danie.test.mvp.presenter.FollowPresenter
import com.example.danie.test.ui.adapter.FollowAdapter
import kotlinx.android.synthetic.main.followlayout.*
//videoCollectionWithBrief
class FollowFragment : BaseFragment<FollowPresenter>() ,FollowContract.View{
    override fun setFirstData(issue:  HomeBean.Issue) {
        itemList.clear()
        issue.itemList.forEach {
         item->   if ( item.type=="videoCollectionWithBrief"){
            itemList.add(item)
        }
        }
        follow_recycler.adapter.notifyDataSetChanged()
    }

    override fun setMoreData(issue: HomeBean.Issue) {
        issue.itemList.forEach {
            item->   if ( item.type=="videoCollectionWithBrief"){
            itemList.add(item)
        }
        }
        //需要调用这个代码声明loadmore已经完成可以继续请求更多数据
        adapter.loadMoreComplete()
        adapter.notifyDataSetChanged()
    }

    override fun showError(msg: String, errorCode: Int) {
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
        //停止刷新
        swipefollow.isRefreshing=false
    }

    override fun lazyLoad() {
        mPresenter?.getFirstdata()
    }
        val itemList by lazy {
            ArrayList<HomeBean.Issue.Item>()
        }
    val adapter by lazy { FollowAdapter(R.layout.item_follow,itemList) }
    override fun initviews() {
        //下拉刷新
        swipefollow.setOnRefreshListener {
            mPresenter?.getFirstdata()
        }
        follow_recycler.layoutManager=LinearLayoutManager(activity,LinearLayout.VERTICAL,false)
        follow_recycler.adapter=adapter;
        //上拉加载
        adapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
            mPresenter?.getmoredata()
        },follow_recycler)

    }


    override fun layoutid(): Int {
     return R.layout.followlayout
    }

}