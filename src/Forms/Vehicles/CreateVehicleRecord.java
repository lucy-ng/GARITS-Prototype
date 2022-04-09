package Forms.Vehicles;

import Database.CustomerAccount;
import Database.EmployeeAccount;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class CreateVehicleRecord {
    private JLabel createVehicleRecordTitle;
    private JLabel registrationNumberLabel;
    private JTextField registrationNumber;
    private JTextField searchField;
    private JButton searchButton;
    private JScrollPane scrollPane;
    private JLabel colourLabel;
    private JTextField colour;
    private JLabel makeLabel;
    private JTextField make;
    private JLabel modelLabel;
    private JTextField model;
    private JLabel chassisNumberLabel;
    private JTextField chassisNumber;
    private JLabel engineSerialLabel;
    private JTextField engineSerial;
    private JTextField year;
    private JButton createButton;
    private JLabel yearLabel;
    private JLabel accountUsernameLabel;
    private JTextField username;
    private JPanel mainPanel;
    private JTable searchResults;

    public CreateVehicleRecord() {
        Vector headers = new Vector();
        headers.addElement("Company Name");
        headers.addElement("Username");
        headers.addElement("First Name");
        headers.addElement("Last Name");
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

            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM UserAccounts WHERE AccountID = ?");

            while (rs.next()) {
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

                    CustomerAccount customerAccount;
                    customerAccount = new CustomerAccount(companyName, username, firstName, lastName, email, phoneNumber, address, homePhoneNo, daytimePhoneNo, eveningPhoneNo, membershipType);
                    customerAccountList.add(customerAccount);

                    Object[] row = new Object[4];
                    for (int i = 0; i < customerAccountList.size(); i++) {
                        row[0] = customerAccount.getCompanyName();
                        row[1] = customerAccount.getUsername();
                        row[2] = customerAccount.getFirstName();
                        row[3] = customerAccount.getLastName();
                    }
                    accountTableModel.addRow(row);
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
                ArrayList<EmployeeAccount> employeeAccountList = new ArrayList<>();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
                    connection.setAutoCommit(false);
                    PreparedStatement statement = connection.prepareStatement("SELECT username, firstName, lastName, email, phoneNo from UserAccounts where username = ?");
                    statement.setString(1, text);
                    ResultSet rs = statement.executeQuery();

                    PreparedStatement stmt = connection.prepareStatement("SELECT EmployeePosition, Department, labourRate FROM EmployeeAccount WHERE AccountID = (SELECT AccountID FROM UserAccounts where username = ?)");
                    ResultSet rsrs = stmt.executeQuery();

                    EmployeeAccount employeeAccount;

                    while (rs.next()) {
                        String usernameText = rs.getString("username");
                        String firstNameText = rs.getString("firstName");
                        String lastNameText = rs.getString("lastName");
                        String emailText = rs.getString("email");
                        String phoneNoText = rs.getString("phoneNo");
                        String employeePositionText = rsrs.getString("EmployeePosition");
                        String departmentText = rsrs.getString("Department");
                        BigDecimal labourRateValue = rsrs.getBigDecimal("labourRate");

                        employeeAccount = new EmployeeAccount(usernameText, firstNameText, lastNameText, emailText, phoneNoText, employeePositionText, departmentText, labourRateValue);
                        employeeAccountList.add(employeeAccount);

                        Object[] row = new Object[8];
                        for (int i = 0; i < employeeAccountList.size(); i++) {
                            row[0] = employeeAccount.getUsername();
                            row[1] = employeeAccount.getFirstName();
                            row[2] = employeeAccount.getLastName();
                            row[3] = employeeAccount.getEmail();
                            row[4] = employeeAccount.getPhoneNo();
                            row[5] = employeeAccount.getEmployeePosition();
                            row[6] = employeeAccount.getDepartment();
                            row[7] = employeeAccount.getLabourRate();
                        }

                        Object[][] data =  {row};
                        String[] columnNames = {"Username", "First Name", "Last Name", "Email", "Phone Number", "Employee Position", "Department", "Labour Rate"};
                        searchResults = new JTable(data, columnNames);
                        scrollPane.setViewportView(searchResults);
                        searchResults.setVisible(true);
                    }
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();;
                }
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String registrationNoText = registrationNumber.getText();
                    String colourText = colour.getText();
                    String makeText = make.getText();
                    String modelText = model.getText();
                    String chassisNoText = chassisNumber.getText();
                    String engineSerialText = engineSerial.getText();
                    String yearText = year.getText();
                    String usernameText = username.getText();

                    connection.setAutoCommit(false);

                    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Vehicles (registrationNo, colour, make, model, chassisNo, engineSerial, year, AccountID) VALUES (?,?,?,?,?,?,?,(SELECT AccountID FROM UserAccounts WHERE username = ?))")) {
                        statement.setString(1, registrationNoText);
                        statement.setString(2, colourText);
                        statement.setString(3, makeText);
                        statement.setString(4, modelText);
                        statement.setString(5, chassisNoText);
                        statement.setString(6, engineSerialText);
                        statement.setString(7, yearText);
                        statement.setString(8, usernameText);
                        statement.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "Vehicle record created!");
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
