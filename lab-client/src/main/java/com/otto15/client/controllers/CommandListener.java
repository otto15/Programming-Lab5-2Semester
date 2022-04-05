package com.otto15.client.controllers;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Listens to stream for commands
 *
 * @author Rakhmatullin R.
 */
public class CommandListener {

    private static boolean performanceStatus = true;
    private final Reader reader;


    public CommandListener() {
        reader = new InputStreamReader(System.in);
    }

    public CommandListener(Reader reader) {
        this.reader = reader;
    }

    public static boolean getPerformanceStatus() {
        return performanceStatus;
    }

    public static void switchPerformanceStatus() {
        performanceStatus = !performanceStatus;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(reader)) {
            while (performanceStatus) {
                if (!(reader.getClass() == FileReader.class)) {
                    System.out.println("===========================");
                }
                String input = in.readLine();
                if (input == null) {
                    break;
                }
                if (!"".equals(input)) {
                    CommandManager.onCommandReceived(input);
                }
            }
        } catch (IOException e) {
            System.out.println("Invalid output.");
        }

    }

}
