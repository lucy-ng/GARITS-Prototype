import Forms.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Creating the GARITS system
public class GARITS {

    public GARITS() {
        LoginAccount loginAccount = new LoginAccount();
        JFrame mainWindow = new JFrame();
        mainWindow.setContentPane(loginAccount.getMainPanel());
        mainWindow.setTitle("GARITS");
        mainWindow.setSize(500,500);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

        loginAccount.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPage adminPage = new AdminPage();
                mainWindow.setContentPane(adminPage.getMainPanel());
                mainWindow.setSize(500,500);
                mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                mainWindow.setVisible(true);
            }
        });
    }

    // Running the GARITS system
    public static void main (String[] args){
        new GARITS();
    }
}
