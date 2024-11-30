package MMAD;

public class Display {
    private static Display d;

    private Display(){

    }

    public static Display access(){
        if (d == null){
            d = new Display();
        }
        return d;
    }

    public void loginOrSignup(){
        System.out.println("");
    }
}

