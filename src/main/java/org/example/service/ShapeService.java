package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.exception.InvalidInputDataException;
import org.example.model.*;


import java.io.File;
import java.io.IOException;
import java.util.*;

public class ShapeService implements IShapeService {

    private ObjectMapper objectMapper;


    public ShapeService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Shape findShapeWithTheLargestArea(List<Shape> shapes) throws InvalidInputDataException {
        return Optional.ofNullable(shapes)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingDouble(Shape::calculateArea))
                .orElseThrow(() -> new InvalidInputDataException("Niepoprawny element"));
    }

    @Override
    public Shape findShapeWithTheLargestPerimeterOfSpecifiedType(List<Shape> shapes, Class<?> shapeType) throws InvalidInputDataException {
        return Optional.ofNullable(shapes)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .filter(x -> x.getClass() == shapeType)
                .max(Comparator.comparingDouble(Shape::calculatePerimeter))
                .orElseThrow(() -> new InvalidInputDataException("Niepoprawny element"));
    }

    @Override
    public void exportListOfShapesToJson(List<Shape> shapes, String path) throws InvalidInputDataException {

        if (shapes == null)
            throw new InvalidInputDataException("Lista jest nullem");
        if (path == null)
            throw new InvalidInputDataException("Ściezka do pliku jest nullem");

        try {
            objectMapper.writerFor(new TypeReference<List<Shape>>() {
            }).writeValue(new File(path), shapes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Shape> importListOfShapesFromJson(String path) throws InvalidInputDataException {
        Optional.ofNullable(path)
                .orElseThrow(() -> new InvalidInputDataException("Wrong path"));

        List<Shape> shapes;
        try {
            shapes = objectMapper.readValue(new File(path), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return shapes;
    }


}

