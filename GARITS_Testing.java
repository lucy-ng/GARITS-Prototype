import Forms.*;
import Forms.Accounts.*;
import Forms.StockControl.*;
import Forms.Users.*;
import Forms.Reception.*;
import Forms.Jobs.*;
import Forms.Payments.*;

public class GARITS_Testing {
    final public JFrame mainWindow = new JFrame("GARITS Testing");

    public GARITS_Testing() {
        // Testing
        /*
        ForepersonPage forepersonPage = new ForepersonPage();
        mainWindow.setContentPane(forepersonPage.getMainPanel());
        mainWindow.setSize(1250,650);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        FranchiseePage franchiseePage = new FranchiseePage();
        mainWindow.setContentPane(franchiseePage.getMainPanel());
        mainWindow.setSize(1250,650);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        MechanicPage mechanicPage = new MechanicPage();
        mainWindow.setContentPane(mechanicPage.getMainPanel());
        mainWindow.setSize(1250,650);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        ReceptionistPage receptionistPage = new ReceptionistPage();
        mainWindow.setContentPane(receptionistPage.getMainPanel());
        mainWindow.setSize(1250,650);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        CreateCustomerRecord createCustomerRecord = new CreateCustomerRecord();
        mainWindow.setContentPane(createCustomerRecord.getMainPanel());
        mainWindow.setSize(500,500);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        AddJob addJob = new AddJob();
        mainWindow.setContentPane(addJob.getMainPanel());
        mainWindow.setSize(500,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        BookingService bookingService = new BookingService();
        mainWindow.setContentPane(bookingService.getMainPanel());
        mainWindow.setSize(500,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        /*
        RegisterCustAccount registerCustAccount = new RegisterCustAccount();
        mainWindow.setContentPane(registerCustAccount.getMainPanel());
        mainWindow.setSize(500,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);

         */

        OrderParts orderParts = new OrderParts();
        mainWindow.setContentPane(orderParts.getMainPanel());
        mainWindow.setSize(500,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
    }

    // Testing the GARITS system
    public static void main (String[] args){
        new GARITS_Testing();
    }
}
