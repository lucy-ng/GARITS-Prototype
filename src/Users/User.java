package Users;

public class User {
    protected String name;
    protected String surname;
    protected String role = "Person";
    protected String username;
    protected String password;

    public User(String _name, String _surname, String _role, String _username, String _password){
        this.name = _name;
        this.surname = _surname;
        this.role = _role;
        this.username = _username;
        this.password = _password;
    }

    public User(String _name, String _surname, String _username, String _password){
        this.name = _name;
        this.surname = _surname;
        this.username = _username;
        this.password = _password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
