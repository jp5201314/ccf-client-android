package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;

public class PlatformParameterActivity extends BaseActivity {

    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.fl_title)
    FrameLayout flTitle;
    @BindView(R.id.tv_total_ccf)
    TextView tvTotalCcf;
    @BindView(R.id.tv_total_rights)
    TextView tvTotalRights;
    @BindView(R.id.tv_total_current_rights)
    TextView tvTotalCurrentRights;
    @BindView(R.id.tv_alive_total)
    TextView tvAliveTotal;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.tv_total_tba)
    TextView tvTotalTba;
    @BindView(R.id.tv_cc_points)
    TextView tvCcPoints;
    @BindView(R.id.tv_available_consume_points)
    TextView tvAvailableConsumePoints;
    @BindView(R.id.tv_wait_free_consume_points)
    TextView tvWaitFreeConsumePoints;
    @BindView(R.id.tv_cc_volume)
    TextView tvCcVolume;
    @BindView(R.id.tv_wait_free_cc_points)
    TextView tvWaitFreeCcPoints;
    @BindView(R.id.tv_frozen_amount)
    TextView tvFrozenAmount;
    @BindView(R.id.tv_current_cycle_package_amount)
    TextView tvCurrentCyclePackageAmount;
    @BindView(R.id.tv_current_price)
    TextView tvCurrentPrice;
    @BindView(R.id.tv_init_total_amount)
    TextView tvInitTotalAmount;
    @BindView(R.id.tv_current_account_amount)
    TextView tvCurrentAccountAmount;
    @BindView(R.id.tv_current_difficult_point)
    TextView tvCurrentDifficultPoint;
    @BindView(R.id.tv_province_total)
    TextView tvProvinceTotal;
    @BindView(R.id.tv_city_total)
    TextView tvCityTotal;
    @BindView(R.id.tv_county_total)
    TextView tvCountyTotal;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform_parameter);
        unbinder = ButterKnife.bind(this);
        tvTitle.setText("平台参数");
        ibtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
