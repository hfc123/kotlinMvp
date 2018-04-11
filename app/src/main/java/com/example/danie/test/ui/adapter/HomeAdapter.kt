package com.example.danie.test.ui.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.danie.test.R
import com.example.danie.test.mvp.model.bean.HomeBean

class HomeAdapter : BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {
    constructor(data: MutableList<MultiItemEntity>?) : super(data){
        addItemType(ITEM_TYPE_BANNER, R.layout.bannerlayout);  //必须设置Item类型,否则空职指针异常
        addItemType(ITEM_TYPE_TEXT_HEADER, R.layout.titlelayout);
        addItemType(ITEM_TYPE_CONTENT, R.layout.contentlayout);
    }
    companion object {

        private val ITEM_TYPE_BANNER = 1    //Banner 类型
        private val ITEM_TYPE_TEXT_HEADER = 2   //textHeader
        private val ITEM_TYPE_CONTENT = 3    //item
    }

    override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {
        when(item?.itemType){
            ITEM_TYPE_BANNER ->{

            }
            ITEM_TYPE_TEXT_HEADER->{

            }
            ITEM_TYPE_CONTENT->{

            }
        }
    }


}