package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;

public class CCPublicBenefitActivity extends BaseActivity {

    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_donate_step)
    TextView tvDonateStep;
    @BindView(R.id.tv_beneficent_funds)
    TextView tvBeneficentFunds;
    @BindView(R.id.tv_plant_tree_funds)
    TextView tvPlantTreeFunds;

    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccpublic_benefit);
        unbinder = ButterKnife.bind(this);
        tvTitle.setText("碳控公益");
        ibtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
