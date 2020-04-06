package group1.nick.coreNLP;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class SentimentAnalysis {

	public static int computeSentiment(String text) {
		
		StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
		
		//Text used for testing
		//String text = "Hello this is John. I really hate this place. I love animals so darn much that it makes me so happy to think about!";
		
		CoreDocument coreDocument = new CoreDocument(text);
		
		stanfordCoreNLP.annotate(coreDocument);
		
		//List<CoreSentence> sentences = coreDocument.sentences();
		
		int totalSentiment = 0;
		int sentenceCount = 0;
		
		Annotation annotation = stanfordCoreNLP.process(text);
		 for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
             Tree tree = sentence.get(SentimentAnnotatedTree.class);
             int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
             //System.out.println(sentence.toString() + ": "+ sentiment);
             sentenceCount++;
             totalSentiment += sentiment;
		 }
		 
		 double averageSentiment = (double) totalSentiment/sentenceCount;
		 //System.out.println("Average Sentiment: " + averageSentiment);
		 //System.out.println("Rounded value: " + Math.round(averageSentiment));
		 
		 return (int) Math.round(averageSentiment);
		 /*
		for(CoreSentence sentence: sentences) {
			String sentiment = sentence.sentiment();
			System.out.println(sentence+"\t"+sentiment);
		}
		*/
	}
}