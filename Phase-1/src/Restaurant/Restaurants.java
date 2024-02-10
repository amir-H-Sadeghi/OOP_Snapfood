package Restaurant;

public class Restaurants {
    public String name;
    public int x_coordinate;
    public int y_coordinate;
    public int ID;
    enum foodType{
        Fastfood,

    }
    public String getName(){return name;}
    public int getX_coordinate(){return x_coordinate;}
    public int getY_coordinate(){return y_coordinate;}
    public int getID(){return ID;}

    Restaurants(String name , int x_coordinate , int y_coordinate , int ID){setName(name);setX_coordinate(x_coordinate);setY_coordinate(y_coordinate);setID(ID);}
    private void setName(String name){this.name=name;}
    private void setX_coordinate(int x_coordinate){this.x_coordinate = x_coordinate;}
    private void setY_coordinate(int y_coordinate){this.y_coordinate = y_coordinate;}
    private void setID(int ID){this.ID=ID;}

}
