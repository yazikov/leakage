package ru.rushydro.vniig.storage.entry;

import ru.rushydro.vniig.entry.UstavkaParamSys;

import javax.persistence.*;
import java.util.List;

/**
 * Created by alyon on 27.09.2015.
 */
@Entity
@Table(name = "PASSPORT_PARAM_SYS")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PassportParamSysStorage extends AbstractStorageEntry {
    @Column(name = "OBJ_MONITOR")
    String objMonitor;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "MEAS_PARAM_TYPE_SIG")
    UstavkaParamSysStorage measParamTypeSig;

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

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "passportParamSys")
//    List<MeasParamSysStorage> measParamSys;

//    public List<SignSysStorage> getSignSys() {
//        return signSys;
//    }
//
//    public void setSignSys(List<SignSysStorage> signSys) {
//        this.signSys = signSys;
//    }

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "passportParamSys")
//    List<SignSysStorage> signSys;

    public String getObjMonitor() {
        return objMonitor;
    }

    public void setObjMonitor(String objMonitor) {
        this.objMonitor = objMonitor;
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

//    public List<MeasParamSysStorage> getMeasParamSys() {
//        return measParamSys;
//    }
//
//    public void setMeasParamSys(List<MeasParamSysStorage> measParamSys) {
//        this.measParamSys = measParamSys;
//    }

    public UstavkaParamSysStorage getMeasParamTypeSig() {
        return measParamTypeSig;
    }

    public void setMeasParamTypeSig(UstavkaParamSysStorage measParamTypeSig) {
        this.measParamTypeSig = measParamTypeSig;
    }

    //    public List<InsisionSensors> getInsisionSensorsList() {
//        return insisionSensorsList;
//    }
//
//    public void setInsisionSensorsList(List<InsisionSensors> insisionSensorsList) {
//        this.insisionSensorsList = insisionSensorsList;
//    }

  /*  public String toJSON() {
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

        if (measParamTypeSig != null) {
            if (measParamTypeSig.getValueUstavkaPre() != null) {
                if (!sb.toString().isEmpty()) {
                    sb.append(",");
                }
                sb.append("setPre:").append(measParamTypeSig.getValueUstavkaPre());
            }
            if (measParamTypeSig.getValueUstavkaAv() != null) {
                if (!sb.toString().isEmpty()) {
                    sb.append(",");
                }
                sb.append("setAv:").append(measParamTypeSig.getValueUstavkaAv());
            }
        }

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
    }*/
}
