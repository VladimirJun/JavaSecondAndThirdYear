package org.example.houseFlatPerson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.example.Task10.HouseDeserializer;
import org.example.Task10.HouseSerializer;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonSerialize(using = HouseSerializer.class)
@JsonDeserialize(using = HouseDeserializer.class)
public class House implements Serializable {

    private String cadastralNumber;
    private String address;
    private Person houseHolder;
    private List<Flat> flats;

    public House() {
    }

    public House(String cadastralNumber, String address, Person houseHolder, List<Flat> flats) {
        this.cadastralNumber = cadastralNumber;
        this.address = address;
        this.houseHolder = houseHolder;
        this.flats = flats;
    }

    public String getCadastralNumber() {
        return cadastralNumber;
    }

    public void setCadastralNumber(String cadastralNumber) {
        this.cadastralNumber = cadastralNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Person getHouseHolder() {
        return houseHolder;
    }

    public void setHouseHolder(Person houseHolder) {
        this.houseHolder = houseHolder;
    }

    public List<Flat> getFlats() {
        return flats;
    }

    public void setFlats(List<Flat> flats) {
        this.flats = flats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(cadastralNumber, house.cadastralNumber) && Objects.equals(address, house.address) && Objects.equals(houseHolder, house.houseHolder) && Objects.equals(flats, house.flats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cadastralNumber, address, houseHolder, flats);
    }

    @Override
    public String toString() {
        return "House{" +
                "cadastralNumber='" + cadastralNumber + '\'' +
                ", address='" + address + '\'' +
                ", houseHolder=" + houseHolder +
                ", flats=" + flats +
                '}';
    }

    //task8*
    public static String serializeJackson(House house) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(house);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static House deserializeJackson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, House.class);
    }
}
