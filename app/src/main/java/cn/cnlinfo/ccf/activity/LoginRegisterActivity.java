package cn.cnlinfo.ccf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.Constant;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.entity.User;
import cn.cnlinfo.ccf.utils.ObtainVerificationCode;
import cn.cnlinfo.ccf.utils.RxUtils;
import rx.Observer;

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
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setVerificationCode();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
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
    private void startLogin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String verificationCode = etVerificationCode.getText().toString();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            toast("用户名或密码不能为空");
        } else {
            if (verificationCode != null && verificationCode.toLowerCase().equals(tvGetVerificationCode.getText().toString().trim().toLowerCase())) {
                RxUtils.getLoginObserable(Constant.getHost(), username, password).subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                        toast("登录成功");
                    }
                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        Logger.d(user.toString());
                        UserSharedPreference.getInstance().setJwtToken("1");
                        UserSharedPreference.getInstance().setIsFirstLogin(true);
                        UserSharedPreference.getInstance().setUser(user);
                        Intent intent = new Intent(LoginRegisterActivity.this, MainPageActivity.class);
                        startActivity(intent);
                        LoginRegisterActivity.this.finish();
                    }
                });
            }else {
                toast("验证码不正确");
            }
        }
    }
}
