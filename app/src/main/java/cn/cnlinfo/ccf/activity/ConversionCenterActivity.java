package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;

public class ConversionCenterActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_ccf_conversion)
    TextView tvCcfConversion;
    @BindView(R.id.tv_cycle_coupon_conversion)
    TextView tvCycleCouponConversion;
    @BindView(R.id.tv_consumption_points_conversion)
    TextView tvConsumptionPointsConversion;

    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_center);
        unbinder = ButterKnife.bind(this);

        tvTitle.setText("兑换中心");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case R.id.tv_ccf_conversion:
               break;
               case R.id.tv_cycle_coupon_conversion:
                   break;
                   case R.id.tv_consumption_points_conversion:
                       break;
        }
    }
}
