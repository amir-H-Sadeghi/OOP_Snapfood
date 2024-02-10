package com.example.phase2;

import ObjectClasses.Account;
import ObjectClasses.Restuarants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.TreeSet;

public class Start extends Application {
    public static Stage AllStage;
    public static  Admin admin = new Admin("Ali123","Ali123",1);
    public void StartRunner(Admin admin1){
        admin = admin1;
        try {
            start(AllStage);
        }catch (Exception e){

        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        AllStage=stage;
        BorderPane layout = new BorderPane();
        layout.setPrefSize(292,550);
        ImageView imageView = new ImageView(new Image(new FileInputStream("C:/Users/sadeg/OneDrive/Desktop/SUT/OOP/Project/phase2/Phase2/src/main/resources/Pictures/Google_Contacts_icon.svg.png")));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        GridPane gridPane= new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPrefSize(192,100);
        Label UserName = new Label("Username: ");
        Label Password = new Label("Password: ");
        Button button = new Button("Edit Profile");
        button.setOnMouseClicked(e -> EditProfilePage());
        gridPane.add(UserName,0,0);
        gridPane.add(Password,0,1);
        gridPane.add(button,2,2);
        gridPane.add(new Label(admin.userName),1,0);
        gridPane.add(new Label(admin.password),1,1);
        HBox information = new HBox(imageView,gridPane);
        layout.setTop(information);
        Label label = new Label("Restaurants:");
        BorderPane restaurantList = new BorderPane();
        restaurantList.setPrefSize(450,285);
        restaurantList.setTop(label);
        BorderPane RestaurantList = AdminFirstPage.RestaurantList(restaurantList);
        Button Logout = new Button("Logout");
        //


        Logout.setAlignment(Pos.CENTER);
        Button AddRestaurant = new Button("Add Restaurant");
        AddRestaurant.setAlignment(Pos.CENTER);
        HBox hBox = new HBox(Logout,AddRestaurant);
        hBox.setAlignment(Pos.CENTER);
        RestaurantList.setBottom(hBox);
        layout.setCenter(RestaurantList);
        Scene scene = new Scene(layout, 292, 550);
        AddRestaurant.setOnMouseClicked(e -> AddRestaurant());
        stage.setTitle("SnapFood");
        stage.setScene(scene);
        stage.show();
    }

    public void EditProfilePage(){
        Label UserName = new Label("UserName: ");
        TextField textField = new TextField();
        textField.setPromptText("UserName");
        Label Pass = new Label("Password: ");
        TextField textField1 = new TextField();
        textField1.setPromptText("Password");
        HBox Password = new HBox(Pass,textField1);
        Password.setAlignment(Pos.CENTER);
        HBox User = new HBox(UserName,textField);
        User.setAlignment(Pos.CENTER);
        Button Exit = new Button("Back");
        Exit.setOnMouseClicked(e -> StartRunner(admin));
        Button Save = new Button("Save");
        Save.setOnMouseClicked(e -> EditSaver(textField,textField1));
        HBox Buttons = new HBox(Save,Exit);
        Buttons.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(User,Password,Buttons);
        StackPane stackPane = new StackPane(vBox);
        Scene scene = new Scene(stackPane,292,550);
        AllStage.setScene(scene);
    }
    public void EditSaver(TextField userName , TextField Password){
        boolean condition = true;
        if(userName.getText().isEmpty()){
            condition = false;
            userName.clear();
            userName.setPromptText("Can`t be empty");
        }
        if(Password.getText().isEmpty()){
            condition = false;
            Password.clear();
            Password.setPromptText("Can`t be empty");
        }
        if (condition){
            admin.userName = userName.getText();
            admin.password= Password.getText();
            //SQL

            StartRunner(admin);
        }

    } //SQL
    public void AddRestaurant(){
        Label nameLabel = new Label("Name: ");
        nameLabel.setAlignment(Pos.CENTER);
        TextField nameField = new TextField();
        nameField.setAlignment(Pos.CENTER);
        nameField.setPromptText("Name");
        HBox Name = new HBox(nameLabel,nameField);
        Name.setAlignment(Pos.CENTER);
        Label LocationLabel = new Label("Location:");
        LocationLabel.setAlignment(Pos.CENTER);
        TextField location = new TextField();
        location.setAlignment(Pos.CENTER);
        location.setPrefWidth(30);
        location.setPromptText("Location");
        Label Error = new Label("");
        HBox Location=new HBox(LocationLabel,location,Error);
        Location.setAlignment(Pos.CENTER);
        Label type = new Label("Food type:");
        CheckBox FastFood = new CheckBox("FastFood");
        CheckBox Iranian = new CheckBox("Iranian");
        CheckBox Kebab = new CheckBox("Kebab");
        CheckBox Salad = new CheckBox("Salad");
        CheckBox International = new CheckBox("International");
        VBox vBox1 = new VBox(FastFood,International,Iranian,Salad,Kebab);
        HBox FoodType = new HBox(type,vBox1);
        FoodType.setAlignment(Pos.CENTER);
        Button Exit = new Button("BACK");
        Button Save = new Button("SAVE");
        Exit.setOnMouseClicked(e -> StartRunner(admin));
        Save.setOnMouseClicked(e->AdminFirstPage.AddRestaurant(nameField,location,FastFood,Iranian,International,Kebab,Salad,Location));
        HBox hBox = new HBox(Exit,Save);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(Name,Location,FoodType,hBox);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox,292,550);
        AllStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}