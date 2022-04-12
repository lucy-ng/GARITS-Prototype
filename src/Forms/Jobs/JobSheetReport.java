package Forms.Jobs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.LabelView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.*;
import java.util.Vector;

public class JobSheetReport {
    private JLabel jobSheetLabel;
    private JPanel mainPanel;
    private JLabel MOTDateBookedInLabel;
    private JTextPane address;
    private JTextField motDate;
    private JLabel serviceDateBookedInLabel;
    private JTextField serviceDate;
    private JLabel vehicleRegistrationNumberLabel;
    private JTextField vehicleRegNo;
    private JLabel makeLabel;
    private JTextField make;
    private JLabel modelLabel;
    private JTextField model;
    private JLabel customerNameLabel;
    private JTextField custName;
    private JLabel telephoneNumberLabel;
    private JTextField teleNo;
    private JLabel workRequiredLabel;
    private JScrollPane workRequiredScollPane;
    private JLabel sparePartsLabel;
    private JScrollPane partsScrollPane;
    private JLabel estimatedTimeLabel;
    private JTextField estimatedTime;
    private JLabel actualTimeLabel;
    private JTextField actualTime;
    private JLabel signatureLabel;
    private JButton printButton;
    private JTable jobsTable;
    private JTable partsTable;

    public JobSheetReport(String regNo, String custName) {
        this.custName.setText(custName);

        Vector jobHeaders = new Vector();
        jobHeaders.addElement("Description");
        jobHeaders.addElement("Estimated Time");
        jobHeaders.addElement("Job Status");
        jobHeaders.addElement("Actual Time");
        Vector jobRows = new Vector();
        jobsTable = new JTable(jobRows, jobHeaders);
        DefaultTableModel jobsTableModel = (DefaultTableModel) jobsTable.getModel();
        workRequiredScollPane.setViewportView(jobsTable);
        jobsTable.setVisible(true);

        Vector partHeaders = new Vector();
        partHeaders.addElement("Part Name");
        partHeaders.addElement("Code");
        partHeaders.addElement("Amount Used");
        Vector partRows = new Vector();
        partsTable = new JTable(partRows, partHeaders);
        DefaultTableModel partsTableModel = (DefaultTableModel) partsTable.getModel();
        partsScrollPane.setViewportView(partsTable);
        partsTable.setVisible(true);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");
            PreparedStatement statement = connection.prepareStatement("SELECT registrationNo, make, model FROM Vehicles WHERE CustomerAccountID = (SELECT CustomerAccountID FROM CustomerAccount WHERE AccountID = (SELECT AccountID FROM UserAccounts WHERE username = ?))");
            statement.setString(1, custName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                String registrationNo = resultSet.getString("registrationNo");
                String makeText = resultSet.getString("make");
                String modelText = resultSet.getString("model");
                vehicleRegNo.setText(registrationNo);
                make.setText(makeText);
                model.setText(modelText);

                PreparedStatement stmt = connection.prepareStatement("SELECT phoneNo FROM UserAccounts WHERE username = ?");
                stmt.setString(1, custName);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String phoneNoText = rs.getString("phoneNo");
                    teleNo.setText(phoneNoText);

                    PreparedStatement selectStmt = connection.prepareStatement("SELECT serviceDate, motDate FROM Booking WHERE registrationNo = ?");
                    selectStmt.setString(1, regNo);
                    ResultSet r = selectStmt.executeQuery();

                    while (r.next()) {
                        String serviceDateText = r.getString("serviceDate");
                        String motDateText = r.getString("motDate");
                        serviceDate.setText(serviceDateText);
                        motDate.setText(motDateText);

                        PreparedStatement preparedStatement = connection.prepareStatement("SELECT description, estimatedTime, jobStatus, actualTime FROM Job WHERE registrationNo = ?");
                        preparedStatement.setString(1, regNo);
                        ResultSet rsrs = preparedStatement.executeQuery();

                        while (rsrs.next()) {
                            String descriptionDone = rsrs.getString("description");
                            String estimatedTimeText = rsrs.getString("estimatedTime");
                            String jobStatusText = rsrs.getString("jobStatus");
                            String actualTimeText = rsrs.getString("actualTime");

                            Object[] jobRow = new Object[4];
                            for (int i = 0; i < 4; i++) {
                                jobRow[0] = descriptionDone;
                                jobRow[1] = estimatedTimeText;
                                jobRow[2] = jobStatusText;
                                jobRow[3] = actualTimeText;
                            }
                            jobsTableModel.addRow(jobRow);

                            PreparedStatement s = connection.prepareStatement("SELECT SpareParts.name, SpareParts.code, SparePartsUse.amountUsed FROM SpareParts INNER JOIN SparePartsUse ON SpareParts.partID = SparePartsUse.partID WHERE registrationNo = ?");
                            s.setString(1, regNo);
                            ResultSet result = s.executeQuery();

                            while (result.next()) {
                                String partNameText = result.getString("name");
                                String codeText = result.getString("code");
                                int amountUsedText = result.getInt("amountUsed");

                                Object[] row = new Object[3];
                                for (int i = 0; i < 3; i++) {
                                    row[0] = partNameText;
                                    row[1] = codeText;
                                    row[2] = amountUsedText;
                                }
                                partsTableModel.addRow(row);
                            }
                        }
                    }
                }
            }

            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printPanel(mainPanel,"Job Sheet Report");
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
                graphics2D.scale(0.7,1.0);
                panel.paint(graphics2D);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean returningResult = printerJob.printDialog();
        if(returningResult){
            try {
                // here
                jobSheetLabel.setForeground(Color.black);
                MOTDateBookedInLabel.setForeground(Color.black);
                serviceDateBookedInLabel.setForeground(Color.black);
                vehicleRegistrationNumberLabel.setForeground(Color.black);
                makeLabel.setForeground(Color.black);
                modelLabel.setForeground(Color.black);
                customerNameLabel.setForeground(Color.black);
                telephoneNumberLabel.setForeground(Color.black);
                workRequiredLabel.setForeground(Color.black);
                sparePartsLabel.setForeground(Color.black);
                estimatedTimeLabel.setForeground(Color.black);
                actualTimeLabel.setForeground(Color.black);
                signatureLabel.setForeground(Color.black);
                mainPanel.setOpaque(false);
                printButton.setVisible(false);
                printerJob.print();
                printButton.setVisible(true);
                MOTDateBookedInLabel.setForeground(Color.white);
                serviceDateBookedInLabel.setForeground(Color.white);
                vehicleRegistrationNumberLabel.setForeground(Color.white);
                makeLabel.setForeground(Color.white);
                modelLabel.setForeground(Color.white);
                customerNameLabel.setForeground(Color.white);
                telephoneNumberLabel.setForeground(Color.white);
                workRequiredLabel.setForeground(Color.white);
                sparePartsLabel.setForeground(Color.white);
                estimatedTimeLabel.setForeground(Color.white);
                actualTimeLabel.setForeground(Color.white);
                signatureLabel.setForeground(Color.white);
                jobSheetLabel.setForeground(Color.white);
            }catch (PrinterException printerException){
                JOptionPane.showMessageDialog(null, "Print Error: " + printerException.getMessage());
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
