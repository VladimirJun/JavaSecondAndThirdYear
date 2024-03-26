package org.example.Task10;

import com.fasterxml.jackson.core.*;
import org.example.houseFlatPerson.Flat;
import java.io.IOException;
import java.util.ArrayList;
public class FlatDeserializer {
    public static Flat deserialize(String flatJson) throws RuntimeException {
        JsonFactory factory = new JsonFactory();

        try {
            JsonParser parser = factory.createParser(flatJson);

            Flat flat = new Flat(0, 0, new ArrayList<>());

            while (!parser.isClosed()) {
                JsonToken token = parser.nextToken();

                if (JsonToken.FIELD_NAME.equals(token)) {
                    String fieldName = parser.getCurrentName();
                    if ("area".equals(fieldName)) {
                        parser.nextToken();
                        flat.setArea(parser.getDoubleValue());
                    } else if ("number".equals(fieldName)) {
                        parser.nextToken();
                        flat.setNumber(parser.getIntValue());
                    } else if ("owners".equals(fieldName)) {
                        parser.nextToken();
                        flat.setOwners(parser.readValueAsTree());
                    }
                }
            }
            parser.close();
            return flat;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
