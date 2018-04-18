package com.example.danie.test.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_scrolling.*
import com.example.danie.test.R
import com.example.danie.test.base.BaseActivity
import com.example.danie.test.base.BasePresenter
import com.example.danie.test.goVideoActivity
import com.example.danie.test.mvp.contract.FenLeiDetailContract
import com.example.danie.test.mvp.model.bean.FenLeiBean
import com.example.danie.test.mvp.model.bean.HomeBean
import com.example.danie.test.mvp.presenter.FenLeiDetaiPresenter
import com.example.danie.test.ui.adapter.RankAdapter
import com.hazz.kotlinmvp.glide.GlideApp

class Scrolling_Detail_FenLei_Activity : BaseActivity<FenLeiDetaiPresenter>(), FenLeiDetailContract.View {

    var itembean :FenLeiBean?=null

    val itemList by lazy {
        ArrayList<HomeBean.Issue.Item>()
    }
    val adapter by lazy {
        RankAdapter(R.layout.item_category_detail,itemList)
    }
    //获取更多
    override fun setMore(issue: HomeBean.Issue?) {
        if (issue!=null){
            itemList.addAll(issue.itemList)
        }
        adapter.loadMoreComplete()
        adapter.notifyDataSetChanged()
    }

    override fun setFenLeiDetai(issue: HomeBean.Issue) {
        itemList.clear()
        itemList.addAll(issue.itemList)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
    }

    override fun startnet() {
    mPresenter?.getFenLeiDetai(itembean?.getId()?.toLong()!!)
    }

    override fun initview() {
        //setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        //设置toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        toolbar_layout.title=itembean?.getName()
        toolbar_layout.setExpandedTitleColor(Color.WHITE) //设置还没收缩时状态下字体颜色
        toolbar_layout.setCollapsedTitleTextColor(Color.BLACK) //设置收缩后Toolbar上字体的颜色
       var a= itembean?.getDescription()
        tv_category_desc.setText("#$a#")
        FLD_mRecyclerView.layoutManager=LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        FLD_mRecyclerView.adapter=adapter
        adapter.setOnItemClickListener({ baseQuickAdapter: BaseQuickAdapter<Any, BaseViewHolder>, view: View, i: Int ->
            val imgview= adapter.getViewByPosition(FLD_mRecyclerView,i,R.id.iv_image) as ImageView
            goVideoActivity(this@Scrolling_Detail_FenLei_Activity,imgview,itemList.get(i))
        })
        adapter.setOnLoadMoreListener({
            mPresenter?.getMore()
        },FLD_mRecyclerView)

        GlideApp.with(this).load(itembean?.getHeaderImage())
                .placeholder(R.drawable.placeholder_banner)
                .transition(DrawableTransitionOptions().crossFade())
                .into(image)
    }

    override fun initdata() {
        itembean=intent.getSerializableExtra("FENLEINAME") as FenLeiBean
    }

    override fun layoutid(): Int =R.layout.activity_scrolling

    override fun isNeedLoadStatusBar(): Boolean =true

}
