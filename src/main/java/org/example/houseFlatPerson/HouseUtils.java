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
        public static void writeHouseToCsv(House house) {
            String fileName = "house_" + house.getCadastralNumber() + ".csv";
            try (PrintWriter writer = new PrintWriter(new File(fileName))) {
                StringBuilder sb = new StringBuilder();
                sb.append("\tInfo about house\t\n");
                sb.append("Cadastral Number:\t");
                sb.append(house.getCadastralNumber()).append(";\n");
                sb.append("Address:\t");
                sb.append(house.getAddress()).append(";\n");
                sb.append("Householder: \t");
                sb.append(house.getHouseHolder().getFullName());
                sb.append(";\n");
                sb.append("\tFlats\n");
                for (Flat flat : house.getFlats()) {
                    sb.append("Number:\t");
                    sb.append(flat.getNumber()).append("\t");
                    sb.append("Area:\t");
                    sb.append(flat.getArea()).append("\t");
                    sb.append("Owner:\t");
                    sb.append(flat.getOwners()).append("\t");
                    sb.append("\n");
                }
                writer.write(sb.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    }

    //task 9
    public static boolean compareJsonStrings(String json1, String json2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node1 = objectMapper.readTree(json1);
        JsonNode node2 = objectMapper.readTree(json2);

        return node1.equals(node2);
    }
}
