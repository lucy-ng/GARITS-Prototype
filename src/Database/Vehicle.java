package Database;

public class Vehicle {
    private String registrationNo;
    private String colour;
    private String make;
    private String model;
    private String chassisNo;
    private String engineSerial;
    private String year;

    public Vehicle(String regNo, String colour, String make, String model, String chassisNo, String engineSerial, String year) {
        this.registrationNo = regNo;
        this.colour = colour;
        this.make = make;
        this.model = model;
        this.chassisNo = chassisNo;
        this.engineSerial = engineSerial;
        this.year = year;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public String getColour() {
        return colour;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public String getEngineSerial() {
        return engineSerial;
    }

    public String getYear() {
        return year;
    }
}
