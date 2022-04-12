package Forms.Reception;

import javax.swing.*;

public class MonthlyReport {
    private JLabel monthlyReportTitle;
    private JTextField fromDate;
    private JTextField toDate;
    private JTextField vehiclesMOT;
    private JTextField vehiclesService;
    private JButton printButton;
    private JPanel mainPanel;
    private JLabel fromDateLabel;
    private JLabel toDateLabel;
    private JLabel numberOfVehiclesBookedLabel;
    private JLabel MOTLabel;
    private JLabel serviceLabel;
    private JScrollPane motScrollPane;
    private JScrollPane serviceScrollPane;
    private JTable motTable;
    private JTable serviceTable;

    public MonthlyReport() {
        motScrollPane.setSize(500,500);
        serviceScrollPane.setSize(500,500);


    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
