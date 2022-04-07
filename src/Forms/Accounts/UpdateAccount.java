package Forms.Accounts;

import Database.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class UpdateAccount {
    private JLabel updateTitle;
    private JLabel firstNameLabel;
    private JTextField firstName;
    private JLabel lastNameLabel;
    private JTextField lastName;
    private JLabel usernameLabel;
    private JTextField username;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JLabel roleLabel;
    private JTextField role;
    private JButton updateButton;
    private JPanel mainPanel;
    private JLabel emailLabel;
    private JTextField email;
    private JLabel phoneNumberLabel;
    private JTextField phoneNumber;
    private JLabel departmentLabel;
    private JTextField department;
    private JButton searchButton;
    private JTextField searchField;
    private JScrollPane scrollPane;
    private JLabel oldUsernameLabel;
    private JTextField oldUsername;
    private JTextField labourRate;
    private JLabel labourRateLabel;
    private JTable searchResults;

    public UpdateAccount() {
        scrollPane.setPreferredSize(new Dimension(500,500));

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
                        while (rsrs.next()){
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
                            fillResults();
                        }
                    }
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();;
                    JOptionPane.showMessageDialog(null, "No account found!");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String usernameText = username.getText();
                    String firstNameText = firstName.getText();
                    String lastNameText = lastName.getText();
                    String passwordText = String.valueOf(password.getPassword());
                    String emailText = email.getText();
                    String phoneNumberText = phoneNumber.getText();
                    String roleText = role.getText();
                    String departmentText = department.getText();
                    String oldUsernameText = oldUsername.getText();
                    String labourRateText = labourRate.getText();

                    connection.setAutoCommit(false);
                    try (PreparedStatement statement = connection.prepareStatement("UPDATE UserAccounts SET username = ?, firstName = ?, lastName = ?, password = ?, email = ?, phoneNo = ? WHERE username = ?")) {
                        statement.setString(1, usernameText);
                        statement.setString(2, firstNameText);
                        statement.setString(3, lastNameText);
                        statement.setString(4, passwordText);
                        statement.setString(5, emailText);
                        statement.setString(6, phoneNumberText);
                        statement.setString(7, oldUsernameText);
                        statement.executeUpdate();
                    }

                    try (PreparedStatement stmt = connection.prepareStatement("UPDATE EmployeeAccount SET EmployeePosition = ?, Department = ?, labourRate = ? WHERE AccountID = (SELECT AccountID FROM UserAccounts WHERE username = ?)")) {
                        stmt.setString(1, roleText);
                        stmt.setString(2, departmentText);
                        stmt.setString(3, labourRateText);
                        stmt.setString(4, usernameText);
                        stmt.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "Account updated!");
                    connection.setAutoCommit(true);
                    connection.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Fields cannot be null!");
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
                oldUsername.setText(searchResults.getModel().getValueAt(selectedRow, 1).toString());
                firstName.setText(searchResults.getModel().getValueAt(selectedRow,2).toString());
                lastName.setText(searchResults.getModel().getValueAt(selectedRow,3).toString());
                email.setText(searchResults.getModel().getValueAt(selectedRow,4).toString());
                phoneNumber.setText(searchResults.getModel().getValueAt(selectedRow,5).toString());
                role.setText(searchResults.getModel().getValueAt(selectedRow,6).toString());
                department.setText(searchResults.getModel().getValueAt(selectedRow,7).toString());
                labourRate.setText(searchResults.getModel().getValueAt(selectedRow,8).toString());
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
