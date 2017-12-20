package cn.cnlinfo.ccf.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2017/10/30 0030.
 */

public class PlatformInfo {
    /**
     "ID": 1,
     "TotalCCF": 120000000,
     "TotalPack": 3000,
     "CurrentPrice": 10.278,
     "InitActiveCCF": 960000,
     "ActiveCCF": 958866.789968,
     "TotalInertiaCCF": 119040110,
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
     "Haschange": 11,
     "SysRunDate": "/Date(1510305888380+0800)/",
     "RuningDays": 613,
     "CurTrade": 2000
     */
    @JSONField(name = "ID")
    private int ID;
    @JSONField(name = "TotalPack")
    private int TotalPack;
    @JSONField(name = "CurrentPrice")
    private double CurrentPrice;
    @JSONField(name = "InitActiveCCF")
    private String InitActiveCCF;
    @JSONField(name = "TotalAccount")
    private String TotalAccount;
    @JSONField(name = "Current_u")
    private String Current_u;
    @JSONField(name = "InertiaConsumeScore")
    private String InertiaConsumeScore;
    @JSONField(name = "ProvinceProxy")
    private String ProvinceProxy;
    @JSONField(name = "CityProxy")
    private String CityProxy;
    @JSONField(name = "CountyProxy")
    private String CountyProxy;
    @JSONField(name = "PackTime")
    private String PackTime;
    @JSONField(name = "Haschange")
    private String Haschange;
    @JSONField(name = "SysRunDate")
    private String SysRunDate;
    @JSONField(name = "RuningDays")
    private int RuningDays;
    @JSONField(name = "CurTrade")
    private int CurTrade;
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

    @Override
    public String toString() {
        return "PlatformInfo{" +
                "ID=" + ID +
                ", TotalPack=" + TotalPack +
                ", CurrentPrice=" + CurrentPrice +
                ", InitActiveCCF='" + InitActiveCCF + '\'' +
                ", TotalAccount='" + TotalAccount + '\'' +
                ", Current_u='" + Current_u + '\'' +
                ", InertiaConsumeScore='" + InertiaConsumeScore + '\'' +
                ", ProvinceProxy='" + ProvinceProxy + '\'' +
                ", CityProxy='" + CityProxy + '\'' +
                ", CountyProxy='" + CountyProxy + '\'' +
                ", PackTime='" + PackTime + '\'' +
                ", Haschange='" + Haschange + '\'' +
                ", SysRunDate='" + SysRunDate + '\'' +
                ", RuningDays=" + RuningDays +
                ", CurTrade=" + CurTrade +
                ", total='" + total + '\'' +
                ", activated='" + activated + '\'' +
                ", price='" + price + '\'' +
                ", toActivateCcf='" + toActivateCcf + '\'' +
                ", ccIntegral='" + ccIntegral + '\'' +
                ", cycleIntegral='" + cycleIntegral + '\'' +
                ", cycleStock='" + cycleStock + '\'' +
                ", bonusPoints='" + bonusPoints + '\'' +
                '}';
    }

    public PlatformInfo(int ID, int totalPack, double currentPrice, String initActiveCCF, String totalAccount, String current_u, String inertiaConsumeScore, String provinceProxy, String cityProxy, String countyProxy, String packTime, String haschange, String sysRunDate, int runingDays, int curTrade, String total, String activated, String price, String toActivateCcf, String ccIntegral, String cycleIntegral, String cycleStock, String bonusPoints) {
        this.ID = ID;
        TotalPack = totalPack;
        CurrentPrice = currentPrice;
        InitActiveCCF = initActiveCCF;
        TotalAccount = totalAccount;
        Current_u = current_u;
        InertiaConsumeScore = inertiaConsumeScore;
        ProvinceProxy = provinceProxy;
        CityProxy = cityProxy;
        CountyProxy = countyProxy;
        PackTime = packTime;
        Haschange = haschange;
        SysRunDate = sysRunDate;
        RuningDays = runingDays;
        CurTrade = curTrade;
        this.total = total;
        this.activated = activated;
        this.price = price;
        this.toActivateCcf = toActivateCcf;
        this.ccIntegral = ccIntegral;
        this.cycleIntegral = cycleIntegral;
        this.cycleStock = cycleStock;
        this.bonusPoints = bonusPoints;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTotalPack() {
        return TotalPack;
    }

    public void setTotalPack(int totalPack) {
        TotalPack = totalPack;
    }

    public double getCurrentPrice() {
        return CurrentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        CurrentPrice = currentPrice;
    }

    public String getInitActiveCCF() {
        return InitActiveCCF;
    }

    public void setInitActiveCCF(String initActiveCCF) {
        InitActiveCCF = initActiveCCF;
    }

    public String getTotalAccount() {
        return TotalAccount;
    }

    public void setTotalAccount(String totalAccount) {
        TotalAccount = totalAccount;
    }

    public String getCurrent_u() {
        return Current_u;
    }

    public void setCurrent_u(String current_u) {
        Current_u = current_u;
    }

    public String getInertiaConsumeScore() {
        return InertiaConsumeScore;
    }

    public void setInertiaConsumeScore(String inertiaConsumeScore) {
        InertiaConsumeScore = inertiaConsumeScore;
    }

    public String getProvinceProxy() {
        return ProvinceProxy;
    }

    public void setProvinceProxy(String provinceProxy) {
        ProvinceProxy = provinceProxy;
    }

    public String getCityProxy() {
        return CityProxy;
    }

    public void setCityProxy(String cityProxy) {
        CityProxy = cityProxy;
    }

    public String getCountyProxy() {
        return CountyProxy;
    }

    public void setCountyProxy(String countyProxy) {
        CountyProxy = countyProxy;
    }

    public String getPackTime() {
        return PackTime;
    }

    public void setPackTime(String packTime) {
        PackTime = packTime;
    }

    public String getHaschange() {
        return Haschange;
    }

    public void setHaschange(String haschange) {
        Haschange = haschange;
    }

    public String getSysRunDate() {
        return SysRunDate;
    }

    public void setSysRunDate(String sysRunDate) {
        SysRunDate = sysRunDate;
    }

    public int getRuningDays() {
        return RuningDays;
    }

    public void setRuningDays(int runingDays) {
        RuningDays = runingDays;
    }

    public int getCurTrade() {
        return CurTrade;
    }

    public void setCurTrade(int curTrade) {
        CurTrade = curTrade;
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
}
