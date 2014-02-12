package helloTest.java;

import java.io.File;
import java.util.*;

public class Conjugation {

	static PersistentList irgvrbs = PersistentList.open(new File("irgvrbs.obj"));
	int j = 0;
	String verb;
	ArrayList<String>temp4;
	
	public Conjugation(String verb){
		this.verb = verb;
	}

	public String irgConjd2inf(){
		for(Object vb: irgvrbs){
			List temp = (List)vb;
			for( Object temp2 : temp){
				String temp3 = (String)temp2;
				temp4 = new ArrayList(Arrays.asList(temp3.split("/")));
			if(temp4.contains(verb)){
				j = temp4.indexOf(verb);
				return (String) temp.get(0);
			}
			}
		}
		return verb;
	}
	
	//returns string possibly incorrect strings.  requires handling during searches(s,.
	public String regConjd2inf(){
		int end = verb.length();
		if ( verb.endsWith("ed"))
			end = verb.lastIndexOf("ed");
		else if ( verb.endsWith("ing"))
			end = verb.lastIndexOf("ing");
		else if ( verb.endsWith("ses"))
			end = verb.lastIndexOf("es");
		else if ( verb.substring( verb.length()-2 ).matches("[a-z]s"))
			end = verb.lastIndexOf("s");
		return verb.substring(0,end);
		
		}
	
	public String irgInf2conjd(){
		try{
		return temp4.get(j);
	}
		catch(Exception e){
			return verb;
		}
	}
	
	public static void main(String[] args){
		Conjugation test1 = new Conjugation("cracks");
		System.out.println(test1.regConjd2inf());
		Conjugation test2 = new Conjugation("was");
		System.out.println(test2.irgConjd2inf());
		System.out.println(test2.irgInf2conjd());
		
	}
	
	}
	

