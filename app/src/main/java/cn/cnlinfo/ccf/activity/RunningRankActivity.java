package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.adapter.RunningRankAdapter;
import cn.cnlinfo.ccf.entity.RunningRankEntity;
import cn.cnlinfo.ccf.listener.LoadMoreOnScrollListener;

public class RunningRankActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.layout_swipe_refresh)
    SwipeRefreshLayout layoutSwipeRefresh;
    @BindView(R.id.ibt_back)
    ImageButton ibt;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ibt_add)
    ImageButton ibAdd;
    private Unbinder unbinder;
    private List<RunningRankEntity> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_rank);
        unbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        ibAdd.setVisibility(View.INVISIBLE);
        ibt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("跑步排名");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        final RunningRankAdapter adapter = new RunningRankAdapter(this, acquireData());
        rv.setAdapter(adapter);
        layoutSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.notifyDataSetChanged();
                layoutSwipeRefresh.setRefreshing(false);
            }
        });

        rv.addOnScrollListener(new LoadMoreOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                Logger.d(currentPage+"");
                getData();
                adapter.notifyDataSetChanged();
                layoutSwipeRefresh.setRefreshing(false);
    }
});
    }

    /**
     * 获取到排名数据
     *
     * @return
     */
    private List<RunningRankEntity> acquireData() {
        list = new ArrayList<>();
        getData();
        return list;
    }

    private void getData() {
        for (int i= 0;i<10;i++){
            RunningRankEntity entity = new RunningRankEntity(i, "jp", "5212", 452);
            list.add(entity);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
