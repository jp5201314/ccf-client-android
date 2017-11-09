package cn.cnlinfo.ccf.entity;


import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Table;

/**
 * Created by Administrator on 2017/11/7 0007.
 */
@Table("T_Province")
public class Province {
    @Column("ProName")
    private String proName;
    @Column("ProSort")
    private String proSort;
    @Column("ProRemark")
    private String rpoRemark;

    public Province(String proName, String proSort, String rpoRemark) {
        this.proName = proName;
        this.proSort = proSort;
        this.rpoRemark = rpoRemark;
    }

    public Province() {
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProSort() {
        return proSort;
    }

    public void setProSort(String proSort) {
        this.proSort = proSort;
    }

    public String getRpoRemark() {
        return rpoRemark;
    }

    public void setRpoRemark(String rpoRemark) {
        this.rpoRemark = rpoRemark;
    }

    @Override
    public String toString() {
        return "Province{" +
                "proName='" + proName + '\'' +
                ", proSort='" + proSort + '\'' +
                ", rpoRemark='" + rpoRemark + '\'' +
                '}';
    }
}
