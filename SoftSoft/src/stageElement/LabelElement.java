package stageElement;

import java.util.LinkedList;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import stageElement.StageElement;

public class LabelElement extends StageElement {

	Label element;
	static String codeSnippet;//should it be a linked list of string too?
	
	public LabelElement(Label e, int x, int y, String c){
		super(x, y);
		this.element = e;
		this.codeSnippet = c;
		
	}
	
	public Node getElement(){
		return this.element;
	}
	
}
