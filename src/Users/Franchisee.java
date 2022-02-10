package Users;

public class Franchisee extends User{
    public Franchisee(String _name, String _surname, String _username, String _password){
        super(_name, _surname, "Administrator", _username, _password);
    }
}
