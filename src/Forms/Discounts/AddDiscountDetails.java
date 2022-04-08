package Forms.Discounts;

import Database.CustomerAccount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class AddDiscountDetails {
    private JLabel addDiscountDetailsLabel;
    private JPanel mainPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JScrollPane scrollPane;
    private JLabel usernameLabel;
    private JTextField username;
    private JLabel discountPlanLabel;
    private JComboBox discountPlan;
    private JLabel discountPriceLabel;
    private JTextField discountPrice;
    private JLabel discountPercentageLabel;
    private JTextField discountPercentage;
    private JButton addButton;
    private JTable searchResults;

    public AddDiscountDetails() {

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

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String usernameText = username.getText();
                    String discountPlanText = discountPlan.getSelectedItem().toString();
                    String discountPriceText = discountPrice.getText();
                    String discountPercentageText = discountPercentage.getText();

                    connection.setAutoCommit(false);
                    PreparedStatement stmt = connection.prepareStatement("SELECT CustomerAccountID FROM CustomerAccount WHERE AccountID = (SELECT AccountID FROM UserAccounts WHERE username = ?)");
                    stmt.setString(1, usernameText);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO Discounts (discountPlan, discountPrice, discountPercentage, CustomerAccountID) VALUES (?,?,?,?)");
                        statement.setString(1, discountPlanText);
                        statement.setString(2, discountPriceText);
                        statement.setString(3, discountPercentageText);
                        statement.setInt(4, rs.getInt("CustomerAccountID"));
                        statement.executeUpdate();
                    }
                    JOptionPane.showMessageDialog(null, "Discount details created!");
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