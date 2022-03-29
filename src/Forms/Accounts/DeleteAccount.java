package Forms.Accounts;

import Database.UserAccount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class DeleteAccount {
    private JLabel deleteAccountTitle;
    private JTextField searchField;
    private JButton searchButton;
    private JScrollPane scrollPane;
    private JButton deleteButton;
    private JTextField usernameDelete;
    private JLabel usernameDeleteLabel;

    public DeleteAccount() {
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

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String usernameText = usernameDelete.getText();

                    connection.setAutoCommit(false);
                    try (PreparedStatement statement = connection.prepareStatement("DELETE FROM UserAccounts WHERE username = ?")) {
                        statement.setString(1, usernameText);
                        statement.executeUpdate();
                    }

                    try (PreparedStatement stmtInsert = connection.prepareStatement("DELTE FROM EmployeeAccount WHERE AccountID = LAST_INSERT_ID()")) {
                        stmtInsert.executeUpdate();
                    }


                    JOptionPane.showMessageDialog(null, "Account deleted!");
                    connection.setAutoCommit(true);
                    connection.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
