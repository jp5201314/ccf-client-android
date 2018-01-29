package cn.cnlinfo.ccf.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;
import com.scrat.app.selectorlibrary.ImageSelector;
import com.shizhefei.mvc.MVCHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.API;
import cn.cnlinfo.ccf.Constant;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.adapter.OrderListItemAdapter;
import cn.cnlinfo.ccf.event.ConfirmReceiveMoneyEvent;
import cn.cnlinfo.ccf.event.ReceiveComplainEvent;
import cn.cnlinfo.ccf.event.SendOrderIdEvent;
import cn.cnlinfo.ccf.mvc.datasource.OrderListDataSource;
import cn.cnlinfo.ccf.mvc.helper.MVCUltraHelper;
import cn.cnlinfo.ccf.net_okhttp.OKHttpManager;
import cn.cnlinfo.ccf.net_okhttp.OkHttp3Utils;
import cn.cnlinfo.ccf.net_okhttp.UiHandlerCallBack;
import cn.cnlinfo.ccf.view.FullyLinearLayoutManager;
import cn.finalteam.okhttpfinal.HttpRequest;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class OrderCenterActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.pfl)
    PtrClassicFrameLayout pfl;
    @BindView(R.id.ibt_back)
    ImageButton mIbtBack;
    @BindView(R.id.ibt_add)
    ImageButton mIbtAdd;
    private Unbinder unbinder;
    private MVCHelper mvcHelper;
    private String orderId;
    //上传图片的结果码
    private static final int REQUEST_CODE_SELECT_IMG = 1;
    private OrderListItemAdapter orderListItemAdapter;
    private static String tradTypeBuy = "where SellerID="+ UserSharedPreference.getInstance().getUser().getUserID()+"or PurchaserID = "+ UserSharedPreference.getInstance().getUser().getUserID()+" and  TranType=2";
    private static String tradTypeSell = "where SellerID="+UserSharedPreference.getInstance().getUser().getUserID()+"or PurchaserID = "+ UserSharedPreference.getInstance().getUser().getUserID()+"and  TranType=1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_center);
        unbinder = ButterKnife.bind(this);
        tvTitle.setText("订单中心");
        getCurrentOrderList();
        EventBus.getDefault().register(this);
    }

    /**
     * 获取当前订单
     */
    private void getCurrentOrderList() {
        rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);
        rv.setLayoutManager(new FullyLinearLayoutManager(this));
        mvcHelper = new MVCUltraHelper(pfl);
        mvcHelper.setDataSource(new OrderListDataSource(tradTypeBuy));
        orderListItemAdapter = new OrderListItemAdapter(this);
        mvcHelper.setAdapter(orderListItemAdapter);
        mvcHelper.refresh();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mvcHelper.destory();
        EventBus.getDefault().unregister(this);
        HttpRequest.cancel(Constant.GET_DATA_HOST + API.ORDERLISTRECORD);
        HttpRequest.cancel(Constant.OPERATE_CCF_HOST + API.SELLERSENDCCF);
    }

    //接收到点击上传凭证这个单子的id，为这个订单上传凭证
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveOrderEvent(SendOrderIdEvent sendOrderIdEvent) {
        orderId = sendOrderIdEvent.getOrderId();
    }

    //接收到点击确认收款，刷新界面
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveConfirmReceiveMoney(ConfirmReceiveMoneyEvent confirmReceiveMoneyEvent) {
        showMessage(confirmReceiveMoneyEvent.getErrorCode(), confirmReceiveMoneyEvent.getMsg());
        mvcHelper.refresh();
    }

    //接收到点击确认收款，刷新界面
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveConfirmReceiveMoney(ReceiveComplainEvent receiveComplainEvent) {
        showMessage(receiveComplainEvent.getErrorCode(), receiveComplainEvent.getMsg());
        mvcHelper.refresh();
    }

    //选择图片上传回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_SELECT_IMG) {
            List<String> yourSelectImgPaths = ImageSelector.getImagePaths(data);
            List<File> files = new ArrayList<>();
            for (String yourSelectImgPath : yourSelectImgPaths) {
                File file = new File(yourSelectImgPath);
                files.add(file);
            }
            if (files != null && files.size() > 0) {
                Map<String, String> map = new HashMap<>();
                map.put("orderid", orderId);
                OKHttpManager.getInstance().newCall(OkHttp3Utils.uploadMultiImage(Constant.GET_MESSAGE_CODE_HOST + API.UPLOADIMAGE, map, "one", files)).enqueue(new UiHandlerCallBack() {
                    @Override
                    public void success(JSONObject data) {
                        Logger.d(data.toJSONString());
                        showMessage("上传成功");
                        mvcHelper.refresh();
                    }

                    @Override
                    public void error(int status, String message) {
                        showMessage(status, message);
                    }

                    @Override
                    public void progress(int progress) {
                        Logger.d(progress);
                    }

                    @Override
                    public void failed(int code, String msg) {

                        showMessage(code, msg);
                    }
                });
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick({R.id.ibt_back, R.id.ibt_add})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.ibt_back:
                finish();
                break;
            case R.id.ibt_add:
                showOrderMenu(v);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showOrderMenu(View view){
        PopupMenu popupMenu = new PopupMenu(this,view, Gravity.END);
        Menu menu = popupMenu.getMenu();
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.layout_trading_menu,menu);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.sell:
                        mvcHelper.setDataSource(new OrderListDataSource(tradTypeSell));
                        orderListItemAdapter = new OrderListItemAdapter(OrderCenterActivity.this);
                        mvcHelper.setAdapter(orderListItemAdapter);
                        break;
                    case R.id.buy:
                        mvcHelper.setDataSource(new OrderListDataSource(tradTypeBuy));
                        orderListItemAdapter = new OrderListItemAdapter(OrderCenterActivity.this);
                        mvcHelper.setAdapter(orderListItemAdapter);
                        break;
                }
                mvcHelper.refresh();
                return false;
            }
        });
    }
}
