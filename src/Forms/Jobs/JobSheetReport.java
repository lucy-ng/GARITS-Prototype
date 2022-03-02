package Forms.Jobs;

import javax.swing.*;

public class JobSheetReport {
    private JLabel jobSheetLabel;
    private JTextField vehicleRegNo;
    private JTextField dateBooked;
    private JTextField make;
    private JTextField model;
    private JTextField teleNo;
    private JTextField customerName;
    private JList workRequired;
    private JTextField estimatedTime;
    private JProgressBar progressBar1;
    private JTextField estimatedTime2;
    private JList workCarriedOut;
    private JProgressBar progressBar2;
    private JButton printButton;
    private JTable tblSpareParts;
    private JPanel mainPanel;
    private JLabel vehicleRegistrationNumberLabel;
    private JLabel dateBookedInLabel;
    private JLabel makeLabel;
    private JTextPane address;
    private JLabel signatureLabel;
    private JLabel sparePartsLabel;
    private JLabel estimatedTime2Label;
    private JLabel workCarriedOutLabel;
    private JLabel estimatedTimeLabel;
    private JLabel workRequiredLabel;
    private JLabel telephoneNumberLabel;
    private JLabel customerNameLabel;
    private JLabel modelLabel;

    public JobSheetReport() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
