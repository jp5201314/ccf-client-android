package cn.cnlinfo.ccf.fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.orhanobut.logger.Logger;
import com.tendcloud.tenddata.TCAgent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.API;
import cn.cnlinfo.ccf.Constant;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.event.ShowMainPageEvent;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class CCMallFragment extends BaseFragment {
    @BindView(R.id.wv)
    WebView wv;
    private Unbinder unbinder;
    private static final String APP_CACAHE_DIRNAME = "/webcache";
    private static String phoneAndPassword;

    //收到html页面点击返回上一页执行显示上一页操作
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    wv.goBack();
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cc_mall,container,false);
        unbinder = ButterKnife.bind(this,view);
        phoneAndPassword = UserSharedPreference.getInstance().getPhoneAndPassword();
        setWvProperty();
        return view;
    }

    /**
     * 设置webView的属性
     */
    @SuppressLint("JavascriptInterface")
    private void setWvProperty(){
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);//设置支持JavaScript代码
        webSettings.setLoadWithOverviewMode(true);//网页适应手机屏幕大小
        webSettings.setDomStorageEnabled(true);//开启DOM storage API功能
        webSettings.setUseWideViewPort(true);//webview推荐使用的窗口
        webSettings.setLoadWithOverviewMode(true);//webview加载的页面的模式
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//滚动条风格
        webSettings.setSupportZoom(true);  //支持放大缩小
        webSettings.setBuiltInZoomControls(true);//隐藏缩放按钮
        //js调用android本地方法
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wv.addJavascriptInterface(new JsInterface(), "androidMethod");
        } else {
            wv.addJavascriptInterface(this, "androidMethod");
        }
        if (!TextUtils.isEmpty(phoneAndPassword)){
            wv.loadUrl(Constant.CCMALL_HOST+String.format(API.CCMALLLOGIN,phoneAndPassword.substring(0,phoneAndPassword.indexOf('/')),phoneAndPassword.substring(phoneAndPassword.indexOf('/')+1)));
        }
        webSettings.setSaveFormData(true);// 保存表单数据
        String cacheDirPath =getActivity().getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME; //缓存路径
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //缓存模式
        webSettings.setAppCachePath(cacheDirPath); //设置缓存路径
        webSettings.setAppCacheEnabled(true); //开启缓存功能
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //view.loadUrl(url);
                Logger.d(url);
            }
        });

        //点击网页上的返回按钮返回到上一页面
        wv.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
                    if(event.getAction()==KeyEvent.ACTION_DOWN){ //只处理一次
                        handler.sendEmptyMessage(1);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        destroyWebView();
        TCAgent.onPageEnd(getActivity(), "CC商城");
    }

    private void destroyWebView() {
        if (wv != null) {
            wv.pauseTimers();
            wv.removeAllViews();
            wv.destroy();
            wv = null;
        }
    }
    //操作js中函数调用本地的方法，方法名必须一致
    @JavascriptInterface
    public void setHomePage(){
        EventBus.getDefault().post(new ShowMainPageEvent());
    }
    //android api18之前是定义类来设置本地方法的，在之后就是使用@JavascriptInterfece这样的方式来设置本地方法
    public class JsInterface {
        public void setHomePage() {
            EventBus.getDefault().post(new ShowMainPageEvent());
        }
    }
}
