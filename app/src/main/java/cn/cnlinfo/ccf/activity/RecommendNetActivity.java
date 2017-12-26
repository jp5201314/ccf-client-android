package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shizhefei.mvc.IDataAdapter;
import com.shizhefei.mvc.MVCHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.adapter.ShareUserAdapter;
import cn.cnlinfo.ccf.entity.ShareUserEntity;
import cn.cnlinfo.ccf.mvc.datasource.ShareUserDataSource;
import cn.cnlinfo.ccf.mvc.factory.MyLoadViewFactory;
import cn.cnlinfo.ccf.mvc.helper.MVCUltraHelper;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class RecommendNetActivity extends BaseActivity {
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
    private List<ShareUserEntity> shareUserEntityList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_net);
        unbinder = ButterKnife.bind(this);
        tvTitle.setText("推荐网络");
        ibtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gainTierList();
    }

    /**
     * 获取当前用户层级列表
     */
    private void gainTierList() {
        shareUserEntityList = new ArrayList<>();
        MVCHelper.setLoadViewFractory(new MyLoadViewFactory());
        this.setMaterialHeader(pfl);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setNestedScrollingEnabled(true);
        mvcHelper = new MVCUltraHelper<List<ShareUserEntity>>(pfl);
        mvcHelper.setNeedCheckNetwork(true);
        mvcHelper.setDataSource(new ShareUserDataSource());
        mvcHelper.setAdapter(new IDataAdapter<List<ShareUserEntity>>() {
            @Override
            public void notifyDataChanged(List<ShareUserEntity> list, boolean isRefresh) {
                if (rv != null) {
                    if (list!=null){
                        shareUserEntityList.addAll(0,list);
                        rv.setAdapter(new ShareUserAdapter(RecommendNetActivity.this,shareUserEntityList));
                    }
                }
            }

            @Override
            public List<ShareUserEntity> getData() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });
        mvcHelper.refresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mvcHelper.destory();
    }
}
