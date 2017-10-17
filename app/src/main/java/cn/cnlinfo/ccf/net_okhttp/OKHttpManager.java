package cn.cnlinfo.ccf.net_okhttp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class OKHttpManager {

    private static OkHttpClient okHttpClient = new OkHttpClient().newBuilder().connectTimeout(5, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();

    private static List<Call> calls = new ArrayList<>();
    private static Call call;

    public static OkHttpClient getInstance() {
        return okHttpClient;
    }

    public static Response syncGet(OkHttpGetRequestBuilder builder, String tag) {
        try {
            call = OKHttpManager.getInstance().newCall(builder.build(tag));
            calls.add(call);
            return call.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Response syncPost(OkHttpPostRequestBuilder builder, String tag) {
        try {
            call = OKHttpManager.getInstance().newCall(builder.build(tag));
            calls.add(call);
            return call.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void get(OkHttpGetRequestBuilder builder, String tag,UiHandlerCallBack callBack) {
        try {
            OKHttpManager.getInstance().newCall(builder.build(tag)).enqueue(callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void post() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearCalls(){
        calls.clear();
    }

}
