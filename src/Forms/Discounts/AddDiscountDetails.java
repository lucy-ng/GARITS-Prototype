package Forms.Discounts;

import Database.CustomerAccount;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

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
    private JLabel minimumDiscountPriceLabel;
    private JTextField minimumDiscountPrice;
    private JLabel discountPercentageLabel;
    private JTextField discountPercentage;
    private JButton addButton;
    private JLabel maximumDiscountPriceLabel;
    private JTextField maximumDiscountPrice;
    private JTable searchResults;

    public AddDiscountDetails() {
        Vector headers = new Vector();
        headers.addElement("Company Name");
        headers.addElement("Username");
        headers.addElement("First Name");
        headers.addElement("Last Name");
        headers.addElement("Membership Type");
        Vector rows = new Vector();
        searchResults = new JTable(rows, headers);
        DefaultTableModel accountTableModel = (DefaultTableModel) searchResults.getModel();
        scrollPane.setViewportView(searchResults);
        searchResults.setVisible(true);

        ArrayList<CustomerAccount> customerAccountList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT AccountID, CustomerAccountID, membershipType, companyName from CustomerAccount");
            ResultSet rs = statement.executeQuery();

            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM UserAccounts WHERE AccountID = ?");

            while (rs.next()) {
                stmt.setInt(1, rs.getInt("AccountID"));
                ResultSet r = stmt.executeQuery();
                while (r.next()) {
                    String companyName = rs.getString("companyName");
                    String membershipType = rs.getString("membershipType");

                    String username = r.getString("username");
                    String firstName = r.getString("firstName");
                    String lastName = r.getString("lastName");

                    CustomerAccount customerAccount;
                    customerAccount = new CustomerAccount(companyName, username, firstName, lastName, membershipType);
                    customerAccountList.add(customerAccount);

                    Object[] row = new Object[5];
                    for (int i = 0; i < customerAccountList.size(); i++) {
                        row[0] = customerAccount.getCompanyName();
                        row[1] = customerAccount.getUsername();
                        row[2] = customerAccount.getFirstName();
                        row[3] = customerAccount.getLastName();
                        row[4] = customerAccount.getMembershipType();
                    }
                    accountTableModel.addRow(row);
                    fillResults();
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
                    PreparedStatement statement = connection.prepareStatement("SELECT username, firstName, lastName from UserAccounts where username = ?");
                    statement.setString(1, text);
                    ResultSet rs = statement.executeQuery();

                    PreparedStatement stmt = connection.prepareStatement("SELECT membershipType, companyName FROM CustomerAccount WHERE AccountID = (SELECT AccountID FROM UserAccounts where username = ?)");
                    stmt.setString(1, text);
                    ResultSet rsrs = stmt.executeQuery();

                    while (rs.next()) {
                        while (rsrs.next()) {
                            String usernameText = rs.getString("username");
                            String firstNameText = rs.getString("firstName");
                            String lastNameText = rs.getString("lastName");

                            String companyNameText = rsrs.getString("companyName");
                            String membershipTypeText = rsrs.getString("membershipType");

                            CustomerAccount customerAccount;
                            customerAccount = new CustomerAccount(companyNameText, usernameText, firstNameText, lastNameText, membershipTypeText);
                            customerAccountList.add(customerAccount);

                            Object[] row = new Object[5];
                            for (int i = 0; i < customerAccountList.size(); i++) {
                                row[0] = customerAccount.getCompanyName();
                                row[1] = customerAccount.getUsername();
                                row[2] = customerAccount.getFirstName();
                                row[3] = customerAccount.getLastName();
                                row[4] = customerAccount.getMembershipType();
                            }

                            Object[][] data =  {row};
                            String[] columnNames = {"Company Name", "Username", "First Name", "Last Name", "Membership Type"};
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

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String usernameText = username.getText();
                    String discountPlanText = discountPlan.getSelectedItem().toString();
                    String minimumDiscountPriceText = minimumDiscountPrice.getText();
                    String maximumDiscountPriceText = maximumDiscountPrice.getText();
                    String discountPercentageText = discountPercentage.getText();

                    connection.setAutoCommit(false);
                    PreparedStatement stmt = connection.prepareStatement("SELECT CustomerAccountID FROM CustomerAccount WHERE AccountID = (SELECT AccountID FROM UserAccounts WHERE username = ?)");
                    stmt.setString(1, usernameText);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO Discounts (discountPlan, minDiscountPrice, maxDiscountPrice, discountPercentage, CustomerAccountID) VALUES (?,?,?,?,?)");
                        statement.setString(1, discountPlanText);
                        statement.setString(2, minimumDiscountPriceText);
                        statement.setString(3, maximumDiscountPriceText);
                        statement.setString(4, discountPercentageText);
                        statement.setInt(5, rs.getInt("CustomerAccountID"));
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

    public void fillResults() {
        searchResults.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = searchResults.getSelectedRow();
                username.setText(searchResults.getModel().getValueAt(selectedRow,1).toString());
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}