package ru.rushydro.vniig.entry;

/**
 * Created by yazik on 08.06.2016.
 */
public class SensorRegion extends AbstractEntry {
    Integer id;

    String title;

    String coord;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }
}
