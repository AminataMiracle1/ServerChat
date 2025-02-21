import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Server {

    private static final String FICHIER = "message.json";
    private static ArrayList<Message> listeMessages = new ArrayList<>();

    public static void main(String[] args) {

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
                socketOut.println("Entrer votre user name");

                while ((ligne = socketIn.readLine()) != null && !ligne.isEmpty()) {
                    message.setUserName(ligne);
                    // Demander une commande après avoir reçu le message
                    socketOut.println("Utilisate : "  +  message.getUserName());

                    if (ligne.equals("chat")) {
                        // Sauvegarder le message
                        message.ecrireUnMessage(ligne);
                        listeMessages.add(message);
                    } else if (ligne.equals("static")) {
                        socketOut.println("La taille des messages : " + staticMessage());  // Afficher le nombre de messages
                    }
                }
                System.out.println("_____Message____ ");
                System.out.println(message.getUnMessage());
                socketOut.println(listeMessages.toString());
                System.out.println("____fin________");
                // Fermer la connexion avec le client
                sauvegarderMessage();
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
            fileWriter.write(gson.toJson(json));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int staticMessage() {
        return listeMessages.size();
    }

    // historique des messages
    public static ArrayList<Message> getListeMessages() {
        return listeMessages;
    }


}
