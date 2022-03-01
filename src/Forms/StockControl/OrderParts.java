package Forms.StockControl;

import javax.swing.*;

public class OrderParts {
    private JTextField searchParts;
    private JTable tblParts;
    private JButton searchButton;
    private JList spareParts;
    private JButton orderButton;
    private JPanel mainPanel;
    private JLabel orderPartsLabel;
    private JLabel searchSparePartsLabel;
    private JLabel partsTableLabel;
    private JLabel listOfPartsLabel;

    public OrderParts() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
