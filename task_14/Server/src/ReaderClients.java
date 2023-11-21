import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class ReaderClients implements Runnable {
    private final Socket clientSocket;

        public ReaderClients(Socket socket) {
        this.clientSocket = socket;

    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(clientSocket.getInputStream())) {
            while (true) {
                try {
                    String message = scanner.nextLine();
                    for (PrintWriter client : Server.clients) {
                        client.println(message);
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Пользователь завершил диалог");
                    break;
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
