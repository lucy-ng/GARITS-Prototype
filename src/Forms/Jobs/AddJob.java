package Forms.Jobs;

import Database.Part;
import Database.Vehicle;
import Forms.StockControl.SearchParts;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class AddJob {
    private JTextField searchCust;
    private JTextField searchVehicle;
    private JTextArea jobDescription;
    private JPanel mainPanel;
    private JLabel addJobLabel;
    private JLabel descriptionOfJobLabel;
    private JLabel sparePartsLabel;
    private JLabel tableOfJobsLabel;
    private JLabel customerDetailsLabel;
    private JLabel vehicleDetailsLabel;
    private JScrollPane resultsCust;
    private JScrollPane resultsVehicle;
    private JLabel estimatedTimeLabel;
    private JLabel mechanicLabel;
    private JTextField mechanic;
    private JScrollPane resultsJobs;
    private JButton searchCustomerButton;
    private JButton searchVehicleButton;
    private JButton searchMechanicButton;
    private JTextField estimatedTime;
    private JButton addJobButton;
    private JLabel jobStatusLabel;
    private JTextField searchSparePart;
    private JButton addSparePartButton;
    private JScrollPane resultsMechanic;
    private JScrollPane resultsSpareParts;
    private JComboBox jobStatus;

    public AddJob() {
        addJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String jobDescriptionText = jobDescription.getText();
                    String estimatedTimeText = estimatedTime.getText();
                    Object jobStatusText = jobStatus.getSelectedItem();
                    String registrationNoText = searchVehicle.getText();




                    connection.setAutoCommit(false);
                    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Job (description, estimatedTime, jobStatus,registrationNo ) VALUES (?,?,?,?)")) {
                        statement.setString(1, jobDescriptionText);
                        statement.setString(2, estimatedTimeText);
                        statement.setObject(3,jobStatusText);
                        statement.setString(4,registrationNoText);


                        statement.executeUpdate();
                    }

                   /* try (PreparedStatement stmtInsert = connection.prepareStatement("INSERT INTO EmployeeAccount (EmployeePosition, Department, AccountID, labourRate) VALUES (?,?, LAST_INSERT_ID(), ?)")) {
                        stmtInsert.setString(1, roleText);
                        stmtInsert.setString(2, departmentText);
                        stmtInsert.setString(3, labourRateText);
                        stmtInsert.executeUpdate();
                    }*/

                    JOptionPane.showMessageDialog(null, "Job added!");
                    connection.setAutoCommit(true);
                    connection.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                arrayList<Job> jobList = new arrayList
            }
        });

        addSparePartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchSparePart.getText();
                ArrayList<Part> partList = new ArrayList<>();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
                    PreparedStatement statement = connection.prepareStatement("SELECT name, code, manufacturer, vehicleType, year, price, quantity, lowThreshold from SpareParts where name = ?");
                    statement.setString(1, text);
                    ResultSet rs = statement.executeQuery();
                    Part part;

                    while (rs.next()) {
                        String name = rs.getString("name");
                        String code = rs.getString("code");
                        String manufacturer = rs.getString("manufacturer");
                        String vehicleType = rs.getString("vehicleType");
                        String year = rs.getString("year");
                        String price = rs.getString("price");
                        int quantity = rs.getInt("quantity");
                        int lowThreshold = rs.getInt("lowThreshold");

                        part = new Part(name, code, manufacturer, vehicleType, year, price, quantity, lowThreshold);
                        partList.add(part);

                        Object[] row = new Object[8];
                        for (int i = 0; i < partList.size(); i++) {
                            row[0] = part.getName();
                            row[1] = part.getCode();
                            row[2] = part.getManufacturer();
                            row[3] = part.getVehicleType();
                            row[4] = part.getYear();
                            row[5] = part.getPrice();
                            row[6] = part.getQuantity();
                            row[7] = part.getLowThreshold();
                        }

                        Object[][] data =  {row};
                        String[] columnNames = {"Part Name", "Code", "Manufacturer", "Vehicle Type", "Year", "Price", "Quantity", "Low Threshold"};
                        JTable searchResults = new JTable(data, columnNames);
                        resultsSpareParts.setViewportView(searchResults);
                        searchResults.setVisible(true);
                    }
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();;
                }

            }
        });

        searchVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchVehicle.getText();
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


                        vehicle = new Vehicle(registrationNo,colour,make,model,chassisNo,engineSerial,year);
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
                        String[] columnNames = {"Registration Number", "Colour", "Make", "Model", "Chassis Number", "Engine Serial Number", "Year"};
                        JTable searchResults = new JTable(data, columnNames);
                        resultsVehicle.setViewportView(searchResults);
                        searchResults.setVisible(true);
                    }
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();;
                }

            }
        });

        String text = searchVehicle.getText();
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


                vehicle = new Vehicle(registrationNo,colour,make,model,chassisNo,engineSerial,year);
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
                String[] columnNames = {"Registration Number", "Colour", "Make", "Model", "Chassis Number", "Engine Serial Number", "Year"};
                JTable searchResults = new JTable(data, columnNames);
                resultsVehicle.setViewportView(searchResults);
                searchResults.setVisible(true);
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();;
        }

    }


    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
