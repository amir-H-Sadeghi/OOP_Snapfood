package com.example.phase2;

import ObjectClasses.Comment;
import ObjectClasses.Food;
import ObjectClasses.Orders;
import ObjectClasses.Restuarants;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.TreeSet;

public class RestaurantPage {
    public static Stage SuperStage;
    public static Admin admin;
    public static Restuarants restuarants;
    public static void RestaurantMainPage(Stage stage , Admin admin1 ,Restuarants restuarants1){
        SuperStage = stage;
        admin = admin1;
        restuarants = restuarants1;



        BorderPane borderPane = new BorderPane();
        Label label1 = new Label("Restaurant "+restuarants.name);
        label1.setFont(Font.font(30));
        label1.setAlignment(Pos.CENTER);
        label1.setContentDisplay(ContentDisplay.CENTER);
        label1.setPrefSize(292,50);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPrefSize(292,150);
        Label label2 = new Label("Location "+restuarants.Location);
        Label label3 = new Label();
        if(restuarants.FoodType==null)
            label3.setText("No food type registered");
        else
            label3.setText("FoodType: "+restuarants.FoodType.toString());
        Button button = new Button("Edit Restaurant");
        button.setOnMouseClicked(e -> EditRestaurant());
        Button button1 = new Button("Orders");
        button1.setOnMouseClicked(e -> Orders());
        HBox hBox1 = new HBox(button,button1);
        hBox1.setAlignment(Pos.CENTER);
        Label Rating = new Label();
        if(restuarants.ratings.size()==0){
            Rating.setText("No Rating yet");
        }
        else
            Rating.setText("Rating: " + restuarants.ratings.toString());
        gridPane.add(label2,0,0);
        gridPane.add(label3,0,1);
        gridPane.add(hBox1,2,2);
        gridPane.add(Rating,0,2);
        VBox vBox = new VBox(label1,gridPane);
        vBox.setPrefSize(292,200);
        Button button2 = new Button("Back");
        button2.setOnMouseClicked(e -> new Start().StartRunner(admin));
        Button button3 = new Button("Add Food");
        button3.setOnMouseClicked(e -> AddFood());
        Button button4 = new Button("Comments");
        button4.setOnMouseClicked(e -> Comments());
        HBox hBox = new HBox(button2,button3,button4);
        hBox.setAlignment(Pos.CENTER);
        ListView<String> FoodList = new ListView<>();
        BorderPane borderPane1 = new BorderPane();
        Label label = new Label("Food list:");
        borderPane1.setTop(label);
        ScrollPane scrollPane = new ScrollPane(FoodList);
        borderPane1.setCenter(scrollPane);
        borderPane.setTop(vBox);
        borderPane.setBottom(hBox);
        borderPane.setCenter(borderPane1);


        //SQL //یه لیست از غذاهایی که اون رستوران داره
        Food[] foodlist = new Food[2];
        foodlist[0] = new Food("sholeh",1000,1,0);
        foodlist[1] = new Food("sholeh2",1000,1,0);
        for(int i = 0 ; i < foodlist.length ; i++){
            FoodList.getItems().add(foodlist[i].name);
        }
        FoodList.getSelectionModel().selectedItemProperty().addListener((var) -> FoodPage.FoodMainPageRunner(foodlist[FoodList.getSelectionModel().getSelectedIndex()], restuarants, admin, SuperStage));
        Scene scene = new Scene(borderPane,292,550);
        SuperStage.setScene(scene);
    }//SQL
    public static void AddFood(){
        BorderPane borderPane = new BorderPane();
        Label label = new Label("ADD FOOD");
        label.setPrefSize(292,100);
        label.setContentDisplay(ContentDisplay.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font(40));
        borderPane.setTop(label);
        Label name = new Label("Name: ");
        TextField textField = new TextField();
        textField.setPromptText("Name");
        HBox Name = new HBox(name,textField);
        Name.setAlignment(Pos.CENTER);
        Label Price = new Label("Price: ");
        TextField textField1 = new TextField();
        textField1.setPromptText("Price");
        HBox price = new HBox(Price,textField1);
        price.setAlignment(Pos.CENTER);
        Label discount = new Label("Discount: ");
        TextField textField2 = new TextField();
        textField2.setPromptText("Discount");
        HBox Discount = new HBox(discount,textField2);
        Discount.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(Name,price,Discount);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);
        Button button = new Button("Back");
        button.setOnMouseClicked(e -> RestaurantMainPage(SuperStage,admin,restuarants));
        Button button1=new Button("Save");
        button1.setOnMouseClicked(e -> FoodAdder(textField,textField1,textField2,Name,price,Discount));
        HBox hBox = new HBox(button,button1);
        hBox.setAlignment(Pos.CENTER);
        borderPane.setBottom(hBox);
        Scene scene = new Scene(borderPane,292,550);
        SuperStage.setScene(scene);
    }
    public static void FoodAdder(TextField name , TextField price , TextField discount , HBox Name , HBox Price , HBox Discount){
        boolean condition =  true;
        if(name.getText().isEmpty()){
            name.clear();
            name.setPromptText("Can`t be empty");
            condition= false;
        }
        try {
            if(Integer.parseInt(price.getText())<1){
                price.clear();
                price.setPromptText("Can`t be negative");
                condition = false;
            }
        }catch (Exception e){
            price.clear();
            price.setPromptText("Enter just numbers");
            condition = false;
        }
        try {
            if(Integer.parseInt(discount.getText())<0||Integer.parseInt(discount.getText())>100){
                discount.clear();
                discount.setPromptText("Discount must be between 0 and 100");
                condition = false;
            }
        }catch (Exception e){
            discount.clear();
            discount.setPromptText("Enter just numbers");
            condition = false;
        }
        if(condition){
            Food food = new Food(name.getText(),Integer.parseInt(price.getText()),0,Integer.parseInt(discount.getText()));
            //Sql

            RestaurantMainPage(SuperStage,admin,restuarants);
        }
    }//SQL


    public static void EditRestaurant(){
        BorderPane borderPane = new BorderPane();
        Label label = new Label("EDIT RESTAURANT");
        label.setFont(Font.font(20));
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(292,100);
        label.setContentDisplay(ContentDisplay.CENTER);
        borderPane.setTop(label);
        Label name = new Label("Name: ");
        TextField textField = new TextField();
        textField.setPromptText("Name");
        HBox Name = new HBox(name,textField);
        Name.setAlignment(Pos.CENTER);
        Label location = new Label("Location: ");
        TextField textField1 = new TextField();
        textField1.setPromptText("Location");
        HBox Location = new HBox(location,textField1);
        Location.setAlignment(Pos.CENTER);
        Label foodtype = new Label("FoodType: ");
        CheckBox Iranian = new CheckBox("Iranian");
        CheckBox FastFood = new CheckBox("FastFood");
        CheckBox International = new CheckBox("International");
        CheckBox Salad = new CheckBox("Salad");
        CheckBox Kebab = new CheckBox("Kebab");
        VBox vBox1 = new VBox(FastFood,International,Iranian,Kebab,Salad);
        HBox FoodType = new HBox(foodtype,vBox1);
        FoodType.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(Name,Location,FoodType);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);
        Button button = new Button("Back");
        button.setOnMouseClicked(e -> RestaurantMainPage(SuperStage,admin,restuarants));
        Button button1 = new Button("Save");
        button1.setOnMouseClicked(e -> RestaurantEditor(textField,textField1,FastFood,International,Salad,Iranian,Kebab,vBox1));
        HBox hBox = new HBox(button,button1);
        hBox.setAlignment(Pos.CENTER);
        borderPane.setBottom(hBox);
        Scene scene = new Scene(borderPane,292,550);
        SuperStage.setScene(scene);
    }
    public static void RestaurantEditor(TextField name , TextField Location , CheckBox Fastfood , CheckBox International , CheckBox Salad , CheckBox Iranian , CheckBox Kebab,VBox vBox){
        boolean condition = true;
        if(name.getText().isEmpty()){
            name.clear();
            name.setPromptText("Can`t be empty");
            condition = false;
        }
        if(Location.getText().isEmpty()){
            Location.clear();
            Location.setPromptText("Can`t be empty");
            condition = false;
        }
        if(!Fastfood.isSelected()&&!International.isSelected()&&!Iranian.isSelected()&&!Salad.isSelected()&&!Kebab.isSelected()){
            Label label = new Label("One Food type should be checked");
            vBox.getChildren().add(label);
            condition = false;
        }
        if (condition){
            //SQL


            TreeSet<String> types = new TreeSet<>();
            restuarants.name = name.getText();
            if(Fastfood.isSelected())
                types.add("FastFood");
            if(International.isSelected())
                types.add("International");
            if(Iranian.isSelected())
                types.add("Iranian");
            if(Salad.isSelected())
                types.add("Salad");
            if(Kebab.isSelected())
                types.add("Kebab");
            restuarants.FoodType = types;
            RestaurantMainPage(SuperStage,admin,restuarants);
        }
    }
    //SQL
    public static void Comments(){
        Comment[] comments = new Comment[2];
        comments[0] = new Comment(null,"ali",restuarants);
        comments[1] = new Comment(null,"ali2",restuarants);
        //SQL receive// لیست کامنت های مربوط به این رستوران
        BorderPane borderPane = new BorderPane();

        Label label = new Label("Comments");
        label.setFont(Font.font(30));
        label.setAlignment(Pos.CENTER);
        label.setContentDisplay(ContentDisplay.CENTER);
        borderPane.setTop(label);
        if(comments.length==0){
            Label label1 = new Label("No comments yet");
            borderPane.setCenter(label1);
        }
        else {
            ListView<VBox> listView = new ListView<>();
            listView.setPrefSize(270,400);
            for (int i = 0 ; i < comments.length ; i++){
                Label label1 = new Label(comments[i].comment);
                if(comments[i].Answer==null) {
                    Button button = new Button("Add answer");
                    VBox vBox = new VBox(label1,button);
                    int x = i;
                    button.setOnMouseClicked(e -> AddAnswer(vBox,comments[x]));
                    listView.getItems().add(vBox);
                }
                else {
                    Label label3 = new Label(comments[i].comment);
                    Label label2 = new Label("Answer:");
                    VBox vBox = new VBox(label1,label2,label3);
                    listView.getItems().add(vBox);
                }
            }
            ScrollPane scrollPane = new ScrollPane(listView);
            borderPane.setCenter(scrollPane);
        }
        Button button = new Button("Back");
        button.setOnMouseClicked(e -> RestaurantMainPage(SuperStage,admin,restuarants));
        borderPane.setBottom(button);

        Scene scene = new Scene(borderPane,292,550);
        SuperStage.setScene(scene);
    }//SQL
    public static void AddAnswer(VBox vBox,Comment comment){
        TextField textField = new TextField();
        textField.setPromptText("Answer");
        vBox.getChildren().remove(1);
        vBox.getChildren().add(textField);
        Button button = new Button("Save");
        button.setOnMouseClicked(e -> AnswerAdder(textField,comment));
        vBox.getChildren().add(button);
    }
    public static void AnswerAdder(TextField textField,Comment comment){
        if(textField.getText().isEmpty()) {
            textField.clear();
            textField.setPromptText("Can`t be empty");
        }
        else {
            //SQL ADD

            comment.Answer = textField.getText();
            RestaurantMainPage(SuperStage,admin,restuarants);
        }
    }//SQL

    public static void Orders(){
        Orders[] orders = new Orders[2];
        orders[0] = new Orders(restuarants,new Food("Sholleh",1000,1,10),null);
        orders[1] = new Orders(restuarants,new Food("Sholleh2",2000,2,30),null);
        //SQL receive// لیست سفارش هایی که اون رستوران تا حالا داشته

        BorderPane borderPane = new BorderPane();
        Label label = new Label("Orders");
        label.setFont(Font.font(30));
        label.setAlignment(Pos.CENTER);
        label.setContentDisplay(ContentDisplay.CENTER);
        label.setPrefSize(292,100);
        borderPane.setTop(label);
        if(orders.length==0){
            Label label1 = new Label("No orders registered yet");
            label1.setPrefWidth(292);
            label1.setPrefHeight(400);
            label1.setFont(Font.font(20));
            label1.setContentDisplay(ContentDisplay.CENTER);
            label1.setAlignment(Pos.CENTER);
            borderPane.setCenter(label1);
        }
        else {
            ListView<VBox> listView = new ListView<>();
            for (int i = 0 ; i < orders.length ; i++){
                Label label1 = new Label(orders[i].food.name);
                label1.setFont(Font.font(15));
                Label label2 = new Label("Price: "+orders[i].food.price);
                Label label3 = new Label("Discount: "+orders[i].food.discount);
                VBox vBox = new VBox(label1,label2,label3);
                listView.getItems().add(vBox);
            }
            ScrollPane scrollPane = new ScrollPane(listView);
            borderPane.setCenter(scrollPane);
        }
        Button button = new Button("Back");
        button.setOnMouseClicked(e -> RestaurantMainPage(SuperStage,admin,restuarants));
        HBox hBox = new HBox(button);
        hBox.setPrefSize(292,50);
        hBox.setAlignment(Pos.CENTER);
        borderPane.setBottom(hBox);
        Scene scene = new Scene(borderPane);
        SuperStage.setScene(scene);
    }//SQL
}
