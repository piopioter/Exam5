package org.example.model;

import org.assertj.core.api.Assertions;
import org.example.exception.InvalidInputDataException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShapeFactoryTest {

    List<Shape> shapeList;
    ShapeFactory shapeFactory;


    @Before
    public void init() {
        shapeFactory = new ShapeFactory();
        shapeList = new ArrayList<>();
        shapeList.add(new Circle(5));
        shapeList.add(new Circle(10));
        shapeList.add(new Square(10));
        shapeList.add(new Square(20));
        shapeList.add(new Rectangle(10, 15));
        shapeList.add(new Rectangle(20, 30));

    }

    @Test
    public void shouldAddNewSquareToList() throws InvalidInputDataException {
        //given
        shapeFactory.setShapes(shapeList);

        //when
        Square square = shapeFactory.createSquare(6);

        //then
        assertEquals(7, shapeList.size());
        assertNotEquals(6, shapeList.size());
        Assertions.assertThat(shapeList).contains(square);
    }

    @Test
    public void shouldReturnShapeFromList() throws InvalidInputDataException {
        //given
        shapeFactory.setShapes(shapeList);

        //when
        Square square = shapeFactory.createSquare(10);

        //then
        assertSame(shapeList.get(2), square);
    }

    @Test(expected = InvalidInputDataException.class)
    public void shouldThrowInvalidInputDataException() throws InvalidInputDataException {
        //given
        shapeFactory.setShapes(shapeList);

        //when
        shapeFactory.createSquare(0);

    }

    @Test
    public void shouldAddNewRectangleToList() throws InvalidInputDataException {
        //given
        shapeFactory.setShapes(shapeList);

        //when
        Rectangle rectangle = shapeFactory.createRectangle(6, 10);

        //then
        assertEquals(shapeList.get(6), rectangle);
    }

    @Test
    public void shouldReturnRectangleFromList() throws InvalidInputDataException {
        //given
        shapeFactory.setShapes(shapeList);

        //when
        Rectangle rectangle = shapeFactory.createRectangle(10, 15);

        //then
        assertSame(shapeList.get(4), rectangle);
    }

    @Test
    public void shouldGetInvalidInputDataException() {
        //given
        shapeFactory.setShapes(shapeList);

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
    public void shouldNewAddCircleToList() throws InvalidInputDataException {
        //given
        shapeFactory.setShapes(shapeList);

        //when
        Circle circle = shapeFactory.createCircle(100);

        //then
        assertEquals(shapeList.get(6), circle);
    }

    @Test
    public void shouldReturnCircleFromList() throws InvalidInputDataException {
        //given
        shapeFactory.setShapes(shapeList);

        //when
        Circle circle = shapeFactory.createCircle(5);

        //then
        assertSame(shapeList.get(0), circle);
    }

    @Test(expected = InvalidInputDataException.class)
    public void shouldThrowInvalidInputDataExceptionWhenRadiusIsZero() throws InvalidInputDataException {
        //given
        shapeFactory.setShapes(shapeList);

        //when
        shapeFactory.createCircle(0);

    }

}





