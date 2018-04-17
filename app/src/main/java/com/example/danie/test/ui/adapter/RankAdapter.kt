package com.example.danie.test.ui.adapter

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.danie.test.R
import com.example.danie.test.changedate
import com.example.danie.test.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.glide.GlideApp

/**
 * Created by Administrator on 2018/4/16 0016.
 */
class RankAdapter : BaseQuickAdapter<HomeBean.Issue.Item,BaseViewHolder> {
  override fun convert(helper: BaseViewHolder?, item: HomeBean.Issue.Item?) {
    helper?.setText(R.id.tv_title,item?.data?.title)
    var tags:String ="#"
    item?.data?.tags?.forEach {
      tags+=it.name
    }
    helper?.setText(R.id.tv_tag,tags+ changedate(item?.data?.duration))

    GlideApp.with(mContext)
      .load(item?.data?.cover?.feed)
      .placeholder(R.drawable.placeholder_banner)
      .transition(DrawableTransitionOptions().crossFade())
      .into(helper?.getView<ImageView>(R.id.iv_image))
  }

  constructor(layoutResId: Int, data: MutableList<HomeBean.Issue.Item>?) : super(layoutResId, data)
  constructor(data: MutableList<HomeBean.Issue.Item>?) : super(data)
  constructor(layoutResId: Int) : super(layoutResId)
}
