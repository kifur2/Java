import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private String clientName;
    private String serverName;
    private Socket socket;
    private int port;
    private ObjectInputStream in;        // to read from the socket
    private ObjectOutputStream out;        // to write on the socket

    private ClientView clientView;

    public Client(String serverName, String clientName, int port, ClientView clientView) {
        this.serverName = serverName;
        this.clientName = clientName;
        this.port = port;
        this.clientView = clientView;
    }

    public void start() {
        try {
            socket = new Socket(serverName, port);
        } catch (Exception ex) {
            ex.printStackTrace();
            disconnect();
        }
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
            disconnect();
        }
        ListenServer listen = new ListenServer();
        listen.start();
        try {
            out.writeObject(clientName);
        } catch (IOException ex) {
            ex.printStackTrace();
            disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void sendMessage(ChatMessage msg) {
        try {
            out.writeObject(msg);
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void disconnect() {
        try {
            if (in != null) in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (out != null) out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (clientView != null)
            clientView.connectionFailed();
    }

    class ListenServer extends Thread {

        public ListenServer(){
            System.out.println("Tu jestem");
        }

        public void run() {
            while (true) {
                try {
                    String msg = (String) in.readObject();
                    clientView.append(msg);
                } catch (IOException e) {
                    System.out.println("Connection closed: " + e);
                    clientView.connectionFailed();
                    break;
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
