package Forms.Users;

import Database.DBbackup;
import Forms.Accounts.*;
import Database.*;

import javax.swing.*;
import java.awt.event.*;

import static Database.DBbackup.backup;

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

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteAccount deleteAccount = new DeleteAccount();
                contentPanel.removeAll();
                contentPanel.add(deleteAccount.getMainPanel());
                contentPanel.revalidate();
            }
        });

        backupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backup();
                JOptionPane.showMessageDialog(null,"Database backup created");


            }
        });
    }
}
