package cn.cnlinfo.ccf.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import cn.cnlinfo.ccf.API
import cn.cnlinfo.ccf.Constant
import cn.cnlinfo.ccf.R
import cn.cnlinfo.ccf.UserSharedPreference
import cn.cnlinfo.ccf.net_okhttpfinal.CCFHttpRequestCallback
import cn.cnlinfo.ccf.utils.ObtainVerificationCode
import cn.finalteam.okhttpfinal.HttpRequest
import cn.finalteam.okhttpfinal.RequestParams
import com.alibaba.fastjson.JSONObject
import kotlinx.android.synthetic.main.activity_login_register.*

/**
 * Created by JP on 2017/10/11 0011.
 */

class LoginRegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)
        tv_get_verification_code.text = ObtainVerificationCode.createVerificationCode()
    }
    fun gainVerificationCode(view: View) {
        tv_get_verification_code.text = ObtainVerificationCode.createVerificationCode()
    }
    fun toLogin(view: View) {
        startLogin()
    }
   private fun startLogin() {
        if (!TextUtils.isEmpty(et_username!!.text) && !TextUtils.isEmpty(et_password!!.text) && !TextUtils.isEmpty(et_verification_code!!.text)) {
            if (et_verification_code!!.text!!.toString() == tv_get_verification_code!!.text!!.toString()) {
                val params = RequestParams()
                params.addFormDataPart("username", et_username!!.text!!.toString())
                params.addFormDataPart("password", et_password!!.text!!.toString())
                HttpRequest.post(Constant.getHost() + API.CCFLOGIN, params, object : CCFHttpRequestCallback() {
                    override fun onDataSuccess(data: JSONObject?) {
                        showMessage(0,"登录成功")
                        startActivity(Intent(this@LoginRegisterActivity, MainPageActivity::class.java))
                        this@LoginRegisterActivity.finish()
                        val usp = UserSharedPreference.getInstance()
                        usp.jwtToken = "1"
                        usp.isFirstLogin = true
                        val userinfoJsonobject = data!!.getJSONObject("userinfo")
                        val jsonString = userinfoJsonobject!!.toJSONString()
                        usp.userInfo = jsonString
                    }

                    override fun onDataError(code: Int, flag: Boolean, msg: String) {
                        showMessage(code,msg)
                    }
                })
            } else {
                toast("验证码不正确")
            }
        } else {
            toast("输入框不为空")
        }
    }

    fun toRegister(view: View) {
        skipToRegister()
    }

    private fun skipToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun toForgetPass(view: View) {
        startActivity(Intent(this, ForgetPasswordActivity::class.java))
    }
}