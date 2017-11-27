package cn.cnlinfo.ccf.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.Constant;
import cn.cnlinfo.ccf.R;
import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import okhttp3.Headers;
import okhttp3.Response;

public class LaunchWeixinPayActivity extends BaseActivity implements IWXAPIEventHandler {

    @BindView(R.id.btn_register_appid_to_weixin)
    Button btnRegisterAppidToWeixin;
    @BindView(R.id.btn_get_prepare_id)
    Button btnGetPrepareId;
    @BindView(R.id.btn_send_pay)
    Button btnSendPay;
    private Unbinder unbinder;
    private IWXAPI msgApi;
    /**
     * 服务器生成预付单，获取到prepareId之后发起微信支付
     */
    String url = "http://wxpay.wxutil.com/pub_v2/app/app_pay.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_weixin_pay);
        unbinder = ButterKnife.bind(this);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        msgApi = WXAPIFactory.createWXAPI(this, Constant.APP_ID, false);

        btnRegisterAppidToWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgApi.registerApp(Constant.APP_ID);
            }
        });

        btnGetPrepareId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://wxpay.wxutil.com/pub_v2/app/app_pay.php";
                btnGetPrepareId.setEnabled(false);
                toast("获取订单中...");

                    HttpRequest.get(url,new BaseHttpRequestCallback(){

                        @Override
                        public void onResponse(final Response httpResponse, final String response, Headers headers) {
                            super.onResponse(httpResponse,response, headers);
                            toast("订单获取成功");
                            Logger.d(response);
                            btnSendPay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        byte[] buf =  response.getBytes();
                                        Logger.d(new String(buf));
                                        if (buf != null && buf.length > 0) {
                                            String content = new String(buf);
                                            JSONObject json = null;
                                            json = new JSONObject(content);
                                            if(null != json && !json.has("retcode") ){
                                                PayReq req = new PayReq();
                                                //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                                                req.appId			= json.getString("appid");
                                                req.partnerId		= json.getString("partnerid");
                                                req.prepayId		= json.getString("prepayid");
                                                req.nonceStr		= json.getString("noncestr");
                                                req.timeStamp		= json.getString("timestamp");
                                                req.packageValue	= json.getString("package");
                                                req.sign			= json.getString("sign");
                                                req.extData			= "app data"; // optional
                                                toast("正常调起支付");
                                                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信

                                                if (msgApi.isWXAppInstalled()){
                                                    if (msgApi.isWXAppSupportAPI()){
                                                            msgApi.openWXApp();
                                                            msgApi.sendReq(req);
                                                    }else {
                                                        toast("微信客户端不支持支付，请及时升级客户端版本!");
                                                    }
                                                }else {
                                                    toast("未安装微信客户端!");
                                                }

                                            }else{
                                                Logger.d("PAY_GET", "返回错误"+json.getString("retmsg"));
                                                toast("返回错误"+json.getString("retmsg"));
                                            }
                                        }else{
                                            Logger.d("服务器请求错误");
                                            toast("服务器请求错误");
                                        }
                                    } catch (Exception e) {
                                        Logger.d(e.getMessage());
                                        toast( "异常："+e.getMessage());
                                    }
                                }
                            });
                        }


                    });
                btnGetPrepareId.setEnabled(true);
            }
        });
        msgApi.handleIntent(getIntent(), this);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


// 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {

    }

}
