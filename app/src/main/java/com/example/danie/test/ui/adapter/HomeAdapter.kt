package com.example.danie.test.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.danie.test.R
import com.example.danie.test.mvp.model.bean.HomeBean

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
                index++
              bannerFeedList.add((item as HomeBean.Issue.Item)?.data?.cover?.feed?:"")
              bannerTitleList.add((item as HomeBean.Issue.Item)?.data?.title?:"")
              if (index==bannersize){
                helper?.getView<BGABanner>(R.id.banner).run {
                  this!!.setAutoPlayAble(bannersize> 1)
                  this!!.setAdapter(object :BGABanner.Adapter<ImageView, String>{
                    override fun fillBannerItem(p0: BGABanner?, p1: ImageView?, p2: String?, p3: Int) {
                     /*GlideApp.with(mContext)
                        .load(p2)
                        .transition(DrawableTransitionOptions().crossFade())
                        .into(p1)*/
                      /*GlideApp*/

                    }
                  })
                }
                index=0
                }
            //  }
            }
            ITEM_TYPE_TEXT_HEADER->{

            }
            ITEM_TYPE_CONTENT->{

            }
        }
    }


}
