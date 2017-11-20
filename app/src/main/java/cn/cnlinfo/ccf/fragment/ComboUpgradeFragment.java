package cn.cnlinfo.ccf.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;

/**
 * Created by JP on 2017/11/20 0020.
 */

public class ComboUpgradeFragment extends BaseFragment {

    @BindView(R.id.tv_my_rank)
    TextView tvMyRank;
    @BindView(R.id.sp_service_type)
    Spinner spServiceType;
    @BindView(R.id.btn_upgrade_combo)
    Button btnUpgradeCombo;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_combo_upgrade, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
