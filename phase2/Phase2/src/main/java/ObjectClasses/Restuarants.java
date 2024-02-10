package ObjectClasses;
import java.util.ArrayList;
import java.util.TreeSet;

public class Restuarants {
    public String name;
    public String Location;
    public int ID;
    public TreeSet<String> FoodType;
    int Foods ;
    public Orders[] orders;
    public ArrayList<Rating> ratings = new ArrayList<>();
    public String getName(){return name;}
    public int getID(){return ID;}

    public Restuarants(String name ,String location1, TreeSet<String> FoodType){setName(name);Location=location1;setID();setFoodType(FoodType);Foods=0;}
    private void setName(String name){this.name=name;}
    private void setID(){this.ID=++Admin.RestaurantNumbers;}
    private void setFoodType(TreeSet<String> FoodType){this.FoodType=FoodType;}

    public void setOrders(Orders[] orders) {
        this.orders = orders;
    }
}
