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
        setClickListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void setClickListener(){
        ibtBack.setOnClickListener(this);
        tvContributeValueRecord.setOnClickListener(this);
        tvDealRecord.setOnClickListener(this);
        tvToOut.setOnClickListener(this);
        tvToSelf.setOnClickListener(this);
        tvStepRecord.setOnClickListener(this);
        tvBasicContributionRateRecord.setOnClickListener(this);
        tvConversionRecord.setOnClickListener(this);
        tvEWalletRecord.setOnClickListener(this);
        tvShoppingRecord.setOnClickListener(this);
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
                startActivity(new Intent(this,ExternalTransferActivity.class));
                break;
                /**
                 * 自身转账记录
                 * */
            case R.id.tv_to_self:
                startActivity(new Intent(this,ItselfTransferRecordsActivity.class));
                break;
                /**
                 * 步行记录
                 * */
            case R.id.tv_step_record:
                startActivity(new Intent(this,WalkRecordActivity.class));
                break;
            /**
             * 贡献值记录
             * */
            case R.id.tv_contribute_value_record:
                startActivity(new Intent(this,ContributeValueRecordActivity.class));
                break;
            /**
             * 基础贡献率记录
             * */
            case R.id.tv_basic_contribution_rate_record:
                startActivity(new Intent(this,BasicContributionRateRecordActivity.class));
                break;
            /**
             * 兑换记录
             * */
            case R.id.tv_conversion_record:
                startActivity(new Intent(this,ConversionRecordActivity.class));
                break;
            /**
             * 电子钱包转账记录
             * */
            case R.id.tv_e_wallet_record:
                startActivity(new Intent(this,Wallet_Record_Activity.class));
                break;
            /**
             * 购物记录
             * */
            case R.id.tv_shopping_record:
                startActivity(new Intent(this,ShoppingRecordActivity.class));
                break;
            /**
             * 交易记录
             * */
            case R.id.tv_deal_record:
                startActivity(new Intent(this,TransactionRecordActivity.class));
                break;
            default:
                break;
        }
    }
}
