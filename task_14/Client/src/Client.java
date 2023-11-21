import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        String serverIP = "127.0.0.1";
        int port = 11111;

        try (Socket socket = new Socket(serverIP, port);
             Scanner scanner = new Scanner(System.in);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             Scanner serverScanner = new Scanner(socket.getInputStream())) {

            System.out.print("Введите никнейм: ");
            String nickname = scanner.nextLine();

            Thread serverListener = new Thread(new Listener(serverScanner));

            serverListener.start();

            while (true) {
                String userMessage = scanner.nextLine();
                writer.println("@" + nickname + ": " + userMessage);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

