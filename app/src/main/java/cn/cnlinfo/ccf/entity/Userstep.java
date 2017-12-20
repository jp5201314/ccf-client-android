package cn.cnlinfo.ccf.entity;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class Userstep {
    /**
     * DayStep : 0  步数
     * Ranking : 31 跑步排名
     * Praise : 0  获得的赞
     * Carbonnum : 0 待激活碳控因子量
     * F : 0 基础贡献值
     * E : 0.880700432441527  贡献值
     */

    private int DayStep;
    private int Ranking;
    private int Praise;
    private int Carbonnum;
    private double F;
    private double E;

    public Userstep() {
    }

    public Userstep(int dayStep, int ranking, int praise, int carbonnum, double f, double e) {
        DayStep = dayStep;
        Ranking = ranking;
        Praise = praise;
        Carbonnum = carbonnum;
        F = f;
        E = e;
    }

    public int getDayStep() {
        return DayStep;
    }

    public void setDayStep(int DayStep) {
        this.DayStep = DayStep;
    }

    public int getRanking() {
        return Ranking;
    }

    public void setRanking(int Ranking) {
        this.Ranking = Ranking;
    }

    public int getPraise() {
        return Praise;
    }

    public void setPraise(int Praise) {
        this.Praise = Praise;
    }

    public int getCarbonnum() {
        return Carbonnum;
    }

    public void setCarbonnum(int Carbonnum) {
        this.Carbonnum = Carbonnum;
    }

    public double getF() {
        return F;
    }

    public void setF(double F) {
        this.F = F;
    }

    public double getE() {
        return E;
    }

    public void setE(double E) {
        this.E = E;
    }
}
