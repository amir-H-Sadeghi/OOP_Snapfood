package Restaurant;

import java.util.ArrayList;
import java.util.Scanner;
import Verification.*;

public class RestaurantMenu {
    public static void AdminRestaurantMenu(Scanner scanner, String UserName , String Password , int id){
        System.out.println("Welcome to your account admin "+UserName);//
        System.out.println("What do you want to do?");
        System.out.println("1- Add restaurant");
        System.out.println("2- Show my restaurant list");
        System.out.println("0- Logout");
        int command;
        Admin admin = new Admin(UserName,Password,id);
        do {
            command=Command(scanner,2);
            switch (command){
                case 1:
                    restaurantRegister(scanner,admin);
                    break;
                case 2:
                    restaurantList(scanner,admin);
                    break;
                case 0:
                    admin=null;
                    System.gc();
                    FirstMenu.interiorMenu(scanner);
                    break;
            }
        }while (true);
    }
    public static void UserRestaurantMenu(Scanner scanner,String UserName , String Password , int id){
        System.out.println("Welcome to your account user "+UserName);//
        System.out.println("What do you want to do?");
        System.out.println("0- Logout");
        int command;
        User user = new User(UserName,Password,id);
        do {
            command=Command(scanner,1);
            switch (command){
                case 0:
                    user=null;
                    System.gc();
                    FirstMenu.interiorMenu(scanner);
                    break;
            }
        }while (true);
    }
    public static void restaurantRegister(Scanner scanner,Admin admin){
        do{
            System.out.println("0-Back");
            System.out.println("Enter your restaurant name");
            String RestaurantName = scanner.nextLine();
            try {
                int nothing = Integer.parseInt(RestaurantName);
                if(nothing==0)
                    AdminRestaurantMenu(scanner,admin.userName,admin.password, admin.ID);
                else if(nothing==-99)
                    System.exit(0);
            }catch (Exception e){}
            boolean condition1=true,condition2 = true;
            int X=0,Y=0;
            System.out.println("Please enter the x and y coordinates of the restaurant");
            do {
                try {
                    X = Integer.parseInt(scanner.nextLine());
                    condition1=false;
                }catch (Exception e){
                    System.out.println("Please insert a number");
                }
            }while (condition1);
            do {
                try {
                    Y = Integer.parseInt(scanner.nextLine());
                    condition2=false;
                }catch (Exception e){
                    System.out.println("Please insert a number");
                }
            }while (condition2);
            admin.RestaurantNumbers++;
            Restaurants restaurants = new Restaurants(RestaurantName,X,Y, admin.RestaurantNumbers);
            //SQL Add //public static void insertRestaurant(Restaurant restaurant)
            AdminRestaurantMenu(scanner,admin.userName, admin.password, admin.ID);
        }while(true);

    }
    public static void restaurantList(Scanner scanner,Admin admin){
        ArrayList<Restaurants> restuarantsArrayList = /*public static void restaurantList(Admin admin)*/new ArrayList<>(); //SQL search
        for (int i=0;i< restuarantsArrayList.size() ; i++){
            System.out.println(i+1+"-"+restuarantsArrayList.get(i).name);
        }
        System.out.println("Choose your restaurant");
        int command;
        do {
            command =Command(scanner,restuarantsArrayList.size());
            if(command==0)
                AdminRestaurantMenu(scanner,admin.userName, admin.password, admin.ID);
            if (command==-99)
                System.exit(0);
            else {
                restuarantsArrayList = null;
                System.gc();
                restaurantPage(scanner, admin, restuarantsArrayList.get(command-1));
            }
        }while (true);
    }
    public static void restaurantPage(Scanner scanner, Admin admin , Restaurants restaurants){
        System.out.println("Restaurant "+ restaurants.name);
        System.out.println("Location: " + restaurants.x_coordinate + ","+ restaurants.y_coordinate);
        System.out.println();
        System.out.println("1- Edit restaurant");
        System.out.println("0-Back");
        int command;
        do {
            command = Command(scanner,1);
            switch (command){
                case 0:
                    restaurants=null;
                    System.gc();
                    AdminRestaurantMenu(scanner,admin.userName, admin.password, admin.ID);
                    break;
                case 1:

                    break;
                case -99:
                    System.exit(0);
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
