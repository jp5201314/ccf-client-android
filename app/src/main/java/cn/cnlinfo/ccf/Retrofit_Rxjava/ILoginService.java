package cn.cnlinfo.ccf.Retrofit_Rxjava;


import com.google.gson.JsonObject;

import cn.cnlinfo.ccf.API;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by JP on 2017/11/24 0024.
 */

public interface ILoginService {
    @FormUrlEncoded
    @POST(API.CCFLOGIN)
    Observable<JsonObject> getUser(@Field("username") String username, @Field("password") String password);
}
