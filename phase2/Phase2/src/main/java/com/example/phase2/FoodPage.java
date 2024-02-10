package com.example.phase2;

import ObjectClasses.Comment;
import ObjectClasses.Food;
import ObjectClasses.Restuarants;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class FoodPage {
    public static Food food;
    public static Restuarants restuarants;
    public static Admin admin;
    public static Stage SuperStage;
    public static void FoodMainPageRunner(Food food1, Restuarants restuarants1, Admin admin1 , Stage stage){
        food = food1;
        restuarants = restuarants1;
        admin = admin1;
        SuperStage=stage;
        try {
            FoodMainPage();
        }catch (Exception e){}
    }
    public static void FoodMainPage () throws IOException {
        Label label = new Label("Food: "+ food.name);
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(292,80);
        label.setContentDisplay(ContentDisplay.CENTER);
        label.setFont(Font.font(40));
        GridPane gridPane = new GridPane();
        Label label1 = new Label("Price: "+String.valueOf(food.price));
        Label label2 = new Label("Discount: "+String.valueOf(food.discount));
        Label label3 = new Label();
        if(food.ratings.size()==0)
            label3.setText("No rating yet");
        else
            label3 = new Label("Rating: "+ food.ratings.toString());
        Button button2 = new Button("Edit food");
        button2.setOnMouseClicked(e -> EditFoodPage());
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPrefSize(292,120);
        gridPane.add(label1,0,0);
        gridPane.add(label2,0,1);
        gridPane.add(label3,0,2);
        gridPane.add(button2,2,2);
        VBox vBox = new VBox(label,gridPane);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(292,200);
        Button button = new Button("Back");
        button.setOnMouseClicked(e-> RestaurantPage.RestaurantMainPage(SuperStage,admin,restuarants));
        HBox hBox = new HBox(button);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefSize(292,30);
        BorderPane borderPane1 = new BorderPane();
        borderPane1.setTop(new Label("Comments:"));
        ListView<HBox> listView = new ListView<>();
        ScrollPane scrollPane = new ScrollPane(listView);
        //SQL

        //یه لیست از کامنت هایی که اون غذا داره
        Comment[] comments = new Comment[2];
        comments[0] = new Comment(null,"comment1",restuarants,food);
        comments[1] = new Comment(null,"comment2",restuarants,food);
        for (int i = 0 ; i < comments.length;i++){
            Label label4 = new Label(comments[i].comment);
            if(comments[i].Answer.equals("")) {
                Button button1 = new Button("Add answer");
                int x = i;
                button1.setOnMouseClicked(e -> AddAnswer(comments[x]));
                HBox hBox1 = new HBox(label4, button1);
                listView.getItems().add(hBox1);
            }
            else {
                TextArea textArea = new TextArea(comments[i].Answer);
                HBox hBox1 =new HBox(label4,textArea);
                listView.getItems().add(hBox1);
            }
        }
        borderPane1.setCenter(scrollPane);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(vBox);
        borderPane.setBottom(hBox);
        borderPane.setCenter(borderPane1);
        Scene scene = new Scene(borderPane,292,550);
        SuperStage.setScene(scene);
    }//SQL
    public static void EditFoodPage(){
        Label label = new Label("Name: ");
        TextField textField = new TextField();
        textField.setPromptText("Name");
        HBox name = new HBox(label,textField);
        name.setAlignment(Pos.CENTER);
        Label label1 = new Label("Price: ");
        TextField textField1 = new TextField();
        textField1.setPromptText("Price");
        HBox Price = new HBox(label1,textField1);
        Price.setAlignment(Pos.CENTER);
        Label label2 = new Label("Discount: ");
        TextField textField2 = new TextField();
        textField2.setPromptText("Discount");
        HBox Discount = new HBox(label2,textField2);
        Discount.setAlignment(Pos.CENTER);
        Button button = new Button("Back");
        button.setOnMouseClicked(e -> FoodMainPageRunner(food,restuarants,admin,SuperStage));
        Button button1 = new Button("Save");
        button1.setOnMouseClicked(e -> Editor(textField,name,textField1,Price,textField2,Discount));
        HBox hBox = new HBox(button,button1);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(name,Price,Discount,hBox);
        vBox.setAlignment(Pos.CENTER);
        StackPane stackPane = new StackPane(vBox);
        Scene scene = new Scene(stackPane,292,550);
        SuperStage.setScene(scene);
    }
    public static void Editor(TextField name,HBox Name ,TextField Price ,HBox price,TextField Discount,HBox discount){
        boolean condition = true;
        if (name.getText().equals("")){
            Label label = new Label("Can`t be empty");
            Name.getChildren().add(label);
            condition=false;
        }
        else
            food.name = name.getText();
        try{
            food.price = Integer.parseInt(Price.getText());
        }catch (Exception e){
            Label label = new Label("Please enter numbers only");
            price.getChildren().add(label);
            Price.clear();
            condition=false;
        }
        try{
            if(Integer.parseInt(Discount.getText())>=0&&Integer.parseInt(Discount.getText())<100)
                food.discount = Integer.parseInt(Discount.getText());
            else {
                Label label = new Label("Number must be between 0 and 100");
                discount.getChildren().add(label);
                Discount.clear();
                condition=false;
            }
        }catch (Exception e){
            Label label = new Label("Please enter numbers only");
            discount.getChildren().add(label);
            Discount.clear();
            condition=false;
        }
        if (condition) {
            //SQL CHANGE
            FoodMainPageRunner(food, restuarants, admin, SuperStage);
        }
    }//SQL
    public static void AddAnswer(Comment comment){

        BorderPane borderPane = new BorderPane();
        Label label = new Label(comment.comment);
        label.setPrefSize(292,200);
        label.setAlignment(Pos.TOP_LEFT);
        borderPane.setTop(label);
        TextField textField = new TextField();
        textField.setPromptText("Write your answer");
        textField.setAlignment(Pos.TOP_LEFT);
        textField.setPrefSize(250,200);
        BorderPane borderPane1 = new BorderPane();
        Label label1=new Label("Answer:");
        label1.setAlignment(Pos.BOTTOM_LEFT);
        borderPane1.setTop(label1);
        borderPane1.setCenter(textField);
        Button button = new Button("Back");
        button.setOnMouseClicked(e -> FoodMainPageRunner(food,restuarants,admin,SuperStage));
        Button button1 = new Button("Save");
        button1.setOnMouseClicked(e -> AnswerChanger(textField,comment));
        HBox hBox = new HBox(button,button1);
        hBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(borderPane1);
        borderPane.setBottom(hBox);
        Scene scene = new Scene(borderPane,292,550);
        SuperStage.setScene(scene);
    }
    public static void AnswerChanger(TextField answer,Comment comment){
        if(answer.getText().equals("")){
            answer.setPromptText("Can`t be empty");
        }
        else {
            comment.Answer = answer.getText();
            //sql change
            FoodMainPageRunner(food,restuarants,admin,SuperStage);
        }
    }//SQL
}
