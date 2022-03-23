package Forms;

import Forms.Accounts.LoginAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage {
    private JLabel garitsTitle;
    private JButton loginButton;
    private JButton exitButton;
    private JPanel mainPanel;
    private JLabel icon;

    public Homepage(JFrame window) {
        ImageIcon logo = new ImageIcon(new ImageIcon("Images/TwoSix technologies-5.png").getImage().getScaledInstance(300,300, Image.SCALE_SMOOTH));
        icon.setIcon(logo);

        window.setContentPane(mainPanel);
        window.setVisible(true);

        // Login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // New login page
                LoginAccount loginAccount = new LoginAccount(window);
            }
        });

        // Exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
