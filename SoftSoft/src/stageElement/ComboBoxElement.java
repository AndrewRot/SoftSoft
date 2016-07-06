package stageElement;

import java.util.LinkedList;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ComboBoxElement extends StageElement{
	
	ComboBox element;
	static String codeSnippet;//should it be a linked list of string too?
	public static LinkedList<String> options;
	
	public ComboBoxElement(ComboBox e, int x, int y, String c, LinkedList<String> o){
		super(x, y);
		this.element = setProperties(e);
		this.codeSnippet = c;
		this.options = o;
		
		
	}
	
	private ComboBox setProperties(ComboBox e){
		e.getStyleClass().add("combo-border");
		e.setMinWidth(100.0);
		//e.setEditable(true);
		e.setPrefWidth(e.getMinWidth());
		e.setVisibleRowCount(10);
		return e;
	}
	
	public Node getElement(){
		return this.element;
	}
	
	public LinkedList<String> getOptions(){
		return this.options;
	}
	
	
	
	
	//This method must return a Pane filled with edit options,
	//use helpers to added different kinds of display elements
	//add them all to the returned pane
	//Code below is good for 1 component of the final edit pane.
	
	public Pane displayEditOptions(Pane propertyPane, Node element) {
		
		//@Override
		//element.getItems().addAll(options);
		Pane newPane = new Pane(); //attach all edit options in here & return it
		VBox comboBoxPane = new VBox(10); //like above but used for spacing
		VBox comboBoxOptions = new VBox(2); //combo box editable options
		
		Label EditPaneName = new Label("ComboBox Options");
		//EditPaneName.setLayoutX(20);
		//EditPaneName.setLayoutY(100);
		EditPaneName.setTextFill(Color.WHITE);
		
		//Move into own method
		refreshOptions(options, comboBoxOptions, newPane);
		
		ScrollPane s1 = new ScrollPane();
		s1.setPannable(true);
		s1.setPrefSize(170, 180);
		//s1.setLayoutX(20);
		//s1.setLayoutY(150);
		s1.setContent(comboBoxOptions);
		
		TextField newEntry = new TextField();
		newEntry.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent ke) {
	            if (ke.getCode().equals(KeyCode.ENTER)) {
	            	//Not working, still addingblack entries
	            	if(!newEntry.equals("")){
	            		//**PROBLEM, not removing old list, just adding
		            	//entire list over again, untill clicked out and
	            		//then reselected
		            	comboBoxOptions.getChildren().removeAll();
		            	options.add(newEntry.getText());
		            	refreshOptions(options, comboBoxOptions, newPane);
		            	newEntry.setText("");
		            	s1.setContent(comboBoxOptions);
	            	}
	            	System.out.println("enter text");
	            }
	        }
		});
		comboBoxPane.getChildren().addAll(EditPaneName, s1, newEntry);
		
		newPane.getChildren().addAll(comboBoxPane);	
		return newPane;
	}
	
	//public redisplay
	
	//**** DUDE, if we pass the pane, just update the elements on it! - problem is
	//updating the parent with these changes constantly - 
	
	
	//Refresh the items in the scrollpane. -Temporarily added passing the pane..
	//Needs to completely refresh when we delete an entry. Right now, 
	//it only deletes when we click the delete button and then click enter.
	public VBox refreshOptions(LinkedList<String> o, VBox comboBoxOptions, Pane pane){
		
		for(int i = 0; i < this.options.size(); i++){
			System.out.println(this.options.get(i));
			HBox EntryBox = new HBox(10);
			
			Button deleteButton = new Button("X");
			final int temp = i; //used to carry over index inside of method below
			deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                System.out.println("del this entry");
	                options.remove(temp);
	                //Pane newEditPane = displayEditOptions(propertyPane, element);
	                //again, only works when i click enter
	                refreshOptions(options, comboBoxOptions, pane);
	            }
	        });
			
			Label newEntry = new Label(this.options.get(i));
			EntryBox.getChildren().addAll(deleteButton, newEntry);
			comboBoxOptions.getChildren().add(EntryBox);
		}
		
		//set the combobox option on stage
		refreshComboBox(o);
		
		return comboBoxOptions;
		
	}


	private void refreshComboBox(LinkedList<String> o) {
		element.getItems().clear();
		//element.getItems().removeAll();
		element.getItems().setAll(o);
		//element.getItems().addAll(o);	
	}
	
}