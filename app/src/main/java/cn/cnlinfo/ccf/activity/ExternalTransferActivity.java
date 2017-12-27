package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shizhefei.mvc.IDataAdapter;
import com.shizhefei.mvc.MVCHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.adapter.OutTransferAdapter;
import cn.cnlinfo.ccf.entity.TransferRecordEntity;
import cn.cnlinfo.ccf.mvc.datasource.OutTransferDataSource;
import cn.cnlinfo.ccf.mvc.helper.MVCUltraHelper;
import cn.cnlinfo.ccf.view.FullyLinearLayoutManager;
import cn.cnlinfo.ccf.view.NormalNoLoadViewFactory;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by Administrator on 2017/11/30 0030.
 * 对外转账记录
 */

public class ExternalTransferActivity extends BaseActivity{

    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.pfl)
    PtrClassicFrameLayout pfl;
    private Unbinder unbinder;
    private MVCHelper mvcHelper;
    private OutTransferDataSource outTransferDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_transfer);
        unbinder = ButterKnife.bind(this);
        tvTitle.setText("对外转账记录");
        ibtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getOutTransferRecord();
    }

    private void getOutTransferRecord(){
        MVCHelper.setLoadViewFractory(new NormalNoLoadViewFactory());
        this.setMaterialHeader(pfl);
        rv.setLayoutManager(new FullyLinearLayoutManager(this));
        rv.setNestedScrollingEnabled(false);
        mvcHelper = new MVCUltraHelper<List<TransferRecordEntity>>(pfl);
        mvcHelper.setNeedCheckNetwork(true);
        outTransferDataSource = new OutTransferDataSource();
        mvcHelper.setDataSource(outTransferDataSource);
        final OutTransferAdapter adapter = new OutTransferAdapter(ExternalTransferActivity.this);
        mvcHelper.setAdapter(new IDataAdapter<List<TransferRecordEntity>>() {
            @Override
            public void notifyDataChanged(List<TransferRecordEntity> list, boolean isRefresh) {
                adapter.notifyDataChanged(list, true);
            }

            @Override
            public List<TransferRecordEntity> getData() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });
        rv.setAdapter(adapter);
        mvcHelper.refresh();

        /*rv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //设置什么布局管理器,就获取什么的布局管理器
                FullyLinearLayoutManager manager = (FullyLinearLayoutManager) recyclerView.getLayoutManager();
                // 当停止滑动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition ,角标值
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    //所有条目,数量值
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部，并且是向右滚动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        Logger.d("loadmore");
                        //加载更多功能的代码
                        mvcHelper.loadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                //dx>0:向右滑动,dx<0:向左滑动
                //dy>0:向下滑动,dy<0:向上滑动
                if (dy > 0) {
                    isSlidingToLast = true;
                } else {
                    isSlidingToLast = false;
                }
            }
        });*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mvcHelper.destory();
    }
/*
    *//**
     * 判断是否RecycleView滑动到了底部
     * @param recyclerView
     * @return
     *//*
    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }*/
}
