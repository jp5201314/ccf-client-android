package cn.cnlinfo.ccf.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.cnlinfo.ccf.R;

/**
 * Created by Administrator on 2017/10/23 0023.
 */

public class MainPageInfoFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment_one,container,false);
        return view;
    }
}
