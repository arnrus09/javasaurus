package javasaurus;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public	class Thesaurus{
	
	static Shelf thesaurus = Shelf.open(new File("soule_thesaurus.obj"));
	static Random rand = new Random();
	
	
	public static String getWord(String word){
		String capWord = word.substring(0,1).toUpperCase()+word.substring(1);
		String face;
		if(thesaurus.keySet().contains(capWord)){
			rand.nextInt();
			ArrayList words = (ArrayList)thesaurus.get(capWord);
			int size = words.size();
			face = (String)words.get(rand.nextInt(size));
	}
		else
			face = word;
		return face;	
}
	public static void main(String[] args){
		System.out.println(getWord("avenge"));
		System.out.println(getWord("event"));
		
		
	}
}