package Forms.Accounts;

import Database.EmployeeAccount;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class DeleteAccount {
    private JLabel deleteAccountTitle;
    private JTextField searchField;
    private JButton searchButton;
    private JScrollPane scrollPane;
    private JButton deleteButton;
    private JTextField usernameDelete;
    private JLabel usernameDeleteLabel;
    private JPanel mainPanel;
    private JTable searchResults;

    public DeleteAccount() {
        scrollPane.setPreferredSize(new Dimension(500,500));

        Vector headers = new Vector();
        headers.addElement("Username");
        headers.addElement("First Name");
        headers.addElement("Last Name");
        headers.addElement("Email");
        headers.addElement("Phone Number");
        headers.addElement("Role");
        headers.addElement("Department");
        headers.addElement("Labour Rate");
        Vector rows = new Vector();
        searchResults = new JTable(rows, headers);
        DefaultTableModel accountTableModel = (DefaultTableModel) searchResults.getModel();
        scrollPane.setViewportView(searchResults);
        searchResults.setVisible(true);

        ArrayList<EmployeeAccount> employeeAccountList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT * from EmployeeAccount");
            ResultSet rs = statement.executeQuery();

            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM UserAccounts WHERE AccountID = ?");

            while (rs.next()) {
                stmt.setInt(1, rs.getInt("AccountID"));
                ResultSet r = stmt.executeQuery();
                while (r.next()) {
                    String username = r.getString("username");
                    String firstName = r.getString("firstName");
                    String lastName = r.getString("lastName");
                    String email = r.getString("email");
                    String phoneNumber = r.getString("phoneNo");
                    String role = rs.getString("EmployeePosition");
                    String department = rs.getString("Department");
                    BigDecimal labourRate = rs.getBigDecimal("labourRate");

                    EmployeeAccount employeeAccount;
                    employeeAccount = new EmployeeAccount(username, firstName, lastName, email, phoneNumber, role, department, labourRate);
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
                    accountTableModel.addRow(row);
                    fillResults();
                }
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchField.getText();
                ArrayList<EmployeeAccount> employeeAccountList = new ArrayList<>();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
                    connection.setAutoCommit(false);
                    PreparedStatement statement = connection.prepareStatement("SELECT username, firstName, lastName, email, phoneNo from UserAccounts where username = ?");
                    statement.setString(1, text);
                    ResultSet rs = statement.executeQuery();

                    PreparedStatement stmt = connection.prepareStatement("SELECT EmployeePosition, Department, labourRate FROM EmployeeAccount WHERE AccountID = (SELECT AccountID FROM UserAccounts where username = ?)");
                    stmt.setString(1, text);
                    ResultSet rsrs = stmt.executeQuery();

                    EmployeeAccount employeeAccount;

                    while (rs.next()) {
                        while (rsrs.next()) {
                            String usernameText = rs.getString("username");
                            String firstNameText = rs.getString("firstName");
                            String lastNameText = rs.getString("lastName");
                            String emailText = rs.getString("email");
                            String phoneNoText = rs.getString("phoneNo");
                            String employeePositionText = rsrs.getString("EmployeePosition");
                            String departmentText = rsrs.getString("Department");
                            BigDecimal labourRateValue = rsrs.getBigDecimal("labourRate");

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
                            searchResults = new JTable(data, columnNames);
                            scrollPane.setViewportView(searchResults);
                            searchResults.setVisible(true);
                        }
                    }
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                    JOptionPane.showMessageDialog(null, "No account found!");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String usernameText = usernameDelete.getText();

                    connection.setAutoCommit(false);

                    try (PreparedStatement statement = connection.prepareStatement("DELETE FROM UserAccounts WHERE username = ?")) {
                        statement.setString(1, usernameText);
                        statement.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "Account deleted!");
                    connection.setAutoCommit(true);
                    connection.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void fillResults() {
        searchResults.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = searchResults.getSelectedRow();
                usernameDelete.setText(searchResults.getModel().getValueAt(selectedRow, 0).toString());
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
