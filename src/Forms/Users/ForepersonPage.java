package Forms.Users;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Forms.Accounts.DeleteCustAccount;
import Forms.Accounts.RegisterCustAccount;
import Forms.Accounts.UpdateCustAccount;
import Forms.Jobs.AddJob;
import Forms.Jobs.PickJob;
import Forms.Jobs.UpdateJob;
import Forms.StockControl.AddParts;
import Forms.StockControl.ManageStock;
import Forms.StockControl.SearchParts;
import Forms.StockControl.UpdateParts;
import Forms.Vehicles.CreateVehicleRecord;
import Forms.Vehicles.DeleteVehicleRecord;
import Forms.Vehicles.UpdateVehicleRecord;

public class ForepersonPage {
    private JPanel mainPanel;
    private JLabel forepersonPageTitle;
    private JButton addCustomerAccountButton;
    private JLabel accountsLabel;
    private JButton updateCustomerAccountButton;
    private JButton deleteCustomerAccountButton;
    private JButton addVehicleRecordButton;
    private JButton updateVehicleRecordButton;
    private JButton deleteVehicleRecordButton;-
    private JPanel contentPanel;
    private JButton viewJobsButton;
    private JButton allocateMechanicButton;
    private JButton sellPartsButton;
    private JButton manageStockButton;
    private JButton makePaymentButton;
    private JButton generateMOTReminderButton;
    private JButton orderPartsButton;
    private JButton addPartsButton;
    private JButton searchPartsButton;
    private JButton updatePartsButton;
    private JButton addJobButton;
    private JButton updateJobButton;
    private JButton pickJobButton;
    private JLabel jobsLabel;
    private JLabel stockControlLabel;
    private JButton jobSheetButton;
    private JLabel receptionLabel;
    private JButton invoiceButton;
    private JButton monthlyReportButton;
    private JButton stockLevelReportButton;

    public ForepersonPage(JFrame window) {
        window.setContentPane(mainPanel);
        window.setVisible(true);

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
                        JOptionPane.showMessageDialog(null, nameText + " is low in stock!");
                    }
                }
            }
            connection.setAutoCommit(true);
            connection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        addCustomerAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterCustAccount registerCustAccount = new RegisterCustAccount();
                contentPanel.removeAll();
                contentPanel.add(registerCustAccount.getMainPanel());
                contentPanel.revalidate();
            }
        });

        updateCustomerAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateCustAccount updateCustAccount = new UpdateCustAccount();
                contentPanel.removeAll();
                contentPanel.add(updateCustAccount.getMainPanel());
                contentPanel.revalidate();
            }
        });

        deleteCustomerAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteCustAccount deleteCustAccount = new DeleteCustAccount();
                contentPanel.removeAll();
                contentPanel.add(deleteCustAccount.getMainPanel());
                contentPanel.revalidate();
            }
        });

        addVehicleRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateVehicleRecord createVehicleRecord = new CreateVehicleRecord();
                contentPanel.removeAll();
                contentPanel.add(createVehicleRecord.getMainPanel());
                contentPanel.revalidate();
            }
        });

        updateVehicleRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateVehicleRecord updateVehicleRecord = new UpdateVehicleRecord();
                contentPanel.removeAll();
                contentPanel.add(updateVehicleRecord.getMainPanel());
                contentPanel.revalidate();
            }
        });

        deleteVehicleRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteVehicleRecord deleteVehicleRecord = new DeleteVehicleRecord();
                contentPanel.removeAll();
                contentPanel.add(deleteVehicleRecord.getMainPanel());
                contentPanel.revalidate();
            }
        });

        addPartsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddParts addParts = new AddParts();
                contentPanel.removeAll();
                contentPanel.add(addParts.getMainPanel());
                contentPanel.revalidate();
            }
        });

        updatePartsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateParts updateParts = new UpdateParts();
                contentPanel.removeAll();
                contentPanel.add(updateParts.getMainPanel());
                contentPanel.revalidate();
            }
        });

        manageStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageStock manageStock = new ManageStock();
                contentPanel.removeAll();
                contentPanel.add(manageStock.getMainPanel());
                contentPanel.revalidate();
            }
        });
        addJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddJob addJob = new AddJob();
                contentPanel.removeAll();
                contentPanel.add(addJob.getMainPanel());
                contentPanel.revalidate();;
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
    }
}
