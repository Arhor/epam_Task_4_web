/*
 * class: ElementsEnum
 */

package by.epam.task4.service.parsing;

/**
 * Serves as enumeration of possible XML elements and their String values
 * 
 * @author Maxim Burishinets
 * @version 1.0 20 Aug 2018
 */
public enum ElementsEnum {

    MEDICINS("Medicins"),
    ANTIBIOTIC("Antibiotic"),
    VITAMIN("Vitamin"),
    ANALGETIC("Analgetic"),
    VERSION("Version"),
    CERTIFICATE("Certificate"),
    PACK("Pack"),
    DOSAGE("Dosage"),
    PHARM("Pharm"),
    PRODUCER("Producer"),
    FORM("Form"),
    REGISTRED_BY("Registred_by"),
    REGISTRATION_DATE("Registration_date"),
    EXPIRE_DATE("Expire_date"),
    QUANTITY("Quantity"),
    PRICE("Price"),
    AMOUNT("Amount"),
    FREQUENCY("Frequency");
    
    private String value;
    
    private ElementsEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}
