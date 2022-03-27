package com.otto15.client.io.xml;

import com.otto15.client.io.CollectionFileReader;
import com.otto15.client.io.CollectionFileWriter;
import com.otto15.client.utils.InputStreamToStringConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.otto15.client.entities.Person;
import com.otto15.client.entities.PersonCollectionManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * XML file worker
 * <p>
 * Reads and writes collection xml data
 * @author Rakhmatullin R.
 */
public class XmlCollectionFileOperator implements CollectionFileReader<PersonCollectionManager>, CollectionFileWriter<PersonCollectionManager> {
    @Override
    public PersonCollectionManager read(File file) throws IOException, ConversionException {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("person", Person.class);
        xStream.alias("persons", PersonCollectionManager.class);
        xStream.omitField(PersonCollectionManager.class, "creationDate");
        xStream.omitField(Person.class, "id");
        xStream.omitField(PersonCollectionManager.class, "groupsByHeight");
        xStream.addImplicitCollection(PersonCollectionManager.class, "persons");
        String xml = InputStreamToStringConverter.inputStreamToString(file);
        return (PersonCollectionManager) xStream.fromXML(xml);
    }

    @Override
    public void write(File file, PersonCollectionManager persons) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("person", Person.class);
        xStream.alias("persons", PersonCollectionManager.class);
        xStream.addImplicitCollection(PersonCollectionManager.class, "persons");
        xStream.omitField(PersonCollectionManager.class, "creationDate");
        xStream.omitField(Person.class, "id");
        xStream.omitField(PersonCollectionManager.class, "groupsByHeight");
        String xml = xStream.toXML(persons);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        printWriter.print(xml);
        printWriter.close();
    }
}
