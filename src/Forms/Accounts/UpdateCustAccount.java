package Forms.Accounts;

import Database.UserAccount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class UpdateCustAccount {
    private JLabel updateTitle;
    private JPanel mainPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JScrollPane scrollPane;
    private JTextField oldUsername;
    private JLabel oldUsernameLabel;
    private JLabel usernameLabel;
    private JTextField username;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JLabel firstNameLabel;
    private JTextField firstName;
    private JLabel lastNameLabel;
    private JTextField lastName;
    private JLabel emailLabel;
    private JTextField email;
    private JLabel addressLabel;
    private JTextArea address;
    private JLabel mobileNoLabel;
    private JTextField mobileNo;
    private JTextField homePhoneNo;
    private JLabel homePhoneNoLabel;
    private JTextField daytimePhoneNo;
    private JLabel daytimePhoneNoLabel;
    private JLabel eveningPhoneNoLabel;
    private JTextField eveningPhoneNo;
    private JLabel membershipTypeLabel;
    private JComboBox membershipType;
    private JComboBox discountPlan;
    private JLabel discountPlanLabel;
    private JButton updateButton;

    public UpdateCustAccount() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchField.getText();
                ArrayList<UserAccount> userAccountList = new ArrayList<>();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
                    PreparedStatement statement = connection.prepareStatement("SELECT username, firstName, lastName, email, phoneNo from UserAccounts where username = ?");
                    statement.setString(1, text);
                    ResultSet rs = statement.executeQuery();
                    UserAccount userAccount;

                    while (rs.next()) {
                        String username = rs.getString("username");
                        String firstName = rs.getString("firstName");
                        String lastName = rs.getString("lastName");
                        String email = rs.getString("email");
                        String phoneNo = rs.getString("phoneNo");

                        userAccount = new UserAccount(username, firstName, lastName, email, phoneNo);
                        userAccountList.add(userAccount);

                        Object[] row = new Object[5];
                        for (int i = 0; i < userAccountList.size(); i++) {
                            row[0] = userAccount.getUsername();
                            row[1] = userAccount.getFirstName();
                            row[2] = userAccount.getLastName();
                            row[3] = userAccount.getEmail();
                            row[4] = userAccount.getPhoneNo();
                        }

                        Object[][] data =  {row};
                        String[] columnNames = {"Username", "First Name", "Last Name", "Email", "Phone Number"};
                        JTable searchResults = new JTable(data, columnNames);
                        scrollPane.setViewportView(searchResults);
                        searchResults.setVisible(true);
                    }
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();;
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String usernameText = username.getText();
                    String firstNameText = firstName.getText();
                    String lastNameText = lastName.getText();
                    String passwordText = String.valueOf(password.getPassword());
                    String emailText = email.getText();
                    String addressText = address.getText();
                    String mobileNoText = mobileNo.getText();
                    String homePhoneNoText = homePhoneNo.getText();
                    String daytimePhoneNoText = daytimePhoneNo.getText();
                    String eveningPhoneNoText = eveningPhoneNo.getText();
                    String membershipTypeText = String.valueOf(membershipType.getSelectedItem());
                    String discountPlanText = String.valueOf(discountPlan.getSelectedItem());
                    String oldUsernameText = oldUsername.getText();

                    connection.setAutoCommit(false);
                    try (PreparedStatement statement = connection.prepareStatement("UPDATE UserAccounts SET username = ?, firstName = ?, lastName = ?, password = ?, email = ?, phoneNo = ? WHERE username = ?")) {
                        statement.setString(1, usernameText);
                        statement.setString(2, firstNameText);
                        statement.setString(3, lastNameText);
                        statement.setString(4, passwordText);
                        statement.setString(5, emailText);
                        statement.setString(6, mobileNoText);
                        statement.setString(7, oldUsernameText);
                        statement.executeUpdate();
                    }

                    try (PreparedStatement stmt = connection.prepareStatement("UPDATE CustomerAccount SET address = ?, homePhoneNo = ?, daytimePhoneNo = ?, eveningPhoneNo = ?, membershipType = ?, discountPlan = ? WHERE AccountID = (SELECT AccountID FROM UserAccounts WHERE username = ?)")) {
                        stmt.setString(1, addressText);
                        stmt.setString(2, homePhoneNoText);
                        stmt.setString(3, daytimePhoneNoText);
                        stmt.setString(4, eveningPhoneNoText);
                        stmt.setString(5, membershipTypeText);
                        stmt.setString(6, discountPlanText);
                        stmt.setString(7, usernameText);
                        stmt.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "Account updated!");
                    connection.setAutoCommit(true);
                    connection.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
