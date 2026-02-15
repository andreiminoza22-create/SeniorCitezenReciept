package Controller;

import Network.SocketClient;
import Network.XMLBuilder;

/*
 * Controller for medication-related actions.
 */

public class MedicationController {

    public static String prescribe(String n, String d, String f, String i) {

        String xml = XMLBuilder.buildMedicationXML(n,d,f,i);

        return SocketClient.sendRequest(xml);
    }
}
