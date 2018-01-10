package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.API;
import cn.cnlinfo.ccf.Constant;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.entity.PurCCFEntity;
import cn.cnlinfo.ccf.net_okhttpfinal.CCFHttpRequestCallback;
import cn.cnlinfo.ccf.view.CleanEditText;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class PurCCFActivity extends BaseActivity {

    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_user_level)
    TextView tvUserLevel;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_order_info)
    TextView tvOrderInfo;
    @BindView(R.id.tv_weixin_account)
    TextView tvWeixinAccount;
    @BindView(R.id.tv_alipay_account)
    TextView tvAlipayAccount;
    @BindView(R.id.tv_bank_account)
    TextView tvBankAccount;
    @BindView(R.id.ct_pur_num)
    CleanEditText ctPurNum;
    @BindView(R.id.ct_safe_pass)
    CleanEditText ctSafePass;
    @BindView(R.id.btn_confirm_pur)
    Button btnConfirmPur;
    private Unbinder unbinder;
    private String roomId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pur_ccf);
        unbinder = ButterKnife.bind(this);
        roomId = getIntent().getStringExtra("roomId");
        getPurCCFInfo();
    }

    /**
     * 购买碳控因子
     */
    private void purCCF() {
        if (!TextUtils.isEmpty(ctPurNum.getText().toString())&&!TextUtils.isEmpty(ctSafePass.getText().toString())){
            int num = Integer.valueOf(ctPurNum.getText().toString());
            String pass = ctSafePass.getText().toString();
            if (num!=0){
                RequestParams params = new RequestParams();
                params.addFormDataPart("purchaserID", UserSharedPreference.getInstance().getUser().getUserID());
                params.addFormDataPart("ccfNum", 3);
                params.addFormDataPart("roomID", roomId);
                params.addFormDataPart("",pass);
                HttpRequest.post(Constant.OPERATE_CCF_HOST + API.CLICKBUYCCF, params, new CCFHttpRequestCallback() {
                    @Override
                    protected void onDataSuccess(JSONObject data) {

                    }

                    @Override
                    protected void onDataError(int code, boolean flag, String msg) {
                        showMessage(code,msg);
                    }

                    @Override
                    public void onFailure(int errorCode, String msg) {
                        super.onFailure(errorCode, msg);
                        showMessage(errorCode,msg);
                    }
                });
            }else {
                toast("输入的数量不能为0");
            }
        }else {
            showEditTextNoNull();
        }
    }

    /**
     * 获取该订单详细展示的信息
     */
    private void getPurCCFInfo(){
        RequestParams params = new RequestParams();
        params.addFormDataPart("JobberId",roomId);
        HttpRequest.post(Constant.GET_DATA_HOST + API.GETJOBBERINFO, params, new CCFHttpRequestCallback() {
            @Override
            protected void onDataSuccess(JSONObject data) {
                PurCCFEntity purCCFEntity = JSONObject.parseObject(data.getJSONObject("JobberInfo").toJSONString(),PurCCFEntity.class);
                if (purCCFEntity!=null){
                    tvUserLevel.setText(purCCFEntity.getBusinessLev());
                    tvUserName.setText(purCCFEntity.getUserName());
                    tvOrderInfo.setText(String.format(tvOrderInfo.getText().toString(),purCCFEntity.getCCF(),purCCFEntity.getPrice()));
                    tvWeixinAccount.setText(purCCFEntity.getWebChat());
                    tvAlipayAccount.setText(purCCFEntity.getAliPay());
                    tvBankAccount.setText(purCCFEntity.getBankCode());
                }
            }

            @Override
            protected void onDataError(int code, boolean flag, String msg) {
                showMessage(code,msg);
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                showMessage(errorCode,msg);
            }
        });
    }
    @OnClick({R.id.btn_confirm_pur})
    public void click(View view){
        switch (view.getId()){
            case R.id.ibt_back:
                finish();
                break;
            case R.id.btn_confirm_pur:
                purCCF();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
