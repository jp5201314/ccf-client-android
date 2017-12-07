package cn.cnlinfo.ccf.entity

/**
 * Created by JP on 2017/10/30 0030.
 */

/*
class AccountInfo {
    @JSONField(name = "NickName")
    var nickName: String? = null
    @JSONField(name = "UCode")
    var acountId: String? = null
    @JSONField(name = "InLevel")
    var level: String? = null
    @JSONField(name = "InvitationCode")
    var invatationCode: String? = null
    @JSONField(name = "CCF")
    var ccf: String? = null
    @JSONField(name = "CarbonIntegral")
    var ccIntegral: String? = null
    @JSONField(name = "CircleTicket")
    var frozened: String? = null
    @JSONField(name = "CarbonNum")
    var toActivate: String? = null

}
*/

data class AccountInfo(
		val UCode: String, //1001
		val NickName: String, //1001
		val InvitationCode: String,
		val InLevel: String, //0
		val CCF: String, //799717.8
		val CarbonNum: String, //0
		val CircleTicket: String, //
		val CarbonIntegral: String //0
)