package Forms.Users;

import Forms.Accounts.RegisterAccount;
import Forms.Accounts.UpdateAccount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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

    public AdminPage(JFrame window) {
        window.setContentPane(mainPanel);
        window.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterAccount registerAccount = new RegisterAccount();
                contentPanel.removeAll();
                contentPanel.add(registerAccount.getMainPanel());
                contentPanel.revalidate();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateAccount updateAccount = new UpdateAccount();
                contentPanel.removeAll();
                contentPanel.add(updateAccount.getMainPanel());
                contentPanel.revalidate();
            }
        });

    }
}
