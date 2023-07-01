package org.example.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"type", "width", "height"})
public class Rectangle implements Shape {
    private static final ShapeType shapeType = ShapeType.RECTANGLE;
    private double width;
    private double height;

    public Rectangle() {
    }

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * width + 2 * height;
    }


    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public ShapeType getType() {
        return shapeType;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
