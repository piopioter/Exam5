package org.example.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.example.model.Shape;

public enum ObjectMapperHolder {
    INSTANCE;
    private final ObjectMapper objectMapper;

    ObjectMapperHolder() {
        objectMapper = create();
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    private static ObjectMapper create (){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        ShapeDeserializer deserialize = new ShapeDeserializer(Shape.class);

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        module.addDeserializer(Shape.class,deserialize);
        mapper.registerModule(module);

        return mapper;

    }
}
