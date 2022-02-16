package Forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAccount extends JFrame {
    private JLabel loginTitle;
    private JTextField username;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JButton loginButton;
    private JPanel mainPanel;

    public LoginAccount() {
        setContentPane(mainPanel);
        setTitle("Login to Account");
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPage adminPage = new AdminPage();
            }
        });
    }
}
