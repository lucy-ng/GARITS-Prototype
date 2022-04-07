package Forms.Accounts;

import Database.CustomerAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JTable searchResults;

    public UpdateCustAccount() {
        scrollPane.setPreferredSize(new Dimension(500,500));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchField.getText();
                ArrayList<CustomerAccount> customerAccountList = new ArrayList<>();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
                    connection.setAutoCommit(false);
                    PreparedStatement statement = connection.prepareStatement("SELECT username, firstName, lastName, email, phoneNo from UserAccounts where username = ?");
                    statement.setString(1, text);
                    ResultSet rs = statement.executeQuery();

                    PreparedStatement stmt = connection.prepareStatement("SELECT address, homePhoneNo, daytimePhoneNo, eveningPhoneNo, membershipType FROM CustomerAccount WHERE AccountID = (SELECT AccountID FROM UserAccounts where username = ?)");
                    stmt.setString(1, text);
                    ResultSet rsrs = stmt.executeQuery();

                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT discountPlan FROM Discounts WHERE CustomerAccountID = (SELECT CustomerAccountID FROM CustomerAccount where AccountID = (SELECT AccountID FROM UserAccounts where username = ?))");
                    preparedStatement.setString(1,text);
                    ResultSet r = preparedStatement.executeQuery();

                    CustomerAccount customerAccount;

                    while (rs.next()) {
                        while (rsrs.next()) {
                            while (r.next()) {
                                String usernameText = rs.getString("username");
                                String firstNameText = rs.getString("firstName");
                                String lastNameText = rs.getString("lastName");
                                String emailText = rs.getString("email");
                                String phoneNoText = rs.getString("phoneNo");

                                String addressText = rsrs.getString("address");
                                String homePhoneNoText = rsrs.getString("homePhoneNo");
                                String daytimePhoneNoText = rsrs.getString("daytimePhoneNo");
                                String eveningPhoneNoText = rsrs.getString("eveningPhoneNo");
                                String membershipTypeText = rsrs.getString("membershipType");

                                String discountPlanText = r.getString("discountPlan");

                                customerAccount = new CustomerAccount(usernameText, firstNameText, lastNameText, emailText, phoneNoText, addressText, homePhoneNoText, daytimePhoneNoText, eveningPhoneNoText, membershipTypeText, discountPlanText);
                                customerAccountList.add(customerAccount);

                                Object[] row = new Object[11];
                                for (int i = 0; i < customerAccountList.size(); i++) {
                                    row[0] = customerAccount.getUsername();
                                    row[1] = customerAccount.getFirstName();
                                    row[2] = customerAccount.getLastName();
                                    row[3] = customerAccount.getEmail();
                                    row[4] = customerAccount.getPhoneNo();
                                    row[5] = customerAccount.getAddress();
                                    row[6] = customerAccount.getHomePhoneNo();
                                    row[7] = customerAccount.getDaytimePhoneNo();
                                    row[8] = customerAccount.getEveningPhoneNo();
                                    row[9] = customerAccount.getMembershipType();
                                    row[10] = customerAccount.getDiscountPlan();
                                }

                                Object[][] data =  {row};
                                String[] columnNames = {"Username", "First Name", "Last Name", "Email", "Phone Number", "Address", "Home Phone", "Daytime Phone", "Evening Phone", "Membership Type", "Discount Plan"};
                                searchResults = new JTable(data, columnNames);
                                scrollPane.setViewportView(searchResults);
                                searchResults.setVisible(true);
                                fillResults();
                            }
                        }
                    }
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Fields cannot be null!");
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

                    try (PreparedStatement stmt = connection.prepareStatement("UPDATE CustomerAccount SET address = ?, homePhoneNo = ?, daytimePhoneNo = ?, eveningPhoneNo = ?, membershipType = ? WHERE AccountID = (SELECT AccountID FROM UserAccounts WHERE username = ?)")) {
                        stmt.setString(1, addressText);
                        stmt.setString(2, homePhoneNoText);
                        stmt.setString(3, daytimePhoneNoText);
                        stmt.setString(4, eveningPhoneNoText);
                        stmt.setString(5, membershipTypeText);
                        stmt.setString(6, usernameText);
                        stmt.executeUpdate();
                    }

                    try (PreparedStatement updateStmt = connection.prepareStatement("UPDATE Discounts SET discountPlan = ? WHERE CustomerAccountID = (SELECT CustomerAccountID FROM CustomerAccount WHERE AccountID = (SELECT AccountID FROM UserAccounts WHERE username = ?))")) {
                        updateStmt.setString(1, discountPlanText);
                        updateStmt.setString(2, usernameText);
                        updateStmt.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "Account updated!");
                    connection.setAutoCommit(true);
                    connection.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "No account found!");
                }
            }
        });
    }

    public void fillResults() {
        searchResults.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = searchResults.getSelectedRow();
                oldUsername.setText(searchResults.getModel().getValueAt(selectedRow, 1).toString());
                firstName.setText(searchResults.getModel().getValueAt(selectedRow,2).toString());
                lastName.setText(searchResults.getModel().getValueAt(selectedRow,3).toString());
                email.setText(searchResults.getModel().getValueAt(selectedRow,4).toString());
                address.setText(searchResults.getModel().getValueAt(selectedRow,5).toString());
                mobileNo.setText(searchResults.getModel().getValueAt(selectedRow,6).toString());
                homePhoneNo.setText(searchResults.getModel().getValueAt(selectedRow,7).toString());
                daytimePhoneNo.setText(searchResults.getModel().getValueAt(selectedRow,8).toString());
                eveningPhoneNo.setText(searchResults.getModel().getValueAt(selectedRow,9).toString());
                membershipType.setSelectedItem(searchResults.getModel().getValueAt(selectedRow,10).toString());
                discountPlan.setSelectedItem(searchResults.getModel().getValueAt(selectedRow,11).toString());
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
