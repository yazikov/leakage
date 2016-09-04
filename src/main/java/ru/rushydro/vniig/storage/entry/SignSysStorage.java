package ru.rushydro.vniig.storage.entry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.rushydro.vniig.entry.AbstractEntry;
import ru.rushydro.vniig.entry.PassportParamSys;
import ru.rushydro.vniig.entry.TypeSignalTable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by alyon on 27.09.2015.
 */
@Entity
@Table(name = "SIGN_SYS")
public class SignSysStorage extends AbstractStorageEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sign")
    Integer idSign;
    @Column(name = "date_sign")
    Date dateSign;
    @Column(name = "time_sign")
    Date timeSign;
    @ManyToOne
    @JoinColumn(name = "sort_sign")
    TypeSignalTableStorage sortSign;

    @Column(name = "date_kvint")
    Date dateKvint;
    @Column(name = "time_kvint")
    Date timeKvint;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sensors")
    PassportParamSysStorage passportParamSys;

    public Integer getIdSign() {
        return idSign;
    }

    public void setIdSign(Integer idSign) {
        this.idSign = idSign;
    }

    public Date getDateSign() {
        return dateSign;
    }

    public void setDateSign(Date dateSign) {
        this.dateSign = dateSign;
    }

    public Date getTimeSign() {
        return timeSign;
    }

    public void setTimeSign(Date timeSign) {
        this.timeSign = timeSign;
    }

    public TypeSignalTableStorage getSortSign() {
        return sortSign;
    }

    public void setSortSign(TypeSignalTableStorage sortSign) {
        this.sortSign = sortSign;
    }

    public PassportParamSysStorage getPassportParamSys() {
        return passportParamSys;
    }

    public void setPassportParamSys(PassportParamSysStorage passportParamSys) {
        this.passportParamSys = passportParamSys;
    }

    public Date getDateKvint() {
        return dateKvint;
    }

    public void setDateKvint(Date dateKvint) {
        this.dateKvint = dateKvint;
    }

    public Date getTimeKvint() {
        return timeKvint;
    }

    public void setTimeKvint(Date timeKvint) {
        this.timeKvint = timeKvint;
    }

    public int getType() {
        if (getSortSign() != null && getSortSign().getIdSignal() != null) {
            return getSortSign().getIdSignal();
        } else {
            return 1;
        }
    }

    public String getText () {
        return getSortSign() != null ? getSortSign().getTextSignal() : "";
    }

    public boolean isKvint () {
        return getTimeKvint() != null && getDateKvint() != null;
    }
}