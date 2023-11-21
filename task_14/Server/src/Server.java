import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class Server {
    protected static Set<PrintWriter> clients = new HashSet<>();

    public static void main(String[] args) {
        int port=11111;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен. Ожидание подключений...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Подключен новый пользователь");

                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                clients.add(writer);

                Thread clientReader = new Thread(new ReaderClients(clientSocket));
                clientReader.start();


            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


