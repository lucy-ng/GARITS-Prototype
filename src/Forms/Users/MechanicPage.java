package Forms.Users;

import Forms.Jobs.*;
import Forms.StockControl.*;

import javax.swing.*;
import java.awt.event.*;

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

    public MechanicPage(JFrame window) {
        window.setContentPane(mainPanel);
        window.setVisible(true);

        viewJobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewJobs viewJobs = new ViewJobs();
                contentPanel.removeAll();
                contentPanel.add(viewJobs.getMainPanel());
                contentPanel.revalidate();
            }
        });

        updateJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateJob updateJob = new UpdateJob();
                contentPanel.removeAll();
                contentPanel.add(updateJob.getMainPanel());
                contentPanel.revalidate();
            }
        });

        pickJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
