package ru.rushydro.vniig.entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by nikolay on 27.12.15.
 */
@Entity
@Table(name = "parameters")
public class Parametr extends AbstractEntry {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "VALUE")
    private Long value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
