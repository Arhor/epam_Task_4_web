/*
 * class: MedicinsBuilderFactory
 */

package by.epam.task4.service.factory;

import by.epam.task4.exception.ParserNotPresentedException;
import by.epam.task4.service.parsing.MedicinsAbstractBuilder;
import by.epam.task4.service.parsing.dom.MedicinsDOMBuilder;
import by.epam.task4.service.parsing.sax.MedicinsSAXBuilder;
import by.epam.task4.service.parsing.stax.MedicinsStAXBuilder;

/**
 * Factory class which serves for creation concrete medicines builder depending
 * on passed value
 * 
 * @author Maxim Burishinets
 * @version 1.0 20 Aug 2018
 */
public class MedicinsBuilderFactory {
        
    private static final String SAX = "SAX";
    private static final String DOM = "DOM";
    private static final String STAX = "STAX";

    /**
     * Factory method for creation concrete medicines builder
     * 
     * @param name - name of parsing type which will be used to parse XML 
     * document and build set of {@link Medicine} objects
     * @return concrete medicines builder
     * @throws ParserNotPresentedException
     */
    public MedicinsAbstractBuilder getBuilder(String name)
            throws ParserNotPresentedException {
        MedicinsAbstractBuilder builder = null;
        switch (name.toUpperCase()) {
            case SAX:
                builder = new MedicinsSAXBuilder();
                break;
            case DOM:
                builder = new MedicinsDOMBuilder();
                break;
            case STAX:
                builder = new MedicinsStAXBuilder();
                break;
            default:
                throw new ParserNotPresentedException(
                        "There is not such parser: " + name);
        }
        return builder;
    }
}
