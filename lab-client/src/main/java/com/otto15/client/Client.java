package com.otto15.client;

import com.otto15.client.commands.CommandListener;
import com.otto15.client.config.Configurator;

import java.io.IOException;
import java.io.InputStreamReader;

public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void greet() {
        System.out.println("Welcome to the collection worker app!");
        System.out.println("Call \"help\" to see command list.");
    }

    public static void main(String[] args) {
        try {
            Configurator.configure();
            greet();
            CommandListener listener = new CommandListener(new InputStreamReader(System.in));
            listener.run();
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
