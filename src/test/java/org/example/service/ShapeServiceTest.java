package org.example.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.exception.InvalidInputDataException;
import org.example.model.Shape;
import org.example.model.ShapeFactory;
import org.example.model.ShapeType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.assertj.core.api.Assertions;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class ShapeServiceTest {

    @Mock
    ObjectMapper objectMapper;
    @InjectMocks
    ShapeService shapeService;
    ShapeFactory shapeFactory;
    List<Shape> shapes;

    @Before
    public void init() throws InvalidInputDataException {
        MockitoAnnotations.openMocks(this);
        shapeFactory = new ShapeFactory();
        shapes = Arrays.asList(
                shapeFactory.createSquare(10),
                shapeFactory.createCircle(10),
                shapeFactory.createRectangle(10, 15),
                shapeFactory.createSquare(15),
                shapeFactory.createCircle(15)
        );
    }

    @Test
    public void shouldGetShapeWithLargestArea() throws InvalidInputDataException {
        //given
        List<Shape> shapeList = shapes;
        //when
        Shape shapeWithTheLargestArea = shapeService.findShapeWithTheLargestArea(shapeList);
        //then
        assertSame(shapes.get(4), shapeWithTheLargestArea);
    }

    @Test(expected = InvalidInputDataException.class)
    public void shouldThrowInvalidInputDataExceptionWhenShapeListIsNull() throws InvalidInputDataException {
        //given
        List<Shape> shapeList = null;
        //when
        shapeService.findShapeWithTheLargestArea(shapeList);

    }

    @Test
    public void shouldGetSpecifiedShapeWithLargestPerimeter() throws InvalidInputDataException {
        //given
        List<Shape> shapeList = shapes;
        ShapeType shapeType = ShapeType.SQUARE;
        //when
        Shape result = shapeService.findShapeWithTheLargestPerimeterOfSpecifiedType(shapeList, shapeType);
        //then
        assertSame(shapes.get(3), result);
    }

    @Test(expected = InvalidInputDataException.class)
    public void getThrowInvalidInputDataExceptionWhenListIsNull() throws InvalidInputDataException {
        //given
        List<Shape> shapeList = null;
        ShapeType shapeType =  ShapeType.SQUARE;
        //when
        shapeService.findShapeWithTheLargestPerimeterOfSpecifiedType(shapeList, shapeType);
    }

    @Test
    public void shouldWriteJson() throws IOException, InvalidInputDataException {
        //given
        List<Shape> shapeList = shapes;
        String path = "src/main/resources/shapes.json";
        //when
        shapeService.exportListOfShapesToJson(shapeList, path);
        //then
        Mockito.verify(objectMapper, Mockito.only()).writeValue(new File(path), shapeList);
    }

    @Test(expected = InvalidInputDataException.class)
    public void shouldThrowInvalidInputDataExceptionWhenListIsNull() throws IOException, InvalidInputDataException {
        //given
        List<Shape> shapeList = null;
        String path = "src/main/resources/shapes.json";
        //when
        shapeService.exportListOfShapesToJson(shapeList, path);
        //then
        Mockito.verify(objectMapper, Mockito.never()).writeValue(new File(path), shapeList);
    }

    @Test
    public void shouldThrowInvalidInputDataException() {
        //given
        List<Shape> shapeList = shapes;
        String path = null;
        //when
        Exception e =
                assertThrows(InvalidInputDataException.class, () -> shapeService.exportListOfShapesToJson(shapeList, path));
        //then
        Assertions.assertThat(e)
                .isExactlyInstanceOf(InvalidInputDataException.class)
                .hasMessage("Ściezka do pliku jest nullem")
                .hasNoCause();
    }

    @Test
    public void shouldReturnListOfShapesFromJson() throws IOException, InvalidInputDataException {
        //given
        String path = "src/main/resources/shapes.json";
        List<Shape> list = shapes;

        Mockito.when(objectMapper.readValue(Mockito.eq(new File(path)),Mockito.any(TypeReference.class))).thenReturn(list);
        //when
        List<Shape> result = shapeService.importListOfShapesFromJson(path);

        //then
        Assert.assertTrue(result.containsAll(list));
        Mockito.verify(objectMapper, Mockito.times(1)).readValue(Mockito.eq(new File(path)),
               Mockito.any(TypeReference.class));




    }
    @Test
    public void shouldThrowInvalidInputDataExceptionWhenPathIsNull() {
        //given
        String path = null;
        //when
        Exception e =
                assertThrows(InvalidInputDataException.class , () -> shapeService.importListOfShapesFromJson(path));
        //then
        Assertions.assertThat(e)
                .isExactlyInstanceOf(InvalidInputDataException.class)
                .hasMessage("Wrong path")
                .hasNoCause();
    }


}