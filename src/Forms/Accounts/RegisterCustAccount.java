package Forms.Accounts;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterCustAccount {
    private JLabel registerCustomerAccountTitle;
    private JPanel mainPanel;
    private JTextField username;
    private JPasswordField password;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField email;
    private JTextField mobileNo;
    private JComboBox membershipType;
    private JButton registerButton;
    private JLabel usernameLabel;
    private JLabel firstNameLabel;
    private JLabel emailLabel;
    private JLabel mobileNoLabel;
    private JLabel passwordLabel;
    private JLabel lastNameLabel;
    private JLabel addressLabel;
    private JLabel membershipTypeLabel;
    private JTextField daytimePhoneNo;
    private JTextField homePhoneNo;
    private JTextField eveningPhoneNo;
    private JTextArea address;
    private JLabel customerDetailsLabel;
    private JLabel homePhoneNoLabel;
    private JLabel daytimePhoneNoLabel;
    private JLabel eveningPhoneNoLabel;
    private JLabel companyNameLabel;
    private JTextField companyName;
    private JComboBox discountPlan;
    private JLabel discountPlanLabel;
    private JLabel discountPriceLabel;
    private JLabel discountPercentageLabel;
    private JTextField discountPrice;
    private JTextField discountPercentage;

    public RegisterCustAccount() {

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String usernameText = username.getText();
                    String passwordText = String.valueOf(password.getPassword());
                    String firstNameText = firstName.getText();
                    String lastNameText = lastName.getText();
                    String emailText = email.getText();
                    String companyNameText = companyName.getText();
                    String addressText = address.getText();
                    String mobileNoText = mobileNo.getText();
                    String homePhoneNoText = homePhoneNo.getText();
                    String daytimePhoneNoText = daytimePhoneNo.getText();
                    String eveningPhoneNoText = eveningPhoneNo.getText();
                    String membershipTypeText = membershipType.getSelectedItem().toString();
                    String discountPlanText = discountPlan.getSelectedItem().toString();
                    String discountPriceText = discountPrice.getText();
                    String discountPercentageText = discountPercentage.getText();

                    connection.setAutoCommit(false);
                    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO UserAccounts (username, firstName, lastName, password, email, phoneNo) VALUES (?,?,?,?,?,?)")) {
                        statement.setString(1, usernameText);
                        statement.setString(2, firstNameText);
                        statement.setString(3, lastNameText);
                        statement.setString(4, passwordText);
                        statement.setString(5, emailText);
                        statement.setString(6, mobileNoText);
                        statement.executeUpdate();
                    }

                    try (PreparedStatement stmtInsert = connection.prepareStatement("INSERT INTO CustomerAccount (address, homePhoneNo, daytimePhoneNo, eveningPhoneNo, membershipType, AccountID, companyName) VALUES (?,?,?,?,?, LAST_INSERT_ID(), ?)")) {
                        stmtInsert.setString(1, addressText);
                        stmtInsert.setString(2, homePhoneNoText);
                        stmtInsert.setString(3, daytimePhoneNoText);
                        stmtInsert.setString(4, eveningPhoneNoText);
                        stmtInsert.setString(5, membershipTypeText);
                        stmtInsert.setString(6, companyNameText);
                        stmtInsert.executeUpdate();
                    }

                    try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO Discounts (discountPlan, discountPrice, discountPercentage, CustomerAccountID) VALUES (?,?,?, LAST_INSERT_ID())")) {
                        stmt.setString(1, discountPlanText);
                        stmt.setString(2, discountPriceText);
                        stmt.setString(3, discountPercentageText);
                        stmt.executeUpdate();
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
}
