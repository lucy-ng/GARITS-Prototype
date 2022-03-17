package Forms.Users;

import javax.swing.*;

public class MechanicPage {
    private JPanel mainPanel;
    private JButton viewJobsButton;
    private JButton pickJobButton;
    private JLabel jobsLabel;
    private JButton updateJobButton;
    private JLabel mechanicPageTitle;
    private JButton searchPartsButton;
    private JPanel contentPanel;
    private JLabel partsLabel;

    public MechanicPage() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
    public JPanel getContentPanel() { return contentPanel; }

    // Return buttons
    public JButton getViewJobsButton() {
        return viewJobsButton;
    }

    public JButton getPickJobButton() {
        return pickJobButton;
    }

    public JButton getUpdateJobButton() {
        return updateJobButton;
    }

    public JButton getSearchPartsButton() {
        return searchPartsButton;
    }
}
