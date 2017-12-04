package cn.cnlinfo.ccf.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.view.menu.MenuPopupHelper
import android.support.v7.widget.PopupMenu
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import cn.cnlinfo.ccf.R
import cn.cnlinfo.ccf.UserSharedPreference
import cn.cnlinfo.ccf.adapter.MainPageFragmentAdapter
import cn.cnlinfo.ccf.fragment.*
import cn.cnlinfo.ccf.manager.AppManage
import cn.cnlinfo.ccf.utils.QRCodeUtil
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.tendcloud.tenddata.TCAgent
import com.yzq.zxinglibrary.common.Constant
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlinx.android.synthetic.main.item_title.*


/**
 * Created by JP on 2017/11/29 0029.
 */
class MainPageActivity : BaseActivity(), View.OnClickListener,ViewPager.OnPageChangeListener,PopupMenu.OnMenuItemClickListener, MultiplePermissionsListener {

    private var alertDialog: AlertDialog? = null
    private var popupMenu: PopupMenu? = null
    private var fragmentList: MutableList<Fragment>? = null
    private var mainPageFragmentAdapter: MainPageFragmentAdapter? = null

    companion object {
        val REQUEST_CODE_SCAN = 100
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        TCAgent.onPageStart(this, "主页")
        validLoadGuidePage()
        vp.setStopScroll(true)
        init()
    }

    fun init() {
        ibt_back.visibility = View.GONE
        tv_title.text = "主页"
        registerOnClickListener()
        tv_main_page.setBackgroundColor(resources.getColor(R.color.indicator_color))
        setPopupMenu(ibt_add)
        ibt_add.setOnClickListener {
            popupMenu!!.show()
        }
        mainPageFragmentAdapter = MainPageFragmentAdapter(supportFragmentManager, setFragmentList())
        vp.adapter = mainPageFragmentAdapter
        vp.offscreenPageLimit = 5
        vp.setOnPageChangeListener(this)
    }

   private fun registerOnClickListener() {
        tv_main_page.setOnClickListener(this)
        tv_gauage_panel.setOnClickListener(this)
        tv_trading_center.setOnClickListener(this)
        tv_cc_mall.setOnClickListener(this)
        tv_cc_union.setOnClickListener(this)
    }

    private fun setFragmentList(): MutableList<Fragment>? {
        fragmentList = mutableListOf()
        fragmentList!!.add(MainPageFragment())
        fragmentList!!.add(GaugePanelFragment())
        fragmentList!!.add(TradingCenterFragment())
        fragmentList!!.add(CCMallFragment())
        fragmentList!!.add(CCUnionFragment())
        return fragmentList
    }

    @SuppressLint("RestrictedApi")
    private fun setPopupMenu(view: View) {
        popupMenu = PopupMenu(this, view, Gravity.END)
        val menuInflater = popupMenu!!.menuInflater
        val menu = popupMenu!!.menu
        menuInflater.inflate(R.menu.layout_menu, menu)
        val field = popupMenu!!.javaClass.getDeclaredField("mPopup")
        field.isAccessible = true
        val menuPopupHelper = field.get(popupMenu) as MenuPopupHelper
        menuPopupHelper.setForceShowIcon(true)
        popupMenu!!.setOnMenuItemClickListener(this)
    }

    private fun validLoadGuidePage() {
        if (!validNewVersion()) {
            if (validLogin()) {
                if (UserSharedPreference.instance!!.isFirstLogin) {
                    showRiskWarningDialog()
                    UserSharedPreference.instance!!.isFirstLogin = false
                } else {

                }
            } else {
                finish()
            }
        }
    }

    /**
     * 弹出风险提示dialog
     */
    private fun showRiskWarningDialog() {
        val builder = AlertDialog.Builder(this)
        val view = View.inflate(this, R.layout.dialog_risk_warning, null)
        val imageView = view.findViewById<ImageView>(R.id.iv_close_dialog)
        builder.setView(view)
        alertDialog = builder.create()
        alertDialog!!.setCancelable(false)
        imageView.setOnClickListener {
            if (alertDialog!!.isShowing()) {
                alertDialog!!.dismiss()
            }
        }
        alertDialog!!.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        TCAgent.onPageEnd(this, "主页")
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_main_page -> {
                vp.setCurrentItem(0, false)
                setMainPageBackgroundColor()
                tv_title.text = "主页"
            }

            R.id.tv_gauage_panel -> {
                vp.setCurrentItem(1, false)
                setGauagePanelBackgroundColor()
                tv_title.text = "仪表盘"
            }
            R.id.tv_trading_center -> {
                vp.setCurrentItem(2, false)
                setTradCenterBackgroundColor()
                tv_title.text = "交易中心"
            }

            R.id.tv_cc_mall -> {
                vp.setCurrentItem(3, false)
                setCCMallBackgroundColor()
                tv_title.text = "CC商城"
            }
            R.id.tv_cc_union -> {
                vp.setCurrentItem(4, false)
                setCCUnionBackgroundColor()
                tv_title.text = "CC联盟"
            }
        }
    }

    private fun setCCUnionBackgroundColor() {
        tv_main_page.setBackgroundColor(resources.getColor(R.color.white))
        tv_gauage_panel.setBackgroundColor(resources.getColor(R.color.white))
        tv_trading_center.setBackgroundColor(resources.getColor(R.color.white))
        tv_cc_mall.setBackgroundColor(resources.getColor(R.color.white))
        tv_cc_union.setBackgroundColor(resources.getColor(R.color.indicator_color))
    }

    private fun setCCMallBackgroundColor() {
        tv_main_page.setBackgroundColor(resources.getColor(R.color.white))
        tv_gauage_panel.setBackgroundColor(resources.getColor(R.color.white))
        tv_trading_center.setBackgroundColor(resources.getColor(R.color.white))
        tv_cc_mall.setBackgroundColor(resources.getColor(R.color.indicator_color))
        tv_cc_union.setBackgroundColor(resources.getColor(R.color.white))
    }

    private fun setTradCenterBackgroundColor() {
        tv_main_page.setBackgroundColor(resources.getColor(R.color.white))
        tv_gauage_panel.setBackgroundColor(resources.getColor(R.color.white))
        tv_trading_center.setBackgroundColor(resources.getColor(R.color.indicator_color))
        tv_cc_mall.setBackgroundColor(resources.getColor(R.color.white))
        tv_cc_union.setBackgroundColor(resources.getColor(R.color.white))
    }

    private fun setGauagePanelBackgroundColor() {
        tv_main_page.setBackgroundColor(resources.getColor(R.color.white))
        tv_gauage_panel.setBackgroundColor(resources.getColor(R.color.indicator_color))
        tv_trading_center.setBackgroundColor(resources.getColor(R.color.white))
        tv_cc_mall.setBackgroundColor(resources.getColor(R.color.white))
        tv_cc_union.setBackgroundColor(resources.getColor(R.color.white))
    }

    private fun setMainPageBackgroundColor() {
        tv_main_page.setBackgroundColor(resources.getColor(R.color.indicator_color))
        tv_gauage_panel.setBackgroundColor(resources.getColor(R.color.white))
        tv_trading_center.setBackgroundColor(resources.getColor(R.color.white))
        tv_cc_mall.setBackgroundColor(resources.getColor(R.color.white))
        tv_cc_union.setBackgroundColor(resources.getColor(R.color.white))
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        when(position){
            0->setMainPageBackgroundColor()
            1->setGauagePanelBackgroundColor()
            2->setTradCenterBackgroundColor()
            3->setCCMallBackgroundColor()
            4->setCCUnionBackgroundColor()
        }
    }

    override fun onPageSelected(position: Int) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==100&&resultCode== Activity.RESULT_OK){
            val content = data!!.getStringExtra(Constant.CODED_CONTENT)
            val intent = Intent(this,WebActivity::class.java)
            intent.putExtra("url",content)
            startActivity(intent)
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.sweep->{
                if (Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
                    if (Dexter.isRequestOngoing()){
                       return false
                    }
                    Dexter.checkPermissions(this@MainPageActivity,Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE)
                }else{
                    QRCodeUtil.toRichScan(this,100)
                }
            }
            R.id.my_qrcode->startActivity(Intent(this,BuildQRCodeActivity::class.java))
            R.id.exit->exit()
            R.id.setting->startActivity(Intent(this,SettingActivity::class.java))
        }
           return true
    }
    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
        if (report!!.areAllPermissionsGranted()){
            QRCodeUtil.toRichScan(this,100)
        }else{
            toast("请检查所需的权限是否完全开启")
        }
    }

    override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?, token: PermissionToken?) {
/*          val dialog = AlertDialog.Builder(this).setTitle("权限").setMessage("需要的权限").setPositiveButton("允许",DialogInterface.OnClickListener { dialog, which ->
              dialog.dismiss()
              token!!.continuePermissionRequest()
          }).setNegativeButton("拒绝",DialogInterface.OnClickListener { dialog, which ->
              dialog.dismiss()
              token!!.cancelPermissionRequest()
          }).show()*/
        token!!.continuePermissionRequest()
    }

    private fun exit(){
        UserSharedPreference.instance!!.logout()
        AppManage.getInstance().exit(this)
    }
}



