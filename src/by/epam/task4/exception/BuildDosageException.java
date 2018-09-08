/*
 * class: BuildDosageException
 */

package by.epam.task4.exception;

/**
 * @author Maxim Burishinets
 * @version 1.0 31 Aug 2018
 */
public class BuildDosageException extends Exception {

    private static final long serialVersionUID = 3731630959386551415L;

    public BuildDosageException() {
        super();
    }

    public BuildDosageException(String message) {
        super(message);
    }

    public BuildDosageException(String message, Throwable cause) {
        super(message, cause);
    }
}
