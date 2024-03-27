package org.example.Task10;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import org.example.houseFlatPerson.Person;

import java.io.IOException;


public class PersonSerializer extends JsonSerializer<Person> {

    @Override
    public void serialize(Person person, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("fullName", person.getFullName());
        jsonGenerator.writeStringField("birth", person.getBirth());
        jsonGenerator.writeEndObject();

    }
}

