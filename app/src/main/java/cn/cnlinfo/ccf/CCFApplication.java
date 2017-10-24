package cn.cnlinfo.ccf;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.tendcloud.tenddata.TCAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.cnlinfo.ccf.activity.LoginRegisterActivity;
import cn.cnlinfo.ccf.event.ErrorMessageEvent;
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
              /* 初始化talking data*/
        if (!BuildConfig.DEBUG) {
            TCAgent.LOG_ON = true;
            TCAgent.init(this);
            TCAgent.setReportUncaughtExceptions(true);
        }
        Logger.init("CCFinal").hideThreadInfo();
        EventBus.getDefault().register(mContext);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        EventBus.getDefault().unregister(mContext);
    }

    /**
     * 200是登陆成功
     * -1是您的帐号不存在或者密码输入有误，请查证后再次尝试登录！
     * -2是用户名已存在，请重新输入
     * -14
     * @param errorMessageEvent
     */
    //接收EventBus发送来的信息并作相应处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showErrorMessage(ErrorMessageEvent errorMessageEvent){
        int code = errorMessageEvent.getErrorCode();
        String msg = errorMessageEvent.getMsg();
        toast(msg);
    }

    private void toast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
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
