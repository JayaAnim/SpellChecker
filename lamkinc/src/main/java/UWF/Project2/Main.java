package UWF.Project2;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
    	try {
	    	HashTable hash = new HashTable();
	    	
	    	primaryStage.setTitle("Spell Checker"); // create primary stage title
	    	
	        Menu menu1 = new Menu("File"); // Create menu bar
	        MenuItem menuItem1 = new MenuItem("Open");
	        MenuItem menuItem2 = new MenuItem("Save");
	        MenuItem menuItem3 = new MenuItem("Exit");
	        menu1.getItems().add(menuItem1);
	        menu1.getItems().add(menuItem2);
	        menu1.getItems().add(menuItem3);
	        Menu menu2 = new Menu("Edit");
	        MenuItem menuItem4 = new MenuItem("Spell Check");
	        menu2.getItems().add(menuItem4);
	        MenuBar menuBar = new MenuBar();
	        menuBar.getMenus().add(menu1);
	        menuBar.getMenus().add(menu2);
	        
	        TextArea textArea = new TextArea(); // Create text area
	        textArea.setWrapText(true);
	        textArea.setPrefHeight(500);
	        
	        VBox vbox = new VBox(menuBar); // create vbox and add items
	        vbox.getChildren().add(textArea);
	        
	        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent event) {
	        		try {
	            		FileManager obj = new FileManager("content.txt");
						textArea.setText(obj.readFile());
					} catch (Exception e) {
						e.printStackTrace();
					}
	        		
	        	}
	        });
	        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent event) {
	        		try {
						FileManager obj = new FileManager("content.txt");
						String data = textArea.getText();
						obj.editFile(data);
					} catch (Exception e) {
						e.printStackTrace();
					}
	        		
	        	}
	        });
	        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent event) {
	        		primaryStage.close();
	        	}
	        });
	        menuItem4.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent event) {
	        		
	        		String suggestions = "";
	        		String str = textArea.getText();
	        		if (!str.equals("")) {
		    		    String[] split = str.split("[ !.,]+");
		    		    for (int i = 0; i < split.length; ++i) {
		    		    	if (!hash.contains(split[i])) {
		    		    		suggestions += "\nSuggestions for " + split[i] + ":" + hash.suggestions(split[i]);
		    		    	}
		    		    }
	        		}
	        		if (suggestions.equals("")) {
	        			suggestions = "No suggestions";
	        		}
	    		    
	    		      Dialog<String> dialog = new Dialog<String>();
	    		      dialog.setTitle("Spelling Suggestions");
	    		      ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
	    		      dialog.setContentText(suggestions);
	    		      dialog.getDialogPane().getButtonTypes().add(type);
	    		      dialog.showAndWait();
	        	}
	        });
	        
	        Scene scene = new Scene(vbox, 400, 500); // add vbox to scene
	        
	        primaryStage.setScene(scene); // Add vbox to the scene and show scene
	        primaryStage.show();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
	public static void main(String[] args) {
		launch(args);
	}
}
