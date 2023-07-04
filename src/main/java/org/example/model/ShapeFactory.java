package org.example.model;

import com.sun.jdi.ArrayReference;
import org.example.exception.InvalidInputDataException;
import org.example.model.Circle;
import org.example.model.Rectangle;
import org.example.model.Shape;
import org.example.model.Square;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShapeFactory {

    private Map<String, Shape> shapes;

    public ShapeFactory() {
        this.shapes = new HashMap<>();
    }

    public void setShapes(Map<String, Shape> shapes) {
        this.shapes = shapes;
    }

    public Square createSquare(double side) throws InvalidInputDataException {
        if (side <= 0)
            throw new InvalidInputDataException("Wartości musi być powyżej zera");
        String key = "Square" + side;
        if (shapes.containsKey(key))
            return (Square) shapes.get(key);
        else {
            Square square = new Square(side);
            shapes.put(key, square);
            return square;
        }
    }

    public Rectangle createRectangle(double width, double height) throws InvalidInputDataException {
        if (width <= 0 || height <= 0)
            throw new InvalidInputDataException("Wartości musi być powyżej zera");
        String key = "Rectangle" + width + height;
        if (shapes.containsKey(key))
            return (Rectangle) shapes.get(key);
        else {
            Rectangle rectangle = new Rectangle(width, height);
            shapes.put(key, rectangle);
            return rectangle;
        }
    }

    public Circle createCircle(double radius) throws InvalidInputDataException {
        if (radius <= 0)
            throw new InvalidInputDataException("Wartości musi być powyżej zera");
        String key = "Circle" + radius;
        if (shapes.containsKey(key))
            return (Circle) shapes.get(key);
        else {
            Circle circle = new Circle(radius);
            shapes.put(key, circle);
            return circle;
        }
    }

}