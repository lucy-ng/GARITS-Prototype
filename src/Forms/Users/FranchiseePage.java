package Forms.Users;

import Forms.Accounts.*;
import Forms.Jobs.*;
import Forms.Reception.*;
import Forms.StockControl.*;

import javax.swing.*;
import java.awt.event.*;

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

    public FranchiseePage(JFrame window) {
        window.setContentPane(mainPanel);
        window.setVisible(true);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showOptionDialog(new JFrame(), "Is the customer working for a company?", "",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, new Object[] {"Yes", "No"}, JOptionPane.YES_OPTION);
                if (choice == JOptionPane.YES_OPTION){

                }
                else if (choice == JOptionPane.NO_OPTION){
                    RegisterCustAccount registerCustAccount = new RegisterCustAccount();
                    contentPanel.removeAll();
                    contentPanel.add(registerCustAccount.getMainPanel());
                    contentPanel.revalidate();
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        addViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddJob addJob = new AddJob();
                contentPanel.removeAll();
                contentPanel.add(addJob.getMainPanel());
                contentPanel.revalidate();
            }
        });

        updatePickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateJob updateJob = new UpdateJob();
                contentPanel.removeAll();
                contentPanel.add(updateJob.getMainPanel());
                contentPanel.revalidate();
            }
        });

        jobSheetInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Invoice invoice = new Invoice();
                contentPanel.removeAll();
                contentPanel.add(invoice.getMainPanel());
                contentPanel.revalidate();
            }
        });

        orderPartsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderParts orderParts = new OrderParts();
                contentPanel.removeAll();
                contentPanel.add(orderParts.getMainPanel());
                contentPanel.revalidate();
            }
        });

        searchPartsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchParts searchParts = new SearchParts();
                contentPanel.removeAll();
                contentPanel.add(searchParts.getMainPanel());
                contentPanel.revalidate();
            }
        });

        stockReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StockLevelReport stockLevelReport = new StockLevelReport();
                contentPanel.removeAll();
                contentPanel.add(stockLevelReport.getMainPanel());
                contentPanel.revalidate();
            }
        });
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
