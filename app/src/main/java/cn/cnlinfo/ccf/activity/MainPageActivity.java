package cn.cnlinfo.ccf.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tendcloud.tenddata.TCAgent;
import com.tendcloud.tenddata.TalkingDataSMS;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.adapter.MainPageFragmentAdapter;
import cn.cnlinfo.ccf.dialog.DialogCreater;
import cn.cnlinfo.ccf.entity.User;
import cn.cnlinfo.ccf.fragment.CCMallFragment;
import cn.cnlinfo.ccf.fragment.CCUnionFragment;
import cn.cnlinfo.ccf.fragment.GaugePanelFragment;
import cn.cnlinfo.ccf.fragment.MainPageFragment;
import cn.cnlinfo.ccf.fragment.TradingCenterFragment;
import cn.cnlinfo.ccf.view.StopScrollViewPager;

public class MainPageActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.tv_main_page)
    TextView tvMainPage;

    private List<Fragment> fragmentList;
    private MainPageFragmentAdapter pageFragmentAdapter;
    private Unbinder unbinder;
    private User user;
    private Intent intent;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        unbinder = ButterKnife.bind(this);
        TCAgent.onPageStart(this, "主页");
        validLoadGuidePage();
        //设置为false是停止滑动ViewPager切换Fragment
        vp.setStopScroll(true);
        init();
    }

    /**
     * 验证是否加载引导页
     */
    private void validLoadGuidePage() {
        if (!validNewVersion()) {
            if (validLogin()) {
                if (UserSharedPreference.getInstance().getIsFirstLogin()){
                    showRiskWarningDialog();
                    UserSharedPreference.getInstance().setIsFirstLogin(false);
                }else {

                }
            } else {
                finish();
            }
        }
    }

    /**
     * 弹出风险提示dialog
     */
    private void showRiskWarningDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.dialog_risk_warning, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_close_dialog);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alertDialog!=null){
                    alertDialog.dismiss();
                }
            }
        });
        alertDialog.show();
    }

    private void init() {
        intent = getIntent();
        user = intent.getParcelableExtra("userinfo");
        fragmentList = new ArrayList<>();
        fragmentList = setFragmentList();
        if (fragmentList != null && fragmentList.size() > 0) {
            pageFragmentAdapter = new MainPageFragmentAdapter(getSupportFragmentManager(), fragmentList);
            vp.setAdapter(pageFragmentAdapter);
        }
        registerOnClickListener();
        tvMainPage.setBackgroundColor(getResources().getColor(R.color.color_blue_4d8cd6));
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setTvMainPageBackgroundColor();
                        break;
                    case 1:
                        setTvGauagePanelBackgroundColor();
                        break;
                    case 2:
                        setTvTradingCenterBackgroundColor();
                        break;
                    case 3:
                        setTvCcMallBackgroundColor();
                        break;
                    case 4:
                        setTvCcUnionBackgroundColor();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<Fragment> setFragmentList() {
        MainPageFragment mainPageFragment = new MainPageFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("userinfo", user);
        mainPageFragment.setArguments(bundle);
        fragmentList.add(mainPageFragment);
        fragmentList.add(new GaugePanelFragment());
        fragmentList.add(new TradingCenterFragment());
        fragmentList.add(new CCMallFragment());
        fragmentList.add(new CCUnionFragment());
        return fragmentList;
    }

    private void registerOnClickListener() {
        tvMainPage.setOnClickListener(this);
        tvGauagePanel.setOnClickListener(this);
        tvTradingCenter.setOnClickListener(this);
        tvCcMall.setOnClickListener(this);
        tvCcUnion.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        TCAgent.onPageEnd(this, "主页");
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.tv_main_page:
                vp.setCurrentItem(0, false);
                setTvMainPageBackgroundColor();
                break;
            case R.id.tv_gauage_panel:
                //smoothScroll为false就是去除切换fragment的动画效果
                vp.setCurrentItem(1, false);
                setTvGauagePanelBackgroundColor();
                break;
            case R.id.tv_trading_center:
                vp.setCurrentItem(2, false);
                setTvTradingCenterBackgroundColor();
                break;
            case R.id.tv_cc_mall:
                vp.setCurrentItem(3, false);
                setTvCcMallBackgroundColor();
                break;
            case R.id.tv_cc_union:
                vp.setCurrentItem(4, false);
                setTvCcUnionBackgroundColor();
                break;

        }
    }

    private void setTvMainPageBackgroundColor() {
        tvMainPage.setBackgroundColor(getResources().getColor(R.color.color_blue_4d8cd6));
        tvGauagePanel.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
        tvCcUnion.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
        tvCcMall.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
        tvTradingCenter.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
    }

    private void setTvGauagePanelBackgroundColor() {
        tvMainPage.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
        tvGauagePanel.setBackgroundColor(getResources().getColor(R.color.color_blue_4d8cd6));
        tvCcUnion.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
        tvCcMall.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
        tvTradingCenter.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
    }

    private void setTvCcUnionBackgroundColor() {
        tvMainPage.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
        tvGauagePanel.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
        tvCcUnion.setBackgroundColor(getResources().getColor(R.color.color_blue_4d8cd6));
        tvCcMall.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
        tvTradingCenter.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
    }

    private void setTvCcMallBackgroundColor() {
        tvMainPage.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
        tvGauagePanel.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
        tvCcUnion.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
        tvCcMall.setBackgroundColor(getResources().getColor(R.color.color_blue_4d8cd6));
        tvTradingCenter.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
    }

    private void setTvTradingCenterBackgroundColor() {
        tvMainPage.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
        tvGauagePanel.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
        tvCcUnion.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
        tvCcMall.setBackgroundColor(getResources().getColor(R.color.color_white_faf9f9));
        tvTradingCenter.setBackgroundColor(getResources().getColor(R.color.color_blue_4d8cd6));
    }
}
