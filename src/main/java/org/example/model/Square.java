package org.example;

public  class Square implements IShape {
    private double side;

    private Square(double side) {
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
        return 2 * side;
    }
}
