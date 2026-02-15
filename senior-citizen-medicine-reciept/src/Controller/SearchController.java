package Controller;

import Network.SocketClient;
import Network.XMLBuilder;

/*
 * Handles search requests from the GUI.
 */

public class SearchController {

    public static String search(String keyword) {

        String xml = XMLBuilder.buildSearchXML(keyword);

        return SocketClient.sendRequest(xml);
    }
}
