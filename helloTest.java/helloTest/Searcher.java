package helloTest;


import java.io.IOException;
import java.util.*;
import java.io.*;

//this method thanks to Pavel Repin
public class Searcher {
	
	static String convertStreamToString(InputStream is) {
	    Scanner s = new Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
	
public static List<String> searchWN( String word, String wnarg, String usage ) throws IOException{
	
	File home = new File("/Users/russellarnold");
	List<String> theArgs = new ArrayList<String>();
	theArgs.add("python");
	theArgs.add("search_wn.py");
	theArgs.add(word);
	theArgs.add(wnarg);
	theArgs.add(usage);
	//System.out.println(theArgs.toString());
	
	ProcessBuilder pb = new ProcessBuilder(theArgs);
	
	pb.directory(home);
	 Process p = pb.start();
	String temp = convertStreamToString(p.getInputStream());
	//System.out.println(temp);
	String[] temp2 = temp.split(",");
	List<String> words = new ArrayList<String>();
	for(String s: temp2){
		String s2 = s.trim();
		int newlen = s2.length()-1;
		if (newlen > 0) words.add(s.trim().substring(1,newlen));
	}
	//System.out.println(words.toString());
	return words;
}
public static void main(String[] args){
	try{
	System.out.println(searchWN("house","hype","v").toString());
}
	catch(IOException e){
		System.out.println(e.toString());
	}
}
}
