package com.otto15.client;

import com.otto15.client.controllers.CommandListener;
import com.otto15.client.controllers.CommandManager;
import com.otto15.client.config.Configurator;
import com.otto15.client.controllers.CollectionManager;

import java.io.IOException;

/**
 * Entry point.
 */
public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void greet() {
        System.out.println("Welcome to the collection worker app!");
        System.out.println("Call \"help\" to see command list.");
    }

    public static void main(String[] args) {
        if (Configurator.configure()) {
            try {
                CollectionManager collectionManager = CollectionManager.initFromFile(Configurator.COLLECTION_FILE_READER, Configurator.getInputFile());
                greet();
                CommandManager.setCollectionManager(collectionManager);
                CommandListener listener = new CommandListener();
                listener.run();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
