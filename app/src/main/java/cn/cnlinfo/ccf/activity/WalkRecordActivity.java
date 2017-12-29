package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shizhefei.mvc.MVCHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.adapter.StepRecordAdapter;
import cn.cnlinfo.ccf.entity.TransferRecordEntity;
import cn.cnlinfo.ccf.mvc.datasource.StepRecordDataSource;
import cn.cnlinfo.ccf.mvc.helper.MVCUltraHelper;
import cn.cnlinfo.ccf.view.FullyLinearLayoutManager;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by Administrator on 2017/11/30 0030.
 * 步行记录
 */

public class WalkRecordActivity extends BaseActivity {

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
        setContentView(R.layout.activity_walk_record);
        unbinder = ButterKnife.bind(this);
        tvTitle.setText("步行记录");
        ibtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getWalkRecord();
    }

    private void getWalkRecord() {
        //MVCHelper.setLoadViewFractory(new NormalNoLoadViewFactory());
        //this.setMaterialHeader(pfl);
        rv.setLayoutManager(new FullyLinearLayoutManager(this));
        rv.setNestedScrollingEnabled(false);
        mvcHelper = new MVCUltraHelper<List<TransferRecordEntity>>(pfl);
        mvcHelper.setNeedCheckNetwork(true);
        mvcHelper.setDataSource(new StepRecordDataSource());
        mvcHelper.setAdapter(new StepRecordAdapter(this));
        mvcHelper.refresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mvcHelper.destory();
    }
}
