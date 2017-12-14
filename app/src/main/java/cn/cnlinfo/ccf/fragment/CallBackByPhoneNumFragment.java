package cn.cnlinfo.ccf.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.view.CleanEditText;

/**
 * Created by JP on 2017/10/25 0025.
 */

public class CallBackByPhoneNumFragment extends BaseFragment {
    @BindView(R.id.et_login_pass)
    CleanEditText etLoginPass;
    @BindView(R.id.et_verify_pass)
    CleanEditText etVerifyPass;
    @BindView(R.id.et_phone_num)
    CleanEditText etPhoneNum;
    @BindView(R.id.et_verification_code_callback)
    CleanEditText etVerificationCodeCallback;
    @BindView(R.id.btn_get_verification_code)
    Button btnGetVerificationCode;
    @BindView(R.id.btn_ok)
    Button btnOk;
    private Unbinder unbinder;
    private String username;
    private String loginPass;
    private String verifyPass;
    private String phoneNum;
    private String verifyCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call_back_by_phone_num, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initEditData(){
        loginPass = etLoginPass.getText().toString();
        verifyPass = etVerifyPass.getText().toString();
        phoneNum = etPhoneNum.getText().toString();
        verifyCode = etVerificationCodeCallback.getText().toString();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
