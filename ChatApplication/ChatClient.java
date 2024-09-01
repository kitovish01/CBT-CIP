package ChatApplication;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public ChatClient() throws IOException {
        clientSocket = new Socket("localhost", 8000);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void start() {
        Thread clientThread = new Thread(new ClientThread());
        clientThread.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Client: ");
            String message = scanner.nextLine();
            out.println(message);
        }
    }

    private class ClientThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    String serverMessage = in.readLine();
                    System.out.println("Server: " + serverMessage);
                }
            } catch (IOException e) {
                System.out.println("Error reading from server: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ChatClient chatClient = new ChatClient();
        chatClient.start();
    }
}