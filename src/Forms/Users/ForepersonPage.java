package Forms.Users;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Forms.Accounts.DeleteCustAccount;
import Forms.Accounts.RegisterCustAccount;
import Forms.Accounts.UpdateCustAccount;
import Forms.Discounts.AddDiscountDetails;
import Forms.Discounts.UpdateDiscountDetails;
import Forms.Jobs.*;
import Forms.Payments.MakePayment;
import Forms.Reception.*;
import Forms.StockControl.*;
import Forms.Vehicles.CreateVehicleRecord;
import Forms.Vehicles.DeleteVehicleRecord;
import Forms.Vehicles.UpdateVehicleRecord;

public class ForepersonPage {
    private JPanel mainPanel;
    private JLabel forepersonPageTitle;
    private JLabel accountsLabel;
    private JPanel contentPanel;
    private JButton manageStockButton;
    private JButton managePaymentsButton;
    private JLabel jobsLabel;
    private JLabel stockControlLabel;
    private JLabel receptionLabel;
    private JButton customerAccountButton;
    private JButton vehicleRecordButton;
    private JButton discountDetailsButton;
    private JButton manageJobsButton;
    private JButton managePartsButton;
    private JButton jobSheetInvoiceButton;
    private JButton manageBookingsButton;
    private JPanel customerVehiclePanel;
    private JPanel receptionPanel;
    private JPanel jobsPanel;
    private JPanel stockControlPanel;

    public ForepersonPage(JFrame window) {
        window.setContentPane(mainPanel);
        window.setVisible(true);

        // Low stock alert
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("SELECT name, lowThreshold, quantity FROM SpareParts")) {
                ResultSet rs = statement.executeQuery();

                while (rs.next()){
                    String nameText = rs.getString("name");
                    int lowThresholdText = rs.getInt("lowThreshold");
                    int quantityText = rs.getInt("quantity");

                    if (quantityText < lowThresholdText) {
                        JOptionPane.showMessageDialog(null, nameText + " is low in stock!", "Low Stock Alert", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            connection.setAutoCommit(true);
            connection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Late payment alert
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("SELECT UserAccounts.username, Booking.servicePaid, Booking.motPaid FROM UserAccounts INNER JOIN Booking INNER JOIN CustomerAccount ON UserAccounts.AccountID = CustomerAccount.AccountID")) {
                ResultSet rs = statement.executeQuery();

                while (rs.next()){
                    String usernameText = rs.getString("username");
                    int servicePaid = rs.getInt("servicePaid");
                    int motPaid = rs.getInt("motPaid");

                    if (servicePaid == 0 && motPaid == 0) {
                        JOptionPane.showMessageDialog(null, usernameText + " has not paid for MOT!", "Late Payment Alert", JOptionPane.WARNING_MESSAGE);
                        JOptionPane.showMessageDialog(null, usernameText + " has not paid for service!", "Late Payment Alert", JOptionPane.WARNING_MESSAGE);
                    }
                    else if (motPaid == 1 && servicePaid == 0) {
                        JOptionPane.showMessageDialog(null, usernameText + " has not paid for service!", "Late Payment Alert", JOptionPane.WARNING_MESSAGE);
                    }
                    else if (motPaid == 0 && servicePaid == 1) {
                        JOptionPane.showMessageDialog(null, usernameText + " has not paid for MOT!", "Late Payment Alert", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            connection.setAutoCommit(true);
            connection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        customerAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] buttons = new String[] {"Add", "Update", "Delete"};
                int result = JOptionPane.showOptionDialog(null, "Choose options below:","Customer Account", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
                if (result == 0) {
                    RegisterCustAccount registerCustAccount = new RegisterCustAccount();
                    contentPanel.removeAll();
                    contentPanel.add(registerCustAccount.getMainPanel());
                    contentPanel.revalidate();
                }
                else if (result == 1) {
                    UpdateCustAccount updateCustAccount = new UpdateCustAccount();
                    contentPanel.removeAll();
                    contentPanel.add(updateCustAccount.getMainPanel());
                    contentPanel.revalidate();
                }
                else if (result == 2) {
                    DeleteCustAccount deleteCustAccount = new DeleteCustAccount();
                    contentPanel.removeAll();
                    contentPanel.add(deleteCustAccount.getMainPanel());
                    contentPanel.revalidate();
                }
            }
        });

        vehicleRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] buttons = new String[] {"Add", "Update", "Delete"};
                int result = JOptionPane.showOptionDialog(null, "Choose options below:","Vehicle Record", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
                if (result == 0) {
                    CreateVehicleRecord createVehicleRecord = new CreateVehicleRecord();
                    contentPanel.removeAll();
                    contentPanel.add(createVehicleRecord.getMainPanel());
                    contentPanel.revalidate();
                }
                else if (result == 1) {
                    UpdateVehicleRecord updateVehicleRecord = new UpdateVehicleRecord();
                    contentPanel.removeAll();
                    contentPanel.add(updateVehicleRecord.getMainPanel());
                    contentPanel.revalidate();
                }
                else if (result == 2) {
                    DeleteVehicleRecord deleteVehicleRecord = new DeleteVehicleRecord();
                    contentPanel.removeAll();
                    contentPanel.add(deleteVehicleRecord.getMainPanel());
                    contentPanel.revalidate();
                }
            }
        });

        manageJobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] buttons = new String[] {"Add", "Update", "View", "Use Parts"};
                int result = JOptionPane.showOptionDialog(null, "Choose options below:","Manage Jobs", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
                if (result == 0) {
                    AddJob addJob = new AddJob();
                    contentPanel.removeAll();
                    contentPanel.add(addJob.getMainPanel());
                    contentPanel.revalidate();
                }
                else if (result == 1) {
                    UpdateJob updateJob = new UpdateJob();
                    contentPanel.removeAll();
                    contentPanel.add(updateJob.getMainPanel());
                    contentPanel.revalidate();
                }
                else if (result == 2) {
                    ViewJobs viewJobs = new ViewJobs();
                    contentPanel.removeAll();
                    contentPanel.add(viewJobs.getMainPanel());
                    contentPanel.revalidate();
                }
                else if (result == 3) {
                    UseParts useParts = new UseParts();
                    contentPanel.removeAll();
                    contentPanel.add(useParts.getMainPanel());
                    contentPanel.revalidate();
                }
            }
        });

        jobSheetInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] buttons = new String[] {"Job Sheet", "Invoice"};
                int result = JOptionPane.showOptionDialog(null, "Choose options below:","Job Sheet / Invoice", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
                if (result == 0) {
                    String regNoMessage = "Enter registration number:";
                    JTextField regNoText = new JTextField();
                    int regNoResult = JOptionPane.showOptionDialog(null, new Object[] {regNoMessage, regNoText}, "Registration Number", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (regNoResult == JOptionPane.OK_OPTION) {
                        String custNameMessage = "Enter customer username:";
                        JTextField custNameText = new JTextField();
                        int custNameResult = JOptionPane.showOptionDialog(null, new Object[] {custNameMessage, custNameText}, "Customer Username", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                        if (custNameResult == JOptionPane.OK_OPTION) {
                            JobSheetReport jobSheetReport = new JobSheetReport(regNoText.getText(), custNameText.getText());
                            contentPanel.removeAll();
                            contentPanel.add(jobSheetReport.getMainPanel());
                            contentPanel.revalidate();
                        }
                    }
                }
                else if (result == 1) {
                    Invoice invoice = new Invoice();
                    contentPanel.removeAll();
                    contentPanel.add(invoice.getMainPanel());
                    contentPanel.revalidate();
                }
            }
        });

        managePartsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] buttons = new String[] {"Add", "Update", "Search", "Order", "Sell", "Record Delivery"};
                int result = JOptionPane.showOptionDialog(null, "Choose options below:","Manage Parts", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
                if (result == 0) {
                    AddParts addParts = new AddParts();
                    contentPanel.removeAll();
                    contentPanel.add(addParts.getMainPanel());
                    contentPanel.revalidate();
                }
                else if (result == 1) {
                    UpdateParts updateParts = new UpdateParts();
                    contentPanel.removeAll();
                    contentPanel.add(updateParts.getMainPanel());
                    contentPanel.revalidate();
                }
                else if (result == 2) {
                    SearchParts searchParts = new SearchParts();
                    contentPanel.removeAll();
                    contentPanel.add(searchParts.getMainPanel());
                    contentPanel.revalidate();
                }
                else if (result == 3) {
                    OrderParts orderParts = new OrderParts();
                    contentPanel.removeAll();
                    contentPanel.add(orderParts.getMainPanel());
                    contentPanel.revalidate();
                }
                else if (result == 4) {
                    SellParts sellParts = new SellParts();
                    contentPanel.removeAll();
                    contentPanel.add(sellParts.getMainPanel());
                    contentPanel.revalidate();
                }
                else if (result == 5) {
                    DeliverParts deliverParts = new DeliverParts();
                    contentPanel.removeAll();
                    contentPanel.add(deliverParts.getMainPanel());
                    contentPanel.revalidate();
                }
            }
        });

        manageStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] buttons = new String[] {"Manage Stock", "Stock Level Report"};
                int result = JOptionPane.showOptionDialog(null, "Choose options below:","Manage Stock", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
                if (result == 0) {
                    ManageStock manageStock = new ManageStock();
                    contentPanel.removeAll();
                    contentPanel.add(manageStock.getMainPanel());
                    contentPanel.revalidate();
                }
                else if (result == 1) {
                    String[] options = new String[] {"Automatic", "On Demand"};
                    int option = JOptionPane.showOptionDialog(null, "Choose options below:","Automatic or On Demand", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (option == 0) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar calendar = Calendar.getInstance();
                        Date date = calendar.getTime();
                        String dateFrom = simpleDateFormat.format(date);

                        Calendar calendarLater = Calendar.getInstance();
                        calendarLater.add(Calendar.MONTH, 1);
                        Date dateLater = calendarLater.getTime();
                        String dateTo = simpleDateFormat.format(dateLater);
                        StockLevelReport stockLevelReport = new StockLevelReport(dateFrom, dateTo);
                        contentPanel.removeAll();
                        contentPanel.add(stockLevelReport.getMainPanel());
                        contentPanel.revalidate();
                    }
                    else if (option == 1) {
                        String dateFrom = JOptionPane.showInputDialog("Enter Date From (YYYY-MM-DD):");
                        String dateTo = JOptionPane.showInputDialog("Enter Date To (YYYY-MM-DD):");

                        StockLevelReport stockLevelReport = new StockLevelReport(dateFrom, dateTo);
                        contentPanel.removeAll();
                        contentPanel.add(stockLevelReport.getMainPanel());
                        contentPanel.revalidate();
                    }
                }
            }
        });

        managePaymentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] buttons = new String[] {"Record", "View"};
                int result = JOptionPane.showOptionDialog(null, "Choose options below:","Manage Payments", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
                if (result == 0) {
                    MakePayment makePayment = new MakePayment();
                    contentPanel.removeAll();
                    contentPanel.add(makePayment.getMainPanel());
                    contentPanel.revalidate();
                }
                else if (result == 1) {

                }
            }
        });

        manageBookingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] buttons = new String[] {"Generate MOT Reminder","MOT Booking", "Service Booking", "MOT and Service Booking", "View Bookings"};
                int result = JOptionPane.showOptionDialog(null, "Choose options below:","Manage Bookings", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
                if (result == 0) {
                    MOT_Reminder mot_reminder = new MOT_Reminder();
                    contentPanel.removeAll();
                    contentPanel.add(mot_reminder.getMainPanel());
                    contentPanel.revalidate();
                }
                else if (result == 1) {
                    MOTBooking motBooking = new MOTBooking();
                    contentPanel.removeAll();
                    contentPanel.add(motBooking.getMainPanel());
                    contentPanel.revalidate();
                }
                else if (result == 2) {
                    ServiceBooking serviceBooking = new ServiceBooking();
                    contentPanel.removeAll();
                    contentPanel.add(serviceBooking.getMainPanel());
                    contentPanel.revalidate();
                } else if (result == 3) {
                    MOTServiceBooking motServiceBooking = new MOTServiceBooking();
                    contentPanel.removeAll();
                    contentPanel.add(motServiceBooking.getMainPanel());
                    contentPanel.revalidate();
                }
            }
        });
    }
}
