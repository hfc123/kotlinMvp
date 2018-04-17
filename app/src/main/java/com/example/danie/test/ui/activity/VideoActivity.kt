package com.example.danie.test.ui.activity

import android.os.Build
import android.support.v4.view.ViewCompat
import com.example.danie.test.base.BaseActivity
import com.example.danie.test.base.BasePresenter
import com.example.danie.test.R
import kotlinx.android.synthetic.main.videolayout.*

class VideoActivity :BaseActivity<BasePresenter>(){
    override fun startnet() {
    }

    override fun initview() {
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition()
            ViewCompat.setTransitionName(video, "videoImage")
            startPostponedEnterTransition()
        } else {
           // loadVideoInfo()
        }
    }

    override fun initdata() {
    }

    override fun layoutid(): Int {
        return R.layout.videolayout
    }

    override fun isNeedLoadStatusBar(): Boolean {
        return true
    }
}