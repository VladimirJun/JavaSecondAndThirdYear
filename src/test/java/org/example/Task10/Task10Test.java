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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.example.houseFlatPerson.Person;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class Task10Test {
    @Test
    public void testSerializePerson() throws IOException {
        String json1 = "{\"fullName\":\"Петров Владимир Юрьевич\",\"birth\":\"11.06.2004\"}";
        Person person = new Person("Петров", "Владимир", "Юрьевич", "11.06.2004");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(person);
        Person result = mapper.readValue(json,Person.class);

        assertEquals(person.toString(),result.toString());
        assertEquals(json1,json);
    }

    @Test
    public void PersonDeserializeTestInvalidJson() throws IOException {
        String json = "invalid json";

        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(json);

        assertThrows(Exception.class, () -> new PersonDeserializer().deserialize(parser, null));
    }


    @Test public void testFlatSerialize() throws IOException {

        Person p1 = new Person("Петров", "Владимир", "Юрьевич", "11.06.2004");
        Person p2 = new Person("Иванов", "Иван", "Иванович", "12.07.2014");
        List<Person> owners = new ArrayList<>();
        owners.add(p1);
        owners.add(p2);
        Flat flat = new Flat(100, 5, owners);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(flat);
        Flat result = mapper.readValue(json,Flat.class);
        assertEquals(flat.toString(),result.toString());
        System.out.println(json);
    }



    @Test
    void flatDeserializeEmptyOwners() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"number\":101, \"area\": 100, \"owners\": [] }";
        Flat fl = objectMapper.readValue(json,Flat.class);
        Flat expectedFlat = new Flat(101, 100, new ArrayList<Person>());
        assertEquals(expectedFlat.toString(),fl.toString());

    }

    @Test
    public void testSerializeHouse() throws IOException {
        List<Flat> flats = new ArrayList<>();
        Person p1 = new Person("Петров", "Владимир", "Юрьевич", "11.06.2004");
        Person p2 = new Person("Иванов", "Иван", "Иванович", "12.07.2014");
        Person holder = new Person("Ашаев", "Игорь", "Викторович", "12.12.1974");
        List<Person> owners = new ArrayList<>();
        List<Person> owners2 = new ArrayList<>();
        owners.add(p1);
        owners2.add(p2);
        Flat flat = new Flat(100, 5, owners);
        Flat flat2 = new Flat(50,340,owners2);
        flats.add(flat);
        flats.add(flat2);
        House house = new House("123456789", "Test Address", holder, flats);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(house);
        House result = mapper.readValue(json,House.class);
        assertEquals(house.toString(),result.toString());
    }




    }