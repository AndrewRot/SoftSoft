package stageElement;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TextFieldElement extends StageElement{


	TextField element;
	static String codeSnippet;//should it be a linked list of string too?
	
	public TextFieldElement(TextField e, int x, int y, String c){
		super(x, y);
		this.element = e;
		this.codeSnippet = c;
	}
	
	public Node getElement(){
		return this.element;
	}
	
}
