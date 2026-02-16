package Controller;

import Network.SocketClient;
import Network.XMLBuilder;

public class MedicationController {

    public static String prescribe(String n, String d, String f, String i, String role) {
        // Requirement 4.C: Verify Role
        if (role.equalsIgnoreCase("Senior") || role.equalsIgnoreCase("Caregiver")) {
            return "Access Denied: Seniors/Caregivers cannot create prescriptions.";
        }

        // Requirement 4.A: Input Integrity check
        if (n.trim().isEmpty() || d.trim().isEmpty() || f.trim().isEmpty()) {
            return "Error: Medicine name, dosage, and frequency are required.";
        }

        // Updated to pass 'role' to the XMLBuilder
        String xml = XMLBuilder.buildMedicationXML(n, d, f, i, role);

        return SocketClient.sendRequest(xml);
    }

    public static String changeDosage(String seniorId, String medName, String newDosage, String role) {
        // Requirement 4.C: Only Doctors should modify medication details
        if (!role.equalsIgnoreCase("Doctor")) {
            return "Access Denied: Only doctors can change medication dosages.";
        }

        String xml = XMLBuilder.buildChangeDosageXML(seniorId, medName, newDosage);
        return SocketClient.sendRequest(xml);
    }

    public static String discontinueMedication(String seniorId, String medName, String role) {
        // Requirement 4.C: Verify authorization for medical history changes
        if (!role.equalsIgnoreCase("Doctor")) {
            return "Access Denied: Only doctors can discontinue medications.";
        }

        // Uses the builder that includes <status>inactive</status>
        String xml = XMLBuilder.buildDiscontinueMedXML(seniorId, medName);
        return SocketClient.sendRequest(xml);
    }
}