package Forms.Jobs;

import Database.EmployeeAccount;
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

public class AddJob {
    private JTextField vehicle;
    private JTextArea jobDescription;
    private JPanel mainPanel;
    private JLabel addJobLabel;
    private JLabel descriptionOfJobLabel;
    private JLabel sparePartsLabel;
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

    public AddJob() {
        Vector headers = new Vector();
        headers.addElement("Part Name");
        headers.addElement("Manufacturer");
        headers.addElement("Vehicle Type");
        headers.addElement("Year");
        headers.addElement("Price");
        headers.addElement("Quantity");
        headers.addElement("Low Threshold");
        Vector rows = new Vector();
        sparePartsTable = new JTable(rows, headers);
        DefaultTableModel sparePartsModel = (DefaultTableModel) sparePartsTable.getModel();
        resultsSpareParts.setViewportView(sparePartsTable);
        sparePartsTable.setVisible(true);

        displayMechanics();

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

        addSparePartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String partNameText = searchSparePart.getText();
                    ArrayList<Part> partList = new ArrayList<>();
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
                    PreparedStatement statement = connection.prepareStatement("SELECT name, manufacturer, vehicleType, year, price, quantity, lowThreshold from SpareParts where name = ?");
                    statement.setString(1, partNameText);
                    ResultSet rs = statement.executeQuery();
                    Part part;

                    while (rs.next()) {
                        String nameText = rs.getString("name");
                        String manufacturerText = rs.getString("manufacturer");
                        String vehicleTypeText = rs.getString("vehicleType");
                        String yearText = rs.getString("year");
                        BigDecimal priceText = rs.getBigDecimal("price");
                        int quantityText = rs.getInt("quantity");
                        int lowThresholdText = rs.getInt("lowThreshold");

                        part = new Part(nameText, manufacturerText, vehicleTypeText, yearText, priceText, quantityText, lowThresholdText);
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

                        sparePartsModel.addRow(row);
                    }
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();;
                }
            }
        });

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

                    try (PreparedStatement updatePartStmt = connection.prepareStatement("UPDATE SpareParts SET quantity = ? WHERE name = ?")) {
                        for (int i = 1; i < sparePartsModel.getRowCount(); i++) {
                            String partName = sparePartsModel.getValueAt(i,1).toString();
                            int partQuantity = (int) sparePartsModel.getValueAt(i, 6);
                            int newPartQuantity = partQuantity - 1;

                            updatePartStmt.setInt(1,  newPartQuantity);
                            updatePartStmt.setString(2, partName);
                            updatePartStmt.executeUpdate();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    try (PreparedStatement updateJobStmt = connection.prepareStatement("UPDATE Job SET partID = (SELECT partID FROM SpareParts WHERE name = ?)")) {
                        for (int i = 1; i < sparePartsModel.getRowCount(); i++) {
                            String partNameText = sparePartsModel.getValueAt(i,1).toString();
                            updateJobStmt.setString(1, partNameText);
                            updateJobStmt.executeUpdate();
                        }
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
