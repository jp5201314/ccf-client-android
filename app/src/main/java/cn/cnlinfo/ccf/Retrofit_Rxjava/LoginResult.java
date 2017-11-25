package cn.cnlinfo.ccf.Retrofit_Rxjava;

/**
 * Created by Administrator on 2017/11/24 0024.
 */

public class LoginResult<T> {
    private int MessageID;
    private boolean Success;
    private String Content;
    private T Data;


    public LoginResult() {
    }

    public LoginResult(int messageID, boolean success, String content, T data) {
        MessageID = messageID;
        Success = success;
        Content = content;
        Data = data;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "MessageID=" + MessageID +
                ", Success=" + Success +
                ", Content='" + Content + '\'' +
                ", Data=" + Data +
                '}';
    }

    public int getMessageID() {
        return MessageID;
    }

    public void setMessageID(int messageID) {
        MessageID = messageID;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
