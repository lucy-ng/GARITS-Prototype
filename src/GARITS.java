import Forms.*;
import Forms.Accounts.*;
import Forms.StockControl.*;
import Forms.Users.*;
import Forms.Reception.*;
import Forms.Jobs.*;

import javax.swing.*;
import java.awt.event.*;

// Creating the GARITS system
public class GARITS {
    final public JFrame mainWindow = new JFrame("GARITS");

    // Methods to return forms
    public Homepage homepage() {
        // Set up window for homepage
        Homepage h = new Homepage();
        mainWindow.setContentPane(h.getMainPanel());
        mainWindow.setSize(500,500);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        mainWindow.setSize(500,500);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        mainWindow.setSize(1000,650);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

        // Register account button
        a.getAddAccountButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterAccount r = new RegisterAccount();
                a.getContentPanel().add(r.getMainPanel());
                a.getContentPanel().setVisible(true);
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
        // Create instance of login account form
        Homepage homepage = homepage();
    }

    // Running the GARITS system
    public static void main (String[] args){
        new GARITS();
    }
}
