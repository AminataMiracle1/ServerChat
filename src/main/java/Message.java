import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;

public class Message {
    private String userName ;
    private String dateMessage ;
    private StringBuilder unMessage = new StringBuilder() ;
    private ArrayList<String> listeMessages = new ArrayList<String>() ;
    public String getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(String dateMessage) {
        this.dateMessage = dateMessage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public StringBuilder getUnMessage() {
        return unMessage;
    }

    public void setUnMessage(StringBuilder unMessage) {
        this.unMessage = unMessage;
    }

    public Message(String userName, String dateMessage, StringBuilder messageClient) {
        this.userName = userName;
        this.dateMessage = dateMessage;
        this.unMessage = messageClient;
    }
    public Message() {}

    public ArrayList<String> getListeMessages() {
        return listeMessages ;
    }
    public void setListeMessages(ArrayList<String> listeMessages) {
        this.listeMessages = listeMessages;
    }


    // Méthode pour écrire dans la listes de messages du client.
    public void ecrireUnMessage(String message) {
        listeMessages.add(message);
    }

    // Enregistrer les message de  cet objet
    public void sauvegarderMessage(String fichier){
        // Écriture vers un fichier un message
        try {
            Gson gson = new Gson();
            String json = gson.toJson(listeMessages, ArrayList.class);
            FileWriter fileWriter = new FileWriter(fichier);
            fileWriter.write(gson.toJson(json));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}
