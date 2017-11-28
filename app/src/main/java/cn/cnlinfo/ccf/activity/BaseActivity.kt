package cn.cnlinfo.ccf.activity

import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.TargetApi
import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast

import com.orhanobut.logger.Logger

import org.greenrobot.eventbus.EventBus

import cc.cloudist.acplibrary.ACProgressFlower
import cn.cnlinfo.ccf.R
import cn.cnlinfo.ccf.UserSharedPreference
import cn.cnlinfo.ccf.dialog.DialogCreater
import cn.cnlinfo.ccf.event.ErrorMessageEvent
import cn.cnlinfo.ccf.inter.IActivityFinish
import cn.cnlinfo.ccf.inter.IComponentContainer
import cn.cnlinfo.ccf.inter.ILifeCycleComponent
import cn.cnlinfo.ccf.manager.AppManage
import cn.cnlinfo.ccf.manager.LifeCycleComponentManager
import cn.cnlinfo.ccf.manager.PhoneManager
import cn.cnlinfo.ccf.manager.SystemBarTintManager
import cn.cnlinfo.ccf.net_okhttp.OKHttpManager
import cn.cnlinfo.ccf.receiver.GlobalErrorMessageReceiver
import cn.cnlinfo.ccf.receiver.NetworkConnectChangedReceiver
import cn.cnlinfo.ccf.view.RefreshHeaderView
import `in`.srain.cube.views.ptr.PtrClassicFrameLayout
import `in`.srain.cube.views.ptr.PtrFrameLayout

/**
 * Created by JP on 2017/10/11 0011.
 */

open class BaseActivity : AppCompatActivity(), IComponentContainer, IActivityFinish {
    private val mComponentContainer = LifeCycleComponentManager()
    protected var waitingDialog: ACProgressFlower? = null
    private var messageReceiver: GlobalErrorMessageReceiver? = null
    private var receiver: NetworkConnectChangedReceiver? = null


    protected//类名
            //完整类名
            //包名
    val runningActivityName: String
        get() {
            val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val info = manager.getRunningTasks(1)[0]
            val shortClassName = info.topActivity.shortClassName
            val className = info.topActivity.className
            val packageName = info.topActivity.packageName
            val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val cn = am.getRunningTasks(1)[0].topActivity
            return shortClassName
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManage.getInstance().addActivity(this)
        messageReceiver = GlobalErrorMessageReceiver()
        receiver = NetworkConnectChangedReceiver()
        registerNetworkConnectChangedReceiver()
        registerGlobalErrorMessageReceiver()
    }

    private fun registerNetworkConnectChangedReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED")
        intentFilter.addAction("android.net.wifi.STATE_CHANGE")
        intentFilter.addAction(BROADCAST_NETWORK_FLAG)
        registerReceiver(receiver, intentFilter)
    }

    private fun registerGlobalErrorMessageReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(BROADCAST_FLAG)
        registerReceiver(messageReceiver, intentFilter)
    }

    override fun getResources(): Resources {
        val res = super.getResources()
        val config = Configuration()
        config.setToDefaults()
        res.updateConfiguration(config, res.displayMetrics)
        return res
    }

    protected fun setStatusBarColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true)
            val tintManager = SystemBarTintManager(this)
            tintManager.isStatusBarTintEnabled = true
            tintManager.setStatusBarTintResource(color)//通知栏所需颜色
        }
    }

    protected fun toLogin() {
        startActivity(Intent(this, LoginRegisterActivity::class.java))
    }

    protected fun showMessage(status: Int, message: String) {
        EventBus.getDefault().post(ErrorMessageEvent(message))
    }

    protected fun showMessage(message: String) {
        EventBus.getDefault().post(ErrorMessageEvent(message))
    }

    @JvmOverloads protected fun showWaitingDialog(show: Boolean, waitingNotice: String = getString(R.string.please_wait)) {
        if (!show) {
            waitingDialog?.dismiss()
            return
        }
        waitingDialog = DialogCreater.createProgressDialog(this, waitingNotice)
        waitingDialog?.show()
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

    override fun finish() {
        AppManage.getInstance().finishActivity(this)
    }

    override fun finishActivity() {
        super.finish()
    }

    override fun onRestart() {
        super.onRestart()
        mComponentContainer.onBecomesVisibleFromTotallyInvisible()
    }

    override fun onResume() {
        super.onResume()
        mComponentContainer.onBecomesVisibleFromPartiallyInvisible()
        val intent = Intent()
        intent.action = BROADCAST_NETWORK_FLAG
        sendBroadcast(intent)
    }

    override fun onPause() {
        super.onPause()

        mComponentContainer.onBecomesPartiallyInvisible()
    }


    public override fun onStop() {
        super.onStop()
        mComponentContainer.onBecomesTotallyInvisible()
    }

    override fun onDestroy() {
        super.onDestroy()
        mComponentContainer.onDestroy()
        unregisterReceiver(messageReceiver)
        unregisterReceiver(receiver)
        OKHttpManager.cancelAndRemoveAllCalls()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 开始执行contentView动画
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected fun startContentViewAnimation(contentView: View, onAnimationEnd: AnimatorListenerAdapter) {
        val set = AnimatorSet()
        set.playTogether(
                ObjectAnimator.ofFloat(contentView, "alpha", 1.0f),
                ObjectAnimator.ofFloat(contentView, "translationY", 0f)
        )
        set.setDuration(400).start()
        set.interpolator = AccelerateDecelerateInterpolator()
        set.addListener(onAnimationEnd)
    }

    /**
     * toast message
     *
     * @param text
     */
    protected fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    /**
     * toast message
     *
     * @param resource
     */
    protected fun toast(resource: Int) {
        Toast.makeText(this, resource, Toast.LENGTH_SHORT).show()
    }

    override fun addComponent(component: ILifeCycleComponent) {
        mComponentContainer.addComponent(component)
    }


    protected fun setMaterialHeader(ptr: PtrClassicFrameLayout) {
        val ptrHeader = RefreshHeaderView(applicationContext)
        ptrHeader.layoutParams = PtrFrameLayout.LayoutParams(-1, -2)
        ptrHeader.setPtrFrameLayout(ptr)
        ptr.setLoadingMinTime(800)
        ptr.setDurationToCloseHeader(800)
        ptr.headerView = ptrHeader
        ptr.addPtrUIHandler(ptrHeader)
    }

    /**
     * 验证是否登录,如果未登录,则跳转登录页面
     *
     * @return
     */
    protected fun validLogin(): Boolean {
        if (!UserSharedPreference.getInstance().hasLogined()) {
            startActivity(Intent(this@BaseActivity, LoginRegisterActivity::class.java))
            AppManage.getInstance().finishOther()
            return false
        }
        return true
    }

    /**
     * 验证是否新版,如果新版,则跳转引导页
     *
     * @return
     */
    protected fun validNewVersion(): Boolean {
        val nowVersionCode = PhoneManager.getVersionInfo()!!.versionCode
        val userSharedPreference = UserSharedPreference.getInstance()
        if (userSharedPreference.isNewVersionCode(nowVersionCode)) {
            userSharedPreference.latestVersionCode = nowVersionCode
            startActivity(Intent(this@BaseActivity, GuideActivity::class.java))
            finish()
            return true
        }
        return false
    }

    /**
     * Override this function when you need control whether you will cancel OkHttpFinal after Destroy
     *
     * @return boolean
     */
    protected fun cancelOkHttpFinalAfterDestory(): Boolean {
        return true
    }

    companion object {

        val BROADCAST_FLAG = "cn.cnlinfo.ccf.response.message"
        val BROADCAST_NETWORK_FLAG = "cn.cnlinfo.ccf.net"
    }

}
