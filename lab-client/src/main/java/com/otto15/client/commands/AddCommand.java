package com.otto15.client.commands;

import com.otto15.client.controllers.CommandManager;
import com.otto15.client.entities.Person;
import com.otto15.client.entities.PersonLoader;

import java.io.IOException;


/**
 * Command for adding new elements to collection
 */
public class AddCommand extends AbstractCommand {

    public AddCommand() {
        super("add", "adds element to collection", 0);
    }

    @Override
    public boolean execute(String[] args) {
        try {
            Person personToAdd = PersonLoader.loadPerson(getReader());
            CommandManager.getCollectionManager().add(personToAdd);
            System.out.println("New person successfully created!");
            return true;
        } catch (IOException e) {
            System.out.println("Input error.");
        }
        return false;
    }
}
