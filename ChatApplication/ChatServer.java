package ChatApplication;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public ChatServer() throws IOException {
        serverSocket = new ServerSocket(8000);
        clientSocket = serverSocket.accept();
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void start() {
        Thread serverThread = new Thread(new ServerThread());
        serverThread.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Server: ");
            String message = scanner.nextLine();
            out.println(message);
        }
    }

    private class ServerThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    String clientMessage = in.readLine();
                    System.out.println("Client: " + clientMessage);
                }
            } catch (IOException e) {
                System.out.println("Error reading from client: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ChatServer chatServer = new ChatServer();
        chatServer.start();
    }
}