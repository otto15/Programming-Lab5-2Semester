package com.otto15.client.commands;

import com.otto15.client.config.Configurator;
import com.otto15.client.entities.Person;
import com.otto15.client.entities.PersonLoader;

import java.io.IOException;
import java.util.Collections;

public class AddIfMinCommand extends AbstractCommand {

    public AddIfMinCommand() {
        super("add_if_min", "adds new person if minimal value", 0);
    }

    @Override
    public boolean execute(String[] args) {
        try {
            Person newPerson = PersonLoader.loadPerson(getReader());
            if (newPerson.compareTo(Collections.min(CommandManager.getCollectionManager().getPersons())) < 0) {
                CommandManager.getCollectionManager().add(newPerson);
                System.out.println("New person successfully created!");
            } else {
                System.out.println("Given person is not minimal.");
            }
            return true;
        } catch (IOException e) {
            System.out.println("Input error.");
        }
        return false;
    }
}
