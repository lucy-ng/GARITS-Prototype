package Forms.Users;

import Forms.Jobs.*;
import Forms.StockControl.*;

import javax.swing.*;
import java.awt.event.*;

public class MechanicPage {
    private JPanel mainPanel;
    private JButton viewJobsButton;
    private JButton usePartsButton;
    private JLabel jobsLabel;
    private JButton updateJobButton;
    private JButton searchPartsButton;
    private JPanel contentPanel;
    private JLabel partsLabel;
    private JLabel mechanicPageTitle;

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
                UpdateJobMechanic updateJobMechanic = new UpdateJobMechanic();
                contentPanel.removeAll();
                contentPanel.add(updateJobMechanic.getMainPanel());
                contentPanel.revalidate();
            }
        });

        usePartsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UseParts useParts = new UseParts();
                contentPanel.removeAll();
                contentPanel.add(useParts.getMainPanel());
                contentPanel.revalidate();
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
        return usePartsButton;
    }

    public JButton getUpdateJobButton() {
        return updateJobButton;
    }

    public JButton getSearchPartsButton() {
        return searchPartsButton;
    }
}
