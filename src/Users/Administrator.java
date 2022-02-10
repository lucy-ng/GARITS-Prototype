package Users;

// Administrator is the user
public class Administrator extends User{
    public Administrator(String _name, String _surname, String _username, String _password){
        super(_name, _surname, "Administrator", _username, _password);
    }

    //
    public void addUser(String _name, String _surname, String _username, String _password){
        User user = new User(_name, _surname, _username, _password);
    }
    public void removeUser(){

    }
    public void changeUser(){

    }
}
