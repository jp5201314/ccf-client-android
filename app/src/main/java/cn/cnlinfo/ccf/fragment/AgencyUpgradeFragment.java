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

public class AgencyUpgradeFragment extends BaseFragment {

    @BindView(R.id.tv_my_rank)
    TextView tvMyRank;
    @BindView(R.id.sp_agency_type)
    Spinner spAgencyType;
    @BindView(R.id.sp_service_type)
    Spinner spServiceType;
    @BindView(R.id.tv_agency_address)
    TextView tvAgencyAddress;
    @BindView(R.id.btn_upgrade_agency)
    Button btnUpgradeAgency;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agency_upgrade, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
