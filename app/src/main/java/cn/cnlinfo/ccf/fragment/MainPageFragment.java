package cn.cnlinfo.ccf.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.inter.IObtainCallByTag;
import cn.cnlinfo.ccf.net_okhttp.OKHttpManager;
import cn.cnlinfo.ccf.net_okhttp.OkHttpGetRequestBuilder;
import cn.cnlinfo.ccf.net_okhttp.OkHttpPostRequestBuilder;
import cn.cnlinfo.ccf.net_okhttp.OkHttpRequestBuilder;
import cn.cnlinfo.ccf.net_okhttp.UiHandlerCallBack;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class MainPageFragment extends BaseFragment implements View.OnClickListener,IObtainCallByTag{

    @BindView(R.id.tv_main_page)
    TextView tvMainPage;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_stop)
    Button btnStop;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_center, container, false);
        unbinder = ButterKnife.bind(this, view);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_start:
              /*
              /**
              *测试同步的get post方式
              /
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        //final Response response = OKHttpManager.syncGet(new OkHttpGetRequestBuilder("https://api.douban.com/v2/movie/top250?start=0&count=10"), "mainfragment");
                        OkHttpPostRequestBuilder okHttpPostRequestBuilder =  new OkHttpPostRequestBuilder("http://ccf.hrkji.com/RegUser.asmx/Login");
                        okHttpPostRequestBuilder.put("username",1001);
                        okHttpPostRequestBuilder.put("password",123456);
                        final Response response = OKHttpManager.syncPost(okHttpPostRequestBuilder,"mainfragment");
                        try {
                            final String content = response.body().string();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvMainPage.setText(content);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();*/
            /**
             * 测试异步的get post方式
             */
              OKHttpManager.get(new OkHttpGetRequestBuilder("https://api.douban.com/v2/movie/top250?start=0&count=10"), "mainfragment", new UiHandlerCallBack() {
                  @Override
                  public void success(JSONObject data) {

                  }

                  @Override
                  public void error(int status, String message, JSONObject statusInfo) {

                  }

                  @Override
                  public void progress(int progress) {

                  }

                  @Override
                  public void failed(int code, String msg) {

                  }

                  @Override
                  public void onFailure(Call call, IOException e) {

                  }

                  @Override
                  public void onResponse(Call call, Response response) throws IOException {
                        final  String content = response.body().string();
                        Logger.d(content);
                      getActivity().runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              //tvMainPage.setText(content);
                          }
                      });

                  }
              });
                OkHttpPostRequestBuilder okHttpPostRequestBuilder =  new OkHttpPostRequestBuilder("http://ccf.hrkji.com/RegUser.asmx/Login");
                okHttpPostRequestBuilder.put("username",1001);
                okHttpPostRequestBuilder.put("password",123456);
                OKHttpManager.post(okHttpPostRequestBuilder, "sdsa", new UiHandlerCallBack() {
                    @Override
                    public void success(JSONObject data) {

                    }

                    @Override
                    public void error(int status, String message, JSONObject statusInfo) {

                    }

                    @Override
                    public void progress(int progress) {

                    }

                    @Override
                    public void failed(int code, String msg) {

                    }

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String content = response.body().string();
                        Logger.d(content);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvMainPage.setText(content);
                            }
                        });
                    }
                });
                break;
            case R.id.btn_stop:
                OKHttpManager.findTagCall("mainfragment",this);
                break;
        }
    }
    @Override
    public void cancelCallByTag(Call call) {
        if (call!=null){
            if (!call.isCanceled()){
                call.cancel();
                Logger.i(String.valueOf(call.isCanceled()));
            }
        }
    }
}
