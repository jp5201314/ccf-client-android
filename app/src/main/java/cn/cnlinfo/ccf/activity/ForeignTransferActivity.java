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
import cn.cnlinfo.ccf.view.CleanEditText;
import cn.finalteam.okhttpfinal.RequestParams;

public class ForeignTransferActivity extends BaseActivity {
    /**
     * [1:碳控因子,2:注册积分,]
     */
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
    private User user;
    private int typeId;

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
        initData();
    }

    private void  initData(){
        user = UserSharedPreference.getInstance().getUser();
        spTransferType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toTransfer();
            }
        });
    }

    /**
     * 检查输入框是否为空 对外互转
     * @return
     */
    private void toTransfer(){
        String accounrNum = etUserAccount.getText().toString();
        String transNum = etTransferNumber.getText().toString();
        int  transferNum = Integer.valueOf(transNum);
        String safePass = etSafePass.getText().toString();
        if (!TextUtils.isEmpty(accounrNum)&&!TextUtils.isEmpty(transNum)&&!TextUtils.isEmpty(safePass)){
           if (transferNum>0){
               RequestParams params = new RequestParams();
               params.addFormDataPart("type",typeId);
               params.addFormDataPart("sendID",user.getUserID());
               params.addFormDataPart("receiveID",accounrNum);
               params.addFormDataPart("largessValue",transferNum);
               
           }else {
              showMessage("数量不能小于0");

           }
        }else {
            showEditTextNoNull();
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
