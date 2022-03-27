package com.otto15.client.commands;

import com.otto15.client.config.Configurator;
import com.otto15.client.utils.DataNormalizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

/**
 * Listens to stream for commands
 *
 * @author Rakhmatullin R.
 */
public class CommandListener {

    private final Reader reader;

    public CommandListener() {
        reader = new InputStreamReader(System.in);
    }

    public CommandListener(Reader reader) {
        this.reader = reader;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(reader)) {
            while (Configurator.getPerformanceStatus()) {
                System.out.println("===========================");
                String input = in.readLine();
                if (input == null) {
                    break;
                }
                if (!"".equals(input)) {
                    onCommandReceived(input);
                }
            }
        } catch (IOException e) {
            System.out.println("Invalid output.");
        }

    }

    public void onCommandReceived(String inputData) {
        String[] commandWithArgs = DataNormalizer.normalize(inputData);
        String commandName = commandWithArgs[0].toLowerCase(Locale.ROOT);
        String[] args = Arrays.copyOfRange(commandWithArgs, 1, commandWithArgs.length);
        Map<String, AbstractCommand> commands = CommandManager.getCommands();
        if (commands.containsKey(commandName)) {
            if (args.length == commands.get(commandName).getInlineArgsCount()) {
                boolean resultOfExecution = commands.get(commandName).execute(args);
                CommandManager.addCommandToHistory(commandName);
            } else {
                System.out.println("Wrong number of arguments.");
            }
        } else {
            System.out.println("No such command, call \"help\" to see the list of commands.");
        }
    }

}
