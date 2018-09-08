/*
 * class: Version
 */

package by.epam.task4.model;

import java.util.HashSet;

/**
 * Represents concrete version of medicine, it's producer, certificate etc.
 * 
 * @author Maxim Burishinets
 * @version 1.0 20 Aug 2018
 */
public class Version {

    private String tradeName;
    private String producer;
    private String form;
    private Certificate certificate;
    private HashSet<Pack> packs;
    private Dosage dosage;
    
    public Version() {
        packs = new HashSet<Pack>();
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public HashSet<Pack> getPacks() {
        return packs;
    }

    public void setPacks(HashSet<Pack> packs) {
        this.packs = packs;
    }

    public Dosage getDosage() {
        return dosage;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }
    
    public void addPack(Pack pack) {
        packs.add(pack);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (obj == null) { return false; }
        if (obj.getClass() != getClass()) { return false; }
        Version version = (Version) obj;
        if (tradeName == null) {
            if (version.tradeName != null) {
                return false;
            }
        } else if(!tradeName.equals(version.tradeName)) {
            return false;
        }
        if (producer == null) {
            if (version.producer != null) {
                return false;
            }
        } else if (!producer.equals(version.producer)) {
            return false;
        }
        if (form == null) {
            if (version.form != null) {
                return false;
            }
        } else if (!form.equals(version.form)) {
            return false;
        }
        if (certificate == null) {
            if (version.certificate != null) {
                return false;
            }
        } else if (!certificate.equals(version.certificate)) {
            return false;
        }
        if (dosage == null) {
            if (version.dosage != null) {
                return false;
            }
        } else if (!dosage.equals(version.dosage)) {
            return false;
        }
        if (packs == null) {
            if (version.packs != null) {
                return false;
            }
        } else if (!packs.equals(version.packs)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode += tradeName == null ? 0 : tradeName.hashCode();
        hashCode += producer == null? 0 : producer.hashCode();
        hashCode += form == null ? 0 : form.hashCode();
        hashCode += certificate == null ? 0 : certificate.hashCode();
        hashCode += dosage == null ? 0 : dosage.hashCode();
        for (Pack pack : packs) {
            hashCode += pack.hashCode();
        }
        return hashCode;
    }
    
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(
                "\n    " + getClass().getSimpleName() + ":"
                + " trade name='" + tradeName + "'"
                + "\n        producer: " + producer
                + "\n        form :    " + form
                + certificate
                + dosage);
        for (Pack pack : packs) {
            output.append(pack);
        }
        return output.toString();
    }
}
