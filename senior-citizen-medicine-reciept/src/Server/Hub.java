package Server;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hub extends JFrame {
    private ServerSocket serverSocket;
    private boolean isRunning = false;
    private JTextArea logArea;
    private JButton startBtn, stopBtn;

    public Hub() {
        // --- Manual Controls GUI Setup ---
        setTitle("Senior Care Server Hub");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(logArea), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        startBtn = new JButton("Start Server");
        stopBtn = new JButton("Stop Server");
        stopBtn.setEnabled(false);

        controlPanel.add(startBtn);
        controlPanel.add(stopBtn);
        add(controlPanel, BorderLayout.SOUTH);

        // Action Listeners for Manual Controls
        startBtn.addActionListener(e -> startServer(5000));
        stopBtn.addActionListener(e -> stopServer());
    }

    // --- Logger ---
    public synchronized void log(String userIp, String transactionType, String dataAffected, String message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String logEntry = String.format("[%s] User: %s | Type: %s | Data: %s | Msg: %s\n",
                timestamp, userIp, transactionType, dataAffected, message);

        SwingUtilities.invokeLater(() -> logArea.append(logEntry));
    }

    // --- Socket Hub ---
    public void startServer(int port) {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                isRunning = true;
                log("SYSTEM", "START", "Server", "Server started on port " + port);

                SwingUtilities.invokeLater(() -> {
                    startBtn.setEnabled(false);
                    stopBtn.setEnabled(true);
                });

                // Wait for clients
                while (isRunning) {
                    Socket clientSocket = serverSocket.accept();
                    // Threading: Spawn a new thread for every client connection
                    new Thread(new ClientHandler(clientSocket, this)).start();
                }
            } catch (SocketException e) {
                log("SYSTEM", "STOP", "Server", "Server stopped.");
            } catch (IOException e) {
                log("SYSTEM", "ERROR", "Server", e.getMessage());
            }
        }).start();
    }

    public void stopServer() {
        isRunning = false;
        try {
            if (serverSocket != null) serverSocket.close();
            SwingUtilities.invokeLater(() -> {
                startBtn.setEnabled(true);
                stopBtn.setEnabled(false);
            });
        } catch (IOException e) {
            log("SYSTEM", "ERROR", "Server", "Failed to close socket: " + e.getMessage());
        }
    }

    // --- Threading: Client Handler ---
    private class ClientHandler implements Runnable {
        private Socket socket;
        private Hub hub;

        public ClientHandler(Socket socket, Hub hub) {
            this.socket = socket;
            this.hub = hub;
        }

        @Override
        public void run() {
            String clientIp = socket.getInetAddress().getHostAddress();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                String xmlRequest = in.readLine();
                if (xmlRequest != null) {
                    String response = processRequest(xmlRequest, clientIp);
                    out.println(response);
                }
            } catch (IOException e) {
                hub.log(clientIp, "ERROR", "Connection", e.getMessage());
            }
        }

        private String processRequest(String xml, String ip) {
            // Route the XML string to the correct database operation
            if (xml.contains("<AddSenior>")) {
                hub.log(ip, "INSERT", "senior_records.xml", "Adding new senior");
                return XMLDatabase.addSenior(xml);
            } else if (xml.contains("<type>medication</type>")) {
                String medName = XMLDatabase.extractTag(xml, "name");
                hub.log(ip, "UPDATE", "medicine_inventory.xml", "Prescribing: " + medName);
                return XMLDatabase.prescribeMedication(xml);
            } else if (xml.contains("<type>vitals</type>")) {
                hub.log(ip, "INSERT", "senior_records.xml", "Logging vitals");
                return "<Response>Vitals successfully logged</Response>"; // Placeholder for vitals DB logic
            } else if (xml.contains("<type>search</type>")) {
                String key = XMLDatabase.extractTag(xml, "key");
                hub.log(ip, "SELECT", "senior_records.xml", "Searching for: " + key);
                return "<Response>Search results for " + key + "</Response>"; // Placeholder for search logic
            }
            return "<Response>Error: Unknown Request</Response>";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Hub().setVisible(true));
    }
}