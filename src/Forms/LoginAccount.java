package Forms;

import javax.swing.*;

public class LoginAccount {
    private JLabel loginTitle;
    private JTextField username;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JButton loginButton;
    private JPanel mainPanel;

    public LoginAccount() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}