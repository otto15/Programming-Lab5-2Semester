package com.otto15.client.commands;

import com.otto15.client.entities.PersonCollectionManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public final class CommandManager {

    private static final int HISTORY_LENGTH = 10;
    private static final Queue<String> COMMAND_HISTORY = new LinkedList<>();
    private static final Map<String, AbstractCommand> COMMANDS = new HashMap<>();
    private static PersonCollectionManager collectionManager;

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

    public static void setCollectionManager(PersonCollectionManager collectionManager) {
        CommandManager.collectionManager = collectionManager;
    }

    public static PersonCollectionManager getCollectionManager() {
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

}
