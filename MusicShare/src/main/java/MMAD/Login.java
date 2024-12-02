package MMAD;
public class Login {
    //not sure if this should be credential or login
    String username;
    String password;

    public Login(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public boolean attemptLogin(DBHandler dbh){
        return dbh.attemptLogin(this);
    }
}
