package Forms.Jobs;

import Database.Job;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class ViewJobs {
    private JLabel addJobLabel;
    private JPanel mainPanel;
    private JLabel tableOfJobsLabel;
    private JButton searchButton;
    private JScrollPane scrollPane;
    private JTable jobsTable;

    public ViewJobs() {
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
        scrollPane.setViewportView(jobsTable);
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
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
