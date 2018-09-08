/*
 * class: MedicineNotPresentedException
 */

package by.epam.task4.exception;

/**
 * @author Maxim Burishinets
 * @version 1.0 25 Aug 2018
 */
public class MedicineNotPresentedException extends Exception {

    private static final long serialVersionUID = -1364027759215712943L;

    public MedicineNotPresentedException() {
        super();
    }
    
    public MedicineNotPresentedException(String message) {
        super(message);
    }
    
    public MedicineNotPresentedException(String message, Throwable cause) {
        super(message, cause);
    }
}
