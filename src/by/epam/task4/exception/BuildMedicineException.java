/*
 * class: BuildMedicineException
 */

package by.epam.task4.exception;

/**
 * @author Maxim Burishinets
 * @version 1.0 25 Aug 2018
 */
public class BuildMedicineException extends Exception {

    private static final long serialVersionUID = -6856437532042142635L;
    
    public BuildMedicineException() {
        super();
    }
    
    public BuildMedicineException(String message) {
        super(message);
    }
    
    public BuildMedicineException(String message, Throwable cause) {
        super(message, cause);
    }
}
