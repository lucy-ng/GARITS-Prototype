package Forms.StockControl;

import Database.Part;
import Database.PartOrder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class DeliverParts {
    private JPanel mainPanel;
    private JLabel recordPartDeliveryLabel;
    private JLabel deliveryDateLabel;
    private JTextField deliveryDate;
    private JLabel amountDeliveredLabel;
    private JTextField amountDelivered;
    private JButton recordButton;
    private JLabel partOrdersLabel;
    private JScrollPane partOrdersScrollPane;
    private JLabel partOrderIDLabel;
    private JTextField partOrderID;
    private JTable partOrdersTable;

    public DeliverParts() {
        partOrdersScrollPane.setPreferredSize(new Dimension(500,500));

        Vector headers = new Vector();
        headers.addElement("Part Order ID");
        headers.addElement("Amount Ordered");
        headers.addElement("Part Order Date");
        headers.addElement("Part ID");
        headers.addElement("Part Name");
        Vector rows = new Vector();
        partOrdersTable = new JTable(rows, headers);
        DefaultTableModel partOrdersModel = (DefaultTableModel) partOrdersTable.getModel();
        partOrdersScrollPane.setViewportView(partOrdersTable);
        partOrdersTable.setVisible(true);

        ArrayList<PartOrder> partOrderList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26","in2018t26","5CrmPJHN");

            PreparedStatement statement = connection.prepareStatement("SELECT * from SparePartsOrder");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                PreparedStatement stmt = connection.prepareStatement("SELECT name FROM SpareParts WHERE partID = ?");
                stmt.setInt(1, rs.getInt("partID"));
                ResultSet r = stmt.executeQuery();

                while (r.next()) {
                    int partOrderIDText = rs.getInt("partOrderID");
                    int amountOrderedText = rs.getInt("amountOrdered");
                    Date partOrderDate = rs.getDate("partOrderDate");
                    int partIDText = rs.getInt("partID");
                    String nameText = r.getString("name");

                    PartOrder partOrder;
                    partOrder = new PartOrder(partOrderIDText, amountOrderedText, partOrderDate, partIDText);
                    partOrderList.add(partOrder);

                    Object[] row = new Object[5];
                    for (int i = 0; i < partOrderList.size(); i++) {
                        row[0] = partOrder.getPartOrderID();
                        row[1] = partOrder.getAmountOrdered();
                        row[2] = partOrder.getPartOrderDate();
                        row[3] = partOrder.getPartID();
                        row[4] = nameText;
                    }
                    partOrdersModel.addRow(row);
                }
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00/in2018t26", "in2018t26", "5CrmPJHN");

                    String partOrderIDText = partOrderID.getText();
                    String deliveryDateText = deliveryDate.getText();
                    String amountDeliveredText = amountDelivered.getText();

                    connection.setAutoCommit(false);
                    PreparedStatement statement = connection.prepareStatement("UPDATE SparePartsOrder SET amountDelivered = ?, deliveryDate = ? WHERE partOrderID = ?");
                    statement.setInt(1, Integer.parseInt(amountDeliveredText));
                    statement.setDate(2, Date.valueOf(deliveryDateText));
                    statement.setInt(3, Integer.parseInt(partOrderIDText));
                    statement.executeUpdate();

                    PreparedStatement selectStmt = connection.prepareStatement("SELECT quantity FROM SpareParts WHERE partID = (SELECT partID FROM SparePartsOrder WHERE partOrderID = ?)");
                    selectStmt.setInt(1, Integer.parseInt(partOrderIDText));
                    ResultSet rs = selectStmt.executeQuery();

                    while (rs.next()) {
                        PreparedStatement stmt = connection.prepareStatement("UPDATE SpareParts SET quantity = ? WHERE partID = (SELECT partID FROM SparePartsOrder WHERE partOrderID = ?)");
                        int sum = rs.getInt("quantity") + Integer.parseInt(amountDeliveredText);
                        stmt.setInt(1, sum);
                        stmt.setInt(2, Integer.parseInt(partOrderIDText));
                        stmt.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "Part updated!");
                    connection.setAutoCommit(true);
                    connection.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
