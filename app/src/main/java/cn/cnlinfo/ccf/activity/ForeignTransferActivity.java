package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.view.CleanEditText;

public class ForeignTransferActivity extends BaseActivity {

    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.sp_transfer_type)
    Spinner spTransferType;
    @BindView(R.id.et_user_account)
    CleanEditText etUserAccount;
    @BindView(R.id.et_transfer_number)
    CleanEditText etTransferNumber;
    @BindView(R.id.et_safe_pass)
    CleanEditText etSafePass;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreign_transfer);
        unbinder = ButterKnife.bind(this);
        ibtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("对外互转");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
