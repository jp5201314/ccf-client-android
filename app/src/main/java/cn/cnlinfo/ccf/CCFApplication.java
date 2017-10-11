package cn.cnlinfo.ccf;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import org.greenrobot.eventbus.EventBus;

import cn.cnlinfo.ccf.activity.LoginRegisterActivity;
import cn.cnlinfo.ccf.manager.ACache;


/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class CCFApplication extends Application {

    private static Context mContext;
    private static CCFApplication INSTANCE;


    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        mContext = getApplicationContext();

        EventBus.getDefault().register(mContext);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        EventBus.getDefault().unregister(mContext);
    }

    protected boolean allowLogin(){
        return null==ACache.get(mContext).getAsString("isLogged");
    }

    protected void jumpToLogin(){
        if (!allowLogin()) return;
        ACache.get(mContext).put("isLogged",true,5);
        Intent intent = new Intent(mContext, LoginRegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public static synchronized CCFApplication getInstance() {
        return INSTANCE;
    }

    public static synchronized Context getContext(){return mContext;}
}
