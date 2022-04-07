package Database;

public class Stock {
    private int partID;
    private String name;
    private String code;
    private String manufacturer;
    private String vehicleType;
    private String year;
    private float price;
    private int quantity;
    private int lowThreshold;

    public Stock(int partID, String name, String code, String manufacturer, String vehicleType, String year, float price, int quantity, int lowThreshold) {
        this.partID = partID;
        this.name = name;
        this.code = code;
        this.manufacturer = manufacturer;
        this.vehicleType = vehicleType;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.lowThreshold = lowThreshold;
    }

    public int getPartID() {
        return partID;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getYear() {
        return year;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getLowThreshold() {
        return lowThreshold;
    }
}
