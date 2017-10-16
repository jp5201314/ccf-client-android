package cn.cnlinfo.ccf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.cnlinfo.ccf.R;

/**
 * Created by JP on 2017/10/11 0011.
 */

public class LoginRegisterActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

    }

    public void toLogin(View view) {
        startActivity(new Intent(this,MainPageActivity.class));
    }


    public void toRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
