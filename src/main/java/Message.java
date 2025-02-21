import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;

public class Message {
    private String userName ;
    private String dateMessage ;
    private StringBuilder unMessage = new StringBuilder() ;
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




    // Méthode pour écrire dans la listes de messages du client.
    public void ecrireUnMessage(String message) {
        unMessage.append(message) ;
    }
    public String toString() {
        return  " User: " + userName + " Message: " + unMessage.toString() ;
    }






}
