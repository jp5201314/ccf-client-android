package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import cn.cnlinfo.ccf.entity.PrimaryNodeEntity;
import cn.cnlinfo.ccf.entity.SecondaryNode;
import cn.cnlinfo.ccf.entity.User;
import cn.cnlinfo.ccf.net_okhttpfinal.CCFHttpRequestCallback;
import cn.cnlinfo.ccf.view.ViewWithLine;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

public class RecommendNetActivity extends BaseActivity {

    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.vwl_self)
    ViewWithLine vwlSelf;
    private Unbinder unbinder;
    private User user;
    private List<PrimaryNodeEntity> primaryNodeEntityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_net);
        unbinder = ButterKnife.bind(this);
        tvTitle.setText("推荐网络");
        init();
        ibtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        user = UserSharedPreference.Companion.getInstance().getUser();
        vwlSelf.rectF_1_1_Text = user.getUCode();
        primaryNodeEntityList = new ArrayList<>();
        showWaitingDialog(true);
        RequestParams params = new RequestParams();
        params.addFormDataPart("userID", user.getID());
        HttpRequest.post(Constant.CONTRIBUTIONMAP_HOST + API.USERCHILDCODEDATA, params, new CCFHttpRequestCallback() {
            @Override
            protected void onDataSuccess(JSONObject data) {
                JSONArray jsonArray = data.getJSONArray("root");
                primaryNodeEntityList = JSONArray.parseArray(jsonArray.toJSONString(),PrimaryNodeEntity.class);
                if (primaryNodeEntityList!=null&&primaryNodeEntityList.size()>0){
                    for (int i = 0;i<primaryNodeEntityList.size();i++){
                        PrimaryNodeEntity primaryNodeEntity = null;
                        primaryNodeEntity = primaryNodeEntityList.get(i);
                        String username = primaryNodeEntity.getuCode();
                        Logger.d(username);
                        if (i==0&&!TextUtils.isEmpty(username)){
                            vwlSelf.rectF_2_1_Text = username;
                            List<SecondaryNode> secondaryNodeList = primaryNodeEntity.getSecondaryNodeList();
                            if (secondaryNodeList!=null&&secondaryNodeList.size()>0){
                                for (int j = 0;j<secondaryNodeList.size();j++){
                                    SecondaryNode secondaryNode = null;
                                    secondaryNode = secondaryNodeList.get(j);
                                    String se_name = secondaryNode.getuCode();
                                    if (j==0&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_1_Text = se_name;
                                    }
                                    if (j==1&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_2_Text = se_name;
                                    }
                                    if (j==2&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_3_Text = se_name;
                                    }
                                    if (j==3&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_4_Text = se_name;
                                    }
                                    if (j==4&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_5_Text = se_name;
                                    }
                                }
                            }
                        }
                        if (i==1&&!TextUtils.isEmpty(username)){
                            vwlSelf.rectF_2_2_Text = username;
                            List<SecondaryNode> secondaryNodeList = primaryNodeEntity.getSecondaryNodeList();
                            if (secondaryNodeList!=null&&secondaryNodeList.size()>0){
                                for (int j = 0;j<secondaryNodeList.size();j++){
                                    SecondaryNode secondaryNode = null;
                                    secondaryNode = secondaryNodeList.get(j);
                                    String se_name = secondaryNode.getuCode();
                                    if (j==0&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_6_Text = se_name;
                                    }
                                    if (j==1&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_7_Text = se_name;
                                    }
                                    if (j==2&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_8_Text = se_name;
                                    }
                                    if (j==3&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_9_Text = se_name;
                                    }
                                    if (j==4&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_10_Text = se_name;
                                    }
                                }
                            }
                        }
                        if (i==2&&!TextUtils.isEmpty(username)){
                            vwlSelf.rectF_2_3_Text = username;
                            List<SecondaryNode> secondaryNodeList = primaryNodeEntity.getSecondaryNodeList();
                            if (secondaryNodeList!=null&&secondaryNodeList.size()>0){
                                for (int j = 0;j<secondaryNodeList.size();j++){
                                    SecondaryNode secondaryNode = null;
                                    secondaryNode = secondaryNodeList.get(j);
                                    String se_name = secondaryNode.getuCode();
                                    if (j==0&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_11_Text = se_name;
                                    }
                                    if (j==1&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_12_Text = se_name;
                                    }
                                    if (j==2&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_13_Text = se_name;
                                    }
                                    if (j==3&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_14_Text = se_name;
                                    }
                                    if (j==4&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_15_Text = se_name;
                                    }
                                }
                            }
                        }
                        if (i==3&&!TextUtils.isEmpty(username)){
                            vwlSelf.rectF_2_4_Text = username;
                            List<SecondaryNode> secondaryNodeList = primaryNodeEntity.getSecondaryNodeList();
                            if (secondaryNodeList!=null&&secondaryNodeList.size()>0){
                                for (int j = 0;j<secondaryNodeList.size();j++){
                                    SecondaryNode secondaryNode = null;
                                    secondaryNode = secondaryNodeList.get(j);
                                    String se_name = secondaryNode.getuCode();
                                    if (j==0&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_16_Text = se_name;
                                    }
                                    if (j==1&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_17_Text = se_name;
                                    }
                                    if (j==2&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_18_Text = se_name;
                                    }
                                    if (j==3&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_19_Text = se_name;
                                    }
                                    if (j==4&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_20_Text = se_name;
                                    }
                                }
                            }
                        }
                        if (i==4&&!TextUtils.isEmpty(username)){
                            vwlSelf.rectF_2_5_Text = username;
                            List<SecondaryNode> secondaryNodeList = primaryNodeEntity.getSecondaryNodeList();
                            if (secondaryNodeList!=null&&secondaryNodeList.size()>0){
                                for (int j = 0;j<secondaryNodeList.size();j++){
                                    SecondaryNode secondaryNode = null;
                                    secondaryNode = secondaryNodeList.get(j);
                                    String se_name = secondaryNode.getuCode();
                                    if (j==0&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_21_Text = se_name;
                                    }
                                    if (j==1&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_22_Text = se_name;
                                    }
                                    if (j==2&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_23_Text = se_name;
                                    }
                                    if (j==3&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_24_Text = se_name;
                                    }
                                    if (j==4&&!TextUtils.isEmpty(se_name)){
                                        vwlSelf.rectF_3_25_Text = se_name;
                                    }
                                }
                            }
                        }
                    }
                }
                vwlSelf.postInvalidate();
                showWaitingDialog(false);
            }

            @Override
            protected void onDataError(int code, boolean flag, String msg) {
                showWaitingDialog(false);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
