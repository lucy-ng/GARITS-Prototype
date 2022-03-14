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
        mainPanel.add(registerTitle);
        mainPanel.add(firstNameLabel);
        mainPanel.add(firstName);
        mainPanel.add(secondNameLabel);
        mainPanel.add(secondName);
        mainPanel.add(usernameLabel);
        mainPanel.add(username);
        mainPanel.add(passwordLabel);
        mainPanel.add(password);
        mainPanel.add(registerButton);
        mainPanel.add(roleLabel);
        mainPanel.add(role);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
