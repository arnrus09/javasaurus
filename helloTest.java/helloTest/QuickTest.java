package helloTest;

import java.util.*;
import java.lang.reflect.*;


public class QuickTest {
	
public static void main(String[] args){
	LinkedList<String> test = new LinkedList<String>();
	Method[] methods = LinkedList.class.getDeclaredMethods();
	for( Method m: methods){
		try{
				m.invoke(test, "test");
				System.out.println(test + "\t\t" + m.getName());
		}
		catch(Exception e){
			;
		}
	}
	}

}
