package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;

public class MyParameterActivity extends BaseActivity {

    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.fl_title)
    FrameLayout flTitle;
    @BindView(R.id.tv_ccf)
    TextView tvCcf;
    @BindView(R.id.tv_cycle_package)
    TextView tvCyclePackage;
    @BindView(R.id.tv_wait_alive_ccf)
    TextView tvWaitAliveCcf;
    @BindView(R.id.tv_cc_volume)
    TextView tvCcVolume;
    @BindView(R.id.tv_bonus_points)
    TextView tvBonusPoints;
    @BindView(R.id.tv_cc_points)
    TextView tvCcPoints;
    @BindView(R.id.tv_tbr_ccr_points)
    TextView tvTbrCcrPoints;
    @BindView(R.id.tv_wait_release_points)
    TextView tvWaitReleasePoints;
    @BindView(R.id.tv_yesterday_contribute_value)
    TextView tvYesterdayContributeValue;
    @BindView(R.id.tv_current_contribution_value)
    TextView tvCurrentContributionValue;
    @BindView(R.id.tv_basic_contribution_rate)
    TextView tvBasicContributionRate;
    @BindView(R.id.tv_total_step)
    TextView tvTotalStep;
    @BindView(R.id.tv_today_step)
    TextView tvTodayStep;
    @BindView(R.id.tv_freeze_ccf)
    TextView tvFreezeCcf;
    @BindView(R.id.tv_wait_alive_limit)
    TextView tvWaitAliveLimit;
    @BindView(R.id.tv_average_difficulty_coefficient)
    TextView tvAverageDifficultyCoefficient;

    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_parameter);
        unbinder = ButterKnife.bind(this);
        tvTitle.setText("我的参数");
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
