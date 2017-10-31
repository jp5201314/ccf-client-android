package cn.cnlinfo.ccf.entity;

/**
 * Created by Administrator on 2017/10/30 0030.
 */

public class PlatformInfo {

    private String total;
    private String activated;
    private String price;
    private String toActivateCcf;
    private String ccIntegral;
    private String cycleIntegral;
    private String cycleStock;
    private String bonusPoints;

    public PlatformInfo() {
    }

    public PlatformInfo(String total, String activated, String price, String toActivateCcf, String ccIntegral, String cycleIntegral, String cycleStock, String bonusPoints) {
        this.total = total;
        this.activated = activated;
        this.price = price;
        this.toActivateCcf = toActivateCcf;
        this.ccIntegral = ccIntegral;
        this.cycleIntegral = cycleIntegral;
        this.cycleStock = cycleStock;
        this.bonusPoints = bonusPoints;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getActivated() {
        return activated;
    }

    public void setActivated(String activated) {
        this.activated = activated;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getToActivateCcf() {
        return toActivateCcf;
    }

    public void setToActivateCcf(String toActivateCcf) {
        this.toActivateCcf = toActivateCcf;
    }

    public String getCcIntegral() {
        return ccIntegral;
    }

    public void setCcIntegral(String ccIntegral) {
        this.ccIntegral = ccIntegral;
    }

    public String getCycleIntegral() {
        return cycleIntegral;
    }

    public void setCycleIntegral(String cycleIntegral) {
        this.cycleIntegral = cycleIntegral;
    }

    public String getCycleStock() {
        return cycleStock;
    }

    public void setCycleStock(String cycleStock) {
        this.cycleStock = cycleStock;
    }

    public String getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(String bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    @Override
    public String toString() {
        return "PlatformInfo{" +
                "total='" + total + '\'' +
                ", activated='" + activated + '\'' +
                ", price='" + price + '\'' +
                ", toActivateCcf='" + toActivateCcf + '\'' +
                ", ccIntegral='" + ccIntegral + '\'' +
                ", cycleIntegral='" + cycleIntegral + '\'' +
                ", cycleStock='" + cycleStock + '\'' +
                ", bonusPoints='" + bonusPoints + '\'' +
                '}';
    }
}
