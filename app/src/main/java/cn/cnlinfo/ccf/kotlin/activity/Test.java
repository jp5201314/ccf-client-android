package cn.cnlinfo.ccf.kotlin.activity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import cn.cnlinfo.ccf.entity.ItemNews;

/**
 * Created by Administrator on 2017/12/7 0007.
 */

public class Test {

    public void test(){
        JsonArray jsonArray = new JsonArray();
        ArrayList<ItemNews> itemNewsList = new ArrayList<>();
        for (JsonElement user : jsonArray) {
            //通过反射 得到UserBean.class
            ItemNews itemNews = new Gson().fromJson(user, new TypeToken<ItemNews>() {}.getType());
            itemNewsList.add(itemNews);
        }
    }
}
