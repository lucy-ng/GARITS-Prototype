package Forms.Jobs;

import javax.swing.*;

public class AddJob {
    private JTextField searchCust;
    private JTextField searchVehicle;
    private JTextArea jobDescription;
    private JPanel mainPanel;
    private JLabel addJobLabel;
    private JLabel descriptionOfJobLabel;
    private JLabel sparePartsLabel;
    private JLabel tableOfJobsLabel;
    private JLabel customerDetailsLabel;
    private JLabel vehicleDetailsLabel;
    private JScrollPane resultsCust;
    private JScrollPane resultsVehicle;
    private JLabel estimatedTimeLabel;
    private JLabel mechanicLabel;
    private JTextField mechanic;
    private JScrollPane resultsJobs;
    private JButton searchCustomerButton;
    private JButton searchVehicleButton;
    private JButton searchMechanicButton;
    private JTextField estimatedTime;
    private JButton addJobButton;
    private JLabel jobStatusLabel;
    private JTextField searchSparePart;
    private JButton addSparePartButton;
    private JScrollPane resultsMechanic;
    private JScrollPane resultsSpareParts;
    private JComboBox jobStatus;

    public AddJob() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
