package cn.cnlinfo.ccf.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.listener.LongPressClick;
import cn.cnlinfo.ccf.view.RegularPolygonView;

public class ContributionMapActivity extends BaseActivity implements View.OnClickListener, LongPressClick {

    @BindView(R.id.rpv_center)
    RegularPolygonView rpvCenter;
    @BindView(R.id.rpv_top)
    RegularPolygonView rpvTop;
    @BindView(R.id.rpv_right_center)
    RegularPolygonView rpvRightCenter;
    @BindView(R.id.rpv_left_center)
    RegularPolygonView rpvLeftCenter;
    @BindView(R.id.rpv_bottom_right)
    RegularPolygonView rpvBottomRight;
    @BindView(R.id.rpv_bottom_left)
    RegularPolygonView rpvBottomLeft;
    @BindView(R.id.rl_map)
    RelativeLayout rlMap;
    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private AlertDialog alertDialog = null;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution_map);
        unbinder = ButterKnife.bind(this);
        tvTitle.setText("贡献图谱");
        registerOnClickListener();
        registerOnLongClickListener();
    }

    /**
     * 注册点击监听器
     */
    private void registerOnClickListener() {
        rpvCenter.setOnClickListener(this);
        rpvBottomLeft.setOnClickListener(this);
        rpvBottomRight.setOnClickListener(this);
        rpvLeftCenter.setOnClickListener(this);
        rpvRightCenter.setOnClickListener(this);
        rpvTop.setOnClickListener(this);
    }

    /**
     * 注册长按监听器
     */
    private void registerOnLongClickListener() {
        rpvCenter.setLongPressClick(this);
        rpvBottomLeft.setLongPressClick(this);
        rpvBottomRight.setLongPressClick(this);
        rpvLeftCenter.setLongPressClick(this);
        rpvRightCenter.setLongPressClick(this);
        rpvTop.setLongPressClick(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibt_back:
                finish();
                break;
            case R.id.rpv_center:
                toast("rpvCenter");
                rpvCenter.setText("sf001\n总:\t125");
                break;
            case R.id.rpv_bottom_left:
                toast("rpvBottomLeft");
                break;
            case R.id.rpv_bottom_right:
                toast("rpvBottomRight");
                break;
            case R.id.rpv_left_center:
                toast("rpvLeftCenter");
                break;
            case R.id.rpv_right_center:
                toast("rpvRightCenter");
                break;
            case R.id.rpv_top:
                toast("rpvTop");
                break;
        }
    }

    /**
     * 长按事件
     *
     * @param view
     */
    @Override
    public void run(View view) {
        switch (view.getId()) {
            case R.id.rpv_center:
                toast("onLongClick1");
                showMapInfoDialog();
                break;
            case R.id.rpv_bottom_left:
                toast("onLongClick2");
                break;
            case R.id.rpv_bottom_right:
                toast("onLongClick3");
                break;
            case R.id.rpv_left_center:
                toast("onLongClick4");
                break;
            case R.id.rpv_right_center:
                toast("onLongClick5");
                break;
            case R.id.rpv_top:
                toast("onLongClick6");
                break;
        }
    }

    private void showMapInfoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.dialog_contribution_map, null);
        ImageView iv_close = view.findViewById(R.id.iv_close_dialog);
        builder.setView(view);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alertDialog != null && alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
