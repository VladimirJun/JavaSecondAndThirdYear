package org.example.Task10;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.example.houseFlatPerson.Person;

import java.io.IOException;
import java.io.Serializable;

public class PersonDeserializer extends com.fasterxml.jackson.databind.JsonDeserializer {
    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        Person person = new Person("", ",", "", "");
        jsonParser.nextToken(); // advance the parser
        person.setSurname(jsonParser.getText());
        jsonParser.nextToken(); // consume END_OBJECT
        person.setName(jsonParser.getText());
        jsonParser.nextToken();
        person.setPatronymic(jsonParser.getText());
        jsonParser.nextToken();
        person.setBirth(jsonParser.getText());
        jsonParser.nextToken();
        return person;
    }
}
