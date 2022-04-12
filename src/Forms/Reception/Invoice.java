package Forms.Reception;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

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
    private JTable labour;
    private JButton printButton;
    private JLabel itemsUsedLabel;
    private JLabel labourLabel;
    private JTextPane address;
    private JTextPane customerAddressTextPane;
    private JScrollPane workDescription;
    private JScrollPane usedItems;

    public Invoice() {

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
