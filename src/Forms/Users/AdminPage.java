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

    public AdminPage(JButton addButton, JButton updateButton, JButton deleteButton, JButton backupButton, JButton restoreButton, JPanel contentPanel, JLabel adminPageTitle, JPanel mainPanel, JLabel accountsLabel, JLabel databaseLabel) {
        this.addButton = addButton;
        this.updateButton = updateButton;
        this.deleteButton = deleteButton;
        this.backupButton = backupButton;
        this.restoreButton = restoreButton;
        this.contentPanel = contentPanel;
        this.adminPageTitle = adminPageTitle;
        this.mainPanel = mainPanel;
        this.accountsLabel = accountsLabel;
        this.databaseLabel = databaseLabel;
    }
}
