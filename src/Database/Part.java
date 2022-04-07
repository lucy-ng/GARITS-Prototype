package Database;

import java.math.BigDecimal;

public class Part {
    private String name;
    private String manufacturer;
    private String vehicleType;
    private String year;
    private BigDecimal price;
    private int quantity;
    private int lowThreshold;

    public Part(String name, String manufacturer, String vehicleType, String year, BigDecimal price, int quantity, int lowThreshold) {
        this.name = name;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getYear() {
        return year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getLowThreshold() {
        return lowThreshold;
    }
}
