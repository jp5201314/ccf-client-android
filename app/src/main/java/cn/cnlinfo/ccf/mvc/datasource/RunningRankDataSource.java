package cn.cnlinfo.ccf.mvc.datasource;

import com.alibaba.fastjson.JSONObject;
import com.shizhefei.mvc.IDataSource;

import java.util.ArrayList;
import java.util.List;

import cn.cnlinfo.ccf.entity.RunningRankEntity;
import cn.cnlinfo.ccf.net_okhttpfinal.CCFHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;

/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class RunningRankDataSource implements IDataSource<List<RunningRankEntity>> {

    private int page = 1;
    private int maxPage = 0;
    private int number = 10;

    @Override
    public List<RunningRankEntity> refresh() throws Exception {
        return loadRunningRank(page);
    }

    @Override
    public List<RunningRankEntity> loadMore() throws Exception {

        return loadRunningRank(page+1);
    }

    private List<RunningRankEntity> loadRunningRank(int page) throws Exception{
        HttpRequest.get("www.baidu.com", new CCFHttpRequestCallback() {
            @Override
            protected void onDataSuccess(JSONObject data) {

            }

            @Override
            protected void onDataError(int code, boolean flag, String msg) {

            }
        });
        List<RunningRankEntity> runningRankEntityList  = new ArrayList<>();
        for (int i = 0; i<number;i++){
            RunningRankEntity runningRankEntity = new RunningRankEntity(i,"jp"+i,"524"+i,i);
            runningRankEntityList.add(runningRankEntity);
        }
        this.page = page;
        return runningRankEntityList;
    }

    @Override
    public boolean hasMore() {
        return page<maxPage;
    }
}
