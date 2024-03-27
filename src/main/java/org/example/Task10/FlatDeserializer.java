package org.example.Task10;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.houseFlatPerson.Flat;
import org.example.houseFlatPerson.House;
import org.example.houseFlatPerson.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlatDeserializer extends JsonDeserializer<Flat> {

    @Override
    public Flat deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        int number = 0;
        double area = 0;
        List<Person> owners = new ArrayList<>();
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = parser.getCurrentName();
            parser.nextToken(); // мы должны двигаться на значение
            switch (fieldname) {
                case "number":
                    number = parser .getValueAsInt();
                    break;
                case "area":
                    area = parser.getDoubleValue();
                    break;
                case "owners":
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode node = parser.readValueAsTree();
                    for (JsonNode personNode : node) {
                        Person person = mapper.treeToValue(personNode, Person.class);
                        owners.add(person);
                    }
                    break;
            }
        }
        return new Flat(number, area, owners);
    }
}
