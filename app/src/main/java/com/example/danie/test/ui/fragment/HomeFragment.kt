package com.example.danie.test.ui.fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.danie.test.R
import com.example.danie.test.base.BaseFragment
import com.example.danie.test.base.BasePresenter
import com.example.danie.test.base.IBaseView
import com.example.danie.test.mvp.contract.HomeContract
import com.example.danie.test.mvp.model.bean.HomeBean
import com.example.danie.test.mvp.presenter.HomePresenter
import com.example.danie.test.ui.adapter.HomeAdapter
import kotlinx.android.synthetic.main.homelayout.*

/**
 * Created by Administrator on 2018/4/10 0010.
 */
class HomeFragment : BaseFragment<HomePresenter>(),HomeContract.View {

  override fun setHomeData(homeBean: HomeBean) {


  }

  override fun setMoreData(itemList:  ArrayList<HomeBean.Issue.Item>) {


  }

  override fun showError(msg: String, errorCode: Int) {
  }

  override fun showLoading() {
    mLayoutStatusView?.showLoading()
  }

  override fun dismissLoading() {

  }

  override fun lazyLoad() {

  }
  private val linearLayoutManager:LinearLayoutManager by lazy {
    LinearLayoutManager(activity,LinearLayout.VERTICAL,false)
  }
  override fun initviews() {
    mLayoutStatusView=multipleStatusView
   // mPresenter?.attachView(this)
    homerecycler.layoutManager=linearLayoutManager
    homerecycler.adapter=HomeAdapter(ArrayList<MultiItemEntity>())
    homerecycler.itemAnimator=DefaultItemAnimator()
  }

  override fun layoutid(): Int {
    return R.layout.homelayout
  }


}
