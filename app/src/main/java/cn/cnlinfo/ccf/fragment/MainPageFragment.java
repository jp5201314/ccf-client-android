package cn.cnlinfo.ccf.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

<<<<<<< HEAD
import com.orhanobut.logger.Logger;
=======
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.LayoutBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
>>>>>>> main_page

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
<<<<<<< HEAD
import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.inter.IObtainCallByTag;
import cn.cnlinfo.ccf.net_okhttp.OKHttpManager;
import okhttp3.Call;
=======
import cn.cnlinfo.ccf.view.StopScrollViewPager;
>>>>>>> main_page

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class MainPageFragment extends BaseFragment {

<<<<<<< HEAD
    @BindView(R.id.tv_main_page)
    TextView tvMainPage;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_stop)
    Button btnStop;
    @BindView(R.id.btn_exit)
    Button btnExit;
=======
    @BindView(R.id.indicator)
    FixedIndicatorView indicator;
    @BindView(R.id.vp)
    StopScrollViewPager vp;
    private IndicatorViewPager indicatorViewPager;
    private IndicatorViewPager.IndicatorFragmentPagerAdapter adapter;
>>>>>>> main_page
    private Unbinder unbinder;
    private final String[] TITLES = {"主页信息", "循环包", "分享信息"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        unbinder = ButterKnife.bind(this, view);
<<<<<<< HEAD
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnExit.setOnClickListener(this);
=======
        vp.setStopScroll(true);
        setIndicator();
>>>>>>> main_page
        return view;
    }


    private void setIndicator() {
        float unSelectSize = 15;
        float selectSize = 15;
        OnTransitionTextListener listener = new OnTransitionTextListener() {
            @Override
            public TextView getTextView(View tabItemView, int position) {
                return (TextView) tabItemView.findViewById(R.id.tv_tab_text);
            }
        };
        indicator.setOnTransitionListener(listener.setColor(getResources().getColor(R.color.color_green_73c482),
                getResources().getColor(R.color.color_black_0e1214)).setSize(selectSize, unSelectSize));
        //将指示器和ViewPager绑定在一起
        indicatorViewPager = new IndicatorViewPager(indicator, vp);
        adapter =  new ItemViewPagerIndicator(getChildFragmentManager());
        indicatorViewPager.setAdapter(adapter);
        indicatorViewPager.setPageOffscreenLimit(3);
        indicator.setScrollBar(new LayoutBar(getActivity(), R.layout.indicator_scroll_bar, ScrollBar.Gravity.BOTTOM));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

<<<<<<< HEAD
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_start:
              /*
              /**
              *测试同步的get post方式
              /
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        //final Response response = OKHttpManager.syncGet(new OkHttpGetRequestBuilder("https://api.douban.com/v2/movie/top250?start=0&count=10"), "mainfragment");
                        OkHttpPostRequestBuilder okHttpPostRequestBuilder =  new OkHttpPostRequestBuilder("http://ccf.hrkji.com/RegUser.asmx/Login");
                        okHttpPostRequestBuilder.put("username",1001);
                        okHttpPostRequestBuilder.put("password",123456);
                        final Response response = OKHttpManager.syncPost(okHttpPostRequestBuilder,"mainfragment");
                        try {
                            final String content = response.body().string();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvMainPage.setText(content);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();*/
                /**
                 * 测试异步的get post方式
                 */
             /* OKHttpManager.get(new OkHttpGetRequestBuilder("https://api.douban.com/v2/movie/top250?start=0&count=10"), "mainfragment", new UiHandlerCallBack() {
                  @Override
                  public void success(JSONObject data) {
                    Logger.json(data.toJSONString());
                  }

                  @Override
                  public void error(int status, String message) {

                  }

                  @Override
                  public void progress(int progress) {

                  }

                  @Override
                  public void failed(int code, String msg) {

                  }

                  @Override
                  public void onFailure(Call call, IOException e) {

                  }

              });*/

               /* OkHttpPostRequestBuilder okHttpPostRequestBuilder = new OkHttpPostRequestBuilder("http://ccf.hrkji.com/RegUser.asmx/Login");
                okHttpPostRequestBuilder.put("username", 1001);
                okHttpPostRequestBuilder.put("password", 123456);
                OKHttpManager.post(okHttpPostRequestBuilder, "sdsa", new UiHandlerCallBack() {

                            @Override
                            public void onFailure(Call call, IOException e) {
                                call.cancel();
                            }

                            @Override
                            public void success(JSONObject data) {
                                Logger.json(data.toJSONString());
                            }

                            @Override
                            public void error(int status, String message) {

                            }

                            @Override
                            public void progress(int progress) {

                            }

                            @Override
                            public void failed(int code, String msg) {

                            }
                        }
                );*/

                break;
            case R.id.btn_stop:
                OKHttpManager.findTagCall("mainfragment", this);
                break;

            case R.id.btn_exit:
                UserSharedPreference.getInstance().removeJwtToken();
                UserSharedPreference.getInstance().removeUser();
                getActivity().finish();
                break;
=======
    class ItemViewPagerIndicator extends IndicatorViewPager.IndicatorFragmentPagerAdapter {


        public ItemViewPagerIndicator(FragmentManager fragmentManager) {
            super(fragmentManager);
>>>>>>> main_page
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item_tab, container, false);
                TextView tv_tab = (TextView) convertView.findViewById(R.id.tv_tab_text);
                tv_tab.setTextSize(12);
                tv_tab.setText(TITLES[position]);
            }
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new MainPageInfoFragment();
                    break;
                case 1:
                    fragment = new CyclePackageFragment();
                    break;
                case 2:
                    fragment = new ShareQRCodeFragment();
                    break;
                default:
                    break;
            }
            return fragment;
        }

    }
}
