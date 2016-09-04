package ru.rushydro.vniig.entry;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by alyon on 18.10.2015.
 */
@Entity
@Table(name = "insision_sensors")
public class InsisionSensors extends AbstractEntry {

    @Id
    @Column(name = "id")
    Integer id;

    @Column(name = "id_insision")
    Integer insision;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_sensors")
    PassportParamSys passportParamSys;

    @Column(name = "x_value")
    Integer xValue;

    @Column(name="y_value")
    Integer yValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInsision() {
        return insision;
    }

    public void setInsision(Integer insision) {
        this.insision = insision;
    }

    public Integer getxValue() {
        return xValue;
    }

    public void setxValue(Integer xValue) {
        this.xValue = xValue;
    }

    public Integer getyValue() {
        return yValue;
    }

    public void setyValue(Integer yValue) {
        this.yValue = yValue;
    }

    public PassportParamSys getPassportParamSys() {
        return passportParamSys;
    }

    public void setPassportParamSys(PassportParamSys passportParamSys) {
        this.passportParamSys = passportParamSys;
    }
}
