package cn.cnlinfo.ccf.entity

import com.alibaba.fastjson.annotation.JSONField

import java.io.Serializable


/**
 * Created by JP on 2017/10/23 0023.
 */

/**
 * "ID": 4,
 * "UCode": "1001",
 * "NickName": "1001",
 * "ParentID": 0,
 * "DirectID": 0,
 * "InvitationCode": "",
 * "JID": 0,
 * "MID": 0,
 * "SID": 0,
 * "HID": 0,
 * "TID": 0,
 * "IsFull": false,
 * "Utype": 0,
 * "Pwd": "",
 * "Pwd2": "",
 * "Mobile": "           ",
 * "LogonTimes": 0,
 * "RegisterIP": "",
 * "RegDate": "/Date(1511325569242+0800)/",
 * "Status": 2,
 * "InLevel": 0,
 * "LastLogonIP": "",
 * "LastLogonDate": "/Date(1511325569242+0800)/",
 * "IsOnLine": 0,
 * "CCF": 0,
 * "Circle": 0,
 * "CarbonNum": 0,
 * "CircleTicket": 0,
 * "CircleTicketScore": 0,
 * "ConsumeIntegral": 0,
 * "CarbonIntegral": 0,
 * "RegisterIntegral": 0,
 * "ReleaseConsume": 0,
 * "ReleaseCarbon": 0,
 * "TotalStep": 0,
 * "TodayStep": 0,
 * "E_max": 0,
 * "Praise": 0,
 * "BusinessLev": 0,
 * "TotalMealWeight": 0,
 * "ServantID": 0,
 * "EnableAutoToCCF": false,
 * "ProductScore": 0
 */
class User : Serializable {

    @JSONField(name = "ID")
    var id: Int = 0


    @JSONField(name = "UCode")
    var username: String? = null


    @JSONField(name = "NickName")
    var nickName: String? = null


    @JSONField(name = "Status")
    var status: Int = 0


    @JSONField(name = "ParentID")
    var parentId: Int = 0


    @JSONField(name = "DirectID")
    var directId: Int = 0

    @JSONField(name = "InvitationCode")
    var invitationCode: String? = null

    @JSONField(name = "JID")
    private var jId: Int = 0

    @JSONField(name = "MID")
    private var mId: Int = 0

    @JSONField(name = "SID")
    private var sId: Int = 0

    @JSONField(name = "HID")
    private var hId: Int = 0

    @JSONField(name = "TID")
    private var tId: Int = 0

    @JSONField(name = "IsFull")
    var isFull: Boolean = false

    @JSONField(name = "Utype")
    private var uType: Int = 0

    @JSONField(name = "Pwd")
    var pwd: String? = null

    @JSONField(name = "Mobile")
    var mobile: String? = null

    @JSONField(name = "LogonTimes")
    var loginTimes: Long = 0

    @JSONField(name = "RegisterIP")
    var registerIp: String? = null

    @JSONField(name = "RegDate")
    var date: String? = null

    @JSONField(name = "InLevel")
    var inLevel: Int = 0

    @JSONField(name = "LastLogonIP")
    var lastLogonIp: String? = null

    @JSONField(name = "LastLogonDate")
    var lastLogonDate: String? = null

    @JSONField(name = "IsOnLine")
    var isOnline: Int = 0

    @JSONField(name = "CCF")
    var ccf: Int = 0

    @JSONField(name = "Circle")
    var circle: Int = 0

    @JSONField(name = "CarbonNum")
    var carbonNum: Int = 0

    @JSONField(name = "ConsumeIntegral")
    var consumeIntegeral: Int = 0
    @JSONField(name = "RegisterIntegral")
    var registerIntegeral: Int = 0

    @JSONField(name = "CarbonIntegral")
    var carbonIntegral: Int = 0

    @JSONField(name = "ReleaseConsume")
    var releaseConsum: Int = 0

    @JSONField(name = "ReleaseCarbon")
    var releaseCarbon: Int = 0

    @JSONField(name = "TotalStep")
    var totalStep: Int = 0

    @JSONField(name = "CircleTicket")
    var circleTicket: Int = 0

    @JSONField(name = "E_max")
    private var eMax: Int = 0

    @JSONField(name = "Praise")
    var praise: Double = 0.toDouble()
    @JSONField(name = "BusinessLev")
    var businessLev: Double = 0.toDouble()
    @JSONField(name = "TotalMealWeight")
    var totalMealWeight: Double = 0.toDouble()
    @JSONField(name = "ServantID")
    var servantId: Double = 0.toDouble()
    @JSONField(name = "EnableAutoToCCF")
    var isEnableAutoToCCF: Boolean = false
    @JSONField(name = "ProductScore")
    var productScore: Int = 0

    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickName='" + nickName + '\'' +
                ", status=" + status +
                ", parentId=" + parentId +
                ", directId=" + directId +
                ", invitationCode='" + invitationCode + '\'' +
                ", jId=" + jId +
                ", mId=" + mId +
                ", sId=" + sId +
                ", hId=" + hId +
                ", tId=" + tId +
                ", isFull=" + isFull +
                ", uType=" + uType +
                ", pwd='" + pwd + '\'' +
                ", mobile='" + mobile + '\'' +
                ", loginTimes=" + loginTimes +
                ", registerIp='" + registerIp + '\'' +
                ", date='" + date + '\'' +
                ", inLevel=" + inLevel +
                ", lastLogonIp='" + lastLogonIp + '\'' +
                ", lastLogonDate='" + lastLogonDate + '\'' +
                ", isOnline=" + isOnline +
                ", ccf=" + ccf +
                ", circle=" + circle +
                ", carbonNum=" + carbonNum +
                ", consumeIntegeral=" + consumeIntegeral +
                ", registerIntegeral=" + registerIntegeral +
                ", carbonIntegral=" + carbonIntegral +
                ", releaseConsum=" + releaseConsum +
                ", releaseCarbon=" + releaseCarbon +
                ", totalStep=" + totalStep +
                ", circleTicket=" + circleTicket +
                ", eMax=" + eMax +
                ", praise=" + praise +
                ", businessLev=" + businessLev +
                ", totalMealWeight=" + totalMealWeight +
                ", servantId=" + servantId +
                ", enableAutoToCCF=" + isEnableAutoToCCF +
                ", productScore=" + productScore +
                '}'
    }

    fun getjId(): Int {
        return jId
    }

    fun setjId(jId: Int) {
        this.jId = jId
    }

    fun getmId(): Int {
        return mId
    }

    fun setmId(mId: Int) {
        this.mId = mId
    }

    fun getsId(): Int {
        return sId
    }

    fun setsId(sId: Int) {
        this.sId = sId
    }

    fun gethId(): Int {
        return hId
    }

    fun sethId(hId: Int) {
        this.hId = hId
    }

    fun gettId(): Int {
        return tId
    }

    fun settId(tId: Int) {
        this.tId = tId
    }

    fun getuType(): Int {
        return uType
    }

    fun setuType(uType: Int) {
        this.uType = uType
    }

    fun geteMax(): Int {
        return eMax
    }

    fun seteMax(eMax: Int) {
        this.eMax = eMax
    }

}
