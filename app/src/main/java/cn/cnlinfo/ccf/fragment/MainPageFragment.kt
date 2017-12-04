package cn.cnlinfo.ccf.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cn.cnlinfo.ccf.R
import cn.cnlinfo.ccf.view.StopScrollViewPager
import com.shizhefei.view.indicator.FixedIndicatorView
import com.shizhefei.view.indicator.IndicatorViewPager
import com.shizhefei.view.indicator.slidebar.LayoutBar
import com.shizhefei.view.indicator.slidebar.ScrollBar
import com.shizhefei.view.indicator.transition.OnTransitionTextListener
import com.tendcloud.tenddata.TCAgent
import org.jetbrains.anko.find

/**
 * Created by JP on 2017/12/1 0001.
 */
class MainPageFragment : BaseFragment(){

    private var indicatorViewPager :IndicatorViewPager? = null
    private var adapter : IndicatorViewPager.IndicatorFragmentPagerAdapter? = null
    var vp :StopScrollViewPager? = null
    var indicator : FixedIndicatorView? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_main_page,container,false)
        TCAgent.onPageStart(context,"主页")
        vp = view!!.find(R.id.ss_vp)
        indicator = view!!.find(R.id.indicator)
        vp!!.setStopScroll(true)
        setIndicator()
        return view!!
    }
    private fun setIndicator(){
        val unSelectSize = 15.0f
        val selectSize = 15.0f

        val listener = MyOnTransitionTextListener()
        /**
         * 定义实现匿名内部类中的方法
         */
      /*  val listener1 = object : OnTransitionTextListener(){
            override fun getTextView(tabItemView: View?, position: Int): TextView {
                return tabItemView!!.find(R.id.tv_tab_text)
            }
        }
        indicator!!.onTransitionListener = object :OnTransitionTextListener(){
            override fun getTextView(tabItemView: View?, position: Int): TextView {
                return super.getTextView(tabItemView, position)
            }
        }*/
        indicator!!.onTransitionListener = listener!!.setColor(resources!!.getColor(R.color.color_blue_4d8cd6),
                resources!!.getColor(R.color.color_black_0e1214))!!.setSize(selectSize, unSelectSize)
        indicatorViewPager = IndicatorViewPager(indicator!!,vp)
        adapter = ItemViewPagerIndicator(fragmentManager)
        indicatorViewPager!!.adapter = adapter
        indicatorViewPager!!.setPageOffscreenLimit(3)
        indicator!!.scrollBar = LayoutBar(context,R.layout.indicator_scroll_bar,ScrollBar.Gravity.BOTTOM)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        TCAgent.onPageEnd(context,"主页")
    }

    companion object {
        val TITLES = arrayOf("主页信息", "循环包", "个人信息")
    }

    internal inner class ItemViewPagerIndicator(fragmentManager: FragmentManager) : IndicatorViewPager.IndicatorFragmentPagerAdapter(fragmentManager) {
        override fun getViewForTab(position: Int, convertView: View?, container: ViewGroup?): View {
            var convertView = convertView
            if (convertView==null){
                convertView = LayoutInflater.from(context)!!.inflate(R.layout.item_tab,container,false)
                var tv_tab = convertView!!.find<TextView>(R.id.tv_tab_text)
                tv_tab!!.textSize = 12f
                tv_tab!!.text = TITLES[position]
            }
            return convertView!!
        }

        override fun getFragmentForPage(position: Int): Fragment {
            var fragment:Fragment? = null
            when(position){
                0->fragment = MainPageInfoFragment()
                1->fragment = CyclePackageFragment()
                2->fragment = MyInfoFragment()
            }
            return fragment!!
        }

        override fun getCount(): Int {
            return TITLES!!.size
        }

    }
    internal inner class MyOnTransitionTextListener :OnTransitionTextListener(){
        override fun getTextView(tabItemView: View?, position: Int): TextView {
            return tabItemView!!.find(R.id.tv_tab_text)
        }
    }
}

