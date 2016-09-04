package ru.rushydro.vniig.entry;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by alyon on 27.09.2015.
 */
@Entity
@Table(name = "SIGN_SYS")
public class SignSys extends AbstractEntry {
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
    TypeSignalTable sortSign;

    @Column(name = "date_kvint")
    Date dateKvint;
    @Column(name = "time_kvint")
    Date timeKvint;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sensors")
    PassportParamSys passportParamSys;

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

    public TypeSignalTable getSortSign() {
        return sortSign;
    }

    public void setSortSign(TypeSignalTable sortSign) {
        this.sortSign = sortSign;
    }

    public PassportParamSys getPassportParamSys() {
        return passportParamSys;
    }

    public void setPassportParamSys(PassportParamSys passportParamSys) {
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
}