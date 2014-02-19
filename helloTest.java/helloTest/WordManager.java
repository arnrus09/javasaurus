package helloTest;

import java.io.IOException;
import java.util.*;

public class WordManager {
		
	boolean firstLetterHappend = false;
	boolean isA = false;
	static Random rand = new Random(47);
	String word;
	String current;
	WordManager previous = null;
	String tag;
	String usage;
	boolean plural = false;
	List<String> searchedWords = new ArrayList<String>();
	Inflector inflector = Inflector.getInstance();
	
	public WordManager(String word){
		this.word = word;
	}

	public WordManager(String taggedWord, String searcharg){
		word = taggedWord.substring(0, taggedWord.indexOf("_"));
		tag = taggedWord.substring(taggedWord.indexOf("_")+1);
		String searchWord = word;
		current = word;
		if(tag.contains("V")){ usage = "v"; searchWord = ProcessTenses.toFpp(word);}
		else if(tag.contains("JJ")) usage = "a";
		else if(tag.contains("RB")) usage = "r";
		else if(tag.contains("NN")){ usage = "n";
			if( tag.endsWith("S")){
				searchWord = inflector.singularize(word);
				plural = true;
			}
		}
		
		else 
			return;
		try{
			searchedWords = Searcher.searchWN( searchWord, searcharg, usage );
		}
		catch(IOException e){
			System.out.println(e.toString());
		}

		}
	
	public boolean isA(){
		boolean ans = (word.equals("a")||word.equals("an")) ? true: false;
		return ans;
		}
	
	public boolean firstLetterHappend(){
		return firstLetterHappend;
	}
	
	public static void fixAs(List<WordManager> lwm){
		for( int i = 0 ; i < lwm.size() ; i++ ){
			if( lwm.get(i).isA() ){
				if( lwm.get( i + 1 ).current.matches( "[aeiouAEIOU].*" ) ){
					lwm.get(i).word = "an";
				}
				else{
					lwm.get(i).word = "a";
				}
			}
		}
	}
	
	
	public String retrieve(){
			
		if( searchedWords.isEmpty() ) return word;
		String searchResult = searchedWords.get(rand.nextInt(searchedWords.size()));
		if( usage.equals("v")){
			current = ProcessTenses.revert(searchResult, tag);
		}
		else if( plural ){
			current = inflector.pluralize(searchResult);
		}
		else{
			current = searchResult;	
			firstLetterHappend = false;
		}
		if( previous != null && previous.word.matches("an?")){
			previous.word = current.matches("[aeiouhAEIOUH].*") ? "an" : "a";
			firstLetterHappend = true;
		}
		return current;
	}
	
	public String toString(){
		return word;
	}
	
	public static void main(String[] args){
		List<WordManager> wms = SentenceManager.getWMs("is this a building or a house?","hypo");
		for (int i = 1; i < 10; i ++){
			fixAs(wms);
		for ( WordManager wm: wms )
			System.out.print(wm.retrieve()+" ");
		System.out.println();
		}
	}
	
}
