package Forms.Users;

import javax.swing.*;

public class FranchiseePage {
    private JPanel mainPanel;
    private JButton createButton;
    private JLabel customerRecordLabel;
    private JButton deleteButton;
    private JButton addViewButton;
    private JButton jobSheetInvoiceButton;
    private JLabel jobsLabel;
    private JButton updatePickButton;
    private JButton updateButton;
    private JButton orderPartsButton;
    private JButton stockReportButton;
    private JLabel stockControlLabel;
    private JButton searchPartsButton;
    private JLabel franchiseePageTitle;
    private JPanel contentPanel;

    public FranchiseePage() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
    public JPanel getContentPanel() { return contentPanel; }

    // Return buttons
    public JButton getCreateButton() {
        return createButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getAddViewButton() {
        return addViewButton;
    }

    public JButton getJobSheetInvoiceButton() {
        return jobSheetInvoiceButton;
    }

    public JButton getUpdatePickButton() {
        return updatePickButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
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
