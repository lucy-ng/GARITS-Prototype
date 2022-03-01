package Forms.Jobs;

import javax.swing.*;

public class UpdateJob {
    private JLabel updateJobTitle;
    private JTextField searchJob;
    private JButton searchButton;
    private JLabel tableOfJobsLabel;
    private JTable jobs;
    private JTextField description;
    private JComboBox status;
    private JTextField vehicleDetails;
    private JTextField customerDetails;
    private JTextField mechanic;
    private JButton updateButton;
    private JPanel mainPanel;
    private JLabel searchForJobLabel;
    private JLabel descriptionLabel;
    private JLabel statusLabel;
    private JLabel vehicleDetailsLabel;
    private JLabel customerDetailsLabel;
    private JLabel mechanicLabel;

    public UpdateJob() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
