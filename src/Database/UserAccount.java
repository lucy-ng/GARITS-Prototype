package Database;

public class UserAccount {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phoneNo;

    public UserAccount(String user, String first, String last, String e, String phone) {
        this.username = user;
        this.firstName = first;
        this.lastName = last;
        this.email = e;
        this.phoneNo = phone;
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

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
