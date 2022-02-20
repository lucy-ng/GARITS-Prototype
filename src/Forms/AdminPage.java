package Forms;

import javax.swing.*;

public class AdminPage extends JFrame {
    private JPanel mainPanel;
    private JButton logoutButton;
    private JLabel adminPageTitle;
    private JButton addAccountButton;
    private JButton deleteAccountButton;
    private JButton updateAccountButton;
    private JButton backupButton;
    private JButton restoreButton;
    private JLabel staffAccountsLabel;
    private JLabel databaseLabel;
    private JPanel contentPanel;

    public AdminPage() {
        contentPanel.setLayout(null);
    }

    // Return panels
    public JPanel getMainPanel() {
        return mainPanel;
    }

    // Set and return content panel
    public JPanel getContentPanel() { return contentPanel; }

    // Return buttons
    public JButton getLogoutButton() {
        return logoutButton;
    }
    public JButton getAddAccountButton() {
        return addAccountButton;
    }
}
