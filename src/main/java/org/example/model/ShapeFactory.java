package org.example.model;

import org.example.exception.InvalidInputDataException;
import org.example.model.Circle;
import org.example.model.Rectangle;
import org.example.model.Shape;
import org.example.model.Square;

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

    public Square createSquare(double side) throws InvalidInputDataException {
        if (side <= 0)
            throw new InvalidInputDataException("Wartości musi być powyżej zera");
        for (Shape shape : shapes) {
            if (shape instanceof Square && ((Square) shape).getSide() == side)
                return (Square) shape;

        }
        Square square = new Square(side);
        shapes.add(square);
        return square;
    }

    public Rectangle createRectangle(double width, double height) throws InvalidInputDataException {
        if (width <= 0 || height <= 0)
            throw new InvalidInputDataException("Wartości musi być powyżej zera");
        for (Shape shape : shapes) {
            if (shape instanceof Rectangle && ((Rectangle) shape).getHeight() == height
                    && ((Rectangle) shape).getWidth() == width)
                return (Rectangle) shape;
        }
        Rectangle rectangle = new Rectangle(width, height);
        shapes.add(rectangle);
        return rectangle;
    }


    public Circle createCircle(double radius) throws InvalidInputDataException {
        if (radius <= 0)
            throw new InvalidInputDataException("Wartości musi być powyżej zera");
        for (Shape shape : shapes) {
            if (shape instanceof Circle && ((Circle) shape).getRadius() == radius)
                return (Circle) shape;
        }
        Circle circle = new Circle(radius);
        shapes.add(circle);
        return circle;
    }

}