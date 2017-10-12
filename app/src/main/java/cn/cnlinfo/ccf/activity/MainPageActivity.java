package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.adapter.MainPageFragmentAdapter;
import cn.cnlinfo.ccf.fragment.CCMallFragment;
import cn.cnlinfo.ccf.fragment.CCUnionFragment;
import cn.cnlinfo.ccf.fragment.GaugePanelFragment;
import cn.cnlinfo.ccf.fragment.SettingCenterFragment;
import cn.cnlinfo.ccf.fragment.TradingCenterFragment;
import cn.cnlinfo.ccf.view.StopScrollViewPager;

public class MainPageActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.vp)
    StopScrollViewPager vp;
    @BindView(R.id.tv_gauage_panel)
    TextView tvGauagePanel;
    @BindView(R.id.tv_trading_center)
    TextView tvTradingCenter;
    @BindView(R.id.tv_cc_mall)
    TextView tvCcMall;
    @BindView(R.id.tv_cc_union)
    TextView tvCcUnion;
    @BindView(R.id.tv_setting_center)
    TextView tvSettingCenter;

    private List<Fragment> fragmentList;
    private MainPageFragmentAdapter pageFragmentAdapter;
    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        unbinder = ButterKnife.bind(this);
        //设置为false是停止滑动ViewPager切换Fragment
        vp.setStopScroll(false);
        init();

    }

    private void init(){
        fragmentList = new ArrayList<>();
        fragmentList = setFragmentList();
        if (fragmentList!=null&&fragmentList.size()>0) {
            pageFragmentAdapter = new MainPageFragmentAdapter(getSupportFragmentManager(), fragmentList);
            vp.setAdapter(pageFragmentAdapter);
        }
        registerOnClickListener();
    }

    private List<Fragment> setFragmentList(){
        fragmentList.add(new GaugePanelFragment());
        fragmentList.add(new TradingCenterFragment());
        fragmentList.add(new CCMallFragment());
        fragmentList.add(new CCUnionFragment());
        fragmentList.add(new SettingCenterFragment());
        return fragmentList;
    }

    private void registerOnClickListener(){
        tvGauagePanel.setOnClickListener(this);
        tvTradingCenter.setOnClickListener(this);
        tvCcMall.setOnClickListener(this);
        tvCcUnion.setOnClickListener(this);
        tvSettingCenter.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId){
            case R.id.tv_gauage_panel:
                vp.setCurrentItem(0);
                break;
            case R.id.tv_trading_center:
                vp.setCurrentItem(1);
                break;
            case R.id.tv_cc_mall:
                vp.setCurrentItem(2);
                break;
            case R.id.tv_cc_union:
                vp.setCurrentItem(3);
                break;
            case R.id.tv_setting_center:
                vp.setCurrentItem(4);
                break;
        }
    }
}
