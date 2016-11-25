/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobelprize;

import java.util.Iterator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.json.JSONObject;

/**
 *
 * @author Peter Cheung
 */

public class NobelPrize{
    
    Button searchButton;
    Button testButton;
    private final Label firstName = new Label("First name:");
    private final Label lastName = new Label("Last name:");
    private final Label fromYear = new Label("From year:");
    private final Label toYear = new Label("To year:");
    private final Label category = new Label("Category:");
    private final Label gender = new Label("Gender:");
    private final Label bornCountry = new Label("Born country:");
    private final Label deathCountry = new Label("Death country:");
    private final Label bornCountryCode = new Label("Born country code:");
    private final Label deathCountryCode = new Label("Death country code:");
    private final Label bornCity = new Label("Born city:");
    private final Label deathCity = new Label("Death city:");
    private final Label birthday = new Label("Birthday: [MM:DD:YYYY]");
    private final Label deathday = new Label("Deathday: [MM:DD:YYYY]");
    private final Label laureateID = new Label("Laureate ID:");
    private final Label result = new Label("");
    
    private final TextField firstNameText = new TextField();
    private final TextField lastNameText = new TextField();
    private final TextField bCityText = new TextField();
    private final TextField dCityText = new TextField();
    private final ComboBox<String> fromYearBox = new ComboBox<>();
    private final ComboBox<String> toYearBox = new ComboBox<>();
    private final ComboBox<String> categoryBox = new ComboBox<>();
    private final ComboBox<String> genderBox = new ComboBox<>();
    private final TextField bCountryText = new TextField();
    private final TextField bCountryCodeText = new TextField();
    private final TextField dCountryText = new TextField();
    private final TextField dCountryCodeText = new TextField();
    private final TextField bCityBox = new TextField();
    private final TextField dCityBox = new TextField();
    private final ComboBox<String> birthDate = new ComboBox<>();
    private final ComboBox<String> birthMonth = new ComboBox<>();
    private final ComboBox<String> birthYear = new ComboBox<>();
    private final ComboBox<String> deathDate = new ComboBox<>();
    private final ComboBox<String> deathMonth = new ComboBox<>();
    private final ComboBox<String> deathYear = new ComboBox<>();
    private final TextField IDText = new TextField();
    
    //@author Steven Letourneau - declaring scene as private global to class here to implement MVC
    private BorderPane border = new BorderPane();
    
    //@author Steven Letourneau - declaring scene as private global to class here to implement MVC
    //Note - removed absolute height and width to allow window to auto fit into parentStage inside of NobelPrizeMain class
    //private Scene scene = new Scene(border, 640, 545);
    private Scene scene = new Scene(border);
          
    //@author Steven Letourneau - initializing NobelPrize "view" using constructor instead of start() method
    public NobelPrize() {
        
        fromYearBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String oldVal, String newVal) {                
                result.setText(fromYearBox.getValue());                
            }    
        });
        
        HBox hbox = addHBox();
        border.setTop(hbox);
        GridPane grid = addGrid();
        border.setLeft(grid);
        VBox vbox = addVBox();
        border.setCenter(vbox);
        
        //@author Steven - this line was commented out when I refactored the class to classical MVC so I left it alone
        //border.getChildren().addAll(fromYearBox, result);
    }

    //@author Steven Letourneau - - returns the built scene to the primaryStage in main 
    //called after "view" is constructed in main as per classical MVC
    public Scene getParentScene() {
        return scene;
    }
    
    public HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #3333FF");
        
        Button backButton = new Button("Back");
        backButton.setPrefSize(60, 20);
        Button generalButton = new Button("All");
        generalButton.setPrefSize(60, 20);
        Button refinedButton = new Button("Refined");
        refinedButton.setPrefSize(60, 20);
        
        hbox.getChildren().addAll(generalButton, refinedButton);
        return hbox;
    }
    
    public GridPane addGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
        //@author Steven - added padding between blue strip and FirstName fields and at bottom of form
        grid.setPadding(new Insets(10, 10, 10, 10));
        
        //@author Steven - changed size on dropdown boxes so they are flush with textboxes (from 80 to 90)
        firstNameText.setPrefSize(80, 20);
        lastNameText.setPrefSize(80, 20);
        bCityText.setPrefSize(80, 20);
        dCityText.setPrefSize(80, 20);
        bCountryText.setPrefSize(80, 20);
        dCountryText.setPrefSize(80, 20);
        bCountryCodeText.setPrefSize(80, 20);
        dCountryCodeText.setPrefSize(80, 20);
        IDText.setPrefSize(80, 20);
        fromYearBox.setPrefSize(90, 20);
        toYearBox.setPrefSize(90, 20);
        genderBox.setPrefSize(90, 20);
        categoryBox.setPrefSize(80, 20);
        birthDate.setPrefSize(90, 20);
        birthMonth.setPrefSize(90, 20);
        birthYear.setPrefSize(90, 20);
        deathDate.setPrefSize(90, 20);
        deathMonth.setPrefSize(90, 20);
        deathYear.setPrefSize(90, 20);        

        grid.add(firstName, 1, 0);
        grid.add(lastName, 1, 1);
        grid.add(gender, 1, 2);
        grid.add(fromYear, 1, 3);
        grid.add(toYear, 1, 4);
        grid.add(bornCountry, 1, 5);
        grid.add(bornCountryCode, 1, 6);
        grid.add(deathCountry, 1, 7);
        grid.add(deathCountryCode, 1, 8);
        grid.add(bornCity, 1, 9);
        grid.add(deathCity, 1, 10);
        grid.add(birthday, 1, 11);
        grid.add(deathday, 1, 12);
        grid.add(laureateID, 1, 13);   
        grid.add(firstNameText, 2, 0);
        grid.add(lastNameText, 2, 1);
        grid.add(genderBox, 2, 2);
        grid.add(fromYearBox, 2, 3);
        grid.add(toYearBox, 2, 4);
        grid.add(bCountryText, 2, 5);
        grid.add(bCountryCodeText, 2, 6);
        grid.add(dCountryText, 2, 7);
        grid.add(dCountryCodeText, 2, 8);
        grid.add(bCityText, 2, 9);
        grid.add(dCityText, 2, 10);
        
        int startYear = 1850;
        for (;startYear < 2017;) {
            String year = String.valueOf(startYear);
            fromYearBox.getItems().add(year);
            toYearBox.getItems().add(year);
            birthYear.getItems().add(year);
            deathYear.getItems().add(year);
            startYear++;
        }
        fromYearBox.setPromptText("Any");
        toYearBox.setPromptText("Any");
        genderBox.getItems().add("Any");
        genderBox.getItems().add("Male");
        genderBox.getItems().add("Female");
        
        for (int i = 1; i < 32; i++) {
            String date = String.valueOf(i);
            birthDate.getItems().add(date);
            deathDate.getItems().add(date);
        }
        for (int i = 1; i < 13; i++) {
            String month = String.valueOf(i);
            birthMonth.getItems().add(month);
            deathMonth.getItems().add(month);
        }
        grid.add(birthMonth, 2, 11);
        grid.add(birthDate, 3, 11);
        grid.add(birthYear, 4, 11);
        grid.add(deathMonth, 2, 12);
        grid.add(deathDate, 3, 12);
        grid.add(deathYear, 4, 12);
        grid.add(IDText, 2, 13);
        
        //@author Steven - added search button and test button for testing, wanted to see if I set up MVC correctly
        searchButton = new Button("SEARCH!");
        searchButton.setPrefSize(90, 30);
        grid.add(searchButton, 2, 15);
        
        testButton = new Button("TEST!");
        testButton.setPrefSize(90, 30);
        grid.add(testButton, 3, 15);
        
        return grid;
    }
    
    public VBox addVBox() {
        VBox vbox = new VBox();
        
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        ListView<String> list = new ListView<>();
        ObservableList<String> data = FXCollections.observableArrayList("Test1", "Test2", "Test3");
        list.setItems(data);
        
        //@author Steven - not sure whether this was commented out before or not - seems to uninhibit height of vbox
        VBox.setVgrow(list, Priority.ALWAYS);
        
        vbox.getChildren().add(list);
        return vbox;
    }
    
    //@author Steven - just testing the MVC and search button, theres probably a better way to add stuff to the VBox already in frame.
    public void toResults(String result) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        border.setCenter(vbox);
        
        ListView<String> list = new ListView<>();
        ObservableList<String> data = FXCollections.observableArrayList(result);
        list.setItems(data);

        VBox.setVgrow(list, Priority.ALWAYS);
        
        vbox.getChildren().add(list);
    }
}