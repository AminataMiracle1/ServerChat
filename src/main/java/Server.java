import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static final String FICHIER = "message.json";
    private static ArrayList<Message> messages;

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

                    if (ligne.equals("static")) {
                        System.out.println("La tailles des messages : "  + staticMessage () );
                    }

                }
                System.out.println("_____Message____ " );
                System.out.println(message.getUnMessage());
                System.out.println("____fin________");
                // Fermer la connexion avec le client
                // Enregistrer le message
                sauvegarderMessage();
                Clientsocket.close();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Enregistrer les message de  cet objet
    public static void  sauvegarderMessage(){
        // Écriture vers un fichier un message
        try {
            Gson gson = new Gson();
            String json = gson.toJson(messages, ArrayList.class);
            FileWriter fileWriter = new FileWriter(FICHIER);
            fileWriter.write(gson.toJson(json));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int staticMessage () {
        return messages.size();
    }

    // Retourner la historique des nessages


}
