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
    private JTextArea jobDescription;
    private JPanel mainPanel;
    private JLabel addJobLabel;
    private JLabel descriptionOfJobLabel;
    private JLabel tableOfJobsLabel;
    private JScrollPane resultsVehicle;
    private JLabel estimatedTimeLabel;
    private JTextField mechanic;
    private JScrollPane resultsJobs;
    private JTextField estimatedTime;
    private JButton addJobButton;
    private JTextField searchSparePart;
    private JButton addSparePartButton;
    private JScrollPane resultsMechanic;
    private JScrollPane resultsSpareParts;
    private JComboBox jobStatus;
    private JLabel jobStatusLabel;
    private JLabel mechanicLabel;
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

    public AddJob() {
        resultsJobs.setPreferredSize(new Dimension(500,500));
        resultsMechanic.setPreferredSize(new Dimension(500,500));

        displayMechanics();

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

                job = new Job(jobID, description, estimatedTime, jobStatus);
                jobList.add(job);

                Object[] row = new Object[4];
                for (int i = 0; i < jobList.size(); i++) {
                    row[0] = job.getJobID();
                    row[1] = job.getDescription();
                    row[2] = job.getEstimatedTime();
                    row[3] = job.getJobStatus();
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
                    String descriptionText = jobDescription.getText();
                    String estimatedTimeText = estimatedTime.getText();
                    String jobStatusText = jobStatus.getSelectedItem().toString();

                    connection.setAutoCommit(false);
                    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Job (description, estimatedTime, jobStatus, AccountID) VALUES (?,?,?,(SELECT AccountID FROM UserAccounts WHERE username = ?))")) {
                        statement.setString(1, descriptionText);
                        statement.setString(2, estimatedTimeText);
                        statement.setString(3, jobStatusText);
                        statement.setString(4, mechanicText);
                        statement.executeUpdate();
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
