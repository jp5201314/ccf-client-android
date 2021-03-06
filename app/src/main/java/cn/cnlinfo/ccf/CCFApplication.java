package cn.cnlinfo.ccf;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tendcloud.tenddata.TCAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.cnlinfo.ccf.activity.LoginRegisterActivity;
import cn.cnlinfo.ccf.entity.ExitLoginEvent;
import cn.cnlinfo.ccf.event.ErrorMessageEvent;
import cn.cnlinfo.ccf.manager.ACache;
import cn.cnlinfo.ccf.manager.AppManage;
import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;


/**
 * Created by JP on 2017/10/11 0011.
 */

public class CCFApplication extends Application {

    private static Context mContext;
    private static CCFApplication INSTANCE;

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        //加入内存次泄漏检测
        refWatcher = LeakCanary.install(this);
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
        Fresco.initialize(mContext);

        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());
        builder.setDebug(true);
        OkHttpFinal.getInstance().updateCommonHeader("Accept","application/json");
    }

    public static RefWatcher getRefWatcher() {
        return INSTANCE.refWatcher;
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
        switch (code){
            case 500:
                toast("服务器正在维护，请稍后再试!!!");
                break;
            case -1:
                toast(msg);
                break;
            case -2:
                toast(msg);
                break;
                default:
                    toast(msg);
                    break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receivedExitLogin(ExitLoginEvent exitLoginEvent){
        UserSharedPreference.getInstance().logout();
        AppManage.getInstance().finishOther();
        jumpToLogin();
    }
    //分包的application添加的方法
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    private void toast(String msg){
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }

    protected boolean allowLogin(){
        return null==ACache.get(mContext).getAsString("isLogged");
    }

    public void jumpToLogin(){
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
