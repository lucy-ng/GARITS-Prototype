package Forms.Users;

import Forms.Jobs.AddJob;
import Forms.Jobs.UpdateJob;
import Forms.Reception.CreateCustomerRecord;
import Forms.Reception.Invoice;
import Forms.Reception.MonthlyReport;
import Forms.StockControl.OrderParts;
import Forms.StockControl.SearchParts;
import Forms.StockControl.StockLevelReport;

import javax.swing.*;
import java.awt.event.*;

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

    public ForepersonPage(JFrame window) {
        window.setContentPane(mainPanel);
        window.setVisible(true);

        createCustomerRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateCustomerRecord createCustomerRecord = new CreateCustomerRecord();
                contentPanel.removeAll();
                contentPanel.add(createCustomerRecord.getMainPanel());
                contentPanel.revalidate();
            }
        });

        monthlyReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MonthlyReport monthlyReport = new MonthlyReport();
                contentPanel.removeAll();
                contentPanel.add(monthlyReport.getMainPanel());
                contentPanel.revalidate();
            }
        });

        addViewJobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddJob addJob = new AddJob();
                contentPanel.removeAll();
                contentPanel.add(addJob.getMainPanel());
                contentPanel.revalidate();
            }
        });

        updateJobButton.addActionListener(new ActionListener() {
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
