package com.otto15.client.commands;

import com.otto15.client.config.Configurator;
import com.otto15.client.entities.Person;
import com.otto15.client.entities.PersonLoader;
import com.otto15.client.entities.PersonSet;
import com.otto15.client.io.CollectionFileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Stores all the commands' implementations.
 * @author Rakhmatullin R.
 */
public class CommandHub {
    private static final int HISTORY_LENGTH = 10;
    private static PersonSet personSet = Configurator.getPersons();
    private static CollectionFileWriter<PersonSet> collectionFileWriter = Configurator.COLLECTION_FILE_WRITER;
    private static File outputFile = Configurator.getOutputFile();
    private Reader reader;
    private Queue<String> history = new LinkedList<>();

    public CommandHub(Reader reader) {
        this.reader = reader;
    }


    // TODO create commands

    @Command(name = "add",
            inlineArgsCount = 0,
            description = "adds element to collection")
    public void add() {
        try {
            personSet.add(PersonLoader.loadPerson(reader));
            System.out.println("New person successfully created!");
        } catch (IOException e) {
            System.out.println("Input error.");
        }
    }

    @Command(name = "help",
            inlineArgsCount = 0,
            description = "shows the list of commands")
    public void help() {
        for (Method method : CommandHub.class.getDeclaredMethods()) {
            Command commandMetaData = method.getAnnotation(Command.class);
            if (commandMetaData != null) {
                System.out.println(commandMetaData.name() + " - " + commandMetaData.description() + ", implies " + commandMetaData.inlineArgsCount() + " argument(s)");
            }
        }
    }

    @Command(name = "info",
            inlineArgsCount = 0,
            description = "shows the info about collection")
    public void info() {
        System.out.println("Collection info:");
        System.out.println(personSet.getPersons().getClass());
        System.out.println(personSet.getCreationDate());
        System.out.println(personSet.getPersons().size());
    }

    @Command(name = "show",
            inlineArgsCount = 0,
            description = "outputs all collection elements")
    public void show() {
        personSet.show();
    }

    @Command(name = "exit",
            inlineArgsCount = 0,
            description = "closes the app")
    public void exit() {
        System.out.print("Shut down.");
        System.exit(0);
    }

    @Command(name = "clear",
            inlineArgsCount = 0,
            description = "deletes all collection elements")
    public void clear() {
        personSet.clear();
    }

    @Command(name = "update",
            inlineArgsCount = 1,
            description = "updates person value")
    public void update(String arg) {
        try {
            Person person = personSet.findById(Long.parseLong(arg));
            person.setName(
                    PersonLoader.loadName(reader, person.getName()));
            person.setCoordinates(
                    PersonLoader.loadCoordinates(reader, person.getCoordinates().toString()));
            person.setHeight(
                    PersonLoader.loadHeight(reader, Long.toString(person.getHeight())));
            person.setEyeColor(
                    PersonLoader.loadEyeColor(reader, person.getEyeColor().name()));
            person.setHairColor(
                    PersonLoader.loadHairColor(reader, person.getHairColor().name()));
            person.setNationality(
                    PersonLoader.loadNationality(reader, person.getNationality().name()));
            person.setLocation(
                    PersonLoader.loadLocation(reader, person.getLocation().toString()));
            System.out.println("Person successfully updated!");
        } catch (IOException e) {
            System.out.println("Input error.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid format of id.");
        }
    }

    @Command(name = "sum_of_height",
            inlineArgsCount = 0,
            description = "outputs the sum of person's heights")
    public void sumOfHeight() {
        System.out.println(personSet.getSumOfHeights());
    }

    @Command(name = "remove_any_by_height",
            inlineArgsCount = 1,
            description = "removes collection element by height")
    public void removeAnyByHeight(String arg) {
        try {
            Person deletedPerson = personSet.removeAnyByHeight(Long.parseLong(arg));
            if (deletedPerson == null) {
                System.out.println("No person found with such height.");
            } else {
                System.out.println("Person[" + deletedPerson.getId() + "] " + deletedPerson.getName() + " was removed.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid format of height.");
        }
    }

    @Command(name = "remove_by_id",
            inlineArgsCount = 1,
            description = "removes collection element by id")
    public void removeById(String id) {
        try {
            personSet.getPersons().remove(personSet.findById(Long.parseLong(id)));
        } catch (NumberFormatException e) {
            System.out.println("Invalid format of id.");
        }
    }

    @Command(name = "save",
            inlineArgsCount = 0,
            description = "saves collection to the file")
    public void save() {
        try {
            collectionFileWriter.write(outputFile, personSet);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    @Command(name = "add_if_min",
            inlineArgsCount = 0,
            description = "adds new person if minimal value")
    public void addIfMin() {
        try {
            Person newPerson = PersonLoader.loadPerson(reader);
            if (newPerson.compareTo(Collections.min(personSet.getPersons())) < 0) {
                personSet.add(newPerson);
                System.out.println("New person successfully created!");
            } else {
                System.out.println("Given person is not minimal.");
            }
        } catch (IOException e) {
            System.out.println("Input error.");
        }
    }

    @Command(name = "remove_greater",
            inlineArgsCount = 0,
            description = "remove all elements greater than given"
    )
    public void removeGreater() {
        try {
            Person comparedPerson = PersonLoader.loadPerson(reader);
            personSet.getPersons().removeIf(person -> person.compareTo(comparedPerson) > 0);
        } catch (IOException e) {
            System.out.println("Input error.");
        }
    }

    @Command(name = "history",
            inlineArgsCount = 0,
            description = "outputs last 10 commands"
    )
    public void outputHistory() {
        if (history.size() == 0) {
            System.out.println("History is empty.");
        } else {
            history.forEach(command -> System.out.println(command));
        }
    }

    public void addCommandToHistory(String commandName) {
        history.add(commandName);
        if (history.size() > HISTORY_LENGTH) {
            history.poll();
        }
    }

    @Command(name = "group_counting_by_height",
            inlineArgsCount = 0,
            description = "outputs the number of group members"
    )
    public void groupCountingByHeight() {
        personSet.makeGroupsByHeight();
        personSet.outputGroupsByHeight();
    }

    @Command(name = "execute_script",
            inlineArgsCount = 0,
            description = "executes the script with commands")
    public void executeScript(String fileName) {
        try {
            CommandListener listenerFromFile = new CommandListener(new FileReader(fileName));
            listenerFromFile.run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
