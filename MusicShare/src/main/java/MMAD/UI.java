package MMAD;

import java.util.*;

public class UI {
    private static UI ui;
    private Scanner input = new Scanner(System.in);
    private UI(){
    }

    public static UI access(){
        if(ui == null){
            ui = new UI();
        }
        return ui;
    }

    public int getInt(){
        int in = -1;
        if(input.hasNextInt()){
            in = input.nextInt();
        }
        
        // Consume the leftover newline character
        input.nextLine();
        return in;
    }

    public String getString(){
        return input.nextLine();
    }

        
}
