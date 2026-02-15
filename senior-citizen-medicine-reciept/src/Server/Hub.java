package Server;

import java.io.*;
import java.util.Date;
import java.net.*;

public class Hub {
    private static boolean isRunning = false;

    public void startServer(int port) {
        isRunning = true;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        }catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)){

                String xmlRequest = in.readLine();

                System.out.println("[" + new Date() + "] Transaction: " + xmlRequest);

                out.println("<Response>Success</Response>");
            } catch (IOException e) {
                e.printStackTrace();
        }

    }
}
    }
