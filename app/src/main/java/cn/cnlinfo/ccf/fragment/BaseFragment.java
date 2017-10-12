package cn.cnlinfo.ccf.fragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class BaseFragment extends Fragment {


    protected void toast(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }
}
