package cn.cnlinfo.ccf.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
/**
 * Created by Administrator on 2017/10/23 0023.
 */

/**
 "ID": 4,
 "UCode": "1001",
 "NickName": "1001",
 "ParentID": 0,
 "DirectID": 0,
 "InvitationCode": "",
 "JID": 0,
 "MID": 0,
 "SID": 0,
 "HID": 0,
 "TID": 0,
 "IsFull": false,
 "Utype": 0,
 "Pwd": "",
 "Pwd2": "",
 "Mobile": "           ",
 "LogonTimes": 0,
 "RegisterIP": "",
 "RegDate": "/Date(1511325569242+0800)/",
 "Status": 2,
 "InLevel": 0,
 "LastLogonIP": "",
 "LastLogonDate": "/Date(1511325569242+0800)/",
 "IsOnLine": 0,
 "CCF": 0,
 "Circle": 0,
 "CarbonNum": 0,
 "CircleTicket": 0,
 "CircleTicketScore": 0,
 "ConsumeIntegral": 0,
 "CarbonIntegral": 0,
 "RegisterIntegral": 0,
 "ReleaseConsume": 0,
 "ReleaseCarbon": 0,
 "TotalStep": 0,
 "TodayStep": 0,
 "E_max": 0,
 "Praise": 0,
 "BusinessLev": 0,
 "TotalMealWeight": 0,
 "ServantID": 0,
 "EnableAutoToCCF": false,
 "ProductScore": 0
 */
public class User1 implements Serializable{

    private int ID;
    private String UCode;
    private String  NickName;

    public User1() {
    }

    public User1(int ID, String UCode, String nickName) {
        this.ID = ID;
        this.UCode = UCode;
        NickName = nickName;
    }


    @Override
    public String toString() {
        return "User1{" +
                "ID=" + ID +
                ", UCode='" + UCode + '\'' +
                ", NickName='" + NickName + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUCode() {
        return UCode;
    }

    public void setUCode(String UCode) {
        this.UCode = UCode;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }
}
