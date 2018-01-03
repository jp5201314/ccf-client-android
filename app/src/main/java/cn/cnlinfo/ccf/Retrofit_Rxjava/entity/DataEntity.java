package cn.cnlinfo.ccf.Retrofit_Rxjava.entity;

/**
 * Created by Administrator on 2017/11/24 0024.
 */

public class DataEntity<T> {

    private T userinfo;

    public T getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(T userinfo) {
        this.userinfo = userinfo;
    }

    @Override
    public String toString() {
        return "DataEntity{" +
                "userinfo=" + userinfo +
                '}';
    }

    public DataEntity(T userinfo) {
        this.userinfo = userinfo;
    }

    public DataEntity() {
    }
}
