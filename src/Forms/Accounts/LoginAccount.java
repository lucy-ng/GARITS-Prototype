package Forms.Accounts;

import Forms.Homepage;
import Forms.Users.AdminPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class LoginAccount {
    private JLabel loginTitle;
    private  JTextField username;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JButton loginButton;
    private JPanel mainPanel;




    public LoginAccount() {

        loginButton.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                String user = String.valueOf(username);
                String pass = String.valueOf(password);

                try {

                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
                    PreparedStatement statement = connection.prepareStatement("SELECT UserName,Password from UserAccounts where UserName = ? and Password = ?");
                    statement.setString(1, String.valueOf(username));
                    statement.setString(2, String.valueOf(password));
                    ResultSet rs = statement.executeQuery();
                    if(rs.next()) {
                        String username = rs.getString("UserName");
                        String password = rs.getString("Password");
                        if(user.equals(username) && pass.equals(password)) {
                            JOptionPane.showMessageDialog(null, "You have successfully logged in as Administrator");
                            AdminPage adminPage = new AdminPage();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"Wrong username or password");
                        LoginAccount loginAccount = new LoginAccount();
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();;
                }


            }
        });
    }



    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}