// Write your names here

import Forms.*;
import Forms.Accounts.*;
import Forms.Users.*;
import Database.DBconnection;
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
        Homepage h = new Homepage(mainWindow);
        return h;
    }

    // LoginAccount
    public LoginAccount loginAccount() {
        // Set up window for login
        LoginAccount l = new LoginAccount(mainWindow);
        return l;
    }

    public JFrame getMainWindow() {
        return mainWindow;
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
                JOptionPane.showMessageDialog(null, "You have logged out");
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
