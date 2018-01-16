package cn.cnlinfo.ccf.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.activity.PreviewSaveActivity;
import cn.cnlinfo.ccf.entity.OrderListItem;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class OrderListItemAdapter extends BaseRecyclerAdapter<OrderListItem> {


    private LayoutInflater inflater;
    private Context context;

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
                    ((ViewHolder) holder).tvTradType.setText(orderListItem.getTranType());
                    Logger.d(orderListItem.getTranType());
                    if (orderListItem.getTranType().equals("挂卖")) {
                        ((ViewHolder) holder).btnClick.setText("上传凭证");
                    }else {
                        ((ViewHolder) holder).btnClick.setText("确认收款");
                    }
                } else {
                    ((ViewHolder) holder).tvTradType.setText("暂无");
                }
                ((ViewHolder) holder).mImg1.enable();
                if (!TextUtils.isEmpty(orderListItem.getBuyerScreenshot())) {
                    ((ViewHolder) holder).linearLayout.setVisibility(View.VISIBLE);
                    String url = "http://ccf.hrkji.com/" + orderListItem.getBuyerScreenshot();
                    Glide.with(context).load(url).asBitmap().error(R.drawable.empty).placeholder(R.drawable.empty).into(((ViewHolder) holder).mImg1);
                    ((ViewHolder) holder).mImg1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, PreviewSaveActivity.class);
                            intent.putExtra("url",url);
                            context.startActivity(intent);
                        }
                    });
                } else {
                    ((ViewHolder) holder).linearLayout.setVisibility(View.GONE);
                }
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
    @BindView(R.id.img1)
    PhotoView mImg1;
    @BindView(R.id.btn_click)
    Button btnClick;
    @BindView(R.id.ll_content)
    LinearLayout linearLayout;
    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}

