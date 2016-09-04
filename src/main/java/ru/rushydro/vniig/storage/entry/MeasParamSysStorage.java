package ru.rushydro.vniig.storage.entry;

import ru.rushydro.vniig.entry.AbstractEntry;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by alyon on 27.09.2015.
 */
@Entity
@Table(name = "MEAS_PARAM_SYS")
public class MeasParamSysStorage extends AbstractStorageEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "id_sensors")
    PassportParamSysStorage passportParamSys;

    @Column(name = "status_sensors")
    Integer statusSensors;
    @Column(name = "date_meas")
//    @Temporal(value= TemporalType.DATE)
    Date dateMeas;
    @Column(name = "time_meas")
    //    @Temporal(value= TemporalType.DATE)
    Date timeMeas;
    @Column(name = "value_meas")
    Float valueMeas;
    @Column(name = "relative_value_meas")
    Float relativeValueMeas;
    @Column(name = "trust_meas")
    Integer trustMeas;
    @Column(name = "work_sensors")
    Boolean workSensors;

    public Integer getStatusSensors() {
        return statusSensors;
    }

    public void setStatusSensors(Integer statusSensors) {
        this.statusSensors = statusSensors;
    }

    public Date getDateMeas() {
        return dateMeas;
    }

    public void setDateMeas(Date dateMeas) {
        this.dateMeas = dateMeas;
    }

    public Date getTimeMeas() {
        return timeMeas;
    }

    public void setTimeMeas(Date timeMeas) {
        this.timeMeas = timeMeas;
    }

    public Float getValueMeas() {
        return valueMeas;
    }

    public void setValueMeas(Float valueMeas) {
        this.valueMeas = valueMeas;
    }

    public Integer getTrustMeas() {
        return trustMeas;
    }

    public void setTrustMeas(Integer trustMeas) {
        this.trustMeas = trustMeas;
    }

    public Float getRelativeValueMeas() {
        return relativeValueMeas;
    }

    public void setRelativeValueMeas(Float relativeValueMeas) {
        this.relativeValueMeas = relativeValueMeas;
    }

    public Boolean getWorkSensors() {
        return workSensors;
    }

    public void setWorkSensors(Boolean workSensors) {
        this.workSensors = workSensors;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PassportParamSysStorage getPassportParamSys() {
        return passportParamSys;
    }

    public void setPassportParamSys(PassportParamSysStorage passportParamSys) {
        this.passportParamSys = passportParamSys;
    }
}
