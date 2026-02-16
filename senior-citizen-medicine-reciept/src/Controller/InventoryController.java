package Controller;

import Network.SocketClient;
import Network.XMLBuilder;

public class InventoryController {

    public static String checkStock(String medicineId) {
        // You must add buildInventoryRequestXML to XMLBuilder
        String xml = XMLBuilder.buildInventoryRequestXML(medicineId);
        return SocketClient.sendRequest(xml);
    }

    public static String updateStock(String medicineId, int newCount) {
        String xml = XMLBuilder.buildUpdateStockXML(medicineId, newCount);
        return SocketClient.sendRequest(xml);
    }
    public static String buildUpdateStockXML(String medicineId, int newStock) {
        return "<request>" +
                "<type>updateStock</type>" +
                "<id>" + medicineId + "</id>" +
                "<stockCount>" + newStock + "</stockCount>" +
                "</request>";
    }
}