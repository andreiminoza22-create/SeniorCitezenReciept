package Network;

import Models.Senior;

/**
 * Utility class to build XML strings for client-server communication.
 * Updated to support role-based transactions and all functional requirements.
 */
public class XMLBuilder {

    public static String buildAddSeniorXML(Senior s) {
        return "<request>" +
                "<type>addSenior</type>" +
                "<name>" + s.getSeniorName() + "</name>" +
                "<age>" + s.getAge() + "</age>" +
                "<address>" + s.getAddress() + "</address>" +
                "</request>";
    }

    public static String buildMedicationXML(String n, String d, String f, String i, String role) {
        return "<transaction>" +
                "<role>" + role + "</role>" +
                "<request>" +
                "<type>medication</type>" +
                "<name>" + n + "</name>" +
                "<dosage>" + d + "</dosage>" +
                "<frequency>" + f + "</frequency>" +
                "<instructions>" + i + "</instructions>" +
                "</request>" +
                "</transaction>";
    }


    public static String buildVitalsXML(int hr, int sys, int dia, double glucose, String role) {
        return "<transaction>" +
                "<role>" + role + "</role>" +
                "<request>" +
                "<type>vitals</type>" +
                "<hr>" + hr + "</hr>" +
                "<sys>" + sys + "</sys>" +
                "<dia>" + dia + "</dia>" +
                "<glucose>" + glucose + "</glucose>" +
                "</request>" +
                "</transaction>";
    }

    public static String buildSearchXML(String key) {
        return "<request>" +
                "<type>search</type>" +
                "<key>" + key + "</key>" +
                "</request>";
    }

    public static String buildInventoryRequestXML(String medicineId) {
        return "<request>" +
                "<type>inventory</type>" +
                "<id>" + medicineId + "</id>" +
                "</request>";
    }

    public static String buildUpdateStockXML(String medicineId, int newStock) {
        return "<request>" +
                "<type>updateStock</type>" +
                "<id>" + medicineId + "</id>" +
                "<stockCount>" + newStock + "</stockCount>" +
                "</request>";
    }

    public static String buildChangeDosageXML(String seniorId, String medName, String newDosage) {
        return "<request>" +
                "<type>updateMedication</type>" +
                "<seniorId>" + seniorId + "</seniorId>" +
                "<name>" + medName + "</name>" +
                "<dosage>" + newDosage + "</dosage>" +
                "</request>";
    }

    public static String buildDiscontinueMedXML(String seniorId, String medName) {
        return "<request>" +
                "<type>discontinueMed</type>" +
                "<seniorId>" + seniorId + "</seniorId>" +
                "<name>" + medName + "</name>" +
                "<status>inactive</status>" +
                "</request>";
    }

    public static String buildUpdateProfileXML(String name, String phone, String emergencyContact) {
        return "<request>" +
                "<type>updateProfile</type>" +
                "<name>" + name + "</name>" +
                "<phone>" + phone + "</phone>" +
                "<emergencyContact>" + emergencyContact + "</emergencyContact>" +
                "</request>";
    }
}