package Forms.Accounts;
import Forms.Users.AdminPage;
import Forms.Users.ForepersonPage;
import Users.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class LoginAccount {
    private JLabel loginTitle;
    private  JTextField username;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JButton loginButton;
    private JPanel mainPanel;

    public LoginAccount(JFrame window) {

        window.setContentPane(mainPanel);
        window.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                String user = String.valueOf(username.getText());
                String pass = String.valueOf(password.getPassword());

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
                    //PreparedStatement statement = connection.prepareStatement("SELECT UserName,Password from UserAccounts where UserName = ? and Password = ?");
                    PreparedStatement statement = connection.prepareStatement("SELECT UserAccounts.UserName, UserAccounts.Password, EmployeeAcct.EmployeePosition from UserAccounts INNER JOIN EmployeeAcct ON EmployeeAcct.AccountID = UserAccounts.AccountID where UserName = ? and Password = ?");
                    statement.setString(1, String.valueOf(username.getText()));
                    statement.setString(2, String.valueOf(password.getPassword()));
                    ResultSet rs = statement.executeQuery();

                    if(rs.next()) {
                        String username = rs.getString("UserName");
                        String password = rs.getString("Password");
                        if(user.equals(username) && pass.equals(password)) {
                            String role = rs.getString("EmployeePosition");
                            if (role.equals("admin")) {
                                JOptionPane.showMessageDialog(null, "You have successfully logged in as Administrator");
                                AdminPage adminPage = new AdminPage(window);
                            }
                            else if (role.equals("foreperson")){
                                JOptionPane.showMessageDialog(null, "You have successfully logged in as Foreperson");
                                ForepersonPage forepersonPage = new ForepersonPage(window);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"Wrong username or password");
                        LoginAccount loginAccount = new LoginAccount(window);
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