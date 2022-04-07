package Forms.Accounts;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterAccount {
    private JLabel registerTitle;
    private JLabel firstNameLabel;
    private JTextField firstName;
    private JLabel lastNameLabel;
    private JTextField lastName;
    private JLabel usernameLabel;
    private JTextField username;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JButton registerButton;
    private JLabel roleLabel;
    private JTextField role;
    private JPanel mainPanel;
    private JLabel emailLabel;
    private JTextField email;
    private JLabel phoneNumberLabel;
    private JTextField phoneNumber;
    private JTextField department;
    private JLabel departmentLabel;
    private JLabel labourRateLabel;
    private JTextField labourRate;

    public RegisterAccount() {

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String usernameText = username.getText();
                    String firstNameText = firstName.getText();
                    String lastNameText = lastName.getText();
                    String passwordText = String.valueOf(password.getPassword());
                    String emailText = email.getText();
                    String phoneNumberText = phoneNumber.getText();
                    String roleText = role.getText();
                    String departmentText = department.getText();
                    String labourRateText = labourRate.getText();

                    connection.setAutoCommit(false);
                    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO UserAccounts (username, firstName, lastName, password, email, phoneNo) VALUES (?,?,?,?,?,?)")) {
                        statement.setString(1, usernameText);
                        statement.setString(2, firstNameText);
                        statement.setString(3, lastNameText);
                        statement.setString(4, passwordText);
                        statement.setString(5, emailText);
                        statement.setString(6, phoneNumberText);

                        statement.executeUpdate();
                    }

                    try (PreparedStatement stmtInsert = connection.prepareStatement("INSERT INTO EmployeeAccount (EmployeePosition, Department, AccountID, labourRate) VALUES (?,?, LAST_INSERT_ID(), ?)")) {
                        stmtInsert.setString(1, roleText);
                        stmtInsert.setString(2, departmentText);
                        stmtInsert.setString(3, labourRateText);
                        stmtInsert.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "Account created!");
                    connection.setAutoCommit(true);
                    connection.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Fields cannot be null!");
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
        return lastName;
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
