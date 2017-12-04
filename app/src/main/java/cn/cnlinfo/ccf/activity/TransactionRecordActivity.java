package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.adapter.TransactionRecordAdapter;

/**
 * Created by Administrator on 2017/11/27 0027.
 * 交易记录
 */

public class TransactionRecordActivity  extends BaseActivity{

    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private Unbinder unbinder;
    private RecyclerView mRecyclerview;
    TransactionRecordAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String[] date = new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_record);
        unbinder = ButterKnife.bind(this);
        tvTitle.setText("交易记录");
        ibtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setClickListener();

        for (int i = 0; i < date.length; i++) {
            date[i] = i+"";
        }
        adapter = new TransactionRecordAdapter(date,this);
        mLayoutManager = new LinearLayoutManager(this);
        //创建默认的线性LayoutManager
        mRecyclerview.setLayoutManager(mLayoutManager);
        //创建并设置Adapter
        mRecyclerview.setAdapter(adapter);
    }
    private void setClickListener() {
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
