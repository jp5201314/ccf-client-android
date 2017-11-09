package cn.cnlinfo.ccf.entity;

/**
 * Created by Administrator on 2017/11/7 0007.
 */
public class Zone {

    private int zoneId;
    private String zoneName;
    private String cityId;

    public Zone() {
    }

    public Zone(int zoneId, String zoneName, String cityId) {
        this.zoneId = zoneId;
        this.zoneName = zoneName;
        this.cityId = cityId;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "zoneId=" + zoneId +
                ", zoneName='" + zoneName + '\'' +
                ", cityId='" + cityId + '\'' +
                '}';
    }
}
