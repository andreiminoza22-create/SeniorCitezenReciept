package Controller;

import Network.SocketClient;
import Network.XMLBuilder;

/*
 * Controller class for handling vital sign operations.
 *
 * It connects the GUI (View) to the network layer.
 */

public class VitalsController {

    public static String sendVitals(int hr, int sys, int dia, double glucose) {

        // Convert values into XML request
        String xml = XMLBuilder.buildVitalsXML(hr, sys, dia, glucose);

        // Send XML to server and return server response
        return SocketClient.sendRequest(xml);
    }
}
