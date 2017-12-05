package cn.cnlinfo.ccf.entity

/**
 * Created by Administrator on 2017/12/5 0005.
 */

data class User(
		val ID: Int, //4
		val UCode: String, //1001
		val NickName: String, //1001
		val Status: Int, //2
		val Mobile: String,
		val ParentID: Int, //0
		val DirectID: Int, //0
		val InvitationCode: String,
		val JID: Int, //5
		val MID: Int, //6
		val SID: Int, //7
		val HID: Int, //8
		val TID: Int, //9
		val IsFull: Boolean, //true
		val Utype: Int, //0
		val Pwd: String, //E10ADC3949BA59ABBE56E057F20F883E
		val Pwd2: String, //F379EAF3C831B04DE153469D1BEC345E
		val LogonTimes: Int, //174
		val RegisterIP: String, //1.1.1.1
		val RegDate: String, ///Date(1507797281880+0800)/
		val InLevel: Int, //0
		val LastLogonIP: String, //127.0.0.1
		val LastLogonDate: String, ///Date(1512444509187+0800)/
		val IsOnLine: Int, //1
		val CCF: Double, //799717.8
		val Circle: Int, //2
		val CarbonNum: Int, //0
		val CircleTicket: Int, //997102
		val CircleTicketScore: Int, //100
		val ConsumeIntegral: Int, //270
		val CarbonIntegral: Int, //0
		val RegisterIntegral: Int, //296535
		val ReleaseConsume: Int, //0
		val ReleaseCarbon: Int, //0
		val TotalStep: Int, //0
		val TodayStep: Int, //5000
		val E_max: Int, //0
		val Praise: Int, //0
		val BusinessLev: Int, //0
		val TotalMealWeight: Int, //36
		val ServantID: Int, //0
		val EnableAutoToCCF: Boolean, //true
		val ProductScore: Int //5400
)