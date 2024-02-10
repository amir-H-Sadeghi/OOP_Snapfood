package Verification;

import java.util.Scanner;
import Restaurant.*;

public class FirstMenu {
    public static int AdminIdCounter=0;
    public static int UserIdCounter=0;
    public static void interiorMenu(Scanner scanner){
        System.out.println("///// Hello buddy  ////");
        System.out.println("  Good to see you again  ");
        System.out.println("  To continue Enter your command with the given number");
        System.out.println("Tip: You can end the program whenever you want with entering the number -99");
        System.out.println("  So what can I do for you? ");
        System.out.println("1- Sign in ");
        System.out.println("2- Sign up ");
        int command;
        do{
            command=Command(scanner,2);
            switch (command){
                case 1:
                    ChooseRole(scanner,true);
                    break;
                case 2:
                    ChooseRole(scanner,false);
                    break;
                case 0:
                    break;
            }
        }while (command!=0);

    }
    public static void ChooseRole(Scanner scanner,boolean identifier){
        System.out.println("Choose your role");
        System.out.println("1-Admin");
        System.out.println("2-User");
        System.out.println("0-Back");
        int command;
        do {
            command=Command(scanner,2);
            switch (command){
                case 1:
                    if (identifier)
                        AdminSignInMenu(scanner);
                    else
                        AdminRegister(scanner);
                    break;
                case 2:
                    if (identifier)
                        UserSignInMenu(scanner);
                    else
                        UserRegister(scanner);
                    break;
                case 0:
                    interiorMenu(scanner);
                    break;
            }
        }while (true);
    }
    public static void AdminSignInMenu(Scanner scanner){
        System.out.println("0-Back");

        do {
            boolean Search =true;
            System.out.println("Enter your username");
            String UserName = scanner.nextLine();
            try{
                int search = Integer.parseInt(UserName);
                if(search==-99)
                    System.exit(0);
                else if(search==0)
                    ChooseRole(scanner,true);
            }catch (Exception e){}
            if(SQLiteJDBC.search(UserName,"PASSWORD",1).isEmpty()){
                System.out.println("There is no Account with the given username");
                Search=false;
            }
            if(Search){
                do {
                    System.out.println("Enter your password");
                    String Password = scanner.nextLine();

                    if(SQLiteJDBC.search(UserName,"PASSWORD",1).equals(Password)){
                        RestaurantMenu.AdminRestaurantMenu(scanner, UserName,Password,Integer.parseInt(SQLiteJDBC.search(UserName,"id",1)));
                    }
                    else
                        System.out.println("Wrong password");
                }while (true);
            }
        }while (true);

    }
    public static void UserSignInMenu(Scanner scanner){

        System.out.println("0-Back");
        do {
            boolean Search=true;
            System.out.println("Enter your username");
            String UserName = scanner.nextLine();
            try{
                int search = Integer.parseInt(UserName);
                if(search==0)
                    ChooseRole(scanner,true);
                if(search==-99)
                    System.exit(0);
            }catch (Exception e){}
            if(SQLiteJDBC.search(UserName,"PASSWORD",2).isEmpty()){
                System.out.println("There is no Account with the given username");
                Search=false;
            }
            if(Search) {
                do {
                    System.out.println("Enter your password");
                    String Password = scanner.nextLine();
                    if(SQLiteJDBC.search(UserName,"PASSWORD",2).equals(Password)){
                        User A = new User(UserName,Password,Integer.parseInt(SQLiteJDBC.search(UserName,"id",2)));//?
                        RestaurantMenu.UserRestaurantMenu(scanner, UserName , Password , Integer.parseInt(SQLiteJDBC.search(UserName,"id",2)));
                    }
                    else
                        System.out.println("Wrong password");
                }while (true);
            }
        }while (true);

    }
    public static void AdminRegister(Scanner scanner){
        do {
            System.out.println("0-Back");
            System.out.println("Enter your username");
            String UserName = scanner.nextLine();
            try {
                int notImportant = Integer.parseInt(UserName);
                if(notImportant==0)
                    ChooseRole(scanner,false);
                else if(notImportant==-99)
                    System.exit(0);
            }catch (Exception e){}
            System.out.println("Enter your password");
            String Password = scanner.nextLine();
            if(!SQLiteJDBC.search(UserName,"PASSWORD",1).isEmpty())
                System.out.println("An account with the given name already exists");
            else{
                AdminIdCounter++;
                Admin A = new Admin(UserName, Password, AdminIdCounter);
                System.out.println("Account registered successfully");
                SQLiteJDBC.insertSignUp(UserName, Password, 1);
                RestaurantMenu.AdminRestaurantMenu(scanner, UserName,Password,AdminIdCounter);
            }

        }while (true);
    }
    public static void UserRegister(Scanner scanner){
        do {
            System.out.println("0-Back");
            System.out.println("Enter your username");
            String UserName = scanner.nextLine();
            try {
                int notImportant = Integer.parseInt(UserName);
                if(notImportant==0)
                    ChooseRole(scanner,false);
                else if(notImportant==-99)
                    System.exit(0);
            }catch (Exception e){}
            System.out.println("Enter your password");
            String Password = scanner.nextLine();
            if(!SQLiteJDBC.search(UserName,"PASSWORD",2).isEmpty())
                System.out.println("An account with the given name already exists");
            else{
                UserIdCounter++;
                User A = new User(UserName, Password, UserIdCounter);
                SQLiteJDBC.insertSignUp(UserName, Password, 2);
                System.out.println("Account registered successfully");
                RestaurantMenu.UserRestaurantMenu(scanner, UserName,Password,UserIdCounter);
            }
        }while (true);
    }
    public static int Command(Scanner scanner,int limit){
        boolean condition=true;
        String command= scanner.nextLine();
        int commandInt=-10;
        try {
            commandInt = Integer.parseInt(command);
        }catch (Exception InputMismatchException){
            System.out.println("Entered command is not correct. Please enter a number");
            condition=false;

        }
        if(condition) {
            if(commandInt==-99)
                System.exit(0);
            if (commandInt > limit || commandInt < 0)
                System.out.println("invalid command.Please choose a number from command list");
        }
        return commandInt;
    }
}
