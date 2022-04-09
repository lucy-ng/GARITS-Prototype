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
    private boolean motPaid = true;
    private boolean servicePaid = true;

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

                    CustomerAccount customerAccount;

                    while (rs.next()) {
                        while (rsrs.next()) {
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
                            String companyNameText = rsrs.getString("companyName");

                            customerAccount = new CustomerAccount(companyNameText, usernameText, firstNameText, lastNameText, emailText, phoneNoText, addressText, homePhoneNoText, daytimePhoneNoText, eveningPhoneNoText, membershipTypeText);
                            customerAccountList.add(customerAccount);

                            Object[] row = new Object[11];
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
                            }

                            Object[][] data =  {row};
                            String[] columnNames = {"Company Name", "Username", "First Name", "Last Name", "Email", "Phone Number", "Address", "Home Phone", "Daytime Phone", "Evening Phone", "Membership Type"};
                            searchResults = new JTable(data, columnNames);
                            scrollPane.setViewportView(searchResults);
                            searchResults.setVisible(true);
                            fillResults();
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
                    String paymentDateText = paymentDate.getText();

                    connection.setAutoCommit(false);

                    PreparedStatement stmt = connection.prepareStatement("SELECT CustomerAccountID FROM CustomerAccount WHERE AccountID = (SELECT AccountID FROM UserAccounts WHERE username = ?)");
                    stmt.setString(1, usernameText);
                    ResultSet resultSet = stmt.executeQuery();

                    while (resultSet.next()) {
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO Payments (companyName, cardType, cardNumber, expiryDate, amount, CustomerAccountID, paymentDate) VALUES (?,?,?,?,?,?,?)");
                        statement.setString(1, companyNameText);
                        statement.setString(2, cardTypeText);
                        statement.setString(3, cardNumberText);
                        statement.setString(4, expiryDateText);
                        statement.setString(5, amountText);
                        statement.setString(6, resultSet.getString("CustomerAccountID"));
                        statement.setString(7, paymentDateText);
                        statement.executeUpdate();

                        PreparedStatement updateStmt;
                        if (servicePaid == false && motPaid == true) {
                            updateStmt = connection.prepareStatement("UPDATE Booking SET servicePaid = ? WHERE CustomerAccountID = ?");
                            updateStmt.setInt(1, 1);
                            updateStmt.setString(2, resultSet.getString("CustomerAccountID"));
                            updateStmt.executeUpdate();
                        }
                        else if (motPaid == false && servicePaid == true) {
                            updateStmt = connection.prepareStatement("UPDATE Booking SET motPaid = ? WHERE CustomerAccountID = ?");
                            updateStmt.setInt(1, 1);
                            updateStmt.setString(2, resultSet.getString("CustomerAccountID"));
                            updateStmt.executeUpdate();
                        }
                        else if (motPaid == false && servicePaid == false) {
                            updateStmt = connection.prepareStatement("UPDATE Booking SET servicePaid = ?, motPaid = ? WHERE CustomerAccountID = ?");
                            updateStmt.setInt(1, 1);
                            updateStmt.setInt(2, 1);
                            updateStmt.setString(3, resultSet.getString("CustomerAccountID"));
                            updateStmt.executeUpdate();
                        }
                    }
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
                    PreparedStatement statement = connection.prepareStatement("SELECT serviceAmount, motAmount, servicePaid, motPaid FROM Booking WHERE CustomerAccountID = (SELECT CustomerAccountID FROM CustomerAccount WHERE AccountID = (SELECT AccountID FROM UserAccounts WHERE username = ?))");
                    statement.setString(1, username.getText());
                    ResultSet rs = statement.executeQuery();

                    while (rs.next()) {
                        int servicePaidValue = rs.getInt("servicePaid");
                        int motPaidValue = rs.getInt("motPaid");
                        BigDecimal serviceAmountValue = rs.getBigDecimal("serviceAmount");
                        BigDecimal motAmountValue = rs.getBigDecimal("motAmount");

                        if (servicePaidValue == 0 && motPaidValue == 1) {
                            serviceAmount.setText(String.valueOf(serviceAmountValue));
                            totalAmount.setText(String.valueOf(serviceAmountValue));
                            servicePaid = false;
                        }
                        else if (motPaidValue == 0 && servicePaidValue == 1) {
                            motAmount.setText(String.valueOf(motAmountValue));
                            totalAmount.setText(String.valueOf(motAmountValue));
                            motPaid = false;
                        }
                        else if (servicePaidValue == 0 && motPaidValue == 0) {
                            serviceAmount.setText(String.valueOf(serviceAmountValue));
                            motAmount.setText(String.valueOf(motAmountValue));
                            BigDecimal totalAmountValue;
                            totalAmountValue = serviceAmountValue.add(motAmountValue);
                            totalAmount.setText(String.valueOf(totalAmountValue));
                            motPaid = false;
                            servicePaid = false;
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
