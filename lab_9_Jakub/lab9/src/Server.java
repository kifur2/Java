import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static ArrayList<ClientThread> clientsList;
    private static int uID;
    private final int port;
    private boolean isOn;

    public Server(int port) {
        this.port = port;
        clientsList = new ArrayList<>();
    }

    public void start() {
        isOn = true;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (isOn) {
                System.out.println("Server waiting for clients...");
                Socket socket = serverSocket.accept();
                if (!isOn)
                    break;
                ClientThread clientThread = new ClientThread(socket); //przykładowa obsługa żądania
                clientsList.add(clientThread);
                clientThread.start();
            }
            try {
                serverSocket.close();
                for (ClientThread tmpClientThread : clientsList) {
                    tmpClientThread.in.close();
                    tmpClientThread.out.close();
                    tmpClientThread.socket.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static synchronized void sendEveryone(String msg) {
        System.out.println(msg);
        for (int i = clientsList.size() - 1; i >= 0; i--) {
            ClientThread tmpClientThread = clientsList.get(i);
            if (!tmpClientThread.writeMsg(msg)) {
                clientsList.remove(i);
                System.out.println(tmpClientThread.loginName + " disconnected.");
            }
        }
    }

    public static class ClientThread extends Thread {

        private int id;
        private Socket socket;
        private ObjectInputStream in;
        private ObjectOutputStream out;
        String loginName;
        ChatMessage chatMessage;

        public ClientThread(Socket socket) throws IOException {
            try {
                id=++uID;
                this.socket = socket;
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                loginName = (String) in.readObject();
                System.out.println(loginName + " connected.");
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public void run() {
            boolean isOn = true;
            while (isOn) {
                try {
                    chatMessage = (ChatMessage) in.readObject();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                String mess = chatMessage.getMessage();
                switch (chatMessage.getType()) {
                    case ChatMessage.MESSAGE:
                        if(!mess.equals(""))
                            sendEveryone(loginName + ": " + mess + "\n");
                        break;
                    case ChatMessage.LOGOUT:
                        System.out.println(loginName + " disconnected using logout");
                        isOn = false;
                        break;
                }
            }
            for(int i = 0; i < clientsList.size(); ++i) {
                ClientThread tmpClientThread = clientsList.get(i);
                // found it
                if(tmpClientThread.id == id) {
                    clientsList.remove(i);
                }
            }
            close();
        }

        private void close() {
            try {
                if (out != null) out.close();
            } catch (Exception ignored) {
            }
            try {
                if (in != null) in.close();
            } catch (Exception ignored) {
            }
            try {
                if (socket != null) socket.close();
            } catch (Exception ignored) {
            }
        }

        public boolean writeMsg(String msg) {
            if (!socket.isConnected()) {
                close();
                return false;
            }
            try {
                out.writeObject(msg);
            } catch (IOException ex) {
                System.out.println("Error sending message to " + loginName);
                ex.printStackTrace();
            }
            return true;
        }


    }
    public static void main(String[] args) {
        int portNum = 2000;
        Server server = new Server(portNum);
        server.start();
    }
}
