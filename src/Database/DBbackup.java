package Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBbackup {
    public  void Connection() {

        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");
            Statement statement = conn.createStatement();
            Process process = Runtime.getRuntime().exec("ping 8.8.8.8");
            Runtime.getRuntime().exec("cmd.exe /c start MySQLWorkbench.exe");


            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
                System.exit(0);
            } else {
                //abnormal...
            }



        } catch (Exception e) {
            e.printStackTrace();
        }




    }
    }



