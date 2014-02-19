package helloTest;


import java.io.*;

public class TextGetter
{
	FileReader reader;
	File f;
	BufferedReader br;
	
	public TextGetter(String fname){
		try {
			f = new File(fname);
		    reader = new FileReader(f);
		    br = new BufferedReader(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	    
	public void close(){
	    try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	

	public String getText(){	
		try
		{
			String out = (br.readLine());
		    return out;
		}
		catch (Exception e)
		{
			System.err.println ("Unable to read from file");
			System.exit(-1);
			return "error";
		}
	}	
	
	

	

	public static void main (String args[]){
		PersistentList pluralKeys = PersistentList.open( new File("plural_keys.obj"));
		TextGetter tg = new TextGetter("helloTest/Inflector.java");
		String result = tg.getText();
		while(result != null){
			if( result.contains( "inflect.addSingularize(" ) ){
				pluralKeys.add(result.substring(result.indexOf("(")+1, result.indexOf(",") ) );
			}
			result = tg.getText();
		}
		System.out.println(pluralKeys.toString());
		pluralKeys.sync();
	}
}

