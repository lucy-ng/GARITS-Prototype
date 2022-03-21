package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
// Test class which prints a record from a table in the db
public class DBconnection {
    public static void main(String[] args) {

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from people ");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("firstName"));
                System.out.println(resultSet.getString("lastName"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
