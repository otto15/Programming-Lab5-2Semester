package com.otto15.client.config;

import com.otto15.client.entities.PersonSet;
import com.otto15.client.io.CollectionFileReader;
import com.otto15.client.io.CollectionFileWriter;
import com.otto15.client.io.xml.XmlCollectionFileOperator;

import java.io.File;
import java.io.IOException;

public final class Configurator {
    public static final CollectionFileReader<PersonSet> COLLECTION_FILE_READER = new XmlCollectionFileOperator();
    public static final CollectionFileWriter<PersonSet> COLLECTION_FILE_WRITER = new XmlCollectionFileOperator();
    private static File inputFile;
    private static File outputFile;
    private static PersonSet persons;

    private Configurator() {

    }

    public static File getInputFile() {
        return inputFile;
    }

    public static File getOutputFile() {
        return outputFile;
    }

    public static PersonSet getPersons() {
        return persons;
    }

    public static void configure() throws IOException {
        if (System.getenv("COLLECTION_FILE") == null) {
            throw new NullPointerException("Set your collection file as a COLLECTION_FILE environment variable and restart the app.");
        } else {
            inputFile = new File(System.getenv("COLLECTION_FILE"));
            outputFile = new File(System.getenv("COLLECTION_FILE"));
            persons = COLLECTION_FILE_READER.read(inputFile);
        }
    }

}
