package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shizhefei.mvc.MVCHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.mvc.helper.MVCUltraHelper;
import cn.cnlinfo.ccf.view.FullyLinearLayoutManager;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by JP on 2017/11/25 0025.
 * 贡献值记录
 */

public class ContributeValueRecordActivity extends BaseActivity {

    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.pfl)
    PtrClassicFrameLayout pfl;
    private Unbinder unbinder;
    private MVCHelper mvcHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribute_value_record);
        unbinder = ButterKnife.bind(this);

        tvTitle.setText("贡献值记录");
        ibtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getContributeValueRecord();
    }


    private void getContributeValueRecord(){
        rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);
        rv.setLayoutManager(new FullyLinearLayoutManager(this));
        mvcHelper = new MVCUltraHelper(pfl);
        mvcHelper.setNeedCheckNetwork(true);
        //mvcHelper.setDataSource();
        //mvcHelper.setAdapter();
        mvcHelper.refresh();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mvcHelper.destory();
    }
}
