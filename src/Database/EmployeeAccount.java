package Database;

import java.math.BigDecimal;

public class EmployeeAccount {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private String employeePosition;
    private String department;
    private BigDecimal labourRate;

    public EmployeeAccount(String user, String first, String last, String email, String phone, String employeePosition, String department, BigDecimal labourRate) {
        this.username = user;
        this.firstName = first;
        this.lastName = last;
        this.email = email;
        this.phoneNo = phone;
        this.employeePosition = employeePosition;
        this.department = department;
        this.labourRate = labourRate;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public String getDepartment() {
        return department;
    }

    public BigDecimal getLabourRate() {
        return labourRate;
    }
}
