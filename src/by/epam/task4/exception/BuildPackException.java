/*
 * class: BuildPackException
 */

package by.epam.task4.exception;

/**
 * @author Maxim Burishinets
 * @version 1.0 31 Aug 2018
 */
public class BuildPackException extends Exception {

    private static final long serialVersionUID = -6596694279024538896L;

    public BuildPackException() {
        super();
    }

    public BuildPackException(String message) {
        super(message);
    }

    public BuildPackException(String message, Throwable cause) {
        super(message, cause);
    }
}
