package Forms.Users;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Forms.*;
import Forms.Accounts.RegisterCustAccount;
import Forms.Vehicles.CreateVehicleRecord;

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

        addVehicleRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateVehicleRecord createVehicleRecord = new CreateVehicleRecord();
                contentPanel.removeAll();
                contentPanel.add(createVehicleRecord.getMainPanel());
                contentPanel.revalidate();
            }
        });
    }
}
