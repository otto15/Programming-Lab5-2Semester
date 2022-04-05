package com.otto15.client.commands;


import com.otto15.client.controllers.CommandManager;

public class RemoveByIdCommand extends AbstractCommand {

    public RemoveByIdCommand() {
        super("remove_by_id", "removes collection element by id", 1);
    }

    @Override
    public boolean execute(String[] args) {
        try {
            CommandManager.getCollectionManager().getPersons().remove(CommandManager.getCollectionManager().findById(Long.parseLong(args[0])));
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Invalid format of id.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
