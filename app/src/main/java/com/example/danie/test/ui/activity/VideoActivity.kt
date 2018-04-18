package com.example.danie.test.ui.activity

import android.os.Build
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.danie.test.base.BaseActivity
import com.example.danie.test.R
import com.example.danie.test.mvp.contract.VideoDetailContract
import com.example.danie.test.mvp.model.bean.HomeBean
import com.example.danie.test.mvp.presenter.VideoDetailPresenter
import com.example.danie.test.ui.adapter.VideoDetailAdapter
import com.example.danie.test.utils.DisplayManager
import com.hazz.kotlinmvp.glide.GlideApp
import com.shuyu.gsyvideoplayer.listener.LockClickListener
import com.shuyu.gsyvideoplayer.listener.StandardVideoAllCallBack
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import kotlinx.android.synthetic.main.activity_video_detail.*
import kotlinx.android.synthetic.main.nonetworklayout.view.*

class VideoActivity :BaseActivity<VideoDetailPresenter>(),VideoDetailContract.View{

  val adapter by lazy {
    VideoDetailAdapter(itemlist)
  }
  val itemlist by lazy {
    ArrayList<HomeBean.Issue.Item>()
  }
  var itembean: HomeBean.Issue.Item? =null
  override fun showLoading() {

  }

  override fun dismissLoading() {
  }

  override fun setXiangGuan(issue: HomeBean.Issue) {
    itemlist.clear()
    itembean?.itemtype=0
    itemlist.add(itembean!!)
    issue.itemList.forEach(){
      if (it.type=="textCard"){
        it.itemtype=1
        itemlist.add(it)
      }else if (it.type=="videoSmallCard") {
        it.itemtype=2
        itemlist.add(it)
      }
    }
    adapter.notifyDataSetChanged()
  }

     override fun startnet() {
      mPresenter?.getXiangGuan(itembean?.data?.id!!)
    }

    override fun initview() {
      itembean= intent.getSerializableExtra("video") as  HomeBean.Issue.Item

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition()
            ViewCompat.setTransitionName(mVideoView, "videoImage")
            startPostponedEnterTransition()
        } else {
           // loadVideoInfo()
        }
      initVideoViewConfig()
      setVideo(itembean?.data?.playUrl!!)
      mRecyclerView.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL,false)
      mRecyclerView.adapter=adapter
      adapter.setOnItemClickListener { adapter, view, position ->
        itembean=itemlist.get(position)
        mPresenter?.getXiangGuan(itembean?.data?.id!!)
        //播放器设置
        setVideo(itembean?.data?.playUrl!!)
      }
      //设置背景
      val backgroundUrl = itembean?.data?.cover?.blurred + "/thumbnail/${DisplayManager.getScreenHeight()!! - DisplayManager.dip2px(250f)!!}x${DisplayManager.getScreenWidth()}"
     backgroundUrl.let { setBackground(it) }
    }
    private fun initVideoViewConfig(){
      //是否旋转
      mVideoView.isRotateViewAuto=false
      //是否滑动
      mVideoView.setIsTouchWiget(true)
      //增加封面
      val imageView = ImageView(this)
      imageView.scaleType = ImageView.ScaleType.CENTER_CROP
      GlideApp.with(this)
        .load(itembean?.data?.cover?.feed)
        .centerCrop()
        .into(imageView)
      mVideoView.thumbImageView=imageView
      //播放设置
      mVideoView.setStandardVideoAllCallBack(object :StandardVideoAllCallBack{
        override fun onClickResumeFullscreen(url: String?, vararg objects: Any?) {
        }

        override fun onEnterFullscreen(url: String?, vararg objects: Any?) {
        }

        override fun onClickResume(url: String?, vararg objects: Any?) {
        }

        override fun onClickSeekbarFullscreen(url: String?, vararg objects: Any?) {
        }

        override fun onClickBlankFullscreen(url: String?, vararg objects: Any?) {
        }

        override fun onPrepared(url: String?, vararg objects: Any?) {
        }

        override fun onClickStartIcon(url: String?, vararg objects: Any?) {
        }

        override fun onAutoComplete(url: String?, vararg objects: Any?) {
        }

        override fun onQuitSmallWidget(url: String?, vararg objects: Any?) {
        }

        override fun onTouchScreenSeekVolume(url: String?, vararg objects: Any?) {
        }

        override fun onClickBlank(url: String?, vararg objects: Any?) {
        }

        override fun onClickStop(url: String?, vararg objects: Any?) {
        }

        override fun onTouchScreenSeekLight(url: String?, vararg objects: Any?) {
        }

        override fun onClickSeekbar(url: String?, vararg objects: Any?) {
        }

        override fun onQuitFullscreen(url: String?, vararg objects: Any?) {
        }

        override fun onPlayError(url: String?, vararg objects: Any?) {
        }

        override fun onClickStartThumb(url: String?, vararg objects: Any?) {
        }

        override fun onEnterSmallWidget(url: String?, vararg objects: Any?) {
        }

        override fun onClickStopFullscreen(url: String?, vararg objects: Any?) {
        }

        override fun onClickStartError(url: String?, vararg objects: Any?) {
        }

        override fun onTouchScreenSeekPosition(url: String?, vararg objects: Any?) {
        }
      })
      //back键的设置
      mVideoView.backButton.setOnClickListener({onBackPressed()})
      //设置全屏按键功能
      mVideoView.fullscreenButton.setOnClickListener {
        //直接横屏
        //orientationUtils?.resolveByClick()
        //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
        mVideoView.startWindowFullscreen(this, true, true)
      }
      //锁屏事件
      mVideoView.setLockClickListener(object : LockClickListener {
        override fun onClick(view: View?, lock: Boolean) {
          //配合下方的onConfigurationChanged
        //  orientationUtils?.isEnable = !lock
        }

      })
    }
    override fun initdata() {
    }

    override fun layoutid(): Int {
        return R.layout.activity_video_detail
    }

    override fun isNeedLoadStatusBar(): Boolean {
        return true
    }
  /**
   * 设置背景颜色
   */
   fun setBackground(url: String) {
    GlideApp.with(this)
      .load(url)
      .centerCrop()
      .format(DecodeFormat.PREFER_ARGB_8888)
      .transition(DrawableTransitionOptions().crossFade())
      .into(mVideoBackground)
    }
  /*
  * 播放设置
  * */
  fun setVideo(url: String) {
    mVideoView.setUp(url, false, "")
    //开始自动播放
    mVideoView.startPlayLogic()
  }

  override fun onDestroy() {
    super.onDestroy()
    GSYVideoPlayer.releaseAllVideos()
  }
}
