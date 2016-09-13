package ru.rushydro.vniig.entry;

/**
 * Created by yazik on 11.09.2016.
 */
public class Block {
    Integer id;

    String title;

    int x = 0;

    int y = 0;

    int width = 83;

    int height = 65;

    MeasParamSys measParamSys;

    SignSys signSys;

    Double criterion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public MeasParamSys getMeasParamSys() {
        return measParamSys;
    }

    public void setMeasParamSys(MeasParamSys measParamSys) {
        this.measParamSys = measParamSys;
    }

    public SignSys getSignSys() {
        return signSys;
    }

    public void setSignSys(SignSys signSys) {
        this.signSys = signSys;
    }

    public Double getCriterion() {
        return criterion;
    }

    public void setCriterion(Double criterion) {
        this.criterion = criterion;
    }

    public int getType() {
        if (measParamSys != null && measParamSys.getWorkSensors() != null && !measParamSys.getWorkSensors()) {
            return 4;
        } else {
            if (signSys.getSortSign() != null && signSys.getSortSign().getIdSignal() != null) {
                return signSys.getSortSign().getIdSignal();
            } else {
                return 1;
            }
        }
    }

    public String getColor() {
        String color = "#0FEB00";
        if (getType() == 2) {
            color = "yellow";
        } else if (getType() == 3) {
            color = "red";
        }
        return color;
    }
}
