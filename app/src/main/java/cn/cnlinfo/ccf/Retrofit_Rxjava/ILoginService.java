package cn.cnlinfo.ccf.Retrofit_Rxjava;


import cn.cnlinfo.ccf.API;
import cn.cnlinfo.ccf.Retrofit_Rxjava.entity.DataEntity;
import cn.cnlinfo.ccf.entity.User;
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
    Observable<LoginResult<DataEntity<User>>> getUser(@Field("username") String username, @Field("password") String password);
}
