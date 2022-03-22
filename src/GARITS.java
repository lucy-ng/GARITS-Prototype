import Forms.*;
import Forms.Accounts.*;
import Forms.Users.*;
import Database.DBconnection;
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
        /*l.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Check database for login details

                // New adminPage page
                AdminPage adminPage = adminPage();
            }
        });*/
        return l;
    }

    public JFrame getMainWindow() {
        return mainWindow;
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
