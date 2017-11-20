package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.view.CleanEditText;

public class OnlineWalletActivity extends BaseActivity {

    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_wallet_balance)
    TextView tvWalletBalance;
    @BindView(R.id.et_roll_out_number)
    CleanEditText etRollOutNumber;
    @BindView(R.id.et_roll_out_address)
    CleanEditText etRollOutAddress;
    @BindView(R.id.et_safe_pass)
    CleanEditText etSafePass;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.rv)
    RecyclerView rv;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_wallet);
        unbinder = ButterKnife.bind(this);
        ibtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTitle.setText("在线钱包");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
