package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.model.Shape;
import org.example.model.ShapeFactory;
import org.example.model.ShapeType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.assertj.core.api.Assertions;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class IIShapeServiceTest {
    @Mock
    ObjectMapper objectMapper;
    @InjectMocks
    ShapeService shapeService;

    ShapeFactory shapeFactory;
    List<Shape> shapes;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        shapeFactory = new ShapeFactory();
        shapes = Arrays.asList(
                shapeFactory.createSquare(10), //100  40
                shapeFactory.createCircle(10), //314.16  62.83
                shapeFactory.createRectangle(10, 15), //150  50
                shapeFactory.createSquare(15), //225 60
                shapeFactory.createCircle(15)//706,9  94,2
        );


    }

    @Test
    public void shouldGetShapeWithLargestArea() {
        //given
        List<Shape> shapeList = shapes;
        //when
        Shape shapeWithTheLargestArea = shapeService.findShapeWithTheLargestArea(shapeList);
        //then
        assertSame(shapes.get(4), shapeWithTheLargestArea);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionWhenShapeListIsNull() {
        //given
        List<Shape> shapeList = null;
        //when
        shapeService.findShapeWithTheLargestArea(shapeList);

    }

    @Test
    public void shouldGetSpecifiedShapeWithLargestPerimeter() {
        //given
        List<Shape> shapeList = shapes;
        ShapeType shapeType = ShapeType.SQUARE;
        //when
        Shape result = shapeService.findShapeWithTheLargestPerimeterOfSpecifiedType(shapeList, shapeType);
        //then
        assertSame(shapes.get(3), result);
    }

    @Test(expected = NoSuchElementException.class)
    public void getThrowNoSuchElementExceptionWhenShapeListIsNull() {
        //given
        List<Shape> shapeList = null;
        ShapeType shapeType = ShapeType.SQUARE;
        //when
        shapeService.findShapeWithTheLargestPerimeterOfSpecifiedType(shapeList, shapeType);
    }

    @Test
    public void shouldReturnJson() throws IOException {
        //given
        List<Shape> shapeList = shapes;
        String path = "src/main/resources/shapes.json";
        //when
        shapeService.exportListOfShapesToJson(shapeList, path);
        //then
        Mockito.verify(objectMapper, Mockito.only()).writeValue(new File(path), shapeList);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionWhenListIsNull() throws IOException {
        //given
        List<Shape> shapeList = null;
        String path = "src/main/resources/shapes.json";
        //when
        shapeService.exportListOfShapesToJson(shapeList, path);
        //then
        Mockito.verify(objectMapper, Mockito.never()).writeValue(new File(path), shapeList);
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenPathIsNull()  {
        //given
        List<Shape> shapeList = shapes;
        String path = null;
        //when
        NoSuchElementException e =
                assertThrows(NoSuchElementException.class, () -> shapeService.exportListOfShapesToJson(shapeList, path));
        //then
        Assertions.assertThat(e)
                .isExactlyInstanceOf(NoSuchElementException.class)
                .hasMessage("Ściezka do pliku jest nullem")
                .hasNoCause();
    }

    @Test
    public void shouldReturnListOfShapesFromJson(){
        //given
        String path = "src/main/resources/shapes.json";
        //when
        List<Shape> shapeList = shapeService.importListOfShapesFromJson(path);
        //then
        assertEquals(5,shapeList.size());

    }

}