package program;

import java.awt.List;
import java.util.ArrayList;

public class SimpleDocument {
	public ArrayList<Character> doc=new ArrayList<Character>();
	public void insertString(int begin,int end,String s){
		ArrayList<Character> after=new ArrayList<Character>(doc.subList(end,doc.size()));
		doc=new ArrayList<Character>(doc.subList(0, begin));
		doc.addAll(stringToCharList(s));
		doc.addAll(after);
	}
	public void deleteString(int begin,int end){
		ArrayList<Character> after=new ArrayList<Character>(doc.subList(end,doc.size()-1));
		doc=new ArrayList<Character>(doc.subList(0, begin));
		doc.addAll(after);
	}
	public SimpleDocument(){
		doc=new ArrayList<Character>();
	}
	private ArrayList<Character> stringToCharList(String s){
		ArrayList<Character> l=new ArrayList<Character>();
		for(char c:s.toCharArray()){
			l.add(new Character(c));
		}
		return l;
	}
	
}
