package com.otto15.client.io.xml;

import com.otto15.client.io.CollectionFileReader;
import com.otto15.client.io.CollectionFileWriter;
import com.otto15.client.utils.FileStreamToStringConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.otto15.client.entities.Person;
import com.otto15.client.controllers.CollectionManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * XML file worker
 * <p>
 * Reads and writes collection xml data
 * @author Rakhmatullin R.
 */
public class XmlCollectionFileOperator implements CollectionFileReader<CollectionManager>, CollectionFileWriter<CollectionManager> {
    @Override
    public CollectionManager read(File file) throws IOException, ConversionException {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("person", Person.class);
        xStream.alias("persons", CollectionManager.class);
        xStream.omitField(CollectionManager.class, "creationDate");
        xStream.omitField(Person.class, "id");
        xStream.omitField(CollectionManager.class, "groupsByHeight");
        xStream.addImplicitCollection(CollectionManager.class, "persons");
        String xml = FileStreamToStringConverter.fileStreamToString(file);
        return (CollectionManager) xStream.fromXML(xml);
    }

    @Override
    public void write(File file, CollectionManager persons) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("person", Person.class);
        xStream.alias("persons", CollectionManager.class);
        xStream.addImplicitCollection(CollectionManager.class, "persons");
        xStream.omitField(CollectionManager.class, "creationDate");
        xStream.omitField(Person.class, "id");
        xStream.omitField(CollectionManager.class, "groupsByHeight");
        String xml = xStream.toXML(persons);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        printWriter.print(xml);
        printWriter.close();
    }
}
