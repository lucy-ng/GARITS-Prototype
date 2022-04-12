package Forms.Reception;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.util.Vector;

public class MOT_Reminder {
    private JLabel monthlyReportTitle;
    private JTextField dateToday;
    private JButton sendReminderButton;
    private JLabel dateTodayLabel;
    private JLabel MOTTestRemindersLabel;
    private JPanel mainPanel;
    private JButton enterDateButton;
    private JScrollPane motScrollPane;
    private JLabel customerUsernameLabel;
    private JTextField username;
    private JLabel customerEmailLabel;
    private JTextField email;
    private JLabel MOTDateLabel;
    private JTextField motDate;
    private JLabel registrationNumberLabel;
    private JTextField regNo;
    private JTable motTable;

    public MOT_Reminder() {
        Vector headers = new Vector();
        headers.addElement("MOT Date");
        headers.addElement("Registration Number");
        headers.addElement("Customer First Name");
        headers.addElement("Customer Last Name");
        headers.addElement("Customer Address");
        headers.addElement("Customer Email");
        Vector rows = new Vector();
        motTable = new JTable(rows, headers);
        DefaultTableModel motTableModel = (DefaultTableModel) motTable.getModel();
        motScrollPane.setViewportView(motTable);
        motTable.setVisible(true);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT motDate, registrationNo, CustomerAccountID from Booking");
            ResultSet rs = statement.executeQuery();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT firstName, lastName, email FROM UserAccounts WHERE AccountID = (SELECT AccountID FROM CustomerAccount WHERE CustomerAccountID = ?)");

            while (rs.next()) {
                PreparedStatement selectStmt = connection.prepareStatement("SELECT address FROM CustomerAccount WHERE CustomerAccountID = ?");
                selectStmt.setInt(1, rs.getInt("CustomerAccountID"));
                ResultSet r = selectStmt.executeQuery();
                while (r.next()) {
                    preparedStatement.setInt(1, rs.getInt("CustomerAccountID"));
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Date motDateText = rs.getDate("motDate");
                        String regNoText = rs.getString("registrationNo");

                        String firstNameText = resultSet.getString("firstName");
                        String lastNameText = resultSet.getString("lastName");
                        String emailText = resultSet.getString("email");

                        String addressText = r.getString("address");

                        Object[] row = new Object[6];
                        for (int i = 0; i < 6; i++) {
                            row[0] = motDateText;
                            row[1] = regNoText;
                            row[2] = firstNameText;
                            row[3] = lastNameText;
                            row[4] = emailText;
                            row[5] = addressText;
                        }
                        motTableModel.addRow(row);
                    }
                }
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        enterDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate today = LocalDate.parse(dateToday.getText());
                Date sevenDays = Date.valueOf(today.plusDays(7));

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
                    PreparedStatement statement = connection.prepareStatement("SELECT motDate, registrationNo, CustomerAccountID from Booking WHERE motDate = ?");
                    statement.setDate(1, sevenDays);
                    ResultSet rs = statement.executeQuery();

                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT firstName, lastName, email FROM UserAccounts WHERE AccountID = (SELECT AccountID FROM CustomerAccount WHERE CustomerAccountID = ?)");

                    while (rs.next()) {
                        PreparedStatement selectStmt = connection.prepareStatement("SELECT address FROM CustomerAccount WHERE CustomerAccountID = ?");
                        selectStmt.setInt(1, rs.getInt("CustomerAccountID"));
                        ResultSet r = selectStmt.executeQuery();
                        while (r.next()) {
                            preparedStatement.setInt(1, rs.getInt("CustomerAccountID"));
                            ResultSet resultSet = preparedStatement.executeQuery();
                            while (resultSet.next()) {
                                Date motDateText = rs.getDate("motDate");
                                String regNoText = rs.getString("registrationNo");

                                String firstNameText = resultSet.getString("firstName");
                                String lastNameText = resultSet.getString("lastName");
                                String emailText = resultSet.getString("email");

                                String addressText = r.getString("address");

                                Object[] row = new Object[6];
                                for (int i = 0; i < 6; i++) {
                                    row[0] = motDateText;
                                    row[1] = regNoText;
                                    row[2] = firstNameText;
                                    row[3] = lastNameText;
                                    row[4] = emailText;
                                    row[5] = addressText;
                                }

                                Object[][] data =  {row};
                                String[] columnNames = {"MOT Date", "Registration Number", "First Name", "Last Name", "Email", "Address"};
                                JTable searchResults = new JTable(data, columnNames);
                                motScrollPane.setViewportView(searchResults);
                                searchResults.setVisible(true);
                            }
                        }
                    }
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }

            }
        });

        sendReminderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendEmail();
            }
        });
    }

    public void sendEmail() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
