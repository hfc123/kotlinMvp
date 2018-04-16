package com.example.danie.test.ui.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.danie.test.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.glide.GlideApp
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.danie.test.R

class FollowAdapter :BaseQuickAdapter<HomeBean.Issue.Item,BaseViewHolder>{
    override fun convert(helper: BaseViewHolder?, item: HomeBean.Issue.Item?) {
        helper?.setText(R.id.tv_title,item?.data?.title?:"title")
        var desc:String =""
    /*    item?.data?.tags?.forEach {
            desc+=it.name?:"tagname"
        }*/
        //tag
        helper?.setText(R.id.tv_desc , item?.data?.header?.description?:"description")
        GlideApp.with(mContext).load(item?.data?.author?.icon?:"")
                .placeholder(R.mipmap.default_avatar)
                .transition(DrawableTransitionOptions().crossFade())
                .into( helper?.getView<ImageView>(R.id.iv_avatar))
       var  fl_recyclerView :RecyclerView?  = helper?.getView<RecyclerView>(R.id.fl_recyclerView)
       var adapter= FollowItemAdapter1(R.layout.item_follow_horizontal,item?.data?.itemList)
        fl_recyclerView?.layoutManager=LinearLayoutManager(mContext, LinearLayout.HORIZONTAL,false)
        fl_recyclerView?.adapter=adapter
        adapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                //  BaseQuickAdapter adapter, View view, int position
                //todo 点击事件
            }

        })
    }
 // var datas : ArrayList<HomeBean.Issue.Item>?=ArrayList<HomeBean.Issue.Item>()
    constructor(layoutResId: Int, data: MutableList<HomeBean.Issue.Item>?) : super(layoutResId, data)
    constructor(data: MutableList<HomeBean.Issue.Item>?) : super(data)
    constructor(layoutResId: Int) : super(layoutResId)
}