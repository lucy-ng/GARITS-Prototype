package Forms.Jobs;

import Database.EmployeeAccount;
import Database.Job;
import Database.Part;
import Database.Vehicle;

import javax.swing.*;
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

    public void displayMechanics() {
        resultsJobs.setPreferredSize(new Dimension(500,500));
        resultsMechanic.setPreferredSize(new Dimension(500,500));
        resultsVehicle.setPreferredSize(new Dimension(500,500));

        ArrayList<EmployeeAccount> employeeAccountList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("SELECT UserAccounts.username, UserAccounts.firstName, UserAccounts.lastName, UserAccounts.email, UserAccounts.phoneNo," +
                    "EmployeeAccount.EmployeePosition, EmployeeAccount.Department, EmployeeAccount.labourRate from UserAccounts INNER JOIN EmployeeAccount ON UserAccounts.AccountID = EmployeeAccount.AccountID WHERE EmployeeAccount.EmployeePosition = 'Mechanic' ");
            ResultSet rs = statement.executeQuery();

            EmployeeAccount employeeAccount;

            while (rs.next()) {
                String usernameText = rs.getString("username");
                String firstNameText = rs.getString("firstName");
                String lastNameText = rs.getString("lastName");
                String emailText = rs.getString("email");
                String phoneNoText = rs.getString("phoneNo");
                String employeePositionText = rs.getString("EmployeePosition");
                String departmentText = rs.getString("Department");
                BigDecimal labourRateValue = rs.getBigDecimal("labourRate");

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
                JTable mechanicResults = new JTable(data, columnNames);
                resultsMechanic.setViewportView(mechanicResults);
                mechanicResults.setVisible(true);
            }
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void displayVehicles() {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT registrationNo, colour, make, model, chassisNo, engineSerial, year from Vehicles");
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
                resultsVehicle.setViewportView(searchResults);
                searchResults.setVisible(true);
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();;
        }
    }

    public AddJob() {
        displayMechanics();
        displayVehicles();

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

                Object[][] data =  {row};
                String[] columnNames = {"JobID", "Description", "Estimated Time", "Job Status", "Registration Number"};
                JTable jobSearchResults = new JTable(data, columnNames);
                resultsJobs.setViewportView(jobSearchResults);
                jobSearchResults.setVisible(true);
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
