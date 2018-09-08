/*
 * class: ParserNotPresentedException
 */

package by.epam.task4.exception;

/**
 * @author Maxim Burishinets
 * @version 1.0 25 Aug 2018
 */
public class ParserNotPresentedException extends Exception {

    private static final long serialVersionUID = 3488687349870618790L;

    public ParserNotPresentedException() {
        super();
    }
    
    public ParserNotPresentedException(String message) {
        super(message);
    }
    
    public ParserNotPresentedException(String message, Throwable cause) {
        super(message, cause);
    }
}
