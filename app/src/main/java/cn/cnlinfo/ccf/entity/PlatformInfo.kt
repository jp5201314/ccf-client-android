package cn.cnlinfo.ccf.entity

/**
 * Created by Administrator on 2017/10/30 0030.
 */
/*
public class PlatformInfo {

    @JSONField(name = "TotalCCF")
    private String total;
    @JSONField(name = "ActiveCCF")
    private String activated;
    @JSONField(name = "CurrentPrice")
    private String price;
    @JSONField(name = "TotalInertiaCCF")
    private String toActivateCcf;
    @JSONField(name = "CCScore")
    private String ccIntegral;
    @JSONField(name = "InertiaCCScore")
    private String cycleIntegral;
    @JSONField(name = "CircleTicket")
    private String cycleStock;
    @JSONField(name = "ActiveConsumeScore")
    private String bonusPoints;

}*/


data class PlatformInfo(
		val ID: Int, //1
		val TotalCCF: Int, //120000000
		val TotalPack: Int, //3000
		val CurrentPrice: Double, //10.278
		val InitActiveCCF: Int, //960000
		val ActiveCCF: Double, //958866.789968
		val TotalInertiaCCF: Int, //119040110
		val TotalAccount: Int, //0
		val CCScore: Int, //0
		val Current_u: Double, //0.00185
		val ActiveConsumeScore: Int, //0
		val InertiaConsumeScore: Int, //0
		val CircleTicket: Int, //0
		val InertiaCCScore: Int, //0
		val ProvinceProxy: Int, //0
		val CityProxy: Int, //0
		val CountyProxy: Int, //0
		val PackTime: Int, //1024
		val Haschange: Int, //11
		val SysRunDate: String, ///Date(1510305888380+0800)/
		val RuningDays: Int, //613
		val CurTrade: Int //2000
)
