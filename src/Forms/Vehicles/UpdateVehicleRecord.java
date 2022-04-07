package Forms.Vehicles;

import Database.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

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

    public UpdateVehicleRecord() {
        searchAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = usernameSearchField.getText();
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
                        usernameScrollPane.setViewportView(searchResults);
                        searchResults.setVisible(true);
                    }
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();;
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

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
