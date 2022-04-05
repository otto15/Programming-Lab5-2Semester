package com.otto15.client.commands;


import com.otto15.client.controllers.CommandManager;

/**
 * Command for clearing the collection
 */
public class ClearCommand extends AbstractCommand {

    public ClearCommand() {
        super("clear", "deletes all collection elements", 0);
    }

    @Override
    public boolean execute(String[] args) {
        CommandManager.getCollectionManager().clear();
        return true;
    }

}
