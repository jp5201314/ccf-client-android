package cn.cnlinfo.ccf.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.API;
import cn.cnlinfo.ccf.Constant;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.entity.Exchangepackageinfo;
import cn.cnlinfo.ccf.entity.User;
import cn.cnlinfo.ccf.entity.Userstep;
import cn.cnlinfo.ccf.net_okhttpfinal.CCFHttpRequestCallback;
import cn.cnlinfo.ccf.step_count.UpdateUiCallBack;
import cn.cnlinfo.ccf.step_count.service.StepService;
import cn.cnlinfo.ccf.step_count.utils.SharedPreferencesUtils;
import cn.cnlinfo.ccf.view.CleanEditText;
import cn.cnlinfo.ccf.view.StepArcView;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * Created by JP on 2017/10/23 0023.
 */

public class CyclePackageFragment extends BaseFragment implements View.OnClickListener {
    Unbinder unbinder;
    @BindView(R.id.tv_cc_num)
    TextView tvCcNum;
    @BindView(R.id.tv_cycle_stock)
    TextView tvCycleStock;
    @BindView(R.id.self_step_arc)
    StepArcView selfStepArc;
    @BindView(R.id.tv_basic_contribute_value)
    TextView tvBasicContributeValue;
    @BindView(R.id.tv_wait_act_value)
    TextView tvWaitActValue;
    @BindView(R.id.tv_contribute_value)
    TextView tvContributeValue;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.sdv_cycle)
    SimpleDraweeView sdvCycle;
    @BindView(R.id.tv_cycle_pack)
    TextView tvCyclePack;
    @BindView(R.id.fl_cycle_package)
    FrameLayout flCyclePackage;
    @BindView(R.id.btn_at_once_transform)
    Button btnAtOnceTransform;
    @BindView(R.id.tv_pack_time)
    TextView tvPackTime;
    @BindView(R.id.tv_conversion_cycle_pack)
    TextView tvConversionCyclePack;
    @BindView(R.id.tv_hold_cycle_pack)
    TextView tvHoldCyclePack;
    @BindView(R.id.et_conversion_cycle_pack)
    CleanEditText etConversionCyclePack;
    @BindView(R.id.btn_conversion_cycle_pack)
    Button btnConversionCyclePack;
    @BindView(R.id.tv_current_rank)
    TextView tvCurrentRank;
    @BindView(R.id.tv_praise_num)
    TextView tvPraiseNum;

    private SharedPreferencesUtils sharedPreferencesUtils;
    private boolean isBind = false;
    private Animatable animatable;
    private User user;
    private Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cycle_package, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        /**
         *  Glide.with(getActivity()).load(R.drawable.icon_cycle).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new GlideDrawableImageViewTarget(ivCycle, 1));
         *  GlideDrawableImageViewTarget这个设置播放次数
         *  Glide.with(this).load(R.drawable.icon_cycle).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivCycle);
         */
        return view;
    }

    private void initData() {
        etConversionCyclePack.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    etConversionCyclePack.setText("");
                }
            }
        });
        setOnClickListener();
        user = UserSharedPreference.getInstance().getUser();
        setControllerIntoSdvCycle();
        sharedPreferencesUtils = new SharedPreferencesUtils(this.getApplicationContext());
        startUpService();
    }

    /**
     * 注册监听事件
     */
    private void setOnClickListener(){
        btnConversionCyclePack.setOnClickListener(this);
        btnAtOnceTransform.setOnClickListener(this);
    }

    /**
     * 获取兑换循环包数据
     */
    private void gainConversionCyclePackData(){
        RequestParams params = new RequestParams();
        params.addFormDataPart("userid",user.getId());
        HttpRequest.post(Constant.GET_MESSAGE_CODE_HOST + API.GETCIRCLE, params, new CCFHttpRequestCallback() {
            @Override
            protected void onDataSuccess(JSONObject data) {
                Exchangepackageinfo exchangepackageinfo = JSONObject.parseObject(data.getJSONObject("Exchangepackageinfo").toJSONString(),Exchangepackageinfo.class);
                setCyclePackParams(exchangepackageinfo);
            }

            @Override
            protected void onDataError(int code, boolean flag, String msg) {
                showMessage(code,msg);
            }
        });
    }

    /**
     * 设置循环包参数
     */
    private void setCyclePackParams( Exchangepackageinfo exchangepackageinfo){
        tvCcNum.setText(String.valueOf(exchangepackageinfo.getCCF()));
        tvCycleStock.setText(String.valueOf(exchangepackageinfo.getCircleTicket()));
        tvCyclePack.setText(String.valueOf(exchangepackageinfo.getCircle()));
        tvCenter.setText(String.valueOf(exchangepackageinfo.getCircle()));
        tvPackTime.setText(String.format(tvPackTime.getText().toString(),exchangepackageinfo.getPackTime()));
        tvConversionCyclePack.setText(String.format(tvConversionCyclePack.getText().toString(),exchangepackageinfo.getHaschange(),exchangepackageinfo.getResidue()));
        tvHoldCyclePack.setText(String.format(tvHoldCyclePack.getText().toString(),exchangepackageinfo.getUpperLimit()));
        etConversionCyclePack.setHint(String.valueOf(exchangepackageinfo.getConvertible()));
    }

    /**
     * 设置当前的步数
     *
     * @param currentStep
     */
    private void setCurrentStep(int currentStep) {
        //获取用户设置的计划锻炼步数，没有设置过的话默认2000
        String planWalk_QTY = (String) sharedPreferencesUtils.getParam("stepTotal", "2003");
        if (selfStepArc != null) {
            //设置当前步数为0
            selfStepArc.setCurrentCount(Integer.parseInt(planWalk_QTY), currentStep);
        }
        uploadStep(currentStep);
    }

    /**
     * 上传步数
     * @param step
     */
    private void uploadStep(int step){
        if (user!=null&&step!=0){
            RequestParams params = new RequestParams();
            params.addFormDataPart("userid",user.getId());
            params.addFormDataPart("step",step);
            HttpRequest.post(Constant.UPLOAD_STEP_HOST + API.UPLOADSTEP, params, new CCFHttpRequestCallback() {
                @Override
                protected void onDataSuccess(JSONObject data) {
                    Logger.d(data.toJSONString());
                    Userstep  userstep = JSONObject.parseObject(data.getJSONObject("Userstep").toJSONString(),Userstep.class);
                    if (userstep!=null){
                        tvCurrentRank.setText(String.format(tvCurrentRank.getText().toString(),userstep.getRanking()));
                        tvPraiseNum.setText(String.format(tvPraiseNum.getText().toString(),userstep.getPraise()));
                        if (!TextUtils.isEmpty(userstep.getF())){
                            tvBasicContributeValue.setText(userstep.getF());
                        }
                       if (!TextUtils.isEmpty(userstep.getE())){
                           tvContributeValue.setText(userstep.getE());
                       }
                        tvWaitActValue.setText(userstep.getCarbonnum());
                    }
                }
                @Override
                protected void onDataError(int code, boolean flag, String msg) {
                    showMessage(code,msg);
                }
            });
        }
    }

    /**
     * 开启计步服务
     */
    private void startUpService() {
        intent = new Intent(getActivity(), StepService.class);
        isBind = getActivity().bindService(intent, conn, Context.BIND_AUTO_CREATE);
        getActivity().startService(intent);
    }


    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StepService.StepBinder stepBinder = (StepService.StepBinder) service;
            StepService stepService = stepBinder.getService();
            int currentStep = stepService.getStepCount();
            setCurrentStep(currentStep);
            //设置步数监听回调
            stepService.registerCallback(new UpdateUiCallBack() {
                @Override
                public void updateUi(int stepCount) {
                    setCurrentStep(stepCount);
                }
            });
        }

        /**
         * 当与Service之间的连接丢失的时候会调用该方法，
         * 这种情况经常发生在Service所在的进程崩溃或者被Kill的时候调用，
         * 此方法不会移除与Service的连接，当服务重新启动的时候仍然会调用 onServiceConnected()。
         * @param name 丢失连接的组件名称
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /**
     * 设置控制器加载gif图片
     */
    private void setControllerIntoSdvCycle() {
        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                //加载drawable里的一张gif图
                .setUri(Uri.parse("res://" + getApplicationContext().getPackageName() + "/" + R.drawable.icon_cycle))//设置uri
                .build();
        sdvCycle.setController(mDraweeController);
    }

    @Override
    public void onResume() {
        super.onResume();
        gainConversionCyclePackData();
        Logger.d("onResume");
        animatable = sdvCycle.getController().getAnimatable();
        if (animatable != null) {
            animatable.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d("onPause");
        animatable = sdvCycle.getController().getAnimatable();
        if (animatable != null) {
            animatable.stop();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.d("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (isBind) {
            getActivity().unbindService(conn);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_conversion_cycle_pack:
                toConversionCyclePack();
                break;
                case R.id.btn_at_once_transform:

                    break;
        }
    }

    /**
     * 去兑换循环包
     */
    private void toConversionCyclePack(){
        String conversionCyclePack = etConversionCyclePack.getText().toString();
        if (!TextUtils.isEmpty(conversionCyclePack)){
            int num = Integer.valueOf(etConversionCyclePack.getText().toString());
            int limitNum = Integer.valueOf(etConversionCyclePack.getHint().toString());
            if (num<=limitNum){
                RequestParams params = new RequestParams();
                params.addFormDataPart("id",user.getId());
                params.addFormDataPart("num",num);
                HttpRequest.post(Constant.GET_MESSAGE_CODE_HOST + API.CONVERSIONCYCLEPACKAGE, params, new CCFHttpRequestCallback() {
                    @Override
                    protected void onDataSuccess(JSONObject data) {
                        toast("兑换成功");
                        Exchangepackageinfo exchangepackageinfo = JSONObject.parseObject(data.getJSONObject("Exchangepackageinfo").toJSONString(),Exchangepackageinfo.class);
                        setCyclePackParams(exchangepackageinfo);
                        etConversionCyclePack.setText("");
                    }

                    @Override
                    protected void onDataError(int code, boolean flag, String msg) {
                        showMessage(code,msg);
                    }
                });

            }else {
                toast("你的兑换量不足，请重新输入!!!");
            }
        }else {
            toast("请输入兑换循环包数量!!!");
        }
    }

}
