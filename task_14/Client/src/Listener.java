
import java.util.Scanner;

public class Listener implements Runnable{
    private final Scanner serverScanner;

    Listener(Scanner serverScanner){
        this.serverScanner = serverScanner;
    }

    @Override
    public void run() {
        while (serverScanner.hasNextLine()) {
            String message = serverScanner.nextLine();
            System.out.println(message);
        }
    }
}
