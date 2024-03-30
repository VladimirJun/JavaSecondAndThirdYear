package org.example.Task10;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.example.houseFlatPerson.Person;

import java.io.IOException;
import java.io.Serializable;

public class PersonDeserializer extends JsonDeserializer<Person> {
    @Override
    public Person deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {
        String surname = "";
        String name = "";
        String patronymic = "";
        String birth = "";
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = parser.getCurrentName();
            if ("surname".equals(fieldname)) {
                parser.nextToken(); // сдвигаемся на значение
                surname = parser.getText();
            } else if ("name".equals(fieldname)) {
                parser.nextToken();
                name = parser.getText();
            } else if("patronymic".equals(fieldname)){
                parser.nextToken();
                patronymic = parser.getText();
            } else if("birth".equals(fieldname)){
                parser.nextToken();
                birth = parser.getText();
            }
        }
        return new Person(surname, name, patronymic, birth);
    }

}
