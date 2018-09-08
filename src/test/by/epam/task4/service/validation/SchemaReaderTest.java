package test.by.epam.task4.service.validation;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import by.epam.task4.service.validation.SchemaReader;

public class SchemaReaderTest {

    private static final String FICTIVE_FILE = "fictive_file";

    @Test(expectedExceptions = SAXException.class)
    public void getSchemaTest() throws SAXException {
        String xsd = FICTIVE_FILE;
        SchemaReader.getSchema(xsd);
    }
}