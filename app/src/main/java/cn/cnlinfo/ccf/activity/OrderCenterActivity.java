package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shizhefei.mvc.MVCHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.adapter.OrderListItemAdapter;
import cn.cnlinfo.ccf.mvc.datasource.OrderListDataSource;
import cn.cnlinfo.ccf.mvc.helper.MVCUltraHelper;
import cn.cnlinfo.ccf.view.FullyLinearLayoutManager;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class OrderCenterActivity extends BaseActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_center);
        unbinder = ButterKnife.bind(this);
        ibtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("订单中心");
        getCurrentOrderList();
    }

    /**
     * 获取当前订单
     */
    private void getCurrentOrderList(){
        rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);
        rv.setLayoutManager(new FullyLinearLayoutManager(this));
        mvcHelper = new MVCUltraHelper(pfl);
        mvcHelper.setDataSource(new OrderListDataSource());
        mvcHelper.setAdapter(new OrderListItemAdapter(this));
        mvcHelper.refresh();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mvcHelper.destory();
    }
}
