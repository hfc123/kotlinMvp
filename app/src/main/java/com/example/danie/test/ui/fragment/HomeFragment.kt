package com.example.danie.test.ui.fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.danie.test.R
import com.example.danie.test.base.BaseFragment
import com.example.danie.test.base.BasePresenter
import com.example.danie.test.base.IBaseView
import com.example.danie.test.mvp.contract.HomeContract
import com.example.danie.test.mvp.model.bean.HomeBean
import com.example.danie.test.mvp.presenter.HomePresenter
import com.example.danie.test.ui.adapter.HomeAdapter
import com.scwang.smartrefresh.header.MaterialHeader
import kotlinx.android.synthetic.main.homelayout.*

/**
 * Created by Administrator on 2018/4/10 0010.
 */
class HomeFragment : BaseFragment<HomePresenter>(),HomeContract.View {

  override fun setHomeData(homeBean: HomeBean) {
    homeBeanList.clear()
  //  homeBeanList.addAll()

    for ((index,value) in homeBean.issueList[0].itemList.withIndex()){
      if (index<homeBean.issueList[0].count){
        value.itemtype=0;
      }else{
        if (value.type == "textHeader"){
          value.itemtype=1;
        }else{
          value.itemtype=2;
        }
      }
    }
   var bannerlist= homeBean.issueList[0].itemList.filter { item -> item.itemtype==0 }
    homeBean.issueList[0].itemList.removeAll(bannerlist)
    homeBean.issueList[0].itemList.add(0,bannerlist.get(0))
    homeBean.issueList[0].itemList.get(0).bannerlist= bannerlist as ArrayList<HomeBean.Issue.Item>;
    homeBeanList.addAll(homeBean.issueList[0].itemList)
    homeAdapter.setsize(homeBean.issueList[0].count)
    homeAdapter.notifyDataSetChanged()
  }

  override fun setMoreData(itemList:  ArrayList<HomeBean.Issue.Item>) {
    for ((index,value) in itemList.withIndex()){
      if (value.type == "textHeader"){
        value.itemtype=1;
      }else{
        value.itemtype=2;
      }
    }
    homeAdapter.loadMoreEnd()
    //需要调用这个代码声明loadmore已经完成可以继续请求更多数据
    homeAdapter.loadMoreComplete()
    homeBeanList.addAll(itemList)
    homeAdapter.notifyDataSetChanged()
  }

  override fun showError(msg: String, errorCode: Int) {
  }

  override fun showLoading() {
    mLayoutStatusView?.showLoading()
  }

  override fun dismissLoading() {
    msmartrefresh.finishRefresh()
  }

  override fun lazyLoad() {
    mPresenter?.requestHomeData(1)
  }
  private val linearLayoutManager:LinearLayoutManager by lazy {
    LinearLayoutManager(activity,LinearLayout.VERTICAL,false)
  }

  val homeBeanList by lazy{
    ArrayList<MultiItemEntity>()

  }
  private var mMaterialHeader: MaterialHeader? = null
  val homeAdapter by lazy { HomeAdapter(homeBeanList) }
  override fun initviews() {
    mLayoutStatusView=multipleStatusView
   // mPresenter?.attachView(this)
    homerecycler.layoutManager=linearLayoutManager
    HomeAdapter(homeBeanList)
    homerecycler.adapter=homeAdapter
    homerecycler.itemAnimator=DefaultItemAnimator()
    homeAdapter.setEnableLoadMore(true)
    homeAdapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
      Log.e("ss","Sss")
      mPresenter?.loadMoreData()
    },homerecycler)
    msmartrefresh.setEnableHeaderTranslationContent(true)
    mMaterialHeader = msmartrefresh.refreshHeader as MaterialHeader?
    //打开下拉刷新区域块背景:
    mMaterialHeader?.setShowBezierWave(true)
    //设置下拉刷新主题颜色
    msmartrefresh.setPrimaryColorsId(R.color.color_light_black, R.color.color_title_bg)
    msmartrefresh.setOnRefreshListener {
      //刷新列表
      mPresenter?.requestHomeData(1)
    }
  }
 /* fun loadmoreend(){

  }*/
  override fun layoutid(): Int {
    return R.layout.homelayout
  }


}
