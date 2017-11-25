package cn.cnlinfo.ccf.Retrofit_Rxjava.entity;

import cn.cnlinfo.ccf.entity.User;

/**
 * Created by Administrator on 2017/11/24 0024.
 */

public class DataEntity {

    private User userinfo;

    public User getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(User userinfo) {
        this.userinfo = userinfo;
    }

    @Override
    public String toString() {
        return "DataEntity{" +
                "userinfo=" + userinfo +
                '}';
    }

    public DataEntity(User userinfo) {
        this.userinfo = userinfo;
    }

    public DataEntity() {
    }
}
