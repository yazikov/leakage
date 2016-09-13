package ru.rushydro.vniig.entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yazik on 08.06.2016.
 */
public class SensorRegion extends AbstractEntry {
    Integer id;

    String title;

    int x = 0;

    int y = 0;

    int width = 83;

    int height = 65;

    List<Block> blocks = new ArrayList<>();

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public int getOffset() {
        int offset = 5;
        if (title.length() < 6) {
            offset = 35 - title.length() * 5;
        }
        return offset;
    }
}
