import javax.swing.*;
import java.awt.event.ActionListener;

public class ClientView {
    private JPanel mainPanel;
    private JTextField clientNameTextField;
    private JTextField messageTextField;
    private JButton sendButton;
    private JLabel clientName;
    private JTextArea chatTextArea;
    private JButton loginButton;
    private JButton logoutButton;

    private Client myClient;

    public ClientView(String serverName, int port) {

        logoutButton.setEnabled(false);
        sendButton.setEnabled(false);
        messageTextField.setEnabled(false);
        loginButton.addActionListener(e -> {
            try {
                String username = clientNameTextField.getText().trim();
                if(username.length() == 0)
                    return;

                // try creating a new Client with GUI
                myClient = new Client(serverName,
                        clientNameTextField.getText(),
                        port,
                        this);
                myClient.start();

                // disable login button
                loginButton.setEnabled(false);
                // enable the 2 buttons
                logoutButton.setEnabled(true);
                sendButton.setEnabled(true);
                messageTextField.setEnabled(true);
                logoutButton.addActionListener(f -> {
                    try {
                        myClient.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));
                        return;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Blad wylogowania!");
                    }
                });
                sendButton.addActionListener(g -> {
                    try {
                        myClient.sendMessage(new ChatMessage(ChatMessage.MESSAGE, messageTextField.getText()));
                        messageTextField.setText("");
                        return;

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Blad wysylania!");
                    }
                });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Blad logowania!");
            }
        });

    }
    public void append(String str) {
        chatTextArea.append(str);
        chatTextArea.setCaretPosition(chatTextArea.getText().length() - 1);
    }
    void connectionFailed() {
        loginButton.setEnabled(true);
        logoutButton.setEnabled(false);
        clientNameTextField.setToolTipText("Enter your username here...");
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Chat");
        ClientView form = new ClientView("localhost", 2000);
        mainFrame.setContentPane(form.mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setSize(400, 300);
        mainFrame.setVisible(true);
    }
}
