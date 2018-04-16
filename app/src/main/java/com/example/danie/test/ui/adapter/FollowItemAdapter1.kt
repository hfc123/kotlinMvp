package com.example.danie.test.ui.adapter

import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.danie.test.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.glide.GlideApp
import com.example.danie.test.R
import com.example.danie.test.changedate

class FollowItemAdapter1 :BaseQuickAdapter<HomeBean.Issue.Item,BaseViewHolder> {
    constructor(layoutResId: Int, data: MutableList<HomeBean.Issue.Item>?) : super(layoutResId, data)
    constructor(data: MutableList<HomeBean.Issue.Item>?) : super(data)
    constructor(layoutResId: Int) : super(layoutResId)

    override fun convert(helper: BaseViewHolder?, item: HomeBean.Issue.Item?) {
        helper?.setText(R.id.tv_title,item?.data?.title)
        var tagname:String="#"
        item?.data?.tags?.forEach {
            tagname+=it?.name+"/"
        }
        helper?.setText(R.id.tv_tag,tagname+changedate(item?.data?.duration))
       var imafeview= helper?.getView<ImageView>(R.id.iv_cover_feed)
        GlideApp.with(mContext)
                .load(item?.data?.cover?.feed!!)
                .placeholder(R.drawable.placeholder_banner)
                .transition(DrawableTransitionOptions().crossFade())
                .into(imafeview)
    }
}