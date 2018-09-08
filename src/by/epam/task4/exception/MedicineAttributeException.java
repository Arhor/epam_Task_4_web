/*
 * class: ElementNotPresentedException
 */

package by.epam.task4.exception;

/**
 * @author Maxim Burishinets
 * @version 1.0 31 Aug 2018
 */
public class MedicineAttributeException extends Exception {

    private static final long serialVersionUID = -7616374672947252285L;

    public MedicineAttributeException() {
        super();
    }

    public MedicineAttributeException(String message) {
        super(message);
    }

    public MedicineAttributeException(String message, Throwable cause) {
        super(message, cause);
    }
}
