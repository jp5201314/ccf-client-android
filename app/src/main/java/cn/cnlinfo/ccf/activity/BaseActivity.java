package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.inter.IActivityFinish;
import cn.cnlinfo.ccf.inter.IComponentContainer;
import cn.cnlinfo.ccf.inter.ILifeCycleComponent;
import cn.cnlinfo.ccf.manager.AppManage;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class BaseActivity extends AppCompatActivity implements IComponentContainer,IActivityFinish {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManage.getInstance().addActivity(this);
    }

    @Override
    public void finishActivity() {

    }

    @Override
    public void addComponent(ILifeCycleComponent component) {

    }

    //判断用户是否已经登录
    public boolean isAllowLogin(){
        return !UserSharedPreference.getInstance().hasLogined();
    }
}
