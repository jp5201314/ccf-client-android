package cn.cnlinfo.ccf.entity;

/**
 * Created by Administrator on 2017/11/7 0007.
 */
public class City {
    private String cityName;
    private String proId;
    private String citySort;

    public City() {

    }

    public City(String cityName, String proId, String citySort) {
        this.cityName = cityName;
        this.proId = proId;
        this.citySort = citySort;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getCitySort() {
        return citySort;
    }

    public void setCitySort(String citySort) {
        this.citySort = citySort;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", proId='" + proId + '\'' +
                ", citySort='" + citySort + '\'' +
                '}';
    }
}
