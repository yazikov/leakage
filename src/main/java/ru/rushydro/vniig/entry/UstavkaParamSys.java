package ru.rushydro.vniig.entry;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by alyon on 27.09.2015.
 */
@Entity
@Table(name = "USTAVKA_PARAM_SYS")
public class UstavkaParamSys extends AbstractEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ustavka", unique = true, nullable = false)
    Integer idUstavka;
    @Column(name = "discription")
    String discription;
    @Column(name = "date_ustavka")
    Date dateUstavka;
    @Column(name = "value_ustavka_pre")
    Float valueUstavkaPre;
    @Column(name = "value_ustavka_av")
    Float valueUstavkaAv;

    public Integer getIdUstavka() {
        return idUstavka;
    }

    public void setIdUstavka(Integer idUstavka) {
        this.idUstavka = idUstavka;
    }

    public Date getDateUstavka() {
        return dateUstavka;
    }

    public void setDateUstavka(Date dateUstavka) {
        this.dateUstavka = dateUstavka;
    }

    public Float getValueUstavkaPre() {
        return valueUstavkaPre;
    }

    public void setValueUstavkaPre(Float valueUstavkaPre) {
        this.valueUstavkaPre = valueUstavkaPre;
    }

    public Float getValueUstavkaAv() {
        return valueUstavkaAv;
    }

    public void setValueUstavkaAv(Float valueUstavkaAv) {
        this.valueUstavkaAv = valueUstavkaAv;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}