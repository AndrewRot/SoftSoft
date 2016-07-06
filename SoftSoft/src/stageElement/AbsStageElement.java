package stageElement;

public abstract class AbsStageElement {

	String name; //this is the ID
	Location loc;
	int stageID;
	//code snip it for item - probs in custom elements
	
	//Color/style?
	
	public AbsStageElement(String n, Location loc, int id){
		this.name = n;
		this.loc = loc;
		this.stageID = id;
	}
	
}
