package org.example.human_student;

import java.util.*;
import java.util.stream.Collectors;

public class ListDemo {
    public static List<Human> findSameSurname(List<Human> humans, Human person) {
        List<Human> sameSurnameArr = new ArrayList<>();
        for (Human human : humans) {
            if (person.getSurname().equals(human.getSurname())) {
                sameSurnameArr.add(human);
            }
        }
        return sameSurnameArr;
    }

    public static List<Human> removePerson(List<Human> humans, Human person) {
        List<Human> withoutPerson = new ArrayList<>();
        for (Human human : humans) {
            if (!person.equals(human)) {
                withoutPerson.add(human);
            }
        }
        return withoutPerson;
    }

    public static List<Set<Integer>> findNonIntersectingSets(List<Set<Integer>> sets, Set<Integer> set) {
        List<Set<Integer>> nonIntersectingSets = new ArrayList<>();
        for (Set<Integer> s : sets) {
            if (s.stream().noneMatch(set::contains)) {
                nonIntersectingSets.add(s);
            }
        }
        return nonIntersectingSets;
    }

    public static Set<Human> findMaxAge(List<? extends Human> humans) {
        Set<Human> humanSet = new HashSet<>();
        int maxAge = -1;
        for (Human person : humans) {
            if (person.getAge() > maxAge) {
                maxAge = person.getAge();
                humanSet.clear();
                humanSet.add(person);
            } else if (person.getAge() == maxAge) {
                humanSet.add(person);
            }
        }
        return humanSet;
    }

    //task6
    public static List<Human> buildSortedlist(Set<? extends Human> humans) {
        Set<Human> sortedSet = new TreeSet<>(new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                int compareNames = o1.getFullName().compareTo(o2.getFullName());
                if (compareNames != 0) {
                    return compareNames;
                } else {
                    return o1.getAge() - (o2.getAge());
                }
            }
        });
        sortedSet.addAll(humans);
        return new ArrayList<>(sortedSet);
    }


    //task7
    public static Set<Human> getHumansByIds(Map<Integer, Human> humanMap, Set<Integer> ids) {
        Set<Human> result = new HashSet<>();
        for (Integer id : ids) {
            if (humanMap.containsKey(id)) {
                result.add(humanMap.get(id));
            }
        }
        return result;
    }


    //task8
    public static List<Integer> getIdsOfMature(Map<Integer, Human> humanMap) {
        List<Integer> result = new ArrayList<>();

        for (Map.Entry<Integer, Human> entry : humanMap.entrySet()) {
            int id = entry.getKey();
            Human human = entry.getValue();

            if (human.getAge() >= 18) {
                result.add(id);
            }
        }
        return result;
    }

    //task9
    public static Map<Integer, Integer> getAgeToHumansMap(Map<Integer, Human> humanSet) {
        Map<Integer, Integer> result = new HashMap<>();

        for (Map.Entry<Integer, Human> entry : humanSet.entrySet()) {
            Integer id = entry.getKey();
            Human human = entry.getValue();

            result.put(id, human.getAge());
        }
        return result;
    }

    //task 10
    public static Map<Integer, List<Human>> buildAgeMap(Set<Human> humans) {
        Map<Integer, List<Human>> result = new HashMap<>();

        for (Human human : humans) {
            if (!result.containsKey(human.getAge())) {
                result.put(human.getAge(), new ArrayList<>());
            }
            result.get(human.getAge()).add(human);
        }
        return result;
    }


    //task11
    public static Map<Integer, Map<Character, List<Human>>> buildAgeLetterMap(Set<Human> humans) {
        Map<Integer, Map<Character, List<Human>>> ageLetterMap = new HashMap<>();

        for (Human human : humans) {
            int age = human.getAge();
            char firstLetter = human.getSurname().charAt(0);
            Map<Character, List<Human>> ageList = ageLetterMap.get(age);

            if (ageList == null) {
                ageList = new HashMap<>();
                ageLetterMap.put(age, ageList);
            }

            List<Human> letterList = ageList.get(firstLetter);

            if (letterList == null) {
                letterList = new ArrayList<>();
                ageList.put(firstLetter, letterList);
            }

            letterList.add(human);
        }

        for (Map.Entry<Integer, Map<Character, List<Human>>> ageEntry : ageLetterMap.entrySet()) {
            Map<Character, List<Human>> letterMap = ageEntry.getValue();
            for (Map.Entry<Character, List<Human>> letterEntry : letterMap.entrySet()) {
                List<Human> letterList = letterEntry.getValue();
                letterList.sort(Comparator.comparing(Human::getFullName, Comparator.reverseOrder()));
            }
        }
        return ageLetterMap;
    }
}
