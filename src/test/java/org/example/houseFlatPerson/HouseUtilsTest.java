package org.example.houseFlatPerson;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
        Flat flat3 = new Flat(3, 43, Collections.singletonList(new Person("Abramov","Lev","Lvovich","11.11.2011")));
        Flat flat4 = new Flat(4, 36, Collections.singletonList(new Person("Berezin","Andrey","Andreevich","11.11.2000")));
        Flat flat5 = new Flat(5, 78, Collections.singletonList(new Person("Volchek","Ivan","Ilich","01.11.2023")));
        Flat flat6 = new Flat(6, 54, Collections.singletonList(new Person("Goman","Nikita","Lvovich","05.06.2001")));
        Flat flat7 = new Flat(7, 99, Collections.singletonList(new Person("Dimik","Dima","Dimovich","11.11.1997")));
        Flat flat8 = new Flat(8, 120, Collections.singletonList(new Person("Evtyagin","Anatoly","Leonidovich","11.11.1991")));
        Flat flat9 = new Flat(9, 300, Collections.singletonList(new Person("Zablotsky","Danya","Danich","08.05.2003")));
        Flat flat10 = new Flat(10, 57, Collections.singletonList(new Person("Nazarov","Oleg","Sergeevich","08.08.2004")));
        Flat flat11 = new Flat(11, 104, Collections.singletonList(new Person("Meshkov","Danil","Sergeevich","13.10.2004")));

        List<Flat> flats = new ArrayList<>();
        flats.add(flat1);
        flats.add(flat2);
        flats.add(flat3);
        flats.add(flat4);
        flats.add(flat5);
        flats.add(flat6);
        flats.add(flat7);
        flats.add(flat8);
        flats.add(flat9);
        flats.add(flat10);
        flats.add(flat11);

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

    @Test
    public void testSerializeJackson() throws IOException {

        House house = new House("123456789", "Test Address", new Person("Петров", "Владимир", "Юрьевич", "11.06.2004"), new ArrayList<>());
        System.out.println(House.serializeJackson(house));
        assertEquals(House.serializeJackson(house), "{\"cadastralNumber\":\"123456789\",\"address\":\"Test Address\",\"houseHolder\":{\"fullName\":\"Петров Владимир Юрьевич\",\"birth\":\"11.06.2004\"},\"flats\":[]}");
        String serializedHouse = House.serializeJackson(house);
        System.out.println("Serialized house: " + serializedHouse);
    }

    @Test
    public void testDeserializeHouse() throws IOException {
        String json = "{\"cadastralNumber\":\"1\",\"address\":\"House1\",\"houseHolder\":{\"surname\":\"Петров\",\"name\":\"Владимир\",\"patronymic\":\"Юрьевич\",\"birth\":\"11.06.2004\"},\"flats\":[]}";
        House h1 = House.deserializeJackson(json);
        House expected = new House("1", "House1", new Person("Петров", "Владимир", "Юрьевич", "11.06.2004"), new ArrayList<Flat>());
        assertEquals(h1.toString(), expected.toString());
    }

    //task9
    @Test
    public void testCompareJsonStrings_DifferentJson() throws IOException {
        String json1 = "{\"cadastralNumber\":\"1\"," +
                "\"address\":\"House1\"," +
                "\"houseHolder\":{" +
                "\"surname\":\"Петров\"," +
                "\"name\":\"Владимир\"," +
                "\"patronymic\":\"Юрьевич\"," +
                "\"birth\":\"11.06.2004\"" +
                "}," +
                "\"flats\":[]}";

        String json2 = "{\"cadastralNumber\":\"1\",\"address\":\"House1\",\"houseHolder\":{\"surname\":\"Петров\",\"name\":\"Владимир\",\"patronymic\":\"Юрьевич\",\"birth\":\"11.06.2004\"},\"flats\":[]}";


        assertTrue(HouseUtils.compareJsonStrings(json1, json2));
    }

    @Test
    public void testCompareJsonStrings_SameJson() throws IOException {
        String json1 =
                "{\"cadastralNumber\":\"1\"," +
                        "\"address\":\"House1\"" +
                        ",\"houseHolder\":{" +
                        "\"surname\":\"Петров\"," +
                        "\"name\":\"Владимир\"," +
                        "\"patronymic\":\"Юрьевич\"," +
                        "\"birth\":\"11.06.2004\"}," +
                        "\"flats\":" +
                        "[]}";

        String json2 =
                "{\"address\":\"House1\"" +
                        ",\"cadastralNumber\":\"1\"" +
                        ",\"houseHolder\":{" +
                        "\"surname\":\"Петров\"," +
                        "\"name\":\"Владимир\"," +
                        "\"patronymic\":\"Юрьевич\"," +
                        "\"birth\":\"11.06.2004\"" +
                        "}," +
                        "\"flats\"" +
                        ":[]" +
                        "}";
        String json3 = "{ \"cadastralNumber\":\"123\", " +
                "\"address\":\"House1\", " +
                "\"houseHolder\":{ " +
                "\"surname\":\"Oleg\", " +
                "\"name\":\"Olegovich\", " +
                "\"patronymic\":\"Olegov\", " +
                "\"birth\":\"22.08.2002\" }," +
                " \"flats\":[" +
                " { \"number\":1, " +
                "\"area\":100, " +
                "\"owners\":[ " +
                "{ \"surname\":\"Petrov\"," +
                " \"name\":\"Vladimir\", " +
                "\"patronymic\":\"Iurevich\"," +
                " \"birth\":\"11.06.2004\" } ] } ] }";

        String json4 = "{ \"cadastralNumber\":\"123\", " +
                "\"address\":\"House1\", " +
                "\"houseHolder\":{ " +
                "\"surname\":\"Oleg\", " +
                "\"name\":\"Olegovich\", " +
                "\"patronymic\":\"Olegov\", " +
                "\"birth\":\"22.08.2002\" }," +
                " \"flats\":[" +
                " { \"number\":1, " +
                "\"area\":100, " +
                "\"owners\":[ " +
                "{ \"name\":\"Vladimir\"," +
                " \"surname\":\"Petrov\", " +
                "\"patronymic\":\"Iurevich\"," +
                " \"birth\":\"11.06.2004\" } ] } ] }";


        assertTrue(HouseUtils.compareJsonStrings(json1, json2));
    }

    @Test
    public void testCompareJsonStrings_SameJsonDiffNodes() throws IOException {
        String json3 = "{ \"cadastralNumber\":\"123\", " +
                "\"address\":\"House1\", " +
                "\"houseHolder\":{ " +
                "\"surname\":\"Oleg\", " +
                "\"name\":\"Olegovich\", " +
                "\"patronymic\":\"Olegov\", " +
                "\"birth\":\"22.08.2002\" }," +
                " \"flats\":[" +
                " { \"number\":1, " +
                "\"area\":100, " +
                "\"owners\":[ " +
                "{ \"surname\":\"Petrov\"," +
                " \"name\":\"Vladimir\", " +
                "\"patronymic\":\"Iurevich\"," +
                " \"birth\":\"11.06.2004\" } ] } ] }";

        String json4 = "{ \"cadastralNumber\":\"123\", " +
                "\"address\":\"House1\", " +
                "\"houseHolder\":{ " +
                "\"surname\":\"Oleg\", " +
                "\"name\":\"Olegovich\", " +
                "\"patronymic\":\"Olegov\", " +
                "\"birth\":\"22.08.2002\" }," +
                " \"flats\":[" +
                " { \"number\":1, " +
                "\"area\":100, " +
                "\"owners\":[ " +
                "{ \"name\":\"Vladimir\"," +
                " \"surname\":\"Petrov\", " +
                "\"patronymic\":\"Iurevich\"," +
                " \"birth\":\"11.06.2004\" } ] } ] }";
        assertTrue(HouseUtils.compareJsonStrings(json3, json4));
    }

    @Test
    public void testCompareJsonStrings_DiffOrderDiffNodes() throws IOException {
        String json3 = "{ \"cadastralNumber\":\"123\", " +
                "\"address\":\"House1\", " +
                "\"houseHolder\":{ " +
                "\"surname\":\"Oleg\", " +
                "\"name\":\"Olegovich\", " +
                "\"patronymic\":\"Olegov\", " +
                "\"birth\":\"22.08.2002\" }," +
                " \"flats\":[" +
                " { \"number\":1, " +
                "\"area\":100, " +
                "\"owners\":[ " +
                "{ \"surname\":\"Petrov\"," +
                " \"name\":\"Vladimir\", " +
                "\"patronymic\":\"Iurevich\"," +
                " \"birth\":\"11.06.22284\" } ] } ] }";

        String json4 = "{ \"cadastralNumber\":\"123\", " +
                "\"address\":\"House1\", " +
                "\"houseHolder\":{ " +
                "\"surname\":\"Oleg\", " +
                "\"name\":\"Olegovich\", " +
                "\"patronymic\":\"Olegov\", " +
                "\"birth\":\"22.08.2002\" }," +
                " \"flats\":[" +
                " { \"number\":1, " +
                "\"area\":100, " +
                "\"owners\":[ " +
                "{ \"name\":\"Vladimir\"," +
                " \"surname\":\"Petrov\", " +
                "\"patronymic\":\"Iurevich\"," +
                " \"birth\":\"11.07.2004\" } ] } ] }";
        assertFalse(HouseUtils.compareJsonStrings(json3, json4));
    }

    @Test
    public void testCompareJsonStrings_InvalidJson() throws IOException {
        String json1 = "{\"id\": 1, \"name\": \"House 1\"}";
        String json2 = "invalid json";

        try {
            HouseUtils.compareJsonStrings(json1, json2);
            fail("Expected IOException was not thrown.");
        } catch (IOException e) {

        }
    }


}