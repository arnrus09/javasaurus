package helloTest;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.util.*;


public class SentenceManager {
	
	private static String[] tempHelplers = "be 	am 	is 	are was 	were 	been 	being have 	has 	had 	could should 	would 	may 	might must 	shall 	can 	will  do 	did 	does 	having".split("\\s");
	private static List<String> helpers = Arrays.asList(tempHelplers);
	
private static List<String> taggerator(String sentence){
	MaxentTagger tagger = new MaxentTagger(
			"taggers/english-left3words-distsim.tagger");
	String tagged = tagger.tagString(sentence);
	System.out.println(tagged);
	return Arrays.asList(tagged.split(" "));
}

private static String findHas(String taggedSentence){
	String[] hasCnjgs = {"has","have","had","Has","Have","Had"};
	for(String cnj: hasCnjgs){
		String rgx = ".*"+cnj+"_V.+_V.*";
		if(taggedSentence.matches(rgx))
			return cnj;
}
	return null;
}




public static List<WordManager> getWMs(String sentence, String searcharg){
	ArrayList<WordManager> wms = new ArrayList<WordManager>();
	List<String> tagged = taggerator(sentence);
	boolean hBeTest = tagged.toString().matches(".*_VB.*VB.*")? true: false;
	boolean participle = tagged.toString().contains("VBN")? true: false;
	//System.out.println(tagged.toString());
	for( String tw: tagged){
		String untw = tw.substring(0, tw.indexOf("_"));
		if(hBeTest && helpers.contains(untw))
			wms.add(new WordManager(untw));
		else if(participle && helpers.contains(untw))
			wms.add(new WordManager(untw));
		else{
			WordManager tempWm = new WordManager(tw, searcharg);
			wms.add(tempWm);
			int tempDex = wms.indexOf(tempWm);
			if( tempDex > 0)
				tempWm.previous = wms.get(tempDex - 1);
		}
	}
	return wms;
}


public static void main(String[] args){
	List<WordManager> wms = getWMs("The new problem has the advantage of drawing a fairly sharp line between the physical and the intellectual capacities of a man. No engineer or chemist claims to be able to produce a material which is indistinguishable from the human skin. It is possible that at some time this might be done, but even supposing this invention available we should feel there was little point in trying to make a \"thinking machine\" more human by dressing it up in such artificial flesh. The form in which we have set the problem reflects this fact in the condition which prevents the interrogator from seeing or touching the other competitors, or hearing -their voices. Some other advantages of the proposed criterion may be shown up by specimen questions and answers.","hypo");
	for(int i = 1; i < 11; i++ ){
	for(WordManager wm: wms)
		System.out.print( wm.retrieve()+" " );
	System.out.println();
	}
	
}
}
