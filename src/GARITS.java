import Forms.*;
import Forms.Accounts.*;
import Forms.StockControl.*;
import Forms.Users.*;
import Forms.Reception.*;
import Forms.Jobs.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
                // New admin page
                AdminPage adminPage = adminPage(l);
            }
        });
        return l;
    }

    // AdminPage
    public AdminPage adminPage(LoginAccount l) {
        // Set up window for admin
        AdminPage a = new AdminPage();
        mainWindow.setContentPane(a.getMainPanel());
        mainWindow.setVisible(true);

        // Register account button
        a.getAddAccountButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterAccount r = new RegisterAccount();
                a.getContentPanel().add(r.getMainPanel());

            }
        });

        // Logout button
        a.getLogoutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // New login account page
                LoginAccount loginAccount = loginAccount();
            }
        });
        return a;
    }

    public GARITS() {
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setSize(1250,650);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Menu
        JMenuBar menu = new JMenuBar();

        // Home button
        JButton homeButton = new JButton("Home");
        menu.add(homeButton);

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
