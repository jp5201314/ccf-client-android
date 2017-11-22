package cn.cnlinfo.ccf.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2017/10/30 0030.
 */

public class PlatformInfo {
    /**
     *  "platforminfo": {
     "ID": 1,
     "TotalCCF": 120000000,
     "TotalPack": 3000,
     "CurrentPrice": 10.278,
     "InitActiveCCF": 960000,
     "ActiveCCF": 958843.189968,
     "TotalInertiaCCF": 119040120,
     "TotalAccount": 0,
     "CCScore": 0,
     "Current_u": 0.00185,
     "ActiveConsumeScore": 0,
     "InertiaConsumeScore": 0,
     "CircleTicket": 0,
     "InertiaCCScore": 0,
     "ProvinceProxy": 0,
     "CityProxy": 0,
     "CountyProxy": 0,
     "PackTime": 1024,
     "Haschange": 12,
     "SysRunDate": "/Date(1510305888380+0800)/",
     "RuningDays": 613,
     "CurTrade": 82990000
     }

     private int m_iD;								//
     private decimal m_totalCCF;						//碳控因子总量
     private int m_totalPack;						//系统当前总循环包数量
     private decimal m_currentPrice;					//当前价格(美刀)
     private decimal m_initActiveCCF;				//初始激活总量
     private decimal m_activeCCF;					//已激活碳控因子总量
     private decimal m_totalInertiaCCF;				//矿池(待激活碳控因子总量)
     private int m_totalAccount;						//当前账户数
     private decimal m_cCScore;						//碳控积分
     private decimal m_current_u;					//当前难度系数
     private decimal m_activeConsumeScore;			//可用消费积分
     private decimal m_inertiaConsumeScore;			//待释放消费积分
     private int m_circleTicket;						//循环卷
     private decimal m_inertiaCCScore;				//待释放循环卷积分
     private int m_provinceProxy;					//省代理数量
     private int m_cityProxy;						//市代理数量
     private int m_countyProxy;						//县代理数量
     private int m_packTime;							//包生成时间
     private int m_haschange;						//已兑换循环包数量
     private DateTime m_sysRunDate;					//系统开始运行日期
     private int m_runingDays;						//运行工作日
     private int m_curTrade;							//历史CCF成交量之和

     */
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

    public PlatformInfo() {
    }

    public PlatformInfo(String total, String activated, String price, String toActivateCcf, String ccIntegral, String cycleIntegral, String cycleStock, String bonusPoints) {
        this.total = total;
        this.activated = activated;
        this.price = price;
        this.toActivateCcf = toActivateCcf;
        this.ccIntegral = ccIntegral;
        this.cycleIntegral = cycleIntegral;
        this.cycleStock = cycleStock;
        this.bonusPoints = bonusPoints;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getActivated() {
        return activated;
    }

    public void setActivated(String activated) {
        this.activated = activated;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getToActivateCcf() {
        return toActivateCcf;
    }

    public void setToActivateCcf(String toActivateCcf) {
        this.toActivateCcf = toActivateCcf;
    }

    public String getCcIntegral() {
        return ccIntegral;
    }

    public void setCcIntegral(String ccIntegral) {
        this.ccIntegral = ccIntegral;
    }

    public String getCycleIntegral() {
        return cycleIntegral;
    }

    public void setCycleIntegral(String cycleIntegral) {
        this.cycleIntegral = cycleIntegral;
    }

    public String getCycleStock() {
        return cycleStock;
    }

    public void setCycleStock(String cycleStock) {
        this.cycleStock = cycleStock;
    }

    public String getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(String bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    @Override
    public String toString() {
        return "PlatformInfo{" +
                "total='" + total + '\'' +
                ", activated='" + activated + '\'' +
                ", price='" + price + '\'' +
                ", toActivateCcf='" + toActivateCcf + '\'' +
                ", ccIntegral='" + ccIntegral + '\'' +
                ", cycleIntegral='" + cycleIntegral + '\'' +
                ", cycleStock='" + cycleStock + '\'' +
                ", bonusPoints='" + bonusPoints + '\'' +
                '}';
    }
}
