package Database;

import java.util.Date;

public class PartUse {
    private int partUseID;
    private int amountUsed;
    private Date partUseDate;
    private int partID;
    private String regNo;

    public PartUse(int partUseID, int amountUsed, Date partUseDate, int partID, String regNo) {
        this.partUseID = partUseID;
        this.amountUsed = amountUsed;
        this.partUseDate = partUseDate;
        this.partID = partID;
        this.regNo = regNo;
    }

    public PartUse(int amountUsed, Date partUseDate) {
        this.amountUsed = amountUsed;
        this.partUseDate = partUseDate;
    }

    public int getPartUseID() {
        return partUseID;
    }

    public int getAmountUsed() {
        return amountUsed;
    }

    public Date getPartUseDate() {
        return partUseDate;
    }

    public int getPartID() {
        return partID;
    }

    public String getRegNo() {
        return regNo;
    }
}
