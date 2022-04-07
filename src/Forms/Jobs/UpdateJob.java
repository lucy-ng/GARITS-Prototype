package Forms.Jobs;

import Database.Job;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class UpdateJob {
    private JLabel updateJobTitle;
    private JLabel tableOfJobsLabel;
    private JTextField description;
    private JComboBox status;
    private JTextField vehicleDetails;
    private JTextField mechanic;
    private JButton updateButton;
    private JPanel mainPanel;
    private JLabel descriptionLabel;
    private JLabel statusLabel;
    private JLabel vehicleDetailsLabel;
    private JLabel mechanicLabel;
    private JLabel estimatedTimeLabel;
    private JTextField estimatedTime;
    private JLabel jobIDLabel;
    private JTextField jobID;
    private JScrollPane resultsJobs;
    private JLabel newJobIDLabel;
    private JTextField newJobID;

    public UpdateJob() {
        ArrayList<Job> jobList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT * from Job");
            ResultSet rs = statement.executeQuery();

            PreparedStatement stmt = connection.prepareStatement("SELECT firstName FROM UserAccounts WHERE AccountID = (SELECT AccountID FROM EmployeeAccount WHERE jobID = ?)");

            Job job;

            while (rs.next()) {
                stmt.setInt(1, rs.getInt("jobID"));
                ResultSet r = stmt.executeQuery();
                while (r.next()) {
                    int jobID = rs.getInt("jobID");
                    String description = rs.getString("description");
                    String estimatedTime = rs.getString("estimatedTime");
                    String jobStatus = rs.getString("jobStatus");
                    String registrationNo = rs.getString("registrationNo");

                    String firstName = r.getString("firstName");

                    job = new Job(jobID, description, estimatedTime, jobStatus, registrationNo);
                    jobList.add(job);

                    Object[] row = new Object[6];
                    for (int i = 0; i < jobList.size(); i++) {
                        row[0] = job.getJobID();
                        row[1] = job.getDescription();
                        row[2] = job.getEstimatedTime();
                        row[3] = job.getJobStatus();
                        row[4] = job.getRegistrationNo();
                        row[5] = firstName;
                    }

                    Object[][] data =  {row};
                    String[] columnNames = {"JobID", "Description", "Estimated Time", "Job Status", "Registration Number", "Mechanic"};
                    JTable jobSearchResults = new JTable(data, columnNames);
                    resultsJobs.setViewportView(jobSearchResults);
                    jobSearchResults.setVisible(true);
                }
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();;
        }

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String jobIDText = jobID.getText();
                    String newJobIDText = newJobID.getText();
                    String mechanicText = mechanic.getText();
                    String vehicleDetailsText = vehicleDetails.getText();
                    String descriptionText = description.getText();
                    String estimatedTimeText = estimatedTime.getText();
                    String statusText = status.getSelectedItem().toString();

                    connection.setAutoCommit(false);
                    try (PreparedStatement updateStmt = connection.prepareStatement("UPDATE EmployeeAccount SET JobID = null WHERE jobID = ?")) {
                        updateStmt.setString(1, jobIDText);
                        updateStmt.executeUpdate();
                    }

                    try (PreparedStatement stmt = connection.prepareStatement("UPDATE EmployeeAccount SET JobID = ? WHERE AccountID = (SELECT AccountID FROM UserAccounts WHERE firstName = ?)")) {
                        stmt.setString(1, newJobIDText);
                        stmt.setString(2, mechanicText);
                        stmt.executeUpdate();
                    }

                    try (PreparedStatement statement = connection.prepareStatement("UPDATE Job SET description = ?, estimatedTime = ?, jobStatus = ?, registrationNo = ? WHERE jobID = ?")) {
                        statement.setString(1, descriptionText);
                        statement.setString(2, estimatedTimeText);
                        statement.setString(3, statusText);
                        statement.setString(4, vehicleDetailsText);
                        statement.setString(5, jobIDText);
                        statement.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "Job updated!");
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
