package Forms.Accounts;

import javax.swing.*;

public class RegisterAccount {
    private JLabel registerTitle;
    private JLabel firstNameLabel;
    private JTextField firstName;
    private JLabel secondNameLabel;
    private JTextField secondName;
    private JLabel usernameLabel;
    private JTextField username;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JButton registerButton;
    private JLabel roleLabel;
    private JTextField role;
    private JPanel mainPanel;

    public RegisterAccount() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    // Getters
    public JTextField getFirstName() {
        return firstName;
    }

    public JTextField getSecondName() {
        return secondName;
    }

    public JTextField getUsername() {
        return username;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public JTextField getRole() {
        return role;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}
