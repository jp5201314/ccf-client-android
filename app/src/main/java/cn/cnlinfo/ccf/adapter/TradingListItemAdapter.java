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

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.activity.PurCCFActivity;
import cn.cnlinfo.ccf.entity.TradingListItem;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class TradingListItemAdapter extends BaseRecyclerAdapter<TradingListItem> {

    private LayoutInflater inflater;
    private int tradType;
    private Context context;

    public TradingListItemAdapter(Context context,int tradType) {
        super(context);
        inflater = LayoutInflater.from(context);
        this.tradType = tradType;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.item_trading_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TradingListItem tradingListItem = list.get(position);
        if (holder instanceof  ViewHolder){
            if (!TextUtils.isEmpty(tradingListItem.getTranType())){
                ((ViewHolder) holder).tvTradingType.setText(tradingListItem.getTranType());
            }else {
                ((ViewHolder) holder).tvTradingType.setText("暂无");
            }
            if (!TextUtils.isEmpty(tradingListItem.getCreateTime())){
                ((ViewHolder) holder).tvTime.setText(tradingListItem.getCreateTime());
            }else {
                ((ViewHolder) holder).tvTime.setText("暂无");
            }
            if (!TextUtils.isEmpty(tradingListItem.getStatus())){
                if (tradType==1){
                    ((ViewHolder) holder).btnTradingStatus.setText("购买");
                }else {
                    ((ViewHolder) holder).btnTradingStatus.setText("出售");
                }
            }else {
                ((ViewHolder) holder).btnTradingStatus.setText("暂无");
                ((ViewHolder) holder).btnTradingStatus.setClickable(false);
            }
            if (!TextUtils.isEmpty(tradingListItem.getSellerID())){
                ((ViewHolder) holder).tvUserId.setText(tradingListItem.getSellerID());
            }else {
                ((ViewHolder) holder).tvUserId.setText("暂无");
            }
            if (!TextUtils.isEmpty(tradingListItem.getBusinessLev())){
                ((ViewHolder) holder).tvUserLevel.setText(tradingListItem.getBusinessLev());
            }else {
                ((ViewHolder) holder).tvUserLevel.setText("暂无");
            }
            if (!TextUtils.isEmpty(tradingListItem.getCCF())){
                ((ViewHolder) holder).tvCcfNumber.setText(tradingListItem.getCCF());
            }else {
                ((ViewHolder) holder).tvCcfNumber.setText("暂无");
            }
            if (!TextUtils.isEmpty(tradingListItem.getPrice())){
                ((ViewHolder) holder).tvCurrentPrice.setText(tradingListItem.getPrice());
            }else {
                ((ViewHolder) holder).tvCurrentPrice.setText("暂无");
            }
           /* if (!TextUtils.isEmpty(tradingListItem.getPurchaserID())){
                ((ViewHolder) holder).tvTradingUser.setText(tradingListItem.getPurchaserID());
            }else {
                ((ViewHolder) holder).tvTradingUser.setText("暂无");
            }*/
           holder.itemView.setTag(position);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.tv_trading_type)
        TextView tvTradingType;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.btn_trading_status)
        Button btnTradingStatus;
        @BindView(R.id.tv_user_id)
        TextView tvUserId;
        @BindView(R.id.tv_user_level)
        TextView tvUserLevel;
        @BindView(R.id.tv_ccf_number)
        TextView tvCcfNumber;
        @BindView(R.id.tv_current_price)
        TextView tvCurrentPrice;
        @BindView(R.id.tv_trading_user)
        TextView tvTradingUser;
        private int position;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            btnTradingStatus.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_trading_status:
                    position = (int) itemView.getTag();
                    if (tradType==1){
                        clickBuy();
                    }else {

                    }
                    break;
            }
        }

        /**
         * 点击购买
         */
        private void clickBuy(){
            if (list!=null){
                Intent intent = new Intent(context, PurCCFActivity.class);
                intent.putExtra("roomId",list.get(position).getID());
                context.startActivity(intent);
            }
        }
    }
}