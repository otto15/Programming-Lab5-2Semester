package com.otto15.client.commands;

import com.otto15.client.controllers.CommandListener;

/**
 * Command for exit
 */
public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "closes the app", 0);
    }

    @Override
    public boolean execute(String[] args) {
        CommandListener.switchPerformanceStatus();
        return true;
    }
}
