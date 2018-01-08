package cn.cnlinfo.ccf.utils;

import com.orhanobut.logger.Logger;

import cn.cnlinfo.ccf.Retrofit_Rxjava.ILoginService;
import cn.cnlinfo.ccf.Retrofit_Rxjava.LoginResult;
import cn.cnlinfo.ccf.Retrofit_Rxjava.entity.DataEntity;
import cn.cnlinfo.ccf.entity.User;
import cn.cnlinfo.ccf.net_okhttp.OKHttpManager;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class RxUtils {
    public static Observable getLoginObserable(String baseUrl,String username,String password){
        ILoginService loginService = getRetrofit(baseUrl).create(ILoginService.class);
        return  loginService.getUser(username,password).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).map(new Func1<LoginResult<DataEntity<User>>, User>() {
            @Override
            public User call(LoginResult<DataEntity<User>> dataEntityLoginResult) {
                Logger.d(dataEntityLoginResult.toString());
                if (!dataEntityLoginResult.isSuccess()){
                    try {
                        throw new Exception(String.valueOf(dataEntityLoginResult.getMessageID()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return dataEntityLoginResult.getData().getUserinfo();
            }
        });
    }

    private static Retrofit getRetrofit(String baseUrl){
        return new Retrofit.Builder().baseUrl(baseUrl).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(OKHttpManager.getInstance()).build();
    }

}
