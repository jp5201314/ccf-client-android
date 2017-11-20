package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;


public class RecordCenterActivity extends BaseActivity {

    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_to_out)
    TextView tvToOut;
    @BindView(R.id.tv_to_self)
    TextView tvToSelf;
    @BindView(R.id.tv_step_record)
    TextView tvStepRecord;
    @BindView(R.id.tv_contribute_value_record)
    TextView tvContributeValueRecord;
    @BindView(R.id.tv_basic_contribution_rate_record)
    TextView tvBasicContributionRateRecord;
    @BindView(R.id.tv_conversion_record)
    TextView tvConversionRecord;
    @BindView(R.id.tv_e_wallet_record)
    TextView tvEWalletRecord;
    @BindView(R.id.tv_shopping_record)
    TextView tvShoppingRecord;
    @BindView(R.id.tv_deal_record)
    TextView tvDealRecord;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_center);
        unbinder = ButterKnife.bind(this);

        tvTitle.setText("记录中心");
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
