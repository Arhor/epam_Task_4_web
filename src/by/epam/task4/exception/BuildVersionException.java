/*
 * class: BuildVersionException
 */

package by.epam.task4.exception;

/**
 * @author Maxim Burishinets
 * @version 1.0 31 Aug 2018
 */
public class BuildVersionException extends Exception {

    private static final long serialVersionUID = 8443593543897671572L;

    public BuildVersionException() {
        super();
    }

    public BuildVersionException(String message) {
        super(message);
    }

    public BuildVersionException(String message, Throwable cause) {
        super(message, cause);
    }
}
