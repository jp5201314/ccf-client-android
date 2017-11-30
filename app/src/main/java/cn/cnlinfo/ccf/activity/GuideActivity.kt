package cn.cnlinfo.ccf.activity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import cn.cnlinfo.ccf.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.tendcloud.tenddata.TCAgent
import kotlinx.android.synthetic.main.viewpager_guide_image_three.*
import java.util.*

/*引导页*/
class GuideActivity : BaseActivity(), MultiplePermissionsListener {

    //装分页显示的view的数组
    private var pageViews: ArrayList<View>? = null
    //将小圆点的图片用数组表示
    private var imageViews: Array<ImageView>? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TCAgent.onPageStart(this, "引导页")
        this.setStatusBarColor(R.color.color_transparent)
        Dexter.checkPermissions(this,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE)

        //将要分页显示的View装入数组中
        val inflater = layoutInflater
        pageViews = ArrayList()
        pageViews!!.add(inflater.inflate(R.layout.viewpager_guide_image_one, null))
        pageViews!!.add(inflater.inflate(R.layout.viewpager_guide_image_two, null))
        pageViews!!.add(inflater.inflate(R.layout.viewpager_guide_image_three, null))

        //创建imageviews数组，大小是要显示的图片的数量,闭包声明数组
        imageViews = Array(3, { ImageView(this) })
        //从指定的XML文件加载视图
        val viewPics = inflater.inflate(R.layout.activity_guide, null) as ViewGroup

        //实例化小圆点的linearLayout和viewpager
        val viewPoints = viewPics.findViewById<View>(R.id.viewGroup) as ViewGroup
        val viewPager = viewPics.findViewById<View>(R.id.guidePages) as ViewPager

        //添加小圆点的图片
        for (i in pageViews!!.indices) {
            val imageView = ImageView(this@GuideActivity)
            //设置小圆点imageview的参数
            val layoutParams = LinearLayout.LayoutParams(20, 20)
            layoutParams.setMargins(10, 0, 10, 0)
            imageView.layoutParams = layoutParams//创建一个宽高均为20 的布局
            imageView.setPadding(20, 0, 20, 0)
            //将小圆点layout添加到数组中
            imageViews!![i] = imageView

            //默认选中的是第一张图片，此时第一个小圆点是选中状态，其他不是
            if (i == 0) {
                imageViews!![i].setBackgroundResource(R.drawable.guide_circle_white)
            } else {
                imageViews!![i].setBackgroundResource(R.drawable.guide_circle_gray)
            }

            //将imageviews添加到小圆点视图组
            viewPoints.addView(imageViews!![i])
        }

        //显示滑动图片的视图
        setContentView(viewPics)

        //设置viewpager的适配器和监听事件
        viewPager.adapter = GuidePageAdapter()
        viewPager.setOnPageChangeListener(GuidePageChangeListener())
    }

    override fun onDestroy() {
        TCAgent.onPageEnd(this, "引导页")
        super.onDestroy()
    }

    private fun setGuided() {
        val settings = getSharedPreferences(SHARED_PREFERENCES_NAME, 0)
        val editor = settings.edit()
        editor.putString(KEY_GUIDE_ACTIVITY, "false")
        editor.apply()
    }

    private inner class GuidePageAdapter : PagerAdapter() {

        //销毁position位置的界面
        override fun destroyItem(v: View?, position: Int, arg2: Any?) {
            (v as ViewPager).removeView(pageViews!![position])
        }


        //获取当前窗体界面数
        override fun getCount(): Int {
            return pageViews!!.size
        }

        //初始化position位置的界面
        override fun instantiateItem(v: View?, position: Int): Any {
            (v as ViewPager).addView(pageViews!![position])
            return pageViews!![position]
        }

        // 判断是否由对象生成界面
        override fun isViewFromObject(v: View, arg1: Any): Boolean {
            return v === arg1
        }

        override fun getItemPosition(`object`: Any?): Int {
            return super.getItemPosition(`object`)
        }

    }

    private inner class GuidePageChangeListener : OnPageChangeListener {

        override fun onPageScrollStateChanged(arg0: Int) {}

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}

        override fun onPageSelected(position: Int) {
            for (i in imageViews!!.indices) {
                imageViews!![position].setBackgroundResource(R.drawable.guide_circle_white)
                //不是当前选中的page，其小圆点设置为未选中的状态
                if (position != i) {
                    imageViews!![i].setBackgroundResource(R.drawable.guide_circle_gray)
                }
            }

            if (position == imageViews!!.size - 1) {
                //设置已经引导
                setGuided()
                ibt_to_service.setOnClickListener {
                    startActivity(Intent(this@GuideActivity, LoginRegisterActivity::class.java))
                    this@GuideActivity.finish()  }
            }
        }
    }
    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {

    }

    override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?, token: PermissionToken?) {
        token!!.continuePermissionRequest()
    }
    companion object {
         val SHARED_PREFERENCES_NAME = "my_pref"
         val KEY_GUIDE_ACTIVITY = "guide_activity"
    }
}