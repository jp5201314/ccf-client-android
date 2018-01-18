package cn.cnlinfo.ccf.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;
import com.scrat.app.selectorlibrary.ImageSelector;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cnlinfo.ccf.API;
import cn.cnlinfo.ccf.Constant;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.activity.OrderCenterActivity;
import cn.cnlinfo.ccf.activity.PreviewSaveActivity;
import cn.cnlinfo.ccf.entity.OrderListItem;
import cn.cnlinfo.ccf.event.ConfirmReceiveMoneyEvent;
import cn.cnlinfo.ccf.event.SendOrderIdEvent;
import cn.cnlinfo.ccf.net_okhttpfinal.CCFHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class OrderListItemAdapter extends BaseRecyclerAdapter<OrderListItem> {

    private LayoutInflater inflater;
    private Context context;
    //上传图片的结果码
    private static final int REQUEST_CODE_SELECT_IMG = 1;

    public OrderListItemAdapter(Context context) {
        super(context);
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_order_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OrderListItem orderListItem = list.get(position);
        if (holder instanceof ViewHolder) {
            if (orderListItem != null) {
                if (!TextUtils.isEmpty(orderListItem.getCreateTime())) {
                    ((ViewHolder) holder).tvTime.setText(orderListItem.getCreateTime());
                } else {
                    ((ViewHolder) holder).tvTime.setText("暂无");
                }
                if (!TextUtils.isEmpty(orderListItem.getStatus())) {
                    ((ViewHolder) holder).btnTradingStatus.setText(orderListItem.getStatus());
                } else {
                    ((ViewHolder) holder).btnTradingStatus.setText("暂无");
                }
                if (!TextUtils.isEmpty(orderListItem.getSellerID())) {
                    ((ViewHolder) holder).tvUserId.setText(orderListItem.getSellerID());
                } else {
                    ((ViewHolder) holder).tvUserId.setText("暂无");
                }
                if (!TextUtils.isEmpty(orderListItem.getPurchaserID())) {
                    ((ViewHolder) holder).tvTradingUser.setText(orderListItem.getPurchaserID());
                } else {
                    ((ViewHolder) holder).tvTradingUser.setText("暂无");
                }
                if (!TextUtils.isEmpty(orderListItem.getCCFNum())) {
                    ((ViewHolder) holder).tvCcfNumber.setText(orderListItem.getCCFNum());
                } else {
                    ((ViewHolder) holder).tvCcfNumber.setText("暂无");
                }
                if (!TextUtils.isEmpty(orderListItem.getPayMoney())) {
                    ((ViewHolder) holder).tvTradMoney.setText(orderListItem.getPayMoney());
                } else {
                    ((ViewHolder) holder).tvTradMoney.setText("暂无");
                }
                if (!TextUtils.isEmpty(orderListItem.getAuctionRoomID())) {
                    ((ViewHolder) holder).tvRoomId.setText(orderListItem.getAuctionRoomID());
                } else {
                    ((ViewHolder) holder).tvRoomId.setText("暂无");
                }
                if (!TextUtils.isEmpty(orderListItem.getTranType())) {
                    //设置交易类型
                    ((ViewHolder) holder).tvTradType.setText(orderListItem.getTranType());
                    if (orderListItem.getTranType().equals("挂卖")) {
                        ((ViewHolder) holder).btnClick.setText("上传凭证");
                        ((ViewHolder) holder).btnClick.setOnClickListener(v -> {
                            ImageSelector.show((OrderCenterActivity) context, REQUEST_CODE_SELECT_IMG, 1);
                            EventBus.getDefault().post(new SendOrderIdEvent(orderListItem.getID()));

                        });
                    } else {
                        if (orderListItem.getStatus().equals("打款中")){
                            Logger.d(orderListItem.getTranType());
                            //当订单状态为打款中时确认收款按钮才显示出来
                            /**
                             * 状态有
                             * 未打款，投诉中，打款中，完成，无效
                             */
                            ((ViewHolder) holder).btnClick.setVisibility(View.VISIBLE);
                            ((ViewHolder) holder).btnClick.setText("确认收款");
                            //确认收款
                            ((ViewHolder) holder).btnClick.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    RequestParams params = new RequestParams();
                                    params.addFormDataPart("orderID",orderListItem.getID());
                                    HttpRequest.post(Constant.OPERATE_CCF_HOST + API.SELLERSENDCCF, params, new CCFHttpRequestCallback() {
                                        @Override
                                        protected void onDataSuccess(JSONObject data) {
                                            EventBus.getDefault().post(new ConfirmReceiveMoneyEvent(0,"成功收款"));
                                        }

                                        @Override
                                        protected void onDataError(int code, boolean flag, String msg) {
                                            EventBus.getDefault().post(new ConfirmReceiveMoneyEvent(code,msg));
                                        }
                                    });
                                }
                            });
                        }else {
                            ((ViewHolder) holder).btnClick.setVisibility(View.GONE);
                        }
                    }
                } else {
                    ((ViewHolder) holder).tvTradType.setText("暂无");
                }
                if (!TextUtils.isEmpty(orderListItem.getBuyerScreenshot())) {
                    if (orderListItem.getTranType().equals("挂卖")) {
                        ((ViewHolder) holder).btnClick.setVisibility(View.GONE);
                        ((ViewHolder) holder).btnShowProof.setText("查看凭证");
                    }
                    String url = "http://ccf.hrkji.com/" + orderListItem.getBuyerScreenshot();
                    ((ViewHolder) holder).btnShowProof.setOnClickListener(v -> {
                        Intent intent = new Intent(context, PreviewSaveActivity.class);
                        intent.putExtra("url", url);
                        context.startActivity(intent);
                    });
                } else {
                    ((ViewHolder) holder).btnShowProof.setText("暂无凭证");
                }
                if (!TextUtils.isEmpty(orderListItem.getAliPay())){
                    ((ViewHolder) holder).tvAlipayAccount.setText(orderListItem.getAliPay());
                }else {
                    ((ViewHolder) holder).tvAlipayAccount.setText("暂无");
                }
                if (!TextUtils.isEmpty(orderListItem.getWebChat())){
                    ((ViewHolder) holder).tvWeixinAccount.setText(orderListItem.getWebChat());
                }else {
                    ((ViewHolder) holder).tvWeixinAccount.setText("暂无");
                }
                if (!TextUtils.isEmpty(orderListItem.getBankCode())){
                    ((ViewHolder) holder).tvBankAccount.setText(orderListItem.getBankCode());
                }else {
                    ((ViewHolder) holder).tvBankAccount.setText("暂无");
                }
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.btn_trading_status)
        Button btnTradingStatus;
        @BindView(R.id.tv_user_id)
        TextView tvUserId;
        @BindView(R.id.tv_trading_user)
        TextView tvTradingUser;
        @BindView(R.id.tv_ccf_number)
        TextView tvCcfNumber;
        @BindView(R.id.tv_trad_money)
        TextView tvTradMoney;
        @BindView(R.id.tv_room_id)
        TextView tvRoomId;
        @BindView(R.id.tv_trad_type)
        TextView tvTradType;
        @BindView(R.id.btn_click)
        Button btnClick;
        @BindView(R.id.btn_show_proof)
        Button btnShowProof;
        @BindView(R.id.tv_alipay_account)
        TextView tvAlipayAccount;
        @BindView(R.id.tv_weixin_account)
        TextView tvWeixinAccount;
        @BindView(R.id.tv_bank_account)
        TextView tvBankAccount;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

