package Forms;

import javax.swing.*;
import java.awt.*;

public class Homepage {
    private JLabel garitsTitle;
    private JButton loginButton;
    private JButton exitButton;
    private JPanel mainPanel;
    private JLabel icon;

    public Homepage() {
        ImageIcon logo = new ImageIcon(new ImageIcon("Images/TwoSix technologies-5.png").getImage().getScaledInstance(300,300, Image.SCALE_SMOOTH));
        icon.setIcon(logo);
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
