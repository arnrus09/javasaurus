package helloTest.java;


import java.io.File;
import java.util.*;
import java.util.regex.*;


public class Process1 {
	static ArrayList test2 = new ArrayList();
public static void main(String[] args){
	TextGetter tg = new TextGetter("/Users/russellarnold/irconjs.txt");
	tg.getText();
	tg.getText();
	String stest = tg.getText();
	while( stest!=null){
		test2.add(Arrays.asList(stest.toLowerCase().split(" ")));
		stest = tg.getText();
	}
	PersistentList irgvrbs = PersistentList.open(new File("irgvrbs.obj"));
	//irgvrbs.addAll(test2);
	System.out.println(irgvrbs.size());
	System.out.println(irgvrbs);
	irgvrbs.sync();
}
}


