import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        // Demander le client de rentrer l'adresse ip et le port
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez l'adresse IP : ");
        String ip = sc.nextLine();
        System.out.println("Entrez l'adresse port : ");
        int port =  sc.nextInt();
        Socket socket = connecterClient(ip, port);

        try (
             BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            String serverResponse = socketIn.readLine();
//            System.out.println(serverResponse);
//            infoGeneraleChat(serverResponse);

//            while ((serverResponse) != null) {
                infoGeneraleChat(serverResponse);

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
//                    break;
                }
            } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    // une méthode qui vérifier si le client est connecté
    public static Socket connecterClient(String ip, int port) {
        if (ip.isEmpty() && port <= 0) {
            ip = "127.0.0.1";
            port = 8888;
            try {
                Socket socket = new Socket(ip,port);
                return socket;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                Socket socket = new Socket(ip,port);
                return socket;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    // Afficher les info générale du server
    public static void infoGeneraleChat(String scokeIn) {
        System.out.println("Vous êtes connecter à chat ! Bienvenu");
        System.out.println("La Static du chat" + scokeIn );

    }



}

