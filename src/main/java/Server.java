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
                String username = socketIn.readLine();
                message.setUserName(username);
                socketOut.println("Bonjour : " + message.getUnMessage());
                socketOut.println("Écriver votre message");
                //
                while ((ligne = socketIn.readLine()) != null && !ligne.isEmpty()) {
                    // Sauvegarder le message
                    message.ecrireUnMessage(ligne);
                    listeMessages.add(message);
                    if (ligne.equals("static")) {
                        socketOut.println("La taille des messages : " + staticMessage());  // Afficher le nombre de messages
                    }
                }
                System.out.println("_____Message____ ");
                System.out.println("Utilisateur : " + message.getUserName());
                System.out.println(message.getUnMessage());
                socketOut.println(listeMessages.toString());
                System.out.println("____fin________");
                // Fermer la connexion avec le client
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
            fileWriter.write(gson.toJson(json));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Désérialisation
    public static void lireFichier() {
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(FICHIER));
            etudiantsRecuperes = gson.fromJson(br, Etudiant[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // C'est le log des messages
    public static ArrayList<Message> getListeMessages() {
        return listeMessages;
    }


}
