package MMAD;

import java.util.*;

public class UI {
    private static Controller c = Controller.access();
    private static Display d = Display.access();
    private static ArrayList<String> menuList = populateMenus();
    private static String currentMenu = "login or signup";

    private static ArrayList<String> populateMenus(){
        ArrayList<String> ar= new ArrayList();
        ar.add("login or signup");
        ar.add("login");
        ar.add("signup");
        ar.add("home");
        ar.add("playlist");
        ar.add("friends");
        ar.add("review");
        ar.add("song search");
        ar.add("album search");
        ar.add("artist search");
        return ar;
    }
    public static void main(String[] args) {
        // Create a Scanner object to read input from the user
        Scanner input = new Scanner(System.in);
        boolean valid = false;

        while(!valid){
            switch(currentMenu){
                case "login or signup":
                    int option = loginOrSignup(input);
                    if(option == 1){
                        currentMenu = "login";
                        valid = true;
                    } else if(option == 2){
                        currentMenu = "signup";
                        valid = true;
                    } else {
                        valid = false;
                    }
                    break;
                case "login":
                    break;
                case "signup":
                    break;
                case "home":
                    break;
                case "playlist":
                    break;
                case "friends":
                    break;
                case "review":
                    break;
                case "song search":
                    break;
                case "album search":
                    break;
                case "artist search":
                    break;
            }
        }

        // Prompt the user to enter a string
        System.out.print("Enter a string: ");
        String userInputString = input.nextLine();  // Read string input

       

        // Close the scanner
        input.close();
    }

    private static int loginOrSignup(Scanner input){
        d.loginOrSignup();
        int userInputInt = input.nextInt();
        // Consume the leftover newline character
        input.nextLine();
        if(userInputInt == 1){
            return 1;
        } else if(userInputInt == 2){
            return 2;
        }
        return -1;
    }


}

