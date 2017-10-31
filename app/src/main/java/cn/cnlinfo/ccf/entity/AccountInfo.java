package cn.cnlinfo.ccf.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2017/10/30 0030.
 */

public class AccountInfo {
    @JSONField(name = "NickName")
    private String nickName;
    @JSONField(name = "UCode")
    private String acountId;
    @JSONField(name = "InLevel")
    private String level;
    @JSONField(name = "InvitationCode")
    private String invatationCode;
    @JSONField(name = "CCF")
    private String ccf;
    @JSONField(name = "CarbonIntegral")
    private String ccIntegral;
    @JSONField(name = "CircleTicket")
    private String frozened;
    @JSONField(name = "CarbonNum")
    private String toActivate;

    public AccountInfo() {
    }

    public AccountInfo(String nickName, String acountId, String level, String invatationCode, String ccf, String ccIntegral, String frozened, String toActivate) {
        this.nickName = nickName;
        this.acountId = acountId;
        this.level = level;
        this.invatationCode = invatationCode;
        this.ccf = ccf;
        this.ccIntegral = ccIntegral;
        this.frozened = frozened;
        this.toActivate = toActivate;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAcountId() {
        return acountId;
    }

    public void setAcountId(String acountId) {
        this.acountId = acountId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getInvatationCode() {
        return invatationCode;
    }

    public void setInvatationCode(String invatationCode) {
        this.invatationCode = invatationCode;
    }

    public String getCcf() {
        return ccf;
    }

    public void setCcf(String ccf) {
        this.ccf = ccf;
    }

    public String getCcIntegral() {
        return ccIntegral;
    }

    public void setCcIntegral(String ccIntegral) {
        this.ccIntegral = ccIntegral;
    }

    public String getFrozened() {
        return frozened;
    }

    public void setFrozened(String frozened) {
        this.frozened = frozened;
    }

    public String getToActivate() {
        return toActivate;
    }

    public void setToActivate(String toActivate) {
        this.toActivate = toActivate;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "nickName='" + nickName + '\'' +
                ", acountId='" + acountId + '\'' +
                ", level='" + level + '\'' +
                ", invatationCode='" + invatationCode + '\'' +
                ", ccf='" + ccf + '\'' +
                ", ccIntegral='" + ccIntegral + '\'' +
                ", frozened='" + frozened + '\'' +
                ", toActivate='" + toActivate + '\'' +
                '}';
    }
}
