package Controller;

import Models.Senior;
import Network.SocketClient;
import Network.XMLBuilder;
import Utils.Validator;

public class SeniorController {

    public static String addSenior(Senior senior) {
        // Requirement 4.A: Age must be between 1 and 129
        if (!Validator.validAge(senior.getAge())) {
            return "Invalid age: Must be between 1 and 129.";
        }

        if (!Validator.notEmpty(senior.getSeniorName()) || !Validator.notEmpty(senior.getAddress())) {
            return "Registration Error: Name and Address are required.";
        }

        String xml = XMLBuilder.buildAddSeniorXML(senior);

        return SocketClient.sendRequest(xml);
    }

    public static String updateProfile(String name, String phone, String emergencyContact) {
        if (!Validator.notEmpty(phone) || !Validator.notEmpty(emergencyContact)) {
            return "Contact fields cannot be empty.";
        }

        String xml = XMLBuilder.buildUpdateProfileXML(name, phone, emergencyContact);

        return SocketClient.sendRequest(xml);
    }
}