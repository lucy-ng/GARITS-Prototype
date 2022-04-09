package Forms.Jobs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class JobSheetReport {
    private JLabel jobSheetLabel;
    private JTextField vehicleRegNo;
    private JTextField make;
    private JTextField customerName;
    private JTextField estimatedTime;
    private JTable tblSpareParts;
    private JPanel mainPanel;
    private JLabel vehicleRegistrationNumberLabel;
    private JLabel makeLabel;
    private JLabel signatureLabel;
    private JLabel sparePartsLabel;
    private JLabel estimatedTimeLabel;
    private JLabel customerNameLabel;
    private JButton printButton;
    private JLabel workRequiredLabel;
    private JList workRequired;
    private JLabel workCarriedOutLabel;
    private JList workCarriedOut;
    private JLabel dateBookedInLabel;
    private JTextField dateBooked;
    private JLabel modelLabel;
    private JTextField model;
    private JLabel telephoneNumberLabel;
    private JTextField teleNo;
    private JTextPane address;
    private JTextField estimatedTime2;

    public JobSheetReport() {
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
                graphics2D.scale(0.95,1.6);
                panel.paint(graphics2D);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean returningResult = printerJob.printDialog();
        if(returningResult){
            try {
                // here
                printButton.setVisible(false);
                printerJob.print();
                printButton.setVisible(true);
            }catch (PrinterException printerException){
                JOptionPane.showMessageDialog(null, "Print Error: " + printerException.getMessage());
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
