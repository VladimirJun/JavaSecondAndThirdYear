package org.example.Task10;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.example.houseFlatPerson.Flat;
import org.example.houseFlatPerson.Person;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FlatSerializer extends com.fasterxml.jackson.databind.JsonSerializer<Flat> {

    @Override
    public void serialize(Flat flat, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("area", flat.getArea());
        jsonGenerator.writeNumberField("number", flat.getNumber());
        jsonGenerator.writeFieldName("owners");
        jsonGenerator.writeObject(flat.getOwners());
        jsonGenerator.writeEndObject();

    }
}


