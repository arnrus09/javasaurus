package helloTest.java;


import java.io.IOException;
import java.util.*;
import java.io.*;

//this method thanks to Pavel Repin
public class Searcher {
	
	static String convertStreamToString(InputStream is) {
	    Scanner s = new Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
	
public static String[] tag(String sentence) throws IOException{
	
	File home = new File("/Users/russellarnold");
	List<String> theArgs = new ArrayList<String>();
	theArgs.add("python");
	//theArgs.add("getTagsTagger.py");
	theArgs.add("testingpy.py");
	theArgs.add("still");
	theArgs.add("working");
	//for( String r: sentence.split(" "))
	//	theArgs.add(r);
	//System.out.println(theArgs.toString());
	System.out.println(theArgs.toString());
	
	ProcessBuilder pb = new ProcessBuilder(theArgs);
	
	pb.directory(home);
	 Process p = pb.start();
	String temp = convertStreamToString(p.getInputStream());
	System.out.println(temp);
	String[] tagged = temp.split(",");
	System.out.println(tagged.toString());
	return tagged;
}
public static void main(String[] args){
	try{
	tag("what is happening?");
}
	catch(IOException e){
		System.out.println(e.toString());
	}
}
}
