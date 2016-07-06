//package SoftSoftApp;

//public class SSApp {

//}
package SoftSoftApp;

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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Callback;

import javafx.application.Application;



public class SSApp extends Application {

	Pane root;
	Pane currentSongPane;
	Pane previewPane;
	ImageView currAlbumImage;
	Label currSongArtist;
	Label currSongTitle;
	
	//private HashMap<ID3v1, MP3File> songs;
	ArrayList<String> code = new ArrayList<String>();
	
	ScrollPane musicPane;
	
	File albumArtFile = new File("iDJGraphics/defaultAlbumCover.png");
	Image defaultCoverPic = new Image(albumArtFile.toURI().toString());
	//temp
	File albumArtFile2 = new File("iDJGraphics/defaultAlbumCover2.png");
	Image defaultCoverPic2 = new Image(albumArtFile2.toURI().toString());
	

	Desktop d = Desktop.getDesktop();
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception{
		
		
		root = new Pane();
		
		Scene scene = new Scene(root, 1100, 750);

		File iconFile = new File("iDJGraphics/tempIcon.png");
        Image iconImage = new Image(iconFile.toURI().toString());
		primaryStage.getIcons().add(iconImage);
		primaryStage.setTitle("EZTunes");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		//*****currentSongPane preferences (on top)********
		currentSongPane = new Pane();
		currentSongPane.setStyle("-fx-background-color: #ffffff");
		currentSongPane.setMinWidth(400);
		currentSongPane.setMaxWidth(400);
		currentSongPane.setMinHeight(70);
		currentSongPane.setMaxHeight(70);
		currentSongPane.relocate(600, 20);
		
		//current song image
		File currSongAlbumFile = new File("iDJGraphics/defaultAlbumCover.png");
		Image currAlbumImagePic = new Image(currSongAlbumFile.toURI().toString());
		currAlbumImage = new ImageView(currAlbumImagePic);
		currAlbumImage.setFitWidth(50); currAlbumImage.setFitHeight(50);
		currAlbumImage.relocate(5, 5);
		
		//current song title and artist
		VBox currTitleBox = new VBox(1);
		currTitleBox.setAlignment(Pos.CENTER);
		currTitleBox.setMinWidth(250);
		currTitleBox.setMaxWidth(250);
		currTitleBox.setTranslateX(125);
		currTitleBox.relocate(-30, 0);
		currSongArtist = new Label("Template: ");
		currSongTitle = new Label("Not selected");
		
		
		currentSongPane.getChildren().addAll(currAlbumImage, currTitleBox);
		root.getChildren().add(currentSongPane);
		
		Label label1 = new Label("Code:");
		TextField textField = new TextField ();
		HBox inputCodeBox = new HBox();
		inputCodeBox.getChildren().addAll(label1, textField);
		inputCodeBox.setSpacing(10);
		inputCodeBox.relocate(20, 20);
		root.getChildren().add(inputCodeBox);
		
		Button generateFile = new Button("Generate");
		generateFile.relocate(250, 20);
		root.getChildren().add(generateFile);
		generateFile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("*Generate File*");
                generateCode(code, textField.getText());
            }
        });
		
		//Preview pane
		previewPane = new Pane();
		previewPane.setStyle("-fx-background-color: #ffffff");
		previewPane.setMinWidth(400);
		previewPane.setMinHeight(480);
		previewPane.relocate(600, 100);
		Rectangle previewImage = new Rectangle(400, 480);
		previewImage.relocate(0, 0);
		previewPane.getChildren().addAll(previewImage);
		root.getChildren().add(previewPane);
		
		
		//list templates
		HBox templates = new HBox();
		Rectangle t1 = new Rectangle(300, 150);
		t1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("t1");
                previewImage.setFill(Color.DARKGREY);
            }
        });
		t1.setFill(Color.DARKGREY);
		Rectangle t2 = new Rectangle(300, 150);
		t2.setFill(Color.BLUE);
		t2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("t2");
                previewImage.setFill(Color.BLUE);
            }
        });
		Rectangle t3 = new Rectangle(300, 150);
		t3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("t3");
                previewImage.setFill(Color.GREEN);
            }
        });
		t3.setFill(Color.GREEN);
		templates.setSpacing(10);
		templates.getChildren().addAll(t1, t2, t3);
		
		ScrollPane templatePane = new ScrollPane();
		templatePane.setContent(templates);
		templatePane.setPrefHeight(180);
		templatePane.setPrefWidth(700);
		templatePane.relocate(300, 550);
		root.getChildren().add(templatePane);
		
		//***************End of stage pane************
		
		//***************Create Code here ***************
		
		//First in form of a List of strings
		/* Codes
		 * 1 -library links
		 * 2 - public class app
		 * 3 - launch
		 * 4 - start
		 * 5 - insert stage elements
		 * 6 - stage actions
		 * 7 - functions
		 */
		//This list contains lines of code that will be appended together at the end
		//to a readable file
		LinkedList code = new LinkedList<String>();
		
		code.add("//1");
		code.add("//2");
		code.add("//3");
		
		
		//on launch find all music files
		//and print it to the console
		//"/Users/andrewrottier/Music/iTunes/iTunes Media/Music"
		//"/Users/andrewrottier/Music/"
		File dir = new File("/Users/andrewrottier/Music/iTunes/iTunes Media/Music"); //replace with root dir later
		//add back, iTunes/iTunes Media/Music
		//searchDirectory(dir);
		
		//print contents of the hashmap
		//displayMusic();
	
	}
	
	//generate the code of the java file with information selected
	private ArrayList<String> generateCode(ArrayList<String> c, String packName) {
		//grab name of package
		c.add("package " + packName + ";");
		c.add("");
		
		//Insert important imports
		addImports(c);
		
		//Start code *** change packname to file name - probs just get rid of package all together
		c.add("public class " + packName + " extends Application {");
		c.add("");
		
		//*****INSERT GLOBAL VARIABLES HERE, ie root pane and such*****
		
		//Launch the program!
		addLaunch(c);
		
		//****INSERT stage objects here
		
		//close the main
		c.add("}");
		
		//***INSERT FUNCTIONS HERE
		
		//Close the application
		c.add("}");
		
		//Print content of c first
		printSourceCode(c);
		
		//return final array *** probably change to .txt file or .java file
		return c;
	}
	
	
	private void addLaunch(ArrayList<String> c) {
		c.add("public static void main(String[] args) {");
		c.add("launch(args);");
		c.add("}");
		c.add("public void start(Stage primaryStage) throws Exception{");
		c.add("");
	}

	//Can granulate these later once we know specific requirements
	//for a software template
	private void addImports(ArrayList<String> c) {
		c.add("import java.*;"); 
		c.add("import javafx.*;");
		c.add("");
	}

	private void printSourceCode(ArrayList<String> c) {
		for(int i = 0; i < c.size(); i++){
			System.out.println(c.get(i));
		}
		
	}

}
