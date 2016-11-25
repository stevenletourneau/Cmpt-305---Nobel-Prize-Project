/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobelprize;

import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Steven Letourneau
 */

//@author Steven - Main class, implements the classical MVC (the "glue" according to Indratmo's PPT)
public class NobelPrizeMain  extends Application {
    
    //@author Steven - overriding start in NobelPrizeMain class now instead of NobelPrize
    @Override
    public void start(Stage primaryStage) {
        Query model = new Query();
        NobelPrize view = new NobelPrize();
        NobelController controller = new NobelController(model, view);
        
        Scene scene = view.getParentScene();
        primaryStage.setTitle("Nobel Prize Quest");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
     public static void main(String[] args) {
        launch(args);
    }       
}
