package com.otto15.client.commands;

import com.otto15.client.config.Configurator;
import com.otto15.client.controllers.CommandManager;

import java.io.IOException;

public class SaveCommand extends AbstractCommand {

    public SaveCommand() {
        super("save", "saves collection to the file", 0);
    }

    @Override
    public boolean execute(String[] args) {
        try {
            Configurator.COLLECTION_FILE_WRITER.write(Configurator.getOutputFile(), CommandManager.getCollectionManager());
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
