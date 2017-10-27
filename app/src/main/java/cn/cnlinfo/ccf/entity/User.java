package cn.cnlinfo.ccf.entity;
import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;
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
 */
public class User implements Parcelable {

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

    public User() {
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
                '}';
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

    protected User(Parcel in) {
        id = in.readInt();
        username = in.readString();
        nickName = in.readString();
        status = in.readInt();
        parentId = in.readInt();
        directId = in.readInt();
        invitationCode = in.readString();
        jId = in.readInt();
        mId = in.readInt();
        sId = in.readInt();
        hId = in.readInt();
        tId = in.readInt();
        isFull = in.readByte() != 0;
        uType = in.readInt();
        pwd = in.readString();
        mobile = in.readString();
        loginTimes = in.readLong();
        registerIp = in.readString();
        date = in.readString();
        inLevel = in.readInt();
        lastLogonIp = in.readString();
        lastLogonDate = in.readString();
        isOnline = in.readInt();
        ccf = in.readInt();
        circle = in.readInt();
        carbonNum = in.readInt();
        consumeIntegeral = in.readInt();
        carbonIntegral = in.readInt();
        releaseConsum = in.readInt();
        releaseCarbon = in.readInt();
        totalStep = in.readInt();
        circleTicket = in.readInt();
        eMax = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(username);
        dest.writeString(nickName);
        dest.writeInt(status);
        dest.writeInt(parentId);
        dest.writeInt(directId);
        dest.writeString(invitationCode);
        dest.writeInt(jId);
        dest.writeInt(mId);
        dest.writeInt(sId);
        dest.writeInt(hId);
        dest.writeInt(tId);
        dest.writeByte((byte) (isFull ? 1 : 0));
        dest.writeInt(uType);
        dest.writeString(pwd);
        dest.writeString(mobile);
        dest.writeLong(loginTimes);
        dest.writeString(registerIp);
        dest.writeString(date);
        dest.writeInt(inLevel);
        dest.writeString(lastLogonIp);
        dest.writeString(lastLogonDate);
        dest.writeInt(isOnline);
        dest.writeInt(ccf);
        dest.writeInt(circle);
        dest.writeInt(carbonNum);
        dest.writeInt(consumeIntegeral);
        dest.writeInt(carbonIntegral);
        dest.writeInt(releaseConsum);
        dest.writeInt(releaseCarbon);
        dest.writeInt(totalStep);
        dest.writeInt(circleTicket);
        dest.writeInt(eMax);
    }
}
