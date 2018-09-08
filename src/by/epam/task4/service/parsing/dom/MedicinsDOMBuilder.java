/*
 * class: MedicinsDOMBuilder
 */

package by.epam.task4.service.parsing.dom;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.w3c.dom.*;

import org.xml.sax.SAXException;

import by.epam.task4.exception.BuildCertificateException;
import by.epam.task4.exception.BuildDosageException;
import by.epam.task4.exception.BuildMedicineException;
import by.epam.task4.exception.BuildPackException;
import by.epam.task4.exception.BuildVersionException;
import by.epam.task4.exception.MedicineAttributeException;
import by.epam.task4.exception.MedicineNotPresentedException;
import by.epam.task4.model.*;
import by.epam.task4.service.factory.MedicineFactory;
import by.epam.task4.service.parsing.AttributesEnum;
import by.epam.task4.service.parsing.ElementsEnum;
import by.epam.task4.service.parsing.MedicinsAbstractBuilder;

/**
 * Class MedicinsDOMBuilder extends abstract class 
 * {@link MedicinsAbstractBuilder}, serves for building set of Medicine objects
 * based on XML-document by parsing it using DOM-parser for XML
 * 
 * @author Maxim Burishinets
 * @version 2.0 30 Aug 2018
 */
public class MedicinsDOMBuilder extends MedicinsAbstractBuilder {
    
    private static final Logger LOG = LogManager.getLogger();

    private DocumentBuilder docBuilder;
    private MedicineFactory mFactory;
    private DateFormat dateFormat;
    
    public MedicinsDOMBuilder() {
        medicins = new HashSet<Medicine>();
        mFactory = new MedicineFactory();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOG.error("Parser configuration exception: ", e);
        }
    }

    /**
     * Parses XML-document using DOM-parser, gets root-element of current 
     * document runs through it and builds set of Medicine objects
     * 
     * @param xml - path to XML-document to parse
     * @return true - if parsing was successful; false - if there occurred any 
     * kind of exception during XML-document parsing
     */
    @Override
    public boolean buildSetMedicins(String xml) {
        Document document = null;
        try {
            document = docBuilder.parse(xml);
            Element root = document.getDocumentElement();
            NodeList medicinsList = root.getChildNodes();
            for (int i = 0; i < medicinsList.getLength(); i++) {
                Node node = medicinsList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element medicineElement = (Element) node;
                    Medicine medicine = buildMedicine(medicineElement);
                    medicins.add(medicine);
                }
            }
            return true;
        } catch (IOException e) {
            LOG.error("I/O exception", e);
        } catch (SAXException e) {
            LOG.error("SAX pasring exception", e);
        } catch (BuildMedicineException e) {
            LOG.error("An error occurred within building Medicine object", e);
        }
        return false;
    }
    
    /**
     * Runs through all child-nodes of 'medicineElement' and builds concrete
     * Medicine object depending on it
     * 
     * @param medicineElement - DOM-element that represents concrete medicine
     * @return {@link Medicine} object
     * @throws BuildMedicineException
     */
    private Medicine buildMedicine(Element medicineElement)
            throws BuildMedicineException {
        Medicine currentMedicine;
        try {
            currentMedicine = mFactory.getMedicine(
                    ElementsEnum.valueOf(
                            medicineElement.getTagName().toUpperCase()));
            setMedicineAttributes(currentMedicine, medicineElement);
            Element pharm = (Element) medicineElement.getElementsByTagName(
                    ElementsEnum.PHARM.getValue()).item(0);
            currentMedicine.setPharm(pharm.getTextContent());
            currentMedicine.setVersions(buildVersions(medicineElement));
            return currentMedicine;
        } catch (MedicineNotPresentedException
                | MedicineAttributeException
                | BuildVersionException e) {
            String errorMessage = "Build medicine exception";
            LOG.error(errorMessage);
            throw new BuildMedicineException(errorMessage, e);
        }
    }
    
    /**
     * Initializes Medicine object's fields depending on attribute-nodes of 
     * passed DOM-element
     * 
     * @param medicine - Medicine object with fields supposed to initialize
     * @param medElement - DOM-element which contains relevant attributes
     * @throws MedicineAttributeException 
     */
    private void setMedicineAttributes(Medicine medicine, Element medElement)
            throws MedicineAttributeException {
        NamedNodeMap attributes = medElement.getAttributes();
        for (int i = 0; i < attributes.getLength() ; i++) {
            Attr attribute = (Attr) attributes.item(i);
            String name = attribute.getName();
            String value = attribute.getValue();
            AttributesEnum currentAttribute = AttributesEnum.valueOf(
                    name.toUpperCase());
            switch (currentAttribute) {
                case NAME:
                    medicine.setName(value);
                    break;
                case CAS:
                    medicine.setCas(value);
                    break;
                case DRUG_BANK:
                    medicine.setDrugBank(value);
                    break;
                case RECIPE:
                    boolean recipe = Boolean.parseBoolean(value);
                    ((Antibiotic)medicine).setRecipe(recipe);
                    break;
                case SOLUTION:
                    ((Vitamin)medicine).setSolution(value);
                    break;
                case NARCOTIC:
                    boolean narcotic = Boolean.parseBoolean(value);
                    ((Analgetic)medicine).setNarcotic(narcotic);
                    break;
                default:
                    String errorMessage = "attribute <" 
                            + currentAttribute
                            + "> is not valid";
                    LOG.error(errorMessage);
                    throw new MedicineAttributeException(errorMessage);
            }
        }
    }
    
    /**
     * Runs through all version-nodes of passed DOM-element and builds set of 
     * Version objects
     * 
     * @param medicineElement - DOM-element that represents concrete medicine
     * @return set of {@link Version} objects
     * @throws BuildVersionException 
     */
    private HashSet<Version> buildVersions(Element medicineElement)
            throws BuildVersionException {
        HashSet<Version> versions = new HashSet<>();
        NodeList versionElements = medicineElement.getElementsByTagName(
                ElementsEnum.VERSION.getValue());
        for (int i = 0; i < versionElements.getLength(); i++) {
            Element versionElement = (Element) versionElements.item(i);
            versions.add(buildVersion(versionElement));
        }
        return versions;
    }
    
    /**
     * Runs through all child-nodes of passed DOM-element and builds Version 
     * object depending on it
     * 
     * @param versionElement - DOM-element that represents version of 
     * concrete medicine
     * @return {@link Version} object
     * @throws BuildVersionException 
     */
    private Version buildVersion(Element versionElement)
            throws BuildVersionException {
        Version currentVersion = new Version();
        currentVersion.setTradeName(versionElement.getAttribute(
                AttributesEnum.TRADE_NAME.getValue()));
        currentVersion.setProducer(versionElement.getElementsByTagName(
                ElementsEnum.PRODUCER.getValue()).item(0).getTextContent());
        currentVersion.setForm(versionElement.getElementsByTagName(
                ElementsEnum.FORM.getValue()).item(0).getTextContent());
        Element certificateElement = 
                (Element) versionElement.getElementsByTagName(
                        ElementsEnum.CERTIFICATE.getValue()).item(0);
        Element dosageElement =    
                (Element) versionElement.getElementsByTagName(
                        ElementsEnum.DOSAGE.getValue()).item(0);
        try {
            currentVersion.setCertificate(buildCertificate(certificateElement));
            currentVersion.setPacks(buildPacks(versionElement));
            currentVersion.setDosage(buildDosage(dosageElement));
        } catch (BuildCertificateException
                | BuildPackException
                | BuildDosageException e) {
            String errorMessage = "Building version exception";
            LOG.error(errorMessage);
            throw new BuildVersionException(errorMessage, e);
        }
        
        return currentVersion;
    }
    
    /**
     * Runs through all child-nodes of passed DOM-element and builds 
     * Certificate object depending on it
     * 
     * @param certificateElement - DOM-element that represents certificate of 
     * concrete medicine version
     * @return {@link Certificate} object
     * @throws BuildCertificateException 
     */
    private Certificate buildCertificate(Element certificateElement)
            throws BuildCertificateException {
        Certificate currentCertificate = new Certificate();
        NodeList certificateFields = certificateElement.getChildNodes();
        for (int j = 0; j < certificateFields.getLength(); j++) {
            Node certField = certificateFields.item(j);
            if (certField.getNodeType() == Node.ELEMENT_NODE) {
                String tagName = 
                        ((Element)certField).getTagName().toUpperCase();
                ElementsEnum currentField = ElementsEnum.valueOf(tagName);
                switch (currentField) {
                    case REGISTRED_BY:
                        currentCertificate.setRegistredBy(
                                certField.getTextContent());
                        break;
                    case REGISTRATION_DATE:
                        try {
                            Date date = dateFormat.parse(
                                    certField.getTextContent());
                            currentCertificate.setRegistrationDate(date);
                        } catch (ParseException e) {
                            LOG.error("Date parsing exception: ", e);
                        }
                        break;
                    case EXPIRE_DATE:
                        try {
                            Date date = dateFormat.parse(
                                    certField.getTextContent());
                            currentCertificate.setExpireDate(date);
                        } catch (ParseException e) {
                            LOG.error("Date parsing exception: ", e);
                        }
                        break;
                    default:
                        String errorMessage = "element <" 
                                + currentField
                                + "> is not supposed to be here";
                        LOG.error(errorMessage);
                        throw new BuildCertificateException(errorMessage);
                }
            }
        }
        return currentCertificate;
    }
    
    /**
     * Runs through all pack-nodes of passed DOM-element and builds set of 
     * Pack objects
     * 
     * @param versionElement - DOM-element that represents version of 
     * concrete medicine
     * @return set of {@link Pack} objects
     * @throws BuildPackException 
     */
    private HashSet<Pack> buildPacks(Element versionElement)
            throws BuildPackException {
        HashSet<Pack> packs = new HashSet<Pack>();
        NodeList packElements = versionElement.getElementsByTagName(
                ElementsEnum.PACK.getValue());
        for (int k = 0; k < packElements.getLength(); k++) {
            Element packElement = (Element) packElements.item(k);
            packs.add(buildPack(packElement));
        }
        return packs;
    }
    
    /**
     * Runs through all child-nodes of passed DOM-element and builds 
     * Pack object depending on it
     * 
     * @param packElement - DOM-element that represents package form for 
     * version of concrete medicine
     * @return {@link Pack} object
     * @throws BuildPackException 
     */
    private Pack buildPack(Element packElement) throws BuildPackException {
        Pack currentPack = new Pack();
        if (packElement.hasAttributes()) {
            Attr size = packElement.getAttributeNode(
                    AttributesEnum.SIZE.getValue());
            currentPack.setSize(size.getValue());
        }
        NodeList packFields = packElement.getChildNodes();
        for (int n = 0; n < packFields.getLength(); n++) {
            Node packField = packFields.item(n);
            if (packField.getNodeType() == Node.ELEMENT_NODE) {
                ElementsEnum currentField = ElementsEnum.valueOf(
                        ((Element)packField).getTagName().toUpperCase());
                switch (currentField) {
                    case QUANTITY:
                        currentPack.setQuantity(Integer.parseInt(
                                packField.getTextContent()));
                        break;
                    case PRICE:
                        currentPack.setPrice(Double.parseDouble(
                                packField.getTextContent()));
                        break;
                    default:
                        String errorMessage = "element <" 
                                + currentField
                                + "> is not supposed to be here";
                        LOG.error(errorMessage);
                        throw new BuildPackException(errorMessage);
                }
            }
        }
        return currentPack;
    }

    /**
     * Runs through all child-nodes of passed DOM-element and builds 
     * Dosage object depending on it
     * 
     * @param dosageElement - DOM-element that represents dosage for version 
     * of concrete medicine
     * @return {@link Dosage} object
     * @throws BuildDosageException 
     */
    private Dosage buildDosage(Element dosageElement)
            throws BuildDosageException {
        Dosage currentDosage = new Dosage();
        NodeList dosageFields = dosageElement.getChildNodes();
        for (int k = 0; k < dosageFields.getLength(); k++) {
            Node dosageField = dosageFields.item(k);
            if (dosageField.getNodeType() == Node.ELEMENT_NODE) {
                ElementsEnum currentField = ElementsEnum.valueOf(
                        ((Element)dosageField).getTagName().toUpperCase());
                switch (currentField) {
                    case AMOUNT:
                        currentDosage.setAmount(
                                dosageField.getTextContent());
                        break;
                    case FREQUENCY:
                        currentDosage.setFrequency(
                                dosageField.getTextContent());
                        break;
                    default:
                        String errorMessage = "element <" 
                                + currentField
                                + "> is not supposed to be here";
                        LOG.error(errorMessage);
                        throw new BuildDosageException(errorMessage);
                }
            }
        }
        return currentDosage;
    }
}