package Database;

import java.util.Date;

public class PartOrder {
    private int partOrderID;
    private int amountOrdered;
    private Date partOrderDate;
    private int partID;

    public PartOrder(int partOrderID, int amountOrdered, Date partOrderDate, int partID) {
        this.partOrderID = partOrderID;
        this.amountOrdered = amountOrdered;
        this.partOrderDate = partOrderDate;
        this.partID = partID;
    }

    public int getPartOrderID() {
        return partOrderID;
    }

    public int getAmountOrdered() {
        return amountOrdered;
    }

    public Date getPartOrderDate() {
        return partOrderDate;
    }

    public int getPartID() {
        return partID;
    }
}
