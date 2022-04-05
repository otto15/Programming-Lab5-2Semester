package com.otto15.client.controllers;

import com.otto15.client.commands.AbstractCommand;
import com.otto15.client.commands.AddCommand;
import com.otto15.client.commands.AddIfMinCommand;
import com.otto15.client.commands.ClearCommand;
import com.otto15.client.commands.ExecuteScriptCommand;
import com.otto15.client.commands.ExitCommand;
import com.otto15.client.commands.GroupCountingByHeightCommand;
import com.otto15.client.commands.HelpCommand;
import com.otto15.client.commands.HistoryCommand;
import com.otto15.client.commands.InfoCommand;
import com.otto15.client.commands.RemoveAnyByHeightCommand;
import com.otto15.client.commands.RemoveByIdCommand;
import com.otto15.client.commands.RemoveGreaterCommand;
import com.otto15.client.commands.SaveCommand;
import com.otto15.client.commands.ShowCommand;
import com.otto15.client.commands.SumOfHeightCommand;
import com.otto15.client.commands.UpdateCommand;
import com.otto15.client.utils.DataNormalizer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

/**
 * Manager for commands, responsible for invoking.
 */
public final class CommandManager {

    private static final int HISTORY_LENGTH = 10;
    private static final Queue<String> COMMAND_HISTORY = new LinkedList<>();
    private static final Map<String, AbstractCommand> COMMANDS = new HashMap<>();
    private static CollectionManager collectionManager;

    private CommandManager() {

    }

    static {
        COMMANDS.put("add", new AddCommand());
        COMMANDS.put("add_if_min", new AddIfMinCommand());
        COMMANDS.put("clear", new ClearCommand());
        COMMANDS.put("exit", new ExitCommand());
        COMMANDS.put("history", new HistoryCommand());
        COMMANDS.put("info", new InfoCommand());
        COMMANDS.put("remove_any_by_height", new RemoveAnyByHeightCommand());
        COMMANDS.put("remove_by_id", new RemoveByIdCommand());
        COMMANDS.put("remove_greater", new RemoveGreaterCommand());
        COMMANDS.put("save", new SaveCommand());
        COMMANDS.put("sum_of_height", new SumOfHeightCommand());
        COMMANDS.put("update", new UpdateCommand());
        COMMANDS.put("group_counting_by_height", new GroupCountingByHeightCommand());
        COMMANDS.put("execute_script", new ExecuteScriptCommand());
        COMMANDS.put("help", new HelpCommand());
        COMMANDS.put("show", new ShowCommand());
    }

    public static void setCollectionManager(CollectionManager collectionManager) {
        CommandManager.collectionManager = collectionManager;
    }

    public static CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public static Map<String, AbstractCommand> getCommands() {
        return COMMANDS;
    }

    public static Queue<String> getCommandHistory() {
        return COMMAND_HISTORY;
    }

    public static void addCommandToHistory(String commandName) {
        COMMAND_HISTORY.add(commandName);
        if (COMMAND_HISTORY.size() > HISTORY_LENGTH) {
            COMMAND_HISTORY.poll();
        }
    }

    /**
     * Normalize the data and pass it to executeCommand()
     * @param inputData data from listener
     */
    public static void onCommandReceived(String inputData) {
        String[] commandWithArgs = DataNormalizer.normalize(inputData);
        String commandName = commandWithArgs[0].toLowerCase(Locale.ROOT);
        String[] args = Arrays.copyOfRange(commandWithArgs, 1, commandWithArgs.length);
        executeCommand(commandName, args);
    }

    /**
     * Invoke command
     * @param commandName command in question
     * @param args arguments for command
     */
    public static void executeCommand(String commandName, String[] args) {
        if (COMMANDS.containsKey(commandName)) {
            if (args.length == COMMANDS.get(commandName).getInlineArgsCount()) {
                boolean resultOfExecution = COMMANDS.get(commandName).execute(args);
                CommandManager.addCommandToHistory(commandName);
//                if (resultOfExecution) {
//                    System.out.println("Command was executed successfully.");
//                } else {
//                    System.out.println("Bad execution.");
//                }
            } else {
                System.out.println("Wrong number of arguments.");
            }
        } else {
            System.out.println("No such command, call \"help\" to see the list of commands.");
        }
    }

}
