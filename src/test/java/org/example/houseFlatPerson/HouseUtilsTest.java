package org.example.houseFlatPerson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.example.Task10.HouseSerializer;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HouseUtilsTest {
    //task6
    @Test
    public void serialize() throws IOException {
        House house = new House("12345", "123 Main St", new Person("Петров", "Владимир", "Юрьевич", "11.06.2004"), new ArrayList<>());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HouseUtils.serialize(house, outputStream);
        // Проверка, что outputStream содержит данные
        assertTrue(outputStream.toByteArray().length > 0);
    }

    @Test
    public void testDeserialize() throws IOException, ClassNotFoundException {

        House expectedHouse = new House("12345", "123 Main St", new Person("Петров", "Владимир", "Юрьевич", "11.06.2004"), new ArrayList<>());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HouseUtils.serialize(expectedHouse, outputStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        House deserializedHouse = HouseUtils.deserialize(inputStream);
        assertEquals(expectedHouse.toString(), deserializedHouse.toString());
    }

    //task7
    @Test
    public void testWriteHouseToCsv() {
        List<Person> owners1 = new ArrayList<>();
        Person p1 = new Person("Иванов", "Иван", "Иванович", "01.01.01");
        owners1.add(p1);
        List<Person> owners2 = new ArrayList<>();
        Person p2 = new Person("Сидоров", "Иван", "Иванович", "01.01.01");
        owners2.add(p2);
        Flat flat1 = new Flat(1, 100, owners1);
        Flat flat2 = new Flat(2, 98, owners2);
        List<Flat> flats = new ArrayList<>();
        flats.add(flat1);
        flats.add(flat2);
        House house = new House("12345", "123 Main St", new Person("Петров", "Владимир", "Юрьевич", "11.06.2004"), flats);

        try {
            HouseUtils.writeHouseToCsv(house);
        } finally {

        }
    }

    @Test
    public void testWriteHouseToCsv2() {
        House house = new House("86422", "123 Main St", new Person("Петров", "Владимир", "Юрьевич", "11.06.2004"), new ArrayList<>());
        HouseUtils.writeHouseToCsv(house);
    }
//    @Test
//    public void testSerializeJackson() throws IOException {
//
//        House house = new House("123456789", "Test Address",new Person("Петров", "Владимир", "Юрьевич", "11.06.2004") , new ArrayList<>());
//        System.out.println(House.serializeJackson(house));
//        assertEquals(House.serializeJackson(house),"{\"cadastralNumber\":\"123456789\",\"address\":\"Test Address\",\"houseHolder\":{\"fullName\":\"Петров Владимир Юрьевич\",\"birth\":\"11.06.2004\"},\"flats\":[]}");
//
//    }
    @Test
    public void testDeserializeHouse() throws IOException {
        String json = "{\"cadastralNumber\":\"1\",\"address\":\"House1\",\"houseHolder\":{\"surname\":\"Петров\",\"name\":\"Владимир\",\"patronymic\":\"Юрьевич\",\"birth\":\"11.06.2004\"},\"flats\":[]}";
        House h1 = House.deserializeJackson(json);
        House expected = new House("1","House1", new Person("Петров","Владимир","Юрьевич","11.06.2004"), new ArrayList<Flat>());
        assertEquals(h1.toString(),expected.toString());
    }
//task9
    @Test
    public void testCompareJsonStrings_SameJson() throws IOException {
        String json1 = "{\"id\": 1, \"name\": \"House 1\"}";
        String json2 = "{\"id\": 1, \"name\": \"House 1\"}";

        assertTrue(HouseUtils.compareJsonStrings(json1, json2));
    }

    @Test
    public void testCompareJsonStrings_DifferentJson() throws IOException {
        String json1 = "{\"id\": 1, \"name\": \"House 1\"}";
        String json2 = "{\"id\": 2, \"name\": \"House 2\"}";

        assertFalse(HouseUtils.compareJsonStrings(json1, json2));
    }

    @Test
    public void testCompareJsonStrings_InvalidJson() throws IOException {
        String json1 = "{\"id\": 1, \"name\": \"House 1\"}";
        String json2 = "invalid json";

        try {
            HouseUtils.compareJsonStrings(json1, json2);
            fail("Expected IOException was not thrown.");
        } catch (IOException e) {
            // Expected IOException
        }
    }


}