package MMAD;

public class AccountHandler {
    private static AccountHandler ah;
    private static DBHandler dbh = new DBHandler();
    Login currentUser;

    private AccountHandler(){

    }

    public static AccountHandler access(){
        if(ah == null){
            ah = new AccountHandler();
        }
        return ah;
    }

    public Login getCurrentUser(){
        return currentUser;
    }

    public int login(UI ui, Display d){
        d.loginUsername();
        String username = ui.getString();
        if(username.equals("0"))
            return 0;
        d.loginPassword();
        String password = ui.getString();
        if(password.equals("0"))
            return 0;

        if(loginAttempt(username, password))
        {   
            d.successfulLogin(username);
            currentUser = new Login(username, password);
            return 1;
        } else {
            d.invalidLogin();
            return -1; //simulates username or password invalid
        }   
    }

    public int signup(UI ui, Display d){
        d.loginUsername();
        String username = ui.getString();
        if(username.equals("0"))
            return 0;
        d.loginPassword();
        String password = ui.getString();
        if(password.equals("0"))
            return 0;

        if(ah.createAccount(username, password)){
            d.successfulSignup();
            currentUser = new Login(username, password);
            return 1;
        } else {
            d.unsuccessfulSignup();
            return -1;
        }
    }


    public boolean createAccount(String username, String password){
        Login login = new Login(username, password);
        return dbh.createUser(login);
    }

    private static boolean loginAttempt(String username, String password){
        Login login = new Login(username, password);
        return login.attemptLogin(dbh);
    }

    public int changePassword(UI ui, Display d){
        d.changePassword();
        String newPass = ui.getString();
        currentUser = new Login(currentUser.getUsername(), newPass);
        //query to change password - return 
        return -1;
    }

    public int logout(){
        currentUser = null;
        return 1;
    }

    public boolean deleteAccount(){
        if(currentUser != null)
            return dbh.deleteUser(currentUser);
        return false;
    }
}
