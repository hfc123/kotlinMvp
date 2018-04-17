package com.example.danie.test.ui.adapter

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.danie.test.R
import com.example.danie.test.changedate
import com.example.danie.test.goVideoActivity
import com.example.danie.test.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.glide.GlideApp

class HomeAdapter : BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {

    constructor(data: MutableList<MultiItemEntity>?) : super(data){

        addItemType(ITEM_TYPE_BANNER, R.layout.item_home_banner);  //必须设置Item类型,否则空职指针异常
        addItemType(ITEM_TYPE_TEXT_HEADER, R.layout.item_home_header);
        addItemType(ITEM_TYPE_CONTENT, R.layout.item_home_content);
    }
    companion object {

        private val ITEM_TYPE_BANNER = 0    //Banner 类型
        private val ITEM_TYPE_TEXT_HEADER = 1   //textHeader
        private val ITEM_TYPE_CONTENT = 2    //item
    }
    var index=0;
  var bannersize:Int=0;
  fun setsize(size:Int){
    bannersize=size

  }

  override fun getItem(position: Int): MultiItemEntity? {
    return super.getItem(position)
  }
  val bannerFeedList = ArrayList<String>()
  val bannerTitleList = ArrayList<String>()
  override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {

        when(item?.itemType){
            ITEM_TYPE_BANNER ->{
            //  if (index==0) {
                bannerFeedList.clear()
                bannerTitleList.clear()
                for (item1 in  (item as HomeBean.Issue.Item)?.bannerlist){
                    bannerFeedList.add(item1?.data?.cover?.feed?:"")
                    bannerTitleList.add(item1?.data?.title?:"")
                }

                helper?.getView<BGABanner>(R.id.banner).run {
                  this!!.setDelegate({ _, imageView, _, i ->
                    goVideoActivity(mContext as Activity,imageView,(item as HomeBean.Issue.Item)?.bannerlist.get(i))
                  })
                  this!!.setAutoPlayAble(bannersize> 1)
                  this!!.setData(bannerFeedList,bannerTitleList)
                  this!!.setAdapter(object :BGABanner.Adapter<ImageView, String>{
                    override fun fillBannerItem(p0: BGABanner?, p1: ImageView?, p2: String?, p3: Int) {
                     GlideApp.with(mContext)
                        .load(p2)
                        .transition(DrawableTransitionOptions().crossFade())
                        .into(p1)
                    }
                  })
                }
            }
            ITEM_TYPE_TEXT_HEADER->{
                helper?.getView<TextView>(R.id.tvHeader)?.setText((item as HomeBean.Issue.Item)?.data?.text?:"")
            }
            ITEM_TYPE_CONTENT->{
              helper?.itemView?.setOnClickListener({
                goVideoActivity(mContext as Activity,helper?.getView<ImageView>(R.id.iv_cover_feed),item as HomeBean.Issue.Item)
              })
                var itemEntity=   item as HomeBean.Issue.Item
                //imageview content图片
                var name:String="#"
                GlideApp.with(mContext).load(itemEntity.data?.cover?.feed?:"")
                        .placeholder(R.drawable.placeholder_banner)
                        .transition(DrawableTransitionOptions().crossFade())
                        .into(helper?.getView<ImageView>(R.id.iv_cover_feed))
                //imageview头像

                GlideApp.with(mContext).load(itemEntity.data?.author?.icon?:"")
                        .placeholder(R.mipmap.default_avatar)
                        .transition(DrawableTransitionOptions().crossFade())
                        .into( helper?.getView<ImageView>(R.id.iv_avatar))
                //标题textview
                helper?.getView<TextView>(R.id.tv_title)?.setText(itemEntity?.data?.title?:"...")
                itemEntity.data?.tags?.take(4)?.forEach {
                    name+=it.name+"/"
                }
                name+= changedate(itemEntity.data?.duration)
                //时间
                helper?.getView<TextView>(R.id.tv_tag)?.setText(name)
                //分类
                val  category  =itemEntity?.data?.category?:"111"
                helper?.getView<TextView>(R.id.tv_category)?.setText("#$category")
            }
        }
    }


}
