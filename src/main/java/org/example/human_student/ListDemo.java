package org.example.human_student;

import java.util.*;
import java.util.stream.Collectors;

public class ListDemo{
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
    public static List<Human> buildSortedlist(List<? extends Human> humans) {
        List<Human> copy = new ArrayList<>(humans);
        copy.sort(new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        return copy;
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
    public static Map<Integer, List<Human>> getAgeToHumansMap(Set<Human> humanSet) {
        Map<Integer, List<Human>> result = new HashMap<>();

        for (Human human : humanSet) {

            if (!result.containsKey(human.getAge())) {
                result.put(human.getAge(), new ArrayList<>());
            }
            result.get(human.getAge()).add(human);
        }
        return result;
    }

    //task 10
    public static Map<Integer, List<Human>> buildAgeMap(List<Human> humans) {
        Map<Integer, List<Human>> ageMap = new HashMap<>();

        for (Human human : humans) {
            int age = human.getAge();
            List<Human> ageList = ageMap.get(age);

            if (ageList == null) {
                ageList = new ArrayList<>();
                ageMap.put(age, ageList);
            }

            ageList.add(human);
        }
        return ageMap;
    }

    //task11
    public static Map<Integer, Map<Character, List<Human>>> buildAgeLetterMap(List<Human> humans) {
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
                letterList.sort(new Comparator<Human>() {
                    @Override
                    public int compare(Human h1, Human h2) {
                        int result = h1.getSurname().compareTo(h2.getSurname());
                        if (result == 0) {
                            result = h1.getName().compareTo(h2.getName());
                            if (result == 0) {
                                result = h1.getPatronymic().compareTo(h2.getPatronymic());
                            }
                        }
                        return result;
                    }
                });
            }
        }
        return ageLetterMap;
    }



}
