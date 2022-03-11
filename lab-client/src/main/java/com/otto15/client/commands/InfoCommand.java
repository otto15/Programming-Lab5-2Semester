package com.otto15.client.commands;

import com.otto15.client.config.Configurator;
import java.time.format.DateTimeFormatter;

public class InfoCommand extends AbstractCommand {

    public InfoCommand() {
        super("info", "shows the info about collection", 0);
    }

    @Override
    public boolean execute(String[] args) {
        System.out.println("Collection info:");
        System.out.println(CommandManager.getCollectionManager().getPersons().getClass().getName());
        System.out.println(CommandManager.getCollectionManager().getCreationDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm")));
        System.out.println(CommandManager.getCollectionManager().getPersons().size() + " person(s) in collection.");
        return true;
    }
}
