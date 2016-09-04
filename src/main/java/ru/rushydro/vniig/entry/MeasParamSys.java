package ru.rushydro.vniig.entry;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by alyon on 27.09.2015.
 */
@Entity
@Table(name = "MEAS_PARAM_SYS")
public class MeasParamSys extends AbstractEntry {
    @Id
    @Column(name = "id_sensors")
    Integer idSensors;
    @Column(name = "status_sensors")
    Integer statusSensors;
    @Column(name = "date_meas")
    @Temporal(value= TemporalType.DATE)
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

    public Integer getIdSensors() {
        return idSensors;
    }

    public void setIdSensors(Integer idSensors) {
        this.idSensors = idSensors;
    }

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
}
