package Forms.Payments;

import Database.CustomerAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class MakePayment {
    private JLabel makePaymentTitle;
    private JTextField cardNumber;
    private JTextField totalAmount;
    private JButton payButton;
    private JPanel mainPanel;
    private JLabel cardNumberLabel;
    private JLabel totalAmountLabel;
    private JLabel expiryDateLabel;
    private JTextField expiryDate;
    private JLabel companyNameLabel;
    private JTextField companyName;
    private JTextField searchField;
    private JButton searchButton;
    private JScrollPane scrollPane;
    private JLabel usernameLabel;
    private JTextField username;
    private JLabel cardTypeLabel;
    private JTextField cardType;
    private JLabel paymentDateLabel;
    private JTextField paymentDate;
    private JLabel serviceAmountLabel;
    private JLabel MOTAmountLabel;
    private JTextField serviceAmount;
    private JTextField motAmount;
    private JTable searchResults;

    public MakePayment() {
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

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String usernameText = username.getText();
                    String companyNameText = companyName.getText();
                    String cardTypeText = cardType.getText();
                    String cardNumberText = cardNumber.getText();
                    String expiryDateText = expiryDate.getText();
                    String amountText = totalAmount.getText();

                    connection.setAutoCommit(false);

                    PreparedStatement stmt = connection.prepareStatement("SELECT CustomerAccountID FROM CustomerAccount WHERE AccountID = (SELECT AccountID FROM UserAccounts WHERE username = ?)");
                    stmt.setString(1, usernameText);
                    ResultSet resultSet = stmt.executeQuery();

                    PreparedStatement statement = connection.prepareStatement("INSERT INTO Payments (companyName, cardType, cardNumber, expiryDate, amount, CustomerAccountID, paymentDate) VALUES (?,?,?,?,?,?,?)");
                    statement.setString(1, companyNameText);
                    statement.setString(2, cardTypeText);
                    statement.setString(3, cardNumberText);
                    statement.setString(4, expiryDateText);
                    statement.setString(5, amountText);
                    statement.setString(6, resultSet.getString("CustomerAccountID"));
                    statement.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Payment recorded!");
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

    public void fillResults() {
        searchResults.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = searchResults.getSelectedRow();
                username.setText(searchResults.getModel().getValueAt(selectedRow, 1).toString());

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");
                    PreparedStatement statement = connection.prepareStatement("SELECT serviceAmount, motAmount FROM Booking WHERE CustomerAccountID = (SELECT CustomerAccountID FROM CustomerAccount WHERE AccountID = (SELECT AccountID FROM UserAccounts WHERE username = ?))");
                    statement.setString(1, username.getText());
                    ResultSet rs = statement.executeQuery();

                    while (rs.next()) {
                        serviceAmount.setText(rs.getString("serviceAmount"));
                        motAmount.setText(rs.getString("motAmount"));
                        int totalAmountValue = Integer.parseInt(serviceAmount.getText() + motAmount.getText());
                        totalAmount.setText(String.valueOf(totalAmountValue));
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
