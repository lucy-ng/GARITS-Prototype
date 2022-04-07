package Forms.Jobs;

import Database.Job;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class ViewJobs {
    private JLabel addJobLabel;
    private JPanel mainPanel;
    private JLabel tableOfJobsLabel;
    private JTextField searchJob;
    private JButton searchButton;
    private JLabel searchForJobLabel;
    private JScrollPane scrollPane;

    public ViewJobs() {
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
                scrollPane.setViewportView(jobSearchResults);
                jobSearchResults.setVisible(true);
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();;
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
