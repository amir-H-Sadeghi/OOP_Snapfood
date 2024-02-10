package ObjectClasses;
public class Comment {
    public Comment(User user1,String comment1,Restuarants restuarants1 ){
        user=user1;comment=comment1;restuarants=restuarants1;food=null;
    }
    public Comment(User user1,String comment1,Restuarants restuarants1,Food food1){
        user=user1;comment=comment1;restuarants=restuarants1;food=food1;Answer="";
    }
    public User user;
    public String comment;
    public Restuarants restuarants;
    public Food food;
    public String Answer;
}
