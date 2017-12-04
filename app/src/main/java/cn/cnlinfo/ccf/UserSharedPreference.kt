package cn.cnlinfo.ccf

import android.content.Context
import android.content.SharedPreferences
import cn.cnlinfo.ccf.entity.User
import cn.cnlinfo.ccf.manager.ACache
import com.alibaba.fastjson.JSONObject

/**
 * Created by JP on 2017/10/11
 *
 *
 * 存储登录信息以及版本信息
 */
class UserSharedPreference {
    var mContext : Context? = null
    private var mACache: ACache? = null
    private var mSharedPreferences: SharedPreferences? = null
    private var mEditor: SharedPreferences.Editor? = null

    internal constructor(context: Context){
        this.mContext = context
        this.mACache = ACache.get(mContext)
        this.mSharedPreferences = context.getSharedPreferences(cn.cnlinfo.ccf.kotlin.activity.UserSharedPreference.SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE)
        this.mEditor = mSharedPreferences!!.edit()
        instance = this
    }

    val user: User?
        get() = JSONObject.parseObject(instance!!.userInfo, User::class.java)

    var userInfo: String?
        get() {
            val jsonObjectFormCache = this.userInfoFormCache
            val jsonObjectFormSharedPreferences = this.userInfoFormSharedpreferences
            return jsonObjectFormCache ?: jsonObjectFormSharedPreferences
        }
        set(userinfo) {
            this.setUserInfoToSharedPreferences(userinfo.toString())
            this.setUserInfoToCache(userinfo!!)
        }

    private val userInfoFormSharedpreferences: String?
        get() = mSharedPreferences!!.getString("userinfo", null)

    private val userInfoFormCache: String?
        get() = mACache!!.getAsString("userinfo")

    var isFirstLogin: Boolean
        get() = mSharedPreferences!!.getBoolean("isFirstLogin", false)
        set(flag) {
            mEditor!!.putBoolean("isFirstLogin", flag)
            mEditor!!.commit()
        }

    /**
     * 获取用户的jwt token
     */
    /**
     * 设置用户的jwt token
     *
     * @param token
     */
    var jwtToken: String?
        get() {
            var token = this.jwtTokenFromCache

            if (null == token) {
                token = this.jwtTokenFromSharedPreference

                if (null != token) {
                    this.putJwtTokenToCache(token!!)
                }
            }
            return token
        }
        set(token) {
            this.putJwtTokenToCache(token!!)

            this.putJwtTokenToSharedPreference(token!!)
        }

    private val jwtTokenFromCache: String?
        get() = mACache!!.getAsString(CACHE_JWT_TOKEN_KEY)

    private val jwtTokenFromSharedPreference: String?
        get() = if (!mSharedPreferences!!.contains(CACHE_JWT_TOKEN_KEY)) {
            null
        } else mSharedPreferences!!.getString(CACHE_JWT_TOKEN_KEY, null)

    /**
     * 获取最后记录的app版本号
     *
     *
     * return int   -1 if no latest version code
     */
    /**
     * 设置app最后的版本号
     *
     * @param versionCode
     */
    var latestVersionCode: Int
        get() {
            var versionCode = this.latestVersionCodeFromCache

            if (versionCode < 0) {
                versionCode = this.latestVersionCodeFromSharedPreference

                if (versionCode > 0) {
                    this.latestVersionCode = versionCode
                }
            }

            return versionCode
        }
        set(versionCode) {
            this.putLatestVersionCodeToCache(versionCode)

            this.putLatestVersionCodeToSharedPreference(versionCode)
        }

    private val latestVersionCodeFromCache: Int
        get() {
            val `object` = mACache!!.getAsObject(CACHE_LATEST_VERSION_CODE_KEY) ?: return -1
            return (`object` as Int).toInt()
        }

    private val latestVersionCodeFromSharedPreference: Int
        get() = if (!mSharedPreferences!!.contains(CACHE_LATEST_VERSION_CODE_KEY)) {
            -1
        } else mSharedPreferences!!.getInt(CACHE_LATEST_VERSION_CODE_KEY, -1)

    /**
     * 获取账号密码
     */
    val phoneAndPassword: String?
        get() {
            var phoneAndPassword = this.phoneAndPasswordFromCache

            if (null == phoneAndPassword) {
                phoneAndPassword = this.phoneAndPasswordFromSharedPreference

                if (null != phoneAndPassword) {
                    this.putPhoneAndPasswordToSharedPreference(phoneAndPassword.substring(0, 11), phoneAndPassword.substring(12))
                }
            }
            return phoneAndPassword
        }

    private val phoneAndPasswordFromCache: String?
        get() = mACache!!.getAsString(CACHE_PHONE_PASSWORD_KEY)

    private val phoneAndPasswordFromSharedPreference: String?
        get() = if (!mSharedPreferences!!.contains(CACHE_PHONE_PASSWORD_KEY)) {
            null
        } else mSharedPreferences!!.getString(CACHE_PHONE_PASSWORD_KEY, null)

    init {
        this.mACache = ACache.get(mContext)
        this.mSharedPreferences = mContext!!.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        this.mEditor = mSharedPreferences!!.edit()
    }

    private fun setUserInfoToSharedPreferences(userInfoToSharedPreferences: String) {
        mEditor!!.putString("userinfo", userInfoToSharedPreferences)
        mEditor!!.commit()
    }

    private fun setUserInfoToCache(userInfoToCache: String) {
        mACache!!.put("userinfo", userInfoToCache)
    }

    /**
     * 是否已经登录
     *
     * @return boolean
     */
    fun hasLogined(): Boolean {
        return null != this.phoneAndPassword || null != this.jwtToken
    }


    /**
     * 退出登录
     */
    fun logout() {
        this.removeJwtToken()
        this.removePhoneAndPassword()
        this.removeUser()
    }

    private fun putJwtTokenToCache(token: String) {
        mACache!!.put(CACHE_JWT_TOKEN_KEY, token, CACHE_SECONDS)
    }

    private fun putJwtTokenToSharedPreference(token: String) {
        mEditor!!.putString(CACHE_JWT_TOKEN_KEY, token)
        mEditor!!.commit()
    }

    /**
     * 清除jwt token
     */
    fun removeJwtToken() {
        this.removeJwtTokenInSharedPreference()

        this.removeJwtTokenInCache()
    }

    private fun removeJwtTokenInCache() {
        mACache!!.remove(CACHE_JWT_TOKEN_KEY)
        mACache!!.clear()
    }

    private fun removeJwtTokenInSharedPreference() {
        mEditor!!.remove(CACHE_JWT_TOKEN_KEY)
        mEditor!!.commit()
    }


    /**
     * 清除用户资料
     */
    fun removeUser() {
        this.removeUserInSharedPreference()

        this.removeUserInCache()
    }

    private fun removeUserInCache() {
        mACache!!.remove(CACHE_USER_KEY)
    }

    private fun removeUserInSharedPreference() {
        mEditor!!.remove(CACHE_USER_KEY)
        mEditor!!.commit()
    }

    private fun putLatestVersionCodeToCache(versionCode: Int) {
        mACache!!.put(CACHE_LATEST_VERSION_CODE_KEY, Integer.valueOf(versionCode), CACHE_SECONDS)
    }

    private fun putLatestVersionCodeToSharedPreference(versionCode: Int) {
        mEditor!!.putInt(CACHE_LATEST_VERSION_CODE_KEY, versionCode)
        mEditor!!.commit()
    }

    /**
     * valid that whether given version is futher
     * than latest version code in cache
     *
     * @return boolean
     */
    fun isNewVersionCode(versionCode: Int): Boolean {
        return versionCode > this.latestVersionCode
    }

    /**
     * 清除latest version code(清除app数据时使用)
     */
    fun removeLatestVersionCode() {
        this.removeLatestVersionCodeInSharedPreference()

        this.removeLatestVersionCodeInCache()
    }

    private fun removeLatestVersionCodeInCache() {
        mACache!!.remove(CACHE_LATEST_VERSION_CODE_KEY)
    }

    private fun removeLatestVersionCodeInSharedPreference() {
        mEditor!!.remove(CACHE_LATEST_VERSION_CODE_KEY)
        mEditor!!.commit()
    }

    /**********************************************账号密码 */
    /**
     * 存储用户账号密码
     *
     * @param phoneNum
     * @param password
     */
    fun setPhoneAndPassword(phoneNum: String, password: String) {
        this.putPhoneAndPasswordToCache(phoneNum, password)
        this.putPhoneAndPasswordToSharedPreference(phoneNum, password)
    }

    fun putPhoneAndPasswordToCache(phoneNum: String, password: String) {
        mACache!!.put(CACHE_PHONE_PASSWORD_KEY, phoneNum + "/" + password, CACHE_SECONDS)
    }

    fun putPhoneAndPasswordToSharedPreference(phoneNum: String, password: String) {
        mEditor!!.putString(CACHE_PHONE_PASSWORD_KEY, phoneNum + "/" + password)
        mEditor!!.commit()
    }

    /**
     * 清除账号密码
     */
    fun removePhoneAndPassword() {
        this.removePhoneAndPasswordInSharedPreference()
        this.removePhoneAndPasswordInCache()
    }

    private fun removePhoneAndPasswordInCache() {
        mACache!!.remove(CACHE_PHONE_PASSWORD_KEY)
    }

    private fun removePhoneAndPasswordInSharedPreference() {
        mEditor!!.remove(CACHE_PHONE_PASSWORD_KEY)
        mEditor!!.commit()
    }

    companion object {
        private val SHARED_PREFERENCE_NAME = "USER"
        private val CACHE_SECONDS = 60 * 60
        private val CACHE_JWT_TOKEN_KEY = "jwt_token"
        private val CACHE_LATEST_VERSION_CODE_KEY = "latest_version_code"
        private val CACHE_USER_KEY = "user"
        private val CACHE_PHONE_PASSWORD_KEY = "phone_password"

        lateinit var instance : UserSharedPreference
        private set



    }


}


