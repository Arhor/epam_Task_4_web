/*
 * class: MedicinsSAXBuilder
 */

package by.epam.task4.service.parsing.sax;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.task4.service.parsing.MedicinsAbstractBuilder;
import by.epam.task4.service.validation.XMLValidator;

/**
 * Class MedicinsDOMBuilder extends abstract class 
 * {@link MedicinsAbstractBuilder}, serves for building set of Medicine objects
 * based on XML-document by parsing it using SAX-parser for XML
 * 
 * @author Maxim Burishinets
 * @version 1.0 19 Aug 2018
 */
public class MedicinsSAXBuilder extends MedicinsAbstractBuilder {
    
    private static final Logger LOG = LogManager.getLogger();

    private SAXParsingHandler handler;
    private XMLReader reader;
    
    public MedicinsSAXBuilder() {
        handler = new SAXParsingHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            LOG.error("SAX parser error: ", e);
        }
    }

    /**
     * Parses XML-document using SAX-parser, gets set of Medicine objects built
     * by {@link SAXParsingHandler}
     * 
     * @param xml - path to XML-document to parse
     * @return true - if parsing was successful; false - if there occurred any 
     * kind of exception during XML-document parsing
     */
    @Override
    public boolean buildSetMedicins(String xml) {
        if (XMLValidator.validate(xml)) {
            try {
                reader.parse(xml);
                medicins = handler.getMedicins();
                return true;
            } catch (SAXException e) {
                LOG.error("SAX parser exception: ", e);
            } catch (IOException e) {
                LOG.error("I/O exception: ", e);
            }
        }
        return false;
    }
}
