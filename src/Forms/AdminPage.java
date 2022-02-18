package Forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton logoutButton;

    public AdminPage() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }
}
