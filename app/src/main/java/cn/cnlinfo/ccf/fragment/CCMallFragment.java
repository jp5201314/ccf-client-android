package cn.cnlinfo.ccf.fragment;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
 * https://blog.csdn.net/qq_34584049/article/details/78280815 资料查询
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
        //清缓存和记录，缓存引起的白屏
        wv.clearCache(true);
        wv.clearHistory();
        wv.requestFocus();//获取请求焦点
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);//设置支持JavaScript代码
        webSettings.setLoadWithOverviewMode(true);//网页适应手机屏幕大小
        webSettings.setDomStorageEnabled(true);//开启DOM storage API功能    解决对某些标签的不支持出现白屏
        webSettings.setUseWideViewPort(true);//webview推荐使用的窗口
        webSettings.setLoadWithOverviewMode(true);//webview加载的页面的模式
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//滚动条风格
        webSettings.setSupportZoom(false);  //支持放大缩小
        webSettings.setBuiltInZoomControls(true);//隐藏缩放按钮
        //js调用android本地方法
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wv.addJavascriptInterface(new JsInterface(), "androidMethod");
        } else {
            wv.addJavascriptInterface(this, "androidMethod");
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webSettings.setLoadsImagesAutomatically(true);
        } else {
           webSettings.setLoadsImagesAutomatically(false);
        }
        wv.loadUrl(Constant.CCMALL_HOST+String.format(API.CCMALLLOGIN,phoneAndPassword.substring(0,phoneAndPassword.indexOf('/')),phoneAndPassword.substring(phoneAndPassword.indexOf('/')+1)));
        webSettings.setSaveFormData(true);// 保存表单数据
        String cacheDirPath =getActivity().getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME; //缓存路径
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);  //缓存模式
        webSettings.setAllowFileAccess(true);//允许文件访问
        webSettings.setAppCachePath(cacheDirPath); //设置缓存路径
        webSettings.setDatabasePath(cacheDirPath); //设置数据库缓存路径
        webSettings.setAppCacheEnabled(true); //开启缓存功能  可以通过setAppCacheEnabled方法来控制webview是否有缓存：
        webSettings.setDatabaseEnabled(true);//开启数据库缓存
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(!webSettings.getLoadsImagesAutomatically()) {
                    webSettings.setLoadsImagesAutomatically(true);//在页面加载完成后再加载图片
                }
            }

       /*     @Override
       //加上后直接出现白屏状态
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不另跳浏览器
                // 在2.3上面不加这句话，可以加载出页面，在4.0上面必须要加入，不然出现白屏
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    view.loadUrl(url);
                    wv.stopLoading();
                    return true;
                }
                return false;
            }*/

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();  // 接受所有网站的证书
                super.onReceivedSslError(view, handler, error);
            }
        });
        //想要在webview上弹出提示框，重写这个客户端,获取信息制定本地dialog显示
       /* wv.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });*/

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
