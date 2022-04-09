package Forms.Reception;

import Database.Vehicle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class MOT_Reminder {
    private JLabel monthlyReportTitle;
    private JTextField date;
    private JButton sendRemindersButton;
    private JLabel dateLabel;
    private JLabel MOTTestRemindersLabel;
    private JPanel mainPanel;
    private JButton enterDateButton;
    private JScrollPane motScrollPane;
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
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
                    PreparedStatement statement = connection.prepareStatement("SELECT motDate from Booking");
                    ResultSet rs = statement.executeQuery();

                    while (rs.next()) {
                        PreparedStatement stmt = connection.prepareStatement("SELECT email FROM UserAccounts WHERE AccountID = (SELECT AccountID FROM CustomerAccount)");
                        ResultSet r = stmt.executeQuery();
                        while (r.next()) {
                            Date motDateText = rs.getDate("motDate");
                            String emailText = r.getString("email");
                        }
                    }
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        sendRemindersButton.addActionListener(new ActionListener() {
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
