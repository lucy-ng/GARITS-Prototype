package Forms.Payments;

import javax.swing.*;

public class MakePayment {
    private JLabel makePaymentTitle;
    private JTextField cardNumber;
    private JTextField amount;
    private JButton payButton;
    private JPanel mainPanel;
    private JLabel cardNumberLabel;
    private JLabel amountLabel;

    public MakePayment() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
