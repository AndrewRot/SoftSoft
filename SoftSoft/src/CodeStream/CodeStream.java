package CodeStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class CodeStream {

	ArrayList<String> code;
	//List<String> list = new ArrayList<>(Arrays.asList(array));
	
	/*
	 * CodeStream - current - array - identifiers at odd locations and 
	 * code at even - code is in form of a linkedlist for easy insertion
	 * id add (adds last) - at the end iterate through array and 
	 * add everything to a text file, if even, helper to add all lines 
	 * from the linked list
	 */
	public CodeStream(ArrayList<String> c){
		//ArrayList<String> list = new ArrayList<String>();
		this.code = initializeCodeStream(c);
	}
	
	private ArrayList<String> initializeCodeStream(ArrayList<String> c){
		LinkedList<LinkedList<String>> newCode = new LinkedList<LinkedList<String>>();
		//im guessing because an array wants to be static in size
		//look into using a vector so space is dynamically allocated
		//need wifis, or look for structor that can contain arbitrary
		//data types, string and linkedlist
		//OR insert lines of string one at a time - painful.. 
		//can do LinkedList<LinkedList<String>> - I believe
		for(int i = 0; i < 6; i++){
			if(i % 2 != 0){
				newCode.add(new LinkedList<String>());
			}
			else{
				
			}
		}
		return code;
		
	}
	
	 
	 
	
}
