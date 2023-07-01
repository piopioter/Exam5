package org.example.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"type", "side"})
public  class Square implements Shape{
    private static final ShapeType shapeType = ShapeType.SQUARE;
    private double side;

    public Square() {
    }

    Square(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public ShapeType getType() {
        return shapeType;
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + side +
                '}';
    }
}
