package app_front_end;

import conf.Settings;
import domain.Contact;
import domain.Message;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FrontEnd {
    private final Main main;
    private final ExecutorService executor;
    private final String backendAddress;

    private final int listeningPort;
    private final int requestPort;

    public FrontEnd(Main main, int port, String backendAddress, int backendPort) throws IOException {
        this.main = main;
        executor = Executors.newCachedThreadPool();
        this.backendAddress = backendAddress;
        this.requestPort = backendPort;
        this.listeningPort = port;
    }

    public void startListening() throws IOException {
        ServerSocket listener = new ServerSocket(listeningPort);
        System.out.println("FrontEnd listening on port " + listener.getLocalPort() + "...");
        while (true) {
            try {
                Socket clientSocket = listener.accept();
                executor.execute(new RequestHandler(clientSocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendRequest(String request) throws IOException {
        System.out.println("Trying to send request to: "+backendAddress+":"+requestPort);
        try (Socket socket = new Socket(backendAddress, requestPort);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
            writer.println(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void requestPull() throws IOException {
        sendRequest(Settings.POLL_REQUEST);
    }

    private class RequestHandler implements Runnable {
        private final Socket clientSocket;

        public RequestHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
//                System.out.println(reader.read());
//                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
//                }
                // Read From, To, and Subject from the connection
                String from = reader.readLine();
                String to = reader.readLine();
                String subject = reader.readLine();
                System.out.println("from: "+ from);
                System.out.println("to: "+to);
                System.out.println("subject: "+ subject);
                System.out.println(main);

                // Read the message body as one or more Strings
                StringBuilder messageBody = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    messageBody.append(line);
                }

                // Create the message and add it to the structure for incoming messages
                Message message = new Message(new Contact(from), new Contact(to),LocalDateTime.now(), subject, messageBody.toString());
                Platform.runLater(() -> {
                    main.addToMessageObservableList(message);
                });

                } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}
