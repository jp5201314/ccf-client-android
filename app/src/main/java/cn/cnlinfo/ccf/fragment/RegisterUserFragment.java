package cn.cnlinfo.ccf.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.cnlinfo.ccf.R;

/**
 * Created by Administrator on 2017/11/6 0006.
 */

public class RegisterUserFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_user, container, false);
        return view;
    }
}
