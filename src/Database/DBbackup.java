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
    public static void backup() {
        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");
            Process process = Runtime.getRuntime().exec("cmd /c mysqldump --column-statistics=0 -h smcse-stuproj00 -u in2018t26 -p5CrmPJHN in2018t26 --single-transaction > C:\\MySQLBackup\\DB_backup.sql", null,
                    new File("C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin"));




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


    public static void restore() {

    }








    }




