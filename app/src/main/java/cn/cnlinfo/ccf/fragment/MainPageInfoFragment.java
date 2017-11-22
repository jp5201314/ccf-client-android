package cn.cnlinfo.ccf.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.API;
import cn.cnlinfo.ccf.Constant;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.entity.AccountInfo;
import cn.cnlinfo.ccf.entity.ItemNews;
import cn.cnlinfo.ccf.entity.PlatformInfo;
import cn.cnlinfo.ccf.net_okhttpfinal.CCFHttpRequestCallback;
import cn.cnlinfo.ccf.view.UpDownTextView;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * Created by JP on 2017/10/23 0023.
 */

public class MainPageInfoFragment extends BaseFragment {
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.dot_0)
    View dot0;
    @BindView(R.id.dot_1)
    View dot1;
    @BindView(R.id.dot_2)
    View dot2;

    Unbinder unbinder;
    @BindView(R.id.tv_up_down)
    UpDownTextView tvUpDown;
    @BindView(R.id.gv_account_info)
    GridView gvAccountInfo;
    @BindView(R.id.gv_platform_info)
    GridView gvPlatformInfo;

    private SimpleAdapter simpleAccountAdapter;
    private SimpleAdapter simplePlatformAdapter;
    private ScheduledExecutorService scheduledExecutorService;
    private ViewPagerAdapter viewPagerAdapter;
    private List<String> imageUrls;
    private List<ImageView> images;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    private int[] nums = {R.drawable.img_guide_one_cooperation, R.drawable.img_guide_two_advantage, R.drawable.img_guide_three_discount};
    private List<View> dots;
    private String accountTitles[] = {"昵称", "账号", "级别", "邀请码", "碳控因子", "碳控积分", "已冻结", "待激活"};
    private String platformTitles[] = {"总量", "已激活因子", "价格", "待激活因子", "碳控积分", "循环卷积分", "循环劵", "消费积分"};
    private List<String> accountAnswer;
    private List<String> platformAnswer;
    private List<String> noticeList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment_one, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        tvUpDown.setText("welcome to Carbon control factor");
        setBannerData();
        setNoticeInfo();
        simpleAccountAdapter = new SimpleAdapter(getActivity(), getAccountData(), R.layout.item_gv_info, new String[]{"title", "answer"}, new int[]{R.id.item_tv_title, R.id.item_tv_answer});
        gvAccountInfo.setAdapter(simpleAccountAdapter);
        setPlatformAnwserData();
    }

    /**
     * 获取个人信息数据
     */
    private List<String> getAccountAnwserData() {
        String userInfoJsonString = UserSharedPreference.getInstance().getUserInfo();
        AccountInfo accountInfo = JSONObject.parseObject(userInfoJsonString, AccountInfo.class);
        accountAnswer = new ArrayList<>();
        accountAnswer.add(accountInfo.getNickName());
        accountAnswer.add(accountInfo.getAcountId());
        accountAnswer.add(accountInfo.getLevel());
        accountAnswer.add(accountInfo.getInvatationCode());
        accountAnswer.add(accountInfo.getCcf());
        accountAnswer.add(accountInfo.getCcIntegral());
        accountAnswer.add(accountInfo.getFrozened());
        accountAnswer.add(accountInfo.getToActivate());
        return accountAnswer;
    }

    /**
     * 设置平台信息数据
     */
    private void setPlatformAnwserData() {
        platformAnswer = new ArrayList<>();
        HttpRequest.get(Constant.GET_DATA_HOST + API.GETPLATFORMINFO, new CCFHttpRequestCallback() {
            @Override
            protected void onDataSuccess(JSONObject data) {
                JSONObject jsonObject = data.getJSONObject("platforminfo");
                PlatformInfo platformInfo = JSONObject.parseObject(jsonObject.toJSONString(),PlatformInfo.class);
                platformAnswer.add(platformInfo.getTotal());
                platformAnswer.add(platformInfo.getActivated());
                platformAnswer.add(platformInfo.getPrice());
                platformAnswer.add(platformInfo.getToActivateCcf());
                platformAnswer.add(platformInfo.getCcIntegral());
                platformAnswer.add(platformInfo.getCycleIntegral());
                platformAnswer.add(platformInfo.getCycleStock());
                platformAnswer.add(platformInfo.getBonusPoints());
                List<Map<String, String>> list = new ArrayList<>();
                        if (platformAnswer!=null&&platformAnswer.size()>0){
                            for (int i = 0; i < platformTitles.length; i++) {
                                Map<String, String> map = new HashMap<>();
                                map.put("title", platformTitles[i]);
                                map.put("answer", platformAnswer.get(i));
                                list.add(map);
                    }
                }
                simplePlatformAdapter = new SimpleAdapter(getActivity(), list, R.layout.item_gv_info, new String[]{"title", "answer"}, new int[]{R.id.item_tv_title, R.id.item_tv_answer});
                gvPlatformInfo.setAdapter(simplePlatformAdapter);
            }

            @Override
            protected void onDataError(int code, boolean flag, String msg) {
                toast("获取平台信息失败");
            }
        });
    }

    /**
     * 设置上下滚动文本信息
     */
    private void setUpDownTextView(final List<ItemNews> itemNewsList) {
        tvUpDown.setSingleLine();
        tvUpDown.setGravity(Gravity.CENTER);
        tvUpDown.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
        tvUpDown.setTextSize(12);
        tvUpDown.setTextList(itemNewsList);
        tvUpDown.setDuring(500);
        tvUpDown.startAutoScroll();
        tvUpDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemNews itemNews = itemNewsList.get(tvUpDown.getCurrentIndex());
                RequestParams params = new RequestParams();
                params.addFormDataPart("NewsID",itemNews.getNewsId());
                HttpRequest.post(Constant.GET_DATA_HOST + API.GETNEWSNOTICE, params, new CCFHttpRequestCallback() {
                    @Override
                    protected void onDataSuccess(JSONObject data) {

                    }

                    @Override
                    protected void onDataError(int code, boolean flag, String msg) {

                    }
                });
            }
        });
    }

    /**
     * 获取公告信息
     */
    private void setNoticeInfo(){
        noticeList = new ArrayList<>();
        RequestParams params = new RequestParams();
        params.addFormDataPart("CurrentPageIndex",1);
        params.addFormDataPart("PageSize",4);
        /**
         * 1是公告 2是新闻
         */
        params.addFormDataPart("type",1);
        params.addFormDataPart("Orderby","ORDER BY IssueDate DESC");
        HttpRequest.post(Constant.GET_DATA_HOST + API.GETNEWSLIST, params, new CCFHttpRequestCallback() {
            @Override
            protected void onDataSuccess(JSONObject data) {
                JSONArray jsonArray = data.getJSONArray("Newslist");
                List<ItemNews> itemNewsList = JSONArray.parseArray(jsonArray.toJSONString(),ItemNews.class);
                setUpDownTextView(itemNewsList);
            }

            @Override
            protected void onDataError(int code, boolean flag, String msg) {
            toast("获取公告失败");
            }
        });
    }


    /**
     * 设置Banner的显示图片
     */
    private void setBannerData() {
        //显示的图片
        images = new ArrayList<ImageView>();
        imageUrls = new ArrayList<>();
        imageUrls.add("http://img01.taopic.com/141025/234987-1410250J11189.jpg");
        imageUrls.add("http://img1.3lian.com/2015/a1/84/d/95.jpg");
        imageUrls.add("http://img1.3lian.com/2015/a1/84/d/102.jpg");
        for (int i = 0; i < imageUrls.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(this).load(imageUrls.get(i)).fitCenter().error(nums[i]).placeholder(nums[i]).into(imageView);
            images.add(imageView);
        }

        dots = new ArrayList<>();
        dots.add(dot0);
        dots.add(dot1);
        dots.add(dot2);
        viewPagerAdapter = new ViewPagerAdapter();
        vp.setAdapter(viewPagerAdapter);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dots.get(position).setBackgroundResource(R.drawable.guide_circle_white);
                dots.get(oldPosition).setBackgroundResource(R.drawable.guide_circle_gray);
                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 得到个人信息数据
     * @return
     */
    private List<Map<String, String>> getAccountData() {
        List<Map<String, String>> list = new ArrayList<>();
        List<String> data = getAccountAnwserData();
        for (int i = 0; i < accountTitles.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("title", accountTitles[i]);
            map.put("answer", data.get(i));
            list.add(map);
        }
        return list;
    }

    @Override
    public void onStart() {
        super.onStart();
        /**
         * 开启线程池管理Banner
         */
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    class ViewPageTask implements Runnable {

        @Override
        public void run() {
            currentItem = (currentItem + 1) % images.size();
            mHandler.sendEmptyMessage(0);
        }
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (vp!=null){
                vp.setCurrentItem(currentItem);
            }
        }
    };

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageUrls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = images.get(position);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "" + (position + 1), Toast.LENGTH_SHORT).show();
                }
            });
            container.addView(imageView);
            return imageView;
        }
    }
}
