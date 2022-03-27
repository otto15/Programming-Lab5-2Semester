package com.otto15.client.commands;

import com.otto15.client.config.Configurator;

public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "closes the app", 0);
    }

    @Override
    public boolean execute(String[] args) {
        Configurator.switchPerformanceStatus();
        return true;
    }
}
