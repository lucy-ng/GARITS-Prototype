package Database;

import java.math.BigDecimal;

public class CustomerAccount {
    private String companyName;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private String address;
    private String homePhoneNo;
    private String daytimePhoneNo;
    private String eveningPhoneNo;
    private String membershipType;
    private String discountPlan;
    private BigDecimal discountPrice;
    private int discountPercentage;

    public CustomerAccount(String companyName, String username, String firstName, String lastName, String email, String phoneNo, String address, String homePhoneNo, String daytimePhoneNo, String eveningPhoneNo, String membershipType, String discountPlan, BigDecimal discountPrice, int discountPercentage) {
        this.companyName = companyName;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.homePhoneNo = homePhoneNo;
        this.daytimePhoneNo = daytimePhoneNo;
        this.eveningPhoneNo = eveningPhoneNo;
        this.membershipType = membershipType;
        this.discountPlan = discountPlan;
        this.discountPrice = discountPrice;
        this.discountPercentage = discountPercentage;
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

    public String getAddress() {
        return address;
    }

    public String getHomePhoneNo() {
        return homePhoneNo;
    }

    public String getDaytimePhoneNo() {
        return daytimePhoneNo;
    }

    public String getEveningPhoneNo() {
        return eveningPhoneNo;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public String getDiscountPlan() {
        return discountPlan;
    }

    public String getCompanyName() {
        return companyName;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

}
