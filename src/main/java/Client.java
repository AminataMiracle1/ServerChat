import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        // Créer une connexion
        try {
            Socket socket = new Socket("localhost", 8888);
            // Obtenir les flux d'entrée et de sortie
            PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Le server attend un commande
            Scanner console = new Scanner(System.in);
            System.out.println("Please enter your name: ");
            socketOut.println(console.nextLine());

            socket.close();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
