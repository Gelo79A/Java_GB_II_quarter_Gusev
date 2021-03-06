package lesson_6_networking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EchoClient extends JFrame {

    private final String SERVER_ADRES = "localhost";
    private final int SERVER_PORT = 8089;

    private  JTextField textField;
    private JTextArea textArea;

    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    private void openConnection() throws IOException {
        socket = new Socket(SERVER_ADRES, SERVER_PORT);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        new Thread(new Runnable() {
            @Override
            public void run() {
try {
    while (true) {
        String massageFromServer = dataInputStream.readUTF();
        if (massageFromServer.equals("/end")) {
            break;
        }
        textArea.append(massageFromServer);
        textArea.append("/n");
    }

} catch (Exception ex) {
    ex.printStackTrace();
}
            }
        }).start();
    }

    private void sendMessage() {
        if (textField.getText().isEmpty()) {
            return;
        }
        try {
            dataOutputStream.writeUTF(textField.getText());
            textField.setText("");
            textField.grabFocus();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            dataOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            dataInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            socket.close();
        } catch (Exception ex) {

        }
    }

    public EchoClient() {
        prepareUI();
    }

    private void prepareUI() {
        setBounds(200, 200, 500, 500);
        setTitle("EchoClient");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);


        JPanel panel = new JPanel(new BorderLayout());
        JButton button = new JButton("Send");
        panel.add(button, BorderLayout.EAST);
        textField = new JTextField();
        panel.add(textField, BorderLayout.CENTER);

        add(panel, BorderLayout.SOUTH);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMessage();
            }
        });
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMessage();
            }
        });


        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EchoClient::new); // SwingUtilities.invokeLater(new Runnable(){@Override pablic void Run(){new EhoClient()}});
    }
}
