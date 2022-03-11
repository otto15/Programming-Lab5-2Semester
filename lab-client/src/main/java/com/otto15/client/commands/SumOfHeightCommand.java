package com.otto15.client.commands;

import com.otto15.client.config.Configurator;

public class SumOfHeightCommand extends AbstractCommand {

    public SumOfHeightCommand() {
        super("sum_of_height", "outputs the sum of person's heights", 0);
    }

    @Override
    public boolean execute(String[] args) {
        System.out.println(CommandManager.getCollectionManager().getSumOfHeights());
        return true;
    }
}
