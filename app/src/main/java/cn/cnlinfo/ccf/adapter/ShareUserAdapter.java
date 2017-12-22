package cn.cnlinfo.ccf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.entity.ShareUserEntity;

/**
 * Created by Administrator on 2017/12/22 0022.
 */

public class ShareUserAdapter extends BaseRecyclerAdapter<ShareUserEntity> {

    private Context context;
    public ShareUserAdapter(Context context , List<ShareUserEntity> shareUserEntityList) {
        super(context);
        this.context = context;
        list = shareUserEntityList;
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
            ((ViewHolder) holder).mTvUserAccount.setText(userEntity.getUserCode());
            ((ViewHolder) holder).mTvUserName.setText(userEntity.getName());
            ((ViewHolder) holder).mTvUserPhone.setText(userEntity.getMobile());
            ((ViewHolder) holder).mTvUserReDate.setText(userEntity.getRegDate());
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
