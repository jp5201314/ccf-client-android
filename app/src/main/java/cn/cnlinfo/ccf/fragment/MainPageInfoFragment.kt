package cn.cnlinfo.ccf.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.SimpleAdapter
import android.widget.Toast
import cn.cnlinfo.ccf.API
import cn.cnlinfo.ccf.Constant
import cn.cnlinfo.ccf.R
import cn.cnlinfo.ccf.UserSharedPreference
import cn.cnlinfo.ccf.entity.AccountInfo
import cn.cnlinfo.ccf.entity.ItemNews
import cn.cnlinfo.ccf.entity.PlatformInfo
import cn.cnlinfo.ccf.net_okhttpfinal.CCFHttpRequestCallback
import cn.cnlinfo.ccf.view.UpDownTextView
import cn.finalteam.okhttpfinal.HttpRequest
import cn.finalteam.okhttpfinal.RequestParams
import com.alibaba.fastjson.JSONObject
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.find
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * Created by JP on 2017/12/5 0005.
 */
class MainPageInfoFragment : BaseFragment(){

        private var simpleAccountAdapter : SimpleAdapter? = null
        private var simplePlateformAdapter : SimpleAdapter? = null
        private var scheduledExecutorService:ScheduledExecutorService? = null
        private var viewPagerAdapter : ViewPagerAdapter? = null
        private var imageUrls : MutableList<String>? = null
        private var images : MutableList<ImageView>? = null
        private var currentItem : Int? = 0
        private var oldPosition : Int? = 0
        private var dots : MutableList<View>? = null
        private var accountAnswer : MutableList<String>? = null
        private var platformAnswer : MutableList<String>? = null
        private var dot0 : View? = null
        private var dot1 : View? = null
        private var dot2 : View? = null
        private var viewPage : ViewPager? = null
        private var gvAccountInfo : GridView? = null
        private var gvPlatformInfo : GridView? = null
        private var tvUpDowm : UpDownTextView? = null

        companion object {
            val nuns = arrayOf(R.drawable.img_guide_one_cooperation,R.drawable.img_guide_two_advantage,R.drawable.img_guide_three_discount)
            val accountTitles = arrayOf("昵称", "账号", "级别", "邀请码", "碳控因子", "碳控积分", "已冻结", "待激活")
            val platformTitles = arrayOf("总量", "已激活因子", "价格", "待激活因子", "碳控积分", "循环卷积分", "循环劵", "消费积分")
        }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.item_fragment_one,container,false)
        dot0 = view.find(R.id.dot_0)
        dot1 = view.find(R.id.dot_1)
        dot2 = view.find(R.id.dot_2)
        viewPage = view.find(R.id.vp)
        gvAccountInfo = view.find(R.id.gv_account_info)
        gvPlatformInfo = view.find(R.id.gv_platform_info)
        tvUpDowm = view.find(R.id.tv_up_down)
        initData()
        return view
    }

    override fun onStart() {
        super.onStart()
        /**
         * 开启线程池管理Banner
         */
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
        scheduledExecutorService!!.scheduleWithFixedDelay(
                ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS)
    }

    override fun onStop() {
        super.onStop()
        if (scheduledExecutorService != null) {
            scheduledExecutorService!!.shutdown()
            scheduledExecutorService = null
        }
    }


    internal inner class ViewPageTask : Runnable {

        override fun run() {
            currentItem = (currentItem!! +1) % images!!.count()
            mHandler.sendEmptyMessage(0)
        }
    }

    internal val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (viewPage != null) {
                viewPage!!.currentItem = currentItem!!
            }
        }
    }
    private fun initData(){
        showWaitingDialog(true)
        setBannerData()
        setNoticeInfo()
        simpleAccountAdapter = SimpleAdapter(activity,getAccountData(),R.layout.item_gv_info, arrayOf("title","answer"), intArrayOf(R.id.item_tv_title,R.id.item_tv_answer))
        gvAccountInfo!!.adapter = simpleAccountAdapter
        setPlatformAnwserData()
    }

    private fun setPlatformAnwserData(){
        platformAnswer = mutableListOf()
        HttpRequest.get(Constant.GET_DATA_HOST + API.GETPLATFORMINFO, object : CCFHttpRequestCallback() {
            override fun onDataSuccess(data: JSONObject) {
                val jsonObject = data.getJSONObject("platforminfo")
                val platformInfo = Gson().fromJson(jsonObject.toJSONString(),PlatformInfo::class.java)
                platformAnswer!!.add(platformInfo.TotalCCF.toString())
                platformAnswer!!.add(platformInfo.ActiveCCF.toString())
                platformAnswer!!.add(platformInfo.CurrentPrice.toString())
                platformAnswer!!.add(platformInfo.TotalInertiaCCF.toString())
                platformAnswer!!.add(platformInfo.CCScore.toString())
                platformAnswer!!.add(platformInfo.InertiaCCScore.toString())
                platformAnswer!!.add(platformInfo.CircleTicket.toString())
                platformAnswer!!.add(platformInfo.ActiveConsumeScore.toString())
                val list = ArrayList<Map<String, String>>()
                if (platformAnswer != null && platformAnswer!!.count() > 0) {
                    for (i in platformTitles.indices) {
                        val map = HashMap<String, String>()
                        map.put("title", platformTitles[i])
                        map.put("answer", platformAnswer!![i])
                        list.add(map)
                    }
                }
                simplePlateformAdapter = SimpleAdapter(activity, list, R.layout.item_gv_info, arrayOf("title", "answer"), intArrayOf(R.id.item_tv_title, R.id.item_tv_answer))
                gvPlatformInfo!!.adapter = simplePlateformAdapter!!
                showWaitingDialog(false)
            }

            override fun onDataError(code: Int, flag: Boolean, msg: String) {
                toast("获取平台信息失败")
                showWaitingDialog(false)
            }
        })
    }

    private fun setNoticeInfo(){
        val params = RequestParams()
        params.addFormDataPart("CurrentPageIndex",1)
        params.addFormDataPart("PageSize",4)
        /**
         * 1是公告  2是新闻
         */
        params.addFormDataPart("type",1)
        params.addFormDataPart("Orderby","ORDER BY IssueDate DESC")
        HttpRequest.post(Constant.GET_DATA_HOST+API.GETNEWSLIST,params,object : CCFHttpRequestCallback() {
            override fun onDataSuccess(data: JSONObject?) {
                val jsonArray = data!!.getJSONArray("Newslist")
                val itemNewsList : MutableList<ItemNews> = jsonArray
                        .asSequence()
                        .map {
                            GsonBuilder().create().fromJson<ItemNews>(it.toString(), object : TypeToken<ItemNews>() {
                            }.type)
                        }
                        .toMutableList()
                setUpDownTextView(itemNewsList)
            }

            override fun onDataError(code: Int, flag: Boolean, msg: String?) {
                toast("获取公告失败")
            }
        })
    }

    private fun setUpDownTextView(itemNewsList:MutableList<ItemNews> ){
        tvUpDowm!!.setText("welcome to Carbon control factor")
        tvUpDowm!!.setSingleLine()
        tvUpDowm!!.gravity = Gravity.CENTER
        tvUpDowm!!.setTextColor(activity.resources.getColor(R.color.colorAccent))
        tvUpDowm!!.setTextSize(12)
        tvUpDowm!!.setTextList(itemNewsList)
        tvUpDowm!!.setDuring(500)
        tvUpDowm!!.startAutoScroll()
        tvUpDowm!!.setOnClickListener(View.OnClickListener {
            val itemNews = itemNewsList[tvUpDowm!!.currentIndex]
            val params = RequestParams()
            params.addFormDataPart("NewsID", itemNews.NewsID)
            HttpRequest.post(Constant.GET_DATA_HOST + API.GETNEWSNOTICE, params, object : CCFHttpRequestCallback() {
                override fun onDataSuccess(data: JSONObject) {

                }

                override fun onDataError(code: Int, flag: Boolean, msg: String) {

                }
            })
        })
    }

    private fun setBannerData(){
        images = mutableListOf()
        imageUrls = mutableListOf()
        imageUrls!!.add("http://img01.taopic.com/141025/234987-1410250J11189.jpg")
        imageUrls!!.add("http://img1.3lian.com/2015/a1/84/d/95.jpg")
        imageUrls!!.add("http://img1.3lian.com/2015/a1/84/d/102.jpg")
        for (i in imageUrls!!.indices){
            val imageView = ImageView(activity)
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            Glide.with(this).load(imageUrls!![i]).fitCenter().error(nuns[i]).placeholder(nuns[i]).into(imageView)
            images!!.add(imageView)
        }

        dots = mutableListOf()
        dots!!.add(dot0!!)
        dots!!.add(dot1!!)
        dots!!.add(dot2!!)
        viewPagerAdapter = ViewPagerAdapter()
        viewPage!!.adapter = viewPagerAdapter
        viewPage!!.offscreenPageLimit = 3
        viewPage!!.setOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                dots!![position].setBackgroundResource(R.drawable.guide_circle_white)
                dots!![oldPosition!!].setBackgroundResource(R.drawable.guide_circle_gray)
                oldPosition = position
                currentItem = position
            }
        })
    }

    private fun getAccountData(): List<Map<String,String>> {
        val list = mutableListOf<Map<String,String>>()
        val <String>data = getAccountAnwserData()
        for (i in accountTitles.indices){
            val map = mutableMapOf<String,String>()
            map.put("title", accountTitles[i])
            map.put("answer", data[i])
            list.add(map)
        }
        return list
    }

    private fun getAccountAnwserData() : List<String> {
        val userInfoJsonString = UserSharedPreference.instance.userInfo
        val accountInfo = GsonBuilder().create().fromJson(userInfoJsonString,AccountInfo::class.java)
        accountAnswer = mutableListOf()
        accountAnswer!!.add(accountInfo.NickName)
        accountAnswer!!.add(accountInfo.UCode)
        accountAnswer!!.add(accountInfo.InLevel)
        accountAnswer!!.add(accountInfo.InvitationCode)
        accountAnswer!!.add(accountInfo.CCF)
        accountAnswer!!.add(accountInfo.CarbonIntegral)
        accountAnswer!!.add(accountInfo.CircleTicket)
        accountAnswer!!.add(accountInfo.CarbonNum)
        return accountAnswer!!
    }

    internal inner class ViewPagerAdapter : PagerAdapter() {

        override fun getCount(): Int {
            return imageUrls!!.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(images!![position])
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageView = images!![position]
            imageView.setOnClickListener(View.OnClickListener { Toast.makeText(activity, "" + (position + 1), Toast.LENGTH_SHORT).show() })
            container.addView(imageView)
            return imageView
        }
    }
}