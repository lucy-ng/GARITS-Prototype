package Forms.Jobs;

import Database.Job;
import Database.Part;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class UseParts {
    private JLabel usePartsLabel;
    private JPanel mainPanel;
    private JLabel searchForSparePartsLabel;
    private JTextField searchParts;
    private JLabel tableOfSparePartsLabel;
    private JScrollPane scrollPane;
    private JLabel tableOfJobsLabel;
    private JScrollPane resultsJobs;
    private JLabel partNameLabel;
    private JTextField partName;
    private JLabel jobIDLabel;
    private JTextField jobID;
    private JButton useButton;
    private JLabel newQuantityLabel;
    private JTextField newQuantity;

    public UseParts() {
        ArrayList<Job> jobList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT * from Job");
            ResultSet rs = statement.executeQuery();
            Job job;

            while (rs.next()) {
                String description = rs.getString("description");
                String estimatedTime = rs.getString("estimatedTime");
                String jobStatus = rs.getString("jobStatus");
                String registrationNo = rs.getString("registrationNo");

                job = new Job(description, estimatedTime, jobStatus, registrationNo);
                jobList.add(job);

                Object[] row = new Object[4];
                for (int i = 0; i < jobList.size(); i++) {
                    row[0] = job.getDescription();
                    row[1] = job.getEstimatedTime();
                    row[2] = job.getJobStatus();
                    row[3] = job.getRegistrationNo();
                }

                Object[][] data =  {row};
                String[] columnNames = {"Description", "Estimated Time", "Job Status", "Registration Number"};
                JTable jobSearchResults = new JTable(data, columnNames);
                resultsJobs.setViewportView(jobSearchResults);
                jobSearchResults.setVisible(true);
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();;
        }

        searchParts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchParts.getText();
                ArrayList<Part> partList = new ArrayList<>();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
                    PreparedStatement statement = connection.prepareStatement("SELECT name, manufacturer, vehicleType, year, price, quantity, lowThreshold from SpareParts where name = ?");
                    statement.setString(1, text);
                    ResultSet rs = statement.executeQuery();
                    Part part;

                    while (rs.next()) {
                        String name = rs.getString("name");
                        String manufacturer = rs.getString("manufacturer");
                        String vehicleType = rs.getString("vehicleType");
                        String year = rs.getString("year");
                        BigDecimal price = rs.getBigDecimal("price");
                        int quantity = rs.getInt("quantity");
                        int lowThreshold = rs.getInt("lowThreshold");

                        part = new Part(name, manufacturer, vehicleType, year, price, quantity, lowThreshold);
                        partList.add(part);

                        Object[] row = new Object[7];
                        for (int i = 0; i < partList.size(); i++) {
                            row[0] = part.getName();
                            row[1] = part.getManufacturer();
                            row[2] = part.getVehicleType();
                            row[3] = part.getYear();
                            row[4] = part.getPrice();
                            row[5] = part.getQuantity();
                            row[6] = part.getLowThreshold();
                        }

                        Object[][] data =  {row};
                        String[] columnNames = {"Part Name", "Manufacturer", "Vehicle Type", "Year", "Price", "Quantity", "Low Threshold"};
                        JTable searchResults = new JTable(data, columnNames);
                        scrollPane.setViewportView(searchResults);
                        searchResults.setVisible(true);
                    }
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();;
                }
            }
        });

        useButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");
                    String partNameText = partName.getText();
                    int newQuantityText = Integer.parseInt(newQuantity.getText());

                    try (PreparedStatement updatePartStmt = connection.prepareStatement("UPDATE SpareParts SET quantity = ? WHERE name = ?")) {
                        updatePartStmt.setInt(1,  newQuantityText);
                        updatePartStmt.setString(2, partNameText);
                        updatePartStmt.executeUpdate();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    try (PreparedStatement updateJobStmt = connection.prepareStatement("UPDATE Job SET partID = (SELECT partID FROM SpareParts WHERE name = ?)")) {
                        updateJobStmt.setString(1, partNameText);
                        updateJobStmt.executeUpdate();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
