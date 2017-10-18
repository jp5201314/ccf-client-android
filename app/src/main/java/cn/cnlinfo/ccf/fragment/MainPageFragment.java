package cn.cnlinfo.ccf.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.inter.IObtainCallByTag;
import cn.cnlinfo.ccf.net_okhttp.OKHttpManager;
import cn.cnlinfo.ccf.net_okhttp.OkHttpGetRequestBuilder;
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
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        final Response response = OKHttpManager.syncGet(new OkHttpGetRequestBuilder("https://api.douban.com/v2/movie/top250?start=0&count=10"), "mainfragment");
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
                }.start();
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
