package cn.cnlinfo.ccf.event;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class ErrorMessageEvent {
    private int errorCode;
    private String msg;
    public ErrorMessageEvent(int errorCode,String msg){
        this.errorCode =errorCode;
        this.msg = msg;
    }

    public ErrorMessageEvent(){}


    public String getMsg(){
        return msg;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
