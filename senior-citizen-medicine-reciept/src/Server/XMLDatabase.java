package Server;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLDatabase {

    // Ensure your "res" folder exists at the root of your project
    private static final String MED_FILE = "res/medicine_inventory.xml";
    private static final String SENIOR_FILE = "res/senior_records.xml";

    // --- Concurrency Control ---
    // The 'synchronized' keyword ensures that if two nurses try to prescribe
    // the last medicine at the same millisecond, they are queued one by one.
    public static synchronized String prescribeMedication(String xmlRequest) {
        String name = extractTag(xmlRequest, "name");
        int qtyToDeduct = 1; // Assuming 1 unit per prescription for simplicity

        try {
            File inputFile = new File(MED_FILE);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList meds = doc.getElementsByTagName("Medicine");
            for (int i = 0; i < meds.getLength(); i++) {
                Element med = (Element) meds.item(i);
                String medName = med.getElementsByTagName("Name").item(0).getTextContent();

                if (medName.equalsIgnoreCase(name)) {
                    int stock = Integer.parseInt(med.getElementsByTagName("Stock").item(0).getTextContent());

                    if (stock >= qtyToDeduct) {
                        stock -= qtyToDeduct; // Deduct the stock
                        med.getElementsByTagName("Stock").item(0).setTextContent(String.valueOf(stock));

                        saveXML(doc, MED_FILE); // Persist to XML Database
                        return "Success! Medicine prescribed. Remaining stock: " + stock;
                    } else {
                        return "Error: Insufficient stock for " + name;
                    }
                }
            }
            return "Error: Medicine not found in inventory.";
        } catch (Exception e) {
            return "Database Error: " + e.getMessage();
        }
    }

    // --- Concurrency Control ---
    public static synchronized String addSenior(String xmlRequest) {
        String name = extractTag(xmlRequest, "Name");
        String age = extractTag(xmlRequest, "Age");
        String address = extractTag(xmlRequest, "Address");

        try {
            File inputFile = new File(SENIOR_FILE);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();

            // Create new Senior Element
            Element newSenior = doc.createElement("Senior");
            newSenior.setAttribute("id", String.valueOf(System.currentTimeMillis()).substring(8)); // Basic random ID

            Element nameElem = doc.createElement("seniorName");
            nameElem.appendChild(doc.createTextNode(name));
            newSenior.appendChild(nameElem);

            Element ageElem = doc.createElement("age");
            ageElem.appendChild(doc.createTextNode(age));
            newSenior.appendChild(ageElem);

            Element addressElem = doc.createElement("address");
            addressElem.appendChild(doc.createTextNode(address));
            newSenior.appendChild(addressElem);

            root.appendChild(newSenior);

            saveXML(doc, SENIOR_FILE);
            return "Success! Senior added to database.";
        } catch (Exception e) {
            return "Database Error: " + e.getMessage();
        }
    }

    // Utility to save Document back to XML File
    private static void saveXML(Document doc, String filePath) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
    }

    // Utility to parse simple XML strings from the Client
    public static String extractTag(String xml, String tag) {
        String startTag = "<" + tag + ">";
        String endTag = "</" + tag + ">";
        int start = xml.indexOf(startTag);
        int end = xml.indexOf(endTag);
        if (start != -1 && end != -1) {
            return xml.substring(start + startTag.length(), end);
        }
        return "Unknown";
    }
}