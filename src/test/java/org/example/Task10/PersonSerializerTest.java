package org.example.Task10;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.example.houseFlatPerson.Flat;
import org.example.houseFlatPerson.House;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import org.example.houseFlatPerson.Person;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PersonSerializerTest {
    @Test
    public void testSerializePerson() throws IOException {
        Person person = new Person("Петров", "Владимир", "Юрьевич", "11.06.2004");
        JsonGenerator jsonGenerator = mock(JsonGenerator.class);
        SerializerProvider serializerProvider = mock(SerializerProvider.class);
        PersonSerializer personSerializer = new PersonSerializer();
        personSerializer.serialize(person, jsonGenerator, serializerProvider);
        verify(jsonGenerator).writeStartObject();
        verify(jsonGenerator).writeStringField("fullName", "Петров Владимир Юрьевич");
        verify(jsonGenerator).writeStringField("birth", "11.06.2004");
        verify(jsonGenerator).writeEndObject();
    }

    @Test
    public void testDeserializePerson() throws IOException {
        String json = "{\"surname\":\"Петров\", \"name\":\"Владимир\", \"patronymic\":\"Юрьевич\", \"birth\":\"11.06.2004\"}";
        Person p1 = new Person("Петров", "Владимир", "Юрьевич", "11.06.2004");
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(json,Person.class);
        assertEquals(person.toString(), p1.toString());
    }

    @Test public void testFlatSerialize() throws IOException {
         Flat flat = new Flat(100, 5, new ArrayList<Person>());

        JsonGenerator jsonGenerator = mock(JsonGenerator.class);
        SerializerProvider serializerProvider = mock(SerializerProvider.class);
        FlatSerializer flatSerializer = new FlatSerializer();
        flatSerializer.serialize(flat, jsonGenerator, null);
        jsonGenerator.close();
        verify(jsonGenerator).writeNumberField("number", 100);
    }
    @Test public void testFlatSerialize2() throws IOException {
        Flat flat = new Flat(100, 5.55, new ArrayList<Person>());

        JsonGenerator jsonGenerator = mock(JsonGenerator.class);
        SerializerProvider serializerProvider = mock(SerializerProvider.class);
        FlatSerializer flatSerializer = new FlatSerializer();
        flatSerializer.serialize(flat, jsonGenerator, null);
        jsonGenerator.close();
        verify(jsonGenerator).writeNumberField("area", 5.55);
    }


    @Test
    void flatDeserialize() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"number\":101, \"area\": 100.5, \"owners\": [ { \"surname\": \"Петров\", \"name\": \"Владимир\", \"patronymic\": \"Юрьевич\", \"birth\": \"11.06.2004\" } ] }";
            Flat fl = objectMapper.readValue(json,Flat.class);
            Flat expectedFlat = new Flat(101, 100.5, Collections.singletonList(new Person("Петров","Владимир","Юрьевич","11.06.2004")));
            assertEquals(expectedFlat.toString(),fl.toString());

    }
    @Test
    void flatDeserializeEmptyOwners() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"number\":101, \"area\": 100.5, \"owners\": [] }";
        Flat fl = objectMapper.readValue(json,Flat.class);
        Flat expectedFlat = new Flat(101, 100.5, new ArrayList<Person>());
        assertEquals(expectedFlat.toString(),fl.toString());

    }

    @Test
    public void testSerializeHouse() throws IOException {
        //Arrange
        House house = new House("123456789", "Test Address", new Person(), new ArrayList<>());
        JsonGenerator jsonGenerator = mock(JsonGenerator.class);
        SerializerProvider serializerProvider = mock(SerializerProvider.class);
        HouseSerializer serializer = new HouseSerializer();
        serializer.serialize(house, jsonGenerator, serializerProvider);
        verify(jsonGenerator).writeStringField("cadastralNumber", "123456789");
    }
    @Test
    public void testSerializeHouse2() throws IOException {
        //Arrange
        House house = new House("123456789", "Test Address", new Person(), new ArrayList<>());
        JsonGenerator jsonGenerator = mock(JsonGenerator.class);
        SerializerProvider serializerProvider = mock(SerializerProvider.class);
        HouseSerializer serializer = new HouseSerializer();
        serializer.serialize(house, jsonGenerator, serializerProvider);
        verify(jsonGenerator).writeStringField("address", "Test Address");
    }

    @Test
    public void testDeserializeHouse() throws IOException {
        String json = "{\"cadastralNumber\":\"1\",\"address\":\"House1\",\"houseHolder\":{\"surname\":\"Петров\",\"name\":\"Владимир\",\"patronymic\":\"Юрьевич\",\"birth\":\"11.06.2004\"},\"flats\":[]}";
        JsonParser jsonParser = new JsonFactory().createParser(new StringReader(json));
        ObjectMapper objectMapper = new ObjectMapper();
        House h1 = objectMapper.readValue(json,House.class);
        House expected = new House("1","House1", new Person("Петров","Владимир","Юрьевич","11.06.2004"), new ArrayList<Flat>());
        assertEquals(h1.toString(),expected.toString());
    }
}