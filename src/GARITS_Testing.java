import Forms.*;
import Forms.Accounts.*;
import Forms.Payments.MakePayment;
import Forms.StockControl.*;
import Forms.Users.*;
import Forms.Reception.*;
import Forms.Jobs.*;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.*;

public class GARITS_Testing {
    final public JFrame mainWindow = new JFrame("GARITS Testing");

    public GARITS_Testing() {
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setSize(1250,650);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Testing

        /*
        AdminPage adminPage = new AdminPage();
        mainWindow.setContentPane(adminPage.getMainPanel());
        mainWindow.setVisible(true);

         */

        /*
        ForepersonPage forepersonPage = new ForepersonPage();
        mainWindow.setContentPane(forepersonPage.getMainPanel());
        mainWindow.setVisible(true);

        forepersonPage.getCreateCustomerRecordButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateCustomerRecord createCustomerRecord = new CreateCustomerRecord();
                forepersonPage.getContentPanel().removeAll();
                forepersonPage.getContentPanel().add(createCustomerRecord.getMainPanel());
                forepersonPage.getContentPanel().revalidate();
            }
        });

        forepersonPage.getMonthlyReportButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MonthlyReport monthlyReport = new MonthlyReport();
                forepersonPage.getContentPanel().removeAll();
                forepersonPage.getContentPanel().add(monthlyReport.getMainPanel());
                forepersonPage.getContentPanel().revalidate();
            }
        });

        forepersonPage.getAddViewJobsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddJob addJob = new AddJob();
                forepersonPage.getContentPanel().removeAll();
                forepersonPage.getContentPanel().add(addJob.getMainPanel());
                forepersonPage.getContentPanel().revalidate();
            }
        });

        forepersonPage.getUpdateJobButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateJob updateJob = new UpdateJob();
                forepersonPage.getContentPanel().removeAll();
                forepersonPage.getContentPanel().add(updateJob.getMainPanel());
                forepersonPage.getContentPanel().revalidate();
            }
        });

        forepersonPage.getJobSheetInvoiceButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Invoice invoice = new Invoice();
                forepersonPage.getContentPanel().removeAll();
                forepersonPage.getContentPanel().add(invoice.getMainPanel());
                forepersonPage.getContentPanel().revalidate();
            }
        });

        forepersonPage.getOrderPartsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderParts orderParts = new OrderParts();
                forepersonPage.getContentPanel().removeAll();
                forepersonPage.getContentPanel().add(orderParts.getMainPanel());
                forepersonPage.getContentPanel().revalidate();
            }
        });

        forepersonPage.getSearchPartsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchParts searchParts = new SearchParts();
                forepersonPage.getContentPanel().removeAll();
                forepersonPage.getContentPanel().add(searchParts.getMainPanel());
                forepersonPage.getContentPanel().revalidate();
            }
        });

        forepersonPage.getStockReportButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StockLevelReport stockLevelReport = new StockLevelReport();
                forepersonPage.getContentPanel().removeAll();
                forepersonPage.getContentPanel().add(stockLevelReport.getMainPanel());
                forepersonPage.getContentPanel().revalidate();
            }
        });


         */

        /*
        FranchiseePage franchiseePage = new FranchiseePage();
        mainWindow.setContentPane(franchiseePage.getMainPanel());
        mainWindow.setVisible(true);

        franchiseePage.getCreateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterCustAccount registerCustAccount = new RegisterCustAccount();
                franchiseePage.getContentPanel().removeAll();
                franchiseePage.getContentPanel().add(registerCustAccount.getMainPanel());
                franchiseePage.getContentPanel().revalidate();
            }
        });

        franchiseePage.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        franchiseePage.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        franchiseePage.getAddViewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddJob addJob = new AddJob();
                franchiseePage.getContentPanel().removeAll();
                franchiseePage.getContentPanel().add(addJob.getMainPanel());
                franchiseePage.getContentPanel().revalidate();
            }
        });

        franchiseePage.getUpdatePickButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateJob updateJob = new UpdateJob();
                franchiseePage.getContentPanel().removeAll();
                franchiseePage.getContentPanel().add(updateJob.getMainPanel());
                franchiseePage.getContentPanel().revalidate();
            }
        });

        franchiseePage.getJobSheetInvoiceButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Invoice invoice = new Invoice();
                franchiseePage.getContentPanel().removeAll();
                franchiseePage.getContentPanel().add(invoice.getMainPanel());
                franchiseePage.getContentPanel().revalidate();
            }
        });

        franchiseePage.getOrderPartsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderParts orderParts = new OrderParts();
                franchiseePage.getContentPanel().removeAll();
                franchiseePage.getContentPanel().add(orderParts.getMainPanel());
                franchiseePage.getContentPanel().revalidate();
            }
        });

        franchiseePage.getSearchPartsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchParts searchParts = new SearchParts();
                franchiseePage.getContentPanel().removeAll();
                franchiseePage.getContentPanel().add(searchParts.getMainPanel());
                franchiseePage.getContentPanel().revalidate();
            }
        });

        franchiseePage.getStockReportButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StockLevelReport stockLevelReport = new StockLevelReport();
                franchiseePage.getContentPanel().removeAll();
                franchiseePage.getContentPanel().add(stockLevelReport.getMainPanel());
                franchiseePage.getContentPanel().revalidate();
            }
        });

         */

        /*
        MechanicPage mechanicPage = new MechanicPage();
        mainWindow.setContentPane(mechanicPage.getMainPanel());
        mainWindow.setVisible(true);

        mechanicPage.getViewJobsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewJobs viewJobs = new ViewJobs();
                mechanicPage.getContentPanel().removeAll();
                mechanicPage.getContentPanel().add(viewJobs.getMainPanel());
                mechanicPage.getContentPanel().revalidate();
            }
        });

        mechanicPage.getUpdateJobButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateJob updateJob = new UpdateJob();
                mechanicPage.getContentPanel().removeAll();
                mechanicPage.getContentPanel().add(updateJob.getMainPanel());
                mechanicPage.getContentPanel().revalidate();
            }
        });

        mechanicPage.getPickJobButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        mechanicPage.getSearchPartsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchParts searchParts = new SearchParts();
                mechanicPage.getContentPanel().removeAll();
                mechanicPage.getContentPanel().add(searchParts.getMainPanel());
                mechanicPage.getContentPanel().revalidate();
            }
        });

         */

        ReceptionistPage receptionistPage = new ReceptionistPage();
        mainWindow.setContentPane(receptionistPage.getMainPanel());
        mainWindow.setVisible(true);

        receptionistPage.getCreateCustomerRecordButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateCustomerRecord createCustomerRecord = new CreateCustomerRecord();
                receptionistPage.getContentPanel().removeAll();
                receptionistPage.getContentPanel().add(createCustomerRecord.getMainPanel());
                receptionistPage.getContentPanel().revalidate();
            }
        });

        receptionistPage.getMonthlyReportButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MonthlyReport monthlyReport = new MonthlyReport();
                receptionistPage.getContentPanel().removeAll();
                receptionistPage.getContentPanel().add(monthlyReport.getMainPanel());
                receptionistPage.getContentPanel().revalidate();
            }
        });

        receptionistPage.getAddViewJobsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddJob addJob = new AddJob();
                receptionistPage.getContentPanel().removeAll();
                receptionistPage.getContentPanel().add(addJob.getMainPanel());
                receptionistPage.getContentPanel().revalidate();
            }
        });

        receptionistPage.getInvoiceButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Invoice invoice = new Invoice();
                receptionistPage.getContentPanel().removeAll();
                receptionistPage.getContentPanel().add(invoice.getMainPanel());
                receptionistPage.getContentPanel().revalidate();
            }
        });

        receptionistPage.getJobSheetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JobSheetReport jobSheetReport = new JobSheetReport();
                receptionistPage.getContentPanel().removeAll();
                receptionistPage.getContentPanel().add(jobSheetReport.getMainPanel());
                receptionistPage.getContentPanel().revalidate();
            }
        });

        receptionistPage.getOrderPartsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderParts orderParts = new OrderParts();
                receptionistPage.getContentPanel().removeAll();
                receptionistPage.getContentPanel().add(orderParts.getMainPanel());
                receptionistPage.getContentPanel().revalidate();
            }
        });

        receptionistPage.getSearchPartsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchParts searchParts = new SearchParts();
                receptionistPage.getContentPanel().removeAll();
                receptionistPage.getContentPanel().add(searchParts.getMainPanel());
                receptionistPage.getContentPanel().revalidate();
            }
        });

        receptionistPage.getStockReportButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StockLevelReport stockLevelReport = new StockLevelReport();
                receptionistPage.getContentPanel().removeAll();
                receptionistPage.getContentPanel().add(stockLevelReport.getMainPanel());
                receptionistPage.getContentPanel().revalidate();
            }
        });

        /*
        CreateCustomerRecord createCustomerRecord = new CreateCustomerRecord();
        mainWindow.setContentPane(createCustomerRecord.getMainPanel());
        mainWindow.setSize(500,500);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        AddJob addJob = new AddJob();
        mainWindow.setContentPane(addJob.getMainPanel());
        mainWindow.setSize(500,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        BookingService bookingService = new BookingService();
        mainWindow.setContentPane(bookingService.getMainPanel());
        mainWindow.setSize(500,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        RegisterCustAccount registerCustAccount = new RegisterCustAccount();
        mainWindow.setContentPane(registerCustAccount.getMainPanel());
        mainWindow.setSize(500,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        OrderParts orderParts = new OrderParts();
        mainWindow.setContentPane(orderParts.getMainPanel());
        mainWindow.setSize(500,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        SearchParts searchParts = new SearchParts();
        mainWindow.setContentPane(searchParts.getMainPanel());
        mainWindow.setSize(500,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        ViewJobs viewJobs = new ViewJobs();
        mainWindow.setContentPane(viewJobs.getMainPanel());
        mainWindow.setSize(500,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        UpdateJob updateJob = new UpdateJob();
        mainWindow.setContentPane(updateJob.getMainPanel());
        mainWindow.setSize(500,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        MonthlyReport monthlyReport = new MonthlyReport();
        mainWindow.setContentPane(monthlyReport.getMainPanel());
        mainWindow.setSize(500,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        MakePayment makePayment = new MakePayment();
        mainWindow.setContentPane(makePayment.getMainPanel());
        mainWindow.setSize(500,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        MOT_Reminder mot_reminder = new MOT_Reminder();
        mainWindow.setContentPane(mot_reminder.getMainPanel());
        mainWindow.setSize(500,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        JobSheetReport jobSheetReport = new JobSheetReport();
        mainWindow.setContentPane(jobSheetReport.getMainPanel());
        mainWindow.setSize(1100,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        Invoice invoice = new Invoice();
        mainWindow.setContentPane(invoice.getMainPanel());
        mainWindow.setSize(500,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        StockLevel stockLevel = new StockLevel();
        mainWindow.setContentPane(stockLevel.getMainPanel());
        mainWindow.setSize(600,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        AddParts addParts = new AddParts();
        mainWindow.setContentPane(addParts.getMainPanel());
        mainWindow.setSize(500,500);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        RegisterAccount registerAccount = new RegisterAccount();
        mainWindow.setContentPane(registerAccount.getMainPanel());
        mainWindow.setSize(500,500);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        UpdateAccount updateAccount = new UpdateAccount();
        mainWindow.setContentPane(updateAccount.getMainPanel());
        mainWindow.setSize(500,500);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */
    }

    // Testing the GARITS system
    public static void main (String[] args){
        new GARITS_Testing();
    }
}
