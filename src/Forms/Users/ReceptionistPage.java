package Forms.Users;

import Forms.Jobs.*;
import Forms.StockControl.*;
import Forms.Reception.*;

import javax.swing.*;
import java.awt.event.*;

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

    public ReceptionistPage(JFrame window) {
        window.setContentPane(mainPanel);
        window.setVisible(true);

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

        invoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Invoice invoice = new Invoice();
                contentPanel.removeAll();
                contentPanel.add(invoice.getMainPanel());
                contentPanel.revalidate();
            }
        });

        jobSheetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JobSheetReport jobSheetReport = new JobSheetReport();
                contentPanel.removeAll();
                contentPanel.add(jobSheetReport.getMainPanel());
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
