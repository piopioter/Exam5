package org.example.service;


import org.example.exception.InvalidInputDataException;
import org.example.model.Shape;
import java.util.List;

public interface IShapeService {
    Shape findShapeWithTheLargestArea(List<Shape> shapes) throws InvalidInputDataException;
    Shape findShapeWithTheLargestPerimeterOfSpecifiedType(List<Shape> shapes, Class<?> shapeType) throws InvalidInputDataException;
    void exportListOfShapesToJson(List<Shape> shapes, String path) throws InvalidInputDataException;
    List<Shape> importListOfShapesFromJson(String path) throws InvalidInputDataException;



}
