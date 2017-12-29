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
import cn.cnlinfo.ccf.entity.ItemNewsEntity;

/**
 * Created by Administrator on 2017/12/22 0022.
 */

public class SystemNewsAdapter extends BaseRecyclerAdapter<ItemNewsEntity> {


    private LayoutInflater inflater;
    private static final int VIEWTYPE1 = 0;
    private static final int VIEWTYPE2 = 1;

    public SystemNewsAdapter(Context context) {
        super(context);
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEWTYPE1;
        } else {
            return VIEWTYPE2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case VIEWTYPE1:
                viewHolder = new ViewHolder1(inflater.inflate(R.layout.item_system_notice_with_text, parent, false));
                break;
            case VIEWTYPE2:
                viewHolder = new ViewHolder2(inflater.inflate(R.layout.item_system_notice, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Logger.d(list.get(position).toString());
        if (holder instanceof ViewHolder1) {
            ItemNewsEntity itemNewsEntity = list.get(position);
            ((ViewHolder1) holder).tvInfo.setText("系统公告");
            if (itemNewsEntity.getNewsId()!=0){
                ((ViewHolder1) holder).tvNewsId.setText(String.valueOf(itemNewsEntity.getNewsId()));
            }else {
                ((ViewHolder1) holder).tvNewsId.setText("暂无");
            }
            if (!TextUtils.isEmpty(itemNewsEntity.getClassId())){
                if (itemNewsEntity.getClassId()=="1"){
                    ((ViewHolder1) holder).tvNewsType.setText("公告");
                }else {
                    ((ViewHolder1) holder).tvNewsType.setText("新闻");
                }
            }else {
                ((ViewHolder1) holder).tvNewsType.setText("暂无");
            }
            if (!TextUtils.isEmpty(itemNewsEntity.getSubject())){
                ((ViewHolder1) holder).tvNewsSubject.setText(itemNewsEntity.getSubject());
            }else {
                ((ViewHolder1) holder).tvNewsSubject.setText("暂无");
            }
        } else {
            ItemNewsEntity itemNewsEntity = list.get(position);
            if (itemNewsEntity.getNewsId()!=0){
                ((ViewHolder2) holder).tvNewsId.setText(String.valueOf(itemNewsEntity.getNewsId()));
            }else {
                ((ViewHolder2) holder).tvNewsId.setText("暂无");
            }
            if (!TextUtils.isEmpty(itemNewsEntity.getClassId())){
                if (itemNewsEntity.getClassId()=="1"){
                    ((ViewHolder2) holder).tvNewsType.setText("公告");
                }else {
                    ((ViewHolder2) holder).tvNewsType.setText("新闻");
                }
            }else {
                ((ViewHolder2) holder).tvNewsType.setText("暂无");
            }
            if (!TextUtils.isEmpty(itemNewsEntity.getSubject())){
                ((ViewHolder2) holder).tvNewsSubject.setText(itemNewsEntity.getSubject());
            }else {
                ((ViewHolder2) holder).tvNewsSubject.setText("暂无");
            }
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_news_id)
        TextView tvNewsId;
        @BindView(R.id.tv_news_type)
        TextView tvNewsType;
        @BindView(R.id.tv_news_subject)
        TextView tvNewsSubject;
        public ViewHolder2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_info)
        TextView tvInfo;
        @BindView(R.id.tv_news_id)
        TextView tvNewsId;
        @BindView(R.id.tv_news_type)
        TextView tvNewsType;
        @BindView(R.id.tv_news_subject)
        TextView tvNewsSubject;

        public ViewHolder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
