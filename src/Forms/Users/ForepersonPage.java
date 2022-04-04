package Forms.Users;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Forms.Accounts.DeleteCustAccount;
import Forms.Accounts.RegisterCustAccount;
import Forms.Accounts.UpdateCustAccount;
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
    private JButton deleteVehicleRecordButton;
    private JPanel contentPanel;
    private JButton viewJobsButton;
    private JButton allocateMechanicButton;
    private JButton sellPartsButton;
    private JButton stockLevelReportButton;
    private JButton makePaymentButton;
    private JButton generateMOTReminderButton;
    private JButton orderPartsButton;

    public ForepersonPage(JFrame window) {
        window.setContentPane(mainPanel);
        window.setVisible(true);

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
    }
}
