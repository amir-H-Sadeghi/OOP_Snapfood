package com.example.phase2;
import java.util.ArrayList;
import ObjectClasses.*;
public abstract class Account {
    String userName;
    String password;
    int ID;
    abstract void setUserName(String userName);
    abstract void setPassword(String password);
    abstract void setID(int ID);

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public int getID() {
        return ID;
    }
}
class Admin extends Account{

    public static int RestaurantNumbers=0;
    Admin(String userName,String password,int ID){setUserName(userName);setPassword(password);setID(ID);}
    @Override
    void setPassword(String password) {this.password = password;}

    @Override
    void setUserName(String userName) {this.userName = userName;}

    @Override
    void setID(int ID) {
        this.ID=ID;
    }
}

class User extends Account{

    User(String userName,String password,int ID){setUserName(userName);setPassword(password);setID(ID);BuyingList=new ArrayList<>();Credit=0;}
    public ArrayList<Orders> BuyingList;
    public Orders[] BuyHistory;
    public int Credit;

    @Override
    void setPassword(String password) {this.password = password;}

    @Override
    void setUserName(String userName) {this.userName = userName;}

    @Override
    void setID(int ID) {this.ID=ID;}
}
