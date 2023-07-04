package org.example.model;

import org.assertj.core.api.Assertions;
import org.example.exception.InvalidInputDataException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ShapeFactoryTest {

    Map<String, Shape> shapeMap;
    ShapeFactory shapeFactory;


    @Before
    public void init() {
        shapeFactory = new ShapeFactory();
        shapeMap = new HashMap<>();
        shapeMap.put("Circle5.0", new Circle(5));
        shapeMap.put("Circle10.0", new Circle(10));
        shapeMap.put("Square10.0", new Square(10));
        shapeMap.put("Square20.0", new Square(20));
        shapeMap.put("Rectangle10.015.0", new Rectangle(10, 15));
        shapeMap.put("Rectangle20.030.0", new Rectangle(20, 30));

    }

    @Test
    public void shouldAddNewSquareToList() throws InvalidInputDataException {
        //given
        shapeFactory.setShapes(shapeMap);

        //when
        Square square = shapeFactory.createSquare(6);

        //then
        assertEquals(7, shapeMap.size());
        assertNotEquals(6, shapeMap.size());
        assertTrue(shapeMap.containsValue(square));
    }

    @Test
    public void shouldReturnShapeFromList() throws InvalidInputDataException {
        //given
        shapeFactory.setShapes(shapeMap);

        //when
        Square square = shapeFactory.createSquare(10);

        //then
        assertEquals(6, shapeMap.size());
        assertTrue(shapeMap.containsValue(square));
    }

    @Test(expected = InvalidInputDataException.class)
    public void shouldThrowInvalidInputDataException() throws InvalidInputDataException {
        //given
        shapeFactory.setShapes(shapeMap);

        //when
        shapeFactory.createSquare(0);

    }

    @Test
    public void shouldAddNewRectangleToList() throws InvalidInputDataException {
        //given
        shapeFactory.setShapes(shapeMap);

        //when
        Rectangle rectangle = shapeFactory.createRectangle(6, 10);

        //then
        assertEquals(7, shapeMap.size());
        Assertions.assertThat(shapeMap).containsValue(rectangle);
    }

    @Test
    public void shouldReturnRectangleFromList() throws InvalidInputDataException {
        //given
        shapeFactory.setShapes(shapeMap);

        //when
        Rectangle rectangle = shapeFactory.createRectangle(10, 15);

        //then
        assertEquals(6, shapeMap.size());
        Assertions.assertThat(shapeMap).containsValue(rectangle);
    }

    @Test
    public void shouldGetInvalidInputDataException() {
        //given
        shapeFactory.setShapes(shapeMap);

        //when
        Exception e =
                assertThrows(InvalidInputDataException.class, () -> shapeFactory.createRectangle(0, 10));

        //then
        Assertions.assertThat(e)
                .hasMessage("Wartości musi być powyżej zera")
                .hasNoCause()
                .isNotNull();

    }

    @Test
    public void shouldAddNewCircleToList() throws InvalidInputDataException {
        //given
        shapeFactory.setShapes(shapeMap);

        //when
        Circle circle = shapeFactory.createCircle(100);

        //then
        assertEquals(7,shapeMap.size());
        Assertions.assertThat(shapeMap).containsValue(circle);
    }

    @Test
    public void shouldReturnCircleFromList() throws InvalidInputDataException {
        //given
        shapeFactory.setShapes(shapeMap);

        //when
        Circle circle = shapeFactory.createCircle(5);

        //then
        assertEquals(6, shapeMap.size());
        Assertions.assertThat(shapeMap).containsValue(circle);
    }

    @Test(expected = InvalidInputDataException.class)
    public void shouldThrowInvalidInputDataExceptionWhenRadiusIsZero() throws InvalidInputDataException {
        //given
        shapeFactory.setShapes(shapeMap);

        //when
        shapeFactory.createCircle(0);

    }

}





