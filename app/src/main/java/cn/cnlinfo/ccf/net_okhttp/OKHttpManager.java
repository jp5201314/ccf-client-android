package cn.cnlinfo.ccf.net_okhttp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class OKHttpManager {

    private  static  OkHttpClient okHttpClient = null;

    public static OkHttpClient getInstance(){
        if (okHttpClient==null){
            okHttpClient = new OkHttpClient().newBuilder().connectTimeout(5, TimeUnit.SECONDS).writeTimeout(30,TimeUnit.SECONDS).readTimeout(30,TimeUnit.SECONDS).build();
        }
        return okHttpClient;
    }

    public static void syncGet(){

    }

    public static void syncPost(){

    }

}
