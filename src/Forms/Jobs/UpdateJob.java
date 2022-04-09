package Forms.Jobs;

import Database.Job;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class UpdateJob {
    private JLabel updateJobTitle;
    private JLabel tableOfJobsLabel;
    private JTextField description;
    private JComboBox status;
    private JTextField mechanic;
    private JButton updateButton;
    private JPanel mainPanel;
    private JLabel descriptionLabel;
    private JLabel statusLabel;
    private JLabel mechanicLabel;
    private JLabel estimatedTimeLabel;
    private JTextField estimatedTime;
    private JLabel jobIDLabel;
    private JTextField jobID;
    private JScrollPane resultsJobs;
    private JLabel newJobIDLabel;
    private JTextField newJobID;
    private JLabel actualTimeHoursLabel;
    private JTextField actualTime;
    private JTable jobsTable;

    public UpdateJob() {
        resultsJobs.setPreferredSize(new Dimension(500,500));

        Vector headers = new Vector();
        headers.addElement("Job ID");
        headers.addElement("Description");
        headers.addElement("Estimated Time");
        headers.addElement("Actual Time");
        headers.addElement("Job Status");
        headers.addElement("Mechanic");
        Vector rows = new Vector();
        jobsTable = new JTable(rows, headers);
        DefaultTableModel jobsTableModel = (DefaultTableModel) jobsTable.getModel();
        resultsJobs.setViewportView(jobsTable);
        jobsTable.setVisible(true);

        ArrayList<Job> jobList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT * from Job");
            ResultSet rs = statement.executeQuery();

            PreparedStatement stmt = connection.prepareStatement("SELECT firstName FROM UserAccounts WHERE AccountID = (SELECT AccountID FROM Job WHERE jobID = ?)");

            Job job;

            while (rs.next()) {
                stmt.setInt(1, rs.getInt("jobID"));
                ResultSet r = stmt.executeQuery();
                while (r.next()) {
                    int jobID = rs.getInt("jobID");
                    String description = rs.getString("description");
                    String estimatedTime = rs.getString("estimatedTime");
                    String actualTime = rs.getString("actualTime");
                    String jobStatus = rs.getString("jobStatus");

                    String firstName = r.getString("firstName");

                    job = new Job(jobID, description, estimatedTime, actualTime,jobStatus);
                    jobList.add(job);

                    Object[] row = new Object[6];
                    for (int i = 0; i < jobList.size(); i++) {
                        row[0] = job.getJobID();
                        row[1] = job.getDescription();
                        row[2] = job.getEstimatedTime();
                        row[3] = job.getActualTime();
                        row[4] = job.getJobStatus();
                        row[5] = firstName;
                    }

                    jobsTableModel.addRow(row);
                }
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String jobIDText = jobID.getText();
                    String newJobIDText = newJobID.getText();
                    String mechanicText = mechanic.getText();
                    String descriptionText = description.getText();
                    String estimatedTimeText = estimatedTime.getText();
                    String actualTimeText = actualTime.getText();
                    String statusText = status.getSelectedItem().toString();

                    connection.setAutoCommit(false);

                    try (PreparedStatement stmt = connection.prepareStatement("UPDATE Job SET AccountID = (SELECT AccountID FROM UserAccounts WHERE firstName = ?)")) {
                        stmt.setString(1, mechanicText);
                        stmt.executeUpdate();
                    }

                    try (PreparedStatement statement = connection.prepareStatement("UPDATE Job SET jobID = ?, description = ?, estimatedTime = ?, actualTime = ?, jobStatus = ? WHERE jobID = ?")) {
                        statement.setString(1, newJobIDText);
                        statement.setString(2, descriptionText);
                        statement.setString(3, estimatedTimeText);
                        statement.setString(4, actualTimeText);
                        statement.setString(5, statusText);
                        statement.setString(6, jobIDText);
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
