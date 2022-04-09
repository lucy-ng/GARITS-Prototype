package Forms.Jobs;

import Database.Job;
import Database.Part;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class UseParts {
    private JLabel usePartsLabel;
    private JPanel mainPanel;
    private JTextField searchParts;
    private JScrollPane resultsParts;
    private JLabel tableOfJobsLabel;
    private JScrollPane resultsJobs;
    private JLabel partNameLabel;
    private JTextField partName;
    private JLabel jobIDLabel;
    private JTextField jobID;
    private JButton useButton;
    private JLabel newQuantityLabel;
    private JTextField newQuantity;
    private JButton searchButton;
    private JLabel amountUsedLabel;
    private JTextField amountUsed;
    private JLabel dateUsedLabel;
    private JTextField dateUsed;
    private JLabel searchForSparePartsLabel;
    private JTable jobsTable;
    private JTable partsTable;

    public UseParts() {
        resultsParts.setSize(500,500);
        searchButton.addActionListener(new ActionListener() {
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
                        resultsParts.setViewportView(searchResults);
                        searchResults.setVisible(true);
                    }
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Parts used!");
                }
            }
        });

        Vector headers = new Vector();
        headers.addElement("Job ID");
        headers.addElement("Description");
        headers.addElement("Estimated Time");
        headers.addElement("Job Status");
        headers.addElement("Registration Number");
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

                    jobsTableModel.addRow(row);
                }
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
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
                        resultsParts.setViewportView(searchResults);
                        searchResults.setVisible(true);
                    }
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();;
                }
            }
        });

        Vector partHeaders = new Vector();
        partHeaders.addElement("Part Name");
        partHeaders.addElement("Manufacturer");
        partHeaders.addElement("Vehicle Type");
        partHeaders.addElement("Year");
        partHeaders.addElement("Price");
        partHeaders.addElement("Quantity");
        partHeaders.addElement("Low Threshold");
        Vector partRows = new Vector();
        partsTable = new JTable(partRows, partHeaders);
        DefaultTableModel partsTableModel = (DefaultTableModel) partsTable.getModel();
        resultsParts.setViewportView(partsTable);
        partsTable.setVisible(true);

        ArrayList<Part> partList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT * from SpareParts");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String manufacturer = rs.getString("manufacturer");
                String vehicleType = rs.getString("vehicleType");
                String year = rs.getString("year");
                BigDecimal price = rs.getBigDecimal("price");
                int quantity = rs.getInt("quantity");
                int lowThreshold = rs.getInt("lowThreshold");

                Part part;
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

                partsTableModel.addRow(row);
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        useButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");
                    String partNameText = partName.getText();
                    int newQuantityText = Integer.parseInt(newQuantity.getText());
                    Date dateUsedText = Date.valueOf(dateUsed.getText());
                    int amountUsedText = Integer.parseInt(amountUsed.getText());

                    PreparedStatement selectStmt = connection.prepareStatement("SELECT partID FROM SpareParts WHERE name = ?");
                    selectStmt.setString(1, partNameText);
                    ResultSet rs = selectStmt.executeQuery();

                    while (rs.next()) {
                        PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO SparePartsUse(amountUsed, partUseDate, partID) VALUES (?,?,?)");
                        insertStmt.setInt(1, amountUsedText);
                        insertStmt.setDate(2, dateUsedText);
                        insertStmt.setInt(3, rs.getInt("partID"));
                    }

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
