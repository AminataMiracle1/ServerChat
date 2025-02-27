import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8888);
             BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            String serverResponse;
            while ((serverResponse = socketIn.readLine()) != null) {
                System.out.println(serverResponse);
                if (serverResponse.contains("Entrer votre user name")) {
                    String username = userInput.readLine();
                    socketOut.println(username);
                } else if (serverResponse.contains("Écriver votre message")) {
                    String message;
                    while (!(message = userInput.readLine()).isEmpty()) {
                        socketOut.println(message);
                        if (message.equals("LOG")) {
                            System.out.println(socketIn.readLine());
                            System.out.println(socketIn.readLine());
                        }
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
