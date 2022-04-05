package com.otto15.client.config;

import com.otto15.client.controllers.CollectionManager;
import com.otto15.client.io.CollectionFileReader;
import com.otto15.client.io.CollectionFileWriter;
import com.otto15.client.io.xml.XmlCollectionFileOperator;

import java.io.File;

/**
 * Configurator, which configures the app
 *
 * @author Rakhmatullin R.
 **/
public final class Configurator {
    public static final CollectionFileReader<CollectionManager> COLLECTION_FILE_READER = new XmlCollectionFileOperator();
    public static final CollectionFileWriter<CollectionManager> COLLECTION_FILE_WRITER = new XmlCollectionFileOperator();
    private static File inputFile;
    private static File outputFile;


    private Configurator() {

    }

    public static File getInputFile() {
        return inputFile;
    }

    public static File getOutputFile() {
        return outputFile;
    }

    public static boolean configure() {
        if (System.getenv("COLLECTION_FILE") == null) {
            System.out.println(("Set your collection file as a COLLECTION_FILE environment variable and restart the app."));
            return false;
        } else {
            inputFile = new File(System.getenv("COLLECTION_FILE"));
            outputFile = new File(System.getenv("COLLECTION_FILE"));
        }
        return true;
    }

}
