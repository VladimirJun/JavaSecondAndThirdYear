package org.example.houseFlatPerson;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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