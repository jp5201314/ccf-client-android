package cn.cnlinfo.ccf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.entity.ShareUserEntity;

/**
 * Created by Administrator on 2017/12/22 0022.
 */

public class ShareUserAdapter extends BaseRecyclerAdapter<ShareUserEntity> {

    private Context context;
    public ShareUserAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_share_user,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Logger.d(list.get(position).toString());
        if (holder instanceof  ViewHolder){
            ShareUserEntity userEntity = list.get(position);
            if (!TextUtils.isEmpty(userEntity.getUserCode())){
                ((ViewHolder) holder).mTvUserAccount.setText(userEntity.getUserCode());
            }else {
                ((ViewHolder) holder).mTvUserAccount.setText("暂无");
            }
            if (!TextUtils.isEmpty(userEntity.getName())){
                ((ViewHolder) holder).mTvUserName.setText(userEntity.getName());
            }else {
                ((ViewHolder) holder).mTvUserName.setText("暂无");
            }
            if (!TextUtils.isEmpty(userEntity.getMobile())){
                ((ViewHolder) holder).mTvUserPhone.setText(userEntity.getMobile());
            }else {
                ((ViewHolder) holder).mTvUserPhone.setText("暂无");
            }
            if (!TextUtils.isEmpty(userEntity.getRegDate())){
                ((ViewHolder) holder).mTvUserReDate.setText(userEntity.getRegDate());
            }else {
                ((ViewHolder) holder).mTvUserReDate.setText("暂无");
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_user_account)
        TextView mTvUserAccount;
        @BindView(R.id.tv_user_name)
        TextView mTvUserName;
        @BindView(R.id.tv_user_phone)
        TextView mTvUserPhone;
        @BindView(R.id.tv_user_re_date)
        TextView mTvUserReDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
