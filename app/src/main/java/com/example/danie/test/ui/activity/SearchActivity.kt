package com.example.danie.test.ui.activity

import android.content.Intent
import android.graphics.Color
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.danie.test.base.BaseActivity
import com.example.danie.test.R
import com.example.danie.test.mvp.contract.SearchContract
import com.example.danie.test.mvp.model.bean.HomeBean
import com.example.danie.test.mvp.presenter.SearchPresenter
import kotlinx.android.synthetic.main.activity_search_layout.*
import android.widget.TextView
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.danie.test.ui.adapter.RankAdapter
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout


class SearchActivity :BaseActivity<SearchPresenter> (),SearchContract.View{
    override fun setHotWords(hotWords: ArrayList<String>) {
        //设置
        hotWords.forEach {
          list.add(it)
            //flexbox.addView(view)
            //flexbox
        }
        //layout消失显示内容
        searchlayout.visibility=View.VISIBLE
        //显示
       // search_recycleview.visibility=View.VISIBLE
        val  mInflater=layoutInflater
        flexbox.setAdapter(object : TagAdapter<String>(list) {
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val tv = mInflater.inflate(R.layout.tv,
                        flexbox!!, false) as TextView
                tv.setText(list.get(position))
                return tv
            }
        })
    }
    val itemList by lazy {
        ArrayList<HomeBean.Issue.Item>()
    }
    val adapter by lazy {
        RankAdapter(R.layout.item_category_detail,itemList)
    }
    override fun setHotSearchWord(issue: HomeBean.Issue) {
        issue.itemList.forEach {
            itemList.add(it)
        }
        //需要调用这个代码声明loadmore已经完成可以继续请求更多数据
        //  adapter.loadMoreComplete()
        adapter.loadMoreComplete()

        adapter.notifyDataSetChanged()
        searchlayout.visibility=View.GONE
        //显示
        search_recycleview.visibility=View.VISIBLE


    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun startnet() {
        mPresenter?.getHotWords()
    }
    val  list by lazy {
        ArrayList<String>()
    }
    //跳转activity
    private fun goVideoActivity(activity: SearchActivity,view: View,issueItem: HomeBean.Issue.Item){
        val intent=Intent(activity,VideoActivity::class.java)
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
    override fun initview() {
        flexbox.setOnTagClickListener(object : TagFlowLayout.OnTagClickListener{
            override fun onTagClick(view: View?, position: Int, parent: FlowLayout?): Boolean {
              val hotWord=  list.get(position)
                //请求
                mPresenter?.getHotSerchWord(hotWord)
                return true
            }
        })
        search_recycleview.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        search_recycleview.adapter=adapter
        adapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
                mPresenter?.loadmore()
        },search_recycleview)
        adapter.setOnItemClickListener { adapter, view, position ->
            //goVideoActivity
           val imgview= adapter.getViewByPosition(search_recycleview,position,R.id.iv_image) as ImageView
            goVideoActivity(this@SearchActivity,imgview,itemList.get(position))
        }

        //取消
        tv_cancel.setOnClickListener { onBackPressed() }
        //键盘的搜索按钮
        et_search_view.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                 //   closeSoftKeyboard()
                   val keyWords = et_search_view.text.toString().trim()
                    if (keyWords.isNullOrEmpty()) {
                        Toast.makeText(this@SearchActivity,"请输入你感兴趣的关键词",Toast.LENGTH_SHORT).show()
                    } else {
                        mPresenter?.getHotSerchWord(keyWords)
                    }
                }
                return false
            }

        })
    }

    override fun initdata() {

    }

    override fun layoutid(): Int {
        return R.layout.activity_search_layout
    }

    override fun isNeedLoadStatusBar(): Boolean {
        return true
    }


}