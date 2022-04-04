package Database;

public class Part {
    private String name;
    private String code;
    private String manufacturer;
    private String vehicleType;
    private String year;
    private String price;
    private int quantity;
    private int lowThreshold;

    public Part(String name, String code, String manufacturer, String vehicleType, String year, String price, int quantity, int lowThreshold) {
        this.name = name;
        this.code = code;
        this.manufacturer = manufacturer;
        this.vehicleType = vehicleType;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.lowThreshold = lowThreshold;
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

    public String getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getLowThreshold() {
        return lowThreshold;
    }
}
