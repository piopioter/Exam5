package org.example.model;

import java.util.ArrayList;
import java.util.List;


public class ShapeFactory {

    private List<Shape> shapes;

    public ShapeFactory() {
        this.shapes = new ArrayList<>();
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public Square createSquare(double side) {
        for (Shape shape : shapes) {
           // if (shape != null)
                if (shape instanceof Square && ((Square) shape).getSide() == side)
                    return (Square) shape;

        }
        Square square = new Square(side);
        shapes.add(square);
        return square;
    }

    public Rectangle createRectangle(double width, double height) {
        for (Shape shape : shapes) {
            if (shape instanceof Rectangle && ((Rectangle) shape).getHeight() == height
                    && ((Rectangle) shape).getWidth() == width)
                return (Rectangle) shape;
        }
        Rectangle rectangle = new Rectangle(width, height);
        shapes.add(rectangle);
        return rectangle;
    }


    public Circle createCircle(double radius) {
        for (Shape shape : shapes) {
            if (shape instanceof Circle && ((Circle) shape).getRadius() == radius)
                return (Circle) shape;
        }
        Circle circle = new Circle(radius);
        shapes.add(circle);
        return circle;
    }

}