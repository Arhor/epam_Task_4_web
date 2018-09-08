/*
 * class: Dosage
 */

package by.epam.task4.model;

/**
 * Represents dosage of concrete medicine version
 * 
 * @author Maxim Burishinets
 * @version 1.0 20 Aug 2018
 */
public class Dosage {

    private String amount;
    private String frequency;
    
    public String getAmount() {
        return amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    public String getFrequency() {
        return frequency;
    }
    
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (obj == null) { return false; }
        if (obj.getClass() != getClass()) { return false; }
        Dosage dosage = (Dosage) obj;
        if (amount == null) {
            if (dosage.amount != null) {
                return false;
            }
        } else if (!amount.equals(dosage.amount)) {
            return false;
        }
        if (frequency == null) {
            if (dosage.frequency != null) {
                return false;
            }
        } else if (!frequency.equals(dosage.frequency)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode += amount == null ? 0 : amount.hashCode();
        hashCode += frequency == null ? 0 : frequency.hashCode();
        return hashCode;
    }
    
    @Override
    public String toString() {
        return "\n        " + getClass().getSimpleName() + ":"
                + "\n            amount:    " + amount
                + "\n            frequency: " + frequency;
    }
}
