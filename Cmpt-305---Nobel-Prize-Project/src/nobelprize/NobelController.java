/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobelprize;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Steven Letourneau
 */

//@author Steven - Controller class. Will contain all of the necessary event handler methods.
public final class NobelController {
    private final Query modelQuery;
    private final NobelPrize gui;
    
    public NobelController(Query modelQuery, NobelPrize gui) {
        this.gui = gui;
        this.modelQuery = modelQuery;
        handlerContainer();
    }
    
    //update methods
    public void handlerContainer() {
        gui.searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gui.toResults("Testing the search button!");
            }
        });
        
        gui.testButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gui.toResults("Testing the test button!");
            }
        });
    }
}
