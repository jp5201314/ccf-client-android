package cn.cnlinfo.ccf.kotlin.activity

import android.content.Context
import android.content.SharedPreferences
import cn.cnlinfo.ccf.manager.ACache

/**
 * Created by JP on 2017/12/1 0001.
 *  存储登录信息以及版本信息
 */
class UserSharedPreference{
    companion object {
       @JvmStatic var instance : UserSharedPreference? = null
        val SHARED_PREFERENCE_NAME : String = "USER"
        val CACHE_SECONDS : Int = 60*60
        val CACHE_JWT_TOKEN_KEY : String = "jwt_token"
        val CACHE_LATEST_VERSION_CODE_KEY : String = "latest_version_code"
        val CACHE_USER_KEY: String = "user"
        val CACHE_PHONE_PASSWORD_KEY:String = "phone_password"
    }

    var mContext : Context? = null
    private var mAcache : ACache? = null
    var mSharedPreferences : SharedPreferences? = null
    var mEditor : SharedPreferences.Editor? = null

    private constructor(context: Context){
        this.mContext = context
        this.mAcache = ACache.get(mContext)
        this.mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE)
        this.mEditor = mSharedPreferences!!.edit()
    }


}