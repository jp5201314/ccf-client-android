package cn.cnlinfo.ccf.entity;

/**
 * Created by Administrator on 2018/1/16 0016.
 */

public class OrderListItem {
    /**
     * TranType : 挂卖
     * SellerID : 1001
     * PurchaserID : 1001
     * CCFNum : 8.00
     * PayMoney : 0.820
     * AuctionRoomID : 36
     * CreateTime : 2018/1/5 16:08:25
     * Status : 打款中
     * BuyerScreenshot :
     * ID : 7
     */

    private String TranType;
    private String SellerID;
    private String PurchaserID;
    private String CCFNum;
    private String PayMoney;
    private String AuctionRoomID;
    private String CreateTime;
    private String Status;
    private String BuyerScreenshot;
    private String ID;

    public String getTranType() {
        return TranType;
    }

    public void setTranType(String TranType) {
        this.TranType = TranType;
    }

    public String getSellerID() {
        return SellerID;
    }

    public void setSellerID(String SellerID) {
        this.SellerID = SellerID;
    }

    public String getPurchaserID() {
        return PurchaserID;
    }

    public void setPurchaserID(String PurchaserID) {
        this.PurchaserID = PurchaserID;
    }

    public String getCCFNum() {
        return CCFNum;
    }

    public void setCCFNum(String CCFNum) {
        this.CCFNum = CCFNum;
    }

    public String getPayMoney() {
        return PayMoney;
    }

    public void setPayMoney(String PayMoney) {
        this.PayMoney = PayMoney;
    }

    public String getAuctionRoomID() {
        return AuctionRoomID;
    }

    public void setAuctionRoomID(String AuctionRoomID) {
        this.AuctionRoomID = AuctionRoomID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getBuyerScreenshot() {
        return BuyerScreenshot;
    }

    public void setBuyerScreenshot(String BuyerScreenshot) {
        this.BuyerScreenshot = BuyerScreenshot;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
