package Forms.Accounts;

import Database.CustomerAccount;
import Database.EmployeeAccount;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class DeleteCustAccount {
    private JLabel deleteCustomerAccountLabel;
    private JPanel mainPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JTextField usernameDelete;
    private JLabel usernameDeleteLabel;
    private JScrollPane scrollPane;
    private JButton deleteButton;
    private JTable searchResults;

    public DeleteCustAccount() {
        scrollPane.setPreferredSize(new Dimension(500,500));

        Vector headers = new Vector();
        headers.addElement("Company Name");
        headers.addElement("Username");
        headers.addElement("First Name");
        headers.addElement("Last Name");
        headers.addElement("Email");
        headers.addElement("Address");
        headers.addElement("Mobile Number");
        headers.addElement("Home Phone Number");
        headers.addElement("Daytime Phone Number");
        headers.addElement("Evening Phone Number");
        headers.addElement("Membership Type");
        headers.addElement("Discount Plan");
        headers.addElement("Discount Price");
        headers.addElement("Discount Percentage");
        Vector rows = new Vector();
        searchResults = new JTable(rows, headers);
        DefaultTableModel accountTableModel = (DefaultTableModel) searchResults.getModel();
        scrollPane.setViewportView(searchResults);
        searchResults.setVisible(true);

        ArrayList<CustomerAccount> customerAccountList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT AccountID, CustomerAccountID, address, homePhoneNo, daytimePhoneNo, eveningPhoneNo, membershipType, companyName from CustomerAccount");
            ResultSet rs = statement.executeQuery();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT discountPlan, discountPrice, discountPercentage FROM Discounts WHERE CustomerAccountID = ?");

            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM UserAccounts WHERE AccountID = ?");

            while (rs.next()) {
                preparedStatement.setInt(1, rs.getInt("CustomerAccountID"));
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    stmt.setInt(1, rs.getInt("AccountID"));
                    ResultSet r = stmt.executeQuery();
                    while (r.next()) {
                        String username = r.getString("username");
                        String firstName = r.getString("firstName");
                        String lastName = r.getString("lastName");
                        String email = r.getString("email");
                        String phoneNumber = r.getString("phoneNo");
                        String address = rs.getString("address");
                        String homePhoneNo = rs.getString("homePhoneNo");
                        String daytimePhoneNo = rs.getString("daytimePhoneNo");
                        String eveningPhoneNo = rs.getString("eveningPhoneNo");
                        String membershipType = rs.getString("membershipType");
                        String companyName = rs.getString("companyName");
                        String discountPlan = resultSet.getString("discountPlan");
                        BigDecimal discountPrice = resultSet.getBigDecimal("discountPrice");
                        int discountPercentage = resultSet.getInt("discountPercentage");

                        CustomerAccount customerAccount;
                        customerAccount = new CustomerAccount(companyName, username, firstName, lastName, email, phoneNumber, address, homePhoneNo, daytimePhoneNo, eveningPhoneNo, membershipType, discountPlan, discountPrice, discountPercentage);
                        customerAccountList.add(customerAccount);

                        Object[] row = new Object[14];
                        for (int i = 0; i < customerAccountList.size(); i++) {
                            row[0] = customerAccount.getCompanyName();
                            row[1] = customerAccount.getUsername();
                            row[2] = customerAccount.getFirstName();
                            row[3] = customerAccount.getLastName();
                            row[4] = customerAccount.getEmail();
                            row[5] = customerAccount.getPhoneNo();
                            row[6] = customerAccount.getAddress();
                            row[7] = customerAccount.getHomePhoneNo();
                            row[8] = customerAccount.getDaytimePhoneNo();
                            row[9] = customerAccount.getEveningPhoneNo();
                            row[10] = customerAccount.getMembershipType();
                            row[11] = customerAccount.getDiscountPlan();
                            row[12] = customerAccount.getDiscountPrice();
                            row[13] = customerAccount.getDiscountPercentage();
                        }
                        accountTableModel.addRow(row);
                        fillResults();
                    }
                }
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

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

                    PreparedStatement stmt = connection.prepareStatement("SELECT address, homePhoneNo, daytimePhoneNo, eveningPhoneNo, membershipType, companyName FROM CustomerAccount WHERE AccountID = (SELECT AccountID FROM UserAccounts where username = ?)");
                    stmt.setString(1, text);
                    ResultSet rsrs = stmt.executeQuery();

                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT discountPlan, discountPrice, discountPercentage FROM Discounts WHERE CustomerAccountID = (SELECT CustomerAccountID FROM CustomerAccount where AccountID = (SELECT AccountID FROM UserAccounts where username = ?))");
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
                                String companyNameText = r.getString("companyName");
                                BigDecimal discountPriceText = r.getBigDecimal("discountPrice");
                                int discountPercentageText = r.getInt("discountPercentage");

                                customerAccount = new CustomerAccount(companyNameText, usernameText, firstNameText, lastNameText, emailText, phoneNoText, addressText, homePhoneNoText, daytimePhoneNoText, eveningPhoneNoText, membershipTypeText, discountPlanText, discountPriceText, discountPercentageText);
                                customerAccountList.add(customerAccount);

                                Object[] row = new Object[14];
                                for (int i = 0; i < customerAccountList.size(); i++) {
                                    row[0] = customerAccount.getCompanyName();
                                    row[1] = customerAccount.getUsername();
                                    row[2] = customerAccount.getFirstName();
                                    row[3] = customerAccount.getLastName();
                                    row[4] = customerAccount.getEmail();
                                    row[5] = customerAccount.getPhoneNo();
                                    row[6] = customerAccount.getAddress();
                                    row[7] = customerAccount.getHomePhoneNo();
                                    row[8] = customerAccount.getDaytimePhoneNo();
                                    row[9] = customerAccount.getEveningPhoneNo();
                                    row[10] = customerAccount.getMembershipType();
                                    row[11] = customerAccount.getDiscountPlan();
                                    row[12] = customerAccount.getDiscountPrice();
                                    row[13] = customerAccount.getDiscountPercentage();
                                }

                                Object[][] data =  {row};
                                String[] columnNames = {"Company Name", "Username", "First Name", "Last Name", "Email", "Phone Number", "Address", "Home Phone", "Daytime Phone", "Evening Phone", "Membership Type", "Discount Plan", "Discount Price", "Discount Percentage"};
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

                    JOptionPane.showMessageDialog(null, "Account deleted!");
                    connection.setAutoCommit(true);
                    connection.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
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
                usernameDelete.setText(searchResults.getModel().getValueAt(selectedRow, 1).toString());
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
