package cn.cnlinfo.ccf.activity;

import android.os.Bundle;

import butterknife.ButterKnife;
import cn.cnlinfo.ccf.R;

public class RecommendNetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_net);
        ButterKnife.bind(this);
    }
}
