package org.example.Task10;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.houseFlatPerson.Flat;
import org.example.houseFlatPerson.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlatDeserializer extends JsonDeserializer<Flat> {

    @Override
    public Flat deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
        final JsonNode tree = context.readTree(parser);
        final int number = tree.get("number").asInt();
        final double area = tree.get("area").asDouble();
        final List<Person> owners = new ArrayList<>();
        for (JsonNode node : tree.get("owners")) {
            owners.add(context.readTreeAsValue(node, Person.class));
        }
        return new Flat(number, area, owners);
    }
}
