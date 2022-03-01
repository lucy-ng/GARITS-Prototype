package Forms.Reception;

import javax.swing.*;

public class MonthlyReport {
    private JLabel monthlyReportTitle;
    private JTextField fromDate;
    private JTextField toDate;
    private JButton searchButton;
    private JTextField vehiclesCasual;
    private JTable tblVehiclesCasual;
    private JTextField vehiclesAccount;
    private JTable tblVehiclesAccount;
    private JButton printButton;
    private JPanel mainPanel;
    private JLabel fromDateLabel;
    private JLabel toDateLabel;
    private JLabel numberOfVehiclesBookedLabel;
    private JLabel searchDatesLabel;
    private JLabel casualLabel;
    private JLabel accountHolderLabel;

    public MonthlyReport() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
