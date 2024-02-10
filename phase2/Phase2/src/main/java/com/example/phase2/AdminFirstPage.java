package com.example.phase2;

import ObjectClasses.Restuarants;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.EventListener;
import java.util.TreeSet;

public class AdminFirstPage {
    public static BorderPane RestaurantList(BorderPane borderPane){
        Restuarants[] restuarantlist = new Restuarants[0];
        //SQL //یه لیست از رستورانای این ادمین


        if(restuarantlist.length==0){
            Label label = new Label("No restaurant have been registered yet.");
            borderPane.setCenter(label);
        }
        else {

            ListView<String> stringListView = new ListView<>();
            stringListView.setOrientation(Orientation.VERTICAL);
            stringListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            for (int i = 0 ; i < restuarantlist.length ; i++){
                stringListView.getItems().add("Restaurant "+ restuarantlist[i].name);
            }
            stringListView.getSelectionModel().selectedItemProperty().addListener((var) -> RestaurantPage.RestaurantMainPage(Start.AllStage,Start.admin,restuarantlist[stringListView.getSelectionModel().getSelectedIndex()]));
            ScrollPane scrollPane = new ScrollPane(stringListView);
            borderPane.setCenter(scrollPane);
        }
        return borderPane;
    } //SQL
    public static void AddRestaurant(TextField Name , TextField location , CheckBox FastFood, CheckBox Iranian , CheckBox International , CheckBox Kebab , CheckBox Salad, HBox Location) {
        boolean condition = false;
        if (Name.getText().isEmpty()) {
            condition = true;
            Name.clear();
            Name.setPromptText("Can`t be empty");
        }
        try {
            if (Integer.parseInt(location.getText()) < 0 || Integer.parseInt(location.getText()) > 1000) {
                location.clear();
                location.setPromptText("Must be between 0 and 1000");
                condition = true;
            }
        } catch (Exception e) {
            location.clear();
            location.setPromptText("Must be number");
            condition = true;
        }
        TreeSet<String> FoodType1 = new TreeSet<>();
        if (FastFood.isSelected())
            FoodType1.add("FastFood");
        if (Iranian.isSelected())
            FoodType1.add("Iranian");
        if (Kebab.isSelected())
            FoodType1.add("Kebab");
        if (Salad.isSelected())
            FoodType1.add("Salad");
        if (International.isSelected())
            FoodType1.add("International");
        if (!condition) {
            Restuarants restuarants = new Restuarants(Name.getText(), location.getText(), FoodType1);
            //SQL//

            try {
                Start a = new Start();
                a.StartRunner(Start.admin);
            } catch (Exception e) {}
        }
    }
}