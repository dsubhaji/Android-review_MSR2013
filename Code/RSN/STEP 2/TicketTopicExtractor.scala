import scalanlp.io._;
import scalanlp.stage._;
import scalanlp.stage.text._;
import scalanlp.text.tokenize._;
import scalanlp.pipes.Pipes.global._;

import edu.stanford.nlp.tmt.stage._;
import edu.stanford.nlp.tmt.model.lda._;
import edu.stanford.nlp.tmt.model.SymmetricDirichletParams;
import java.io.PrintWriter;
import java.io.File;
import java.util.HashMap;

object TicketTopicExtractor {

	def extractTopics(inputFilePath: String, outputFilePath: String, numTopics: Int,
	    beta: Float, alpha: Float, iterations: Int): Unit = {

			println("Hi There");
			val source = CSVFile(inputFilePath) ~> IDColumn(1);
			//val testSource= CSVFile("ValidationTickets.csv");//ValidationAllCategories100BinnedDriftsLastTimestamp.csv
			val tokenizer = {
					SimpleEnglishTokenizer() ~>            // tokenize on space and punctuation
					CaseFolder() ~>                        // lowercase everything
					WordsAndNumbersOnlyFilter() ~>         // ignore non-words and non-numbers
					MinimumLengthFilter(3)                 // take terms with >=3 characters
			}

			val text = {
					source ~>                              // read from the source file
					Column(3) ~>                           // select column containing text
					TokenizeWith(tokenizer) ~>             // tokenize with tokenizer above
					TermCounter() ~>                       // collect counts (needed below)
					TermMinimumDocumentCountFilter(10) ~>   // filter terms in <4 docs
					TermDynamicStopListFilter(0)  ~>      // filter out 30 most common terms
					DocumentMinimumLengthFilter(1)         // take only docs with >=5 terms
			}

			val trainingData = LDADataset(text);

			val params = LDAModelParams(numTopics = numTopics, dataset = trainingData, topicSmoothing = SymmetricDirichletParams(alpha),
					termSmoothing = SymmetricDirichletParams(beta));
			// if the directory exists delete it
			if(new File(outputFilePath+"/Output").exists()){
				  new File(outputFilePath).delete();
			}
			val modelPath = file(outputFilePath+"/Output");
			val model = TrainGibbsLDA(params, trainingData, output=modelPath, maxIterations=iterations);

	}

}
TicketTopicExtractor.extractTopics("./review.csv","./temp/",30,0.1f,0.1f,1000);
