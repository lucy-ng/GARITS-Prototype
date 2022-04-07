package Forms.StockControl;

import Database.Job;
import Database.Stock;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class StockLevelReport {
    private JPanel mainPanel;
    private JLabel stockLevelTitle;
    private JTextPane address;
    private JTextField reportPeriod1;
    private JTextField reportDate;
    private JButton printButton;
    private JLabel tableOfStockLabel;
    private JLabel reportPeriodLabel;
    private JLabel reportDateLabel;
    private JTextField reportPeriod2;
    private JScrollPane stockTable;


    public StockLevelReport() {
        ArrayList<Stock> stockList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT * from SpareParts");
            ResultSet rs = statement.executeQuery();
            Stock stock;

            while (rs.next()) {
                int partID = rs.getInt("partID");
                String name = rs.getString("name");
                String code = rs.getString("code");
                String manufacturer = rs.getString("manufacturer");
                String vehicleType = rs.getString("vehicleType");
                String year = rs.getString("year");
                Float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                int lowThreshold = rs.getInt("lowThreshold");

                stock = new Stock(partID,name,code,manufacturer,vehicleType,year,price,quantity,lowThreshold);
                stockList.add(stock);

                Object[] row = new Object[9];
                for (int i = 0; i < stockList.size(); i++) {
                    row[0] = stock.getPartID();
                    row[1] = stock.getName();
                    row[2] = stock.getCode();
                    row[3] = stock.getManufacturer();
                    row[4] = stock.getVehicleType();
                    row[5] = stock.getYear();
                    row[6] = stock.getPrice();
                    row[7] = stock.getQuantity();
                    row[8] = stock.getLowThreshold();
                }

                Object[][] data =  {row};
                String[] columnNames = {"partID","name","code","manufacturer","vehicleType","year","price","quantity","lowThreshold"};
                JTable stockResults = new JTable(data, columnNames);
                stockTable.setViewportView(stockResults);
                stockTable.setVisible(true);
            }
            connection.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
