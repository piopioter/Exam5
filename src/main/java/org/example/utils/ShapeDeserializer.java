package org.example.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.example.model.Circle;
import org.example.model.Rectangle;
import org.example.model.Shape;
import org.example.model.Square;

import java.io.IOException;

public class Deserialize extends StdDeserializer<Shape> {
    public Deserialize(Class<?> vc) {
        super(vc);
    }

    @Override
    public Shape deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);
        ObjectMapper objectMapper = (ObjectMapper) p.getCodec();

        if(node.has("radius"))
            return objectMapper.treeToValue(node, Circle.class);
        else if(node.has("side"))
            return objectMapper.treeToValue(node, Square.class);
        else if (node.has("width"))
            return objectMapper.treeToValue(node, Rectangle.class);
        else
            throw new RuntimeException("Niepoprawana figura");


    }
}
