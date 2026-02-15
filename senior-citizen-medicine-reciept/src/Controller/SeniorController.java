package Controller;

import Models.Senior;
import Network.SocketClient;
import Network.XMLBuilder;
import Utils.Validator;

public class SeniorController {

    public static String addSenior(Senior senior){

        if(!Validator.validAge(senior.getAge()))
            return "Invalid age.";

        String xml = XMLBuilder.buildAddSeniorXML(senior);

        return SocketClient.sendRequest(xml);
    }
}
