package Forms.Users;

import javax.swing.*;

public class ForepersonPage {
    private JPanel mainPanel;
    private JButton createCustomerRecordButton;
    private JLabel customerRecordLabel;
    private JButton monthlyReportButton;
    private JButton addViewJobsButton;
    private JButton jobSheetInvoiceButton;
    private JLabel jobsLabel;
    private JButton updateJobButton;
    private JPanel contentPanel;
    private JLabel forepersonPageTitle;
    private JButton orderPartsButton;
    private JButton stockReportButton;
    private JLabel stockControlLabel;
    private JButton searchPartsButton;

    public ForepersonPage() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

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

    public JButton getJobSheetInvoiceButton() {
        return jobSheetInvoiceButton;
    }

    public JButton getUpdateJobButton() {
        return updateJobButton;
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
