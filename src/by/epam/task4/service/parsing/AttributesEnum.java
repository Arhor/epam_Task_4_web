/*
 * class: AttributesEnum
 */

package by.epam.task4.service.parsing;

/**
 * Serves as enumeration of possible tag-attributes for XML elements
 * 
 * @author Maxim Burishinets
 * @version 1.0 20 Aug 2018
 */
public enum AttributesEnum {

    NAME("name"),
    CAS("CAS"),
    DRUG_BANK("drug_bank"),
    RECIPE("recipe"),
    SOLUTION("solution"),
    NARCOTIC("narcotic"),
    SIZE("size"),
    TRADE_NAME("trade_name");
    
    private String value;
    
    private AttributesEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}
