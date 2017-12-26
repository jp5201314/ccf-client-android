package cn.cnlinfo.ccf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.API;
import cn.cnlinfo.ccf.Constant;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.activity.MyParameterActivity;
import cn.cnlinfo.ccf.activity.RechargeRegisterPointsActivity;
import cn.cnlinfo.ccf.dialog.DialogCreater;
import cn.cnlinfo.ccf.entity.ItemMealEntity;
import cn.cnlinfo.ccf.entity.User;
import cn.cnlinfo.ccf.net_okhttpfinal.CCFHttpRequestCallback;
import cn.cnlinfo.ccf.view.CleanEditText;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

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
    @BindView(R.id.tv_meal_price)
    TextView tvMealPrice;
    @BindView(R.id.et_register_integral)
    CleanEditText etRegisterIntegral;
    @BindView(R.id.tv_integral)
    TextView tvIntegral;
    @BindView(R.id.ll_integral)
    LinearLayout llIntegral;
    @BindView(R.id.et_safe_pass)
    CleanEditText etSafePass;
    @BindView(R.id.cb_is_read)
    CheckBox cbIsRead;
    @BindView(R.id.tv_upgrade_agency_link)
    TextView tvUpgradeAgencyLink;
    @BindView(R.id.et_meal_num)
    CleanEditText etMealNum;
    private Unbinder unbinder;
    private User user;
    private int serviceTypeId;
    private String myRank;
    private String registerIntegral;
    private int mealPrice;
    //用户有的注册积分
    private int re_integral;
    //用户有的消费积分
    private int co_integral;
    private int typeId;
    private String safePass;
    private int mealNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_combo_upgrade, container, false);
        unbinder = ButterKnife.bind(this, view);
        user = JSONObject.parseObject(UserSharedPreference.getInstance().getUserInfo(), User.class);
        init();
        btnUpgradeCombo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbIsRead.isChecked()) {
                    choosePurchaseMeal();
                } else {
                    toast("请仔细阅读并同意代理协议");
                }

            }
        });
        return view;
    }

    public void choosePurchaseMeal() {
        switch (typeId) {
            case 1:
                toPurchaseSailMeal();
                break;
            case 2:
                toPurchaseNormalMeal();
                break;
        }
    }


    /**
     * 购买普通套餐
     */
    private void toPurchaseNormalMeal() {
        safePass = etSafePass.getText().toString();
        mealNum = Integer.valueOf(etMealNum.getText().toString());
        if (!TextUtils.isEmpty(safePass)&&!TextUtils.isEmpty(etMealNum.getText().toString())){
            if (re_integral<mealPrice*mealNum){
                NormalDialog dialog = DialogCreater.createNormalDialog(getActivity(), "提示", "你现有的注册积分不足，是否去充值购买", new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        startActivity(new Intent(getActivity(), RechargeRegisterPointsActivity.class));
                        getActivity().finish();
                    }
                });
                dialog.show();
                return;
            }
            RequestParams params = new RequestParams();
            params.addFormDataPart("userID", user.getId());
            params.addFormDataPart("pwd2", safePass);
            params.addFormDataPart("setMealID", serviceTypeId);
            params.addFormDataPart("mealNum",mealNum );
            HttpRequest.post(Constant.OPERATE_CCF_HOST + API.PURCHASEMEAL, params, new CCFHttpRequestCallback() {
                @Override
                protected void onDataSuccess(JSONObject data) {
                    toast("购买成功");
                    Logger.d(data.toJSONString());
                }

                @Override
                protected void onDataError(int code,boolean flag, String msg) {
                    showMessage(code, msg);
                }
            });
        }else {
            toast("输入框不能为空");
        }

    }


    /**
     * 购买起航套餐
     */
    private void toPurchaseSailMeal() {
        safePass = etSafePass.getText().toString();
        registerIntegral = etRegisterIntegral.getText().toString();
        mealPrice = Integer.valueOf(tvMealPrice.getText().toString());
        String meal_num = etMealNum.getText().toString();
        if (!TextUtils.isEmpty(registerIntegral)&& !TextUtils.isEmpty(safePass) && mealPrice > 0&&!TextUtils.isEmpty(meal_num)) {
            long rePrice = Integer.valueOf(registerIntegral);
            mealNum = Integer.valueOf(meal_num);
            if (re_integral<mealPrice*mealNum){
                final NormalDialog dialog = DialogCreater.createNormalDialog(getActivity(), "提示", "你现有的注册积分不足，是否去充值购买", new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        startActivity(new Intent(getActivity(), MyParameterActivity.class));
                        getActivity().finish();
                    }
                });
                dialog.show();
                return;
            }
            if (rePrice <= re_integral) {
                if (rePrice >= mealPrice*mealNum * 0.3&&rePrice<=mealPrice*mealNum) {
                        RequestParams params = new RequestParams();
                        params.addFormDataPart("userID", user.getId());
                        params.addFormDataPart("setMealID", serviceTypeId);
                        params.addFormDataPart("registerScore", rePrice);
                        params.addFormDataPart("pwd2", safePass);
                        params.addFormDataPart("mealNum",mealNum );
                        HttpRequest.post(Constant.OPERATE_CCF_HOST + API.PURCHASEMEAL, params, new CCFHttpRequestCallback() {
                            @Override
                            protected void onDataSuccess(JSONObject data) {
                                toast("购买成功");
                                Logger.d(data.toJSONString());
                                getActivity().finish();
                            }

                            @Override
                            protected void onDataError(int code, boolean flag, String msg) {
                                showMessage(code, msg);
                            }
                        });
                } else {
                    toast("注册积分必须在" + mealPrice*mealNum * 0.3+"--"+mealPrice*mealNum+"之间");
                }
            } else {
                toast("注册积分不足,请重新输入!");
            }
        } else {
            toast("输入框不能为空");
        }
    }

    public void init() {
        showWaitingDialog(true);
        CharSequence charSequence = Html.fromHtml("已同意并愿意接受:<a href=\"https://www.pgyer.com/about/termofservice\">蒲公英协议");
        tvUpgradeAgencyLink.setText(charSequence);
        tvUpgradeAgencyLink.setMovementMethod(LinkMovementMethod.getInstance());
        tvUpgradeAgencyLink.setAutoLinkMask(Linkify.ALL);
        llIntegral.setVisibility(View.GONE);
        if (user != null) {
            re_integral = user.getRegisterIntegeral();
            co_integral = user.getConsumeIntegeral();
            tvIntegral.setText("你当前有" + re_integral + "注册积分和" + co_integral + "消费积分");
            int level = user.getInLevel();
            switch (level) {
                case 0:
                    tvMyRank.setText("体验用户");
                    myRank = tvMyRank.getText().toString();
                    break;
                case 1:
                    tvMyRank.setText("1星用户");
                    myRank = tvMyRank.getText().toString();
                    break;
                case 2:
                    tvMyRank.setText("2星用户");
                    myRank = tvMyRank.getText().toString();
                    break;
            }
        }
        gainMealList();
    }

    /**
     * 获取套餐列表
     */
    private void gainMealList() {
        HttpRequest.get(Constant.OPERATE_CCF_HOST + API.GETPACKAGELIST, new CCFHttpRequestCallback() {
            @Override
            protected void onDataSuccess(JSONObject data) {
                JSONArray jsonArray = data.getJSONArray("tree");
                final List<ItemMealEntity> itemMealEntities = JSON.parseArray(jsonArray.toJSONString(), ItemMealEntity.class);
                if (itemMealEntities != null && itemMealEntities.size() > 0) {
                    List<String> mealList = new ArrayList<>();
                    for (int i = 0; i < itemMealEntities.size(); i++) {
                        mealList.add(itemMealEntities.get(i).getMealName());
                    }
                    if (mealList != null && mealList.size() > 0) {
                        spServiceType.setAdapter(new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, mealList));
                        spServiceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                tvMealPrice.setText(itemMealEntities.get(position).getRegisterIntegral() + "");
                                serviceTypeId = itemMealEntities.get(position).getId();
                                typeId = itemMealEntities.get(position).getType();
                                switch (typeId) {
                                    case 1:
                                        if (llIntegral.getVisibility() == View.GONE) {
                                            llIntegral.setVisibility(View.VISIBLE);
                                          /*
                                            etRegisterIntegral.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                                @Override
                                                public void onFocusChange(View v, boolean hasFocus) {
                                                    if (hasFocus){
                                                        etConsumeIntegral.setText(String.valueOf(mealPrice-Integer.valueOf(etRegisterIntegral.getText().toString())));
                                                    }
                                                }
                                            });
                                            etConsumeIntegral.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                                @Override
                                                public void onFocusChange(View v, boolean hasFocus) {
                                                    if (hasFocus){
                                                        etRegisterIntegral.setText(String.valueOf(mealPrice-Integer.valueOf(etConsumeIntegral.getText().toString())));
                                                    }
                                                }
                                            });*/
                                        }
                                        break;
                                    case 2:
                                        if (llIntegral.getVisibility() == View.VISIBLE) {
                                            llIntegral.setVisibility(View.GONE);
                                        }
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
                showWaitingDialog(false);
            }

            @Override
            protected void onDataError(int code, boolean flag, String msg) {
                showMessage(code, msg);
                showWaitingDialog(false);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
