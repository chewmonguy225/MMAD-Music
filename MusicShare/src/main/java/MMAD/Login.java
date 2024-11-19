package MMAD;

public class Login {
    private String username;
    private String password;

    public boolean attemptLogin(String username, String password){
        if(username == this.username && password == this.password){
            //get the access to t
            return true;
        }
        else{
            return false;
        }

    }
}
