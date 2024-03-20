package org.example.SerializeDeserialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.example.houseFlatPerson.Flat;
import org.example.houseFlatPerson.House;
import org.example.houseFlatPerson.Person;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SerializationUtilsTask10 {

    static class PersonSerializer extends StdSerializer<Person> {
        public PersonSerializer() {
            this(null);
        }

        public PersonSerializer(Class<Person> t) {
            super(t);
        }

        @Override
        public void serialize(Person person, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeStartObject();
            gen.writeStringField("fullName", person.getFullName());
            gen.writeEndObject();
        }
    }
    @JsonSerialize(using = SerializationUtilsTask10.PersonSerializer.class)
    @JsonDeserialize(using = SerializationUtilsTask10.PersonDeserializer.class)

    static class PersonDeserializer extends StdDeserializer<Person> {
        public PersonDeserializer() {
            this(null);
        }

        public PersonDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Person deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonNode node = p.getCodec().readTree(p);
            String surname = node.get("surname").asText();
            String name = node.get("name").asText();
            String patronymic = node.get("patronymic").asText();
            String birth = node.get("birth").asText();
            return new Person(surname, name,patronymic,birth);
        }
    }
    @JsonSerialize(using = SerializationUtilsTask10.FlatSerializer.class)
    @JsonDeserialize(using = SerializationUtilsTask10.FlatDeserializer.class)
    static class FlatSerializer extends StdSerializer<Flat> {
        public FlatSerializer() {
            this(null);
        }

        public FlatSerializer(Class<Flat> t) {
            super(t);
        }

        @Override
        public void serialize(Flat flat, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeStartObject();
            gen.writeStringField("Number: ", flat.getNumber() + "Area: " + flat.getArea() + "List of owners: " + flat.getOwners());
            gen.writeEndObject();
        }
    }

    static class FlatDeserializer extends StdDeserializer<Flat> {
        public FlatDeserializer() {
            this(null);
        }

        public FlatDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Flat deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonNode node = p.getCodec().readTree(p);
            ObjectMapper mapper = new ObjectMapper();
            int number =node.get("number").asInt();
            double area = node.get("area").asDouble();
            List<Person> owners = Collections.singletonList(mapper.readValue(p, Person.class));
            return new Flat(number,area, owners);
        }
    }
    @JsonSerialize(using = SerializationUtilsTask10.HouseSerializer.class)
    @JsonDeserialize(using = SerializationUtilsTask10.HouseDeserializer.class)
    static class HouseSerializer extends StdSerializer<House> {
        public HouseSerializer() {
            this(null);
        }

        public HouseSerializer(Class<House> t) {
            super(t);
        }

        @Override
        public void serialize(House house, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeStartObject();
            gen.writeStringField("cadastralNumber: ", house.getCadastralNumber() + "address:  " + house.getAddress() + "houseHolder:  " + house.getHouseHolder() + "Flats:  " + house.getFlats());
            gen.writeEndObject();
        }
    }

    static class HouseDeserializer extends StdDeserializer<House> {
        public HouseDeserializer() {
            this(null);
        }

        public HouseDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public House deserialize(JsonParser p, DeserializationContext ctxt) throws IOException{
         JsonNode node = p.getCodec().readTree(p);
         String cadastralNumber = node.get("cadastralNumber").asText();
           String address = node.get("address").asText();
            ObjectMapper mapper = new ObjectMapper();
            Person houseHolder = mapper.readValue(p, Person.class);
            List<Flat> flats = Collections.singletonList(mapper.readValue(p, Flat.class));
            return new House(cadastralNumber,address,houseHolder,flats);
        }
    }
}
