/*
 * class: Certificate
 */

package by.epam.task4.model;

import java.util.Date;

/**
 * Represents certificate which refers to concrete medicine version
 * 
 * @author Maxim Burishinets
 * @version 1.0 20 Aug 2018
 */
public class Certificate {

    private String registredBy;
    private Date registrationDate;
    private Date expireDate;
    
    public String getRegistredBy() {
        return registredBy;
    }
    
    public void setRegistredBy(String registredBy) {
        this.registredBy = registredBy;
    }
    
    public Date getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationDate(Date registredDate) {
        this.registrationDate = registredDate;
    }
    
    public Date getExpireDate() {
        return expireDate;
    }
    
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (obj == null) { return false; }
        if (obj.getClass() != getClass()) { return false; }
        Certificate certificate = (Certificate) obj;
        if (registredBy == null) {
            if (certificate.registredBy != null) {
                return false;
            }
        } else if (!registredBy.equals(certificate.registredBy)) {
            return false;
        }
        if (registrationDate == null) {
            if (certificate.registrationDate != null) {
                return false;
            }
        } else if (!registrationDate.equals(certificate.registrationDate)) {
            return false;
        }
        if (expireDate == null) {
            if (certificate.expireDate != null) {
                return false;
            }
        } else if (!expireDate.equals(certificate.expireDate)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode += registredBy == null ? 0 : registredBy.hashCode();
        hashCode += registrationDate == null ? 0 : registrationDate.hashCode();
        hashCode += expireDate == null ? 0 : expireDate.hashCode();
        return hashCode;
    }
    
    @Override
    public String toString() {
        return "\n        " + getClass().getSimpleName() + ":"
                + "\n            registred by:      " + registredBy
                + "\n            registration date: " + registrationDate
                + "\n            expire date :      " + expireDate;
    }
}
