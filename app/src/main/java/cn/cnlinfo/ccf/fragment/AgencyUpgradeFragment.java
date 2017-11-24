package cn.cnlinfo.ccf.fragment;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.lljjcoder.city_20170724.CityPickerView;
import com.lljjcoder.city_20170724.bean.CityBean;
import com.lljjcoder.city_20170724.bean.DistrictBean;
import com.lljjcoder.city_20170724.bean.ProvinceBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.entity.User;
import cn.cnlinfo.ccf.utils.CityPickerUtils;
import cn.cnlinfo.ccf.utils.SpinnerUtils;
import cn.cnlinfo.ccf.view.CleanEditText;

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
    @BindView(R.id.et_safe_pass)
    CleanEditText etSafePass;
    @BindView(R.id.cb_is_read)
    CheckBox cbIsRead;
    @BindView(R.id.tv_upgrade_agency_link)
    TextView tvUpgradeAgencyLink;
    private Unbinder unbinder;
    private User user;
    private String safePass;
    private ProvinceBean provinceBean;
    private CityBean cityBean;
    private DistrictBean districtBean;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agency_upgrade, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        btnUpgradeAgency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toUpgradeAgency();
            }
        });
        return view;
    }

    /**
     * 初始化操作
     */
    private void init() {
        CharSequence charSequence = Html.fromHtml("已同意并愿意接受:<a href=\"https://www.pgyer.com/about/termofservice\">蒲公英协议");
        tvUpgradeAgencyLink.setText(charSequence);
        tvUpgradeAgencyLink.setMovementMethod(LinkMovementMethod.getInstance());
        tvUpgradeAgencyLink.setAutoLinkMask(Linkify.ALL);
        user = UserSharedPreference.getInstance().getUser();
        int level = user.getInLevel();
        switch (level) {
            case 0:
                tvMyRank.setText("体验用户");
                break;
            case 1:
                tvMyRank.setText("1星用户");
                break;
            case 2:
                tvMyRank.setText("2星用户");
                break;
        }

        spAgencyType.setAdapter(SpinnerUtils.getArrayAdapter(getActivity(), getResources().getStringArray(R.array.agency_type)));
        spServiceType.setAdapter(SpinnerUtils.getArrayAdapter(getActivity(), getResources().getStringArray(R.array.service_type)));
        tvAgencyAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityPickerView cityPickerView = CityPickerUtils.showCityPicker(getActivity(), 0);
                cityPickerView.setOnCityItemClickListener(new CityPickerView.OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean provinceBean, CityBean cityBean, DistrictBean districtBean) {
                        tvAgencyAddress.setText(provinceBean.getName() + "-" + cityBean.getName() + "-" + districtBean.getName());
                        AgencyUpgradeFragment.this.provinceBean = provinceBean;
                        AgencyUpgradeFragment.this.cityBean = cityBean;
                        AgencyUpgradeFragment.this.districtBean = districtBean;
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        });
    }

    private void toUpgradeAgency() {
        if (cbIsRead.isChecked()) {
            safePass = etSafePass.getText().toString();
            if (!TextUtils.isEmpty(safePass)) {

            } else {
                toast("输入框不能为空");
            }
        } else {
            toast("请仔细阅读并同意代理协议");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
