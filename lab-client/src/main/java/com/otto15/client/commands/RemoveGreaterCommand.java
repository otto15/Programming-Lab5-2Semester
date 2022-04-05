package com.otto15.client.commands;

import com.otto15.client.controllers.CommandManager;
import com.otto15.client.entities.Person;
import com.otto15.client.entities.PersonLoader;

import java.io.IOException;

public class RemoveGreaterCommand extends AbstractCommand {

    public RemoveGreaterCommand() {
        super("remove_greater", "remove all elements greater than given", 0);
    }

    @Override
    public boolean execute(String[] args) {
        try {
            Person comparedPerson = PersonLoader.loadPerson(getReader());
            CommandManager.getCollectionManager().getPersons().removeIf(person -> person.compareTo(comparedPerson) > 0);
            return true;
        } catch (IOException e) {
            System.out.println("Input error.");
        }
        return false;
    }
}
