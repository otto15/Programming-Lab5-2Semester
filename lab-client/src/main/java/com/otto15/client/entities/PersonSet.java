package com.otto15.client.entities;

import com.otto15.client.io.CollectionFileReader;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class PersonSet {
    private final HashSet<Person> persons;
    private ZonedDateTime creationDate;
    private HashMap<Long, List<Person>> groupsByHeight;

    public PersonSet() {
        persons = new HashSet<>();

    }

    public HashSet<Person> getPersons() {
        return persons;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate() {
        creationDate = ZonedDateTime.now();
    }

    public static PersonSet initFromFile(CollectionFileReader<PersonSet> collectionFileReader, File file) throws IOException {
        PersonSet personSet = collectionFileReader.read(file);
        personSet.setCreationDate();
        personSet.updateMaxPersonId();
        return personSet;
    }

    public void add(Person newPerson) {
        persons.add(newPerson);
    }

    public void clear() {
        persons.clear();
    }

    public void show() {
        if (persons.size() > 0) {
            for (Person person : persons) {
                System.out.println(person);
            }
        } else {
            System.out.println("Collection is empty.");
        }
    }

    public Person findById(Long id) {
        for (Person person : persons) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        throw new IllegalArgumentException("No person with such id.");
    }

    public void updateMaxPersonId() {
        persons.forEach(person -> Person.setPreviousId(Math.max(Person.getPreviousId(), person.getId())));
    }

    public long getSumOfHeights() {
        long sumOfHeights = 0;
        for (Person singlePerson : persons) {
            sumOfHeights += singlePerson.getHeight();
        }
        return sumOfHeights;
    }

    public Person removeAnyByHeight(long height) {
        Person deletedPerson = null;
        for (Person singlePerson : persons) {
            if (singlePerson.getHeight() == height) {
                deletedPerson = singlePerson;
                persons.remove(singlePerson);
                break;
            }
        }
        return deletedPerson;
    }

    public void makeGroupsByHeight() {
        groupsByHeight = new HashMap<>();
        for (Person person : persons) {
            if (groupsByHeight.containsKey(person.getHeight())) {
                groupsByHeight.get(person.getHeight()).add(person);
            } else {
                groupsByHeight.put(person.getHeight(), new ArrayList<>(Arrays.asList(person)));
            }
        }
    }

    public void outputGroupsByHeight() {
        for (Map.Entry<Long, List<Person>> group : groupsByHeight.entrySet()) {
            System.out.println("Height - " + group.getKey() + ": " + group.getValue().size() + " members.");
        }
    }
}
