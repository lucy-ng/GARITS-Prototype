package Forms.Reception;

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
import java.util.Vector;

public class Invoice {
    private JLabel invoiceLabel;
    private JPanel mainPanel;
    private JLabel customerNameLabel;
    private JTextField customerName;
    private JLabel vehicleRegistrationNumberLabel;
    private JTextField vehicleRegNo;
    private JLabel makeLabel;
    private JTextField make;
    private JLabel modelLabel;
    private JTextField model;
    private JLabel descriptionOfWorkLabel;
    private JButton printButton;
    private JLabel itemsUsedLabel;
    private JTextPane address;
    private JTextPane customerAddressTextPane;
    private JScrollPane partsScrollPane;
    private JScrollPane descriptionScrollPane;
    private JLabel totalLabourLabel;
    private JTextField labour;
    private JLabel labourRateLabel;
    private JTextField labourRate;
    private JTable partsTable;
    private JTable descriptionTable;

    public Invoice(String regNo, String customer) {
        customerName.setText(customer);
        vehicleRegNo.setText(regNo);

        Vector headers = new Vector();
        headers.addElement("Description");
        headers.addElement("Actual Time");
        headers.addElement("Labour Rate");
        Vector rows = new Vector();
        descriptionTable = new JTable(rows, headers);
        DefaultTableModel descriptionTableModel = (DefaultTableModel) descriptionTable.getModel();
        descriptionScrollPane.setViewportView(descriptionTable);
        descriptionTable.setVisible(true);

        Vector partHeaders = new Vector();
        partHeaders.addElement("Part Name");
        partHeaders.addElement("Code");
        partHeaders.addElement("Price");
        partHeaders.addElement("Amount Used");
        Vector partRows = new Vector();
        partsTable = new JTable(partRows, partHeaders);
        DefaultTableModel partsTableModel = (DefaultTableModel) partsTable.getModel();
        partsScrollPane.setViewportView(partsTable);
        partsTable.setVisible(true);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement selectStmt = connection.prepareStatement("SELECT address FROM CustomerAccount WHERE AccountID = (SELECT AccountID FROM UserAccounts WHERE username = ?)");
            selectStmt.setString(1, customer);
            ResultSet selectResult = selectStmt.executeQuery();

            while (selectResult.next()) {
                String addressText = selectResult.getString("address");
                customerAddressTextPane.setText(addressText);
            }

            PreparedStatement statement = connection.prepareStatement("SELECT make, model FROM Vehicles WHERE CustomerAccountID = (SELECT CustomerAccountID FROM CustomerAccount WHERE AccountID = (SELECT AccountID FROM UserAccounts WHERE username = ?))");
            statement.setString(1, customer);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                String makeText = resultSet.getString("make");
                String modelText = resultSet.getString("model");
                make.setText(makeText);
                model.setText(modelText);

                PreparedStatement s = connection.prepareStatement("SELECT SpareParts.name, SpareParts.code, SpareParts.price, SparePartsUse.amountUsed FROM SpareParts INNER JOIN SparePartsUse ON SpareParts.partID = SparePartsUse.partID WHERE registrationNo = ?");
                s.setString(1, regNo);
                ResultSet result = s.executeQuery();

                while (result.next()) {
                    String partNameText = result.getString("name");
                    String codeText = result.getString("code");
                    BigDecimal priceText = result.getBigDecimal("price");
                    int amountUsedText = result.getInt("amountUsed");

                    Object[] row = new Object[4];
                    for (int i = 0; i < 4; i++) {
                        row[0] = partNameText;
                        row[1] = codeText;
                        row[2] = priceText;
                        row[3] = amountUsedText;
                    }
                    partsTableModel.addRow(row);
                }
            }

            PreparedStatement stmt = connection.prepareStatement("SELECT Job.description, Job.actualTime, EmployeeAccount.labourRate FROM Job INNER JOIN EmployeeAccount ON Job.AccountID = EmployeeAccount.AccountID WHERE Job.registrationNo = ?");
            stmt.setString(1, regNo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String descriptionText = rs.getString("description");
                int actualTimeText = rs.getInt("actualTime");
                BigDecimal labourRateText = rs.getBigDecimal("labourRate");

                labourRate.setText(String.valueOf(labourRateText));

                Object[] row = new Object[3];
                for (int i = 0; i < 3; i++) {
                    row[0] = descriptionText;
                    row[1] = actualTimeText;
                    row[2] = labourRateText;
                }
                descriptionTableModel.addRow(row);

                PreparedStatement sumTime = connection.prepareStatement("SELECT SUM(actualTime) FROM Job INNER JOIN EmployeeAccount ON Job.AccountID = EmployeeAccount.AccountID WHERE Job.registrationNo = ?");
                sumTime.setString(1, regNo);
                ResultSet resultTime = sumTime.executeQuery();
                while (resultTime.next()) {
                    BigDecimal time = BigDecimal.valueOf(resultTime.getInt(1));
                    BigDecimal sum;
                    sum = time.multiply(labourRateText);
                    labour.setText(String.valueOf(sum));
                }
            }

            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printPanel(mainPanel,"Invoice");
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
                mainPanel.setSize(700,700);
                customerNameLabel.setForeground(Color.black);
                vehicleRegistrationNumberLabel.setForeground(Color.black);
                makeLabel.setForeground(Color.black);
                modelLabel.setForeground(Color.black);
                descriptionOfWorkLabel.setForeground(Color.black);
                itemsUsedLabel.setForeground(Color.black);
                invoiceLabel.setForeground(Color.black);
                mainPanel.setOpaque(false);
                printButton.setVisible(false);
                printerJob.print();
                printButton.setVisible(true);
                customerNameLabel.setForeground(Color.white);
                vehicleRegistrationNumberLabel.setForeground(Color.white);
                makeLabel.setForeground(Color.white);
                modelLabel.setForeground(Color.white);
                descriptionOfWorkLabel.setForeground(Color.white);
                itemsUsedLabel.setForeground(Color.white);
                invoiceLabel.setForeground(Color.white);
            }catch (PrinterException printerException){
                JOptionPane.showMessageDialog(null, "Print Error: " + printerException.getMessage());
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
