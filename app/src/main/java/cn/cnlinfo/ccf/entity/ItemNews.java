package cn.cnlinfo.ccf.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by JP on 2017/11/21 0021.
 */

public class ItemNews implements Serializable {

    @JSONField(name = "NewsID")
    /**
     * 公告id
     */
    private int newsId;

    @JSONField(name = "Subject")
    /**
     * 公告标题
     */
    private String subject;


    @JSONField(name = "ClassID")
    /**
     * 1标识是新闻 2是公告
     */
    private String classId;

    public ItemNews() {
    }

    public ItemNews(int newsId, String subject, String classId) {
        this.newsId = newsId;
        this.subject = subject;
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "ItemNews{" +
                "newsId=" + newsId +
                ", subject='" + subject + '\'' +
                ", classId='" + classId + '\'' +
                '}';
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
