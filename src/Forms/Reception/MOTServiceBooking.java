package Forms.Reception;

import Database.Vehicle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class MOTServiceBooking {
    private JLabel MOTAndServiceBookingLabel;
    private JPanel mainPanel;
    private JTextField regNoSearchField;
    private JButton searchVehicleButton;
    private JScrollPane regNoScrollPane;
    private JLabel registrationNumberLabel;
    private JTextField regNo;
    private JLabel serviceDateLabel;
    private JTextField serviceDate;
    private JLabel serviceAmountLabel;
    private JTextField serviceAmount;
    private JComboBox havePaidService;
    private JLabel MOTDateLabel;
    private JTextField motDate;
    private JLabel MOTAmountLabel;
    private JTextField motAmount;
    private JButton bookButton;
    private JComboBox havePaidMOT;

    public MOTServiceBooking() {
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String regNoText = regNo.getText();
                    String serviceDateText = serviceDate.getText();
                    String serviceAmountText = serviceAmount.getText();
                    String motDateText = motDate.getText();
                    String motAmountText = motAmount.getText();
                    String havePaidServiceText = havePaidService.getSelectedItem().toString();
                    String havePaidMOTText = havePaidMOT.getSelectedItem().toString();

                    int havePaidService = 0;
                    int havePaidMOT = 0;

                    if (havePaidServiceText.equals("Yes")) {
                        havePaidService = 1;
                    }
                    else if (havePaidServiceText.equals("No")) {
                        havePaidService = 0;
                    }

                    if (havePaidMOTText.equals("Yes")) {
                        havePaidMOT = 1;
                    }
                    else if (havePaidMOTText.equals("No")) {
                        havePaidMOT = 0;
                    }

                    connection.setAutoCommit(false);
                    PreparedStatement stmt = connection.prepareStatement("SELECT CustomerAccountID FROM CustomerAccount WHERE AccountID = (SELECT AccountID FROM Vehicles WHERE registrationNo = ?)");
                    stmt.setString(1, regNoText);
                    ResultSet resultSet = stmt.executeQuery();

                    while (resultSet.next()) {
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO Booking (serviceDate, serviceAmount, servicePaid, motDate, motAmount, motPaid, CustomerAccountID, registrationNo) VALUES (?,?,?,?,?,?,?,?)");
                        statement.setString(1, serviceDateText);
                        statement.setString(2, serviceAmountText);
                        statement.setInt(3, havePaidService);
                        statement.setString(4, motDateText);
                        statement.setString(5, motAmountText);
                        statement.setInt(6, havePaidMOT);
                        statement.setInt(7, resultSet.getInt("CustomerAccountID"));
                        statement.setString(8, regNoText);

                        statement.executeUpdate();
                    }
                    JOptionPane.showMessageDialog(null, "MOT and Service booked!");
                    connection.setAutoCommit(true);
                    connection.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
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
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
