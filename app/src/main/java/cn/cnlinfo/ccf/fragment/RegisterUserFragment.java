package cn.cnlinfo.ccf.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.view.CleanEditText;

/**
 * Created by Administrator on 2017/11/6 0006.
 */

public class RegisterUserFragment extends BaseFragment {

    @BindView(R.id.et_invitation_code)
    CleanEditText etInvitationCode;
    @BindView(R.id.et_user_name)
    CleanEditText etUserName;
    @BindView(R.id.sp_area_type)
    Spinner spAreaType;
    @BindView(R.id.et_login_pass)
    CleanEditText etLoginPass;
    @BindView(R.id.et_verify_pass)
    CleanEditText etVerifyPass;
    @BindView(R.id.et_phone_num)
    CleanEditText etPhoneNum;
    @BindView(R.id.et_verification_code_register)
    CleanEditText etVerificationCodeRegister;
    @BindView(R.id.btn_get_verification_code)
    Button btnGetVerificationCode;
    @BindView(R.id.btn_register_user)
    Button btnRegisterUser;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
