package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
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
import cn.cnlinfo.ccf.view.CleanEditText;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class ResetPasswordActivity extends BaseActivity {

    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ct_set_login_pass)
    CleanEditText ctSetLoginPass;
    @BindView(R.id.ct_verify_login_pass)
    CleanEditText ctVerifyLoginPass;
    @BindView(R.id.ct_set_safe_pass)
    CleanEditText ctSetSafePass;
    @BindView(R.id.ct_verify_safe_pass)
    CleanEditText ctVerifySafePass;
    @BindView(R.id.btn_ok)
    Button btnOk;
    private Unbinder unbinder;
    private String setLoginPass;
    private String verifyLoginPass;
    private String setSafePass;
    private String verifySafePass;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        unbinder = ButterKnife.bind(this);
        tvTitle.setText("设置新密码");
        user = JSON.parseObject(UserSharedPreference.getInstance().getUserInfo(),User.class);
        gainData();
        ibtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSetNewPass();
            }
        });
    }

    private void toSetNewPass(){
      if (!TextUtils.isEmpty(setLoginPass)&&!TextUtils.isEmpty(verifyLoginPass)&&!TextUtils.isEmpty(setSafePass)&&!TextUtils.isEmpty(verifySafePass)){
          if (setLoginPass.equals(verifyLoginPass)&&setSafePass.equals(verifySafePass)){
              RequestParams params = new RequestParams();
              params.addFormDataPart("uCode",user.getUsername());
              params.addFormDataPart("pwd",setLoginPass);
              params.addFormDataPart("pwd",verifyLoginPass);
              params.addFormDataPart("pwd2",setSafePass);
              params.addFormDataPart("pwd2",verifySafePass);
              HttpRequest.post(Constant.getHost() + API.CCFRESETPASSWORD, params, new CCFHttpRequestCallback() {
                  @Override
                  protected void onDataSuccess(JSONObject data) {
                      showMessage("设置成功");
                  }

                  @Override
                  protected void onDataError(int code, boolean flag, String msg) {
                        showMessage(code,msg);
                  }
              });
          }else {
              showMessage("密码输入不一致");
          }

      }else {
          showMessage("输入框不能为空");
      }
    }

    private void gainData(){
        setLoginPass = ctSetLoginPass.getText().toString();
        verifyLoginPass = ctVerifyLoginPass.getText().toString();
        setSafePass = ctSetSafePass.getText().toString();
        verifySafePass = ctVerifySafePass.getText().toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
