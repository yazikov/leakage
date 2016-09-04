package ru.rushydro.vniig.model;

import ru.rushydro.vniig.entry.PassportParamSys;

import java.util.List;

/**
 * Created by nikolay on 11.10.15.
 */
public class SensorUpdateData {

    Boolean update;

    List<PassportParamSys> sensors;

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public List<PassportParamSys> getSensors() {
        return sensors;
    }

    public void setSensors(List<PassportParamSys> sensors) {
        this.sensors = sensors;
    }
}
