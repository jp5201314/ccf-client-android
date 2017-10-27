package cn.cnlinfo.ccf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cnlinfo.ccf.API;
import cn.cnlinfo.ccf.Constant;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.entity.User;
import cn.cnlinfo.ccf.event.ErrorMessageEvent;
import cn.cnlinfo.ccf.net_okhttp.OKHttpManager;
import cn.cnlinfo.ccf.net_okhttp.OkHttpPostRequestBuilder;
import cn.cnlinfo.ccf.net_okhttp.UiHandlerCallBack;
import cn.cnlinfo.ccf.net_okhttpfinal.CCFHttpRequestCallback;
import cn.cnlinfo.ccf.utils.ObtainVerificationCode;
import cn.cnlinfo.ccf.utils.TimeExchange;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Call;

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

    @Override
    protected void onResume() {
        super.onResume();
        setVerificationCode();
    }

    public void toLogin(View view) {
        startLogin();
    }

    public void toRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void gainVerificationCode(View view) {
        setVerificationCode();
    }

    private void setVerificationCode() {
        String str = ObtainVerificationCode.createVerificationCode();
        tvGetVerificationCode.setText(str);
    }

    public void toForgetPass(View view) {
        startActivity(new Intent(this, ForgetPasswordActivity.class));
    }

    /**
     * 开始登陆
     */
    private void startLogin(){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String verificationCode = etVerificationCode.getText().toString();
        if (TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            toast("用户名或密码不能为空");
        }else {
            if (verificationCode!=null&&verificationCode.equals(tvGetVerificationCode.getText().toString().trim())){
                OkHttpPostRequestBuilder okHttpPostRequestBuilder = new OkHttpPostRequestBuilder(Constant.getHost()+ API.CCFLOGIN);
                okHttpPostRequestBuilder.put("username", username);
                okHttpPostRequestBuilder.put("password", password);
                OKHttpManager.post(okHttpPostRequestBuilder, "login", new UiHandlerCallBack() {

                            @Override
                            public void onFailure(Call call, IOException e) {
                                UserSharedPreference.getInstance().setJwtToken("0");
                                call.cancel();
                            }

                            @Override
                            public void success(JSONObject data) {
                                UserSharedPreference.getInstance().setJwtToken("1");
                                UserSharedPreference.getInstance().setIsFirstLogin(true);

                                Logger.d(TimeExchange.timestampIntoDate(data));
                                User user = data.getJSONObject("userinfo").toJavaObject(User.class);
                                Logger.d(user.toString());
                                if (user!=null){
                                    Intent intent = new Intent(LoginRegisterActivity.this, MainPageActivity.class);
                                    intent.putExtra("userinfo",user);
                                    startActivity(intent);
                                    LoginRegisterActivity.this.finish();
                                    showMessage("登录成功");
                                }
                            }

                            @Override
                            public void error(int status, String message) {
                                showMessage(status,message);
                            }

                            @Override
                            public void progress(int progress) {

                            }

                            @Override
                            public void failed(int code, String msg) {
                                showMessage(msg);
                            }
                        }
                );

               /* RequestParams params = new RequestParams();
                params.addFormDataPart("username",username);
                params.addFormDataPart("password",password);
                HttpRequest.post(Constant.getHost()+API.CCFLOGIN,params,new CCFHttpRequestCallback(){
                    @Override
                    protected void onDataSuccess(JSONObject data) {
                        Logger.json(data.toJSONString());
                    }

                    @Override
                    protected void onDataError(int code, boolean flag, String msg) {
                        Logger.d(code+"  "+flag+"  "+msg);
                    }
                });*/
            }else {
                toast("验证码不正确，请重新输入");
            }
        }

    }
}
