package com.example.danie.test.ui.adapter

import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.danie.test.R
import com.example.danie.test.mvp.model.bean.FenLeiBean
import com.hazz.kotlinmvp.glide.GlideApp

class FenLeiAdapter : BaseQuickAdapter<FenLeiBean,BaseViewHolder> {
    override fun convert(helper: BaseViewHolder?, item: FenLeiBean?) {
       var imageView:ImageView? =helper?.getView<ImageView>(R.id.iv_category)
        GlideApp.with(mContext)
                .load(item?.getHeaderImage())
                .placeholder(R.drawable.placeholder_banner)
                .transition(DrawableTransitionOptions().crossFade())
                .into(imageView)
        helper?.setText(R.id.tv_category_name,item?.getName())
    }

    constructor(layoutResId: Int, data: MutableList<FenLeiBean>?) : super(layoutResId, data)
    constructor(data: MutableList<FenLeiBean>?) : super(data)
    constructor(layoutResId: Int) : super(layoutResId)
}