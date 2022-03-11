package com.otto15.client.commands;

import com.otto15.client.config.Configurator;

public class ShowCommand extends AbstractCommand {

    public ShowCommand() {
        super("show", "outputs all collection elements", 0);
    }

    @Override
    public boolean execute(String[] args) {
        CommandManager.getCollectionManager().show();
        return true;
    }

}
