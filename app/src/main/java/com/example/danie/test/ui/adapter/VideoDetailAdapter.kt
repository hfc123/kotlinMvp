package com.example.danie.test.ui.adapter

import android.widget.Switch
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.danie.test.R
import com.example.danie.test.changedate
import com.example.danie.test.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.glide.GlideApp

/**
 * Created by Administrator on 2018/4/17 0017.
 */
class VideoDetailAdapter:BaseMultiItemQuickAdapter<HomeBean.Issue.Item,BaseViewHolder> {
  override fun convert(helper: BaseViewHolder?, item: HomeBean.Issue.Item?) {
  when(item?.itemtype){
    0->{
      item.data?.title?.let { helper?.setText(R.id.tv_title, it) }
      //视频简介
      item.data?.description?.let { helper?.setText(R.id.expandable_text, it) }
      //标签
      helper?.setText(R.id.tv_tag, "#${item.data?.category} / ${changedate(item.data?.duration)}")
      //喜欢
      helper?.setText(R.id.tv_action_favorites,item?.data?.consumption?.collectionCount.toString())
      //分享
      helper?.setText(R.id.tv_action_share, item.data?.consumption?.shareCount.toString())
      //评论
      helper?.setText(R.id.tv_action_reply, item.data?.consumption?.replyCount.toString())
      //姓名
      helper?. setText(R.id.tv_author_name, item.data?.author?.name)
      //描述
      helper?.setText(R.id.tv_author_desc, item.data?.author?.description)
      GlideApp.with(mContext)
        .load(item.data?.author?.icon)
        .placeholder(R.mipmap.default_avatar).circleCrop()
        .into(helper?.getView(R.id.iv_avatar))
    }
    1->{
//    tv_text_card//iv_action_more_arrow
//    text//
      helper?.setText(R.id.tv_text_card, item.data?.text)
    }
    2->{
     // iv_video_small_card//tv_title//tv_tag
      helper?.setText(R.id.tv_title,item.data?.title)
      var name:String="#"
      item.data?.tags?.forEach {
        name+=it.name
      }
      helper?.setText(R.id.tv_tag, name+ changedate(item.data?.duration))
      GlideApp.with(mContext)
        .load(item.data?.author?.icon)
        .placeholder(R.drawable.placeholder_banner)
        .transition(DrawableTransitionOptions().crossFade())
        .into(helper?.getView(R.id.iv_video_small_card))
    }
  }
  }

  constructor(data: MutableList<HomeBean.Issue.Item>?) : super(data){
    addItemType(0, R.layout.item_video_detail_info);  //必须设置Item类型,否则空职指针异常
    addItemType(1, R.layout.item_video_text_card);
    addItemType(2, R.layout.item_video_small_card);
  }

}
