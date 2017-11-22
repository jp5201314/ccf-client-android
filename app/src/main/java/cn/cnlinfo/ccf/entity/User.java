package cn.cnlinfo.ccf.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
/**
 * Created by Administrator on 2017/10/23 0023.
 */

/**
 *  "ID": 4,
 "UCode": "1001",
 "NickName": "1001",
 "Status": 2,
 "ParentID": 0,
 "DirectID": 0,
 "InvitationCode":""
 "JID": 0,
 "MID": 0,
 "SID": 0,
 "HID": 0,
 "TID": 0,
 "IsFull": false,
 "Utype": 0,
 "Pwd": "",
 "Mobile": "",
 "LogonTimes": 0,
 "RegisterIP": "0.0.0.0",
 "RegDate": "/Date(1508751205404+0800)/",
 "InLevel": 0,
 "LastLogonIP": "0.0.0.0",
 "LastLogonDate": "/Date(1508751205404+0800)/",
 "IsOnLine": 0,
 "CCF": 0,
 "Circle": 0,
 "CarbonNum": 0,
 "CircleTicket": 0,
 "ConsumeIntegral": 0,
 "CarbonIntegral": 0,
 "ReleaseConsume": 0,
 "ReleaseCarbon": 0,
 "TotalStep": 0,
 "TodayStep": 0,
 "E_max": 0
 "Praise": 0,
 "BusinessLev": 0,
 "TotalMealWeight": 0,
 "ServantID": 0,
 "EnableAutoToCCF": false
 */
public class User implements Serializable {

    @JSONField(name = "ID")
    private int id;


    @JSONField(name = "UCode")
    private String username;


    @JSONField(name = "NickName")
    private String  nickName;


    @JSONField(name = "Status")
    private int status;


    @JSONField(name = "ParentID")
    private int parentId;


    @JSONField(name = "DirectID")
    private int directId;

    @JSONField(name = "InvitationCode")
    private String invitationCode;

    @JSONField(name = "JID")
    private int jId;

    @JSONField(name = "MID")
    private int mId;

    @JSONField(name = "SID")
    private int sId;

    @JSONField(name = "HID")
    private int hId;

    @JSONField(name = "TID")
    private int tId;

    @JSONField(name = "IsFull")
    private boolean isFull;

    @JSONField(name = "Utype")
    private int uType;

    @JSONField(name = "Pwd")
    private String pwd;

    @JSONField(name = "Mobile")
    private String mobile;

    @JSONField(name = "LogonTimes")
    private long loginTimes;

    @JSONField(name = "RegisterIP")
    private String registerIp;

    @JSONField(name = "RegDate")
    private String date;

    @JSONField(name = "InLevel")
    private int inLevel;

    @JSONField(name = "LastLogonIP")
    private String lastLogonIp;

    @JSONField(name = "LastLogonDate")
    private String lastLogonDate;

    @JSONField(name = "IsOnLine")
    private int isOnline;

    @JSONField(name = "CCF")
    private int ccf;

    @JSONField(name = "Circle")
    private int circle;

    @JSONField(name = "CarbonNum")
    private int carbonNum;

    @JSONField(name = "ConsumeIntegral")
    private int consumeIntegeral;

    @JSONField(name = "CarbonIntegral")
    private int carbonIntegral;

    @JSONField(name = "ReleaseConsume")
    private int releaseConsum;

    @JSONField(name = "ReleaseCarbon")
    private int releaseCarbon;

    @JSONField(name = "TotalStep")
    private int totalStep;

    @JSONField(name = "CircleTicket")
    private int circleTicket;

    @JSONField(name = "E_max")
    private int eMax;

    @JSONField(name = "Praise")
    private double praise;
    @JSONField(name = "BusinessLev")
    private double businessLev;
    @JSONField(name = "TotalMealWeight")
    private double  totalMealWeight;
    @JSONField(name = "ServantID")
    private double servantId;
    @JSONField(name = "EnableAutoToCCF")
    private boolean enableAutoToCCF;
    @JSONField(name = "ProductScore")
    private int productScore;

    public User(int id, String username, String nickName, int status, int parentId, int directId, String invitationCode, int jId, int mId, int sId, int hId, int tId, boolean isFull, int uType, String pwd, String mobile, long loginTimes, String registerIp, String date, int inLevel, String lastLogonIp, String lastLogonDate, int isOnline, int ccf, int circle, int carbonNum, int consumeIntegeral, int carbonIntegral, int releaseConsum, int releaseCarbon, int totalStep, int circleTicket, int eMax, double praise, double businessLev, double totalMealWeight, double servantId, boolean enableAutoToCCF, int productScore) {
        this.id = id;
        this.username = username;
        this.nickName = nickName;
        this.status = status;
        this.parentId = parentId;
        this.directId = directId;
        this.invitationCode = invitationCode;
        this.jId = jId;
        this.mId = mId;
        this.sId = sId;
        this.hId = hId;
        this.tId = tId;
        this.isFull = isFull;
        this.uType = uType;
        this.pwd = pwd;
        this.mobile = mobile;
        this.loginTimes = loginTimes;
        this.registerIp = registerIp;
        this.date = date;
        this.inLevel = inLevel;
        this.lastLogonIp = lastLogonIp;
        this.lastLogonDate = lastLogonDate;
        this.isOnline = isOnline;
        this.ccf = ccf;
        this.circle = circle;
        this.carbonNum = carbonNum;
        this.consumeIntegeral = consumeIntegeral;
        this.carbonIntegral = carbonIntegral;
        this.releaseConsum = releaseConsum;
        this.releaseCarbon = releaseCarbon;
        this.totalStep = totalStep;
        this.circleTicket = circleTicket;
        this.eMax = eMax;
        this.praise = praise;
        this.businessLev = businessLev;
        this.totalMealWeight = totalMealWeight;
        this.servantId = servantId;
        this.enableAutoToCCF = enableAutoToCCF;
        this.productScore = productScore;
    }

    public User() {
    }

    public int getProductScore() {
        return productScore;
    }

    public void setProductScore(int productScore) {
        this.productScore = productScore;
    }

    @Override
    public String toString() {
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
                ", enableAutoToCCF=" + enableAutoToCCF +
                ", productScore=" + productScore +
                '}';
    }

    public double getPraise() {
        return praise;
    }

    public void setPraise(double praise) {
        this.praise = praise;
    }

    public double getBusinessLev() {
        return businessLev;
    }

    public void setBusinessLev(double businessLev) {
        this.businessLev = businessLev;
    }

    public double getTotalMealWeight() {
        return totalMealWeight;
    }

    public void setTotalMealWeight(double totalMealWeight) {
        this.totalMealWeight = totalMealWeight;
    }

    public double getServantId() {
        return servantId;
    }

    public void setServantId(double servantId) {
        this.servantId = servantId;
    }

    public boolean isEnableAutoToCCF() {
        return enableAutoToCCF;
    }

    public void setEnableAutoToCCF(boolean enableAutoToCCF) {
        this.enableAutoToCCF = enableAutoToCCF;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getDirectId() {
        return directId;
    }

    public void setDirectId(int directId) {
        this.directId = directId;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public int getjId() {
        return jId;
    }

    public void setjId(int jId) {
        this.jId = jId;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int gethId() {
        return hId;
    }

    public void sethId(int hId) {
        this.hId = hId;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public int getuType() {
        return uType;
    }

    public void setuType(int uType) {
        this.uType = uType;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public long getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(long loginTimes) {
        this.loginTimes = loginTimes;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getInLevel() {
        return inLevel;
    }

    public void setInLevel(int inLevel) {
        this.inLevel = inLevel;
    }

    public String getLastLogonIp() {
        return lastLogonIp;
    }

    public void setLastLogonIp(String lastLogonIp) {
        this.lastLogonIp = lastLogonIp;
    }

    public String getLastLogonDate() {
        return lastLogonDate;
    }

    public void setLastLogonDate(String lastLogonDate) {
        this.lastLogonDate = lastLogonDate;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public int getCcf() {
        return ccf;
    }

    public void setCcf(int ccf) {
        this.ccf = ccf;
    }

    public int getCircle() {
        return circle;
    }

    public void setCircle(int circle) {
        this.circle = circle;
    }

    public int getCarbonNum() {
        return carbonNum;
    }

    public void setCarbonNum(int carbonNum) {
        this.carbonNum = carbonNum;
    }

    public int getConsumeIntegeral() {
        return consumeIntegeral;
    }

    public void setConsumeIntegeral(int consumeIntegeral) {
        this.consumeIntegeral = consumeIntegeral;
    }

    public int getCarbonIntegral() {
        return carbonIntegral;
    }

    public void setCarbonIntegral(int carbonIntegral) {
        this.carbonIntegral = carbonIntegral;
    }

    public int getReleaseConsum() {
        return releaseConsum;
    }

    public void setReleaseConsum(int releaseConsum) {
        this.releaseConsum = releaseConsum;
    }

    public int getReleaseCarbon() {
        return releaseCarbon;
    }

    public void setReleaseCarbon(int releaseCarbon) {
        this.releaseCarbon = releaseCarbon;
    }

    public int getTotalStep() {
        return totalStep;
    }

    public void setTotalStep(int totalStep) {
        this.totalStep = totalStep;
    }

    public int getCircleTicket() {
        return circleTicket;
    }

    public void setCircleTicket(int circleTicket) {
        this.circleTicket = circleTicket;
    }

    public int geteMax() {
        return eMax;
    }

    public void seteMax(int eMax) {
        this.eMax = eMax;
    }

}
