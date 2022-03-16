package Forms.Users;

import javax.swing.*;

public class AdminPage {
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton backupButton;
    private JButton restoreButton;
    private JPanel contentPanel;
    private JLabel adminPageTitle;
    private JPanel mainPanel;
    private JLabel accountsLabel;
    private JLabel databaseLabel;

    public AdminPage() {

    }

    // Return panels
    public JPanel getMainPanel() {
        return mainPanel;
    }

    // Set and return content panel
    public JPanel getContentPanel() { return contentPanel; }

    // Return buttons
    public JButton getAddAccountButton() {
        return addButton;
    }
    public JButton getUpdateAccountButton() { return updateButton; }
}
