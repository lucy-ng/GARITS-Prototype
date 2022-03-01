package Forms.Jobs;

import javax.swing.*;

public class AddJob {
    private JTable jobs;
    private JTextField searchCust;
    private JTextField searchVehicle;
    private JTextArea jobDescription;
    private JComboBox resultsParts;
    private JProgressBar estimatedTimeBar;
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
    private JLabel timeLabel;
    private JButton addJobButton;
    private JLabel mechanicLabel;
    private JTextField mechanic;

    public AddJob() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
