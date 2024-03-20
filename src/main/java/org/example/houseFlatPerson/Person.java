package org.example.houseFlatPerson;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {
    private String surname;
    private String name;
    private String patronymic;
    private String birth;

    public Person(String surname, String name, String patronymic, String birth) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birth = birth;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getFullName() {
        return surname +" "+ name +" " + patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return surname == person.surname && name == person.name && patronymic == person.patronymic && Objects.equals(birth, person.birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, patronymic, birth);
    }

    @Override
    public String toString() {
        return "Person{" +
                "surname=" + surname +
                ", name=" + name +
                ", patronymic=" + patronymic +
                ", birth='" + birth + '\'' +
                '}';
    }

}
