package Forms.Users;

import javax.swing.*;

public class ReceptionistPage {
    private JPanel mainPanel;
    private JButton createCustomerRecordButton;
    private JLabel customerRecordLabel;
    private JButton monthlyReportButton;
    private JButton addViewJobsButton;
    private JButton jobSheetButton;
    private JLabel jobsLabel;
    private JButton invoiceButton;
    private JButton orderPartsButton;
    private JButton stockReportButton;
    private JLabel stockControlLabel;
    private JButton searchPartsButton;
    private JLabel receptionistPageTitle;
    private JPanel contentPanel;

    public ReceptionistPage() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
    public JPanel getContentPanel() { return contentPanel; }

    // Return buttons
    public JButton getCreateCustomerRecordButton() {
        return createCustomerRecordButton;
    }

    public JButton getMonthlyReportButton() {
        return monthlyReportButton;
    }

    public JButton getAddViewJobsButton() {
        return addViewJobsButton;
    }

    public JButton getJobSheetButton() {
        return jobSheetButton;
    }

    public JButton getInvoiceButton() {
        return invoiceButton;
    }

    public JButton getOrderPartsButton() {
        return orderPartsButton;
    }

    public JButton getStockReportButton() {
        return stockReportButton;
    }

    public JButton getSearchPartsButton() {
        return searchPartsButton;
    }
}
