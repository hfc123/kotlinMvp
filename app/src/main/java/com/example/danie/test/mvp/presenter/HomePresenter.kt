package com.example.danie.test.mvp.presenter

import android.util.Log
import android.widget.Toast
import com.example.danie.test.base.BasePresenter
import com.example.danie.test.base.IBaseView
import com.example.danie.test.mvp.contract.HomeContract
import com.example.danie.test.mvp.model.HomeModel
import com.example.danie.test.mvp.model.bean.HomeBean
import com.example.danie.test.ui.fragment.HomeFragment

class HomePresenter : BasePresenter() ,HomeContract.Presenter<HomeContract.View> {

    var nextPageUrl:String?=null
    private val homemodel : HomeModel by lazy {
      HomeModel()
    }

  //  mRootView  = mRootView as HomeContract.View

  var count:Int=0
  override fun requestHomeData(num: Int) {
      // 检测是否绑定 View
      checkViewAttached()
      var bannerHomeBean:HomeBean?=null
      val disposable= homemodel.requestHomeData(num).flatMap({
        homeBean ->
        val banneritemlist=homeBean.issueList[0].itemList
        banneritemlist.filter { item -> item.type=="banner2"|| item.type=="horizontalScrollCard" }
          .forEach { item->banneritemlist.remove(item) }
        bannerHomeBean = homeBean //记录第一页是当做 banner 数据
        count=banneritemlist.size
        //根据 nextPageUrl 请求下一页数据
        homemodel.requestMoreData(homeBean.nextPageUrl)
      }).subscribe({
       homebeean->
        nextPageUrl= homebeean.nextPageUrl


        (mRootView as HomeContract.View)?.apply {
          dismissLoading()
          //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
          val newBannerItemList = homebeean.issueList[0].itemList

          newBannerItemList.filter { item ->
            item.type=="banner2"||item.type=="horizontalScrollCard"
          }.forEach{ item ->
            //移除 item
            newBannerItemList.remove(item)
          }
          // 重新赋值 Banner 长度
          bannerHomeBean!!.issueList[0].count = bannerHomeBean!!.issueList[0].itemList.size

          //赋值过滤后的数据 + banner 数据
          bannerHomeBean?.issueList!![0].itemList.addAll(newBannerItemList)
          bannerHomeBean?.issueList!![0].count  =count
          setHomeData(bannerHomeBean!!)
        }
      },{ t ->//errr
        mRootView?.apply {
          dismissLoading()
          Log.e("error","error",t)
        }})
      addSubscription(disposable)
    }

    override fun loadMoreData() {
      checkViewAttached()
     /* mRootView?.showLoading()*/
      homemodel.requestMoreData(nextPageUrl!!).subscribe({
        homebean->
        (mRootView as HomeContract.View)?.apply{
          nextPageUrl=homebean.nextPageUrl
            dismissLoading()
          var itemlist=homebean.issueList[0].itemList
          itemlist.filter { item -> item.type=="banner2"||item.type=="horizontalScrollCard" }.forEach {
           item->
            itemlist.remove(item)
          }
            val itemlistcount=itemlist.size
            Log.e("succsess","succsess:::$itemlistcount")
          setMoreData(itemlist)
        }

      },{
        t->//error
        mRootView?.apply {
          mRootView?.dismissLoading()
          Log.e("error","error",t)
        }
      })
    }
}
