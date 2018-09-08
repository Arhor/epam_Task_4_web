/*
 * class: BuildCertificateException
 */

package by.epam.task4.exception;

/**
 * @author Maxim Burishinets
 * @version 1.0 31 Aug 2018
 */
public class BuildCertificateException extends Exception {

    private static final long serialVersionUID = -6442723198980701875L;

    public BuildCertificateException() {
        super();
    }

    public BuildCertificateException(String message) {
        super(message);
    }

    public BuildCertificateException(String message, Throwable cause) {
        super(message, cause);
    }
}
