package cn.cnlinfo.ccf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.cnlinfo.ccf.R;


/**
 * Created by Administrator on 2017/11/27 0027.
 */

public class TransactionRecordAdapter extends RecyclerView.Adapter<TransactionRecordAdapter.ViewHolder>{

    private static Context context;

    public String[] datas = null;
    public TransactionRecordAdapter(String[] datas,Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction_record,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_money.setText(datas[position]);
        holder.tv_transaction_type.setText(datas[position]);
        holder.tv_price.setText(datas[position]);
        holder.tv_number.setText(datas[position]);

    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_time, tv_money, tv_transaction_type, tv_price, tv_number,tv_details;

        LinearLayout linearLayout;
        boolean visibility_Flag = false;
        public ViewHolder(View view) {
            super(view);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            tv_money = (TextView) view.findViewById(R.id.tv_money);
            tv_transaction_type = (TextView) view.findViewById(R.id.tv_transaction_type);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            tv_number = (TextView) view.findViewById(R.id.tv_number);
            tv_details = (TextView) view.findViewById(R.id.tv_details);
            linearLayout = (LinearLayout) view.findViewById(R.id.linearlayout);
            tv_details.setOnClickListener(this);
//          itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(visibility_Flag){
                linearLayout.setVisibility(View.GONE);
                visibility_Flag = false;
            } else {
                linearLayout.setVisibility(View.VISIBLE);
                visibility_Flag =true;
            }
        }

    }
}
