package com.example.danie.test.ui.fragment

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.GridLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.danie.test.R
import com.example.danie.test.base.BaseFragment
import com.example.danie.test.base.BasePresenter
import com.example.danie.test.mvp.contract.FenLeiContract
import com.example.danie.test.mvp.model.bean.FenLeiBean
import com.example.danie.test.mvp.presenter.FenLeiPresenter
import com.example.danie.test.ui.activity.Scrolling_Detail_FenLei_Activity
import com.example.danie.test.ui.adapter.FenLeiAdapter
import kotlinx.android.synthetic.main.fragment_category.*

class ClassIfication :BaseFragment<FenLeiPresenter>(),FenLeiContract.View{
    override fun setFirstData(list: ArrayList<FenLeiBean>) {
        this.list.clear()
        this.list.addAll(list)
        adapter.notifyDataSetChanged()
    }

    override fun showError(msg: String, errorCode: Int) {

    }

    override fun showLoading() {
      multipleStatusView.showLoading()
    }

    override fun dismissLoading() {

    }

    override fun lazyLoad() {
        mPresenter?.getFirstdata()
    }
    val list :ArrayList<FenLeiBean> by lazy {
        ArrayList<FenLeiBean>()
    }
    val adapter by lazy {
        FenLeiAdapter(R.layout.item_category,list)
    }
    override fun initviews() {
        mRecyclerView.layoutManager=GridLayoutManager(activity,2)
        mRecyclerView.adapter=adapter
        adapter.setOnItemClickListener(object :BaseQuickAdapter.OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val intent=Intent(activity,Scrolling_Detail_FenLei_Activity::class.java)
                intent.putExtra("FENLEINAME",list.get(position))
                       // FENLEINAME
                startActivity(intent)
                }

        })
    }

    override fun layoutid(): Int {
        return    R.layout.fragment_category
    }

}