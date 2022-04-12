package Forms.StockControl;

import Database.Stock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;


public class StockLevelReport extends Component {
    private JPanel mainPanel;
    private JLabel stockLevelTitle;
    private JTextPane address;
    private JTextField reportDate;
    private JButton printButton;
    private JLabel tableOfStockLabel;
    private JLabel dateFromLabel;
    private JLabel dateToLabel;
    private JLabel reportDateLabel;
    private JLabel reportPeriodLabel;
    private JScrollPane stockTable;
    private JTextField dateFrom;
    private JTextField dateTo;
    private JTable sparePartsTable;

    public StockLevelReport(String dateFromValue, String dateToValue) {
        stockTable.setSize(500,500);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String dateTodayText = simpleDateFormat.format(date);
        reportDate.setText(dateTodayText);
        dateFrom.setText(dateFromValue);
        dateTo.setText(dateToValue);

        Vector headers = new Vector();
        headers.addElement("Name");
        headers.addElement("Code");
        headers.addElement("Manufacturer");
        headers.addElement("Vehicle Type");
        headers.addElement("Year");
        headers.addElement("Price");
        headers.addElement("Quantity");
        headers.addElement("Low Threshold");
        headers.addElement("Used");
        headers.addElement("Delivered");
        Vector rows = new Vector();
        sparePartsTable = new JTable(rows, headers);
        DefaultTableModel sparePartsModel = (DefaultTableModel) sparePartsTable.getModel();
        stockTable.setViewportView(sparePartsTable);
        sparePartsTable.setVisible(true);

        ArrayList<Stock> stockList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM SparePartsOrder, SpareParts WHERE (SparePartsOrder.deliveryDate BETWEEN date(?) AND date(?) AND (SpareParts.partID = SparePartsOrder.partID))");
            statement.setDate(1, java.sql.Date.valueOf(dateFromValue));
            statement.setDate(2, java.sql.Date.valueOf(dateToValue));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String code = rs.getString("code");
                String manufacturer = rs.getString("manufacturer");
                String vehicleType = rs.getString("vehicleType");
                String year = rs.getString("year");
                BigDecimal price = rs.getBigDecimal("price");
                int quantity = rs.getInt("quantity");
                int lowThreshold = rs.getInt("lowThreshold");
                int amountDelivered = rs.getInt("amountDelivered");

                Stock stock;
                stock = new Stock(name,code,manufacturer,vehicleType,year,price,quantity,lowThreshold);
                stockList.add(stock);

                Object[] row = new Object[10];
                for (int i = 0; i < stockList.size(); i++) {
                    row[0] = stock.getName();
                    row[1] = stock.getCode();
                    row[2] = stock.getManufacturer();
                    row[3] = stock.getVehicleType();
                    row[4] = stock.getYear();
                    row[5] = stock.getPrice();
                    row[6] = stock.getQuantity();
                    row[7] = stock.getLowThreshold();
                    row[8] = 0;
                    row[9] = amountDelivered;
                }
                sparePartsModel.addRow(row);
            }

            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM SparePartsUse, SpareParts WHERE (SparePartsUse.partUseDate BETWEEN date(?) AND date(?) AND (SpareParts.partID = SparePartsUse.partID))");
            stmt.setDate(1, java.sql.Date.valueOf(dateFromValue));
            stmt.setDate(2, java.sql.Date.valueOf(dateToValue));
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String code = resultSet.getString("code");
                String manufacturer = resultSet.getString("manufacturer");
                String vehicleType = resultSet.getString("vehicleType");
                String year = resultSet.getString("year");
                BigDecimal price = resultSet.getBigDecimal("price");
                int quantity = resultSet.getInt("quantity");
                int lowThreshold = resultSet.getInt("lowThreshold");
                int amountUsed = resultSet.getInt("amountUsed");

                Stock stock;
                stock = new Stock(name,code,manufacturer,vehicleType,year,price,quantity,lowThreshold);
                stockList.add(stock);

                Object[] row = new Object[10];
                for (int i = 0; i < stockList.size(); i++) {
                    row[0] = stock.getName();
                    row[1] = stock.getCode();
                    row[2] = stock.getManufacturer();
                    row[3] = stock.getVehicleType();
                    row[4] = stock.getYear();
                    row[5] = stock.getPrice();
                    row[6] = stock.getQuantity();
                    row[7] = stock.getLowThreshold();
                    row[8] = amountUsed;
                    row[9] = 0;
                }
                sparePartsModel.addRow(row);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printPanel(mainPanel,"Stock Level Report");
            }
        });
    }

    public void printPanel(JPanel panel, String name) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setJobName(name);
        printerJob.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if(pageIndex > 0){
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D graphics2D = (Graphics2D)graphics;
                graphics2D.translate(pageFormat.getImageableX()*2,(pageFormat.getImageableY()*2));
                graphics2D.scale(1,1);
                panel.paint(graphics2D);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean returningResult = printerJob.printDialog();
        if(returningResult){
            try {
                // here

                dateFrom.setBorder(BorderFactory.createLineBorder(Color.black));
                dateTo.setBorder(BorderFactory.createLineBorder(Color.black));
                reportDate.setBorder(BorderFactory.createLineBorder(Color.black));
                stockLevelTitle.setForeground(Color.black);
                tableOfStockLabel.setForeground(Color.black);
                reportPeriodLabel.setForeground(Color.black);
                reportDateLabel.setForeground(Color.black);
                dateFromLabel.setForeground(Color.black);
                dateToLabel.setForeground(Color.black);
                mainPanel.setOpaque(false);
                printButton.setVisible(false);
                printerJob.print();

                printButton.setVisible(true);
                dateFromLabel.setForeground(Color.white);
                dateToLabel.setForeground(Color.white);
                stockLevelTitle.setForeground(Color.white);
                tableOfStockLabel.setForeground(Color.white);
                reportPeriodLabel.setForeground(Color.white);
                reportDateLabel.setForeground(Color.white);
            }catch (PrinterException printerException){
                JOptionPane.showMessageDialog(this, "Print Error: " + printerException.getMessage());
            }
        }
    }



    public JPanel getMainPanel() {
        return mainPanel;
    }


}
