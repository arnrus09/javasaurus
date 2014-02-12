package javasaurus;

import java.io.File;
import java.util.*;
import java.util.regex.*;


public class Process1 {
	static HashMap<String, ArrayList> test2=new HashMap<String, ArrayList>();
public static void main(String[] args){
	TextGetter tg = new TextGetter("/Users/russellarnold/arnrus09_repo/java/Javasaurus/soule_sorted4.txt");
	test2.put("t",new ArrayList());
	String stest = tg.getText();
	String ktest = stest;
	while( stest!=null){
		if (stest.length()>1)
			if (stest.substring(0,1).matches("[A-Z]")){
				test2.put(stest,new ArrayList());
				ktest = stest;}
		else if(stest.length()>1)
			if (stest.trim().substring(0,1).matches("[a-z]"))
				test2.get(ktest).add(stest.trim());

		stest = tg.getText();
	}
	test2.get("t").add(tg.getText());
	Shelf thesaurus = Shelf.open(new File("soule_thesaurus.obj"));
	thesaurus.putAll(test2);
	thesaurus.sync();
	System.out.println(thesaurus.keySet().size());
	System.out.println(thesaurus);
}
}

