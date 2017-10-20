package cn.cnlinfo.ccf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.net_okhttp.OKHttpManager;
import cn.cnlinfo.ccf.net_okhttp.OkHttpPostRequestBuilder;
import cn.cnlinfo.ccf.net_okhttp.UiHandlerCallBack;
import cn.cnlinfo.ccf.utils.ObtainVerificationCode;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by JP on 2017/10/11 0011.
 */

public class LoginRegisterActivity extends BaseActivity {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.tv_get_verification_code)
    TextView tvGetVerificationCode;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_forget_pass)
    Button btnForgetPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        ButterKnife.bind(this);
    }

    public void toLogin(View view) {
        OkHttpPostRequestBuilder okHttpPostRequestBuilder = new OkHttpPostRequestBuilder("http://ccf.hrkji.com/RegUser.asmx/Login");
        okHttpPostRequestBuilder.put("username", 1001);
        okHttpPostRequestBuilder.put("password", 123456);
        OKHttpManager.post(okHttpPostRequestBuilder, "sdsa", new UiHandlerCallBack() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        UserSharedPreference.getInstance().setJwtToken(String.valueOf(false));
                        call.cancel();
                    }

                    @Override
                    public void success(JSONObject data) {
                        UserSharedPreference.getInstance().setJwtToken(String.valueOf(true));
                        Logger.json(data.toJSONString());
                        startActivity(new Intent(LoginRegisterActivity.this, MainPageActivity.class));
                        finish();
                    }

                    @Override
                    public void error(int status, String message) {

                    }

                    @Override
                    public void progress(int progress) {

                    }

                    @Override
                    public void failed(int code, String msg) {

                    }
                }
        );
    }


    public void toRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void gainVerificationCode(View view) {
        String str = ObtainVerificationCode.createVerificationCode();
        tvGetVerificationCode.setText(str);
    }

    public void toForgetPass(View view) {
        startActivity(new Intent(this, ForgetPasswordActivity.class));
    }
}
