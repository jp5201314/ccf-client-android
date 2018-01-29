package cn.cnlinfo.ccf.event;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class CancelHangBuyAndSellEvent {
    private String msg;

    public CancelHangBuyAndSellEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
