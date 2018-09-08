/*
 * class: XMLVelidationException
 */

package by.epam.task4.exception;

/**
 * @author Maxim Burishinets
 * @version 1.0 25 Aug 2018
 */
public class XMLValidationException extends Exception {

    private static final long serialVersionUID = -4249213355583170171L;

    public XMLValidationException() {
        super();
    }
    
    public XMLValidationException(String message) {
        super(message);
    }
    
    public XMLValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
