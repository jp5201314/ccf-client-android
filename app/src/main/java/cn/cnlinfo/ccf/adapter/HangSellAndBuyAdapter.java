package cn.cnlinfo.ccf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cnlinfo.ccf.API;
import cn.cnlinfo.ccf.Constant;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.entity.ItemHangSellAndBuyEntity;
import cn.cnlinfo.ccf.event.CancelHangBuyAndSellEvent;
import cn.cnlinfo.ccf.event.ErrorMessageEvent;
import cn.cnlinfo.ccf.net_okhttpfinal.CCFHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class HangSellAndBuyAdapter extends BaseRecyclerAdapter<ItemHangSellAndBuyEntity> {

    private LayoutInflater inflater;

    public HangSellAndBuyAdapter(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_hang_sell_buy_record, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemHangSellAndBuyEntity itemHangSellAndBuyEntity = list.get(position);
        if (holder instanceof ViewHolder) {
            if (!TextUtils.isEmpty(itemHangSellAndBuyEntity.getTranType())) {
                ((ViewHolder) holder).tvTradType.setText(itemHangSellAndBuyEntity.getTranType() + "记录凭证");
            } else {
                ((ViewHolder) holder).tvTradType.setText("暂无");
            }
            if (!TextUtils.isEmpty(itemHangSellAndBuyEntity.getCreateTime())) {
                ((ViewHolder) holder).tvTime.setText(itemHangSellAndBuyEntity.getCreateTime());
            } else {
                ((ViewHolder) holder).tvTime.setText("暂无");
            }
            if (!TextUtils.isEmpty(itemHangSellAndBuyEntity.getStatus())) {
                ((ViewHolder) holder).tvTradStatus.setText(itemHangSellAndBuyEntity.getStatus());
            } else {
                ((ViewHolder) holder).tvTradStatus.setText("暂无");
            }
            if (!TextUtils.isEmpty(itemHangSellAndBuyEntity.getUserID())) {
                ((ViewHolder) holder).tvUserId.setText(itemHangSellAndBuyEntity.getUserID());
            } else {
                ((ViewHolder) holder).tvUserId.setText("暂无");
            }
            if (!TextUtils.isEmpty(itemHangSellAndBuyEntity.getMsg())) {
                ((ViewHolder) holder).tvInfo.setText(itemHangSellAndBuyEntity.getMsg());
            } else {
                ((ViewHolder) holder).tvInfo.setText("暂无");
            }
            if (!TextUtils.isEmpty(itemHangSellAndBuyEntity.getAuctionRoomID())) {
                ((ViewHolder) holder).tvTradId.setText(itemHangSellAndBuyEntity.getAuctionRoomID());
            } else {
                ((ViewHolder) holder).tvTradId.setText("暂无");
            }
            ((ViewHolder) holder).btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RequestParams params = new RequestParams();
                    params.addFormDataPart("AuctionRoomID",itemHangSellAndBuyEntity.getAuctionRoomID());
                    HttpRequest.post(Constant.getHost() + API.UPDATEPERSIONINFO, params, new CCFHttpRequestCallback() {
                        @Override
                        protected void onDataSuccess(JSONObject data) {
                            EventBus.getDefault().post(new CancelHangBuyAndSellEvent("撤销成功"));
                        }

                        @Override
                        protected void onDataError(int code, boolean flag, String msg) {
                            EventBus.getDefault().post(new ErrorMessageEvent(code,msg));
                        }
                    });
                }
            });
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_trad_type)
        TextView tvTradType;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_trad_status)
        TextView tvTradStatus;
        @BindView(R.id.tv_user_id)
        TextView tvUserId;
        @BindView(R.id.tv_info)
        TextView tvInfo;
        @BindView(R.id.tv_trad_id)
        TextView tvTradId;
        @BindView(R.id.btn_cancel)
        Button btnCancel;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
