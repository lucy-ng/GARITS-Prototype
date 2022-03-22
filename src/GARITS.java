import Forms.*;
import Forms.Accounts.*;
import Forms.Users.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

// Creating the GARITS system
public class GARITS {
    final public JFrame mainWindow = new JFrame("GARITS");

    // Methods to return forms
    public Homepage homepage() {
        // Set up window for homepage
        Homepage h = new Homepage();
        mainWindow.setContentPane(h.getMainPanel());
        mainWindow.setVisible(true);

        // Login button
        h.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // New login page
                LoginAccount loginAccount = loginAccount();
            }
        });

        // Exit button
        h.getExitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return h;
    }

    // LoginAccount
    public LoginAccount loginAccount() {
        // Set up window for login
        LoginAccount l = new LoginAccount();
        mainWindow.setContentPane(l.getMainPanel());
        mainWindow.setVisible(true);

        // Login button
        l.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // New adminPage page
                AdminPage adminPage = adminPage();
            }
        });
        return l;
    }

    // Admin Page
    public AdminPage adminPage(){
        AdminPage adminPage = new AdminPage();
        mainWindow.setContentPane(adminPage.getMainPanel());
        mainWindow.setVisible(true);

        adminPage.getAddAccountButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterAccount registerAccount = new RegisterAccount();
                adminPage.getContentPanel().removeAll();
                adminPage.getContentPanel().add(registerAccount.getMainPanel());
                adminPage.getContentPanel().revalidate();

                registerAccount.getRegisterButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {

                            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");
                            //Statement statement = connection.createStatement();

                            String usernameText = registerAccount.getUsername().getText();
                            String firstNameText = registerAccount.getFirstName().getText();
                            String secondNameText = registerAccount.getSecondName().getText();
                            String positionText = registerAccount.getRole().getText();

                            /*
                            String insertQuery = "insert into EmployeeAccount (username, firstName, secondName, position) values("+usernameText+", "+firstNameText+", "+secondNameText+", "+positionText+")" ;
                            statement.executeUpdate(insertQuery);

                             */

                            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO EmployeeAccount VALUES (?,?,?,?)")) {
                                statement.setString(1, usernameText);
                                statement.setString(2, firstNameText);
                                statement.setString(3, secondNameText);
                                statement.setString(4, positionText);
                                statement.executeUpdate();
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });

        adminPage.getUpdateAccountButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateAccount updateAccount = new UpdateAccount();
                adminPage.getContentPanel().removeAll();
                adminPage.getContentPanel().add(updateAccount.getMainPanel());
                adminPage.getContentPanel().revalidate();
            }
        });

        return adminPage;
    }

    public GARITS() {
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setSize(1250,650);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Menu
        JMenuBar menu = new JMenuBar();

        // Home button
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Homepage homepage = homepage();
            }
        });
        menu.add(homeButton);

        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginAccount loginAccount = loginAccount();
            }
        });
        menu.add(logoutButton);

        // Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(exitButton);

        // Add menu
        mainWindow.setJMenuBar(menu);
        // Homepage
        Homepage homepage = homepage();
    }

    // Running the GARITS system
    public static void main (String[] args){
        new GARITS();
    }
}
