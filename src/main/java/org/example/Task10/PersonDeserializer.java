package org.example.Task10;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.example.houseFlatPerson.Person;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class PersonDeserializer extends JsonDeserializer<Person> {
    @Override
    public Person deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {
        String surname = "";
        String name = "";
        String patronymic = "";
        String birth = "";
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = parser.getCurrentName();
            if ("fullName".equals(fieldname)) {
                parser.nextToken(); // сдвигаемся на значение
                String fullname = parser.getText();
                 String[] words = fullname.split(" ");
                 if (words.length!=3)
                     throw new JsonMappingException("invalid format of fullname");
                surname =words[0];
                name = words[1];
                patronymic = words[2];
            } else if("birth".equals(fieldname)){
                parser.nextToken();
                birth = parser.getText();
            }
        }
        return new Person(surname, name, patronymic, birth);
    }

}
