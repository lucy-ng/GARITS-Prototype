package Forms.Accounts;
import Forms.Users.*;

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
                    PreparedStatement statement = connection.prepareStatement("SELECT UserAccounts.username, UserAccounts.password, EmployeeAccount.EmployeePosition from UserAccounts INNER JOIN EmployeeAccount ON EmployeeAccount.AccountID = UserAccounts.AccountID where username = ? and password = ?");
                    statement.setString(1, String.valueOf(username.getText()));
                    statement.setString(2, String.valueOf(password.getPassword()));
                    ResultSet rs = statement.executeQuery();

                    if(rs.next()) {
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        if(user.equals(username) && pass.equals(password)) {
                            String role = rs.getString("EmployeePosition");
                            if (role.equals("Admin")) {
                                JOptionPane.showMessageDialog(null, "You have successfully logged in as Administrator");
                                AdminPage adminPage = new AdminPage(window);
                            }
                            else if (role.equals("Foreperson")){
                                JOptionPane.showMessageDialog(null, "You have successfully logged in as Foreperson");
                                ForepersonPage forepersonPage = new ForepersonPage(window);
                            }
                            else if (role.equals("Franchisee")){
                                JOptionPane.showMessageDialog(null, "You have successfully logged in as Franchisee");
                                FranchiseePage franchiseePage = new FranchiseePage(window);
                            }
                            else if (role.equals("Mechanic")){
                                JOptionPane.showMessageDialog(null, "You have successfully logged in as Mechanic");
                                MechanicPage mechanicPage = new MechanicPage(window);
                            }
                            else if (role.equals("Receptionist")){
                                JOptionPane.showMessageDialog(null, "You have successfully logged in as Receptionist");
                                ReceptionistPage receptionistPage = new ReceptionistPage(window);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"Wrong username or password");
                        LoginAccount loginAccount = new LoginAccount(window);
                    }
                    connection.close();
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