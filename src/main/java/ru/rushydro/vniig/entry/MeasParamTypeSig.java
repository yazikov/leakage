package ru.rushydro.vniig.entry;

import javax.persistence.*;

/**
 * Created by alyon on 04.10.2015.
 */
@Entity
@Table(name = "meas_param_type_sig")
public class MeasParamTypeSig extends AbstractEntry {

    @Id
    @Column(name = "id_type")
    Integer id_type;

    @Column(name = "discription")
    String discription;


    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Integer getId_type() {
        return id_type;
    }

    public void setId_type(Integer id_type) {
        this.id_type = id_type;
    }
}
