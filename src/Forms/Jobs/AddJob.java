package Forms.Jobs;

import Database.EmployeeAccount;
import Database.Job;
import Database.Vehicle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class AddJob {
    private JTextField vehicle;
    private JTextArea jobDescription;
    private JPanel mainPanel;
    private JLabel addJobLabel;
    private JLabel descriptionOfJobLabel;
    private JLabel tableOfJobsLabel;
    private JLabel vehicleDetailsLabel;
    private JScrollPane resultsVehicle;
    private JLabel estimatedTimeLabel;
    private JLabel mechanicLabel;
    private JTextField mechanic;
    private JScrollPane resultsJobs;
    private JTextField estimatedTime;
    private JButton addJobButton;
    private JLabel jobStatusLabel;
    private JTextField searchSparePart;
    private JButton addSparePartButton;
    private JScrollPane resultsMechanic;
    private JScrollPane resultsSpareParts;
    private JComboBox jobStatus;
    private JTable sparePartsTable;
    private JTable vehicleSearchResults;
    private JTable mechanicSearchResults;
    private JTable jobSearchResults;

    public void displayMechanics() {
        Vector headers = new Vector();
        headers.addElement("Username");
        headers.addElement("First Name");
        headers.addElement("Last Name");
        headers.addElement("Email");
        headers.addElement("Phone Number");
        headers.addElement("Role");
        headers.addElement("Department");
        headers.addElement("Labour Rate");
        Vector rows = new Vector();
        mechanicSearchResults = new JTable(rows, headers);
        DefaultTableModel mechanicSearchResultsModel = (DefaultTableModel) mechanicSearchResults.getModel();
        resultsMechanic.setViewportView(mechanicSearchResults);
        mechanicSearchResults.setVisible(true);

        ArrayList<EmployeeAccount> employeeAccountList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT UserAccounts.username, UserAccounts.firstName, UserAccounts.lastName, UserAccounts.email, UserAccounts.phoneNo, EmployeeAccount.EmployeePosition, EmployeeAccount.Department, EmployeeAccount.labourRate from UserAccounts INNER JOIN EmployeeAccount ON UserAccounts.AccountID = EmployeeAccount.AccountID WHERE EmployeeAccount.EmployeePosition = 'Mechanic' ");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNo");
                String role = rs.getString("EmployeePosition");
                String department = rs.getString("Department");
                BigDecimal labourRate = rs.getBigDecimal("labourRate");

                EmployeeAccount employeeAccount;
                employeeAccount = new EmployeeAccount(username, firstName, lastName, email, phoneNumber, role, department, labourRate);
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
                mechanicSearchResultsModel.addRow(row);
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void displayVehicles() {
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
        resultsVehicle.setViewportView(vehicleSearchResults);
        vehicleSearchResults.setVisible(true);

        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT AccountID from CustomerAccount");
            ResultSet rs = statement.executeQuery();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT registrationNo, colour, make, model, chassisNo, engineSerial, year FROM Vehicles WHERE AccountID = ?");

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
    }

    public AddJob() {
        resultsJobs.setPreferredSize(new Dimension(500,500));
        resultsMechanic.setPreferredSize(new Dimension(500,500));
        resultsVehicle.setPreferredSize(new Dimension(500,500));

        displayMechanics();
        displayVehicles();

        Vector jobHeaders = new Vector();
        jobHeaders.addElement("Username");
        jobHeaders.addElement("Registration Number");
        jobHeaders.addElement("Colour");
        jobHeaders.addElement("Make");
        jobHeaders.addElement("Model");
        Vector jobRows = new Vector();
        jobSearchResults = new JTable(jobRows, jobHeaders);
        DefaultTableModel jobTableModel = (DefaultTableModel) jobSearchResults.getModel();
        resultsJobs.setViewportView(jobSearchResults);
        jobSearchResults.setVisible(true);

        ArrayList<Job> jobList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT * from Job");
            ResultSet rs = statement.executeQuery();
            Job job;

            while (rs.next()) {
                int jobID = rs.getInt("jobID");
                String description = rs.getString("description");
                String estimatedTime = rs.getString("estimatedTime");
                String jobStatus = rs.getString("jobStatus");
                String registrationNo = rs.getString("registrationNo");

                job = new Job(jobID, description, estimatedTime, jobStatus, registrationNo);
                jobList.add(job);

                Object[] row = new Object[5];
                for (int i = 0; i < jobList.size(); i++) {
                    row[0] = job.getJobID();
                    row[1] = job.getDescription();
                    row[2] = job.getEstimatedTime();
                    row[3] = job.getJobStatus();
                    row[4] = job.getRegistrationNo();
                }
                jobTableModel.addRow(row);
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();;
        }

        addJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String mechanicText = mechanic.getText();
                    String vehicleText = vehicle.getText();
                    String descriptionText = jobDescription.getText();
                    String estimatedTimeText = estimatedTime.getText();
                    String jobStatusText = jobStatus.getSelectedItem().toString();

                    connection.setAutoCommit(false);
                    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Job (description, estimatedTime, jobStatus, registrationNo) VALUES (?,?,?,?)")) {
                        statement.setString(1, descriptionText);
                        statement.setString(2, estimatedTimeText);
                        statement.setString(3, jobStatusText);
                        statement.setString(4, vehicleText);
                        statement.executeUpdate();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    try (PreparedStatement stmt = connection.prepareStatement("UPDATE EmployeeAccount SET jobID = LAST_INSERT_ID() WHERE EmployeePosition = 'Mechanic' AND AccountID = (SELECT AccountID FROM UserAccounts WHERE username = ?) ")) {
                        stmt.setString(1, mechanicText);
                        stmt.executeUpdate();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, "Added job!");
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
