import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final String FICHIER = "message.json";

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(8888)) {

            while (true) {
                Socket Clientsocket = serverSocket.accept();
                //// Créer un utilisateur à chaque fois qu'one personne se Connecte
                Message message = new Message();

                // Cette information pour envoyer au client
                PrintWriter socketOut = new PrintWriter(Clientsocket.getOutputStream(), true);
                // C'est ce que nous recevons du client
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(Clientsocket.getInputStream()));
                // Les messages entrer par les clients
                String ligne;

                socketOut.println("Hello Client");
                while ((ligne = socketIn.readLine()) != null && !ligne.isEmpty()) {
                    // La communication n.est pas terminer
                    message.ecrireUnMessage(ligne);
                    message.sauvegarderMessage(FICHIER);
                }
                System.out.println("_____Message____ " );
                System.out.println(message.getListeMessages() );
                System.out.println("____fin________");
                // Fermer la connexion avec le client
                Clientsocket.close();





            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
