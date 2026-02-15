package Network;

import Models.Senior;

public class XMLBuilder {

    public static String buildAddSeniorXML(Senior s) {

        return "<AddSenior>" +
                "<Name>" + s.getSeniorName() + "</Name>" +
                "<Age>" + s.getAge() + "</Age>" +
                "<Address>" + s.getAddress() + "</Address>" +
                "</AddSenior>";
    }

    // Builds XML request for sending vital signs to the server
    public static String buildVitalsXML(int hr, int sys, int dia, double glucose) {

        return "<request><type>vitals</type>" +
                "<hr>" + hr + "</hr>" +
                "<sys>" + sys + "</sys>" +
                "<dia>" + dia + "</dia>" +
                "<glucose>" + glucose + "</glucose>" +
                "</request>";
    }

    // Builds XML request for prescribing medication
    public static String buildMedicationXML(String n, String d, String f, String i) {

        return "<request><type>medication</type>" +
                "<name>" + n + "</name>" +
                "<dosage>" + d + "</dosage>" +
                "<frequency>" + f + "</frequency>" +
                "<instructions>" + i + "</instructions>" +
                "</request>";
    }

    // Builds XML request for searching seniors
    public static String buildSearchXML(String key) {

        return "<request><type>search</type>" +
                "<key>" + key + "</key></request>";
    }
}

