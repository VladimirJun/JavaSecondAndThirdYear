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
    public House deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String cadastralNumber = "";
        String address = "";
        Person houseHolder = new Person();
        List<Flat> flats = new ArrayList<>();
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = jsonParser.getCurrentName();
            jsonParser.nextToken(); // мы должны двигаться на значение
            switch (fieldname) {
                case "cadastralNumber" -> cadastralNumber = jsonParser.getValueAsString();
                case "address" -> address = jsonParser.getValueAsString();
                case "houseHolder" -> houseHolder = jsonParser.readValueAs(Person.class);
                case "flats" -> {
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode node = jsonParser.readValueAsTree();
                    for (JsonNode flatNode : node) {
                        Flat flat = mapper.treeToValue(flatNode, Flat.class);
                        flats.add(flat);
                    }
                }
            }
        }
        return new House(cadastralNumber, address, houseHolder, flats);
    }
}