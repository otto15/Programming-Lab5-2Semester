package com.otto15.client.commands;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ExecuteScriptCommand extends AbstractCommand {

    private static Set<String> fileHistory = new HashSet<>();

    public ExecuteScriptCommand() {
        super("execute_script", "executes the script with commands", 1);
    }

    @Override
    public boolean execute(String[] args) {
        String fileName = args[0];
        if (fileHistory.contains(fileName)) {
            System.out.println("There is a problem: script will loop.");
        } else {
            fileHistory.add(fileName);
            try {
                CommandListener listenerFromFile = new CommandListener(new FileReader(fileName));
                listenerFromFile.run();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            fileHistory.remove(fileName);
        }
        return true;
    }
}
