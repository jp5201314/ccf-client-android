package cn.cnlinfo.ccf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.tendcloud.tenddata.TCAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.activity.ContributionMapActivity;
import cn.cnlinfo.ccf.activity.RegisterMemberActivity;
import cn.cnlinfo.ccf.activity.RunningRankActivity;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class GaugePanelFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @BindView(R.id.gv_gauge_panel)
    GridView gvGaugePanel;
    private Unbinder unbinder;
    private String[] names = {"注册会员", "贡献图谱", "推荐网络", "我的参数",
            "平台参数", "跑步排名", "系统公告", "在线钱包",
            "对外互传", "内部互转", "用户升级", "记录中心",
            "碳控公益", "在线客服", "碳控服务", "我要推广"};
    private int[] icons = {R.drawable.icon_register_member, R.drawable.icon_contribution_atlas, R.drawable.icon_recommend_net, R.drawable.icon_my_parameter,
            R.drawable.icon_platform_parameter, R.drawable.icon_start_running, R.drawable.icon_system_notice, R.drawable.icon_online_wallet,
            R.drawable.icon_external_each_other, R.drawable.icon_internal_each_other, R.drawable.icon_user_upgrade, R.drawable.icon_recode_centre,
            R.drawable.icon_cc_pf, R.drawable.icon_online_service, R.drawable.icon_cc_service, R.drawable.icon_promote};
    private List<Map<String, Object>> list;
    private SimpleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TCAgent.onPageStart(getActivity(), "仪表盘");
        View view = inflater.inflate(R.layout.fragment_gauge_panel, container, false);
        unbinder = ButterKnife.bind(this, view);
        adapter = new SimpleAdapter(getActivity(), getData(), R.layout.item_gv_panel, new String[]{"icon", "name"}, new int[]{R.id.item_iv_icon, R.id.item_tv_name});
        gvGaugePanel.setAdapter(adapter);
        gvGaugePanel.setOnItemClickListener(this);
        return view;

    }

    private List<Map<String, Object>> getData() {
        list = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", names[i]);
            map.put("icon", icons[i]);
            list.add(map);
        }
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        TCAgent.onPageEnd(getActivity(), "仪表盘");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Intent intent = new Intent(getActivity(), RegisterMemberActivity.class);
                startActivity(intent);
                break;
            case 1:
                startActivity(new Intent(getActivity(), ContributionMapActivity.class));
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:
                startActivity(new Intent(getActivity(), RunningRankActivity.class));
                break;
            case 6:

                break;
            case 7:

                break;
            case 8:

                break;
            case 9:

                break;
            case 10:

                break;
            case 11:

                break;
            case 12:

                break;
            case 13:

                break;
            case 14:

                break;
            case 15:

                break;
        }
    }
}
