package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.view.CleanEditText;

public class UpdatePasswordActivity extends BaseActivity {

    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_login_pass)
    CleanEditText etLoginPass;
    @BindView(R.id.et_new_login_pass)
    CleanEditText etNewLoginPass;
    @BindView(R.id.et_verify_login_pass)
    CleanEditText etVerifyLoginPass;
    @BindView(R.id.et_safe_pass)
    CleanEditText etSafePass;
    @BindView(R.id.et_new_safe_pass)
    CleanEditText etNewSafePass;
    @BindView(R.id.et_verify_safe_pass)
    CleanEditText etVerifySafePass;
    @BindView(R.id.btn_affirm_update)
    Button btnAffirmUpdate;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        ButterKnife.bind(this);
        tvTitle.setText("修改密码");
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
