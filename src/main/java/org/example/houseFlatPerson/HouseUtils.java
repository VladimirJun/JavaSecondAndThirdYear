package org.example.houseFlatPerson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.houseFlatPerson.Flat;
import org.example.houseFlatPerson.House;

import java.io.*;
//task6
public class HouseUtils {
        public static void serialize(House house, OutputStream outputStream) throws IOException {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
                objectOutputStream.writeObject(house);

            }
        }

        public static House deserialize(InputStream inputStream) throws IOException, ClassNotFoundException {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
                return (House) objectInputStream.readObject();
            }
        }
        //task7
    public static void writeHouseToCsv(House house, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("house_"+house.getCadastralNumber()+"_");
            writer.newLine();
            for (Flat flat : house.getFlats()) {
                writer.write(house.getCadastralNumber());
                writer.write(';');
                writer.write(house.getAddress());
                writer.write(';');
                writer.write(house.getHouseHolder().toString());
                writer.write(';');
                writer.write(String.valueOf(flat.getNumber()));
                writer.write(';');
                writer.write(String.valueOf(flat.getArea()));
                writer.write(';');
                writer.write(flat.getOwners().toString());
                writer.newLine();
            }
        }
    }
        //task8*
    public static void serializeJackson(House house) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(house);

        System.out.println(json);
    }

    public static House deserializeJackson(House house) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(house.toString(), House.class);
    }
    //task 9
    public static boolean compareJsonStrings(String json1, String json2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node1 = objectMapper.readTree(json1);
        JsonNode node2 = objectMapper.readTree(json2);

        return node1.equals(node2);
    }
}
