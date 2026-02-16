package Controller;

import Network.SocketClient;
import Network.XMLBuilder;
import Utils.Validator;

/*
 * Controller class for handling vital sign operations.
 * Now includes validation and role-based security enforcement.
 */
public class VitalsController {

    public static String sendVitals(int hr, int sys, int dia, double glucose, String role) {

        // Requirement 4.C: Pharmacists are restricted from modifying health logs
        if (role.equalsIgnoreCase("Pharmacist")) {
            return "Access Denied: Pharmacists cannot modify health logs.";
        }

        // Requirement 4.A: Heart Rate must be between 30 and 220 BPM
        if (!Validator.validHeartRate(hr)) {
            return "Invalid Heart Rate: Must be between 30 and 220 BPM.";
        }

        // Requirement 4.A: Values cannot be negative or physically impossible
        if (sys <= 0 || dia <= 0 || glucose < 0) {
            return "Invalid Input: Vital signs cannot be negative or zero.";
        }

        // Convert values into XML request including the role for server-side verification
        String xml = XMLBuilder.buildVitalsXML(hr, sys, dia, glucose, role);

        // Send XML to server and return server response
        return SocketClient.sendRequest(xml);
    }
}