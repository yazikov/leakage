package ru.rushydro.vniig.entry;

import javax.persistence.*;
import java.util.List;

/**
 * Created by alyon on 27.09.2015.
 */
@Entity
@Table(name = "PASSPORT_PARAM_SYS")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PassportParamSys extends AbstractEntry {
    @Column(name = "OBJ_MONITOR")
    String objMonitor;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "MEAS_PARAM_TYPE_SIG")
    UstavkaParamSys measParamTypeSig;

    @Column(name = "NAME_SENSORS")
    String name;

    @Column(name = "SORT_SIGN")
    String sortSign;

    @Column(name = "TYPE_OF_SENSOR")
    String typeOfSensor;

    @Column(name = "NUMBER_OF_SENSOR")
    String number;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
    @Column(name = "id_sensors")
    Integer idSensors;
    @Column(name = "x_value")
    Integer xValue;
    @Column(name = "y_value")
    Integer yValue;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sensors")
    MeasParamSys measParamSys;

    @OneToMany(mappedBy = "passportParamSys", fetch = FetchType.EAGER)
    List<InsisionSensors> insisionSensorsList;

    public SignSys getSignSys() {
        return signSys;
    }

    public void setSignSys(SignSys signSys) {
        this.signSys = signSys;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "passportParamSys")
    SignSys signSys;

    @Column(name = "CRITERION")
    Double criterion;

    @Column(name = "CRITER_RELEASE")
    Double criter_release;

    @Column(name = "IS_RELEASE")
    Boolean is_release;

    public Boolean getIs_release() {
        return is_release;
    }

    public void setIs_release(Boolean is_release) {
        this.is_release = is_release;
    }

    public Double getCriter_release() {
        return criter_release;
    }

    public void setCriter_release(Double criter_release) {
        this.criter_release = criter_release;
    }

    public Double getCriterion() {
        return criterion;
    }

    public void setCriterion(Double criterion) {
        this.criterion = criterion;
    }
    public PassportParamSys() {
    }

    public String getObjMonitor() {
        return objMonitor;
    }

    public void setObjMonitor(String objMonitor) {
        this.objMonitor = objMonitor;
    }

    public UstavkaParamSys getMeasParamTypeSig() {
        return measParamTypeSig;
    }

    public void setMeasParamTypeSig(UstavkaParamSys measParamTypeSig) {
        this.measParamTypeSig = measParamTypeSig;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortSign() {
        return sortSign;
    }

    public void setSortSign(String sortSign) {
        this.sortSign = sortSign;
    }

    public String getTypeOfSensor() {
        return typeOfSensor;
    }

    public void setTypeOfSensor(String typeOfSensor) {
        this.typeOfSensor = typeOfSensor;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getIdSensors() {
        return idSensors;
    }

    public void setIdSensors(Integer idSensors) {
        this.idSensors = idSensors;
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

    public MeasParamSys getMeasParamSys() {
        return measParamSys;
    }

    public void setMeasParamSys(MeasParamSys measParamSys) {
        this.measParamSys = measParamSys;
    }

    public List<InsisionSensors> getInsisionSensorsList() {
        return insisionSensorsList;
    }

    public void setInsisionSensorsList(List<InsisionSensors> insisionSensorsList) {
        this.insisionSensorsList = insisionSensorsList;
    }

    public String toJSON() {
        StringBuilder sb = new StringBuilder();
        if (idSensors != null) {
            sb.append("id:").append(idSensors);
        }

        if (name != null) {
            if (!sb.toString().isEmpty()) {
                sb.append(",");
            }
            sb.append("name:").append("\"").append(name).append("\"");
        }

        if (getValue() != null) {
            if (!sb.toString().isEmpty()) {
                sb.append(",");
            }
            sb.append("value:").append("\"").append(getValue()).append("\"");
        }

        if (xValue != null) {
            if (!sb.toString().isEmpty()) {
                sb.append(",");
            }
            sb.append("x:").append(xValue);
        }

        if (yValue != null) {
            if (!sb.toString().isEmpty()) {
                sb.append(",");
            }
            sb.append("y:").append(yValue);
        }

        if (objMonitor != null) {
            if (!sb.toString().isEmpty()) {
                sb.append(",");
            }
            sb.append("objMonitor:").append("\"").append(objMonitor).append("\"");
        }

        if (criterion != null) {
            if (!sb.toString().isEmpty()) {
                sb.append(",");
            }
            sb.append("setPre:").append(criterion);
        }

//        if (criterion2 != null) {
//            if (!sb.toString().isEmpty()) {
//                sb.append(",");
//            }
//            sb.append("setAv:").append(criterion2);
//        }

        if (measParamSys != null && measParamSys.getWorkSensors() != null && !measParamSys.getWorkSensors()) {
            if (!sb.toString().isEmpty()) {
                sb.append(",");
            }
            sb.append("type:").append(4);
            if (!sb.toString().isEmpty()) {
                sb.append(",");
            }
            sb.append("text:").append("\'\'");
        } else {
            if (signSys != null) {
                if (signSys.getSortSign() != null && signSys.getSortSign().getTextSignal() != null) {
                    if (!sb.toString().isEmpty()) {
                        sb.append(",");
                    }
                    sb.append("text:").append("\'").append(signSys.getSortSign().getTextSignal()).append("\'");
                }
                if (signSys.getSortSign() != null && signSys.getSortSign().getIdSignal() != null) {
                    if (!sb.toString().isEmpty()) {
                        sb.append(",");
                    }
                    sb.append("type:").append(signSys.getSortSign().getIdSignal());
                }
            }
        }

        if (!sb.toString().isEmpty()) {
            sb.append(",");
        }
        sb.append("isKvint:").append(isKvint());

        return "{" + sb.toString() + "}";
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

    public String getText () {
        return signSys != null && signSys.getSortSign() != null ? signSys.getSortSign().getTextSignal() : "";
    }

    public Float getValue () {
        return measParamSys != null && measParamSys.getValueMeas() != null ? measParamSys.getValueMeas() : 0;
    }

    public boolean isKvint () {
        return signSys == null || (signSys.getTimeKvint() != null && signSys.getDateKvint() != null);
    }
}
