/*
 * class: MedicineFactory
 */

package by.epam.task4.service.factory;

import by.epam.task4.exception.MedicineNotPresentedException;
import by.epam.task4.model.Analgetic;
import by.epam.task4.model.Antibiotic;
import by.epam.task4.model.Medicine;
import by.epam.task4.model.Vitamin;
import by.epam.task4.service.parsing.ElementsEnum;

/**
 * Factory class which creates concrete medicine objects depending on passed
 * value
 * 
 * @author Maxim Burishinets
 * @version 1.0 25 Aug 2018
 */
public class MedicineFactory {

    /**
     * Factory method for creation concrete medicine objects
     * 
     * @param element - {@link ElementsEnum} object which represents concrete 
     * type of medicine to create
     * @return {@link Medicine} object ({@link Antibiotic} , {@link Analgetic} 
     * or {@link Vitamin})
     * @throws MedicineNotPresentedException
     */
    public Medicine getMedicine(ElementsEnum element)
            throws MedicineNotPresentedException {
        Medicine medicine = null;
        switch (element) {
            case ANTIBIOTIC:
                medicine = new Antibiotic();
                break;
            case VITAMIN:
                medicine = new Vitamin();
                break;
            case ANALGETIC:
                medicine = new Analgetic();
                break;
            default:
                throw new MedicineNotPresentedException(
                        "There is not such medicine type: " + element);
        }
        return medicine;
    }
}
