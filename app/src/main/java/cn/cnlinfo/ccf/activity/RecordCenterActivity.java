package cn.cnlinfo.ccf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;


public class RecordCenterActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_center);
        unbinder = ButterKnife.bind(this);

        tvTitle.setText("记录中心");
        setClickListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    private void setClickListener() {
        ibtBack.setOnClickListener(this);
        tvContributeValueRecord.setOnClickListener(this);
        tvDealRecord.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ibt_back:
                finish();
                break;
            /**
             * 对外转账记录
             * */
            case R.id.tv_to_out:
                break;
            /**
             * 自身转账记录
             * */
            case R.id.tv_to_self:
                break;
            /**
             * 步行记录
             * */
            case R.id.tv_step_record:
                break;
            /**
             * 贡献值记录
             * */
            case R.id.tv_contribute_value_record:
                startActivity(new Intent(this, ContributeValueRecordActivity.class));
                break;
            /**
             * 基础贡献率记录
             * */
            case R.id.tv_basic_contribution_rate_record:
                break;
            /**
             * 兑换记录
             * */
            case R.id.tv_conversion_record:
                break;
            /**
             * 电子钱包转账记录
             * */
            case R.id.tv_e_wallet_record:
                break;
            /**
             * 购物记录
             * */
            case R.id.tv_shopping_record:
                break;
            /**
             * 交易记录
             * */
            case R.id.tv_deal_record:
                break;
            default:
                break;
        }
    }
}
