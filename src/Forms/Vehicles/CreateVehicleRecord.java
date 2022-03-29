package Forms.Vehicles;

import Database.UserAccount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class CreateVehicleRecord {
    private JLabel createVehicleRecordTitle;
    private JLabel registrationNumberLabel;
    private JTextField registrationNumber;
    private JTextField searchField;
    private JButton searchButton;
    private JScrollPane scrollPane;
    private JLabel colourLabel;
    private JTextField colour;
    private JLabel makeLabel;
    private JTextField make;
    private JLabel modelLabel;
    private JTextField model;
    private JLabel chassisNumberLabel;
    private JTextField chassisNumber;
    private JLabel engineSerialLabel;
    private JTextField engineSerial;
    private JTextField year;
    private JButton createButton;
    private JLabel yearLabel;
    private JLabel accountUsernameLabel;
    private JTextField username;
    private JPanel mainPanel;

    public CreateVehicleRecord() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchField.getText();
                ArrayList<UserAccount> userAccountList = new ArrayList<>();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
                    PreparedStatement statement = connection.prepareStatement("SELECT username, firstName, lastName, email, phoneNo from UserAccounts where username = ?");
                    statement.setString(1, text);
                    ResultSet rs = statement.executeQuery();
                    UserAccount userAccount;

                    while (rs.next()) {
                        String username = rs.getString("username");
                        String firstName = rs.getString("firstName");
                        String lastName = rs.getString("lastName");
                        String email = rs.getString("email");
                        String phoneNo = rs.getString("phoneNo");

                        userAccount = new UserAccount(username, firstName, lastName, email, phoneNo);
                        userAccountList.add(userAccount);

                        Object[] row = new Object[5];
                        for (int i = 0; i < userAccountList.size(); i++) {
                            row[0] = userAccount.getUsername();
                            row[1] = userAccount.getFirstName();
                            row[2] = userAccount.getLastName();
                            row[3] = userAccount.getEmail();
                            row[4] = userAccount.getPhoneNo();
                        }

                        Object[][] data =  {row};
                        String[] columnNames = {"Username", "First Name", "Last Name", "Email", "Phone Number"};
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

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String registrationNoText = registrationNumber.getText();
                    String colourText = colour.getText();
                    String makeText = make.getText();
                    String modelText = model.getText();
                    String chassisNoText = chassisNumber.getText();
                    String engineSerialText = engineSerial.getText();
                    String yearText = year.getText();
                    String usernameText = username.getText();

                    connection.setAutoCommit(false);

                    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Vehicles (registrationNo, colour, make, model, chassisNo, engineSerial, year, AccountID) VALUES (?,?,?,?,?,?,?,(SELECT AccountID FROM UserAccounts WHERE username = ?))")) {
                        statement.setString(1, registrationNoText);
                        statement.setString(2, colourText);
                        statement.setString(3, makeText);
                        statement.setString(4, modelText);
                        statement.setString(5, chassisNoText);
                        statement.setString(6, engineSerialText);
                        statement.setString(7, yearText);
                        statement.setString(8, usernameText);
                        statement.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "Vehicle record created!");
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
