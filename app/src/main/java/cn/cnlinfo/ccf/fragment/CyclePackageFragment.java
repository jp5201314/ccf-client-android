package cn.cnlinfo.ccf.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Animatable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.step_count.UpdateUiCallBack;
import cn.cnlinfo.ccf.step_count.service.StepService;
import cn.cnlinfo.ccf.step_count.utils.SharedPreferencesUtils;
import cn.cnlinfo.ccf.view.StepArcView;

/**
 * Created by Administrator on 2017/10/23 0023.
 */

public class CyclePackageFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.tv_cc_num)
    TextView tvCcNum;
    @BindView(R.id.tv_cycle_stock)
    TextView tvCycleStock;
    @BindView(R.id.tv_cycle_pack)
    TextView tvCyclePack;
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

    private SharedPreferencesUtils sharedPreferencesUtils;
    private boolean isBind = false;
    private Animatable animatable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cycle_package, container, false);

        unbinder = ButterKnife.bind(this, view);
        initData();
        /**
         *  Glide.with(getActivity()).load(R.drawable.icon_cycle).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new GlideDrawableImageViewTarget(ivCycle, 1));
         *  GlideDrawableImageViewTarget这个设置播放次数
         *   Glide.with(this).load(R.drawable.icon_cycle).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivCycle);
         */
        return view;
    }

    private void initData() {
        setControllerIntoSdvCycle();
        sharedPreferencesUtils = new SharedPreferencesUtils(this.getApplicationContext());
        setCurrentStep(0);
        startUpService();
    }

    /**
     * 设置当前的步数
     *
     * @param currentStep
     */
    private void setCurrentStep(int currentStep) {
        //获取用户设置的计划锻炼步数，没有设置过的话默认2000
        String planWalk_QTY = (String) sharedPreferencesUtils.getParam("stepTotal", "2000");
        //设置当前步数为0
        selfStepArc.setCurrentCount(Integer.parseInt(planWalk_QTY), currentStep);
    }

    /**
     * 开启计步服务
     */
    private void startUpService() {
        Intent intent = new Intent(getActivity(), StepService.class);
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
        animatable = sdvCycle.getController().getAnimatable();
        if (animatable != null) {
            animatable.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        animatable = sdvCycle.getController().getAnimatable();
        if (animatable != null) {
            animatable.stop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (isBind) {
            getActivity().unbindService(conn);
        }
    }
}
