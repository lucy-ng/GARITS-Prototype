package Forms.Vehicles;

import Database.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class UpdateVehicleRecord {
    private JTextField usernameSearchField;
    private JButton searchAccountButton;
    private JScrollPane usernameScrollPane;
    private JLabel registrationNumberLabel;
    private JTextField registrationNumber;
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
    private JButton updateButton;
    private JLabel accountUsernameLabel;
    private JTextField username;
    private JPanel mainPanel;
    private JButton searchVehicleButton;
    private JTextField regNoSearchField;
    private JScrollPane regNoScrollPane;
    private JLabel oldRegistrationNumberLabel;
    private JTextField oldRegNo;
    private JLabel yearLabel;
    private JLabel updateVehicleRecordTitle;
    private JTable searchResults;
    private JTable vehicleSearchResults;

    public UpdateVehicleRecord() {
        Vector headers = new Vector();
        headers.addElement("Company Name");
        headers.addElement("Username");
        headers.addElement("First Name");
        headers.addElement("Last Name");
        Vector rows = new Vector();
        searchResults = new JTable(rows, headers);
        DefaultTableModel accountTableModel = (DefaultTableModel) searchResults.getModel();
        usernameScrollPane.setViewportView(searchResults);
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


        Vector vehicleHeaders = new Vector();
        vehicleHeaders.addElement("Username");
        vehicleHeaders.addElement("Registration Number");
        vehicleHeaders.addElement("Colour");
        vehicleHeaders.addElement("Make");
        vehicleHeaders.addElement("Model");
        vehicleHeaders.addElement("Chassis Number");
        vehicleHeaders.addElement("Engine Serial");
        vehicleHeaders.addElement("Year");
        Vector vehicleRows = new Vector();
        vehicleSearchResults = new JTable(vehicleRows, vehicleHeaders);
        DefaultTableModel vehicleTableModel = (DefaultTableModel) vehicleSearchResults.getModel();
        regNoScrollPane.setViewportView(vehicleSearchResults);
        vehicleSearchResults.setVisible(true);

        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT CustomerAccountID, AccountID from CustomerAccount");
            ResultSet rs = statement.executeQuery();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT registrationNo, colour, make, model, chassisNo, engineSerial, year FROM Vehicles WHERE CustomerAccountID = ?");

            while (rs.next()) {
                PreparedStatement selectStmt = connection.prepareStatement("SELECT username FROM UserAccounts WHERE AccountID = ?");
                selectStmt.setInt(1, rs.getInt("AccountID"));
                ResultSet r = selectStmt.executeQuery();
                while (r.next()) {
                    preparedStatement.setInt(1, rs.getInt("CustomerAccountID"));
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        String username = r.getString("username");
                        String registrationNo = resultSet.getString("registrationNo");
                        String colour = resultSet.getString("colour");
                        String make = resultSet.getString("make");
                        String model = resultSet.getString("model");
                        String chassisNo = resultSet.getString("chassisNo");
                        String engineSerial = resultSet.getString("engineSerial");
                        String year = resultSet.getString("year");

                        Vehicle vehicle;
                        vehicle = new Vehicle(registrationNo, colour, make, model, chassisNo, engineSerial, year);
                        vehicleList.add(vehicle);

                        Object[] row = new Object[8];
                        for (int i = 0; i < vehicleList.size(); i++) {
                            row[0] = username;
                            row[1] = vehicle.getRegistrationNo();
                            row[2] = vehicle.getColour();
                            row[3] = vehicle.getMake();
                            row[4] = vehicle.getModel();
                            row[5] = vehicle.getChassisNo();
                            row[6] = vehicle.getEngineSerial();
                            row[7] = vehicle.getYear();
                        }
                        vehicleTableModel.addRow(row);
                    }
                }
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        searchAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = usernameSearchField.getText();
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

                            Object[] row = new Object[4];
                            for (int i = 0; i < customerAccountList.size(); i++) {
                                row[0] = customerAccount.getCompanyName();
                                row[1] = customerAccount.getUsername();
                                row[2] = customerAccount.getFirstName();
                                row[3] = customerAccount.getLastName();
                            }

                            Object[][] data =  {row};
                            String[] columnNames = {"Company Name", "Username", "First Name", "Last Name"};
                            searchResults = new JTable(data, columnNames);
                            usernameScrollPane.setViewportView(searchResults);
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

        searchVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = regNoSearchField.getText();
                ArrayList<Vehicle> vehicleList = new ArrayList<>();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
                    PreparedStatement statement = connection.prepareStatement("SELECT registrationNo, colour, make, model, chassisNo, engineSerial, year from Vehicles where registrationNo = ?");
                    statement.setString(1, text);
                    ResultSet rs = statement.executeQuery();
                    Vehicle vehicle;

                    while (rs.next()) {
                        String registrationNo = rs.getString("registrationNo");
                        String colour = rs.getString("colour");
                        String make = rs.getString("make");
                        String model = rs.getString("model");
                        String chassisNo = rs.getString("chassisNo");
                        String engineSerial = rs.getString("engineSerial");
                        String year = rs.getString("year");

                        vehicle = new Vehicle(registrationNo, colour, make, model, chassisNo, engineSerial, year);
                        vehicleList.add(vehicle);

                        Object[] row = new Object[7];
                        for (int i = 0; i < vehicleList.size(); i++) {
                            row[0] = vehicle.getRegistrationNo();
                            row[1] = vehicle.getColour();
                            row[2] = vehicle.getMake();
                            row[3] = vehicle.getModel();
                            row[4] = vehicle.getChassisNo();
                            row[5] = vehicle.getEngineSerial();
                            row[6] = vehicle.getYear();
                        }

                        Object[][] data =  {row};
                        String[] columnNames = {"Registration Number", "Colour", "Make", "Model", "Chassis Number", "Engine Serial", "Year"};
                        JTable searchResults = new JTable(data, columnNames);
                        regNoScrollPane.setViewportView(searchResults);
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

                    String registrationNoText = registrationNumber.getText();
                    String colourText = colour.getText();
                    String makeText = make.getText();
                    String modelText = model.getText();
                    String chassisNoText = chassisNumber.getText();
                    String engineSerialText = engineSerial.getText();
                    String yearText = year.getText();
                    String oldRegNoText = oldRegNo.getText();

                    connection.setAutoCommit(false);
                    try (PreparedStatement statement = connection.prepareStatement("UPDATE Vehicles SET registrationNo = ?, colour = ?, make = ?, model = ?, chassisNo = ?, engineSerial = ?, year = ? WHERE registrationNo = ?")) {
                        statement.setString(1, registrationNoText);
                        statement.setString(2, colourText);
                        statement.setString(3, makeText);
                        statement.setString(4, modelText);
                        statement.setString(5, chassisNoText);
                        statement.setString(6, engineSerialText);
                        statement.setString(7, yearText);
                        statement.setString(8, oldRegNoText);
                        statement.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "Vehicle record updated!");
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
                int selectedVehicle = vehicleSearchResults.getSelectedRow();
                username.setText(searchResults.getModel().getValueAt(selectedRow, 1).toString());
                oldRegNo.setText(vehicleSearchResults.getModel().getValueAt(selectedVehicle, 0).toString());
                colour.setText(vehicleSearchResults.getModel().getValueAt(selectedVehicle, 1).toString());
                make.setText(vehicleSearchResults.getModel().getValueAt(selectedVehicle, 2).toString());
                model.setText(vehicleSearchResults.getModel().getValueAt(selectedVehicle, 3).toString());
                chassisNumber.setText(vehicleSearchResults.getModel().getValueAt(selectedVehicle, 4).toString());
                engineSerial.setText(vehicleSearchResults.getModel().getValueAt(selectedVehicle, 5).toString());
                year.setText(vehicleSearchResults.getModel().getValueAt(selectedVehicle, 6).toString());
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
