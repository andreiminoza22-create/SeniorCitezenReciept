package Network;

import java.io.*;
import java.net.Socket;

public class SocketClient {

    public static String sendRequest(String xml) {

        try(Socket socket = new Socket("localhost", 5000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()))) {

            out.println(xml);

            return in.readLine();

        } catch(Exception e){
            return "Connection Error: " + e.getMessage();
        }
    }
}
