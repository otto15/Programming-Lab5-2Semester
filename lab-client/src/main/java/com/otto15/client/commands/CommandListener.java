package com.otto15.client.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class CommandListener {
    private final CommandHub cmdHub;
    private HashMap<String, Method> commands = new HashMap<>();
    private final Reader reader;

    public CommandListener(Reader reader) {
        this.reader = reader;
        cmdHub = new CommandHub(new InputStreamReader(System.in));
        initCommands();
    }

    private void initCommands() {
        for (Method method : cmdHub.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                commands.put(method.getAnnotation(Command.class).name(), method);
            }
        }
    }

    public void run() throws IOException {
        try (BufferedReader in = new BufferedReader(reader)) {
            while (true) {
                try {
                    String data = in.readLine();
                    if (data == null) {
                        break;
                    }
                    if (onCommandReceived(data) == 1) {
                        break;
                    }
                } catch (IOException e) {
                    System.out.println("Invalid input.");
                }
            }
        }
    }

    public int onCommandReceived(String inputData) {
        String[] args = inputData.split(" ");
        String commandName = args[0].toLowerCase();
        if ("exit".equals(commandName)) {
            return 1;
        }
        String[] commandArgs = Arrays.copyOfRange(args, 1, args.length);
        Method method = commands.get(commandName);
        if (reader.getClass() == FileReader.class) {
            System.out.println("Called " + "\"" + commandName + "\"");
        }
        try {
            if (method != null) {
                method.invoke(cmdHub, commandArgs);
                cmdHub.addCommandToHistory(commandName);
            } else {
                System.out.println("No such command, call \"help\" to see the list of commands.");
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Invalid command.");
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong number of arguments.");
        }
        return 0;
    }
}
