package com.otto15.client.commands;

public class HistoryCommand extends AbstractCommand {

    public HistoryCommand() {
        super("history", "outputs last 10 commands", 0);
    }

    @Override
    public boolean execute(String[] args) {
        if (CommandManager.getCommandHistory().size() == 0) {
            System.out.println("History is empty.");
        } else {
            CommandManager.getCommandHistory().forEach(System.out::println);
        }
        return true;
    }
}
