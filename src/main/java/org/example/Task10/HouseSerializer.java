package org.example.Task10;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.example.houseFlatPerson.House;
import org.example.houseFlatPerson.Person;

import java.io.IOException;

public class HouseSerializer extends com.fasterxml.jackson.databind.JsonSerializer<House> {

    @Override
    public void serialize(House house, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("cadastralNumber", house.getCadastralNumber());
        jsonGenerator.writeStringField("address", house.getAddress());
        jsonGenerator.writeObject(house.getHouseHolder());
        jsonGenerator.writeObject(house.getFlats());
        jsonGenerator.writeEndObject();
    }

}
