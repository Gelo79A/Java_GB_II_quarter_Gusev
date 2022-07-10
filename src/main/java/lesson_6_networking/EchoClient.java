package lesson_6_networking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EchoClient extends JFrame {

    private final String SERVER_ADRES = "localhost";
    private final int SERVER_POR = 8089;

    private  JTextField textField;
    private JTextArea textArea;

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
                //sendMessage();
            }
        });
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //sendMessage();
            }
        });


        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EchoClient());
    }
}
