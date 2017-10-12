package cn.cnlinfo.ccf.event;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class ErrorMessageEvent {
    private String msg;
    public ErrorMessageEvent(String msg){
        this.msg = msg;
    }

    public ErrorMessageEvent(){}

    public String getMsg(){
        return msg;
    }
}
