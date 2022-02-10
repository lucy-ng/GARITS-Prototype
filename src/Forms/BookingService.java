package Forms;

import javax.swing.*;

public class BookingService extends JFrame{
    private JTextField vehicleRegNo;
    private JLabel vehicleRegNoLabel;
    private JTextField dateBooking;
    private JLabel dateBookingLabel;
    private JButton submitButton;
    private JPanel mainPanel;

    public BookingService() {
        setContentPane(mainPanel);
        setTitle("Book a Service");
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
