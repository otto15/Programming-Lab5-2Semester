package com.otto15.client.commands;

import java.io.InputStreamReader;
import java.io.Reader;


/**
 * Abstract class for commands.
 */
public abstract class AbstractCommand {

    private final String name;
    private final String description;
    private final int inlineArgsCount;
    private final Reader reader = new InputStreamReader(System.in);

    public AbstractCommand(String name, String description, int inlineArgsCount) {
        this.name = name;
        this.description = description;
        this.inlineArgsCount = inlineArgsCount;
    }

    /**
     * Execution method for all commands.
     * @param args
     * @return true - if execution completed successfully, false - if not
     */
    public abstract boolean execute(String[] args);

    public Reader getReader() {
        return reader;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getInlineArgsCount() {
        return inlineArgsCount;
    }
}
