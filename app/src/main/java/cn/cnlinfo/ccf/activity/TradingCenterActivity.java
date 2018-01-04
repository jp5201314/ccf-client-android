package cn.cnlinfo.ccf.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.shizhefei.mvc.MVCHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.adapter.TradingListItemAdapter;
import cn.cnlinfo.ccf.mvc.datasource.TradingListDataSource;
import cn.cnlinfo.ccf.mvc.helper.MVCUltraHelper;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class TradingCenterActivity extends BaseActivity {

    @BindView(R.id.ibt_back)
    ImageButton mIbtBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.pfl)
    PtrClassicFrameLayout mPfl;
    @BindView(R.id.ibt_add)
    ImageButton ibtAdd;
    private Unbinder unbinder;
    private MVCHelper mvcHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trading_center);
        unbinder = ButterKnife.bind(this);
        mTvTitle.setText("交易大厅");
        showTradingCenterInfo();
    }

    /**
     * 显示交易大厅信息
     */
    private void showTradingCenterInfo() {
        mRv.setHasFixedSize(true);
        mRv.setNestedScrollingEnabled(false);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mvcHelper = new MVCUltraHelper(mPfl);
        mvcHelper.setDataSource(new TradingListDataSource("where(1=1) and Status=1"));
        mvcHelper.setAdapter(new TradingListItemAdapter(this));
        mvcHelper.refresh();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick({R.id.ibt_back,R.id.ibt_add})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.ibt_back:
                finish();
                break;
            case R.id.ibt_add:
                showTradingMenu(v);
                break;
        }
    }

    /**
     * 显示交易菜单
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showTradingMenu(View view){
        PopupMenu popupMenu = new PopupMenu(this,view, Gravity.END);
        Menu menu = popupMenu.getMenu();
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.layout_trading_menu,menu);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                String type = "";
                switch (itemId) {
                    case R.id.sell:
                        type = "where TranType=1 and Status=1";
                        mvcHelper.setDataSource(new TradingListDataSource(type));
                        break;
                    case R.id.buy:
                        type = "where TranType=2 and Status=1";
                        mvcHelper.setDataSource(new TradingListDataSource(type));
                        break;
                    case R.id.all:
                        type = "where(1=1) and Status=1";
                        mvcHelper.setDataSource(new TradingListDataSource(type));
                        break;
                }
                mvcHelper.refresh();
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
