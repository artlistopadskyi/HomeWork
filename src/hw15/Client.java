package hw15;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8989);

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writerInServer = new PrintWriter(socket.getOutputStream(), true);

        Chat chat = new Chat();
        chat.createGUI();

        chat.textField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (chat.textField.getText().trim().isEmpty()) {
                        return;
                    }
                    writerInServer.println(chat.textField.getText());
                    chat.textField.setText("");
                }
            }
        });

        String dataFromServer;
        while ((dataFromServer = reader.readLine()) != null) {
            System.out.println(dataFromServer);
            chat.textArea.append(dataFromServer + "\n");
        }
        reader.close();
        writerInServer.close();
        socket.close();
    }

    static class Chat extends JFrame {
        private final JTextArea textArea;
        private final JPanel panel;
        private final JTextField textField;

        public Chat() {
            setTitle("Chat");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.panel = new JPanel();
            this.textField = new JTextField();
            this.textArea = new JTextArea();
        }

        private void createGUI() {
            panel.setLayout(new GridLayout(1, 1));
            panel.add(textField);
            add(panel, BorderLayout.SOUTH);

            textArea.setEditable(false);
            textArea.setMargin(new Insets(7, 7, 7, 7));

            JScrollPane scrollPane = new JScrollPane(textArea);
            add(scrollPane);

            addWindowListener(new WindowAdapter() {
                public void windowOpened(WindowEvent we) {
                    textField.requestFocus();
                }
            });

            setSize(400, 400);
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }
}
