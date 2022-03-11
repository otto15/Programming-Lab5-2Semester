package com.otto15.client.entities;

import com.otto15.client.entities.validators.EntityValidator;
import com.otto15.client.io.CollectionFileReader;
import com.thoughtworks.xstream.converters.ConversionException;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Wrapper for HashSet to store the additional info.
 *
 * @author Rakhmatullin R.
 */
public class PersonCollectionManager {
    private HashSet<Person> persons;
    private ZonedDateTime creationDate = ZonedDateTime.now();
    private Map<Long, List<Person>> groupsByHeight;

    public PersonCollectionManager() {
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

    public static PersonCollectionManager initFromFile(CollectionFileReader<PersonCollectionManager> collectionFileReader, File file) throws IOException {
        try {
            PersonCollectionManager personCollectionManager = collectionFileReader.read(file);
            personCollectionManager.setCreationDate();
            if (personCollectionManager.persons == null) {
                personCollectionManager.persons = new HashSet<>();
            }
            personCollectionManager.setup();
            return personCollectionManager;
        } catch (IllegalArgumentException | ConversionException e) {
            throw new IOException("Objects in file are invalid.");
        }
    }

    public void add(Person newPerson) {
        persons.add(newPerson);
    }

    public void clear() {
        persons.clear();
    }

    public void show() {
        if (!persons.isEmpty()) {
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
        if (groupsByHeight.isEmpty()) {
            System.out.println("Collection is empty");
        } else {
            for (Map.Entry<Long, List<Person>> group : groupsByHeight.entrySet()) {
                System.out.println("Height - " + group.getKey() + ": " + group.getValue().size() + " members.");
            }
        }
    }

    private void setup() {
        for (Person personInQuestion : persons) {
            if (EntityValidator.isEntityValid(personInQuestion)) {
                personInQuestion.setId();
            } else {
                throw new IllegalArgumentException();
            }
        }
    }
}
