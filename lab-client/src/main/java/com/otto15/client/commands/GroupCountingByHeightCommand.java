package com.otto15.client.commands;

import com.otto15.client.config.Configurator;

public class GroupCountingByHeightCommand extends AbstractCommand {

    public GroupCountingByHeightCommand() {
        super("group_counting_by_height", "outputs the number of group members", 0);
    }

    @Override
    public boolean execute(String[] args) {
        CommandManager.getCollectionManager().makeGroupsByHeight();
        CommandManager.getCollectionManager().outputGroupsByHeight();
        return true;
    }
}
