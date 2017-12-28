package cn.cnlinfo.ccf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.entity.CyclePackRecordEntity;

/**
 * Created by JP on 2017/12/22 0022.
 */

public class ConversionCyclePackRecordAdapter extends BaseRecyclerAdapter<CyclePackRecordEntity> {
    private Context context;
    public ConversionCyclePackRecordAdapter(Context context) {
        super(context);
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_conversion_cycle_pack_record, parent, false));
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Logger.d(list.get(position).toString());
        if (holder instanceof ViewHolder) {
            CyclePackRecordEntity cyclePackRecordEntity = list.get(position);
            if (cyclePackRecordEntity != null) {
                if (!TextUtils.isEmpty(cyclePackRecordEntity.getCreateTime())){
                    ((ViewHolder) holder).tvTime.setText(cyclePackRecordEntity.getCreateTime());
                }else {
                    ((ViewHolder) holder).tvTime.setText("暂无");
                }
                if (!TextUtils.isEmpty(cyclePackRecordEntity.getStatus())){
                    ((ViewHolder) holder).btnCycleStatus.setText(cyclePackRecordEntity.getStatus());
                    if (((ViewHolder) holder).btnCycleStatus.getText().toString().equals("未解压")){
                        ((ViewHolder) holder).btnCycleStatus.setClickable(true);
                        ((ViewHolder) holder).btnCycleStatus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                    }else {
                        ((ViewHolder) holder).btnCycleStatus.setClickable(false);
                    }
                }else {
                    ((ViewHolder) holder).btnCycleStatus.setText("暂无");
                }
                if (!TextUtils.isEmpty(cyclePackRecordEntity.getPackID())){
                    ((ViewHolder) holder).tvCyclePackId.setText(cyclePackRecordEntity.getPackID());
                }else {
                    ((ViewHolder) holder).tvCyclePackId.setText("暂无");
                }
                if (!TextUtils.isEmpty(cyclePackRecordEntity.getIsSuccess())){
                    ((ViewHolder) holder).tvConversionStatus.setText(cyclePackRecordEntity.getIsSuccess());
                }else {
                    ((ViewHolder) holder).tvConversionStatus.setText("暂无");
                }
                if (!TextUtils.isEmpty(cyclePackRecordEntity.getMsg())){
                    ((ViewHolder) holder).tvCyclePackInfo.setText(cyclePackRecordEntity.getMsg());
                }else {
                    ((ViewHolder) holder).tvCyclePackInfo.setText("暂无");
                }

            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.btn_cycle_status)
        Button btnCycleStatus;
        @BindView(R.id.tv_cycle_pack_id)
        TextView tvCyclePackId;
        @BindView(R.id.tv_conversion_status)
        TextView tvConversionStatus;
        @BindView(R.id.tv_cycle_pack_info)
        TextView tvCyclePackInfo;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
