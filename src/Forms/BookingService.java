package Forms;

import javax.swing.*;

public class BookingService extends JFrame{
    private JTextField vehicleRegNo;
    private JLabel vehicleRegNoLabel;
    private JTextField dateBooking;
    private JLabel dateBookingLabel;
    private JButton submitButton;
    private JPanel mainPanel;
    private JRadioButton MOTRadioButton;
    private JRadioButton repairRadioButton;
    private JLabel serviceTypeLabel;
    private JLabel bookServiceTitle;
    private JLabel firstNameLabel;
    private JLabel secondNameLabel;
    private JTextField firstName;
    private JTextField secondName;
    private JLabel emailLabel;
    private JTextField email;
    private JTextField phone;
    private JLabel phoneLabel;
    private JLabel addressLabel;
    private JTextField address;
    private JTextField postCode;
    private JLabel postCodeLabel;

    public BookingService() {

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
