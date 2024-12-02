package MMAD;

public class AccountHandler {
    private static AccountHandler ah;
    DBHandler dbh = new DBHandler();

    private AccountHandler(){

    }

    public static AccountHandler access(){
        if(ah == null){
            ah = new AccountHandler();
        }
        return ah;
    }

    public boolean createAccount(String username, String password){
        Login login = new Login(username, password);
        return dbh.createUser(login);
    }

    public boolean loginAttempt(String username, String password){
        Login login = new Login(username, password);
        return login.attemptLogin(dbh);
    }
}
