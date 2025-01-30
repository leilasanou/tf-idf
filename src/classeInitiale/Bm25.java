package classeInitiale;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bm25 {
	HashMap<Document, double[] > tf;
	 double[] idf;
	private double k1;
	private double b;
	private double avgDl;
	
	public Bm25(HashMap<Document, double[]> tf, double[] idf, double k1, double b, double avgDl) {
		super();
		this.tf = tf;
		this.idf = idf;
		this.k1 = k1;
		this.b = b;
		this.avgDl = avgDl;
	}
	
	
	
	 public Bm25() {
		 this.k1 = 1.5; 
	        this.b = 0.75;
	}



	public Bm25(double k1, double b) {
		super();
		this.k1 = k1;
		this.b = b;
	}

	public Bm25(int voc) {
	    this.idf = new double[voc]; 
	}

	public double getK1() {
		return k1;
	}



	public HashMap<Document, double[]> getTf() {
		return tf;
	}



	public double[] getIdf() {
		return idf;
	}



	public void setK1(double k1) {
		this.k1 = k1;
	}



	public double getB() {
		return b;
	}



	public void setB(double b) {
		this.b = b;
	}



	public double getAvgDl() {
		return avgDl;
	}



	public void setAvgDl(double avgDl) {
		this.avgDl = avgDl;
	}



	public void setTf(HashMap<Document, double[]> tf) {
		this.tf = tf;
	}



	public void setIdf(double[] idf) {
		this.idf = idf;
	}



	public Bm25 processCorpus(Corpus c) {
	        Vocabulary voc = Vocabulary.getUnique();
	        tf = new HashMap<>();
	        
	        int totalDocs = c.getDocuments().size();
	        int[] docLengths = new int[totalDocs];  

	       
	        for (int docI = 0; docI < totalDocs; docI++) {
	            Document d = c.getDocuments().get(docI);
	            double[] frequence = new double[voc.getSize()];
	            int totalMots = d.size();
	            docLengths[docI] = totalMots; 

	           
	            for (String mot : d.getContenu().split(" ")) {
	            	 if (!voc.stopWord(mot)) {
	                Integer id = voc.getMotId(mot);
	                if (id != null) {
	                    frequence[id]++;
	                }
	            	 }
	            }

	           
	            for (int i = 0; i < frequence.length; i++) {
	                frequence[i] = frequence[i] / totalMots;
	            }

	            tf.put(d, frequence); 
	        }

	       
	        idf = new double[voc.getSize()];
	        for (int wordId = 0; wordId < voc.getSize(); wordId++) {
	            int docFreq = 0;

	            
	            for (double[] docTf : tf.values()) {
	                if (docTf[wordId] > 0) {
	                    docFreq++;
	                }
	            }

	            // Calcul de l'IDF qui est differente de celle de la classe tfidf
	            idf[wordId] = Math.log((double) (totalDocs - docFreq + 0.5) / (docFreq + 0.5) + 1.0);
	        }

	    
	        int totalLength = 0;
	        for (int length : docLengths) {
	            totalLength += length;
	        }
	        avgDl = totalLength / (double) totalDocs;

	        return this;
	    }
	//method feature
	public double[] features(String requete) {
        Vocabulary voc = Vocabulary.getUnique();
        int taille = voc.getSize();
        double[] r = new double[taille]; 
        int cpt = 0; 

       
        for (String m : requete.split(" ")) {
        	 if (!voc.stopWord(m)) {
            cpt++;
            Integer id = voc.getMotId(m);  
            if (id != null) {
                r[id]++;
            }
        }
        }

       
        if (cpt > 0) {
            for (int i = 0; i < r.length; i++) {
                r[i] = r[i] / cpt;
            }
        }

        for (int i = 0; i < r.length; i++) {
        
            double tf = r[i];
            if (tf > 0) {
                double tfAd = tf * (k1 + 1) / (tf + k1 * (1 - b + b * avgDl));
                r[i] = tfAd * idf[i];  
            }
        }

        return r;
    }

    
    public void calculateAvgDl(Corpus corpus) {
        int totalLength = 0;
        int numDocs = corpus.getDocuments().size();

        
        for (Document doc : corpus.getDocuments()) {
            totalLength += doc.size();
        }

       
        avgDl = (double) totalLength / numDocs;
    }

    public HashMap<Document, Double> evaluate(double[] vecteurF) {
        HashMap<Document, Double> scoresDocuments = new HashMap<>();

       
        for (Document doc : tf.keySet()) {
            double[] tfVal = tf.get(doc); 

            
            double scoreDoc = 0.0;

        
            int docLength = doc.size();
            
            for (int i = 0; i < tfVal.length; i++) {
               
                double tfDoc = tfVal[i];
                
               
                double tfAd = tfDoc * (k1 + 1) / (tfDoc + k1 * (1 - b + b * (double) docLength / avgDl));
                
                
                scoreDoc += tfAd * idf[i] * vecteurF[i];
            }

           
            scoresDocuments.put(doc, scoreDoc);
        }

        return scoresDocuments;
    }
    public void processQuery(String requete, int max) {
        
        double[] queryVector = features(requete);  
  
        HashMap<Document, Double> coef = evaluate(queryVector);  

        List<Map.Entry<Document, Double>> scores = new ArrayList<>(coef.entrySet());
   	 
    	Comparator<Map.Entry<Document, Double>> comparator = Map.Entry.<Document, Double>comparingByValue().reversed();
    	scores.sort(comparator);
        
        System.out.println("les " + max + " meilleurs documents pour la requête : " + requete);
        for (int i = 0; i < Math.min(max, scores.size()); i++) {
            Map.Entry<Document, Double> entry = scores.get(i);
            Document doc = entry.getKey();
            double score = entry.getValue();
            System.out.println("Document : " + doc.getTitre() + " | coef : " + score);
        }
    }

    
}

   
  


	 