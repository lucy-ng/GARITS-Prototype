package Forms;

import javax.swing.*;

public class AdminPage extends JFrame {
    private JLabel adminPageTitle;
    private JButton backupDatabaseButton;
    private JButton restoreDatabaseButton;
    private JButton addAccountButton;
    private JButton updateAccountButton1;
    private JButton deleteAccountButton;
    private JLabel staffAccountsLabel;
    private JLabel welcomeLabel;
    private JLabel databaseLabel;
    private JPanel mainPanel;

    public AdminPage() {
        setContentPane(mainPanel);
        setTitle("Login to Account");
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
