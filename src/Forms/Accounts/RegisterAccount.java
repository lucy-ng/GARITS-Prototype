package Forms.Accounts;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");
                    //Statement statement = connection.createStatement();

                    String usernameText = username.getText();
                    String firstNameText = firstName.getText();
                    String secondNameText = secondName.getText();
                    String positionText = role.getText();

                            /*
                            String insertQuery = "insert into EmployeeAccount (username, firstName, secondName, position) values("+usernameText+", "+firstNameText+", "+secondNameText+", "+positionText+")" ;
                            statement.executeUpdate(insertQuery);

                             */

                    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO EmployeeAccount VALUES (?,?,?,?)")) {
                        statement.setString(1, usernameText);
                        statement.setString(2, firstNameText);
                        statement.setString(3, secondNameText);
                        statement.setString(4, positionText);
                        statement.executeUpdate();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
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
