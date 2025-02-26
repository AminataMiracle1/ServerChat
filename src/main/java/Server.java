import com.google.gson.Gson;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static final String FICHIER = "message.json";
    private static ArrayList listeMessages = new ArrayList<>();
    // Appeler la fonction

    public static void main(String[] args) {
        lireFichier(); // Charger les messages depuis le fichier
        try (ServerSocket serverSocket = new ServerSocket(8888)) {

            while (true) {
                Socket Clientsocket = serverSocket.accept();
                //// Créer un utilisateur à chaque fois qu'une personne se Connecte
                Message message = new Message();
                // Cette information pour envoyer au client
                PrintWriter socketOut = new PrintWriter(Clientsocket.getOutputStream(), true);
                // C'est ce que nous recevons du client
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(Clientsocket.getInputStream()));
                // Les messages entrer par les clients
                String ligne;

                socketOut.println("Bienvenue sur chat");
                socketOut.println("Nombre de messages reçus : " + staticMessage());
                socketOut.println("Entrer votre user name");
                String username = socketIn.readLine();
                message.setUserName(username);
                socketOut.println("Bonjour : " + message.getUserName());

                socketOut.println("Écriver votre message");
                while ((ligne = socketIn.readLine()) != null && !ligne.isEmpty()) {
                    // ecrire le message
                    message.ecrireUnMessage(ligne);
                    if (ligne.equals("LOG")) {
                        socketOut.println("*********l'historique des messages*********");
                        socketOut.println(listeMessages.toString());
                    }
                }
                System.out.println("_____Message____ ");
                System.out.println("Utilisateur : " + message.getUserName());
                System.out.println(message.getUnMessage());
                listeMessages.add(message);
                System.out.println("____fin________");
                // Fermer la connexion avec le client et l'enregistrer avant que le server se ferme
                sauvegarderMessage();
                Clientsocket.close();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Enregistrer les message de  cet objet
    public static void sauvegarderMessage() {
        // Écriture vers un fichier un message
        try {
            Gson gson = new Gson();
            String json = gson.toJson(listeMessages, ArrayList.class);
            FileWriter fileWriter = new FileWriter(FICHIER);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // Désérialisation
    public static void lireFichier() {
        // Vérifier que le fichier json exist
        try {
            Gson gson = new Gson();
            // Vérifier d'abord que le fichier n'est pas  vide
            BufferedReader br = new BufferedReader(new FileReader(FICHIER));
            listeMessages = gson.fromJson(br, ArrayList.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // C'est le log des messages
    public static int staticMessage() {
        return listeMessages.size();
    }


}
