package iorichina.hellojava.helloface.dto;

public class FaceAreaDto {
    private Integer left;
    private Integer top;
    private Integer width;
    private Integer height;

    public FaceAreaDto(Integer left, Integer top, Integer width, Integer height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public Integer getLeft() {
        return left;
    }

    public FaceAreaDto setLeft(Integer left) {
        this.left = left;
        return this;
    }

    public Integer getTop() {
        return top;
    }

    public FaceAreaDto setTop(Integer top) {
        this.top = top;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public FaceAreaDto setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public FaceAreaDto setHeight(Integer height) {
        this.height = height;
        return this;
    }
}
