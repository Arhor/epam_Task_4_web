/*
 * class: MedicinsAbstractBuilder
 */

package by.epam.task4.service.parsing;

import java.io.IOException;
import java.util.Set;

import by.epam.task4.exception.BuildMedicineException;
import by.epam.task4.model.Medicine;
import org.xml.sax.SAXException;

/**
 * Abstract class MedicinsAbstractBuilder serves as basis for concrete builders
 * for {@link Medicine} object sets, depending on concrete XML parsing 
 * technology
 * 
 * @author Maxim Burishinets
 * @version 1.0 25 Aug 2018
 */
public abstract class MedicinsAbstractBuilder {

    protected Set<Medicine> medicins;

    public Set<Medicine> getMedicins() {
        return medicins;
    }
    
    public abstract boolean buildSetMedicins(String xml) throws BuildMedicineException, IOException, SAXException;
}