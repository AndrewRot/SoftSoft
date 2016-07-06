package CustomApp;

import javafx.application.Application;
import javafx.scene.layout.Pane;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

//import org.farng.mp3.MP3File;
//import org.farng.mp3.TagException;
//import org.farng.mp3.id3.ID3v1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Callback;
import stageElement.ComboBoxElement;
import stageElement.LabelElement;
import stageElement.StageElement;
import stageElement.TextFieldElement;
import javafx.application.Application;




public class CustomApp extends Application{
	
	Pane root;
	Pane menuBar;
	Pane propertyPane; 
	int width = 1100, height = 750;
	boolean itemSelected = false;
	/**Different keys turn on different modes
	 * E - Edit mode - edit the properties of an obj
	 * M - Move mode - move stage elements around the screen
	 * D - Delete mode - click on objects to delete them
	 * I - Interact mode - interact with stage objects
	 * Q - Exit all modes
	 */
	boolean editMode = false;
	boolean moveMode = true;
	boolean deleteMode = false;
	boolean interactMode = false;
	final boolean quitMode = false;
	
	Label mode = new Label("moveMode");
	
	Label modeE = new Label("editMode");
	Label modeM = new Label("moveMode");
	Label modeD = new Label("delMode");
	Label modeI = new Label("IntMode");
	
	
	
	Scene scene;

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception{
		
		
		root = new Pane();
		
		scene = new Scene(root, width, height);

		File iconFile = new File("iDJGraphics/tempIcon.png");
        Image iconImage = new Image(iconFile.toURI().toString());
		primaryStage.getIcons().add(iconImage);
		primaryStage.setTitle("Custom Software");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//Temp for pictures***************
		Label temp = new Label("Label");
		temp.setLayoutX(200);
		temp.setLayoutY(200);
		
		TextField temp2 = new TextField("Enter text...");
		temp2.setLayoutX(200);
		temp2.setLayoutY(300);
		//temp
		
		//root.getChildren().addAll(temp, temp2);
		
		//test different actions //temp2 - only when temp2 is slected
		//root is always listening
		/**Different keys turn on different modes
		 * E - Edit mode - edit the properties of an obj
		 * M - Move mode - move stage elements around the screen
		 * D - Delete mode - click on objects to delete them
		 * I - Interact mode - interact with stage objects
		 * Q - Exit all modes
		 * 
		 * Change the mouse to display which mode youre in - 
		 * or change a picture on screen
		 */
		root.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent ke) {
	        	//
	            if (ke.getCode().equals(KeyCode.E)) {
	            	changeMode("E");
	            }
	            if (ke.getCode().equals(KeyCode.M)) {
	            	changeMode("M");
	            }
	            if (ke.getCode().equals(KeyCode.D)) {
	            	changeMode("D");
	            }
	            if (ke.getCode().equals(KeyCode.I)) {
	            	changeMode("I");
	            }
	            if (ke.getCode().equals(KeyCode.Q)) {
	            	changeMode("Q");
	            }
	            
	        }
	    });
		
		scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
			
			if(event.getCode().equals(KeyCode.ESCAPE))
				propertyPane.getChildren().clear();
			
			if (event.getCode().equals(KeyCode.E)) {
				changeMode("E");
				//add to all others but moveMode?
				//itemSelected = false;
			}
			if (event.getCode().equals(KeyCode.M)) {
				changeMode("M");
			}
			if (event.getCode().equals(KeyCode.D)) {
				changeMode("D");
			}
			if (event.getCode().equals(KeyCode.I)) {
				changeMode("I");
			}
			if (event.getCode().equals(KeyCode.Q)) {
				changeMode("Q");
			}
		        
		    
		});
		
		//***********************
		
		mode.setLayoutX(20);
		mode.setLayoutY(height-40);
		mode.setTextFill(Color.WHITE);
		
		//Modemap
		modeE.setLayoutX(20);
		modeE.setLayoutY(height-50);
		modeE.setTextFill(Color.WHITE);
		modeM.setLayoutX(20);
		modeM.setLayoutY(height-60);
		modeM.setTextFill(Color.WHITE);
		modeD.setLayoutX(20);
		modeD.setLayoutY(height-70);
		modeD.setTextFill(Color.WHITE);
		modeI.setLayoutX(20);
		modeI.setLayoutY(height-80);
		modeI.setTextFill(Color.WHITE);
		
		
		menuBar = new Pane();
		menuBar.setStyle("-fx-background-color: #ff6600");
		menuBar.setMinWidth(width);
		menuBar.setMinHeight(70);
		menuBar.relocate(0, 0);
		
		//customize stage elements in this pane
		propertyPane = new Pane();
		propertyPane.setStyle("-fx-background-color: #333333");
		int propertyPaneHeight = height-70, propertyPaneWidth = 200;
		propertyPane.setMinHeight(propertyPaneHeight);
		propertyPane.setMinWidth(propertyPaneWidth);
		propertyPane.relocate(0, height-propertyPaneHeight);
		root.getChildren().addAll(propertyPane, menuBar, modeE
				, modeM, modeD, modeI);
		
		final ComboBox priorityComboBox = new ComboBox();
        priorityComboBox.getItems().addAll(
        		"Select Element",
        		"ComboBox",
        		"Static Text",
        		"Input Text"
        );		   
        priorityComboBox.setValue("Select Element");
        priorityComboBox.relocate(10, 20);
        priorityComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
              //ov, obj prop value, t - prev item, t1 - new item
                itemSelected = true;
                createStageElement(t1);
            }

        });
        
        menuBar.getChildren().add(priorityComboBox);
        
        //mouse clicked actions
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(itemSelected);
                	//itemSelected = false;
            }
        });
        temp2.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	//if(itemSelected){
            		//System.out.println("MouseX: " + event.getSceneX());
            		//System.out.println("MouseY: " + event.getSceneY());
            		//temp2.setLayoutX(event.getSceneX()-25);
            		//temp2.setLayoutY(event.getSceneY()-25);
            	//}
            }
        });
        
	
	}
	
	//TRY TO PUT ACTUAL ELEMENTS ON STAGE in stageELement class
	//add actions to the stage object if possible.
	//make hot keys, m and click for move, p and click for properties
	//just a click = just regular interaction
	
	//Have to make elements selectable for both interactive objects
	//and for static objects --**** made edit panel on the left
	
	//see if stage elements can move with mouse -YES
	
	//Generic stage object - use a Node

	//Based on the item selected, create new stage element and 
	//attach it to the mouse
	private void createStageElement(String t1) {
		changeMode("M");
		Node newObj;
		StageElement newElement;
		//Change to a factory 
		
		if(t1.equals("ComboBox")){
			newObj = new ComboBox<String>();
			
	        LinkedList<String> options = new LinkedList<String>();
	        options.add("Insert Entries");
	        options.add("Insert Entries");
	        options.add("Insert Entries");
	        
	        newElement = new ComboBoxElement((ComboBox) newObj, 100, 100, "code", options);
		}
		else if(t1.equals("Static Text")){
			newObj = new Label("New Label");
	        newElement = new LabelElement((Label) newObj, 200, 100, "code");
		}
		else if(t1.equals("Input Text")){
			newObj = new TextField("Enter Text");
	        newElement = new TextFieldElement((TextField) newObj, 200, 100, "code");
		}
		else{
			newObj = new ComboBox<String>();
			
	        LinkedList<String> options = new LinkedList<String>();
	        options.add("Insert Entries");
	        options.add("Insert Entries");
	        options.add("Insert Entries");
	        newElement = new ComboBoxElement((ComboBox) newObj, 100, 100, "code", options);
		}
			
		
       
		//moverPane.getChildren().removeAll();
		root.getChildren().add(newElement.getElement());
		newElement.getElement().setLayoutX(130);
		newElement.getElement().setLayoutY(100);
		itemSelected = true;
		
		newElement.getElement().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	if(editMode == true){
            		//itemSelected = false;//?
            		Pane newEditPane; // = newElement.displayEditOptions(propertyPane, newElement.getElement());
            		//MAKE displayedit options for each element in the main? need switch statement 
            		//to match the object to the correct method.. this rather than putting
            		//the code in each different class.. sucks but here the interactable
            		//objects in the edit panel would actual be able to live interact.
            		
            		
            		
            		//Add type checking :( - in order to have layers of interactable objects
            		if(t1.equals("ComboBox"))
            			updatedComboEditPane(newElement);
            		else if(t1.equals("Static Text"))
            			updatedLabelEditPane((Label) newElement.getElement());
            		
            		//replaceEditPane(newEditPane);
            		
            	}
            	if(moveMode == true){
            		if(itemSelected){
                		itemSelected = false;
                		System.out.println("Move Enabled");
                	}
                	else if(!itemSelected){
                		System.out.println("Move disabled");
                		itemSelected = true;
                	}
            	}
            	if(deleteMode == true){
            		//delete actual obj too
            		
            		root.getChildren().remove(newElement.getElement());
            	}
            	if(interactMode == true){
            		propertyPane.getChildren().clear();
            	}
            		
            	/*if(itemSelected){
            		itemSelected = false;
            		System.out.println("NewRect Enabled");
            	}//need to think of something better...
            	else if(!itemSelected){
            		System.out.println("NewRect disabled");
            		itemSelected = true;
            	}*/
            }

            
            //If this is left inside what what happen?
			private void updatedLabelEditPane(Label l) {
				VBox EditBox = new VBox(10);
				
				Label EditPaneName = new Label("Label Options");
				EditPaneName.setTextFill(Color.WHITE);
				
				TextField text = new TextField("");
				text.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
					l.setText(text.getText());
				});
				Label Size = new Label("Font Size");
				Size.setTextFill(Color.WHITE);
				
				TextField sizetext = new TextField("");
				sizetext.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
					//also check for letters here and empty string
					if(!event.getCode().equals("")){ //make sure there is something in the text before resizing
						double size = Double.parseDouble(sizetext.getText());
						l.setScaleX(size);
						l.setScaleY(size);
					}
				});
				
				EditBox.getChildren().addAll(EditPaneName, text, Size, sizetext);
				propertyPane.getChildren().clear();
				propertyPane.getChildren().addAll(EditBox);
				
			}

			

			
        });
		
		newElement.getElement().setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	if(itemSelected){
            		newElement.getElement().setLayoutX(event.getSceneX()-(.5*newElement.getElement().getBoundsInLocal().getWidth()));
            		newElement.getElement().setLayoutY(event.getSceneY()-(.5*newElement.getElement().getBoundsInLocal().getHeight()));
	            	
            	}
            }
        });
		
		//NEW CODE - this updates things
		scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
			if (event.getCode().equals(KeyCode.ENTER)) {
				//Pane newEditPane = newElement.displayEditOptions(propertyPane, newElement.getElement());
        		//replaceEditPane(newEditPane);
				updatedComboEditPane(newElement);
        	}
		});	
	}
	
	
	
	
	//turn off all other modes, and then turn on the passed mode
	private void changeMode(String modeName) {
		if(modeName.equals("E")){
			editMode = true;
			modeE.setText("editMode = True");
		}
		else{
			editMode = false;
			modeE.setText("editMode = false");
		}
		if(modeName.equals("M")){
			moveMode = true;
			modeM.setText("moveMode = True");
		}
		else{
			moveMode = false;
			modeM.setText("moveMode = false");
		}
		if(modeName.equals("D")){
			deleteMode = true;
			modeD.setText("deleteMode = True");
		}
		else{
			deleteMode = false;
			modeD.setText("deleteMode = false");
		}
		if(modeName.equals("I")){
			interactMode = true;
			modeI.setText("interactMode = True");
		}
		else{
			interactMode = false;
			modeI.setText("interactMode = false");
		}
		if(modeName.equals("Q")){
			editMode = false;
			moveMode = false;
			deleteMode = false;
			interactMode = false;
			modeE.setText("editMode = false");
			modeM.setText("moveMode = false");
			modeD.setText("deleteMode = false");
			modeI.setText("interactMode = false");
		}
		//Working - im an idiot with copy paste... 
		
		
	}
	
	//Replace the edit pane contents for the element selected.
	private void replaceEditPane(Pane newEditPane) {
		propertyPane.getChildren().removeAll();
		propertyPane.getChildren().add(newEditPane);
		return;
	}
		
	//TO Speed up, change this to initComboEditPane and create pane elements in
	//outside scope, then when refreshing them, only edit those elements that change
	
	//ELEments will have to be added to properties pane - that way they can be directly edited from inside other methods here, ie click objects
	private void updatedComboEditPane(StageElement elm) {

		//@Override
		VBox comboBoxPane = new VBox(10); //like above but used for spacing
		VBox comboBoxOptions = new VBox(2); //combo box editable options
		
		Label EditPaneName = new Label("ComboBox Options");
		EditPaneName.setTextFill(Color.WHITE);
		
		//Move into own method
		refreshOptions(elm.getOptions(), comboBoxOptions, elm);
		
		ScrollPane s1 = new ScrollPane();
		s1.setPannable(true);
		s1.setPrefSize(170, 180);
		s1.setContent(comboBoxOptions);
		
		TextField newEntry = new TextField();
		
    	comboBoxPane.getChildren().removeAll();
		comboBoxPane.getChildren().addAll(EditPaneName, s1, newEntry);
		
		propertyPane.getChildren().clear();
		propertyPane.getChildren().addAll(comboBoxPane);	
		//return newPane;
		
		newEntry.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent ke) {
	            if (ke.getCode().equals(KeyCode.ENTER)) {
	            	if(!newEntry.equals("")){
		            	comboBoxOptions.getChildren().removeAll();
		            	elm.getOptions().add(newEntry.getText());
		            	refreshOptions(elm.getOptions(), comboBoxOptions, elm);
		            	newEntry.setText("");
		            	s1.setContent(comboBoxOptions);
		            	
		            	comboBoxPane.getChildren().clear();
		            	propertyPane.getChildren().clear();
		            	comboBoxPane.getChildren().addAll(EditPaneName, s1, newEntry);
		        		propertyPane.getChildren().addAll(comboBoxPane);
	            	}
	            }
	        }
		});
	}
	
	//public void updatedPropertyPane()
	
	
	public VBox refreshOptions(LinkedList<String> o, VBox comboBoxOptions, StageElement elm){
		
		for(int i = 0; i < elm.getOptions().size(); i++){
			System.out.println(elm.getOptions().get(i));
			HBox EntryBox = new HBox(10);
			
			Button deleteButton = new Button("X");
			final int temp = i; //used to carry over index inside of method below
			deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                System.out.println("del this entry");
	                elm.getOptions().remove(temp);
	                updatedComboEditPane(elm);
	                //Pane newEditPane = displayEditOptions(propertyPane, element);
	                //again, only works when i click enter
	                refreshOptions(elm.getOptions(), comboBoxOptions, elm);
	            }
	        });
			
			Label newEntry = new Label(elm.getOptions().get(i));
			EntryBox.getChildren().addAll(deleteButton, newEntry);
			comboBoxOptions.getChildren().add(EntryBox);
		}
		
		//set the combobox option on stage
		//problem converting Node to ComboBox
		refreshComboBox(o, (ComboBox)elm.getElement());
		
		return comboBoxOptions;
		
	}


	private void refreshComboBox(LinkedList<String> o, ComboBox elm) {
		
		elm.getItems().clear();
		//element.getItems().removeAll();
		elm.getItems().setAll(o);
		//element.getItems().addAll(o);	
	}
	
}  


