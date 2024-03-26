package org.example.Task10;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.example.houseFlatPerson.Flat;
import org.example.houseFlatPerson.House;
import org.example.houseFlatPerson.Person;

import java.io.IOException;
import java.util.ArrayList;

public class HouseDeserializer extends com.fasterxml.jackson.databind.JsonDeserializer {


    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
    House house = new House("","",new Person("","","",""),new ArrayList<Flat>());
        if (jsonParser.nextToken() != JsonToken.START_OBJECT) {
            throw new IOException("Invalid format of json");
        }

        if (jsonParser.nextToken() != JsonToken.START_OBJECT) {
            throw new IOException("Token { not found");
        }
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = jsonParser.getCurrentName();
            if ("cadastralNumber".equals(fieldname)){

                jsonParser.nextToken(); // сдвигаемся на значение
                house.setCadastralNumber(jsonParser.getValueAsString());
            } else if ("address".equals(fieldname)){

                house.setCadastralNumber(jsonParser.getValueAsString());
            }else if("seniorPerson".equals(fieldname)){
                house.setHouseHolder(jsonParser.readValueAsTree());
            }
            else if("flats".equals(fieldname)){
                house.setFlats(jsonParser.readValueAsTree());
            }
            else{

                throw new IOException("invalid format of json");

            }

        }
        return house;// объект готов
    }
}
