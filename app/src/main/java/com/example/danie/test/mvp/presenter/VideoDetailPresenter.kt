package com.example.danie.test.mvp.presenter

import android.util.Log
import com.example.danie.test.base.BasePresenter
import com.example.danie.test.mvp.contract.VideoDetailContract
import com.example.danie.test.mvp.model.VideoModel

/**
 * Created by Administrator on 2018/4/17 0017.
 */
class VideoDetailPresenter:BasePresenter(),VideoDetailContract.Presenter<VideoDetailContract.View> {
  val  model by lazy {
    VideoModel()
  }
  override fun getXiangGuan(id :Long) {

    val mView=mRootView as VideoDetailContract.View
   val  compose=model.getRelatedData(id).subscribe({
     mView.setXiangGuan(it)
   },{
     Log.e("error","error",it)
   })
  }

}
