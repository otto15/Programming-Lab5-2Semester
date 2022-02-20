package com.otto15.client.io.xml;

import com.otto15.client.io.CollectionFileReader;
import com.otto15.client.io.CollectionFileWriter;
import com.otto15.client.utils.InputStreamToStringConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.otto15.client.entities.Person;
import com.otto15.client.entities.PersonSet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class XmlCollectionFileOperator implements CollectionFileReader<PersonSet>, CollectionFileWriter<PersonSet> {
    @Override
    public PersonSet read(File file) throws IOException {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("person", Person.class);
        xStream.alias("persons", PersonSet.class);
        xStream.addImplicitCollection(PersonSet.class, "persons");
        String xml = InputStreamToStringConverter.inputStreamToString(file);
        return (PersonSet) xStream.fromXML(xml);
    }

    @Override
    public void write(File file, PersonSet persons) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("person", Person.class);
        xStream.alias("persons", PersonSet.class);
        xStream.addImplicitCollection(PersonSet.class, "persons");
        String xml = xStream.toXML(persons);
        FileWriter writer = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        printWriter.print(xml);
        printWriter.close();
    }
}
