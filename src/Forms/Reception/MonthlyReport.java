package Forms.Reception;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

public class MonthlyReport {
    private JLabel monthlyReportTitle;
    private JTextField dateFrom;
    private JTextField dateTo;
    private JTextField vehiclesMOT;
    private JTextField vehiclesService;
    private JButton printButton;
    private JPanel mainPanel;
    private JLabel numberOfVehiclesBookedLabel;
    private JLabel MOTLabel;
    private JLabel serviceLabel;
    private JScrollPane motScrollPane;
    private JScrollPane serviceScrollPane;
    private JLabel reportDateLabel;
    private JTextField reportDate;
    private JLabel dateFromLabel;
    private JLabel dateToLabel;
    private JLabel accountHolderLabel;
    private JTextField accountHolder;
    private JLabel casualLabel;
    private JTextField casual;
    private JLabel overallVehiclesLabel;
    private JTextField overallVehicles;
    private JLabel overallCustomersLabel;
    private JTextField overallCustomers;
    private JTable motTable;
    private JTable serviceTable;

    public MonthlyReport(String dateFromValue, String dateToValue) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String dateTodayText = simpleDateFormat.format(date);
        reportDate.setText(dateTodayText);
        dateFrom.setText(dateFromValue);
        dateTo.setText(dateToValue);

        motScrollPane.setSize(500,500);
        serviceScrollPane.setSize(500,500);

        Vector motHeaders = new Vector();
        motHeaders.addElement("First Name");
        motHeaders.addElement("Last Name");
        motHeaders.addElement("MOT Date");
        motHeaders.addElement("Registration Number");
        motHeaders.addElement("Amount");
        Vector motRows = new Vector();
        motTable = new JTable(motRows, motHeaders);
        DefaultTableModel motModel = (DefaultTableModel) motTable.getModel();
        motScrollPane.setViewportView(motTable);
        motTable.setVisible(true);

        Vector serviceHeaders = new Vector();
        serviceHeaders.addElement("First Name");
        serviceHeaders.addElement("Last Name");
        serviceHeaders.addElement("Service Date");
        serviceHeaders.addElement("Registration Number");
        serviceHeaders.addElement("Amount");
        Vector serviceRows = new Vector();
        serviceTable = new JTable(serviceRows, serviceHeaders);
        DefaultTableModel serviceModel = (DefaultTableModel) serviceTable.getModel();
        serviceScrollPane.setViewportView(serviceTable);
        serviceTable.setVisible(true);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

            PreparedStatement statement = connection.prepareStatement("SELECT motDate, motAmount, registrationNo FROM Booking WHERE motDate between date(?) AND date(?)");
            statement.setDate(1, java.sql.Date.valueOf(dateFromValue));
            statement.setDate(2, java.sql.Date.valueOf(dateToValue));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                PreparedStatement stmt = connection.prepareStatement("SELECT firstName, lastName FROM UserAccounts WHERE AccountID = (SELECT AccountID FROM CustomerAccount WHERE CustomerAccountID = (SELECT CustomerAccountID FROM Vehicles WHERE registrationNo = ?))");
                stmt.setString(1, rs.getString("registrationNo"));
                ResultSet resultSet = stmt.executeQuery();

                while(resultSet.next()) {
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    Date motDate = rs.getDate("motDate");
                    String regNo = rs.getString("registrationNo");
                    BigDecimal motAmount = rs.getBigDecimal("motAmount");

                    Object[] row = new Object[5];
                    for (int i = 0; i < 5; i++) {
                        row[0] = firstName;
                        row[1] = lastName;
                        row[2] = motDate;
                        row[3] = regNo;
                        row[4] = motAmount;
                    }
                    motModel.addRow(row);
                }
            }

            PreparedStatement selectStmt = connection.prepareStatement("SELECT serviceDate, serviceAmount, registrationNo FROM Booking WHERE serviceDate between date(?) AND date(?)");
            selectStmt.setDate(1, java.sql.Date.valueOf(dateFromValue));
            selectStmt.setDate(2, java.sql.Date.valueOf(dateToValue));
            ResultSet r = selectStmt.executeQuery();

            while (r.next()) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT firstName, lastName FROM UserAccounts WHERE AccountID = (SELECT AccountID FROM CustomerAccount WHERE CustomerAccountID = (SELECT CustomerAccountID FROM Vehicles WHERE registrationNo = ?))");
                preparedStatement.setString(1, r.getString("registrationNo"));
                ResultSet rsrs = preparedStatement.executeQuery();

                while (rsrs.next()) {
                    String firstName = rsrs.getString("firstName");
                    String lastName = rsrs.getString("lastName");
                    Date serviceDate = r.getDate("serviceDate");
                    String regNo = r.getString("registrationNo");
                    BigDecimal serviceAmount = r.getBigDecimal("serviceAmount");

                    Object[] row = new Object[5];
                    for (int i = 0; i < 5; i++) {
                        row[0] = firstName;
                        row[1] = lastName;
                        row[2] = serviceDate;
                        row[3] = regNo;
                        row[4] = serviceAmount;
                    }
                    serviceModel.addRow(row);
                }
            }

            PreparedStatement countMotStmt = connection.prepareStatement("SELECT COUNT(motDate) FROM Booking WHERE motDate between date(?) AND date(?)");
            countMotStmt.setDate(1, java.sql.Date.valueOf(dateFromValue));
            countMotStmt.setDate(2, java.sql.Date.valueOf(dateToValue));
            ResultSet resultMot = countMotStmt.executeQuery();

            PreparedStatement countServiceStmt = connection.prepareStatement("SELECT COUNT(serviceDate) FROM Booking WHERE serviceDate between date(?) AND date(?)");
            countServiceStmt.setDate(1, java.sql.Date.valueOf(dateFromValue));
            countServiceStmt.setDate(2, java.sql.Date.valueOf(dateToValue));
            ResultSet resultService = countServiceStmt.executeQuery();

            while (resultMot.next()) {
                while (resultService.next()) {
                    int countMot = resultMot.getInt(1);
                    int countService = resultService.getInt(1);
                    int overallVehiclesCount = countMot + countService;

                    overallVehicles.setText(String.valueOf(overallVehiclesCount));
                    vehiclesMOT.setText(String.valueOf(countMot));
                    vehiclesService.setText(String.valueOf(countService));
                }
            }

            PreparedStatement selectCustomer = connection.prepareStatement("SELECT CustomerAccountID FROM Vehicles WHERE registrationNo IN (SELECT registrationNo FROM Booking WHERE (motDate BETWEEN date(?) AND date(?)) OR (serviceDate BETWEEN date(?) AND date(?)))");
            selectCustomer.setDate(1, java.sql.Date.valueOf(dateFromValue));
            selectCustomer.setDate(2, java.sql.Date.valueOf(dateToValue));
            selectCustomer.setDate(3, java.sql.Date.valueOf(dateFromValue));
            selectCustomer.setDate(4, java.sql.Date.valueOf(dateToValue));
            ResultSet resultCustomer = selectCustomer.executeQuery();

            while (resultCustomer.next()) {
                PreparedStatement countAccountStmt = connection.prepareStatement("SELECT count(membershipType) FROM CustomerAccount WHERE membershipType = 'account holder' AND CustomerAccountID = ?");
                countAccountStmt.setInt(1, resultCustomer.getInt("CustomerAccountID"));
                ResultSet resultAccount = countAccountStmt.executeQuery();

                PreparedStatement countCasualStmt = connection.prepareStatement("SELECT count(membershipType) FROM CustomerAccount WHERE membershipType = 'casual' AND CustomerAccountID = ?");
                countCasualStmt.setInt(1, resultCustomer.getInt("CustomerAccountID"));
                ResultSet resultCasual = countCasualStmt.executeQuery();

                while (resultAccount.next()) {
                    while (resultCasual.next()) {
                        int countAccount = resultAccount.getInt(1);
                        int countCasual = resultCasual.getInt(1);
                        int overallCustomerCount = countAccount + countCasual;

                        overallCustomers.setText(String.valueOf(overallCustomerCount));
                        accountHolder.setText(String.valueOf(countAccount));
                        casual.setText(String.valueOf(countCasual));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
