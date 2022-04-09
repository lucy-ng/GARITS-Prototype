package Forms.Vehicles;

import Database.Vehicle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class DeleteVehicleRecord {
    private JLabel deleteVehicleRecordTitle;
    private JPanel mainPanel;
    private JTextField regNoSearchField;
    private JButton searchVehicleButton;
    private JScrollPane regNoScrollPane;
    private JLabel registrationNumberLabel;
    private JTextField regNo;
    private JButton deleteButton;
    private JTable vehicleSearchResults;

    public DeleteVehicleRecord() {
        regNoScrollPane.setPreferredSize(new Dimension(500,500));

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
            PreparedStatement statement = connection.prepareStatement("SELECT AccountID from CustomerAccount");
            ResultSet rs = statement.executeQuery();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT registrationNo, colour, make, model, chassisNo, engineSerial, year FROM Vehicles WHERE CustomerAccountID = (SELECT CustomerAccountID FROM CustomerAccount WHERE AccountID = ?)");


            while (rs.next()) {
                PreparedStatement selectStmt = connection.prepareStatement("SELECT username FROM UserAccounts WHERE AccountID = ?");
                selectStmt.setInt(1, rs.getInt("AccountID"));
                ResultSet r = selectStmt.executeQuery();
                while (r.next()) {
                    preparedStatement.setInt(1, rs.getInt("AccountID"));
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

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String regNoText = regNo.getText();

                    connection.setAutoCommit(false);

                    try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Vehicles WHERE registrationNo = ?")) {
                        statement.setString(1, regNoText);
                        statement.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "Vehicle record deleted!");
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
