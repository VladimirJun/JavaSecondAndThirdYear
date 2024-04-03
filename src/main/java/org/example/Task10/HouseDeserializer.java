package org.example.Task10;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.example.houseFlatPerson.Flat;
import org.example.houseFlatPerson.House;
import org.example.houseFlatPerson.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HouseDeserializer extends StdDeserializer<House> {

    protected HouseDeserializer() {
        super(House.class);
    }

    @Override
    public House deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        final JsonNode tree = context.readTree(parser);
        final String cadastralNumber = tree.get("cadastralNumber").asText();
        final String address = tree.get("address").asText();
        final Person houseHolder = context.readValue(tree.get("houseHolder").traverse(), Person.class);
        final List<Flat> flats = new ArrayList<>();
        for (JsonNode node : tree.get("flats")) {
            flats.add(context.readTreeAsValue(node, Flat.class));
        }

        return new House(cadastralNumber, address, houseHolder, flats);
    }
}