package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.entity.User;
import cn.cnlinfo.ccf.utils.SpinnerUtils;
import cn.cnlinfo.ccf.view.CleanEditText;
import cn.finalteam.okhttpfinal.RequestParams;

public class InternalTransferActivity extends BaseActivity {

    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.sp_transfer_type)
    Spinner spTransferType;
    @BindView(R.id.et_transfer_number)
    CleanEditText etTransferNumber;
    @BindView(R.id.et_safe_pass)
    CleanEditText etSafePass;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    private String num;
    private String safePass;
    private int typeId;

    private Unbinder unbinder;

    private  String[] transferType = {};
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_transfer);
        unbinder = ButterKnife.bind(this);
        tvTitle.setText("内部互转");
        ibtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toTransfer();
            }
        });
    }

    private void toTransfer(){
        num = etTransferNumber.getText().toString();
        safePass = etSafePass.getText().toString();
        if (!TextUtils.isEmpty(num)&&!TextUtils.isEmpty(safePass)){
            RequestParams params = new RequestParams();
            params.addFormDataPart("type",typeId);
            params.addFormDataPart("sendID",user.getId());
            params.addFormDataPart("receiveID",user.getId());
            params.addFormDataPart("largessValue",num);

        }else {
            toast("输入框不能为空");
        }
    }
    private void init() {
        user = UserSharedPreference.Companion.getInstance().getUser();
        transferType = getResources().getStringArray(R.array.spinner_internal_transfer);
        spTransferType.setAdapter(SpinnerUtils.getArrayAdapter(this,transferType));
        spTransferType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if (transferType[position]=="碳控因子转碳控积分"){
                   typeId = 4;
               }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
