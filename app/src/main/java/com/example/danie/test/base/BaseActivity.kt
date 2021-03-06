package com.example.danie.test.base

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.classic.common.MultipleStatusView
import com.example.danie.test.R
import com.example.danie.test.utils.TUtil
import com.readystatesoftware.systembartint.SystemBarTintManager
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<T : BasePresenter> : AppCompatActivity() {
        /*
         *多种状态view的切换
         * */
  var mLayoutStatusView: MultipleStatusView?=null;

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
        setContentView(layoutid())
        initPresenter()
        initStateBar();
        initdata()
        initview()
        startnet()
        initLisener()
  }
    private fun initLisener() {
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }
    open val mRetryClickListener:View.OnClickListener=View.OnClickListener { startnet() }
    abstract fun startnet()

    abstract fun initview()

    abstract fun initdata()

    abstract fun layoutid(): Int
    /**
     * 打卡软键盘
     */
    fun openKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
    /**
     * 关闭软键盘
     */
    fun closeKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        //presenter解绑定
        mPresenter?.detachView()
    }
    private fun initStateBar() {
        setColorId(R.color.color_translucent);
        if (isNeedLoadStatusBar()) {
            loadStateBar();
        }
    }

    abstract fun isNeedLoadStatusBar(): Boolean

    var tintManager:SystemBarTintManager ?= null;
    private fun loadStateBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        tintManager =  SystemBarTintManager(this);
        // 激活状态栏设置
        tintManager!!.setStatusBarTintEnabled(true);
        // 激活导航栏设置
        tintManager!!.setNavigationBarTintEnabled(true);
        // 设置一个状态栏颜色
        tintManager!!.setStatusBarTintResource(getColorId());
    }

    @TargetApi(19)
    private fun setTranslucentStatus(on: Boolean) {
        val win = window
        val winParams = win.attributes
        val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    /**
     * 如果子类使用非默认的StatusBar,就重写此方法,传入布局的id
     */

    public var mColorId = R.color.color_translucent

    public fun setColorId(color: Int) {
        this.mColorId = color//子类重写方式
    }

    public fun getColorId(): Int {
        return mColorId
    }

    var mPresenter: T? = null
    //presenter 初始化
    private fun initPresenter() {
        if (this is IBaseView &&
                this.javaClass.genericSuperclass is ParameterizedType &&
                (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.size > 0) {

            mPresenter = TUtil.getT(this, 0)
            mPresenter!!.attachView(this)
            Log.e("初始化", this.javaClass.simpleName + " presenter 初始化成功...")
        }
    }
}
