package org.example.phoneBook;

import org.example.human_student.Human;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private  Map<Human, List<String>> phoneBook = new HashMap<>();

    public void addPhone(Human human, String phoneNumber) {
        List<String> phoneList = phoneBook.get(human);
        if (phoneList == null) {
            phoneList = new ArrayList<>();
            phoneBook.put(human, phoneList);
        }
        phoneList.add(phoneNumber);
    }

    public void removePhone(Human human, String phoneNumber) {
        List<String> phoneList = phoneBook.get(human);
        if (phoneList != null) {
            phoneList.remove(phoneNumber);
        }
    }

    public List<String> getPhoneList(Human human) {
        return phoneBook.get(human);
    }

    public Human findPersonByPhone(String phoneNumber) {
        for (Map.Entry<Human, List<String>> entry : phoneBook.entrySet()) {
            if (entry.getValue().contains(phoneNumber)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Map<Human, List<String>> findPeopleByName(String surname) {
        Map<Human, List<String>> result = new HashMap<>();
        for (Map.Entry<Human, List<String>> entry : phoneBook.entrySet()) {
            Human human = entry.getKey();
            if (human.getSurname().startsWith(surname)) {
                result.put(human, entry.getValue());
            }
        }
        return result;
    }

}