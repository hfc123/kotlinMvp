package com.example.danie.test

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.widget.LinearLayout
import android.widget.TextView
import android.support.design.widget.TabLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewCompat
import android.util.TypedValue
import android.view.View
import com.example.danie.test.mvp.model.bean.HomeBean
import com.example.danie.test.ui.activity.SearchActivity
import com.example.danie.test.ui.activity.VideoActivity
import java.lang.reflect.Field

//时间转换
fun changedate(duration:Long?):String{
    val minute = duration!! / 60
    val second = duration!!%60
    return if (minute>9){
        if (second>9){
         return "$minute'$second''"
        }else{
         return "$minute'0$second''"
        }
    }else{
        if (second>9){
            return "0$minute'$second''"
        }else{
            return "0$minute'0$second''"
        }
    }

}
//tablayout宽度转换
fun reflex(tabLayout: TabLayout) {
    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
    tabLayout.post {
        try {
            //拿到tabLayout的mTabStrip属性
            val mTabStrip = tabLayout.getChildAt(0) as LinearLayout

            val dp10 = 10

            for (i in 0 until mTabStrip.childCount) {
                val tabView = mTabStrip.getChildAt(i)

                //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                val mTextViewField = tabView.javaClass.getDeclaredField("mTextView")
                mTextViewField.setAccessible(true)

                val mTextView = mTextViewField.get(tabView) as TextView

                tabView.setPadding(0, 0, 0, 0)

                //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                var width = 0
                width = mTextView.width
                if (width == 0) {
                    mTextView.measure(0, 0)
                    width = mTextView.measuredWidth
                }

                //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                val params = tabView.layoutParams as LinearLayout.LayoutParams
                params.width = width
                params.leftMargin = dp10
                params.rightMargin = dp10
                tabView.layoutParams = params

                tabView.invalidate()
            }

        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }

}
//tablayout宽度转换方法2
fun setIndicator(tabs: TabLayout, leftDip: Int, rightDip: Int) {
    val tabLayout = tabs.javaClass
    var tabStrip: Field? = null
    try {
        tabStrip = tabLayout.getDeclaredField("mTabStrip")
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    }

    tabStrip!!.setAccessible(true)
    var llTab: LinearLayout? = null
    try {
        llTab = tabStrip!!.get(tabs) as LinearLayout?
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }

    val left = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip.toFloat(), Resources.getSystem().getDisplayMetrics()).toInt()
    val right = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip.toFloat(), Resources.getSystem().getDisplayMetrics()).toInt()

    for (i in 0 until llTab!!.childCount) {
        val child = llTab.getChildAt(i)
        child.setPadding(0, 0, 0, 0)
        val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f)
        params.leftMargin = left
        params.rightMargin = right
        child.layoutParams = params
        child.invalidate()
    }
}

//跳转activity
 fun goVideoActivity(activity: Activity, view: View, issueItem: HomeBean.Issue.Item){

  val intent= Intent(activity, VideoActivity::class.java)
  intent.putExtra("video",issueItem)
  intent.putExtra("translate",true)
  if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
    ViewCompat.setTransitionName(view,"videoImage")
    val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, "videoImage")
    ActivityCompat.startActivity(activity, intent, activityOptions.toBundle())
  }else{
    activity.startActivity(intent)
    activity.overridePendingTransition(R.anim.anim_in, R.anim.anim_out)
  }
}
