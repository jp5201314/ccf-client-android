package cn.cnlinfo.ccf.entity;

/**
 * Created by Administrator on 2017/11/7 0007.
 */

public class RunningRankEntity {

    private int id;
    private String name;
    private String step;
    private int num;

    public RunningRankEntity(int id, String name, String step, int num) {
        this.id = id;
        this.name = name;
        this.step = step;
        this.num = num;
    }

    public RunningRankEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "RunningRankEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", step='" + step + '\'' +
                ", num=" + num +
                '}';
    }
}
