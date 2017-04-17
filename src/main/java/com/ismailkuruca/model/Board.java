package com.ismailkuruca.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * Created by ismailkuruca on 17/04/2017.
 */
public class Board {

    @NotNull
    @Min(2)
    private Integer width;

    @NotNull
    @Min(2)
    private int height;

    private int[] content;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[] getContent() {
        if (content == null) {
            this.content = new int[width * height];
        }
        return content;
    }

    public void setContent(int[] content) {
        this.content = content;
    }

    public Board duplicate() {
        Board clone = new Board();
        clone.setHeight(this.height);
        clone.setWidth(this.width);
        clone.setContent(Arrays.copyOf(getContent(), getContent().length));
        return clone;
    }

    @Override
    public String toString() {
        return "Board{" +
                "width=" + width +
                ", height=" + height +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
