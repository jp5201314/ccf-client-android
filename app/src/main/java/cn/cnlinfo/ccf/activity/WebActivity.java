package cn.cnlinfo.ccf.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cnlinfo.ccf.R;

public class WebActivity extends BaseActivity {

    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ibt_add)
    ImageButton ibtAdd;
    @BindView(R.id.wv)
    WebView wv;

    private Intent intent;
    private String url;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        ibtAdd.setVisibility(View.INVISIBLE);
        intent = getIntent();
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        url = intent.getStringExtra("url");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wv.addJavascriptInterface(new JsInterface(), "wv");
        } else {
            wv.addJavascriptInterface(this, "wv");
        }
        wv.loadUrl("file:///android_asset/js_webView");
        wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Logger.d(url+"\n"+message+"\n"+result.toString());
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                tvTitle.setText(title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                ibtBack.setImageBitmap(icon);
            }

        });

        wv.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.wv.getSource('<head>'+" +
                        "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                Logger.d("onPageFinished");
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);//不用系统默认浏览器
                return true;
            }
        });

        ibtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 调用js中的方法用wv的post方法中调用
                 */
                wv.post(new Runnable() {
                    @Override
                    public void run() {
                        wv.loadUrl("javascript:alerts()");
                    }
                });
            }
        });


    }

    @JavascriptInterface
    public void getSource(String msg) {
        Logger.d(msg);
        toast(msg);
    }

    @JavascriptInterface
    public void test(){
        toast("hello world");
    }

    public class JsInterface {
        public void test() {
            toast("hello world");
        }
    }
}
