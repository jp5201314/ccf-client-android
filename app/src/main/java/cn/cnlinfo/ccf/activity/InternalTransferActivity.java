package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.API;
import cn.cnlinfo.ccf.Constant;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.entity.User;
import cn.cnlinfo.ccf.net_okhttpfinal.CCFHttpRequestCallback;
import cn.cnlinfo.ccf.utils.SpinnerUtils;
import cn.cnlinfo.ccf.view.CleanEditText;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class InternalTransferActivity extends BaseActivity {
    /**
     * [3:注册积分转产品积分,4:注册积分转消费积分,5:消费积分转产品积分,6:消费积分转碳控因子,
     --7:消费积分转循环积分,8:碳控因子转产品积分,9:碳控因子转消费积分,10:碳控因子转循环积分,11:循环积分转产品积分]
     */
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
    private  User user;
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
        showWaitingDialog(true);
        num = etTransferNumber.getText().toString();
        safePass = etSafePass.getText().toString();
        if (!TextUtils.isEmpty(num)&&!TextUtils.isEmpty(safePass)){
            RequestParams params = new RequestParams();
            params.addFormDataPart("type",typeId);
            params.addFormDataPart("sendID",user.getUserID());
            params.addFormDataPart("receiveID",user.getUserID());
            params.addFormDataPart("largessValue",num);
            HttpRequest.post(Constant.GET_MESSAGE_CODE_HOST + API.USERTRANSFER, params, new CCFHttpRequestCallback() {

                @Override
                protected void onDataSuccess(JSONObject data) {
                    showWaitingDialog(false);

                }

                @Override
                protected void onDataError(int code, boolean flag, String msg) {
                    showWaitingDialog(false);
                    showMessage(code,msg);
                }
            });

        }else {
            toast("输入框不能为空");
        }
    }
    private void init() {
        user = UserSharedPreference.getInstance().getUser();
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
