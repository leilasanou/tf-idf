package classeInitiale;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class TfIdf {
	HashMap<Document, double[] > tf;
	 double[] idf;
	 //constructeur sans argument
	
	/* je fais appel à la seule instance de vocabulary qui existe et j'ajoute les mots du corpus à vocabulaire
	 * l'identifiant unique est crée grace à cpt
	 */
	 public TfIdf() {
	        this.tf = new HashMap<>(); 
	        this.idf = null;         
	    }
	 
	 //ajouter un critère pour ignorer les mots de la stop word et construire un vocabulaire unique à partir des mots
	 
	 
	 public void vocabulaire(Corpus c) {
		 Vocabulary voc = Vocabulary.getUnique();
		 for (Document doc : c.getDocuments()) {
			
			 for (String a : doc.getContenu().split(" ")) {
				 if (!voc.stopWord(a)) {
				 if  (!a.trim().isEmpty()) { //ultra necesaire car j'avais un mot vide
				 Mot b = new Mot(a);
				 
		voc.addVocabulary(b);
				 }
				 }
			 }
		 }
	 }
	
	public HashMap<Document, double[]> getTf() {
		return tf;
	}

	public void setTf(HashMap<Document, double[]> tf) {
		this.tf = tf;
	}

	public double[] getIdf() {
		return idf;
	}

	public void setIdf(double[] idf) {
		this.idf = idf;
	}
	

	 public TfIdf processCorpus(Corpus c) {
	       
	        Vocabulary voc = Vocabulary.getUnique(); 
	        tf = new HashMap<>();
	      
	        for (Document d : c.getDocuments()) {
	            double[] frequence = new double[voc.getSize()]; 
	            int totalM = d.size(); 
	            
	           
	            for (String mot : d.getContenu().split(" ")) {
	            	 if (!voc.stopWord(mot)) {
	                Integer id = voc.getMotId(mot);
	                if (id != null) {
	                    frequence[id]++; 
	                }
	            }
	            }

	           
	            for (int i = 0; i < frequence.length; i++) {
	                frequence[i] = frequence[i]/ totalM; 
	            }
	            

	            tf.put(d, frequence); 
	        }

	       
	        idf = new double[voc.getSize()];

	        for (int wordId = 0; wordId < voc.getSize(); wordId++) {
	            int cpt = 0;

	           
	            for (double[] docTf : tf.values()) {
	                if (docTf[wordId] > 0) {
	                    cpt++;
	                }
	            }

	          
	            idf[wordId] = Math.log((double) c.getDocuments().size() / (1 + cpt));
	        }

	        return this; 
	    }
	 
	 //feature 
	 public double [] features (String requete) {
		 Vocabulary voc = Vocabulary.getUnique();
		 int taille = voc.getSize();
		 double [] r = new double [taille];
		 int cpt = 0;
		 for (String m: requete.split(" ")) {
			 if (!voc.stopWord(m)) {
			 cpt++;
			 Integer id = voc.getMotId(m);
			 if(id != null) {
			 r[id]++;
			 }
		 }
		 }
		
		    if (cpt > 0) {
		        for (int i = 0; i < r.length; i++) {
		            r[i] = r[i] / cpt;
		        }
		    }
		 return r;
	 }
//evaluate tfr = tableau frequence rareté
	 HashMap<Document, Double> evaluate (double [] vecteurF) {
		 HashMap<Document, Double> scoresDocuments = new HashMap<>() ;
		
		 for (Document doc : tf.keySet()) {
			    double[] tfVal = tf.get(doc);
			  
			    double [] tfr = new double [tfVal.length];
			    for (int i = 0; i < tfVal.length; i++) {
			    	tfr[i] = tfVal [i] + idf[i];
			    }
			    double produitS = 0.0;
			    double normeR = 0.0;
			  
				double normeD = 0.0;
			    for (int i = 0; i < vecteurF.length; i++) {
			    	normeR += vecteurF[i] * vecteurF[i] ;
			    	normeD +=  tfr[i] * tfr[i];
			    	produitS += vecteurF[i] * tfr[i];
			    }
			    double cosinus = produitS / Math.sqrt(normeD) * Math.sqrt(normeR);
			    scoresDocuments.put(doc, cosinus);
			}

		 return scoresDocuments;
	 }
	//method processQuerry
	 
    public void processQuery (String requete, int max) {
    	double [] f = features(requete);
    	 HashMap<Document, Double> coef = evaluate(f);
    	 List<Map.Entry<Document, Double>> scores = new ArrayList<>(coef.entrySet());
    	 
    	Comparator<Map.Entry<Document, Double>> comparator = Map.Entry.<Document, Double>comparingByValue().reversed();
    	scores.sort(comparator);
    	
    	System.out.println("les" + max + " meilleurs documents sont : " + requete);
    	int cpt = 0;
    	for (Map.Entry<Document, Double> entry : scores) {
    	    if (cpt >= max) break; 
    	    System.out.println( entry.getKey().getTitre() + " | coef : " + entry.getValue());
    	    cpt++;
    	}

    }

	@Override
	public String toString() {
		return "TfIdf [tf=" + tf + ", idf=" + Arrays.toString(idf) + "]";
	}
	
	 
}
