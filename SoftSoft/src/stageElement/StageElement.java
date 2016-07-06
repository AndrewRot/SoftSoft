package stageElement;

import java.util.LinkedList;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


//Classes will extend this class to get customizeable features
public class StageElement {
	
	/*
	 * Need reconstruction of this class
	 * This class needs X, Y, code snippet, and other class that IS all types of elements
	 * generic stage element class - not this one
	 * 
	 */
	
	
	//make Node insteadog image
	//protected Node element; //move to sub classes as specific element
	private ImageView Pic;//or change to actual stage element if i can..
	private int X;
	private int Y;
	
	
	//Color/style/code
	
	public StageElement(int x, int y){
		//this.element = e;
		this.X = x;
		this.Y = y;
	}
	
	public Node getElement(){
		return null;
	}
	
	public int getX(){
		return this.X;
	}
	
	public int getY(){
		return this.Y;
	}
	
	public LinkedList<String> getOptions(){
		return null;//this.options;
	}
	

	

	public Pane displayEditOptions(Pane propertyPane, Node element2) {
		// TODO Auto-generated method stub
		System.out.print("In parent class");
		return null; //does this override? - i mean the younger method,
		//does it override this?
	}
		
}
